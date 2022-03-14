const val DAY_LIMIT = 15_000_000
const val MONTH_LIMIT = 60_000_000
const val VK_MONTH_LIMIT = 4_000_000
const val VK_LIMIT = 1_500_000

enum class AccountType {
    VKPay, Mastercard, Maestro, Visa, Mir
}

fun main() {
    printMenu()
    val accountType: AccountType = when (readln().toInt()) {
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

    println(
        "Transfer commission: ${
            if (isLimit(transferAmount, accountType, lastTransferAmount)) commissionCalculation(
                transferAmount,
                accountType
            ) else "Limit is exceeded"
        }"
    )
}

fun commissionCalculation(
    transferAmount: Int,
    accountType: AccountType = AccountType.VKPay,
): Int {
    val calculatedCommission: Int
    val visaMirCommission = (transferAmount * .75 / 100).toInt()
    calculatedCommission = when (accountType) {
        AccountType.Maestro, AccountType.Mastercard ->
            (if (transferAmount in 30_000..7_500_000) 0 else transferAmount * .6 / 100 + 20).toInt()
        AccountType.Visa, AccountType.Mir ->
            if (visaMirCommission <= 3_500) 3_500 else visaMirCommission
        else -> 0
    }
    return calculatedCommission
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

fun isLimit(
    transferAmount: Int,
    accountType: AccountType = AccountType.VKPay,
    lastTransferAmount: Int = 0
): Boolean {
    return (accountType == AccountType.VKPay && (lastTransferAmount <= VK_MONTH_LIMIT || transferAmount <= VK_LIMIT)) ||
            (accountType != AccountType.VKPay && (lastTransferAmount <= MONTH_LIMIT || transferAmount <= DAY_LIMIT))
}