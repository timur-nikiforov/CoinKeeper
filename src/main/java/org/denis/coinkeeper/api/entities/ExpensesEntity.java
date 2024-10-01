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
@Table(name = "expenses")
public class ExpensesEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expensesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column
    private String name;

    @Column
    private String category;

    @Column
    private Long price;

    @Column
    @Builder.Default
    private Instant AddedAt = Instant.now();
}
