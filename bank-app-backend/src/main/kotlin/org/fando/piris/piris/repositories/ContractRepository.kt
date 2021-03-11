package org.fando.piris.piris.repositories

import org.fando.piris.piris.entities.Contract
import org.springframework.data.jpa.repository.JpaRepository

interface ContractRepository : JpaRepository<Contract, Long> {
}