Feature: Update User Activation Status

  Scenario: Admin updates activation status for a specific user
    Given the admin is on the Update Activation page
    When the admin enters the "active" status for "username"
    Then the activation status of username will be updated successfully
