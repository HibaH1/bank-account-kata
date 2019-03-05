package com.kata.bankaccount.acceptance;

import com.kata.bankaccount.Amount;
import com.kata.bankaccount.Operation;
import com.kata.bankaccount.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionForTest {
    private final Operation operation;
    private final BigDecimal amount;
    private final String date;
    private final BigDecimal balance;

    public TransactionForTest(Operation operation, BigDecimal amount, String date, BigDecimal balance) {
        this.operation = operation;
        this.amount = amount;
        this.date = date;
        this.balance = balance;
    }

    Transaction toTransaction() {
        return new Transaction(this.operation, LocalDate.parse(this.date), new Amount(this.amount), new Amount(this.balance));
    }

}
