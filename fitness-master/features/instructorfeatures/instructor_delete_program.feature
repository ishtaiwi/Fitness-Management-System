Feature:delete a program
  Scenario Outline: delete a program
    Given instructor is in program delete page
    When I enter a  program number "<programID>"
    Then a confirm message should be printed "<message>" on screen
    Examples:
      | programID | message                     |
      | 1001      |program deleted sucessfully|
      | 1000      |program deleted sucessfully|
      | 1009      |program does not exist     |

