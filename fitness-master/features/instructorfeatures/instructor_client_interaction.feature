Feature: Messaging client
  Scenario Outline: Sending a message to a client with a valid or invalid ID
    Given I am in the messaging menu

    When  I am an instructor With ID "<instructor-id>"
    And I choose a client to message with "<client-id>"
    And I write a message "<message-content>"
     And I hit enter
    Then the message should be "<confirmation-message>"

    Examples:
      |instructor-id| client-id | message-content         |  confirmation-message             |
      |  1001       | 1001     | Hello, how are you?     |  Message sent successfully        |
      |  1003       | 00000     | Reminder: Pay the dues  |  Invalid client ID: Message failed|
      |  1004       |           | Hello there!            |  Client ID is required            |
      |  1001       | 1002     | Don't forget our session|  Message sent successfully        |
      |  1001       | 1004     |                         |  Message content is required      |

