package com.jhajhria.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhajhria.msscbeerservice.web.model.BeerDto;
import com.jhajhria.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testDeleteBeer() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/beer/"+ UUID.randomUUID())
                .contentType("application/json"))
                .andExpect(status().isNoContent());
        //check if data is deleted and response data as well if there is any data.

    }

    @Test
    void testGetBeerById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/"+ UUID.randomUUID())
                .accept("application/json"))
                .andExpect(status().isOk());
        //check data returned as well

    }

    @Test
    void testSaveNewBeer() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beer/")
                .contentType("application/json")
                .content(beerDtoJson))
                .andExpect(status().isCreated());
        //check return content data as well
    }

    @Test
    void testUpdateBeer() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beer/"+ UUID.randomUUID())
                .contentType("application/json")
                .content(beerDtoJson))
                .andExpect(status().isOk());
        //check return data as well
    }

    BeerDto getValidBeerDto(){
        return BeerDto.builder()
                .beerName("My Wheat Beer")
                .beerStyle(BeerStyleEnum.WHEAT)
                .upc(123456789L)
                .price(new BigDecimal("2.99"))
                .build();
    }
}