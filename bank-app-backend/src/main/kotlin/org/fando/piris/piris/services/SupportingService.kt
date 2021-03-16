package org.fando.piris.piris.services

import org.fando.piris.piris.repositories.SimulatedInfoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import javax.transaction.Transactional

@Service
@Transactional
class SupportingService @Autowired constructor(
        private val simulatedInfoRepository: SimulatedInfoRepository
) {
    fun getCurrentDate() = simulatedInfoRepository.getOne(1L)

    fun updateCurrentDate(newDate: LocalDate) = getCurrentDate().let {
        it.currDate = newDate
        simulatedInfoRepository.saveAndFlush(it)
    }
}