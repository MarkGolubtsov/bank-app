package org.fando.piris.piris.services

import org.fando.piris.piris.entities.Client
import org.fando.piris.piris.entities.Contract
import org.fando.piris.piris.entities.Credits
import org.fando.piris.piris.entities.Deposits
import org.fando.piris.piris.models.ContractTypeEnum
import org.fando.piris.piris.models.RequestContract
import org.fando.piris.piris.models.StatusEnum
import org.fando.piris.piris.repositories.ContractRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
@Transactional
class ContractService @Autowired constructor(
        private val contractRepository: ContractRepository
) {

    fun findContractByNumber(contractNumber: String) = contractRepository.findByNumber(contractNumber)

    fun updateContractStatus(contract: Contract, status: StatusEnum) {
        contract.status = status
        contractRepository.save(contract)
    }

    fun createContract(requestContract: RequestContract, deposits: Deposits, client: Client): Contract {
        val contractNumber: String = generateContractNumber(client)
        val contract = Contract(
                contractNumber,
                requestContract.currency,
                requestContract.startDate,
                requestContract.endDate,
                requestContract.amount,
                requestContract.percents,
                client,
                ContractTypeEnum.DEPOSIT,
                StatusEnum.ACTIVE,
                depositType = deposits,
                )
        return contractRepository.save(contract)
    }

    fun createContract(requestContract: RequestContract, credit: Credits, client: Client): Contract {
        val contractNumber: String = generateContractNumber(client)
        val contract = Contract(
                contractNumber,
                requestContract.currency,
                requestContract.startDate,
                requestContract.endDate,
                requestContract.amount,
                requestContract.percents,
                client,
                ContractTypeEnum.CREDIT,
                StatusEnum.ACTIVE,
                creditType = credit
        )
        return contractRepository.save(contract)
    }

    fun getAllContracts(): List<Contract> = contractRepository.findAll()

    private fun generateContractNumber(client: Client) = "${client.idDocument.idNumber}${client.id}${Timestamp.valueOf(LocalDateTime.now()).time}"
}