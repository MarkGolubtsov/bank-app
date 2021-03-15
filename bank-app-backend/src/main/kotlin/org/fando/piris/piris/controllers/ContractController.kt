package org.fando.piris.piris.controllers

import org.fando.piris.piris.models.RequestContract
import org.fando.piris.piris.models.StatusEnum
import org.fando.piris.piris.services.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("contracts")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class ContractController @Autowired constructor(
        private val contractService: ContractService,
        private val clientService: ClientService,
        private val depositService: DepositService,
        private val creditService: CreditsService,
        private val responseService: ResponseService,
        private val accountService: AccountService
) {

    @PostMapping("deposits/{clientId}")
    fun createDepositContract(@Valid @RequestBody requestContract: RequestContract,
                       @PathVariable("clientId") clientId: Long) : ResponseEntity<Any> {
        val client = clientService.getClientById(clientId)
        val deposit = depositService.getDepositById(requestContract.programId)
        return if (!(client.isPresent && deposit.isPresent)) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client or deposit with given id not found")
        } else {
            val contract = contractService.createContract(requestContract, deposit.get(), client.get())
            accountService.createDepositAccounts(client.get(), contract)
            ResponseEntity.ok().build()
        }
    }

    @PostMapping("credits/{clientId}")
    fun createCreditContract(@Valid @RequestBody requestContract: RequestContract,
                              @PathVariable("clientId") clientId: Long) : ResponseEntity<Any> {
        val client = clientService.getClientById(clientId)
        val credit = creditService.getCreditById(requestContract.programId)
        return if (!(client.isPresent && credit.isPresent)) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client or credit with given id not found")
        } else {
            val contract = contractService.createContract(requestContract, credit.get(), client.get())
            accountService.createCreditAccounts(client.get(), contract);
            ResponseEntity.ok().build()
        }
    }

    @PostMapping("deposits/withdraw/{contractNumber}")
    fun withdrawDeposit(@PathVariable("contractNumber") contractNumber: String): ResponseEntity<Any> {
        val contract = contractService.findContractByNumber(contractNumber);
        return if (contract == null) {
            ResponseEntity.badRequest().body("Contract not found")
        } else {
            contractService.updateContractStatus(contract, StatusEnum.CLOSED)
            accountService.closeAccounts(contract)
            ResponseEntity.ok().build()
        }

    }

    @GetMapping("")
    fun getAllContracts() =
            ResponseEntity.ok(responseService.generateResponseContractEntity(contractService.getAllContracts()))

}