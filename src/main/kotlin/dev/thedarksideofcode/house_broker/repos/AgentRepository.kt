package dev.thedarksideofcode.house_broker.repos

import dev.thedarksideofcode.house_broker.domain.Agent
import org.springframework.data.mongodb.repository.MongoRepository


interface AgentRepository : MongoRepository<Agent, Long>
