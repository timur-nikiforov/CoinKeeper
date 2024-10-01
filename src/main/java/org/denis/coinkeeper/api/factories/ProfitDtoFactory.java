package org.denis.coinkeeper.api.factories;

import org.denis.coinkeeper.api.dto.ProfitDto;
import org.denis.coinkeeper.api.entities.ProfitEntity;
import org.springframework.stereotype.Component;

@Component
public class ProfitDtoFactory {

    public ProfitDto makeProfitDto(ProfitEntity profitEntity) {
        return ProfitDto.builder()
                .name(profitEntity.getName())
                .profitId(profitEntity.getProfitId())
                .price(profitEntity.getPrice())
                .userId(profitEntity.getUser().getUserId())
                .category(profitEntity.getCategory())
                .AddedAt(profitEntity.getAddedAt())
                .build();
    }
}
