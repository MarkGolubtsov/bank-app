package org.fando.piris.piris.repositories

import org.fando.piris.piris.entities.Deposits
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DepositRepository : JpaRepository<Deposits, Long> {


}