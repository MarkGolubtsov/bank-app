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
        val contract = Contract(
                requestContract.contractNumber,
                deposits,
                requestContract.currency,
                requestContract.contractStartDate,
                requestContract.contractEndDate,
                requestContract.depositAmount,
                requestContract.percents,
                client,
                ContractTypeEnum.DEPOSIT
        )
        return contractRepository.save(contract)
    }
}