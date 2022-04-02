package jhajhria.com.brewery.web.controller;

import jhajhria.com.brewery.services.BeerService;
import jhajhria.com.brewery.web.model.BeerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    //sample url
    //localhost:8080/api/v1/beer/0eee8dc6-7a5c-43eb-a328-7fbb8a1efe8f
    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable(name = "beerId") UUID beerId) {

        return new ResponseEntity<>(
                beerService.getBeerById(beerId)
                , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDto> saveBeer(@RequestBody BeerDto beerDto) {
        BeerDto savedDto = beerService.saveBeer(beerDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());


        return new ResponseEntity<>(savedDto,
                headers
                        , HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDto> updateBeer(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {
        BeerDto updatedDto = beerService.updateBeer(beerId, beerDto);

        return new ResponseEntity<>(updatedDto,
                HttpStatus.OK);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId) {
        beerService.deleteBeer(beerId);
    }
}