package jhajhria.com.brewery.web.mappers;

import jhajhria.com.brewery.domain.Beer;
import jhajhria.com.brewery.web.model.BeerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { DateMapper.class })
public interface BeerMapper {

    BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}