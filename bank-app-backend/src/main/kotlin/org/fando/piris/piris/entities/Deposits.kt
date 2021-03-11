package org.fando.piris.piris.entities

import org.fando.piris.piris.models.DepositTypes
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
open class Deposits(
        open val depositName: String,
        @Enumerated(EnumType.STRING)
        open val depositType: DepositTypes
) : AbstractJpaPersistable() {

}