package me.dio.credit.request.system.service

import me.dio.credit.application.system.entity.Address
import me.dio.credit.request.system.entity.Credit
import me.dio.credit.request.system.entity.Customer
import me.dio.credit.request.system.repository.CreditRepository
import me.dio.credit.request.system.services.impl.CreditService
import me.dio.credit.request.system.services.impl.CustomerService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class CreditServiceTest {
    @Mock
    lateinit var creditRepository: CreditRepository

    @Mock
    lateinit var customerService: CustomerService

    @InjectMocks
    lateinit var creditService: CreditService

    @BeforeEach
    fun setUp() {

    }

    @AfterEach
    fun tearDown() {

    }

    @Test
    fun `should create credit`() {
        //given
        val credit: Credit = buildCredit()
        val customerId: Long = 1L

        Mockito.`when`(customerService.findById(customerId)).thenReturn(credit.customer!!)
        Mockito.`when`(creditRepository.save(credit)).thenReturn(credit)

        //when
        val actual: Credit = creditService.save(credit)

        //then
        Mockito.verify(customerService, Mockito.times(1)).findById(customerId)
        Mockito.verify(creditRepository, Mockito.times(1)).save(credit)

        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(credit)
    }



    companion object {
        private fun buildCredit(
            creditValue: BigDecimal = BigDecimal.valueOf(100.0),
            dayFirstInstallment: LocalDate = LocalDate.now().plusMonths(2L),
            numberOfInstallments: Int = 15,
            customer: Customer = buildCustomer()
        ): Credit = Credit(
            creditValue = creditValue,
            dayFirstInstallment = dayFirstInstallment,
            numberOfInstallments = numberOfInstallments,
            customer = customer
        )

        private fun buildCustomer(
            firstName: String = "Cami",
            lastName: String = "Cavalcante",
            cpf: String = "28475934625",
            email: String = "camila@gmail.com",
            password: String = "12345",
            zipCode: String = "12345",
            street: String = "Rua da Cami",
            income: BigDecimal = BigDecimal.valueOf(1000.0),
            id: Long = 1L
        ) = Customer(
            firstName = firstName,
            lastName = lastName,
            cpf = cpf,
            email = email,
            password = password,
            address = Address(
                zipCode = zipCode,
                street = street
            ),
            income = income,
            id = id
        )
    }
}
