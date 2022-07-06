Feature: TMNA Dashboard Page
  @tmna @smoke
  Scenario:Dashboard menu options check

    Given the user is on the TMNA login page
    When the user enters the credentials
    Then user should see following options
      | System    |
      | Utilities |
      | History   |

  @tmna @smoke
  Scenario: Dashboard columns check
    Given the user is on the TMNA login page
    When the user enters the credentials
    Then user should see following columns

      | Import           |
      | Extract Metadata |
      | Convert          |
      | Pre-Translate    |
      | Translate        |
      | Post-Translate   |
      | Image Review     |
      | QA               |
      | Publish          |
