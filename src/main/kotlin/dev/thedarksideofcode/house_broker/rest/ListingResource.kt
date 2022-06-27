package dev.thedarksideofcode.house_broker.rest

import dev.thedarksideofcode.house_broker.model.ListingDTO
import dev.thedarksideofcode.house_broker.service.ListingService
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
    value = ["/api/listings"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class ListingResource(
    private val listingService: ListingService
) {

    @GetMapping
    fun getAllListings(): ResponseEntity<List<ListingDTO>> =
            ResponseEntity.ok(listingService.findAll())

    @GetMapping("/{id}")
    fun getListing(@PathVariable id: Long): ResponseEntity<ListingDTO> =
            ResponseEntity.ok(listingService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createListing(@RequestBody @Valid listingDTO: ListingDTO): ResponseEntity<Long> =
            ResponseEntity(listingService.create(listingDTO), HttpStatus.CREATED)

    @PutMapping("/{id}")
    fun updateListing(@PathVariable id: Long, @RequestBody @Valid listingDTO: ListingDTO):
            ResponseEntity<Void> {
        listingService.update(id, listingDTO)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteListing(@PathVariable id: Long): ResponseEntity<Void> {
        listingService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
