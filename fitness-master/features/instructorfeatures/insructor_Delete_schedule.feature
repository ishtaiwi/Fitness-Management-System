Feature: Delete Schedule
  Scenario Outline: delete a specific Schedule
    Given instructor is in Schedule Delete page
    When  I enter a "<Schedule-num>"
    Then "<outcome>" should be excuted

    Examples:
    |Schedule-num|outcome|
    |10001       |Schedule Deleted Successfully|
    |1000180     |Schedule Not Found           |