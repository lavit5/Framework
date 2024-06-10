Feature: Purchase the order from website

  Background:
    Given I land on page

Scenario Outline:  Submitting the order
  Given Logged in with <username> and <password>
  When I add the <product> to Cart
  And Checkout <product> and submit order
  Then "THANKYOU FOR THE ORDER." should appear




  Examples:
  |username|password|product|
  |burek@burek.com|Burekburek#1|ZARA COAT 3|
  |burek@burek.com|Burekburek#1|ADIDAS ORIGINAL|