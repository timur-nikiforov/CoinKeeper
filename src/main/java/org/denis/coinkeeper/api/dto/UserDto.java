package org.denis.coinkeeper.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.denis.coinkeeper.api.entities.CurrencyEntity;
import org.denis.coinkeeper.api.entities.ExpensesEntity;
import org.denis.coinkeeper.api.entities.ProfitEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Null
    private Long userId;

    private Long account;

    private String email;

    private CurrencyDto currency;

}
