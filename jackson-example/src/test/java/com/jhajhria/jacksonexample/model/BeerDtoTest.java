package com.jhajhria.jacksonexample.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.io.IOException;

@JsonTest
class BeerDtoTest extends BaseTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerializeBeerDto() throws JsonProcessingException {

        BeerDto beerDto = getBeerDto();

        String jsonString = objectMapper.writeValueAsString(beerDto);

        System.out.println(jsonString);

    }

    @Test
    void testDeserialize() throws IOException {
        String json = "{\"beerId\":\"639c00ff-9993-4ec1-af12-8277c85f93bb\",\"beerName\":\"BeerName\",\"beerStyle\":\"Ale\",\"upc\":123123123123,\"price\":12.99,\"createdDate\":\"2019-06-02T16:35:58.321001-04:00\",\"lastUpdatedDate\":\"2019-06-02T16:35:58.321872-04:00\"}";

        BeerDto dto = objectMapper.readValue(json, BeerDto.class);

        System.out.println(dto);

    }

}