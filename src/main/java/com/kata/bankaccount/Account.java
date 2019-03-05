package com.kata.bankaccount;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public class Account {

    private BigDecimal balance = ZERO;
    private Client client;

    public Account(Client client) {
        this.client = client;
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
