package com.jhajhria.beerinventoryservice.web.mappers;


import com.jhajhria.beerinventoryservice.domain.BeerInventory;
import com.jhajhria.beerinventoryservice.web.model.BeerInventoryDto;
import org.mapstruct.Mapper;

/**
 * Created by jt on 2019-05-31.
 */
@Mapper(uses = {DateMapper.class},componentModel = "spring")
public interface BeerInventoryMapper {

    BeerInventory beerInventoryDtoToBeerInventory(BeerInventoryDto beerInventoryDTO);

    BeerInventoryDto beerInventoryToBeerInventoryDto(BeerInventory beerInventory);
}