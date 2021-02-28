package org.fando.piris.piris.services

import org.fando.piris.piris.entities.IdDocument
import org.fando.piris.piris.models.IdDocumentInfo
import org.fando.piris.piris.repositories.IdDocumentsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class IdDocumentService @Autowired constructor(
        val idDocumentsRepository: IdDocumentsRepository
) {

    fun saveDocument(documentInfo: IdDocumentInfo): IdDocument {
        val idDocument = IdDocument(
                documentInfo.passportSeries,
                documentInfo.passportNumber,
                documentInfo.issuer,
                documentInfo.issueDate,
                documentInfo.idNumber
        )
        return idDocumentsRepository.save(idDocument)
    }

}