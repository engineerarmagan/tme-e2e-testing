Feature: TME Login

  @tme @smoke
    Scenario: Login TME
      Given the user is on the login page
      When the user enters the credentials
      Then the user should be able to login

  @tme @smoke
    Scenario: XTM login
      When the user is on XTM login page
      And enter the XTM credentials
      Then user should able to login to XTM


