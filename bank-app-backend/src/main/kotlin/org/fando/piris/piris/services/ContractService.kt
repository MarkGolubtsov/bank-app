package org.fando.piris.piris.services

import org.fando.piris.piris.entities.Client
import org.fando.piris.piris.entities.Contract
import org.fando.piris.piris.entities.Deposits
import org.fando.piris.piris.models.ContractTypeEnum
import org.fando.piris.piris.models.RequestContract
import org.fando.piris.piris.repositories.ContractRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class ContractService @Autowired constructor(
        val contractRepository: ContractRepository
) {

    fun createContract(requestContract: RequestContract, deposits: Deposits, client: Client): Contract {
        val contractNumber: String = generateContractNumber(client)
        val contract = Contract(
                contractNumber,
                deposits,
                requestContract.currency,
                requestContract.depositStartDate,
                requestContract.depositEndDate,
                requestContract.depositAmount,
                requestContract.percents,
                client,
                ContractTypeEnum.DEPOSIT
        )
        return contractRepository.save(contract)
    }

    private fun generateContractNumber(client: Client) = "${client.idDocument.idNumber}${client.id}"
}