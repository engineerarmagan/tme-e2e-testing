Feature: TMNA Login

@tmna @smoke
Scenario: Login
Given the user is on the TMNA login page
When the user enters the credentials
Then the user should be able to login