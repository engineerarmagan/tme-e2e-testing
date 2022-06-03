Feature: TME LIM Page

@tme @smoke
  Scenario: Lim language selection

    Given the user is on the login page
    When the user enters the credentials
    And the user navigates to "Utilities" "LIM"
    And selects from dropdown
    Then user should see language options
      | Czech      |
      | Danish    |
      | Dutch      |
      | Finnish    |
      | Greek      |
      | Hebrew     |
      | Hungarian  |
      | Italian    |
      | Norwegian  |
      | Polish     |
      | Portuguese |
      | Russian    |
      | Swedish    |
      | Turkish    |
