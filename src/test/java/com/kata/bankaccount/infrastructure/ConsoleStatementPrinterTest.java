package com.kata.bankaccount.infrastructure;

import com.kata.bankaccount.domain.StatementPrinter;
import com.kata.bankaccount.domain.account.Amount;
import com.kata.bankaccount.domain.account.Operation;
import com.kata.bankaccount.domain.account.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.kata.bankaccount.domain.account.Operation.DEPOSIT;
import static com.kata.bankaccount.domain.account.Operation.WITHDRAWAL;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConsoleStatementPrinterTest {
    private StatementPrinter statementPrinter;
    private PrintStream sysOut;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @Before
    public void setUp() {
        statementPrinter = new ConsoleStatementPrinter();
        sysOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(sysOut);
    }

    @Test
    public void should_console_print() {
        final List<Transaction> transactions = Arrays.asList(
                generateTransaction(DEPOSIT, LocalDate.of(2018, 11, 15), 500, 500),
                generateTransaction(WITHDRAWAL, LocalDate.of(2018, 12, 25), 200, 300),
                generateTransaction(DEPOSIT, LocalDate.of(2019, 1, 10), 1200, 1500),
                generateTransaction(WITHDRAWAL, LocalDate.of(2019, 2, 25), 700, 800));
        final String expected = "| operation | date | amount | balance |\n" +
                "| DEPOSIT | 2018-11-15 | 500 | 500 |\n" +
                "| WITHDRAWAL | 2018-12-25 | 200 | 300 |\n" +
                "| DEPOSIT | 2019-01-10 | 1200 | 1500 |\n" +
                "| WITHDRAWAL | 2019-02-25 | 700 | 800 |\n";

        statementPrinter.print(transactions);

        assertThat(outContent.toString()).isEqualToIgnoringNewLines(expected);
    }

    private Transaction generateTransaction(Operation operation, LocalDate date, long amount, long balance) {
        return new Transaction(operation, date, new Amount(valueOf(amount)), new Amount(valueOf(balance)));
    }
}