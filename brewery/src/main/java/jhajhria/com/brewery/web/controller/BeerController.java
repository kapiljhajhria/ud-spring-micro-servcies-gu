package jhajhria.com.brewery.web.controller;

import jhajhria.com.brewery.services.BeerService;
import jhajhria.com.brewery.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}