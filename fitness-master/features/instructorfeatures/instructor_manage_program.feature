Feature: Create program
  Scenario Outline: Creating a program with price
    Given instructor is in program Add page
    When  I insert ID with "<ID>"
    And I insert program-title with "<program-title>"
    And I insert duration as "<duration>"
    And I insert difficulty as "<difficulty>"
    And I insert goals as "<goals>"
    And I insert media as "<media>"
    And I insert price as "<price>"
    Then the add should be executed
    And message with "<message>" is displayed

    Examples:
     | ID| program-title          | duration | difficulty | goals                          | media                  | price  | message                       |
      |1001 | Beginner Yoga       | 4        | Easy       | Improve flexibility, relaxation | yoga_intro.mp4        | 50    | Program created successfully |
      |1002| Advanced CrossFit    | 8        | Hard       | Build endurance, strength      | crossfit_advanced.mp4 | 120   | Program created successfully |
      |1003| Weight Loss Bootcamp |          | Medium     | Lose weight, tone muscles      | bootcamp_weightloss.mp4| 90   | Missing duration field        |
      |1004|                      | 10       | Easy       | Improve mobility, strength     | senior_fitness.mp4    | 70    | Missing program-title field   |
      |1005| Senior Fitness       | 10       |            | Improve mobility, strength     | senior_fitness.mp4    | 70    | Missing difficulty field      |
      |1006| Senior Fitness       | 10       | Easy       |                                | senior_fitness.mp4    | 70    | Missing goals field|
      |1007| Senior Fitness       | 10       | Easy       | Improve mobility, strength     |                        | 70    | Program created successfully          |
      |1008| Senior Fitness       | 10       | Easy       | Improve mobility, strength     | senior_fitness.mp4    |        |  Program created successfully|



