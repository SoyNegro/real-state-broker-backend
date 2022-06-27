package dev.thedarksideofcode.house_broker.repos

import dev.thedarksideofcode.house_broker.domain.Picture
import org.springframework.data.mongodb.repository.MongoRepository


interface PictureRepository : MongoRepository<Picture, Long>
