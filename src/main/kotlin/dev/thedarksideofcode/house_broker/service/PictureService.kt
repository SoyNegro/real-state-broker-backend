package dev.thedarksideofcode.house_broker.service

import dev.thedarksideofcode.house_broker.domain.Listing
import dev.thedarksideofcode.house_broker.domain.Picture
import dev.thedarksideofcode.house_broker.model.PictureDTO
import dev.thedarksideofcode.house_broker.repos.ListingRepository
import dev.thedarksideofcode.house_broker.repos.PictureRepository
import kotlin.streams.toList
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class PictureService(
    private val pictureRepository: PictureRepository,
    private val listingRepository: ListingRepository
) {

    fun findAll(): List<PictureDTO> {
        return pictureRepository.findAll()
                .stream()
                .map { picture -> mapToDTO(picture, PictureDTO()) }
                .toList()
    }

    fun `get`(id: Long): PictureDTO {
        return pictureRepository.findById(id)
                .map { picture -> mapToDTO(picture, PictureDTO()) }
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun create(pictureDTO: PictureDTO): Long {
        val picture = Picture()
        mapToEntity(pictureDTO, picture)
        return pictureRepository.save(picture).id!!
    }

    fun update(id: Long, pictureDTO: PictureDTO) {
        val picture: Picture = pictureRepository.findById(id)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        mapToEntity(pictureDTO, picture)
        pictureRepository.save(picture)
    }

    fun delete(id: Long) {
        pictureRepository.deleteById(id)
    }

    fun mapToDTO(picture: Picture, pictureDTO: PictureDTO): PictureDTO {
        pictureDTO.id = picture.id
        pictureDTO.name = picture.name
        pictureDTO.image = picture.image
        pictureDTO.gallery = picture.gallery?.id
        return pictureDTO
    }

    fun mapToEntity(pictureDTO: PictureDTO, picture: Picture): Picture {
        picture.name = pictureDTO.name
        picture.image = pictureDTO.image
        if (pictureDTO.gallery != null && (picture.gallery == null || picture.gallery?.id !=
                pictureDTO.gallery)) {
            val gallery: Listing = listingRepository.findById(pictureDTO.gallery!!)
                    .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND,
                            "gallery not found") }
            picture.gallery = gallery
        }
        return picture
    }

}
