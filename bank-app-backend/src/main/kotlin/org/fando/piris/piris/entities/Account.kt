package org.fando.piris.piris.entities

import org.fando.piris.piris.models.AccountTypeEnum
import org.fando.piris.piris.models.StatusEnum
import java.math.BigDecimal
import javax.persistence.*

@Entity
open class Account(
        open val accountCode: String, //TODO: replace with relation to code
        open val accountNumber: String,
        @Enumerated(EnumType.STRING)
        open val accountType: AccountTypeEnum,
        open val debit: BigDecimal,
        open val credit: BigDecimal,
        open val surplus: BigDecimal,
        @ManyToOne
        open var client: Client,
        @Enumerated(EnumType.STRING)
        open var status: StatusEnum
) : AbstractJpaPersistable() {
}