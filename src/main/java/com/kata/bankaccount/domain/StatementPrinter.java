package com.kata.bankaccount.domain;

import com.kata.bankaccount.domain.account.Transaction;
import java.util.List;

public interface StatementPrinter {
    void print(List<Transaction> history);
}
