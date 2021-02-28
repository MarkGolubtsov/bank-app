package org.fando.piris.piris.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.fando.piris.piris.entities.Address
import org.fando.piris.piris.entities.IdDocument
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

data class RequestClient(
        @JsonProperty("name", required = true)
        val name: String,
        @JsonProperty("surname", required = true)
        val surname: String,
        @JsonProperty("patronymic", required = true)
        val patronymic: String,
        @JsonProperty("birthday", required = true)
        val birthDate: LocalDate,
        @JsonProperty("gender", required = true)
        val gender: GenderEnum,
        @JsonProperty("passport", required = true)
        val idDocument: IdDocumentInfo,
        @JsonProperty("placeOfBirth", required = true)
        val birthCountry: CountriesEnum,
        @JsonProperty("residentialAddress", required = true)
        val residentialAddress: Addresses,
        @JsonProperty("homePhoneNumber", required = false)
        val homeTelephoneNum: String?,
        @JsonProperty("mobilePhoneNumber", required = false)
        val mobileTelephoneNum: String?,
        @JsonProperty("email", required = false)
        val email: String?,
        @JsonProperty("work", required = false)
        val jobInfo: JobInfo?,
        @JsonProperty("maritalStatus", required = true)
        val familyStatus: FamilyStatusEnum,
        @JsonProperty("nationality", required = true)
        val nationality: CountriesEnum,
        @JsonProperty("disability", required = true)
        val disability: DisabilityEnum,
        @JsonProperty("pensioner", required = true)
        val isPensioner: Boolean,
        @JsonProperty("monthlyIncome", required = false)
        val monthlyIncome: BigDecimal?,
        @JsonProperty("military", required = true)
        val isMilitaryPerson: Boolean
)
