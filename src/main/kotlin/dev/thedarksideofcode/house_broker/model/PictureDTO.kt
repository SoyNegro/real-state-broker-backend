package dev.thedarksideofcode.house_broker.model

import javax.validation.constraints.Size


data class PictureDTO(
    var id: Long? = null,
    @get:Size(max = 255)
    var name: String? = null,
    @get:Size(max = 255)
    var image: String? = null,
    var gallery: Long? = null
)
