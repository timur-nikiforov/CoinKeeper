package org.denis.coinkeeper.api.controllers;

import org.denis.coinkeeper.api.Services.ExpensesService;
import org.denis.coinkeeper.api.dto.CurrencyDto;
import org.denis.coinkeeper.api.dto.ExpensesDto;
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
class ExpensesControllerTest {
    @Mock
    ExpensesService expensesService;

    @InjectMocks
    ExpensesController expensesController;

    @Test
    @DisplayName("createExpenses, return response code CREATED")
    void createExpenses_returnStatusCodeCreated() {
        //given
        Authentication authentication = mock(Authentication.class);

        String email = "den4k3@mail.ru";

        ExpensesDto expensesDto = ExpensesDto.builder()
                .expensesId(1L)
                .name("Молоко")
                .userId(1L)
                .price(120L)
                .category("Продукты")
                .build();


        //when
        Mockito.when(authentication.getName()).thenReturn(email);
        //Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(userEntity));

        var ResponseEntity = this.expensesController.createExpenses(expensesDto, authentication);
        //then
        assertNotNull(ResponseEntity);
        assertEquals(HttpStatus.CREATED, ResponseEntity.getStatusCode());
    }

    @Test
    @DisplayName("getExpenses, return all expenses")
    void getExpenses_returnListExpensesDto() {
        //given
        Authentication authentication = mock(Authentication.class);

        String email = "den4k3@mail.ru";

        List<ExpensesDto> expensesDtoList = Arrays.asList(
               ExpensesDto.builder()
                        .expensesId(1L)
                        .name("Молоко")
                        .userId(1L)
                        .price(120L)
                        .category("Продукты")
                        .build(),

                ExpensesDto.builder()
                        .expensesId(1L)
                        .name("Яйца")
                        .userId(1L)
                        .price(150L)
                        .category("Продукты")
                        .build()
        );
        //when
        Mockito.when(authentication.getName()).thenReturn(email);
        Mockito.when(expensesService.getAllExpenses(email)).thenReturn(expensesDtoList);

        var ResponseEntity = this.expensesController.getAllExpenses(authentication);
        //then
        assertNotNull(ResponseEntity);
        assertEquals(HttpStatus.OK, ResponseEntity.getStatusCode());
        assertEquals(expensesDtoList, ResponseEntity.getBody());
    }

    @Test
    @DisplayName("putExpenses, return modified expenses")
    void putExpenses_ReturnModifiedExpensesDto() {
        //given
        Authentication authentication = mock(Authentication.class);

        String email = "den4k3@mail.ru";
        Long expensesId = 1L;

        ExpensesDto expensesDto = ExpensesDto.builder()
                .expensesId(1L)
                .name("Молоко")
                .userId(1L)
                .price(120L)
                .category("Продукты")
                .build();
        //when
        Mockito.when(authentication.getName()).thenReturn(email);
        Mockito.when(expensesService.putExpenses(expensesId,expensesDto,email)).thenReturn(expensesDto);

        var ResponseEntity = this.expensesController.putExpenses(expensesDto,expensesId,authentication);
        //then
        assertNotNull(ResponseEntity);
        assertEquals(HttpStatus.OK, ResponseEntity.getStatusCode());
        assertEquals(expensesDto, ResponseEntity.getBody());
    }

    @Test
    @DisplayName("getExpenses, return expenses by id")
    void getExpenses_returnExpensesDto() {
        //given
        Authentication authentication = mock(Authentication.class);

        String email = "den4k3@mail.ru";
        Long expensesId = 1L;

        ExpensesDto expensesDto = ExpensesDto.builder()
                .expensesId(1L)
                .name("Молоко")
                .userId(1L)
                .price(120L)
                .category("Продукты")
                .build();


        //when
        Mockito.when(authentication.getName()).thenReturn(email);
        Mockito.when(expensesService.getExpensesById(expensesId, email)).thenReturn(expensesDto);

        var ResponseEntity = this.expensesController.getExpenses(expensesId,authentication);
        //then
        assertNotNull(ResponseEntity);
        assertEquals(HttpStatus.OK, ResponseEntity.getStatusCode());
        assertEquals(expensesDto, ResponseEntity.getBody());
    }

    @Test
    @DisplayName("removeExpenses, remove expenses by id , return status code NoContent")
    void removeExpenses_returnStatusCodeNoContent() {
        //given
        Authentication authentication = mock(Authentication.class);

        String email = "den4k3@mail.ru";
        Long expensesId = 1L;

        //when
        Mockito.when(authentication.getName()).thenReturn(email);

        var ResponseEntity = this.expensesController.removeExpenses(expensesId,authentication);
        //then
        assertNotNull(ResponseEntity);
        assertEquals(HttpStatus.NO_CONTENT, ResponseEntity.getStatusCode());
    }
}