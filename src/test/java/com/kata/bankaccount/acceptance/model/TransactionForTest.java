package com.kata.bankaccount.acceptance.model;

import com.kata.bankaccount.domain.account.Amount;
import com.kata.bankaccount.domain.account.Operation;
import com.kata.bankaccount.domain.account.Transaction;

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

    public Transaction toTransaction() {
        return new Transaction(this.operation, LocalDate.parse(this.date), new Amount(this.amount), new Amount(this.balance));
    }

}
