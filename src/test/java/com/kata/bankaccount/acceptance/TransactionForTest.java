package com.kata.bankaccount.acceptance;

import com.kata.bankaccount.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionForTest {
    private final String operation;
    private final BigDecimal amount;
    private final String date;
    private final BigDecimal balance;

    public TransactionForTest(String operation, BigDecimal amount, String date, BigDecimal balance) {
        this.operation = operation;
        this.amount = amount;
        this.date = date;
        this.balance = balance;
    }

    Transaction toTransaction() {
        return new Transaction(this.operation, LocalDate.parse(this.date), this.amount, this.balance);
    }

}
