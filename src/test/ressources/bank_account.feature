Feature: A simple bank account version

  Scenario: As a client, I want to make a deposit in my account
    Given My account
    When I deposit 100 euros in this account
    Then The balance in this account is 100 euros

  Scenario: As a client, I want to make multiple deposits in my account
    Given My account
    When I deposit 100 euros in this account
    And I deposit 400 euros in this account
    Then The balance in this account is 500 euros

  Scenario: As a client, I want to make deposits and withdrawals from my account
    Given My account
    When I deposit 500 euros in this account
    And I withdraw 100 euros from this account
    And I withdraw 300 euros from this account
    And I deposit 900 euros in this account
    Then The balance in this account is 1000 euros

  Scenario: As a customer, I want to make several operation and consult my history
    Given My account
    When I had deposited 400 euros in this account on 2018-10-10
    And I had withdrawn 200 euros from this account on 2019-02-11
    When I had deposited 800 euros in this account on 2019-03-01
    Then My history contains 3 transactions
    And My history is :
      | Operation  | Date       | Amount | Balance |
      | DEPOSIT    | 2018-10-10 | 400    | 400     |
      | WITHDRAWAL | 2019-02-11 | 200    | 200     |
      | DEPOSIT    | 2019-03-01 | 800    | 1000    |