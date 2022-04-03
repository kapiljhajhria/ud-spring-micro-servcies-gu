package jhajhria.com.brewery.services.v2;

import jhajhria.com.brewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerServiceV2 {
    BeerDto getBeerById(UUID beerId);

    BeerDto saveBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    void deleteBeer(UUID beerId);
}