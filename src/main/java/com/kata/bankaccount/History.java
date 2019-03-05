package com.kata.bankaccount;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.kata.bankaccount.Operation.DEPOSIT;
import static com.kata.bankaccount.Operation.WITHDRAWAL;
import static java.math.BigDecimal.ZERO;

public class History {

    private Amount balance = new Amount(ZERO);
    private List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getTransactions() {
        return transactions;
    }

    void addDepositTransaction(Amount amount, LocalDate localDate) {
        balance = balance.add(amount);
        Transaction transaction = new Transaction(DEPOSIT, localDate, amount, balance);
        transactions.add(transaction);
    }

    void addWithdrawalTransaction(Amount amount, LocalDate localDate) {
        balance = balance.subtract(amount);
        Transaction transaction = new Transaction(WITHDRAWAL, localDate, amount, balance);
        transactions.add(transaction);
    }

    public Amount getBalance() {
        return balance;
    }
}
