package me.dio.credit.request.system.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.credit.application.system.entity.Address
import me.dio.credit.request.system.entity.Customer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal


data class CustomerDTO(
    @field:NotEmpty(message = "Invalid input") var fisrtName: String,
    @field:NotEmpty(message = "Invalid input") val lastName: String,
    @field:CPF(message = "This Invalid CPF") val cpf: String,
    @field:NotNull(message = "Invalid input") val income: BigDecimal,
    @field:Email(message = "Invalid email")
    @field:NotEmpty(message = "Invalid input") val email: String,
    @field:NotEmpty(message = "Invalid input") val password: String,
    @field:NotEmpty(message = "Invalid input") val zipCode: String,
    @field:NotEmpty(message = "Invalid input") var street: String,
    val firstName: String

){
    fun toEntity(): Customer = Customer(
            firstName = this.fisrtName,
            lastName = this.lastName,
            cpf = this.cpf,
            income = this.income,
            email = this.email,
            password = this.password,
            address = Address(
                    zipCode = this.zipCode,
                    street = this.street
            )
    )
}
