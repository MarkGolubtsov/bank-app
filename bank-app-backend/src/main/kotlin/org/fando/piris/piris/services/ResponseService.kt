package org.fando.piris.piris.services

import org.fando.piris.piris.entities.Address
import org.fando.piris.piris.entities.Client
import org.fando.piris.piris.entities.IdDocument
import org.fando.piris.piris.models.Addresses
import org.fando.piris.piris.models.IdDocumentInfo
import org.fando.piris.piris.models.JobInfo
import org.fando.piris.piris.models.ResponseClient
import org.springframework.stereotype.Service

@Service
class ResponseService {

    fun generateResponseClientEntity(client: Client, idDocument: IdDocument, residentialAddress: Address): ResponseClient {
        val idDocumentInfo = IdDocumentInfo(
                idDocument.id,
                idDocument.passportSeries,
                idDocument.passportNumber,
                idDocument.issuer,
                idDocument.issueDate,
                idDocument.idNumber
        )
        val residentialAddressInfo = Addresses(
                residentialAddress.id,
                residentialAddress.country,
                residentialAddress.city,
                residentialAddress.street,
                residentialAddress.buildingNumber,
                residentialAddress.appartsNumber
        )
        return ResponseClient(
                client.id,
                client.name,
                client.surname,
                client.patronymic,
                client.birthDate,
                client.gender,
                idDocumentInfo,
                client.birthCountry,
                residentialAddressInfo,
                client.homeTelephoneNum,
                client.mobileTelephoneNum,
                client.email,
                JobInfo(client.company, client.position),
                client.familyStatus,
                client.nationality,
                client.disability,
                client.isPensioner,
                client.monthlyIncome,
                client.isMilitaryPerson
        )
    }

    fun generateResponseClientEntity(clients: List<Client>): List<ResponseClient> =
            clients.map { client -> generateResponseClientEntity(client, client.idDocument, client.residentialAddress)  }

}