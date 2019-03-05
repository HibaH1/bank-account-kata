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