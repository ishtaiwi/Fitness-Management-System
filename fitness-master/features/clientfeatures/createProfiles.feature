Feature: User Registration
  As an application user,
  I want to create a user with detailed information,
  So that I can store and retrieve user data properly.

  Scenario Outline: User registration with various inputs
    Given the user navigates to the registration page
    And he fills in 'age' with "<Age>" for create
    And he fills in 'fitnessGoals' with "<FitnessGoals>" for create
    And he fills in 'dietaryPreferences' with "<DietaryPreferences>" for create
    And he submits the create form
    Then he should see "<Message>" for create

    Examples:
      | Age   | FitnessGoals      | DietaryPreferences   | Message                          |
      | 30    | Lose weight       | Vegan                | Profile was created successfully |
      | 25    | Build muscle      | Vegetarian           | Profile was created successfully |
      | 18    | Increase stamina  | Keto                 | Profile was created successfully |
      | 60    | Maintain health   | Paleo                | Profile was created successfully |
      | 0     | None              | None                 | Invalid age                      |
      | -5    | Build muscle      | Vegetarian           | Invalid age                      |
      | 30    |                   | Vegan                | Fitness goals cannot be empty    |
      | 30    | Lose weight       |                      | Dietary preferences cannot be empty |
      | 150   | Lose weight       | Vegan                | Invalid age                      |
      | 25    | Gain muscle       | RandomPreference     | Profile was created successfully |
      | 35    | Unknown goal      | Vegetarian           | Profile was created successfully |
      | 29    | Build muscle      | Keto, Paleo          | Profile was created successfully |
      | 25    | Maintain health   | Gluten-free          | Profile was created successfully |
      | 20    | Build muscle      |                     | Dietary preferences cannot be empty |
