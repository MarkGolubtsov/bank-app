package org.fando.piris.piris.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDate
import javax.validation.constraints.Pattern

data class RequestContract(
        @JsonProperty("programId")
        val programId : Long,
        @JsonProperty("contractNumber")
        @Pattern(regexp = "[0-9]{1,63}")
        val contractNumber: String,
        @JsonProperty("currency")
        val currency: CurrencyEnum,
        @JsonProperty("startDate")
        val startDate: LocalDate,
        @JsonProperty("endDate")
        val endDate: LocalDate,
        @JsonProperty("amount")
        val amount: BigDecimal,
        @JsonProperty("percents")
        val percents: Double
)
