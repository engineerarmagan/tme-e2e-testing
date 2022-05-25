Feature: Event History

@smoke
  Scenario: Event History Columns

    Given the user is on the login page
    When the user enters the credentials
    And the user navigates to "System" "Event History"
    Then user should see following columns
      | CPID         |
      | User Name    |
      | Date         |
      | Alt-Pub-ID   |
      | Version      |
      | Language     |
      | Event Type   |
      | Event Number |
      | Event Title  |
      | Stage/Area   |
      | Details      |