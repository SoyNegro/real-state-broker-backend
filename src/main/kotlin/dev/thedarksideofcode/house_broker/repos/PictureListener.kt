package dev.thedarksideofcode.house_broker.repos

import dev.thedarksideofcode.house_broker.domain.Picture
import dev.thedarksideofcode.house_broker.service.PrimarySequenceService
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent
import org.springframework.stereotype.Component


@Component
class PictureListener(
    private val primarySequenceService: PrimarySequenceService
) : AbstractMongoEventListener<Picture>() {

    override fun onBeforeConvert(event: BeforeConvertEvent<Picture>) {
        if (event.source.id == null) {
            event.source.id = primarySequenceService.getNextValue()
        }
    }

}
