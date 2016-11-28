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
    When I go to the "Customers" page
    Then I should see the "Customers" page