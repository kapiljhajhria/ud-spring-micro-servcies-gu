package com.jhajhria.beerservice.services.brewing;

import com.jhajhria.beerservice.config.JmsConfig;
import com.jhajhria.beerservice.domain.Beer;
import com.jhajhria.beerservice.events.BrewBeerEvent;
import com.jhajhria.beerservice.repositories.BeerRepository;
import com.jhajhria.beerservice.services.inventory.BeerInventoryService;
import com.jhajhria.beerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;

    private final BeerMapper beerMapper;


    @Scheduled(fixedRate = 5000)// every 5 seconds
    public void checkForLowInventory() {
        List<Beer> beers =  beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnhandInventory(beer.getId());// inventory quantity on hand

            log.info("Min Onhand is " + beer.getMinOnHand());
            log.info(("Inventory is " + invQOH));

            if (beer.getMinOnHand() >= invQOH) {
                log.info("Low on inventory for beer " + beer.getBeerName());
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(
                        beerMapper.beerToBeerDto(beer)
                ));


            }

        });

    }
}