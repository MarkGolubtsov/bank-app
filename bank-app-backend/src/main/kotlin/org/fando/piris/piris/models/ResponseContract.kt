package org.fando.piris.piris.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.fando.piris.piris.entities.Client
import org.fando.piris.piris.entities.Credits
import org.fando.piris.piris.entities.Deposits
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

data class ResponseContract(
        @JsonProperty("contractNumber")
        val contractNumber: String,
        @JsonProperty("currecncy")
        val currency: CurrencyEnum,
        @JsonProperty("startDate")
        val contractStartDate: LocalDate,
        @JsonProperty("endDate")
        val contractEndDate: LocalDate,
        @JsonProperty("amount")
        val amount: BigDecimal,
        @JsonProperty("percents")
        val percents: BigDecimal,
        @JsonProperty("clientId")
        val clientId: Long?,
        @JsonProperty("contractType")
        val contractType: ContractTypeEnum,
        @JsonProperty("depositName")
        val depositType: String? = null,
        @JsonProperty("creditName")
        val creditType: String? = null,
        @JsonProperty("status")
        val status: StatusEnum
) {
}