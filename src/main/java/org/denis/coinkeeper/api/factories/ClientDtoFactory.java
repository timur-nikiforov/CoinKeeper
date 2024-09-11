package org.denis.coinkeeper.api.factories;

import org.denis.coinkeeper.api.dto.ClientDto;
import org.denis.coinkeeper.api.entities.ClientEntity;
import org.springframework.stereotype.Component;

@Component
// По сути это не фабрика, а преобразователь в Dto
public class ClientDtoFactory {

    public ClientDto makeClientDto(ClientEntity clientEntity) {
        return ClientDto.builder()
                .clientId(clientEntity.getClientId())
                .account(clientEntity.getAccount())
                .email(clientEntity.getEmail())
                .currency(clientEntity.getCurrency())
                .build();
    }
}
