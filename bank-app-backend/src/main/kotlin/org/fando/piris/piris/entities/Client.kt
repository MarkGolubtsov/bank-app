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
        open var name: String,
        open var surname: String,
        open var patronymic: String,
        open var birthDate: LocalDate,
        @Enumerated(EnumType.STRING)
        open var gender: GenderEnum,
        @OneToOne
        @JoinColumn(name = "document_id")
        open var idDocument: IdDocument,
        @Enumerated(EnumType.STRING)
        open var birthCountry: CountriesEnum,
        @OneToOne
        @JoinColumn(name = "residential_address")
        open var residentialAddress: Address,
        open var homeTelephoneNum: String?,
        open var mobileTelephoneNum: String?,
        open var email: String?,
        open var company: String?,
        open var position: String?,
        @Enumerated(EnumType.STRING)
        open var familyStatus: FamilyStatusEnum,
        @Enumerated(EnumType.STRING)
        open var nationality: CountriesEnum,
        @Enumerated(EnumType.STRING)
        open var disability: DisabilityEnum,
        open var isPensioner: Boolean,
        open var monthlyIncome: BigDecimal?,
        open var isMilitaryPerson: Boolean,
        @OneToMany(mappedBy = "client")
        open var accounts: MutableList<Account> = mutableListOf(),
        @OneToMany(mappedBy = "client")
        open var contracts: MutableList<Contract> = mutableListOf()
) : AbstractJpaPersistable()