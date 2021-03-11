package org.fando.piris.piris.entities

import org.fando.piris.piris.models.DepositTypes
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
open class Credits(
        open val creditName: String,
): AbstractJpaPersistable() {

}