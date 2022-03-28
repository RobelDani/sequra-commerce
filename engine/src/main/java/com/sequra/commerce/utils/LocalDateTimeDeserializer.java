package com.sequra.commerce.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(final JsonParser jsonParser,
                                     final DeserializationContext deserializationContext) throws IOException {
        return StringUtils.isNotEmpty(jsonParser.getText())
                ? LocalDateTime.parse(
                jsonParser.getText(),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                : null;
    }
}
