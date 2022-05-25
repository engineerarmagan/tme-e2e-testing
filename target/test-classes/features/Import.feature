Feature:Import Feature

@import
  Scenario:Import a publication

    #Given user uploads publication type
    When the user is on the login page
    And the user enters the credentials
    Then the user navigates to "HISTORY"
  
    Given filters status "WAITING"
    And filters stage "EXTRACT"
    When filters language "EN"
    And filters
    Then selects first from the list
  
    Given user gets CPID and project name
    And approves the metadata
    When user navigates to stage details page
    Then publication stage should be PUBLISH
    And publication status should be BLOCKED





