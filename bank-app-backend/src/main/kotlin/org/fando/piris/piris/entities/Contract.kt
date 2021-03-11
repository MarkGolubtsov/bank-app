package org.fando.piris.piris.entities

import org.fando.piris.piris.models.ContractTypeEnum
import org.fando.piris.piris.models.CurrencyEnum
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
open class Contract(
        open val number: String,
        open val currency: CurrencyEnum,
        open val contractStartDate: LocalDate,
        open val contractEndDate: LocalDate,
        open val amount: BigDecimal,
        open val percents: Double,
        @ManyToOne
        open val client: Client,
        @Enumerated(EnumType.STRING)
        open val contractType: ContractTypeEnum,
        @OneToOne
        open val depositType: Deposits? = null, //TODO: replace with deposit info
        @OneToOne
        open val creditType: Credits? = null

) : AbstractJpaPersistable() {
}