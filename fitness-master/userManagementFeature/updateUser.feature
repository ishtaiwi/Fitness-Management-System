Feature: update password

  Scenario: admin update password for specific user
    Given the admin is in update password page
    When admin enter "new_password" and "username"
    Then password will change