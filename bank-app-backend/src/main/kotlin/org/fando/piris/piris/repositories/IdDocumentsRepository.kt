package org.fando.piris.piris.repositories

import org.fando.piris.piris.entities.IdDocument
import org.springframework.data.jpa.repository.JpaRepository

interface IdDocumentsRepository : JpaRepository<IdDocument, Long> {
}