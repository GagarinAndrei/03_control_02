import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun commissionCalculationMir() {
        // arrange
        val amount = 1000
        val account = AccountType.Mir

        // act
        val result = commissionCalculation(
            transferAmount = amount,
            accountType = account
        )

        // assert
        assertEquals(3500, result)
    }

    @Test
    fun commissionCalculationMirPercents() {
        // arrange
        val amount = 5_000_000
        val account = AccountType.Mir

        // act
        val result = commissionCalculation(
            transferAmount = amount,
            accountType = account
        )

        // assert
        assertEquals(37500, result)
    }

    @Test
    fun commissionCalculationVk() {
        // arrange
        val amount = 1000
        val account = AccountType.VKPay

        // act
        val result = commissionCalculation(
            transferAmount = amount,
            accountType = account
        )

        // assert
        assertEquals(0, result)
    }

    @Test
    fun commissionCalculationMastercard() {
        // arrange
        val amount = 1000
        val account = AccountType.Mastercard

        // act
        val result = commissionCalculation(
            transferAmount = amount,
            accountType = account
        )

        // assert
        assertEquals(26, result)
    }

    @Test
    fun commissionCalculationMaestro() {
        // arrange
        val amount = 40_000
        val account = AccountType.Maestro

        // act
        val result = commissionCalculation(
            transferAmount = amount,
            accountType = account
        )

        // assert
        assertEquals(0, result)
    }

    @Test
    fun commissionCalculationMaestroSixPercent() {
        // arrange
        val amount = 8_000_000
        val account = AccountType.Maestro

        // act
        val result = commissionCalculation(
            transferAmount = amount,
            accountType = account
        )

        // assert
        assertEquals(48020, result)
    }
}