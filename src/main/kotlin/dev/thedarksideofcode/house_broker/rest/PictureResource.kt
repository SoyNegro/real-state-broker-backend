package dev.thedarksideofcode.house_broker.rest

import dev.thedarksideofcode.house_broker.model.PictureDTO
import dev.thedarksideofcode.house_broker.service.PictureService
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
    value = ["/api/pictures"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class PictureResource(
    private val pictureService: PictureService
) {

    @GetMapping
    fun getAllPictures(): ResponseEntity<List<PictureDTO>> =
            ResponseEntity.ok(pictureService.findAll())

    @GetMapping("/{id}")
    fun getPicture(@PathVariable id: Long): ResponseEntity<PictureDTO> =
            ResponseEntity.ok(pictureService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createPicture(@RequestBody @Valid pictureDTO: PictureDTO): ResponseEntity<Long> =
            ResponseEntity(pictureService.create(pictureDTO), HttpStatus.CREATED)

    @PutMapping("/{id}")
    fun updatePicture(@PathVariable id: Long, @RequestBody @Valid pictureDTO: PictureDTO):
            ResponseEntity<Void> {
        pictureService.update(id, pictureDTO)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deletePicture(@PathVariable id: Long): ResponseEntity<Void> {
        pictureService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
