package dev.thedarksideofcode.house_broker.domain

import dev.thedarksideofcode.house_broker.model.PropertyStatus
import dev.thedarksideofcode.house_broker.model.PropertyType
import java.time.OffsetDateTime
import javax.validation.constraints.Size
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.ReadOnlyProperty
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference


@Document
class Listing {

    @Id
    var id: Long? = null

    @Size(max = 255)
    var description: String? = null

    var price: Double? = null

    @Size(max = 255)
    var neighborhood: String? = null

    var levels: Int? = null

    @Size(max = 255)
    var heating: String? = null

    @Size(max = 255)
    var floor: String? = null

    var parkingSpot: Boolean? = null

    var kitchens: Int? = null

    var livingRooms: Int? = null

    var bathrooms: Int? = null

    var wc: Int? = null

    @Size(max = 255)
    var propertyCode: String? = null

    @Size(max = 255)
    var agentCode: String? = null

    @Size(max = 255)
    var additionalInfo: String? = null

    @Size(max = 255)
    var address: String? = null

    @Size(max = 255)
    var builtIn: String? = null

    var status: PropertyStatus? = null

    @Size(max = 255)
    var zone: String? = null

    @Size(max = 255)
    var renovatedIn: String? = null

    @Size(max = 255)
    var energyClass: String? = null

    var type: PropertyType? = null

    @Size(max = 255)
    var other: String? = null

    @Size(max = 255)
    var brief: String? = null

    var beedrooms: Int? = null

    var area: Long? = null

    @DocumentReference(lazy = true)
    var listings: Agent? = null

    @DocumentReference(
        lazy = true,
        lookup = "{ 'gallery' : ?#{#self._id} }"
    )
    @ReadOnlyProperty
    var galleryPictures: MutableSet<Picture>? = null

    @CreatedDate
    var dateCreated: OffsetDateTime? = null

    @LastModifiedDate
    var lastUpdated: OffsetDateTime? = null

    @Version
    var version: Int? = null

}
