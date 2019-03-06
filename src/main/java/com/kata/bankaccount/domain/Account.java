package com.kata.bankaccount.domain;

import com.kata.bankaccount.StatementPrinter;

import java.time.LocalDate;
import java.util.List;

public class Account {

    private Client client;
    private History history;
    private StatementPrinter statementPrinter;

    public Account(Client client, StatementPrinter statementPrinter) {
        this.client = client;
        this.history = new History();
        this.statementPrinter = statementPrinter;
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

    public void printStatement() {
        statementPrinter.print(history.getTransactions());
    }
}
