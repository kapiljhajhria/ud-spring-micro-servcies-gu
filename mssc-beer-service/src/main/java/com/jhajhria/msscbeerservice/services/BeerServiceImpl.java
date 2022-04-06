package com.jhajhria.msscbeerservice.services;

import com.jhajhria.msscbeerservice.domain.Beer;
import com.jhajhria.msscbeerservice.repositories.BeerRepository;
import com.jhajhria.msscbeerservice.web.exception.NotFoundException;
import com.jhajhria.msscbeerservice.web.mappers.BeerMapper;
import com.jhajhria.msscbeerservice.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BeerServiceImpl implements BeerService {

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerMapper beerMapper;

    @Override
    public BeerDto getBeerById(UUID beerId) {

        Optional<Beer> foundBeer = beerRepository.findById(beerId);

        if(foundBeer.isPresent()) {
            return beerMapper.beerToBeerDto(foundBeer.get());
        }else{
            throw new NotFoundException();
        }
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        // can also check if the beer already exists or not
        Beer savedBeer = beerRepository.save(beerMapper.beerDtoToBeer(beerDto));

        return beerMapper.beerToBeerDto(savedBeer);
    }

    @Override
    public void deleteBeer(UUID beerId) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);//() -> new NotFoundException()

        beerRepository.delete(beer);
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beerToUpdate = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beerToUpdate.setBeerName(beerDto.getBeerName());
        beerToUpdate.setBeerStyle(beerDto.getBeerStyle().name());
        beerToUpdate.setPrice(beerDto.getPrice());
        beerToUpdate.setUpc(beerDto.getUpc());

        Beer updatedBeer = beerRepository.save(beerToUpdate);

        return beerMapper.beerToBeerDto(updatedBeer);
    }
}