package com.jhajhria.beerservice.events;

import com.jhajhria.beerservice.web.model.BeerDto;


public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}