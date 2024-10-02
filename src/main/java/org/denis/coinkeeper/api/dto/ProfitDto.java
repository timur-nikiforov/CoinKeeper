package org.denis.coinkeeper.api.dto;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfitDto {

    @Null
    private Long profitId;

    private String name;

    private String category;

    private Long price;

    @Null
    private Long userId;

    @Null
    @Builder.Default
    private Instant AddedAt = Instant.now();
}
