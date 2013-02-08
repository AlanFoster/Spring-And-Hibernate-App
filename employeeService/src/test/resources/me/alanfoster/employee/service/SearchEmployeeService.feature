Feature: Searching
  As a user of the employee service
  I want to query the employee search service
  In order to get a list of employees meeting my criteria

  Background: The employee service already contains employees for searching
    Given there is an employee service
    And the employee service contains the following employees
      | id | firstName | secondName  | jobId | jobTitle           | deskId |
      | 1  | Alan      | Foster      | 4     | Senior Engineer    | 1      |
      | 2  | Ray       | McPherson   | 5     | Principal Engineer | 2      |
      | 3  | Ted       | Whaley      | 1     | HR                 | 3      |
      | 4  | Helen     | Bevilacqua  | 6     | Tester             | 4      |
      | 5  | Darren    | Tawil       | 3     | Engineer           | 5      |
      | 6  | Allan     | Motyka      | 2     | Operations         | 6      |
      | 7  | Roy       | McHatterson | 5     | Principal Engineer | 7      |

  Scenario: Search employee service with exact first name searching
    When the search employee service is called with the following data
      | firstName |
      | Alan      |
    Then search employee service will return the following employees
      | id | firstName | secondName | jobId | jobTitle        | deskId |
      | 1  | Alan      | Foster     | 4     | Senior Engineer | 1      |

  Scenario: Search Employee service with fuzzy first name searching
    When the search employee service is called with the following data
      | firstName |
      | Al%       |
    Then search employee service will return the following employees
      | id | firstName | secondName | jobId | jobTitle        | deskId |
      | 1  | Alan      | Foster     | 4     | Senior Engineer | 1      |
      | 6  | Allan     | Motyka     | 2     | Operations      | 6      |

  Scenario: Search Employee service with fuzzy first name searching and second name to refine search
    When the search employee service is called with the following data
      | firstName | secondName |
      | Al%       | M%         |
    Then search employee service will return the following employees
      | id | firstName | secondName | jobId | jobTitle   | deskId |
      | 6  | Allan     | Motyka     | 2     | Operations | 6      |

  Scenario: Case insensitive matching
    When the search employee service is called with the following data
      | firstName | secondName |
      | r%Y       | mC%        |
    Then search employee service will return the following employees
      | id | firstName | secondName  | jobId | jobTitle           | deskId |
      | 2  | Ray       | McPherson   | 5     | Principal Engineer | 2      |
      | 7  | Roy       | McHatterson | 5     | Principal Engineer | 7      |
  @Ignore
  Scenario: Searching for a specific job title
    When the search employee service is called with the following data
      | jobTitle   |
      | %engineer% |
    Then search employee service will return the following employees
      | id | firstName | secondName  | jobId | jobTitle           | deskId |
      | 1  | Alan      | Foster      | 4     | Senior Engineer    | 1      |
      | 2  | Ray       | McPherson   | 5     | Principal Engineer | 2      |
      | 5  | Darren    | Tawil       | 3     | Engineer           | 5      |
      | 7  | Roy       | McHatterson | 5     | Principal Engineer | 2      |

  Scenario: Searching for an employee id
    When the search employee service is called with the following data
      | id |
      | 3  |
    Then search employee service will return the following employees
      | id | firstName | secondName | jobId | jobTitle | deskId |
      | 3  | Ted       | Whaley     | 1     | HR       | 3      |

  Scenario: Searching for a desk id between two values
    When the search employee service is called with the following data
      | minDeskId | maxDeskId |
      | 5         | 15        |
    Then search employee service will return the following employees
      | id | firstName | secondName | jobId | jobTitle | deskId |
      | 5  | Darren    | Tawil       | 3     | Engineer           | 5      |
      | 6  | Allan     | Motyka      | 2     | Operations         | 6      |
      | 7  | Roy       | McHatterson | 5     | Principal Engineer | 7      |

  Scenario: Search which will return no results
    When the search employee service is called with the following data
      | minDeskId | maxDeskId |
      | 5         | 0       |
    Then search employee service will return no employees
