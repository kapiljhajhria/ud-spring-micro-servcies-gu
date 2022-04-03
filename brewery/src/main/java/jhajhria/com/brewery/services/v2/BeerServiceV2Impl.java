package jhajhria.com.brewery.services.v2;

import jhajhria.com.brewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerServiceV2Impl implements BeerServiceV2 {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return null;
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        return null;
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        return null;
    }

    @Override
    public void deleteBeer(UUID beerId) {

    }
}