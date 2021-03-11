package org.fando.piris.piris.services

import org.fando.piris.piris.entities.Credits
import org.fando.piris.piris.entities.Deposits
import org.fando.piris.piris.repositories.CreditsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class CreditsService @Autowired constructor(
        val creditsRepository: CreditsRepository
){

    fun getAllCredits(): List<Credits> = creditsRepository.findAll()

    fun getCreditById(id: Long) = creditsRepository.findById(id)
}