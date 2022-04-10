package com.jhajhria.beerorderservice.services.beer;

import com.jhajhria.beerorderservice.web.model.BeerDto;

import java.util.UUID;
import java.util.Optional;
public interface BeerService {

    Optional<BeerDto> getBeerById(UUID uuid);

    Optional<BeerDto> getBeerByUpc(String upc);
}