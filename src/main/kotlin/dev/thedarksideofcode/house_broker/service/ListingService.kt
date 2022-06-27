package dev.thedarksideofcode.house_broker.service

import dev.thedarksideofcode.house_broker.domain.Agent
import dev.thedarksideofcode.house_broker.domain.Listing
import dev.thedarksideofcode.house_broker.model.ListingDTO
import dev.thedarksideofcode.house_broker.repos.AgentRepository
import dev.thedarksideofcode.house_broker.repos.ListingRepository
import kotlin.streams.toList
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class ListingService(
    private val listingRepository: ListingRepository,
    private val agentRepository: AgentRepository
) {

    fun findAll(): List<ListingDTO> {
        return listingRepository.findAll()
                .stream()
                .map { listing -> mapToDTO(listing, ListingDTO()) }
                .toList()
    }

    fun `get`(id: Long): ListingDTO {
        return listingRepository.findById(id)
                .map { listing -> mapToDTO(listing, ListingDTO()) }
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun create(listingDTO: ListingDTO): Long {
        val listing = Listing()
        mapToEntity(listingDTO, listing)
        return listingRepository.save(listing).id!!
    }

    fun update(id: Long, listingDTO: ListingDTO) {
        val listing: Listing = listingRepository.findById(id)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        mapToEntity(listingDTO, listing)
        listingRepository.save(listing)
    }

    fun delete(id: Long) {
        listingRepository.deleteById(id)
    }

    fun mapToDTO(listing: Listing, listingDTO: ListingDTO): ListingDTO {
        listingDTO.id = listing.id
        listingDTO.description = listing.description
        listingDTO.price = listing.price
        listingDTO.neighborhood = listing.neighborhood
        listingDTO.levels = listing.levels
        listingDTO.heating = listing.heating
        listingDTO.floor = listing.floor
        listingDTO.parkingSpot = listing.parkingSpot
        listingDTO.kitchens = listing.kitchens
        listingDTO.livingRooms = listing.livingRooms
        listingDTO.bathrooms = listing.bathrooms
        listingDTO.wc = listing.wc
        listingDTO.propertyCode = listing.propertyCode
        listingDTO.agentCode = listing.agentCode
        listingDTO.additionalInfo = listing.additionalInfo
        listingDTO.address = listing.address
        listingDTO.builtIn = listing.builtIn
        listingDTO.status = listing.status
        listingDTO.zone = listing.zone
        listingDTO.renovatedIn = listing.renovatedIn
        listingDTO.energyClass = listing.energyClass
        listingDTO.type = listing.type
        listingDTO.other = listing.other
        listingDTO.brief = listing.brief
        listingDTO.beedrooms = listing.beedrooms
        listingDTO.area = listing.area
        listingDTO.listings = listing.listings?.id
        return listingDTO
    }

    fun mapToEntity(listingDTO: ListingDTO, listing: Listing): Listing {
        listing.description = listingDTO.description
        listing.price = listingDTO.price
        listing.neighborhood = listingDTO.neighborhood
        listing.levels = listingDTO.levels
        listing.heating = listingDTO.heating
        listing.floor = listingDTO.floor
        listing.parkingSpot = listingDTO.parkingSpot
        listing.kitchens = listingDTO.kitchens
        listing.livingRooms = listingDTO.livingRooms
        listing.bathrooms = listingDTO.bathrooms
        listing.wc = listingDTO.wc
        listing.propertyCode = listingDTO.propertyCode
        listing.agentCode = listingDTO.agentCode
        listing.additionalInfo = listingDTO.additionalInfo
        listing.address = listingDTO.address
        listing.builtIn = listingDTO.builtIn
        listing.status = listingDTO.status
        listing.zone = listingDTO.zone
        listing.renovatedIn = listingDTO.renovatedIn
        listing.energyClass = listingDTO.energyClass
        listing.type = listingDTO.type
        listing.other = listingDTO.other
        listing.brief = listingDTO.brief
        listing.beedrooms = listingDTO.beedrooms
        listing.area = listingDTO.area
        if (listingDTO.listings != null && (listing.listings == null || listing.listings?.id !=
                listingDTO.listings)) {
            val listings: Agent = agentRepository.findById(listingDTO.listings!!)
                    .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND,
                            "listings not found") }
            listing.listings = listings
        }
        return listing
    }

}
