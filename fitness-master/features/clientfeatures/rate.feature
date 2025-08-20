Feature: Client Review with Detailed Information
  As an application user,
  I want to provide detailed reviews with a unique client ID and suggestions,
  So that I can properly track, retrieve, and act upon user reviews.

  Scenario Outline: Client review with various inputs
    Given the client navigates to the review page
    When the client fills in 'clientID' with "<ClientID>" for review
    And the client fills in 'clientUserName' with "<ClientUserName>" for review
    And the client fills in 'programName' with "<ProgramName>" for review
    And the client fills in 'rating' with "<Rating>" for review
    And the client fills in 'feedbackText' with "<FeedbackText>" for review
    And the client fills in 'suggestion' with "<Suggestion>" for review
    And the client submits the review form
    Then the client should see "<Message>" for review

    Examples:
      | ClientID | ClientUserName | ProgramName      | Rating      | FeedbackText        | Suggestion               | Message                              |
      | 5001     | mohammad12     | Weight Loss      | *****       | Excellent program   | Add more meal plans      | Client review confirmed successfully |
      | 5002     | ahmad1999      | Yoga Basics      | **          | Needs improvement   | More beginner exercises  | Client review confirmed successfully |
      | 5003     | osama          | Cardio Blast     | ****        | Loved it            | Extend session duration  | Client review confirmed successfully |
