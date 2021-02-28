package org.fando.piris.piris.services

import org.fando.piris.piris.entities.Address
import org.fando.piris.piris.entities.Client
import org.fando.piris.piris.entities.IdDocument
import org.fando.piris.piris.models.RequestClient
import org.fando.piris.piris.models.ResponseClient
import org.fando.piris.piris.repositories.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class ClientService @Autowired constructor(
        private val clientRepository: ClientRepository
) {

    fun saveClient(requestClient: RequestClient, idDocument: IdDocument, residentialAddress: Address): Client {
        val client = Client(
                requestClient.name,
                requestClient.surname,
                requestClient.patronymic,
                requestClient.birthDate,
                requestClient.gender,
                idDocument,
                requestClient.birthCountry,
                residentialAddress,
                requestClient.homeTelephoneNum,
                requestClient.mobileTelephoneNum,
                requestClient.email,
                requestClient.jobInfo?.company,
                requestClient.jobInfo?.position,
                requestClient.familyStatus,
                requestClient.nationality,
                requestClient.disability,
                requestClient.isPensioner,
                requestClient.monthlyIncome,
                requestClient.isMilitaryPerson
        )
        return clientRepository.save(client)
    }

    fun getClientById(id: Long) = clientRepository.getOne(id);

}