package com.kata.bankaccount;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.kata.bankaccount.Operation.DEPOSIT;
import static com.kata.bankaccount.Operation.WITHDRAWAL;
import static java.math.BigDecimal.ZERO;

public class Account {

    private Amount balance;
    private Client client;
    private List<Transaction> history = new ArrayList<>();

    public Account(Client client) {
        this.balance = new Amount(ZERO);
        this.client = client;
    }

    public void deposit(Amount amount, LocalDate localDate) {
        balance = balance.add(amount);
        Transaction e = new Transaction(DEPOSIT, localDate, amount, balance);
        history.add(e);
    }

    public void withdraw(Amount amount, LocalDate localDate) {
        balance = balance.subtract(amount);
        Transaction e = new Transaction(WITHDRAWAL, localDate, amount, balance);
        history.add(e);
    }

    public Amount getBalance() {
        return balance;
    }

    public List<Transaction> getHistory() {
        return history;
    }
}
