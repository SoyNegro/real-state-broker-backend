package dev.thedarksideofcode.house_broker.util

import java.time.OffsetDateTime
import java.util.Date
import org.bson.Document
import org.springframework.core.convert.converter.Converter


class MongoOffsetDateTimeWriter : Converter<OffsetDateTime, Document> {

    override fun convert(offsetDateTime: OffsetDateTime): Document? {
        val document = Document()
        document.put(DATE_FIELD, Date.from(offsetDateTime.toInstant()))
        document.put(OFFSET_FIELD, offsetDateTime.getOffset().toString())
        return document
    }


    companion object {

        const val DATE_FIELD: String = "dateTime"

        const val OFFSET_FIELD: String = "offset"

    }

}
