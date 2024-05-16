package com.jk.it_one.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jk.it_one.enums.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "balances")
public class Balance {
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "balance")
    String balance;

    @Column(name = "currency")
    Currency currency;

    public Balance() {
    }
    public Balance(User user, String balance, Currency currency) {
        this.user = user;
        this.balance = balance;
        this.currency = currency;
    }
}
