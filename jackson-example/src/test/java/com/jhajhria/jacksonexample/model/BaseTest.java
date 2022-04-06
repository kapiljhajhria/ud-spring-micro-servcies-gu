package com.jhajhria.jacksonexample.model;

import java.math.BigDecimal;
import java.time.LocalDate;
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
                .price(new BigDecimal("12.99"))
                .myLocalDate(LocalDate.now())
                .build();

    }
}