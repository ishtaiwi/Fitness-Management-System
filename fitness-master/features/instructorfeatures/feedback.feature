Feature: sending feedback to clients
  Scenario Outline:
    Given iam an instructor in send feed back page
    When i insert user id with "<user-Id>"
    And i insert program id with "<program-Id>"
    And i insert report path with "<report>"
    And a message text with "<message-text>"
    Then the feedback should be done with message "<message>"
    Examples:
      | user-Id   |program-Id|  report      | message-text        |    message                    |
      | 1001      | 1007     |report1.pdf   | keep the good work! |feedback sent successfully     |
      | 1008      | 1008     | report2.pdf  | keep the good work! |incorrect user id              |
      | 1001      | 1008     |              | keep the good work! |report file cannot be empty    |
      |           | 1007     | report1.pdf  | keep the good work! |user id cannot be empty        |
