package org.fando.piris.piris.repositories

import org.fando.piris.piris.entities.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository : JpaRepository<Client, Long> {
}