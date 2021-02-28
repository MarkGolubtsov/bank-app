package org.fando.piris.piris.controllers

import org.fando.piris.piris.models.RequestClient
import org.fando.piris.piris.models.ResponseClient
import org.fando.piris.piris.services.AddressesService
import org.fando.piris.piris.services.ClientService
import org.fando.piris.piris.services.IdDocumentService
import org.fando.piris.piris.services.ResponseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.xml.ws.Response

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
    fun getClient(@PathVariable("id") clientId: Long): ResponseEntity<ResponseClient> {
        val client = clientService.getClientById(clientId);
        return ResponseEntity.ok(
                responseService.generateResponseClientEntity(client, client.idDocument, client.residentialAddress)
        )
    }


}