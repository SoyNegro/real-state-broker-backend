package dev.thedarksideofcode.house_broker.model

import javax.validation.constraints.Size


data class ListingDTO(
    var id: Long? = null,
    @get:Size(max = 255)
    var description: String? = null,
    var price: Double? = null,
    @get:Size(max = 255)
    var neighborhood: String? = null,
    var levels: Int? = null,
    @get:Size(max = 255)
    var heating: String? = null,
    @get:Size(max = 255)
    var floor: String? = null,
    var parkingSpot: Boolean? = null,
    var kitchens: Int? = null,
    var livingRooms: Int? = null,
    var bathrooms: Int? = null,
    var wc: Int? = null,
    @get:Size(max = 255)
    var propertyCode: String? = null,
    @get:Size(max = 255)
    var agentCode: String? = null,
    @get:Size(max = 255)
    var additionalInfo: String? = null,
    @get:Size(max = 255)
    var address: String? = null,
    @get:Size(max = 255)
    var builtIn: String? = null,
    var status: PropertyStatus? = null,
    @get:Size(max = 255)
    var zone: String? = null,
    @get:Size(max = 255)
    var renovatedIn: String? = null,
    @get:Size(max = 255)
    var energyClass: String? = null,
    var type: PropertyType? = null,
    @get:Size(max = 255)
    var other: String? = null,
    @get:Size(max = 255)
    var brief: String? = null,
    var beedrooms: Int? = null,
    var area: Long? = null,
    var listings: Long? = null
)
