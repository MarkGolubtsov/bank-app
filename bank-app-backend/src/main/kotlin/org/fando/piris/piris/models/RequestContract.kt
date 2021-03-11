package org.fando.piris.piris.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDate
import javax.validation.constraints.Pattern

data class RequestContract(
        @JsonProperty("depositProgramId")
        val programId : Long,
        @JsonProperty("currency")
        val currency: CurrencyEnum,
        @JsonProperty("depositStartDate")
        val depositStartDate: LocalDate,
        @JsonProperty("depositEndDate")
        val depositEndDate: LocalDate,
        @JsonProperty("depositAmount")
        val depositAmount: BigDecimal,
        @JsonProperty("percents")
        val percents: Double
)
