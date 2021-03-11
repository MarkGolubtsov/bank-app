package org.fando.piris.piris.services


import org.fando.piris.piris.entities.Account
import org.fando.piris.piris.entities.Client
import org.fando.piris.piris.entities.Contract
import org.fando.piris.piris.models.AccountCodes
import org.fando.piris.piris.models.AccountTypeEnum
import org.fando.piris.piris.models.StatusEnum
import org.fando.piris.piris.repositories.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.transaction.Transactional
import kotlin.random.Random

@Service
@Transactional
class AccountService @Autowired constructor(
        private val accountRepository: AccountRepository
) {

    fun closeAccounts(contract: Contract) {
        val accounts = accountRepository.findAccountsByContract(contract);
        val bankAccount = accountRepository.findAccountByAccountCode(AccountCodes.BDF.code)
        accounts.filter {it.status != StatusEnum.CLOSED }.map { acc ->
            acc.status = StatusEnum.CLOSED
            if (acc.accountCode == AccountCodes.DEP.code) {
                acc.credit = acc.credit.plus(contract.amount)
                bankAccount.debit = bankAccount.debit.plus(contract.amount)
            }
            accountRepository.save(acc)
        }
        accountRepository.save(bankAccount)
    }

    fun getAllAccounts(): List<Account> = accountRepository.findAll()

    fun replenishBankAccount(amount: BigDecimal) {
        val bankAccount = accountRepository.findAccountByAccountCode(AccountCodes.BDF.code)
        bankAccount.credit.plus(amount)
    }

    fun getFromBankAccount(amount: BigDecimal) {
        val bankAccount = accountRepository.findAccountByAccountCode(AccountCodes.BDF.code)
        bankAccount.debit.plus(amount)
    }

    fun createDepositAccounts(client: Client, contract: Contract) {
        createDepositAccount(client, contract)
        createPercentsDepositAccount(client, contract)
    }

    fun createCreditAccounts(client: Client, contract: Contract) {
        createCreditAccount(client, contract)
        createPercentsCreditAccount(client, contract)
    }

    fun closeDay() {
        val accounts = accountRepository.findAll()
        val bankAccount = accounts.first { it.accountCode == AccountCodes.BDF.code }
        val percentsDepAccounts = accounts.filter { it.accountCode == AccountCodes.DEPPRC.code }
        val debitAccounts = accounts.filter { it.accountCode == AccountCodes.DEP.code }
        percentsDepAccounts.forEach {
            val contract = it.contract
            val depositTermInDays = ChronoUnit.DAYS.between(contract?.contractStartDate, contract?.contractEndDate)
            val percentsAmount = contract?.amount?.multiply(contract.percents)?.multiply(BigDecimal(depositTermInDays))?.divide(BigDecimal(365), 2, RoundingMode.HALF_UP)
            val totalAmount = percentsAmount?.divide(BigDecimal(100));
            if (totalAmount != null) {
                it.credit.plus(totalAmount)
                bankAccount.debit.plus(totalAmount)
            }
            it.surplus = it.surplus.plus(it.credit.minus(it.debit))
            if (LocalDate.now().compareTo(contract?.contractEndDate) == 0) {
                contract?.status = StatusEnum.CLOSED
                it.status = StatusEnum.CLOSED
            }
            accountRepository.save(it)
        }
        debitAccounts.forEach {
            val contract = it.contract
            if (LocalDate.now().compareTo(contract?.contractEndDate) == 0) {
                contract?.status = StatusEnum.CLOSED
                it.status = StatusEnum.CLOSED
                it.credit = contract?.amount ?: it.credit
                it.surplus = it.surplus.plus(it.credit.minus(it.debit))
                bankAccount.debit = contract?.amount ?: bankAccount.debit
            }
            accountRepository.save(it)
        }
        bankAccount.surplus = bankAccount.surplus.plus(bankAccount.credit.minus(bankAccount.debit))
        accountRepository.save(bankAccount)
    }

    private fun createCreditAccount(client: Client, contract: Contract) {
        accountRepository.save(
                Account(
                        AccountCodes.CRD.code,
                        generateAccountNumber(AccountCodes.CRD.code, client),
                        AccountTypeEnum.PASSIVE,
                        BigDecimal(0),
                        BigDecimal(0),
                        BigDecimal(0),
                        client,
                        StatusEnum.ACTIVE,
                        contract
                )
        )
    }

    private fun createPercentsCreditAccount(client: Client, contract: Contract) {
        accountRepository.save(
                Account(
                        AccountCodes.CRDPRC.code,
                        generateAccountNumber(AccountCodes.CRDPRC.code, client),
                        AccountTypeEnum.PASSIVE,
                        BigDecimal(0),
                        BigDecimal(0),
                        BigDecimal(0),
                        client,
                        StatusEnum.ACTIVE,
                        contract
                )
        )
    }

    private fun createDepositAccount(client: Client, contract: Contract) {
        accountRepository.save(
                Account(
                        AccountCodes.DEP.code,
                        generateAccountNumber(AccountCodes.DEP.code, client),
                        AccountTypeEnum.PASSIVE,
                        BigDecimal(0),
                        BigDecimal(0),
                        BigDecimal(0),
                        client,
                        StatusEnum.ACTIVE,
                        contract
                )
        )
    }

    private fun createPercentsDepositAccount(client: Client, contract: Contract) {
        accountRepository.save(
                Account(
                        AccountCodes.DEPPRC.code,
                        generateAccountNumber(AccountCodes.DEPPRC.code, client),
                        AccountTypeEnum.PASSIVE,
                        BigDecimal(0),
                        BigDecimal(0),
                        BigDecimal(0),
                        client,
                        StatusEnum.ACTIVE,
                        contract
                )
        )
    }

    private fun generateAccountNumber(accountCode: Int, client: Client): String = """
        |$accountCode
        |${client.idDocument.passportNumber.substring(0, 5)}
        |${(client.accounts.size + 1).toString().padStart(3, '0')}
        |${Random.nextInt(0, 9)}
        |""".trimMargin()

}