Feature: midtest
@mid
Scenario:Midtest

  Given the user is on the login page
  When the user enters the credentials
  Then the user should be able to login

  When user navigates to URL
  Then user checks the status
 # Then user should ask for Post Edit

@midtest
  Scenario: Midtest cost approval
  Given the user is on the TMNA login page
  When the user enters the credentials
  Then the user should be able to login

  When user navigates to URL
  Then approves the translation cost for specific CPID
  @rename
  Scenario: change file name
    Given user changes file name


