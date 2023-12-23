package me.dio.credit.request.system.services

import me.dio.credit.request.system.entity.Customer

interface ICustomerService {
    fun save(customer: Customer): Customer

    fun findById(id: Long): Customer
    fun delete(id: Long)
}