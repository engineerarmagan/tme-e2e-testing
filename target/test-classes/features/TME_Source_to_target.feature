Feature: TME Source to target languages

 @tme @smoke
  Scenario: Source to target languages columns

    Given the user is on the login page
    When the user enters the credentials
    And the user navigates to "System" "Source to Target Languages"
    Then user should see following columns
      | Source Workflow |
      | Source Language |
      | Source Type     |
      | Format Type     |
      | Target Language |
      | Target Workflow |
      | Translator      |
