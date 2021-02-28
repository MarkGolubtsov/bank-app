package org.fando.piris.piris.entities

import org.fando.piris.piris.models.CountriesEnum
import org.fando.piris.piris.models.DisabilityEnum
import org.fando.piris.piris.models.FamilyStatusEnum
import org.fando.piris.piris.models.GenderEnum
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
open class Client(
        open val name: String,
        open val surname: String,
        open val patronymic: String,
        open val birthDate: LocalDate,
        @Enumerated(EnumType.STRING)
        open val gender: GenderEnum,
        @OneToOne
        @JoinColumn(name = "document_id")
        open val idDocument: IdDocument,
        @Enumerated(EnumType.STRING)
        open val birthCountry: CountriesEnum,
        @OneToOne
        @JoinColumn(name = "residential_address")
        open val residentialAddress: Address,
        open val homeTelephoneNum: String?,
        open val mobileTelephoneNum: String?,
        open val email: String?,
        open val company: String?,
        open val position: String?,
        @Enumerated(EnumType.STRING)
        open val familyStatus: FamilyStatusEnum,
        @Enumerated(EnumType.STRING)
        open val nationality: CountriesEnum,
        @Enumerated(EnumType.STRING)
        open val disability: DisabilityEnum,
        open val isPensioner: Boolean,
        open val monthlyIncome: BigDecimal?,
        open val isMilitaryPerson: Boolean
) : AbstractJpaPersistable()