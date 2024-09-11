package org.denis.coinkeeper.api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Client {

    @Column(name = "client_id") @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long clientId;

    @Column
    private Long account;

    @Column(unique = true)
    private String email;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "currency_id",nullable = false)
    private Currency currency;

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "id",nullable = false)
    private List<Profit> profitList = new ArrayList<>();

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "id",nullable = false)
    private List<Expenses> expensesList = new ArrayList<>();

}
