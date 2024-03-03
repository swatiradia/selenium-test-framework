@tag
  Feature: Purchase any item and place order on E-commerce site

    Background:
      Given I landed on the Ecommerce page

    Scenario Outline: Place an order and complete the transaction

      Given I logged in with <email> and <password>
      When I add the <productName> to the cart
      And I checkout with <productName>
      Then I see <confirmationMessage> is displayed

      Examples:
        | email           | password     | productName     | confirmationMessage     |
        | swati@radia.com | Swati@radia1 | ZARA COAT 3     | Thankyou for the order. |
        | mihir@radia.com | Mihir@radia6 | ADIDAS ORIGINAL | Thankyou for the order. |
