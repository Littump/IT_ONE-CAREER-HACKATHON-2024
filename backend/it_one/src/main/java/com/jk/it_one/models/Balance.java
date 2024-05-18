package com.jk.it_one.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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


    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("balance")
    @Column(name = "balance")
    String value;

    @JsonProperty("currency")
    @Column(name = "currency")
    Currency currency;

    public Balance() {
    }
    public Balance(User user, String value, Currency currency) {
        this.user = user;
        this.value = value;
        this.currency = currency;
    }
}
