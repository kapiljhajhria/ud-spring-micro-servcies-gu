package jhajhria.com.brewery.services;

import jhajhria.com.brewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);
}