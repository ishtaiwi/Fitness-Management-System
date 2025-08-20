Feature:Discussion Forums
  Scenario Outline: Participating in discussion forums
    Given I am an instructor with "<User-ID>"
    And I am in the discussion forums menu
    And I open the forum for "<program-title>"
    And "<Program-ID>"
    When I write a comment "<comment>"
    And I post the comment
    Then the comment should be "<confirmation-message>"

    Examples:
      |User-ID|Program-ID   | program-title       | comment                      | confirmation-message         |
      |1001    | 1001        | Beginner Yoga       | Great progress everyone!    | Comment posted successfully  |
      |1001    | 1002        | Advanced CrossFit   |                             | Comment content is required  |
      |1001    | 1003        | Weight Loss Bootcamp| Remember to log your meals! | Comment posted successfully  |
      |1001    | 1003        | Weight Loss Bootcamp| Greate progress             | Comment posted successfully  |