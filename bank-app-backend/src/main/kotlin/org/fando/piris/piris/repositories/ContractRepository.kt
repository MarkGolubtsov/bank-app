package org.fando.piris.piris.repositories

import org.fando.piris.piris.entities.Contract
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContractRepository : JpaRepository<Contract, Long> {

    fun findByNumber(number: String): Contract?
}