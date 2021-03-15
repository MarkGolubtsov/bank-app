package org.fando.piris.piris.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class ResponseCloseDate(
    @JsonProperty("closeDate")
    val closeDateTime: LocalDate
)
