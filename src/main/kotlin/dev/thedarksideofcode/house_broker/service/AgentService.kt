package dev.thedarksideofcode.house_broker.service

import dev.thedarksideofcode.house_broker.domain.Agent
import dev.thedarksideofcode.house_broker.domain.Picture
import dev.thedarksideofcode.house_broker.model.AgentDTO
import dev.thedarksideofcode.house_broker.repos.AgentRepository
import dev.thedarksideofcode.house_broker.repos.PictureRepository
import kotlin.streams.toList
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class AgentService(
    private val agentRepository: AgentRepository,
    private val pictureRepository: PictureRepository
) {

    fun findAll(): List<AgentDTO> {
        return agentRepository.findAll()
                .stream()
                .map { agent -> mapToDTO(agent, AgentDTO()) }
                .toList()
    }

    fun `get`(id: Long): AgentDTO {
        return agentRepository.findById(id)
                .map { agent -> mapToDTO(agent, AgentDTO()) }
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun create(agentDTO: AgentDTO): Long {
        val agent = Agent()
        mapToEntity(agentDTO, agent)
        return agentRepository.save(agent).id!!
    }

    fun update(id: Long, agentDTO: AgentDTO) {
        val agent: Agent = agentRepository.findById(id)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        mapToEntity(agentDTO, agent)
        agentRepository.save(agent)
    }

    fun delete(id: Long) {
        agentRepository.deleteById(id)
    }

    fun mapToDTO(agent: Agent, agentDTO: AgentDTO): AgentDTO {
        agentDTO.id = agent.id
        agentDTO.name = agent.name
        agentDTO.address = agent.address
        agentDTO.license = agent.license
        agentDTO.summary = agent.summary
        agentDTO.phoneNumber = agent.phoneNumber
        agentDTO.website = agent.website
        agentDTO.logo = agent.logo?.id
        return agentDTO
    }

    fun mapToEntity(agentDTO: AgentDTO, agent: Agent): Agent {
        agent.name = agentDTO.name
        agent.address = agentDTO.address
        agent.license = agentDTO.license
        agent.summary = agentDTO.summary
        agent.phoneNumber = agentDTO.phoneNumber
        agent.website = agentDTO.website
        if (agentDTO.logo != null && (agent.logo == null || agent.logo?.id != agentDTO.logo)) {
            val logo: Picture = pictureRepository.findById(agentDTO.logo!!)
                    .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "logo not found") }
            agent.logo = logo
        }
        return agent
    }

}
