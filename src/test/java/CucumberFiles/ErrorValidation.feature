@tag
Feature: Error message validation on the Ecommerce Website

  @ErrorValidation
  Scenario Outline: I login with the wrong user name and password.

    Given I landed on the Ecommerce page
    When I logged in with <email> and <password>
    Then I see error message <errorMessage> is displayed

    Examples:
      | email         | password  | errorMessage                 |
      | swati@ria.com | Swati@ra1 | Incorrect email or password. |


  @ErrorValidation
  Scenario Outline: To check error message when wrong product name is compared.

    Given I landed on the Ecommerce page
    And I logged in with <email> and <password>
    When I add the <productName> to the cart
    Then I see the error validation

    Examples:
      | email           | password     | productName |
      | swati@radia.com | Swati@radia1 | ZARA COAT 3 |