Feature: Bank Manager

  Scenario: Add new customer
    Given the banking application has been started
    And I am on the "Banking Home" page
    When I click on the "Banking Manager Login" button
    Then I should see the "Banking Manager Home" page
    When I go to the "Customers" page
    Then I should see the "Customers" page
    When I go to the "Add Customer" page
    Then I should see the "Add Customer" page
    And the following fields are shown:
      | First Name |
      | Last Name  |
      | Post Code  |
    And the "Submit" field is available
    When I populate current page with the following data:
      | Field      | Value |
      | First Name | Test  |
      | Last Name  | User  |
      | Post Code  | WWW99 |
    Then I should see the page contains the following data:
      | Field      | Value |
      | First Name | Test  |
      | Last Name  | User  |
      | Post Code  | WWW99 |
    When I click on the "Submit" button
    And accept the alert message
    Then I should see the "Add Customer" page
    When I go to the "Customers" page
    Then I should see the "Customers" page
    And the following labels are shown:
      | Test  |
      | User  |
      | WWW99 |
