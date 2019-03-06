package com.kata.bankaccount;

import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.domain.Amount;
import com.kata.bankaccount.domain.Client;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.domain.exceptions.NegativeAmountException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.kata.bankaccount.domain.Operation.DEPOSIT;
import static com.kata.bankaccount.domain.Operation.WITHDRAWAL;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
}