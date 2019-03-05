package com.kata.bankaccount;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

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
        account.deposit(amount);
        Assertions.assertThat(account.getBalance()).isEqualTo(amount);
    }

    @Test
    public void should_decrease_balance_when_make_withdrawal() {
        BigDecimal amount = valueOf(200);
        account.withdraw(amount);
        Assertions.assertThat(account.getBalance()).isEqualTo(valueOf(-200));
    }
}