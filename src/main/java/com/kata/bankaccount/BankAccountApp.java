package com.kata.bankaccount;

import com.kata.bankaccount.domain.account.Account;
import com.kata.bankaccount.domain.account.Amount;
import com.kata.bankaccount.domain.client.Client;
import com.kata.bankaccount.infrastructure.ConsoleStatementPrinter;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BankAccountApp {
    public static void main(String[] args) {
        Client client = new Client("181920", "First Customer");
        Account account = new Account(client, new ConsoleStatementPrinter());

        account.deposit(new Amount(BigDecimal.valueOf(500)), LocalDate.of(2018, 11, 15));
        account.deposit(new Amount(BigDecimal.valueOf(1200)), LocalDate.of(2018, 12, 25));
        account.withdraw(new Amount(BigDecimal.valueOf(900)), LocalDate.of(2019, 1, 10));
        account.withdraw(new Amount(BigDecimal.valueOf(100)), LocalDate.of(2019, 2, 25));
        account.deposit(new Amount(BigDecimal.valueOf(500)), LocalDate.now());

        account.printStatement();
    }
}
