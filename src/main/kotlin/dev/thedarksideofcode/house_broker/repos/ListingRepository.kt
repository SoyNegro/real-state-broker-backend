package dev.thedarksideofcode.house_broker.repos

import dev.thedarksideofcode.house_broker.domain.Listing
import org.springframework.data.mongodb.repository.MongoRepository


interface ListingRepository : MongoRepository<Listing, Long>
