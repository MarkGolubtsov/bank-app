package org.fando.piris.piris.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDate

data class ResponseClient(
        @JsonProperty("id")
        val id: Long?,
        @JsonProperty("name")
        val name: String,
        @JsonProperty("surname")
        val surname: String,
        @JsonProperty("patronymic")
        val patronymic: String,
        @JsonProperty("birthday")
        val birthDate: LocalDate,
        @JsonProperty("gender")
        val gender: GenderEnum,
        @JsonProperty("passport")
        val idDocument: IdDocumentInfo,
        @JsonProperty("placeOfBirth")
        val birthCountry: CountriesEnum,
        @JsonProperty("residentialAddress")
        val residentialAddress: Addresses,
        @JsonProperty("homePhoneNumber")
        val homeTelephoneNum: String?,
        @JsonProperty("mobilePhoneNumber")
        val mobileTelephoneNum: String?,
        @JsonProperty("email")
        val email: String?,
        @JsonProperty("work")
        val jobInfo: JobInfo?,
        @JsonProperty("maritalStatus")
        val familyStatus: FamilyStatusEnum,
        @JsonProperty("nationality")
        val nationality: CountriesEnum,
        @JsonProperty("disability")
        val disability: DisabilityEnum,
        @JsonProperty("pensioner")
        val isPensioner: Boolean,
        @JsonProperty("monthlyIncome")
        val monthlyIncome: BigDecimal?,
        @JsonProperty("military")
        val isMilitaryPerson: Boolean
)
