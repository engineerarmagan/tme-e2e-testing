Feature: TMNA Event History Page

  @tmna @smoke
Scenario: Event History columns and filtering

Given the user is on the TMNA login page
When the user enters the credentials
And the user navigates to "System" "Event History"
Then user should see following columns
| CPID         |
| User Name    |
| Date         |
| Alt-Pub-ID   |
| Version      |
| Language     |
| Event Type   |
| Event Number |
| Event Title  |
| Stage/Area   |
| Details      |

And filters the event type "trigger"
And filters
Then user should see only the filtered event type