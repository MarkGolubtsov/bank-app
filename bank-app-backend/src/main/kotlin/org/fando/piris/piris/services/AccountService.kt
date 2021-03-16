package org.fando.piris.piris.services


import org.fando.piris.piris.entities.Account
import org.fando.piris.piris.entities.Client
import org.fando.piris.piris.entities.Contract
import org.fando.piris.piris.models.AccountCodes
import org.fando.piris.piris.models.AccountTypeEnum
import org.fando.piris.piris.models.DepositTypes
import org.fando.piris.piris.models.StatusEnum
import org.fando.piris.piris.repositories.AccountRepository
import org.fando.piris.piris.repositories.SimulatedInfoRepository
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
    private val accountRepository: AccountRepository,
    private val supportingService: SupportingService
) {

    fun closeAccounts(contract: Contract) {
        val accounts = accountRepository.findAccountsByContract(contract);
        val bankAccount = accountRepository.findAccountByAccountCode(AccountCodes.BDF.code)
        accounts.filter { it.status != StatusEnum.CLOSED }.map { acc ->
            acc.status = StatusEnum.CLOSED
            if (acc.accountCode == AccountCodes.DEP.code) {
                acc.credit = acc.credit.plus(contract.amount)
                bankAccount.debit = bankAccount.debit.plus(contract.amount)
            }
            accountRepository.save(acc)
        }
        accountRepository.save(bankAccount)
    }

    fun getCashAccount() = accountRepository.findAccountByAccountCode(AccountCodes.CASH.code)

    fun getAllAccounts(): List<Account> = accountRepository.findAll()

    fun replenishBankAccount(amount: BigDecimal) {
        val bankAccount = accountRepository.findAccountByAccountCode(AccountCodes.BDF.code)
        bankAccount.credit.plus(amount)
    }

    fun getBankAccount() = accountRepository.findAccountByAccountCode(AccountCodes.BDF.code)

    fun createDepositAccounts(client: Client, contract: Contract) {
        val acc = createDepositAccount(client, contract)
        createPercentsDepositAccount(client, contract)
        val cashAcc = getCashAccount()
        cashAcc.debit += contract.amount
        cashAcc.credit += contract.amount
        acc.credit += contract.amount
        acc.debit += contract.amount
        val bankAcc = getBankAccount()
        bankAcc.credit += contract.amount
    }

    fun createCreditAccounts(client: Client, contract: Contract) {
        val acc = createCreditAccount(client, contract)
        createPercentsCreditAccount(client, contract)
        val bankAcc = getBankAccount()
        bankAcc.debit += contract.amount
        acc.debit += contract.amount
        acc.credit += contract.amount
        val cashAcc = getCashAccount()
        cashAcc.debit += contract.amount
        cashAcc.credit += contract.amount
    }

    fun closeDay(closeUntil: LocalDate, currentDate: LocalDate) {
        val countDays = ChronoUnit.DAYS.between(currentDate, closeUntil)
        for (day in 0L..countDays) {
            closeOneDay(currentDate, currentDate.plusDays(day))
        }
    }

    private fun closeOneDay(currentDate: LocalDate, closeDate: LocalDate) {
        val accounts = accountRepository.findAll()
        val bankAccount = getBankAccount()
        val cashAccount = getCashAccount()
        val percentsDepAccounts =
            accounts.filter { it.accountCode == AccountCodes.DEPPRC.code && it.status == StatusEnum.ACTIVE }
        val debitAccounts =
            accounts.filter { it.accountCode == AccountCodes.DEP.code && it.status == StatusEnum.ACTIVE }
        val percentsCreditAccounts =
            accounts.filter { it.accountCode == AccountCodes.CRDPRC.code && it.status == StatusEnum.ACTIVE }
        val creditAccounts =
            accounts.filter { it.accountCode == AccountCodes.CRD.code && it.status == StatusEnum.ACTIVE }
        percentsDepAccounts.forEach {
            if (closeDate.monthValue > currentDate.monthValue && it.contract?.depositType?.depositType == DepositTypes.REVOCABLE) {
                it.debit = it.credit
                cashAccount.debit += it.debit
                cashAccount.credit += it.debit
            }
            val contract = it.contract
            val depositTermInDays = ChronoUnit.DAYS.between(contract?.contractStartDate, contract?.contractEndDate)
            val percentsAmount = contract?.amount?.multiply(contract.percents)?.multiply(BigDecimal(depositTermInDays))
                ?.divide(BigDecimal(365), 2, RoundingMode.HALF_UP)
            val totalAmount = percentsAmount?.divide(BigDecimal(100));
            if (totalAmount != null) {
                it.credit = it.credit.plus(totalAmount)
                bankAccount.debit = bankAccount.debit.plus(totalAmount)
            }
            it.surplus = it.credit - it.debit
            if (closeDate.compareTo(contract?.contractEndDate) == 0) {
                if (it.contract?.depositType?.depositType == DepositTypes.IRREVOCABLE) {
                    it.debit = it.credit
                    cashAccount.debit += it.debit
                    cashAccount.credit += it.debit
                }
                contract?.status = StatusEnum.CLOSED
                it.status = StatusEnum.CLOSED
            }
            accountRepository.saveAndFlush(it)
        }
        debitAccounts.forEach {
            val contract = it.contract
            if (closeDate.compareTo(contract?.contractEndDate) == 0) {
                if (contract?.amount != null) {
                    bankAccount.debit += contract.amount
                    it.credit += contract.amount
                    it.debit += contract.amount
                    cashAccount.debit += contract.amount
                    cashAccount.credit += contract.amount
                }
                contract?.status = StatusEnum.CLOSED
                it.status = StatusEnum.CLOSED
                it.surplus = it.credit - it.debit

            }
            accountRepository.saveAndFlush(it)
        }
        percentsCreditAccounts.forEach {
            val contract = it.contract
            if (closeDate.monthValue > currentDate.monthValue) {
                it.debit = it.credit
                cashAccount.debit += it.debit
                cashAccount.credit += it.debit
            }
            if (contract != null) {
                val percentsAmount: BigDecimal
                if (contract.creditType?.creditName?.equals("First", true) == true) {
                    val creditTermInMonths =
                        ChronoUnit.MONTHS.between(contract.contractStartDate, contract.contractEndDate)
                    val monthlyAmount =
                        contract.amount.divide(BigDecimal(creditTermInMonths), 2, RoundingMode.HALF_UP)
                    val countMonths = ChronoUnit.MONTHS.between(contract.contractStartDate, closeDate)
                    val mainDept =
                        monthlyAmount.times(BigDecimal(countMonths)).let { amount -> contract.amount.minus(amount) }
                    percentsAmount = (mainDept * contract.percents.divide(BigDecimal(100)))
                        .divide(
                            BigDecimal(365),
                            2,
                            RoundingMode.HALF_UP
                        )
                } else {
                    val creditTermInMonths =
                        ChronoUnit.MONTHS.between(contract.contractStartDate, contract.contractEndDate)
                    val percents = contract.percents.divide(BigDecimal(100)).divide(BigDecimal(12))
                    val numerator = contract.amount * (percents * (BigDecimal.ONE + percents).pow(creditTermInMonths.toInt()))
                    val denominator = (BigDecimal.ONE + percents).pow(creditTermInMonths.toInt()) - BigDecimal.ONE
                    val monthlyPay = numerator.divide(denominator, 2, RoundingMode.HALF_UP)
                    percentsAmount = monthlyPay - (contract.amount.divide(BigDecimal(creditTermInMonths)))
                }
                it.credit += percentsAmount
                bankAccount.credit += percentsAmount
            }
            it.surplus = it.debit - it.credit
            accountRepository.saveAndFlush(it)
        }
        creditAccounts.forEach {
            val contract = it.contract
            if (closeDate.compareTo(contract?.contractEndDate) == 0) {
                if (contract?.amount != null) {
                    cashAccount.debit += contract.amount
                    cashAccount.credit += contract.amount
                    it.credit += contract.amount
                    it.debit += contract.amount
                    bankAccount.credit += contract.amount
                }
                contract?.status = StatusEnum.CLOSED
                it.status = StatusEnum.CLOSED
                it.surplus = it.credit - it.debit

            }
            accountRepository.saveAndFlush(it)
        }
        bankAccount.surplus = bankAccount.credit - bankAccount.debit
        cashAccount.surplus = cashAccount.debit - cashAccount.credit
        accountRepository.saveAndFlush(bankAccount)
        accountRepository.saveAndFlush(cashAccount)
        supportingService.updateCurrentDate(closeDate);
    }

    private fun createCreditAccount(client: Client, contract: Contract): Account {
        return accountRepository.save(
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

    private fun createDepositAccount(client: Client, contract: Contract): Account {
        return accountRepository.save(
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

    private fun generateAccountNumber(accountCode: Int, client: Client): String =
        "$accountCode${client.idDocument.passportNumber.substring(0, 5)}${
            (client.accounts.size + 1).toString().padStart(3, '0')
        }${Random.nextInt(0, 9)}"

}