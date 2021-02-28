package org.fando.piris.piris.entities

import org.fando.piris.piris.models.DocumentTypeEnum
import java.time.LocalDate
import javax.persistence.*

@Entity
open class IdDocument(
        open val passportSeries: String,
        open val passportNumber: String,
        open val issuer: String,
        open val issueDate: LocalDate,
        open val idNumber: String
) : AbstractJpaPersistable()
