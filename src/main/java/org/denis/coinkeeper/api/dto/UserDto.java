package org.denis.coinkeeper.api.dto;


import jakarta.validation.constraints.Null;
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
public class UserDto {

    @Null
    private Long userId;

    private Long account;

    private String email;

    private CurrencyDto currency;

}
