package com.kata.bankaccount;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;

public class AccountTest {

    @Test
    public void should_increase_balance_when_make_deposit() {
        Client client = new Client("123456", "clientName");
        Account account = new Account(client);
        BigDecimal amount = BigDecimal.valueOf(200);
        account.deposit(amount);
        Assertions.assertThat(account.getBalance()).isEqualTo(amount);
    }
}