package com.jhajhria.beerservice.services.brewing;

import com.jhajhria.beerservice.config.JmsConfig;
import com.jhajhria.beerservice.domain.Beer;
import com.jhajhria.common.events.BrewBeerEvent;
import com.jhajhria.common.events.NewInventoryEvent;
import com.jhajhria.beerservice.repositories.BeerRepository;
import com.jhajhria.beerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event) {

        BeerDto beerDto = event.getBeerDto();

        Beer beer = beerRepository.getById(beerDto.getId());

        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        // send event to update inventory after brewing the beer

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        log.info("Brewed beer: " + beer.getBeerName()+" min on hand"+beer.getMinOnHand()+ "qty on hand : "+beerDto.getQuantityOnHand());

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);

    }
}