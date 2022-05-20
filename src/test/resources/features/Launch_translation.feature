Feature:Translation feature

@ht
 Scenario Outline: Translation QA-BULLETIN
    Given the user is on the login page
    And the user enters the credentials
    When the user navigates to "HISTORY"
    And filters status "BLOCKED"
    Then filters language "EN"

    Given user filters publication type "<publication type>"
    Then  filters
    When selects first from the list
    And change the QA by value chain to OFF
    Then click translation status

    Given user selects translation type PE
    And selects the language and launch
    When the user navigates to "HISTORY"
    And clear the filter
    Then selects first from the list
    Then user gets CPID and project name

    Given the user is on XTM login page
    And enter the XTM credentials
    Then user should able to login to XTM

    Given user on All Project page
    When search the project
    And select the first result
    Then wait until cost analysis complete

    Given user navigates to stage details page
    Then selects view costs and approves if required

    Given the user navigates to previous XTM page
    Given user on All Project page
    When search the project
    Then finish the project


  Given user navigates to stage details page
  And user checks the status
  Examples:
    |publication type|
    |NCF       |


  @tme
  Scenario: Translation with QA-NCF

  Given the user is on the login page
  And the user enters the credentials
  When the user navigates to "HISTORY"
  And filters status "BLOCKED"
  Then filters language "EN"

  Given user filters publication type "NCF"
  And filters
  When selects first from the list
  And change the QA by value chain to ON
    And change the Block at publish to OFF
  Then click translation status

  Given user selects translation type PE
  And selects the language and launch
  When the user navigates to "HISTORY"
  And clear the filter
  Then selects first from the list
  Then user gets CPID and project name

  Given the user is on XTM login page
  And enter the XTM credentials
  Then user should able to login to XTM

  Given user on All Project page
  When search the project
  And select the first result
  Then wait until cost analysis complete

  Given user navigates to stage details page
  Then selects view costs and approves if required


  Given the user navigates to previous XTM page
  Given user on All Project page
  When search the project
  Then finish the project

  Given user navigates to stage details page
    And user checks the status

  @tme
  Scenario: Translation with QA-SDS

    Given the user is on the login page
    And the user enters the credentials
    When the user navigates to "HISTORY"
    And filters status "BLOCKED"
    Then filters language "EN"

    Given user filters publication type "SDS"
    And filters
    When selects first from the list
    And change the QA by value chain to ON
    And change the Block at publish to OFF
    Then click translation status

    Given user selects translation type PE
    And selects the language and launch
    When the user navigates to "HISTORY"
    And clear the filter
    Then selects first from the list
    Then user gets CPID and project name

    Given the user is on XTM login page
    And enter the XTM credentials
    Then user should able to login to XTM

    Given user on All Project page
    When search the project
    And select the first result
    Then wait until cost analysis complete

    Given user navigates to stage details page
    Then selects view costs and approves if required


    Given the user navigates to previous XTM page
    Given user on All Project page
    When search the project
    Then finish the project

    Given user navigates to stage details page
    And user checks the status











