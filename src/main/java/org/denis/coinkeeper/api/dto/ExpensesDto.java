package org.denis.coinkeeper.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpensesDto {

    @Null
    private Long expensesId;

    private String name;

    private String category;

    private Long price;

    @Null
    private Long userId;

    @Null
    @Builder.Default
    private Instant AddedAt = Instant.now();
}
