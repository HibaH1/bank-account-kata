package com.kata.bankaccount.infrastructure;

import com.kata.bankaccount.StatementPrinter;
import com.kata.bankaccount.domain.Transaction;

import java.util.List;

public class ConsoleStatementPrinter implements StatementPrinter {

    private static final String HEADER = "| operation | date | amount | balance |";

    public void print(List<Transaction> history) {
        System.out.println(HEADER);
        history.forEach(ConsoleStatementPrinter::printLine);
    }

    private static void printLine(Transaction transaction) {
        String line = "| " + transaction.getOperation() +
                " | " + transaction.getDate() +
                " | " + transaction.getAmount().getValue() +
                " | " + transaction.getBalance().getValue() + " |";
        System.out.println(line);
    }
}
