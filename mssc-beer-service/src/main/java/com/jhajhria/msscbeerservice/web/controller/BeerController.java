package com.jhajhria.msscbeerservice.web.controller;

import com.jhajhria.msscbeerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {


    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
        //todo impl
        return ResponseEntity.ok(BeerDto.builder().id(beerId).beerName("Beer Name").build());
    }

    @PostMapping
    public ResponseEntity<BeerDto> saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
        BeerDto createdBeerDto = BeerDto.builder().id(UUID.randomUUID()).beerName(beerDto.getBeerName()).build();
        //todo impl
        return ResponseEntity
                .created(URI.create("api/v1/beer/"+createdBeerDto.getId().toString()))
                .body(createdBeerDto);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDto> updateBeer(@PathVariable("beerId") UUID beerId,@Valid @RequestBody BeerDto beerDto) {
        //todo impl
        return ResponseEntity.ok(BeerDto.builder().id(beerId).beerName("Beer Updated").build());
    }

    @DeleteMapping("/{beerId}")

    public ResponseEntity<Void> deleteBeer(@PathVariable("beerId") UUID beerId) {
        //todo impl
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}