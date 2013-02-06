Feature: Enterprise Application Integration
  As legacy system
  I want to interact with the Employee batch drop folder
  In order to maintain employee records and receive a file response

  Scenario: Placing legacy XML into the 'drop folder' and interacting with the employee webservice with a success
    Given there is an employee webservice
    And the drop input folder is empty
    And the drop output folder is empty
    When I drop the following XML payload into the drop folder
    """
    <?xml version="1.0" encoding="UTF-8"?>
    <foo:people xmlns:foo="http://www.foobarbaz.com/">
        <foo:persons>
            <foo:name content="Helen_Bevilacqua"/>
            <foo:job id="6" title="Tester"/>
            <foo:desk>1</foo:desk>
        </foo:persons>
        <foo:persons>
            <foo:name content="Darren_Tawil"/>
            <foo:job id="3" title="Engineer"/>
            <foo:desk>2</foo:desk>
        </foo:persons>
        <foo:persons>
            <foo:name content="Allan_Motyka"/>
            <foo:job id="2" title="Operations"/>
            <foo:desk>3</foo:desk>
        </foo:persons>
    </foo:people>
    """
    Then the employee webservice will now have the following employee details
      | id | firstName | secondName | jobId | jobTitle   | deskId |
      | 1  | Helen     | Bevilacqua | 6     | Tester     | 1      |
      | 2  | Darren    | Tawil      | 3     | Engineer   | 2      |
      | 3  | Allan     | Motyka     | 2     | Operations | 3      |
    And the output folder will contain an xml file
    And the xml result file will contain a successful response


  @Ignore
  Scenario: Placing blank legacy XML into the 'drop folder' and interacting with the employee webservice with a success
    Given there is an employee webservice
    And the drop input folder is empty
    And the drop output folder is empty
    When I drop the following XML payload into the drop folder
    """
    <?xml version="1.0" encoding="UTF-8"?>
    <foo:people xmlns:foo="http://www.foobarbaz.com/" />
    """
    Then the employee webservice will no employee details
    And the result will contain be a success
    And the failed employee list will be empty

  @Ignore
  Scenario: Ensuring the batch completes ignoring single failures
    Given there is an employee webservice
    And the drop folder is empty
    When I drop the following XML payload into the drop folder
    """
    <?xml version="1.0" encoding="UTF-8"?>
    <foo:people xmlns:foo="http://www.foobarbaz.com/">
        <foo:persons>
            <foo:name content="Helen_Bevilacqua"/>
            <foo:job id="6" title="Tester"/>
            <foo:desk>1</foo:desk>
        </foo:persons>
        <foo:persons>
            <foo:name content="Allan_Motyka"/>
            <foo:job id="-1" title="Wrong Job Title"/>
            <foo:desk>3</foo:desk>
        </foo:persons>
        <foo:persons>
            <foo:name content="Allan_Motyka"/>
            <foo:job id="2" title="Operations"/>
            <foo:desk>3</foo:desk>
        </foo:persons>
    </foo:people>
    """
    Then the employee webservice will now have the following employee details
      | id | firstName | secondName | jobId | jobTitle   | deskId |
      | 1  | Helen     | Bevilacqua | 6     | Tester     | 1      |
      | 3  | Darren    | Tawil      | 3     | Engineer   | 2      |
    And the xml result file will contain an unsuccessful response
    And the xml result will contain one failed employee