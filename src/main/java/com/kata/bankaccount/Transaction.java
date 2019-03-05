package com.kata.bankaccount;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Transaction {
    private final String operation;
    private final LocalDate date;
    private final BigDecimal amount;
    private final BigDecimal balance;

    public Transaction(String operation, LocalDate date, BigDecimal amount, BigDecimal balance) {

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
        return Objects.equals(operation, that.operation) &&
                Objects.equals(date, that.date) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, date, amount, balance);
    }
}
