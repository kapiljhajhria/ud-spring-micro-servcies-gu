package com.jhajhria.common.events;

import com.jhajhria.beerservice.web.model.BeerDto;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = 8405549087927393804L;

    private BeerDto beerDto;

}