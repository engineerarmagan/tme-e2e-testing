Feature: TMNA Metadata

  @tmna @smoke
  Scenario:Metadata Object Download

    Given the user is on the TMNA login page
    And the user enters the credentials
    When the user navigates to "history"
    Then filters language "EN"

    Given user filters publication type "NCF"
    And filters
    When selects first from the list
    And user selects Metadata
    Then user should be able to download metadata objects
