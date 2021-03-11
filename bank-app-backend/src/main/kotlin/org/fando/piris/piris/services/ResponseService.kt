package org.fando.piris.piris.services

import org.fando.piris.piris.entities.*
import org.fando.piris.piris.models.*
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
            clients.map { client -> generateResponseClientEntity(client, client.idDocument, client.residentialAddress) }

    fun generateResponseDepositEntity(deposits: List<Deposits>) =
            deposits.map { dep -> generateResponseDepositEntity(dep) }

    fun generateResponseDepositEntity(deposit: Deposits) =
            ResponseDeposit(
                    deposit.id,
                    deposit.depositName,
            )

    fun generateResponseContractEntity(contract: Contract) =
            ResponseContract(
                    contract.number,
                    contract.currency,
                    contract.contractStartDate,
                    contract.contractEndDate,
                    contract.amount,
                    contract.percents,
                    contract.client.id,
                    contract.contractType,
                    contract.depositType?.depositName,
                    contract.creditType?.creditName,
                    contract.status
            )

    fun generateResponseContractEntity(contracts: List<Contract>) =
            contracts.map { contract -> generateResponseContractEntity(contract) }

    fun generateResponseAccountEntity(accounts: List<Account>) =
            accounts.map { acc -> generateResponseAccountEntity(acc) }

    fun generateResponseAccountEntity(account: Account) =
            ResponseAccount(
                    account.accountCode,
                    account.accountNumber,
                    account.accountType,
                    account.debit,
                    account.credit,
                    account.surplus,
                    account.client?.id,
                    account.status
            )



}