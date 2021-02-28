package org.fando.piris.piris.repositories

import org.fando.piris.piris.entities.Address
import org.springframework.data.jpa.repository.JpaRepository

interface AddressRepository : JpaRepository<Address, Long> {
}