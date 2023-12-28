package me.dio.credit.request.system.exceptions

data class BusinessException(override val message : String?): RuntimeException(message) {
}