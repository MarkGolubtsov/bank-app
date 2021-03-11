package org.fando.piris.piris.entities

import org.fando.piris.piris.models.ContractTypeEnum
import org.fando.piris.piris.models.CurrencyEnum
import org.fando.piris.piris.models.StatusEnum
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
open class Contract(
        open val number: String,
        @Enumerated(EnumType.STRING)
        open val currency: CurrencyEnum,
        open val contractStartDate: LocalDate,
        open val contractEndDate: LocalDate,
        open val amount: BigDecimal,
        open val percents: BigDecimal,
        @ManyToOne
        open val client: Client,
        @Enumerated(EnumType.STRING)
        open val contractType: ContractTypeEnum,
        @Enumerated(EnumType.STRING)
        open var status: StatusEnum,
        @OneToMany(mappedBy = "contract")
        open var accounts: MutableList<Account> = mutableListOf(),
        @OneToOne
        open val depositType: Deposits? = null, //TODO: replace with deposit info
        @OneToOne
        open val creditType: Credits? = null

) : AbstractJpaPersistable() {
}