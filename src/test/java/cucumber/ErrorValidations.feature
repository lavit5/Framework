Feature: Error validation

  Scenario Outline:
    Given I land on page
    When Logged in with <username> and <password>
    Then "Incorrect email or password." should be displayed

    Examples:
      |username|password|
      |burek1@burek.com|Burekburek#1|
      |burek2@burek.com|Burekburek#1|