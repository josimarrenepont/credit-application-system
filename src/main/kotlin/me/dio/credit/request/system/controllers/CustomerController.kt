package me.dio.credit.request.system.controllers

import me.dio.credit.request.system.dto.CustomerDTO
import me.dio.credit.request.system.dto.CustomerView
import me.dio.credit.request.system.entity.Customer
import me.dio.credit.request.system.services.impl.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController(
        @Autowired
        private val customerService: CustomerService
) {

    @PostMapping
    fun saveCustomer(@RequestBody customerDTO: CustomerDTO): String{
        val savedCustomer = this.customerService.save(customerDTO.toEntity())
        return "Customer ${savedCustomer.email} saved!"
    }
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): CustomerView {
        val customer: Customer = this.customerService.findById(id)
        return CustomerView(customer)
    }
    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long){
        this.customerService.delete(id)
    }
}