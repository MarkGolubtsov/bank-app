package org.fando.piris.piris.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSetter
import java.time.LocalDate

data class IdDocumentInfo(
        @JsonProperty("id")
        val id: Long?,
        @JsonProperty("series", required = true)
        val passportSeries: String,
        @JsonProperty("number", required = true)
        val passportNumber: String,
        @JsonProperty("issuedBy", required = true)
        val issuer: String,
        @JsonProperty("dateOfIssue", required = true)
        val issueDate: LocalDate,
        @JsonProperty("identificationNumber", required = true)
        val idNumber: String
)
