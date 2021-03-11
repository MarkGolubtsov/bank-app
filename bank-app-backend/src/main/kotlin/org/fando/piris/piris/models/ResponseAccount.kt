package org.fando.piris.piris.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.fando.piris.piris.entities.Client
import java.math.BigDecimal
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.ManyToOne

data class ResponseAccount(
        @JsonProperty("accountCode")
        val accountCode: Int,
        @JsonProperty("accountNumber")
        val accountNumber: String,
        @JsonProperty("accountType")
        val accountType: AccountTypeEnum,
        @JsonProperty("debit")
        var debit: BigDecimal,
        @JsonProperty("credit")
        var credit: BigDecimal,
        @JsonProperty("surplus")
        var surplus: BigDecimal,
        @JsonProperty("clientId")
        val clientId: Long?,
        @JsonProperty("accountStatus")
        var status: StatusEnum
)
