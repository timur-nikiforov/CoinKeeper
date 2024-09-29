package org.denis.coinkeeper.api.Services;

import org.denis.coinkeeper.api.dto.CurrencyDto;
import org.denis.coinkeeper.api.factories.CurrencyDtoFactory;
import org.denis.coinkeeper.api.repositories.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    CurrencyRepository currencyRepository;
    CurrencyDtoFactory currencyDtoFactory;

    public List<CurrencyDto> getCurrencyDtoList() {

        return currencyRepository.streamAllBy()
                .map(currencyDtoFactory::makeCurrencyDto)
                .collect(Collectors.toList());
    }
}
