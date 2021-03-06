package com.jhajhria.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhajhria.msscbeerservice.bootstrap.BeerLoader;
import com.jhajhria.msscbeerservice.services.BeerService;
import com.jhajhria.msscbeerservice.web.model.BeerDto;
import com.jhajhria.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs(uriScheme = "https",uriHost = "kapiljhajhria.dev.spring",uriPort = 80)
@ExtendWith({RestDocumentationExtension.class})
@WebMvcTest(BeerController.class)
@ComponentScan("com.jhajhria.msscbeerservice.web.mappers")
public class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerService beerService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testDeleteBeer() throws Exception {

        mockMvc.perform(delete("/api/v1/beer/{beerId}", UUID.randomUUID())
                        .contentType("application/json"))
                .andExpect(status().isNoContent())
                .andDo(document("v1/beer-delete", pathParameters(parameterWithName("beerId").description("UUID of desired beer to delete"))));
        //check if data is deleted and response data as well if there is any data.

    }

    @Test
    void testGetBeerById() throws Exception {
        given(beerService.getBeerById(any())).willReturn(getValidBeerDto());

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/beer/{beerId}", UUID.randomUUID())
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andDo(document("v1/beer-get",
                        pathParameters(
                                parameterWithName("beerId").description("UUID of desired beer to get")
                        ),
                        responseFields(
                                fieldWithPath("id").description("UUID of the beer"),
                                fieldWithPath("beerName").description("Name of the beer"),
                                fieldWithPath("beerStyle").description("Style of the beer"),
                                fieldWithPath("upc").description("UPC of the beer"),
                                fieldWithPath("price").description("Price of the beer"),
                                fieldWithPath("quantityOnHand").description("Quantity of the beer"),
                                fieldWithPath("createdDate").description("Date of creation of the beer"),
                                fieldWithPath("lastModifiedDate").description("Date of last modification of the beer"),
                                fieldWithPath("version").description("Version Number")

                        )));
        //check data returned as well

    }

    @Test
    void testSaveNewBeer() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        BeerDto savedBeerDto = getValidBeerDto();
        savedBeerDto.setId(UUID.randomUUID());
        given(beerService.saveNewBeer(any())).willReturn(savedBeerDto);

        ConstrainedFields fields = new ConstrainedFields(BeerDto.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beer")
                        .contentType("application/json")
                        .content(beerDtoJson))
                .andExpect(status().isCreated())
                .andDo(
                        document("v1/beer-new",
                                requestFields(
                                        fields.withPath("id").ignored(),
                                        fields.withPath("version").ignored(),
                                        fields.withPath("createdDate").ignored(),
                                        fields.withPath("lastModifiedDate").ignored(),
                                        fields.withPath("beerName").description("Name of the beer"),
                                        fields.withPath("beerStyle").description("Style of the beer"),
                                        fields.withPath("upc").description("UPC of the beer").attributes(),
                                        fields.withPath("price").description("Price of the beer"),
                                        fields.withPath("quantityOnHand").ignored()
                                )
                        )

                );
        //check return content data as well
    }

    @Test
    void testUpdateBeer() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beer/" + UUID.randomUUID())
                        .contentType("application/json")
                        .content(beerDtoJson))
                .andExpect(status().isOk());
        //check return data as well
    }

    BeerDto getValidBeerDto(){
        return BeerDto.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .price(new BigDecimal("2.99"))
                .upc(BeerLoader.BEER_1_UPC)
                .build();
    }


    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }
}