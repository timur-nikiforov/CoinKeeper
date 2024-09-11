package org.denis.coinkeeper.api.factories;

import org.denis.coinkeeper.api.dto.ExpensesDto;
import org.denis.coinkeeper.api.entities.ExpensesEntity;
import org.springframework.stereotype.Component;

@Component
public class ExpensesDtoFactory {

    public ExpensesDto makeExpensesDto(ExpensesEntity expensesEntity) {
        return ExpensesDto.builder()
                .expensesId(expensesEntity.getExpensesId())
                .price(expensesEntity.getPrice())
                .category(expensesEntity.getCategory())
                .AddedAt(expensesEntity.getAddedAt())
                .build();
    }
}
