package org.fando.piris.piris.entities

import org.fando.piris.piris.models.CountriesEnum
import javax.persistence.*

@Entity
open class Address(
        @Enumerated(EnumType.STRING)
        open val country: CountriesEnum,
        open val city: String,
        open val street: String,
        open val buildingNumber: Int,
        open val appartsNumber: Int?
) : AbstractJpaPersistable()