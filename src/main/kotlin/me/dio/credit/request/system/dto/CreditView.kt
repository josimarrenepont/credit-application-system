package me.dio.credit.request.system.dto

import me.dio.credit.request.system.entity.Credit
import me.dio.credit.request.system.enummeration.Status
import java.math.BigDecimal
import java.util.*

data class CreditView(
        val creditCode: UUID,
        val creditValue: BigDecimal,
        val numberOfInstallment: Int,
        val status: Status,
        val emailCustomer: String?,
        val incomeCustomer: BigDecimal?
) {
    constructor(credit: Credit): this(
            creditCode = credit.creditCode,
            creditValue = credit.creditValue,
            numberOfInstallment = credit.numberOfInstallments,
            status = credit.status,
            emailCustomer = credit.customer?.email,
            incomeCustomer = credit.customer?.income
    )

}
