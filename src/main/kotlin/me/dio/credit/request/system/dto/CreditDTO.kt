package me.dio.credit.request.system.dto

import jakarta.validation.constraints.*
import me.dio.credit.request.system.entity.Credit
import me.dio.credit.request.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDTO(
    @field:NotNull(message = "Invalid input") val creditValue: BigDecimal,
    @field:Future(message = "Must be a future date") val dayFirstOfInstallment: LocalDate,
    @field:Min(value = 1) @field:Max(value = 60) val numberOfInstallments: Int,
    @field:NotNull(message = "Invalid input") val customerId: Long
) {
    fun toEntity(): Credit = Credit(
            creditValue = this.creditValue,
            dayFirstInstallment = this.dayFirstOfInstallment,
            numberOfInstallments = this.numberOfInstallments,
            customer = Customer(id = this.customerId)
    )
}
