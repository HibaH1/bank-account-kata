package com.kata.bankaccount.domain;

import com.kata.bankaccount.domain.account.Account;
import com.kata.bankaccount.domain.account.Amount;
import com.kata.bankaccount.domain.account.Operation;
import com.kata.bankaccount.domain.account.Transaction;
import com.kata.bankaccount.domain.client.Client;
import com.kata.bankaccount.domain.exceptions.NegativeAmountException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.kata.bankaccount.domain.account.Operation.DEPOSIT;
import static com.kata.bankaccount.domain.account.Operation.WITHDRAWAL;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AccountTest {

    private Account account;
    private StatementPrinter statementPrinter;

    @Before
    public void setUp() {
        statementPrinter = mock(StatementPrinter.class);
        Client client = new Client("123456", "clientName");
        account = new Account(client, statementPrinter);
    }

    @Test
    public void should_increase_balance_when_make_deposit() {
        Amount amount = new Amount(valueOf(200));
        account.deposit(amount, LocalDate.now());
        assertThat(account.getBalance()).isEqualTo(amount);
    }

    @Test
    public void should_decrease_balance_when_make_withdrawal() {
        account.deposit(new Amount(valueOf(300)), LocalDate.now());
        account.withdraw(new Amount(valueOf(200)), LocalDate.now());
        assertThat(account.getBalance()).isEqualTo(new Amount(valueOf(100)));
    }

    @Test
    public void should_render_history_of_all_transactions() {
        Amount firstTransactionAmount = new Amount(valueOf(500));
        Amount secondTransactionAmount = new Amount(valueOf(200));
        List<Transaction> expectedHistory = Arrays.asList(
                new Transaction(DEPOSIT, LocalDate.now(), firstTransactionAmount, new Amount(valueOf(500))),
                new Transaction(WITHDRAWAL, LocalDate.now(), secondTransactionAmount, new Amount(valueOf(300)))
        );
        account.deposit(firstTransactionAmount, LocalDate.now());
        account.withdraw(secondTransactionAmount, LocalDate.now());
        assertThat(account.getTransactions()).containsExactlyElementsOf(expectedHistory);
    }

    @Test
    public void should_throw_exception_when_negative_transaction_amount() {
        assertThatThrownBy(() -> new Amount(valueOf(-500)))
                .isInstanceOf(NegativeAmountException.class)
                .hasMessage("Business Error: Negative amounts are not allowed.");
    }

    @Test
    public void should_print_statement() {
        List<Transaction> transactions = Collections.singletonList(generateSampleTransaction());
        account.deposit(new Amount(valueOf(500)), LocalDate.now());
        account.printStatement();
        verify(statementPrinter).print(transactions);
    }

    private Transaction generateSampleTransaction() {
        return new Transaction(Operation.DEPOSIT,
                LocalDate.now(),
                new Amount(valueOf(500)),
                new Amount(valueOf(500)));
    }
}