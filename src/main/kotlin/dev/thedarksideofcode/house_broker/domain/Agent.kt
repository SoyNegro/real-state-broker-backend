package dev.thedarksideofcode.house_broker.domain

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
class Agent {

    @Id
    var id: Long? = null

    @Size(max = 255)
    var name: String? = null

    @Size(max = 255)
    var address: String? = null

    @Size(max = 255)
    var license: String? = null

    @Size(max = 255)
    var summary: String? = null

    @Size(max = 255)
    var phoneNumber: String? = null

    @Size(max = 255)
    var website: String? = null

    @DocumentReference(
        lazy = true,
        lookup = "{ 'listings' : ?#{#self._id} }"
    )
    @ReadOnlyProperty
    var listingsListings: MutableSet<Listing>? = null

    @DocumentReference(lazy = true)
    var logo: Picture? = null

    @CreatedDate
    var dateCreated: OffsetDateTime? = null

    @LastModifiedDate
    var lastUpdated: OffsetDateTime? = null

    @Version
    var version: Int? = null

}
