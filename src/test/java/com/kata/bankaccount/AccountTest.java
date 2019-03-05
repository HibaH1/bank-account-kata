package com.kata.bankaccount;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
        BigDecimal amount = valueOf(200);
        account.deposit(amount, LocalDate.now());
        assertThat(account.getBalance()).isEqualTo(amount);
    }

    @Test
    public void should_decrease_balance_when_make_withdrawal() {
        BigDecimal amount = valueOf(200);
        account.withdraw(amount, LocalDate.now());
        assertThat(account.getBalance()).isEqualTo(valueOf(-200));
    }

    @Test
    public void should_render_history_of_all_transactions() {
        account.deposit(valueOf(500), LocalDate.now());
        account.withdraw(valueOf(200), LocalDate.now());
        List<Transaction> expectedHistory = Arrays.asList(
                new Transaction("DEPOSIT", LocalDate.now(), valueOf(500), valueOf(500)),
                new Transaction("WITHDRAWAL", LocalDate.now(), valueOf(200), valueOf(300))
        );
        assertThat(account.getHistory()).containsExactlyElementsOf(expectedHistory);
    }
}