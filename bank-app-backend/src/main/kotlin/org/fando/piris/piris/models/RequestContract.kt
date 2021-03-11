package org.fando.piris.piris.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDate
import javax.validation.constraints.Pattern

data class RequestContract(
        @JsonProperty("depositProgramId")
        val programId : Long,
        @JsonProperty("contractNumber")
        @Pattern(regexp = "[0-9]{1,63}")
        val contractNumber: String,
        @JsonProperty("currency")
        val currency: CurrencyEnum,
        @JsonProperty("depositStartDate")
        val depositStartDate: LocalDate,
        @JsonProperty("depositEndDate")
        val depositEndDate: LocalDate,
        @JsonProperty("contractStartDate")
        val contractStartDate: LocalDate,
        @JsonProperty("contractEndDate")
        val contractEndDate: LocalDate,
        @JsonProperty("depositAmount")
        val depositAmount: BigDecimal,
        @JsonProperty("percents")
        val percents: Double
)
