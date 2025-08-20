Feature:enroll in program
  Scenario Outline: enroll in a program
    Given iam a client in enroll in programs page with ID "<client-id>"
    When i choose a program to enroll in with "<Program-ID>"
    Then the instruction will be made and "<message>" will be printed
    Examples:
     |client-id    | Program-ID | message              |
     | 1001        | 1007       |enrolled successfully |
     | 1002        |10080       |program not found     |
     | 1001        | 1007       |enrolled successfully |
