Feature: Employee Application
  As an application user
  I want to use the application to add and remove employees
  In order to maintain employee records

  @Ignore
  Scenario: Visiting in the employee page
    When I visit the employees page
    Then the browser title should be 'Employee Application 1.0'

  @ignore
  Scenario: Adding an employee
    Given I am on the employee page
    When I put in the following information
      | firstName | secondName | jobTitle | deskId |
      | John      | Smith      | HR       | 2      |
    And I press 'Add Employee'
    Then I should redirected to the employees page
    And the browser title should be 'Employee'