Feature: A simple bank account version

  Scenario: As a client, I want to make a deposit in my account
    Given my account
    When I deposit 100 euros in this account
    Then The balance in this account is 100 euros

  Scenario: As a client, I want to make multiple deposits in my account
    Given my account
    When I deposit 100 euros in this account
    And I deposit 400 euros in this account
    Then The balance in this account is 500 euros

  Scenario: As a client, I want to make deposits and withdrawals from my account
    Given my account
    When I deposit 500 euros in this account
    And I withdraw 100 euros from this account
    And I withdraw 300 euros from this account
    And I deposit 900 euros in this account
    Then The balance in this account is 1000 euros