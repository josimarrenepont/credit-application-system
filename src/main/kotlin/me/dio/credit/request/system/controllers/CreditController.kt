package me.dio.credit.request.system.controllers

import me.dio.credit.request.system.dto.CreditDTO
import me.dio.credit.request.system.entity.Credit
import me.dio.credit.request.system.services.impl.CreditService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/credits")
class CreditController(
        private val creditService: CreditService
) {
    @PostMapping
    fun saveCredit(@RequestBody creditDTO: CreditDTO): String{
        val credit: Credit =  this.creditService.save(creditDTO.toEntity())
        return "Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved!"
    }
}