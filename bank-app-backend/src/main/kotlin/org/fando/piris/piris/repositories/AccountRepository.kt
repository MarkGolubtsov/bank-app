package org.fando.piris.piris.repositories

import org.fando.piris.piris.entities.Account
import org.fando.piris.piris.entities.Contract
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {

    fun findAccountByAccountCode(accountCode: Int) : Account

    fun findAccountsByContract(contract: Contract): List<Account>
}