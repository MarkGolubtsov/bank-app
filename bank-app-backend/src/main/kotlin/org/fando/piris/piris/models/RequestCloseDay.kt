package org.fando.piris.piris.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class RequestCloseDay(
        @JsonProperty("closeDateTime")
        val closeUntil: LocalDate
)
