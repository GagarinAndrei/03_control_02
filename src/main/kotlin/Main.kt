const val DAY_LIMIT = 15_000_000
const val MONTH_LIMIT = 60_000_000
const val VK_MONTH_LIMIT = 4_000_000
const val VK_LIMIT = 1_500_000

enum class AccountType {
    VKPay, Mastercard, Maestro, Visa, Mir
}

fun main() {
    printMenu()
    val accountTypeNum = readln().toInt()
    val accountType: AccountType = when (accountTypeNum) {
        1 -> AccountType.Maestro
        2 -> AccountType.Mastercard
        3 -> AccountType.Visa
        4 -> AccountType.Mir
        else -> AccountType.VKPay
    }
    print("Enter month transfer amount -> ")
    val lastTransferAmount = readln().toInt()

    print("Enter transfer amount -> ")
    val transferAmount = readln().toInt()

    commissionCalculation(transferAmount, accountType, lastTransferAmount)
}

fun commissionCalculation(
    transferAmount: Int,
    accountType: AccountType = AccountType.VKPay,
    lastTransferAmount: Int = 0
) {
    if ((accountType == AccountType.VKPay && (lastTransferAmount <= VK_MONTH_LIMIT || transferAmount <= VK_LIMIT)) ||
        (accountType != AccountType.VKPay && (lastTransferAmount <= MONTH_LIMIT || transferAmount <= DAY_LIMIT))
    ) {
        val visaMirCommission = (transferAmount * .75 / 100).toInt()
        val calculatedCommission: Int = when (accountType) {
            AccountType.Maestro, AccountType.Mastercard ->
                (if (transferAmount in 30_000..7_500_000) 0 else transferAmount * .6 / 100 + 20).toInt()
            AccountType.Visa, AccountType.Mir ->
                if (visaMirCommission <= 3_500) 3_500 else visaMirCommission
            else -> 0
        }
        println("Transfer commission: $calculatedCommission")
    } else println("Limit is exceeded")
}

fun printMenu() {
    print(
        """Enter account type: 
        | 1 - ${AccountType.Maestro}
        | 2 - ${AccountType.Mastercard}
        | 3 - ${AccountType.Visa}
        | 4 - ${AccountType.Mir}
        | 5 - ${AccountType.VKPay}
        | -> 
    """.trimMargin()
    )
}