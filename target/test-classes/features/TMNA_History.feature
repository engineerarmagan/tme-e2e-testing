Feature: TMNA History Page

  @tmna @smoke
  Scenario:History Columns

    Given the user is on the TMNA login page
    And the user enters the credentials
    When the user navigates to "history"
    Then user should see following sortable headers
      | CPID                 |
      | AltPubId / Ver / Run |
      | Pub Type             |
      | Content Type         |
      | Language             |
      | Translation Type     |
      | Start Time           |
      | Last Update          |
      | Status               |