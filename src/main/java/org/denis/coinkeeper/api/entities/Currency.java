package org.denis.coinkeeper.api.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Currency {

    @Column(name = "currency_id") @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long currencyId;

    @Column(name = "currency_name")
    private String currencyName;

    @Column(name = "currency_description")
    private String currencyDescription;
}
