Feature: Job Service
  As a developer
  I want to interact with the Job service
  In order to get information about the jobs registered in the job service

  Scenario: Calling the get job title counts operation when there are no employees in the system
    Given there is an job service
    And there is an employee service
    And there are no employees
    When the get job title counts operation is called
    Then there will be no job titles returned

  Scenario: Calling the get job title counts operation when there is 1 employee in the system
    Given there is an job service
    And there is an employee service
    And an employee with the following details
      | firstName | secondName | jobId | jobTitle | deskId |
      | Alan      | Foster     | 3     | Engineer | 1      |
    When the get job title counts operation is called
    Then the job title counts returned will be
      | jobTitle | count |
      | Engineer | 1     |

  Scenario: Calling the get job title counts operation when there is 2 employees in the system with the same job
    Given there is an job service
    And there is an employee service
    And the employee service contains the following employees
      | firstName | secondName | jobId | jobTitle | deskId |
      | Alan      | Foster     | 3     | Engineer | 1      |
      | Ray       | McPherson  | 3     | Engineer | 2      |
    When the get job title counts operation is called
    Then the job title counts returned will be
      | jobTitle | count |
      | Engineer | 2     |

  Scenario: Calling the get job title counts operation when there is 2 different kinds of job
    Given there is an job service
    And there is an employee service
    And the employee service contains the following employees
      | firstName | secondName | jobId | jobTitle | deskId |
      | Alan      | Foster     | 3     | Engineer | 1      |
      | Ray       | McPherson  | 3     | Engineer | 3      |
      | Helen     | Whaley     | 1     | HR       | 3      |
    When the get job title counts operation is called
    Then the job title counts returned will be
      | jobTitle | count |
      | Engineer | 2     |
      | HR       | 1     |

  Scenario: Calling the get job title counts operation when there is one employee for each type of job
    Given there is an job service
    And there is an employee service
    And the employee service contains the following employees
      | firstName | secondName | jobId | jobTitle           | deskId |
      | Alan      | Foster     | 4     | Senior Engineer    | 1      |
      | Ray       | McPherson  | 5     | Principal Engineer | 2      |
      | Ted       | Whaley     | 1     | HR                 | 3      |
      | Helen     | Bevilacqua | 6     | Tester             | 4      |
      | Darren    | Tawil      | 3     | Engineer           | 5      |
      | Allan     | Motyka     | 2     | Operations         | 6      |
    When the get job title counts operation is called
    Then the job title counts returned will be
      | jobTitle           | count |
      | HR                 | 1     |
      | Operations         | 1     |
      | Engineer           | 1     |
      | Senior Engineer    | 1     |
      | Principal Engineer | 1     |
      | Tester             | 1     |

  Scenario: Calling the get jobs operation
    Given there is an job service
    And there is an employee service
    When the get jobs operation is called
    Then the job returned jobs will be
      | JobId | jobTitle           |
      | 1     | HR                 |
      | 2     | Operations         |
      | 3     | Engineer           |
      | 4     | Senior Engineer    |
      | 5     | Principal Engineer |
      | 6     | Tester             |