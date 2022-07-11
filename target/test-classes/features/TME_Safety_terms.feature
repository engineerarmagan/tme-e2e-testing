Feature: TME Safety Terms

@tme @smoke
  Scenario: Safety Terms dropdowns and download

    Given the user is on the login page
    When the user enters the credentials
    And the user navigates to "Utilities" "Safety Terms"
    Then should see the show only options as
    | Unknown      |
    | Translated   |
    | Untranslated |
  And user clicks Translated

  Then user can download the csv file with name "safety_terms"