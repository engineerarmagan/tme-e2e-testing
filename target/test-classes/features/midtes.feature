    Feature: midtest
@mid
Scenario:Midtest

  Given the user is on the login page
  When the user enters the credentials
  Then the user should be able to login

  When user navigates to URL
  Then user checks the status
 # Then user should ask for Post Edit

