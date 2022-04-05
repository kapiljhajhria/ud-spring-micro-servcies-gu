package jhajhria.com.brewery.web.controller.v2;

import jhajhria.com.brewery.services.v2.BeerServiceV2;
import jhajhria.com.brewery.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/beer")
public class BeerControllerV2 {

    private final BeerServiceV2 beerService;

    //sample url
    //localhost:8080/api/v1/beer/0eee8dc6-7a5c-43eb-a328-7fbb8a1efe8f
    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable(name = "beerId") UUID beerId) {

        return new ResponseEntity<>(
                beerService.getBeerById(beerId)
                , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDto> saveBeer(@RequestBody @Valid BeerDto beerDto) {
        log.debug("POST /api/v2/beer -  Saving beer: " + beerDto);
        val savedDto = beerService.saveBeer(beerDto);

        val headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());


        return new ResponseEntity<>(savedDto,
                headers
                , HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDto> updateBeer(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto) {
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