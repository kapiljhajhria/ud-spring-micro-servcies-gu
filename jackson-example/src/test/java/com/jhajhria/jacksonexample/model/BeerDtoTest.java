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
        String json = "{\"beerName\":\"Wheat beer\",\"beerStyle\":\"Pale ale\",\"upc\":123456789,\"price\":\"12.99\",\"createdDate\":\"2022-04-06T20:11:25+0530\",\"lastUpdatedDate\":1649256085.582788000,\"myLocalDate\":\"20220406\",\"beerId\":\"eebb5d78-cf4d-4c26-aeed-e76d70c2a6a3\"}";

        BeerDto dto = objectMapper.readValue(json, BeerDto.class);

        System.out.println(dto);

    }

}