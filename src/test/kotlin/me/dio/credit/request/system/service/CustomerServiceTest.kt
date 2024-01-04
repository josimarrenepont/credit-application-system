import me.dio.credit.application.system.entity.Address
import me.dio.credit.request.system.entity.Customer
import me.dio.credit.request.system.exceptions.BusinessException
import me.dio.credit.request.system.repository.CustomerRepository
import me.dio.credit.request.system.services.impl.CustomerService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import java.util.*

@ExtendWith(MockitoExtension::class)
class CustomerServiceTest {
    @Mock
    lateinit var customerRepository: CustomerRepository

    @InjectMocks
    lateinit var customerService: CustomerService

    @Test
    fun `should create customer`() {
        //given
        val fakeCustomer: Customer = buildCustomer()
        Mockito.`when`(customerRepository.save(Mockito.any())).thenReturn(fakeCustomer)

        //when
        val actual: Customer = customerService.save(fakeCustomer)

        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        Mockito.verify(customerRepository, Mockito.times(1)).save(fakeCustomer)
    }

    @Test
    fun `should find customer by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        Mockito.`when`(customerRepository.findById(fakeId)).thenReturn(Optional.of(fakeCustomer))

        //when
        val actual: Customer = customerService.findById(fakeId)

        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        Mockito.verify(customerRepository, Mockito.times(1)).findById(fakeId)
    }

    @Test
    fun `should not find customer by invalid id and throw BusinessException`() {
        //given
        val fakeId: Long = Random().nextLong()
        Mockito.`when`(customerRepository.findById(fakeId)).thenReturn(Optional.empty())

        //when / then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { customerService.findById(fakeId) }
            .withMessage("Id $fakeId not found")
        Mockito.verify(customerRepository, Mockito.times(1)).findById(fakeId)
    }

    @Test
    fun `should delete customer by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        Mockito.`when`(customerRepository.findById(fakeId)).thenReturn(Optional.of(fakeCustomer))
        Mockito.doNothing().`when`(customerRepository).delete(fakeCustomer)

        //when
        customerService.delete(fakeId)

        //then
        Mockito.verify(customerRepository, Mockito.times(1)).findById(fakeId)
        Mockito.verify(customerRepository, Mockito.times(1)).delete(fakeCustomer)
    }

    companion object {
        fun buildCustomer(
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
