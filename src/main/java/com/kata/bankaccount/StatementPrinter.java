package com.kata.bankaccount;

import com.kata.bankaccount.domain.Transaction;
import java.util.List;

public interface StatementPrinter {
    void print(List<Transaction> history);
}
