package org.denis.coinkeeper.api.factories;

import lombok.RequiredArgsConstructor;
import org.denis.coinkeeper.api.dto.CurrencyDto;
import org.denis.coinkeeper.api.dto.ProfitDto;
import org.denis.coinkeeper.api.dto.UserDto;
import org.denis.coinkeeper.api.dto.UserFinanceDto;
import org.denis.coinkeeper.api.entities.ProfitEntity;
import org.denis.coinkeeper.api.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
// По сути это не фабрика, а преобразователь в Dto
public class UserDtoFactory {

    private final ProfitDtoFactory profitDtoFactory;
    private final ExpensesDtoFactory expensesDtoFactory;


    public UserDto makeUserDto(UserEntity userEntity) {
        return UserDto.builder()
                .userId(userEntity.getUserId())
                .account(userEntity.getAccount())
                .email(userEntity.getEmail())
                .currency(CurrencyDto.builder()
                        .currencyId(userEntity.getCurrency().getCurrencyId())
                        .currencyName(userEntity.getCurrency().getCurrencyName())
                        .currencyDescription(userEntity.getCurrency().getCurrencyDescription())
                        .build())
                .build();
    }
    public UserFinanceDto makeFinanceDto(UserEntity userEntity) {
        return UserFinanceDto.builder()
                .profitEntityList(userEntity.getProfitList()
                        .stream()
                        .map(profitDtoFactory::makeProfitDto)
                        .toList())
                .expensesEntityList(userEntity.getExpensesList()
                        .stream()
                        .map(expensesDtoFactory::makeExpensesDto)
                        .toList()).build();
    }
}
