package com.jhajhria.beerorderservice.web.mappers;


import com.jhajhria.beerorderservice.domain.BeerOrderLine;
import com.jhajhria.beerorderservice.web.model.BeerOrderLineDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerOrderLineMapper {
    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);

    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);
}