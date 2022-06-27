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
class Picture {

    @Id
    var id: Long? = null

    @Size(max = 255)
    var name: String? = null

    @Size(max = 255)
    var image: String? = null

    @DocumentReference(lazy = true)
    var gallery: Listing? = null

    @DocumentReference(
        lazy = true,
        lookup = "{ 'logo' : ?#{#self._id} }"
    )
    @ReadOnlyProperty
    var logo: Agent? = null

    @CreatedDate
    var dateCreated: OffsetDateTime? = null

    @LastModifiedDate
    var lastUpdated: OffsetDateTime? = null

    @Version
    var version: Int? = null

}
