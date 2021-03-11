package org.fando.piris.piris.models

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseDeposit(
        @JsonProperty("depositProgramId")
        val depositProgramId: Long?,
        @JsonProperty("programName")
        val programName: String,
) {

}