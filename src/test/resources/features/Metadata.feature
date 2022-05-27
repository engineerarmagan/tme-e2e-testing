Feature: Metadata
  @metadata
  Scenario:Metadata Object Download

    Given the user is on the login page
    And the user enters the credentials
    When the user navigates to "history"
    And filters status "BLOCKED"
    Then filters language "EN"

    Given user filters publication type "NCF"
    And filters
    When selects first from the list
    And user selects Metadata
    Then user should be able to download metadata objects
