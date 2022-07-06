Feature: TME Dashboard Page
@tme @smoke1
  Scenario:Dashboard menu options check

    Given the user is on the login page
    When the user enters the credentials
    Then user should see following options
      | System |
      | Utilities |
      | SIL       |
      | Queue     |
      | History   |

@tme @smoke1
  Scenario: Dashboard columns check
    Given the user is on the login page
    When the user enters the credentials
    Then user should see following columns

      | Import           |
      | Extract Metadata |
      | Convert          |
      | Pre-Translate    |
      | Translate        |
      | Post-Translate   |
      | Image Review     |
      | Publish          |
      | Pre-QA           |
      | QA               |
