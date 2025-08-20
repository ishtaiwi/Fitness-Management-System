Feature: Scheduling group sessions for a program
  Scenario Outline: Scheduling group sessions
    Given I am an instructor in add schedule
    When I set the schedule num as "<schedule-num>"
    And create a new group session for "<program-num>"
    And I set the session type as "<session-type>"
    And I set the session date as "<session-date>"
    And I set the session time as "<session-time>"
    And I set the maximum participants to "<max-participants>"
    And I set the location as "<location>"
    Then the session should be processed
    And participants will be notified.
    And message with "<message>" is printed on

    Examples:
      | schedule-num | program-num        | session-type | session-date | session-time | max-participants | location             | message                       |
      | 10001        | 1007               | Online       | 2024-12-10   | 10:00 AM     | 20               | Zoom Meeting Link    | Session scheduled successfully |
      | 10002        | 1007               | In-person    | 2024-12-15   | 6:00 PM      | 15               | Gym Room A           | Session scheduled successfully |
      | 10003        | 1007               | Online       | 2024-12-12   | 7:30 PM      | 25               | Google Meet Link     | Session scheduled successfully |
      | 10004        | 1007               | In-person    | 2024-12-18   | 9:00 AM      | 10               | Community Hall B     | Session scheduled successfully |
      | 10005        | 1008               | Online       |              | 7:30 PM      | 25               | Google Meet Link     | Missing session date field     |
      | 10006        | 1007               | In-person    | 2024-12-15   | 6:00 PM      |                  | Gym Room A           | Missing max-participants field |
      | 10007        | 1008               | In-person    | 2024-12-18   |              | 10               | Community Hall B     | Missing session time field     |
      | 10008        | 1008               | Online       | 2024-12-10   | 10:00 AM     | 20               |                      | Missing location field         |
