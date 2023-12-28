package me.dio.credit.request.system.exceptions

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerValidException(ex: MethodArgumentNotValidException): ResponseEntity<ExceptionDetails>{
        val errors: MutableMap<String,String?> = HashMap()
        ex.bindingResult.allErrors.stream().forEach{
            error: ObjectError ->
            val fildName: String = (error as FieldError).field
            val messageError: String? = error.defaultMessage
            errors[fildName] = messageError
        }
        return ResponseEntity(
                ExceptionDetails(
                        title = "Bad Request! Consult the documentation",
                        timestamp = LocalDateTime.now(),
                        status = HttpStatus.BAD_REQUEST.value(),
                        exception = ex.javaClass.toString(),
                        details = errors
                ), HttpStatus.BAD_REQUEST
        )
    }
    @ExceptionHandler(DataAccessException::class)
    fun handlerValidException(ex: DataAccessException): ResponseEntity<ExceptionDetails>{
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ExceptionDetails(
                        title = "Conflict! Consult the documentation",
                        timestamp = LocalDateTime.now(),
                        status = HttpStatus.CONFLICT.value(),
                        exception = ex.javaClass.toString(),
                        details = mutableMapOf(ex.cause.toString() to ex.message)
                        ))
        }
    @ExceptionHandler(BusinessException::class)
    fun handlerValidException(ex: BusinessException): ResponseEntity<ExceptionDetails>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ExceptionDetails(
                        title = "Bad Request! Consult the documentation",
                        timestamp = LocalDateTime.now(),
                        status = HttpStatus.BAD_REQUEST.value(),
                        exception = ex.javaClass.toString(),
                        details = mutableMapOf(ex.cause.toString() to ex.message)
                ))
    }
    @ExceptionHandler(IllegalStateException::class)
    fun handlerValidException(ex: IllegalStateException): ResponseEntity<ExceptionDetails>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ExceptionDetails(
                        title = "Bad Request! Consult the documentation",
                        timestamp = LocalDateTime.now(),
                        status = HttpStatus.BAD_REQUEST.value(),
                        exception = ex.javaClass.toString(),
                        details = mutableMapOf(ex.cause.toString() to ex.message)
                ))
    }
    }