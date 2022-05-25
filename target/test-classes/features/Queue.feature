Feature: Queue
@smoke
  Scenario: Queue Columns

    Given the user is on the login page
    When the user enters the credentials
    When the user navigates to "queue"
    Then user should see following queue columns
      | Order      |
      | AltPub     |
      | Version    |
      | GuardHouse |
      | Language   |
      | Status     |