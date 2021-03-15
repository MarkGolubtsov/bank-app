package org.fando.piris.piris.repositories

import org.fando.piris.piris.entities.SimulatedInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SimulatedInfoRepository : JpaRepository<SimulatedInfo, Long> {
}