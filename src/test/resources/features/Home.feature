@Homepage
Feature: Test the Sign In functionality

  @Title
  Scenario: Verify Print title, url
    Given I open Auckland Airport website
    Then I validate title and URL

  @ValidLogin
  Scenario Outline: Login with valid credentials
    Given I open Auckland Airport website
    When I try to login using "<username>" and "<password>"
    And I check login username

    Examples:
      | username            | password   |
      | test@mailinator.com | test123456 |


  @InValidLogin
  Scenario Outline: Invalid Email address and password - Verify signIn is UnSuccessful
    Given I open Auckland Airport website
    When I try to login using "<username>" and "<password>"
    And I should see following alert
     """
     Sorry, we do not have a Strata Club Membership with this email address. Please check you've entered your email address correctly.
     """

    Examples:
      | username   | password |
      | aa@abc.com | test123  |
