package com.kata.bankaccount;

import java.time.LocalDate;
import java.util.Objects;

public class Transaction {
    private final Operation operation;
    private final LocalDate date;
    private final Amount amount;
    private final Amount balance;

    public Transaction(Operation operation, LocalDate date, Amount amount, Amount balance) {
        this.operation = operation;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return operation == that.operation &&
                Objects.equals(date, that.date) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(balance, that.balance);
    }
}
