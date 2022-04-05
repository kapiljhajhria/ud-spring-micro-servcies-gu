package com.jhajhria.msscbeerservice.web.mappers;

import com.jhajhria.msscbeerservice.domain.Beer;
import com.jhajhria.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}