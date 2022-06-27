package dev.thedarksideofcode.house_broker.repos

import dev.thedarksideofcode.house_broker.domain.Agent
import dev.thedarksideofcode.house_broker.service.PrimarySequenceService
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent
import org.springframework.stereotype.Component


@Component
class AgentListener(
    private val primarySequenceService: PrimarySequenceService
) : AbstractMongoEventListener<Agent>() {

    override fun onBeforeConvert(event: BeforeConvertEvent<Agent>) {
        if (event.source.id == null) {
            event.source.id = primarySequenceService.getNextValue()
        }
    }

}
