package org.fando.piris.piris.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Addresses(
        @JsonProperty("id")
        val id: Long?,
        @JsonProperty("country", required = true)
        val country: CountriesEnum,
        @JsonProperty("city", required = true)
        val city: String,
        @JsonProperty("street", required = true)
        val street: String,
        @JsonProperty("buildingNumber", required = true)
        val buildingNumber: Int,
        @JsonProperty("apartmentsNumber", required = false)
        val apartsNumber: Int?
)
