package org.fando.piris.piris.entities

import java.time.LocalDate
import javax.persistence.Entity

@Entity
open class SimulatedInfo(
        open var currDate: LocalDate
) : AbstractJpaPersistable() {
}