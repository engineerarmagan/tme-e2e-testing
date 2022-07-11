Feature:TME Import Feature

@import
Scenario: Import BRM
  Given user imports publication type "BRM"
  When the user is on the login page
  And the user enters the credentials
  Then the user navigates to "HISTORY"

  Given filters status "WAITING"
  And filters stage "IMPORT"
  When filters language "EN"
  And user filters publication type "BRM"
  And filters
  Then selects first from the list

  Given user gets CPID and project name
  And user checks the status

  @import
  Scenario: Import BULLETIN
    Given user imports publication type "BULLETIN"
    When the user is on the login page
    And the user enters the credentials
    Then the user navigates to "HISTORY"

    Given filters status "WAITING"
    And filters stage "IMPORT"
    When filters language "EN"
    And user filters publication type "BULLETIN"
    And filters
    Then selects first from the list

    Given user gets CPID and project name
    And user checks the status

  @import
  Scenario: Import EWD
    Given user imports publication type "EWD"
    When the user is on the login page
    And the user enters the credentials
    Then the user navigates to "HISTORY"

    Given filters status "WAITING"
    And filters stage "IMPORT"
    When filters language "EN"
    And user filters publication type "EWD"
    And filters
    Then selects first from the list

    Given user gets CPID and project name
    And user checks the status

  @import
  Scenario: Import NCF
    Given user imports publication type "NCF"
    When the user is on the login page
    And the user enters the credentials
    Then the user navigates to "HISTORY"

    Given filters status "WAITING"
    And filters stage "IMPORT"
    When filters language "EN"
    And user filters publication type "NCF"
    And filters
    Then selects first from the list

    Given user gets CPID and project name
    And user checks the status

  @import
  Scenario: Import SDS
    Given user imports publication type "SDS"
    When the user is on the login page
    And the user enters the credentials
    Then the user navigates to "HISTORY"

    Given filters status "WAITING"
    And filters stage "IMPORT"
    When filters language "EN"
    And user filters publication type "SDS"
    And filters
    Then selects first from the list

    Given user gets CPID and project name
    And user checks the status