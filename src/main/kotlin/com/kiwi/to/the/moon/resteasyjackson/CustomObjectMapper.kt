package com.kiwi.to.the.moon.resteasyjackson

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import io.quarkus.jackson.ObjectMapperCustomizer
import javax.inject.Singleton

@Singleton
class CustomObjectMapper : ObjectMapperCustomizer {
    override fun customize(objectMapper: ObjectMapper) {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL) // Suppress serializing properties with null values
    }
}
