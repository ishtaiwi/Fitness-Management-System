Feature: Progress
  Scenario Outline: Updating client's progress
    Given I am an instructor on the client progress page
    When I insert client Id with "<client-ID>"
    And I select a program that the client is enrolled in "<program-id>"
    And I put the client completion rate "<progress>"
    And I put the client attendance rate "<attend-rate>"
    Then the transaction should be set with confirm message "<message>"

    Examples:
      | client-ID | program-id | progress | attend-rate | message                               |
      | 1001      | 1007       | 0.3      | 0.5         | Progress updated successfully         |
      | 1001      | 000        | 0.4      | 0.2         | program not found               |
      | 0000      | 1001       | 0.2      | 0.4         | Client ID is incorrect                |
      | 1001      | 1007       | 0.4      |  -1           |Attendance rate is incorrect           |
      | 1001      | 1007       | 0.4      | 1.3         | Attendance rate is incorrect          |
      | 1001      | 1007       | 1.4      | 0.9         | Progress rate is incorrect            |


