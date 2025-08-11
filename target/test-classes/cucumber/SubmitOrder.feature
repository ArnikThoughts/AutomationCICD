
@tag
Feature: Purchse the order from Ecommerce website 

Background: I landed on Ecommerce Page


@tag2
Scenario Outline: Positive Test of submitting the order

   Given Logged in with username <userName> and password <password>
   When I add product<productName> to cart
   And  checkOut<productName> and submit the order
   Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page
   
   Examples:
            | userName             | password     | productName |
            |minaranisen@gmail.com |Minaranisen#1 |ZARA COAT 3  |