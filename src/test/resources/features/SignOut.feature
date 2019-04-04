Feature: Test the Sign Out functionality

  @Homepage
  @SignOut
  Scenario Outline: Verify sign out
    Given user open Auckland Airport website
    When user try to login using "<username>" and "<password>"
    And user clicks on my account
    And user sign out

    Examples:
      | username            | password   |
      | test@mailinator.com | test123456 |