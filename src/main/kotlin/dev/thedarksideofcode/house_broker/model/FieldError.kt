package dev.thedarksideofcode.house_broker.model


data class FieldError(
    var `field`: String? = null,
    var errorCode: String? = null
)
