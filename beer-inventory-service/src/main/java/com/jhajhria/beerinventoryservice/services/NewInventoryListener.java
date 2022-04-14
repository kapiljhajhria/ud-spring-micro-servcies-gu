package com.jhajhria.beerinventoryservice.services;

import com.jhajhria.beerinventoryservice.config.JmsConfig;
import com.jhajhria.beerinventoryservice.domain.BeerInventory;
import com.jhajhria.common.events.NewInventoryEvent;
import com.jhajhria.beerinventoryservice.repositories.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service // can also use component annotation
@Slf4j
@RequiredArgsConstructor
public class NewInventoryListener {

    private final BeerInventoryRepository beerInventoryRepository;

    @JmsListener(destination = JmsConfig.NEW_INVENTORY_REQUEST_QUEUE)
    public void listen(NewInventoryEvent event) {
        log.info("received new inventory event: " + event.toString());


        beerInventoryRepository.save(BeerInventory
                .builder()
                .beerId(event.getBeerDto().getId())
                .upc(event.getBeerDto().getUpc())
                .quantityOnHand(event.getBeerDto().getQuantityOnHand())
                .build());
    }
}