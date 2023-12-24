package me.dio.credit.request.system.controllers

import me.dio.credit.request.system.dto.CreditDTO
import me.dio.credit.request.system.dto.CreditViewList
import me.dio.credit.request.system.entity.Credit
import me.dio.credit.request.system.services.impl.CreditService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors

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
    @GetMapping()
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): List<CreditViewList>{
      return this.creditService.findAllByCustomer(customerId).stream()
              .map { credit: Credit -> CreditViewList(credit) }.collect(Collectors.toList())

    }
}