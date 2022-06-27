package dev.thedarksideofcode.house_broker.rest

import dev.thedarksideofcode.house_broker.model.AgentDTO
import dev.thedarksideofcode.house_broker.service.AgentService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import java.lang.Void
import javax.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(
    value = ["/api/agents"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class AgentResource(
    private val agentService: AgentService
) {

    @GetMapping
    fun getAllAgents(): ResponseEntity<List<AgentDTO>> = ResponseEntity.ok(agentService.findAll())

    @GetMapping("/{id}")
    fun getAgent(@PathVariable id: Long): ResponseEntity<AgentDTO> =
            ResponseEntity.ok(agentService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createAgent(@RequestBody @Valid agentDTO: AgentDTO): ResponseEntity<Long> =
            ResponseEntity(agentService.create(agentDTO), HttpStatus.CREATED)

    @PutMapping("/{id}")
    fun updateAgent(@PathVariable id: Long, @RequestBody @Valid agentDTO: AgentDTO):
            ResponseEntity<Void> {
        agentService.update(id, agentDTO)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteAgent(@PathVariable id: Long): ResponseEntity<Void> {
        agentService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
