package org.denis.coinkeeper.api.controllers;

import org.denis.coinkeeper.api.Services.ProfitService;
import org.denis.coinkeeper.api.dto.ProfitDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class ProfitControllerTest {

    @Mock
    ProfitService profitService;

    @InjectMocks
    ProfitController profitController;

    @Test
    @DisplayName("createProfit, return response code CREATED")
    void createProfit_returnStatusCodeCreated() {
        //given
        Authentication authentication = mock(Authentication.class);

        String email = "den4k3@mail.ru";

        ProfitDto profitDto = ProfitDto.builder()
                .profitId(1L)
                .name("Кешбек сбербанк")
                .userId(1L)
                .price(100L)
                .category("Кешбек").build();
        //when
        Mockito.when(authentication.getName()).thenReturn(email);

        var ResponseEntity = this.profitController.createProfit(profitDto, authentication);
        //then
        assertNotNull(ResponseEntity);
        assertEquals(HttpStatus.CREATED, ResponseEntity.getStatusCode());
    }

    @Test
    @DisplayName("getProfits, return all profits")
    void getProfits_returnListProfitDto() {
        //given
        Authentication authentication = mock(Authentication.class);

        String email = "den4k3@mail.ru";
        List<ProfitDto> profitDtoList = Arrays.asList(
                ProfitDto.builder()
                        .profitId(1L)
                        .name("Кешбек сбербанк")
                        .userId(1L)
                        .price(100L)
                        .category("Кешбек")
                        .build(),

                ProfitDto.builder()
                        .profitId(1L)
                        .name("Долг друга")
                        .userId(1L)
                        .price(1000L)
                        .category("Переводы")
                        .build()
        );


        //when
        Mockito.when(authentication.getName()).thenReturn(email);
        Mockito.when(profitService.getProfits(email)).thenReturn(profitDtoList);

        var ResponseEntity = this.profitController.getProfits(authentication);
        //then
        assertNotNull(ResponseEntity);
        assertEquals(HttpStatus.OK, ResponseEntity.getStatusCode());
        assertEquals(profitDtoList, ResponseEntity.getBody());
    }

    @Test
    @DisplayName("putProfit, return modified profit")
    void putProfit_ReturnModifiedProfitDto() {
        //given
        Authentication authentication = mock(Authentication.class);

        String email = "den4k3@mail.ru";
        Long profitId = 1L;

        ProfitDto profitDto = ProfitDto.builder()
                .profitId(profitId)
                .name("Долг друга")
                .userId(1L)
                .price(1000L)
                .category("Переводы")
                .build();


        //when
        Mockito.when(authentication.getName()).thenReturn(email);
        Mockito.when(profitService.putProfit(profitId,profitDto,email)).thenReturn(profitDto);

        var ResponseEntity = this.profitController.putProfit(profitDto,profitId,authentication);
        //then
        assertNotNull(ResponseEntity);
        assertEquals(HttpStatus.OK, ResponseEntity.getStatusCode());
        assertEquals(profitDto, ResponseEntity.getBody());
    }

    @Test
    @DisplayName("getProfit, return profit by id")
    void getProfit_returnProfitDto() {
        //given
        Authentication authentication = mock(Authentication.class);

        String email = "den4k3@mail.ru";
        Long profitId = 1L;

        ProfitDto profitDto = ProfitDto.builder()
                .profitId(profitId)
                .name("Долг друга")
                .userId(1L)
                .price(1000L)
                .category("Переводы")
                .build();


        //when
        Mockito.when(authentication.getName()).thenReturn(email);
        Mockito.when(profitService.getProfitById(profitId, email)).thenReturn(profitDto);

        var ResponseEntity = this.profitController.getProfit(profitId,authentication);
        //then
        assertNotNull(ResponseEntity);
        assertEquals(HttpStatus.OK, ResponseEntity.getStatusCode());
        assertEquals(profitDto, ResponseEntity.getBody());
    }

    @Test
    @DisplayName("removeProfit, remove profit by id , return status code NoContent")
    void removeExpenses_returnStatusCodeNoContent(){
        //given
        Authentication authentication = mock(Authentication.class);

        String email = "den4k3@mail.ru";
        Long profitId = 1L;

        //when
        Mockito.when(authentication.getName()).thenReturn(email);

        var ResponseEntity = this.profitController.removeProfit(profitId,authentication);
        //then
        assertNotNull(ResponseEntity);
        assertEquals(HttpStatus.NO_CONTENT, ResponseEntity.getStatusCode());
    }
}