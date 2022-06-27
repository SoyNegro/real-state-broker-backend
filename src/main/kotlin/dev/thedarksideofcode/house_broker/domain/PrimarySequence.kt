package dev.thedarksideofcode.house_broker.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document
class PrimarySequence {

    @Id
    var id: String? = null

    var seq: Long? = null

}
