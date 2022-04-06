package com.jhajhria.msscbeerservice.web.controller;

import com.jhajhria.msscbeerservice.services.BeerService;
import com.jhajhria.msscbeerservice.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @Autowired
    BeerService beerService;


    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {


        return ResponseEntity.ok(beerService.getBeerById(beerId));
    }

    @PostMapping
    public ResponseEntity<BeerDto> saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
        BeerDto createdBeerDto = beerService.saveNewBeer(beerDto);

        return ResponseEntity
                .created(URI.create("api/v1/beer/"+createdBeerDto.getId().toString()))
                .body(createdBeerDto);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDto> updateBeer(@PathVariable("beerId") UUID beerId,@Valid @RequestBody BeerDto beerDto) {

        return ResponseEntity.ok(beerService.updateBeer(beerId,beerDto));
    }

    @DeleteMapping("/{beerId}")

    public ResponseEntity<Void> deleteBeer(@PathVariable("beerId") UUID beerId) {
        beerService.deleteBeer(beerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}