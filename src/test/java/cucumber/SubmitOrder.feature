@tag
Feature:Purchase the order from ecommerce website
 
Background:
Given I landed on Ecommerce Page
 
@Regression
Scenario Outline: positive test of submitting the order
Given Logged in with username <name> and password <password>
When I add product <productName> to cart
And Checkout <productName> and submit the order
Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

Examples:       
| name                   | password      | | productName     |      
| Ankit.Gautam@gmail.com | Ankit@123     | | ZARA COAT 3     |  
 
