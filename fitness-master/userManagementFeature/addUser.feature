Feature: addUser

  Scenario Outline: add-user with various inputs
    When admin is in add-user page
    And he fills in 'ID' with "<ID>" for add
    And he fills in 'username' with "<Username>" for add
    And he fills in 'firstName' with "<FirstName>" for add
    And he fills in 'lastName' with "<LastName>" for add
    And he fills in 'phoneNumber' with "<PhoneNumber>" for add
    And he fills in 'password' with "<Password>" for add
    And he fills in 'email' with "<Email>" for add
    And he fills in 'userType' with "<userType>" for add
    And he submits the add form
    Then he should see "<Message>" for add

    Examples:
      |ID    |Username   | FirstName | LastName    | PhoneNumber | Password     | Email                | userType         | Message                          |
      | 1001 |osama12    | osama     | ishtaiwi    | 0568446291  | 1234**Oo     | osama@gmail.com      | client           | User was added successfully      |
      | 1002 |mohammad12 | Mohammad  | Murad       | 0595478769  | 1234**Fa     | mohammad22@gmail.com | instructor       | User was added successfully      |
      | 1003 |saif23     | saif      | shayed      | 059500002   | 1234**Aa     | saif@gmail.com       | client           | Invalid phone number             |
      | 1004 |osama1     | osama     | ishtaiwi    | 059111111a  | 1234**Aa     | osama@gmail.com      | client           | Invalid phone number             |
      | 1005 |osama13    | osama     | ishtaiwi    | 0568446291  | weakPassword | osama@gmail.com      | client           | Invalid password                 |
      | 1006 |osama14    | osama     | ishtaiwi    | 0568446291  | 1234**Aa     | osama22ail.com       | client           | Invalid email address            |
      | 1007 |osama1     | osama     | ishtaiwi    | 0568446291  | 1234**Aa     | osama@gmail.com      | client           | User was added successfully      |
      | 1008 |           | osama     | ishtaiwi    | 0568446291  | 1234**Aa     | osama@gmail.com      | client           | Username can't be empty          |
      | 1009 |osama1     |           | ishtaiwi    | 0568446291  | 1234**Aa     | osama@gmail.com      | client           | First name can't be empty        |
      | 10010|osama1     | osama     |             | 0568446291  | 1234**Aa     | osama@gmail.com      | client           | Last name can't be empty         |
      | 10011|osama1     | osama     | ishtaiwi    |             | 1234**Aa     | osama@gmail.com      | client           | Phone number can't be empty      |
      | 10012|osama1     | osama     | ishtaiwi    | 0568446291  |              | osama@gmail.com      | client           | Password can't be empty          |
      | 10013|osama1     | osama     | ishtaiwi    | 0568446291  | 1234**Aa     |                      | client           | Email address can't be empty     |
      | 10014|osama1     | osama     | ishtaiwi    | 0568446291  | 1234**Aa     | osama@gmail.com      |                  | User type can't be empty         |
      | 10015|osama1     | osama     | ishtaiwi    | 0568446291  | 1234**Aa     | osama@gmail.com      | client           | Username is already taken      |
      | 10016|osama15    | osama     | ishtaiwi    | 0568446291  | 1234**Aa     | osama@gmail.com      | client           | User was added successfully      |
      | 10017|osama1     | osama     | ishtaiwi    | 0568446291  | 1234**Aa     | osama@gmail.com      | none             | Invalid user type                |









