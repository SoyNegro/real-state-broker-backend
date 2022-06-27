package dev.thedarksideofcode.house_broker.repos

import dev.thedarksideofcode.house_broker.domain.Listing
import dev.thedarksideofcode.house_broker.service.PrimarySequenceService
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent
import org.springframework.stereotype.Component


@Component
class ListingListener(
    private val primarySequenceService: PrimarySequenceService
) : AbstractMongoEventListener<Listing>() {

    override fun onBeforeConvert(event: BeforeConvertEvent<Listing>) {
        if (event.source.id == null) {
            event.source.id = primarySequenceService.getNextValue()
        }
    }

}
