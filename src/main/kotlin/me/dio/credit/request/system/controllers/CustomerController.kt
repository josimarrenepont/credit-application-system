package me.dio.credit.request.system.controllers

import jakarta.validation.Valid
import me.dio.credit.request.system.dto.CustomerDTO
import me.dio.credit.request.system.dto.CustomerUpdateDTO
import me.dio.credit.request.system.dto.CustomerView
import me.dio.credit.request.system.entity.Customer
import me.dio.credit.request.system.services.impl.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController(
        @Autowired
        private val customerService: CustomerService
) {

    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDTO: CustomerDTO): ResponseEntity<CustomerView>{
        val savedCustomer: Customer = this.customerService.save(customerDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerView(savedCustomer))
    }
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer))
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Long){
        this.customerService.delete(id)
    }
    @PatchMapping
    fun updateCustomer(@RequestParam(value = "customerId") id: Long,
                       @RequestBody @Valid customerUpdateDTO: CustomerUpdateDTO): ResponseEntity<CustomerView>{
        val customer: Customer = this.customerService.findById(id)
        val customerToUpdate = customerUpdateDTO.toEntity(customer)
        val customerUpdated: Customer = this.customerService.save(customerToUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customerUpdated))
    }
}