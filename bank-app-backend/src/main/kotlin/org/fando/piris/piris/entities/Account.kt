package org.fando.piris.piris.entities

import org.fando.piris.piris.models.AccountTypeEnum
import org.fando.piris.piris.models.StatusEnum
import java.math.BigDecimal
import javax.persistence.*

@Entity
open class Account(
        open val accountCode: Int,
        open val accountNumber: String,
        @Enumerated(EnumType.STRING)
        open val accountType: AccountTypeEnum,
        open var debit: BigDecimal,
        open var credit: BigDecimal,
        open var surplus: BigDecimal,
        @ManyToOne
        open val client: Client?,
        @Enumerated(EnumType.STRING)
        open var status: StatusEnum,
        @ManyToOne
        open val contract: Contract?
) : AbstractJpaPersistable() {
}