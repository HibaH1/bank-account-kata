package com.kata.bankaccount.acceptance;

import com.kata.bankaccount.Account;
import com.kata.bankaccount.Client;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountStepDefs {

    private Account account;

    @Given("^my account$")
    public void myAccount() {
        Client client = new Client("123456", "clientName");
        account = new Account(client);
    }

    @When("^I deposit (\\d+) euros in this account$")
    public void iDepositEurosInThisAccount(BigDecimal amount) {
        account.deposit(amount);
    }

    @When("^I withdraw (\\d+) euros from this account$")
    public void iWithdrawEurosFromThisAccount(BigDecimal amount) {
        account.withdraw(amount);
    }

    @Then("^The balance in this account is (\\d+) euros$")
    public void theBalanceInThisAccountShouldBeEuros(BigDecimal amount) {
        assertThat(account.getBalance()).isEqualTo(amount);
    }
}