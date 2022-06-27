package dev.thedarksideofcode.house_broker.util

import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.Date
import org.bson.Document
import org.springframework.core.convert.converter.Converter


class MongoOffsetDateTimeReader : Converter<Document, OffsetDateTime> {

    override fun convert(document: Document): OffsetDateTime? {
        val dateTime: Date = document.getDate(MongoOffsetDateTimeWriter.DATE_FIELD)
        val offset: ZoneOffset =
                ZoneOffset.of(document.getString(MongoOffsetDateTimeWriter.OFFSET_FIELD))
        return OffsetDateTime.ofInstant(dateTime.toInstant(), offset)
    }

}
