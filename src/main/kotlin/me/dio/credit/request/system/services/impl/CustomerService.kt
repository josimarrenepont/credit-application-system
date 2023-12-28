package me.dio.credit.request.system.services.impl

import me.dio.credit.request.system.entity.Customer
import me.dio.credit.request.system.exceptions.BusinessException
import me.dio.credit.request.system.repository.CustomerRepository
import me.dio.credit.request.system.services.ICustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import java.util.Optional

@Service
class CustomerService(

        @Autowired
        private val customerRepository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer =
        this.customerRepository.save(customer)


    override fun findById(id: Long): Customer = this.customerRepository.findById(id).orElseThrow {
            throw BusinessException("Id $id not found")
        }
    override fun delete(id: Long){
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }
}