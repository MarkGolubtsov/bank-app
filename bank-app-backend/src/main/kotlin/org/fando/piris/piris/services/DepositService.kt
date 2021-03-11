package org.fando.piris.piris.services

import org.fando.piris.piris.entities.Deposits
import org.fando.piris.piris.repositories.ClientRepository
import org.fando.piris.piris.repositories.DepositRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class DepositService @Autowired constructor(
        private val depositRepository: DepositRepository
) {

    fun getAllDeposits(): List<Deposits> = depositRepository.findAll()

    fun getDepositById(id: Long) = depositRepository.findById(id)

//    fun createDeposit(re) {
//        Deposits()
//    }
}