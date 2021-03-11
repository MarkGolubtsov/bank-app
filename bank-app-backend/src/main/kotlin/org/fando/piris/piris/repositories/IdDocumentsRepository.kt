package org.fando.piris.piris.repositories

import org.fando.piris.piris.entities.IdDocument
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IdDocumentsRepository : JpaRepository<IdDocument, Long> {

    fun existsByPassportNumber(passportNumber: String): Boolean
}