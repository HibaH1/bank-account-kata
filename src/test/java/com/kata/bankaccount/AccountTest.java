package com.kata.bankaccount;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.kata.bankaccount.Operation.DEPOSIT;
import static com.kata.bankaccount.Operation.WITHDRAWAL;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() {
        Client client = new Client("123456", "clientName");
        account = new Account(client);
    }

    @Test
    public void should_increase_balance_when_make_deposit() {
        Amount amount = new Amount(valueOf(200));
        account.deposit(amount, LocalDate.now());
        assertThat(account.getBalance()).isEqualTo(amount);
    }

    @Test
    public void should_decrease_balance_when_make_withdrawal() {
        Amount amount = new Amount(valueOf(200));
        account.withdraw(amount, LocalDate.now());
        assertThat(account.getBalance()).isEqualTo(new Amount(valueOf(-200)));
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
        assertThat(account.getHistory()).containsExactlyElementsOf(expectedHistory);
    }
}