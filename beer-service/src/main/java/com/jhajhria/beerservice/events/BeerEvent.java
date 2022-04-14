package com.jhajhria.beerservice.events;

import com.jhajhria.beerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = 8405549087927393804L;

    private final BeerDto beerDto;
}