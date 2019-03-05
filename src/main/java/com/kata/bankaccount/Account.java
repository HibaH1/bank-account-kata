package com.kata.bankaccount;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ZERO;

public class Account {

    private BigDecimal balance = ZERO;
    private Client client;
    private List<Transaction> history = new ArrayList<>();

    public Account(Client client) {
        this.client = client;
    }

    public void deposit(BigDecimal amount, LocalDate localDate) {
        balance = balance.add(amount);
        history.add(new Transaction("DEPOSIT", localDate, amount, balance));
    }

    public void withdraw(BigDecimal amount, LocalDate localDate) {
        balance = balance.subtract(amount);
        history.add(new Transaction("WITHDRAWAL", localDate, amount, balance));
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getHistory() {
        return history;
    }
}
