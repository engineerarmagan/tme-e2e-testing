Feature: SIL

  @sil
  Scenario: Sil Columns

    Given the user is on the login page
    When the user enters the credentials
    When the user navigates to "sil"
    Then user should see following SIL columns
      | Sil        |
      | Model      |
      | Term       |
      | Generation |
      | RM         |
      | BRM        |
      | EWD        |
      | NCF        |
      | SDS        |
      | Embargo    |
    Then user selects a sil
    And user can download the csv file with name "sil_history"
