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
    And the "First Name" field is available
    And the "Last Name" field is available
    And the "Post Code" field is available
    And the "Submit" field is available
    When I enter "Test" text into the "First Name" field
    And enter "User" text into the "Last Name" field
    And enter "WWW99" text into the "Post Code" field
    Then I should see the "First Name" field contains the "Test" text
    And the "Last Name" field contains the "User" text
    And the "Post Code" field contains the "WWW99" text
    When I go to the "Customers" page
    Then I should see the "Customers" page
