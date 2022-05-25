Feature: Dashboard

  @awstest
  Scenario:Dashboard Menu Options

    Given the user is on the login page
    When the user enters the credentials
    Then user should see following options
      | Syste  |
      | Utilities |
      | SIL       |
      | Queue     |
      | History   |

 @awstest
  Scenario: Dashboard Columns
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
