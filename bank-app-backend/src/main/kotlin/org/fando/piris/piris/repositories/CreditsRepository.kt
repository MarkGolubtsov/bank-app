package org.fando.piris.piris.repositories

import org.fando.piris.piris.entities.Credits
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditsRepository : JpaRepository<Credits, Long> {
}