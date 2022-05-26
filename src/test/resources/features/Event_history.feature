Feature: Event History

@event
  Scenario: Event History page TME

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
  #Given the user filters event stage "CONVERT"
  And filters the event type "audit"
  And filters
  Then user should see only the filtered event type


@event
  Scenario: Event History page TMNA

    Given the user is on the TMNA login page
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
    #Given the user filters event stage "CONVERT"
    And filters the event type "trigger"
    And filters
    Then user should see only the filtered event type
