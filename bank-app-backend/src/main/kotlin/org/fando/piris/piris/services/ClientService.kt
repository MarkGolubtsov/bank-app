package org.fando.piris.piris.services

import org.fando.piris.piris.entities.Address
import org.fando.piris.piris.entities.Client
import org.fando.piris.piris.entities.IdDocument
import org.fando.piris.piris.models.ClientStatus
import org.fando.piris.piris.models.RequestClient
import org.fando.piris.piris.models.ResponseClient
import org.fando.piris.piris.repositories.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException
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
                requestClient.isMilitaryPerson,
                ClientStatus.ACTIVE
        )
        return clientRepository.save(client)
    }

    fun getClientById(id: Long) = clientRepository.findById(id)

    fun getAllClients(): MutableList<Client> = clientRepository.findAll()

    fun updateClient(clientId: Long, requestClient: RequestClient): Client? {
        val client = getClientById(clientId);
        if (!client.isPresent) {
            return null
        } else {
            val foundClient = client.get()
            foundClient.birthCountry = requestClient.birthCountry
            foundClient.name = requestClient.name
            foundClient.surname = requestClient.surname
            foundClient.patronymic = requestClient.patronymic
            foundClient.birthDate = requestClient.birthDate
            foundClient.gender = requestClient.gender
            foundClient.birthCountry = requestClient.birthCountry
            foundClient.homeTelephoneNum = requestClient.homeTelephoneNum
            foundClient.mobileTelephoneNum = requestClient.mobileTelephoneNum
            foundClient.email = requestClient.email
            foundClient.company = requestClient.jobInfo?.company
            foundClient.position = requestClient.jobInfo?.position
            foundClient.familyStatus = requestClient.familyStatus
            foundClient.nationality = requestClient.nationality
            foundClient.disability = requestClient.disability
            foundClient.isPensioner = requestClient.isPensioner
            foundClient.monthlyIncome = requestClient.monthlyIncome
            foundClient.isMilitaryPerson = requestClient.isMilitaryPerson
            return clientRepository.save(foundClient)
        }
    }

    fun deactivateClient(client: Client) {
        client.clientStatus = ClientStatus.INACTIVE
        clientRepository.save(client)
    }


}