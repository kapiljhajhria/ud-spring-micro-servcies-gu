package jhajhria.com.brewery.services;

import jhajhria.com.brewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Wheat Beer")
                .beerStyle("brewed").build();
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        return BeerDto
                .builder()
                .id(UUID.randomUUID())
                .beerName(beerDto.getBeerName())
                .beerStyle(beerDto.getBeerStyle())
                .build();

    }
}