Feature: Approve new instructor registrations

  Scenario Outline: Admin approves pending instructor registrations
    Given admin is on the instructor approval page
    When an instructor with the following details is pending for approval:
      | Username   | FirstName  | LastName   | Email                | PhoneNumber | Status     |
      | <Username> | <FirstName>| <LastName> | <Email>              | <PhoneNumber>| <Status>  |
    And admin sets the status to "Approved"
    Then the system should update the instructor status to "Approved"
    And the message "<Message>" should be displayed

    Examples:
      | Username    | FirstName | LastName  | Email                 | PhoneNumber  | Status      | Message                         |
      | mohammad12  | Mohammad  | Murad     | mohammad22@gmail.com  | 0595478769   | Pending     | Instructor approved successfully |
      | ahmad21     | Ahmad     | Nassar    | ahmad21@gmail.com     | 0569876543   | Pending     | Instructor approved successfully |
      | instructor1 | Ali       | Hasan     | ali.hasan@gmail.com   | 0551231234   | Approved    | Instructor is already approved  |
