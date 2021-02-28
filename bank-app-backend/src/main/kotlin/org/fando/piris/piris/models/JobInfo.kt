package org.fando.piris.piris.models

import com.fasterxml.jackson.annotation.JsonProperty

data class JobInfo(
        @JsonProperty("place")
        val company: String?,
        @JsonProperty("position")
        val position: String?,
)