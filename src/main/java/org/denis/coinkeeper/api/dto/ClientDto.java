package org.denis.coinkeeper.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.denis.coinkeeper.api.entities.CurrencyEntity;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    @JsonProperty("client_id")
    private Long clientId;

    private Long account;

    private String email;

    private CurrencyEntity currency;
}
