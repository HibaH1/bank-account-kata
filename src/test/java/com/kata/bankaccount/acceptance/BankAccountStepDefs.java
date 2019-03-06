package com.kata.bankaccount.acceptance;

import com.kata.bankaccount.acceptance.model.TransactionForTest;
import com.kata.bankaccount.domain.account.Account;
import com.kata.bankaccount.domain.account.Amount;
import com.kata.bankaccount.domain.client.Client;
import com.kata.bankaccount.domain.account.Transaction;
import com.kata.bankaccount.infrastructure.ConsoleStatementPrinter;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountStepDefs {

    private Account account;

    @Given("^My account$")
    public void myAccount() {
        Client client = new Client("123456", "clientName");
        account = new Account(client, new ConsoleStatementPrinter());
    }

    @When("^I deposit (\\d+) euros in this account$")
    public void iDepositEurosInThisAccount(BigDecimal amount) {
        account.deposit(new Amount(amount), LocalDate.now());
    }

    @When("^I withdraw (\\d+) euros from this account$")
    public void iWithdrawEurosFromThisAccount(BigDecimal amount) {
        account.withdraw(new Amount(amount), LocalDate.now());
    }

    @When("^I had deposited (\\d+) euros in this account on (.*)$")
    public void iDepositEurosInThisAccountOn(BigDecimal amount, String date) {
        account.deposit(new Amount(amount), LocalDate.parse(date));
    }

    @When("^I had withdrawn (\\d+) euros from this account on (.*)$")
    public void iWithdrawEurosFromThisAccountOn(BigDecimal amount, String date) {
        account.withdraw(new Amount(amount), LocalDate.parse(date));
    }

    @Then("^The balance in this account is (\\d+) euros$")
    public void theBalanceInThisAccountShouldBeEuros(BigDecimal amount) {
        assertThat(account.getBalance()).isEqualTo(new Amount(amount));
    }

    @Then("^My history contains (\\d+) transactions$")
    public void myHistoryContainsTransactions(int historySize) {
        assertThat(account.getTransactions().size()).isEqualTo(historySize);
    }

    @Then("^My history is :$")
    public void myHistoryIs(List<TransactionForTest> transactions) {
        List<Transaction> expectedTransactions = transactions.stream()
                .map(TransactionForTest::toTransaction)
                .collect(Collectors.toList());
        assertThat(account.getTransactions()).isEqualTo(expectedTransactions);
    }
}