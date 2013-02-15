Feature: Employee Application
  As an application user
  I want to use the application to add and remove employees
  In order to maintain employee records

  Scenario: Visiting the home page
    When I visit the home page
    Then the browser title should be 'Employee Management System 1.0'

  Scenario: Visiting the add employee page
    Given I am on the home page
    When I click the 'addEmployee' navigation link
    Then the content title will be 'Add Employee'

  Scenario: Visiting the search page with no employees in the system
    Given I am on the home page
    When I click the 'searchEmployee' navigation link
    #Then the alert will say 'Sorry. There are no employees which matched your requirement'

  Scenario: Visiting the reports page
    Given I am on the home page
    When I click the 'reports' navigation link
    Then the browser title should be 'Employee Management System 1.0'

  @Ignore
  Scenario: Adding an employee
    Given I am on the employee page
    When I put in the following information
      | firstName | secondName | jobTitle | deskId |
      | John      | Smith      | HR       | 2      |
    And I press 'Add Employee'
    Then I should redirected to the employees page
    And the browser title should be 'Employee'