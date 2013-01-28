Feature: Employee Data Access
  As a developer
  I want to interact with the Employee service
  In order to maintain employee records

  Scenario: Creating a new employee and validating the data is saved correctly
    Given there is an employee service
    When the create employee service is called with the following data
      | firstName | secondName | jobTitle      | deskId |
      | Alan      | Foster     | Engineer | 1      |
    Then there will be '1' employee in the employee service
    And the employee with id '1' will have the following details
      | id | firstName | secondName | jobTitle      | deskId |
      | 1  | Alan      | Foster     | Engineer | 1      |

  Scenario: updating an existing employee and validating the data is saved correctly
    Given there is an employee service
    And an employee with the following details
      | id | firstName | secondName | jobTitle      | deskId |
      | 1  | alan      | foster | Engineer  | 1      |
    When the update employee service is called with the following data
      | id | firstName | secondName | jobTitle      | deskId |
      | 1  | Alan      | Foster     | Senior Engineer | 2      |
    Then the employee with id '1' will have the following details
      | id | firstName | secondName | jobTitle      | deskId |
      | 1  | Alan      | Foster     | Senior Engineer | 2      |

  Scenario: Removing an employee by id
    Given there is an employee service
    And an employee with the following details
      | id | firstName | secondName | jobTitle      | deskId |
      | 1  | John      | Smyth | HR  | 1      |
    Then there will be '1' employee in the employee service
    When the delete employee service is called with the employee id '1'
    Then there will be '0' employee in the employee service