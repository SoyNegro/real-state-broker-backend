package dev.thedarksideofcode.house_broker.model

import javax.validation.constraints.Size


data class AgentDTO(
    var id: Long? = null,
    @get:Size(max = 255)
    var name: String? = null,
    @get:Size(max = 255)
    var address: String? = null,
    @get:Size(max = 255)
    var license: String? = null,
    @get:Size(max = 255)
    var summary: String? = null,
    @get:Size(max = 255)
    var phoneNumber: String? = null,
    @get:Size(max = 255)
    var website: String? = null,
    var logo: Long? = null
)
