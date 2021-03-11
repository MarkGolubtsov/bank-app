package org.fando.piris.piris.controllers

import org.fando.piris.piris.models.RequestContract
import org.fando.piris.piris.services.ClientService
import org.fando.piris.piris.services.ContractService
import org.fando.piris.piris.services.DepositService
import org.fando.piris.piris.services.ResponseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("contracts")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class ContractController @Autowired constructor(
        val contractService: ContractService,
        val clientService: ClientService,
        val depositService: DepositService
) {

    @PostMapping("deposits/{clientId}")
    fun createDepositContract(@Valid @RequestBody requestContract: RequestContract,
                       @PathVariable("clientId") clientId: Long) : ResponseEntity<Any> {
        val client = clientService.getClientById(clientId)
        val deposit = depositService.getDepositById(requestContract.programId)
        return if (!(client.isPresent && deposit.isPresent)) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client or deposit with given id not found")
        } else {
            contractService.createContract(requestContract, deposit.get(), client.get())
            ResponseEntity.ok().build()
        }
    }
}