import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*
import me.dio.credit.request.system.controllers.CreditController
import me.dio.credit.request.system.dto.CreditDTO
import me.dio.credit.request.system.dto.CreditView
import me.dio.credit.request.system.dto.CreditViewList
import me.dio.credit.request.system.entity.Credit
import me.dio.credit.request.system.services.impl.CreditService
import me.dio.credit.request.system.enummeration.Status

class CreditControllerTest {
    @Mock
    private lateinit var creditService: CreditService

    @InjectMocks
    private lateinit var creditController: CreditController

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test saveCredit`() {
        val creditDTO = CreditDTO(
            creditValue = BigDecimal.valueOf(1.0),
            dayFirstOfInstallment = LocalDate.now(),
            numberOfInstallments = 10,
            customerId = 1
        )
        val credit = Credit(
            creditValue = creditDTO.creditValue,
            dayFirstInstallment = creditDTO.dayFirstOfInstallment,
            numberOfInstallments = creditDTO.numberOfInstallments,
            customer = null,
            status = Status.IN_PROGRESS
        )

        `when`(creditService.save(any())).thenReturn(credit)

        val response: ResponseEntity<String> = creditController.saveCredit(creditDTO)

    }

    @Test
    fun `test findAllByCustomerId`() {
        val customerId = 123L
        val creditList = listOf(
            Credit(
                creditValue = BigDecimal.valueOf(1000.0),
                dayFirstInstallment = LocalDate.now(),
                numberOfInstallments = 12,
                customer = null,
                status = Status.IN_PROGRESS
            )
        )

        `when`(creditService.findAllByCustomer(customerId)).thenReturn(creditList)

        val response: ResponseEntity<List<CreditViewList>> = creditController.findAllByCustomerId(customerId)

    }

    @Test
    fun `test findByCreditCode`() {
        val customerId = 123L
        val creditCode = UUID.randomUUID()
        val credit = Credit(
            creditValue = BigDecimal.valueOf(500.0),
            dayFirstInstallment = LocalDate.now(),
            numberOfInstallments = 6,
            customer = null,
            status = Status.IN_PROGRESS
        )

        `when`(creditService.findByCreditCode(customerId, creditCode)).thenReturn(credit)

        val response: ResponseEntity<CreditView> = creditController.findByCreditCode(customerId, creditCode)

    }
}
