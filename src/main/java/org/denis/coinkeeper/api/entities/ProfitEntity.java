package org.denis.coinkeeper.api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profit")
public class ProfitEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profitId;

    @Column
    private String category;

    @Column
    private Long price;

    @Column
    @Builder.Default
    private Instant AddedAt = Instant.now();
}
