package org.denis.coinkeeper.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
public class UserSummaryDto {

    private UserDto userDto;
    private UserFinanceDto userFinanceDto;

}
