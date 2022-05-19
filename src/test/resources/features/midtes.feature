    Feature: midtest
@mid
Scenario:Midtest

  Given the user is on the TMNA login page
  And the user enters the credentials
  When the user navigates to "HISTORY"


  And filters status "WAITING"
  Given user filters publication type "NCF"
  Then filters
  When selects nth from the list

  And user checks the status of TMNA

  Then user should see PUBLISH-PROCESSED


  #Then selects view costs and approves if required for TMNA
