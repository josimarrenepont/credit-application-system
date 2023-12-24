package me.dio.credit.request.system.dto

import me.dio.credit.request.system.entity.Address
import me.dio.credit.request.system.entity.Customer
import java.math.BigDecimal

data class CustomerDTO(
  val fisrtName: String,
  val lastName: String,
  val cpf: String,
  val income: BigDecimal,
  val email: String,
  val password: String,
  val zipCode: String,
  val street: String

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
