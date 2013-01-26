Feature: Employee Data Access
  As a developer
  I want to interact with the Employee service
  In order to maintain employee records

  Scenario: Adding an employee and validating the data is saved correctly
    Given there is an employee service
    When I add the following Employee
      | firstName | secondName | jobTitle      | deskId |
      | Alan      | Foster     | Being Awesome | 1      |
    Then there will be '1' employee in the employee service
    And the employee with id '1' will have the following details
      | firstName | secondName | jobTitle      | deskId |
      | Alan      | Foster     | Being Awesome | 1      |