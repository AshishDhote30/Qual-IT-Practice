Feature: Sample purchase from Auckland Airport Shopping App

  Background: Open AKL Airport Website
    Given user open Auckland Airport website

  @Homepage
  @PurchaseWine
  Scenario: A user purchase Spirit and Wine
    And the user clicks the "wine and spirit" link
    And clicks on the spirit
    When the user adds it to cart then selects "departure" as pickup from AKL international airport
    Then the message "You’ve added this item to your cart for pick up departing from AKL International Airport." is displayed
    When the user clicks his cart
    Then the item "White Walker 1L" should be listed in the cart

  @Homepage
  @PurchaseWineBySearch
  Scenario: a user searches for a particular wine and add to cart
    And the user input "Johnnie Walker Black 200ml" and click search button
    When the user adds it to cart then selects "departure" as pickup from AKL international airport
    Then the message "You’ve added this item to your cart for pick up departing from AKL International Airport." is displayed
    When the user clicks his cart
    Then the item "Johnnie Walker Black 200ml" should be listed in the cart


  @PurchaseTech
  Scenario: a user purchases a phone
    And the user clicks the "Technology" link
    And clicks on the phone
    When the user adds it to cart then selects "departure" as pickup from AKL international airport
    Then the message "You’ve added this item to your cart for pick up departing from AKL International Airport" is displayed
    When the user clicks his cart
    Then the item "Apple iPhone XS 64GB Space Grey" should be listed in the cart
    And the cart price should display the correct amount for this purchase
