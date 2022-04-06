package com.jhajhria.jacksonexample.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public class BaseTest {

    BeerDto getBeerDto() {
        return BeerDto.builder()
                .beerName("Wheat beer")
                .beerStyle("Pale ale")
                .id(UUID.randomUUID())
                .createdDate(OffsetDateTime.now())
                .lastUpdatedDate(OffsetDateTime.now())
                .upc(123456789L)
                .build();

    }
}