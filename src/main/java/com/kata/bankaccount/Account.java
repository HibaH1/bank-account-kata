package com.kata.bankaccount;

import java.time.LocalDate;
import java.util.List;

public class Account {

    private Client client;
    private History history;

    public Account(Client client) {
        this.client = client;
        this.history = new History();
    }

    public void deposit(Amount amount, LocalDate localDate) {
        history.addDepositTransaction(amount, localDate);
    }

    public void withdraw(Amount amount, LocalDate localDate) {
        history.addWithdrawalTransaction(amount, localDate);
    }

    public Amount getBalance() {
        return history.getBalance();
    }

    public List<Transaction> getTransactions() {
        return history.getTransactions();
    }
}
