package jhajhria.com.brewery.web.mappers;

import javax.annotation.processing.Generated;
import jhajhria.com.brewery.domain.Beer;
import jhajhria.com.brewery.domain.Beer.BeerBuilder;
import jhajhria.com.brewery.web.model.BeerDto;
import jhajhria.com.brewery.web.model.BeerDto.BeerDtoBuilder;
import jhajhria.com.brewery.web.model.v2.BeerStyle;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-05T14:30:04+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
public class BeerMapperImpl implements BeerMapper {

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
        if ( beer == null ) {
            return null;
        }

        BeerDtoBuilder beerDto = BeerDto.builder();

        beerDto.id( beer.getId() );
        beerDto.beerName( beer.getBeerName() );
        if ( beer.getBeerStyle() != null ) {
            beerDto.beerStyle( beer.getBeerStyle().name() );
        }
        beerDto.upc( beer.getUpc() );

        return beerDto.build();
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto) {
        if ( beerDto == null ) {
            return null;
        }

        BeerBuilder beer = Beer.builder();

        beer.id( beerDto.getId() );
        beer.beerName( beerDto.getBeerName() );
        if ( beerDto.getBeerStyle() != null ) {
            beer.beerStyle( Enum.valueOf( BeerStyle.class, beerDto.getBeerStyle() ) );
        }
        beer.upc( beerDto.getUpc() );

        return beer.build();
    }
}
