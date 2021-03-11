package org.fando.piris.piris.repositories

import org.fando.piris.piris.entities.Deposits
import org.springframework.data.jpa.repository.JpaRepository

interface DepositRepository : JpaRepository<Deposits, Long> {
}