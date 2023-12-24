package me.dio.credit.request.system.dto

import me.dio.credit.request.system.entity.Credit
import me.dio.credit.request.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDTO(
     val creditValue: BigDecimal,
     val dayFirstOfInstallment: LocalDate,
     val numberOfInstallments: Int,
     val customerId: Long
) {
    fun toEntity(): Credit = Credit(
            creditValue = this.creditValue,
            dayFirstInstallment = this.dayFirstOfInstallment,
            numberOfInstallments = this.numberOfInstallments,
            customer = Customer(id = this.customerId)
    )
}
