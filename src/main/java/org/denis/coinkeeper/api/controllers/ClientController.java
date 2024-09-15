package org.denis.coinkeeper.api.controllers;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.denis.coinkeeper.api.dto.ClientDto;
import org.denis.coinkeeper.api.dto.CurrencyDto;
import org.denis.coinkeeper.api.entities.CurrencyEntity;
import org.denis.coinkeeper.api.factories.CurrencyDtoFactory;
import org.denis.coinkeeper.api.repositories.CurrencyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Transactional
@RestController
public class ClientController {

    private final CurrencyRepository currencyRepository;
    private final CurrencyDtoFactory currencyDtoFactory;

    public static final String MAIN_PAGE = "/api/main";
    public static final String CURRENCY_PAGE = "/api/main/currency";
    public static final String ACCOUNT_PAGE = "/api/main/account";


    @GetMapping(CURRENCY_PAGE)
    public List<CurrencyDto> getCurrencyPage() {
        return currencyRepository.streamAllBy()
                .map(currencyDtoFactory::makeCurrencyDto)
                .collect(Collectors.toList());
    }
//
//    @PostMapping(CURRENCY_PAGE)
//    public void createCurrencyPage(@RequestParam(value = "currency") String currency) {
//
//    }
//    @GetMapping(MAIN_PAGE)
//    public getAccountPage() {
//
//    }
}
