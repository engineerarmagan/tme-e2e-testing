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

  Scenario: Change the queue priority

    Given the user is on the login page
    When the user enters the credentials
    And the user navigates to "System" "Queue Priority"
    Then gets the priorities list

    Given user drags and drops a publication
    And save the new order
    Then check the new order is saved