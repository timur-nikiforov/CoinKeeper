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
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column
    private Long account;

    @Column(unique = true)
    private String email;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "currency_id",nullable = false)
    private CurrencyEntity currency;

    @Builder.Default
    @OneToMany
    //referencedColumnName - указываем название поля в java, а если есть @Column, то что внутри
    @JoinColumn(name = "client_id",referencedColumnName = "clientId", nullable = false)
    private List<ProfitEntity> profitEntityList = new ArrayList<>();

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "client_id",referencedColumnName = "clientId",nullable = false)
    private List<ExpensesEntity> expensesList = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name = "user", referencedColumnName = "userId")
//    private UserEntity user;

}
