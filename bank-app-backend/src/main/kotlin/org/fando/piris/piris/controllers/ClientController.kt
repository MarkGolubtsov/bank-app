package org.fando.piris.piris.controllers

import org.fando.piris.piris.models.RequestClient
import org.fando.piris.piris.models.ResponseClient
import org.fando.piris.piris.services.AddressesService
import org.fando.piris.piris.services.ClientService
import org.fando.piris.piris.services.IdDocumentService
import org.fando.piris.piris.services.ResponseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("client")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class ClientController @Autowired constructor(
        val clientService: ClientService,
        val idDocumentService: IdDocumentService,
        val addressesService: AddressesService,
        val responseService: ResponseService
) {

    @PostMapping()
    fun createClient(@RequestBody requestClient: RequestClient): ResponseEntity<ResponseClient> {
        val idDocument = idDocumentService.saveDocument(requestClient.idDocument)
        val residentialAddress = addressesService.saveAddress(requestClient.residentialAddress)
        val client = clientService.saveClient(requestClient, idDocument, residentialAddress)
        return ResponseEntity.ok(responseService.generateResponseClientEntity(client, idDocument, residentialAddress))
    }

    @GetMapping("{id}")
    fun getClient(@PathVariable("id") clientId: Long): ResponseEntity<Any> {
        val client = clientService.getClientById(clientId)
        return if (!client.isPresent) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client with given id not found")
        } else {
            ResponseEntity.ok(
                    responseService.generateResponseClientEntity(client.get(), client.get().idDocument, client.get().residentialAddress)
            )
        }
    }

    @PutMapping("{id}")
    fun updateClient(@PathVariable("id") clientId: Long, @RequestBody requestClient: RequestClient): ResponseEntity<Any> {
        val client = clientService.updateClient(clientId, requestClient);
        return if (client == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client with given id not found")
        } else {
            ResponseEntity.ok(responseService.generateResponseClientEntity(client, client.idDocument, client.residentialAddress))
        }
    }

    @DeleteMapping("{id}")
    fun deleteClient(@PathVariable("id") clientId: Long): ResponseEntity<Any> {
        try {
            clientService.deleteClient(clientId)
        } catch (e: EmptyResultDataAccessException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client with given id not found")
        }
        return ResponseEntity.ok().build()
    }

    @GetMapping("all")
    fun getAllClients(): ResponseEntity<List<ResponseClient>> {
        val clients = clientService.getAllClients()
        return ResponseEntity.ok(responseService.generateResponseClientEntity(clients))
    }

}