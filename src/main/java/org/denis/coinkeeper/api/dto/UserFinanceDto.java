package org.denis.coinkeeper.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
public class UserFinanceDto {

    @JsonProperty("profit")
    private List<ProfitDto> profitEntityList;

    @JsonProperty("expenses")
    private List<ExpensesDto> expensesEntityList;

}
