Feature: Update program
  Scenario Outline: Updating a valid program
    Given instructor is in program Update page
    When I put  programnum "<programnum>"
    And I update program-title to "<program-title>"
    And I update duration to "<duration>"
    And I update difficulty to "<difficulty>"
    And I update goals to "<goals>"
    And I update media to "<media>"
    And I update price to "<price>"
    Then the transaction should be executed
    And message with "<message>" is put

    Examples:
      | programnum   | program-title        | duration | difficulty | goals                          | media                  | price  | message                       |
      | 1003 | Weight Loss Bootcamp |          | Medium     | Lose weight, tone muscles       | bootcamp_weightloss.mp4| 90     | Missing duration field        |
      | 1004 |                      | 10 weeks | Easy       | Improve mobility, strength      | senior_fitness.mp4    | 70     | Missing program-title field   |
      | 1005 | Senior Fitness       | 10 weeks |            | Improve mobility, strength      | senior_fitness.mp4    | 70     | Missing difficulty field      |
      | 1006 | Senior Fitness       | 10 weeks | Easy       |                                 | senior_fitness.mp4    | 70     | Missing goals field           |
      | 1007 | Senior Fitness       | 10 weeks | Easy       | Improve mobility, strength      |                       | 70     | Program updated successfully |
      | 1006 | Senior Fitness       | 10 weeks | Easy       | Improve mobility, strength      | senior_fitness.mp4    |        | Program updated successfully |
      | 10010| Senior Fitness       | 10 weeks | Easy       | Improve mobility, strength      |                       | 70     | Program not found          |
      | 10011| Senior Fitness       | 10 weeks | Easy       | Improve mobility, strength      | senior_fitness.mp4    |        | Program not found |
