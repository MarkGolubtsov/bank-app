package org.fando.piris.piris.entities

import org.fando.piris.piris.models.AccountTypeEnum
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
open class AccountPlan(
        open val accountCode: String,
        open val description: String,
        @Enumerated(EnumType.STRING)
        open val accountType: AccountTypeEnum
) : AbstractJpaPersistable() {
}