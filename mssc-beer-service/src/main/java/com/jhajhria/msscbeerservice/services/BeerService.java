package com.jhajhria.msscbeerservice.services;

import com.jhajhria.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    void deleteBeer(UUID beerId);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);
}