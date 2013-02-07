package me.alanfoster.employee.eai;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import me.alanfoster.employee.service.FlatEmployee;
import me.alanfoster.employee.webservice.IEmployeeWebservice;
import me.alanfoster.services.employee.models.IEmployee;
import me.alanfoster.services.employee.models.impl.Employee;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.unitils.reflectionassert.ReflectionComparatorMode;
import org.unitils.thirdparty.org.apache.commons.io.FileUtils;

import javax.sql.DataSource;
import java.io.File;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class StepDefs {
    /**
     * Basic SLF4J logger
     *
     * @See {@link http://www.slf4j.org/}
     */
    private static final Logger logger = LoggerFactory.getLogger(StepDefs.class);

    @Autowired
    private IEmployeeWebservice employeeWebservice;

    @Value("${dropBox.input}")
    private String dropBoxInput;

    @Value("${dropBox.output}")
    private String dropBoxOutput;

    private String inputFileName;
    private File outputFile;
    private Document outputDocument;

    @Autowired
    private IBatchProcessor batchProcessor;

    @Autowired
    private DataSource dataSource;

    @Before
    public void startUp() {
        System.out.println(dataSource);
    }

    @Given("^there is an employee webservice$")
    public void there_is_an_employee_webservice() throws Throwable {
        // Autowired
        assertNotNull("Employee web service should not be null", employeeWebservice);
    }

    @Given("^the drop input folder is empty$")
    public void the_drop_input_folder_is_empty() throws Exception {
        FileUtils.deleteDirectory(new File(dropBoxInput));
    }

    @Given("^the drop output folder is empty$")
    public void the_drop_output_folder_is_empty() throws Exception {
        FileUtils.deleteDirectory(new File(dropBoxOutput));
    }

    @When("^I drop the following XML payload into the drop folder$")
    public void I_drop_the_following_XML_payload_into_the_drop_folder(String xml) throws Throwable {
        // Create a temp file and keep track of its name, then place it into the drop box
        File inputFile = File.createTempFile("batchFile", ".xml");
        inputFileName = inputFile.getName();
        FileUtils.writeStringToFile(inputFile, xml, "UTF-8");
        FileUtils.copyFileToDirectory(inputFile, new File(dropBoxInput));

        // Kick off the EAI process manually
        batchProcessor.poll();
    }

    @Then("^the employee webservice will now have the following employee details$")
    public void the_employee_webservice_will_now_have_the_following_employee_details(List<FlatEmployee> flatEmployees) throws Throwable {
        List<IEmployee> expectedEmployees = FlatEmployee.getEmployeeDataTableAsIEmployee(flatEmployees);
        List<Employee> actualEmployees = employeeWebservice.getAllEmployees();
        // Assert the expected and returned lists are equal in any order
        assertReflectionEquals(expectedEmployees, actualEmployees, ReflectionComparatorMode.LENIENT_ORDER);
    }

    @Then("^the output folder will contain an xml file$")
    public void the_output_folder_will_contain_an_xml_file() throws Throwable {
        File[] files = new File(dropBoxOutput).listFiles();
        assertNotNull("The output directory should exist", files);
        assertEquals("The output directory should contain one file", files.length, 1);

        outputFile = files[0];
        assertEquals("The file should have the same input file name", outputFile.getName(), inputFileName);
        String fileContent = FileUtils.readFileToString(outputFile, "UTF-8");
        outputDocument = DocumentHelper.parseText(fileContent);
    }

    @Then("^the xml result file will be in a state of '(success|failed)'$")
    public void the_xml_result_file_will_be_in_a_state_of_success(String expectedState) throws Throwable {
      assertEquals("The state should be as expected", outputDocument.selectSingleNode("/BatchProcessorResponse/state").getText(), expectedState);
    }

    @Then("^the (successfulEmployees|failedEmployees) list will contain the following$")
    public void the_successfulEmployees_list_will_contain_the_following(String type, List<FlatEmployee> flatEmployees) throws Throwable {
        List<IEmployee> expectedEmployees = FlatEmployee.getEmployeeDataTableAsIEmployee(flatEmployees);
        List<Employee> actualEmployees = type.equals("successfulEmployees") ? getSuccessfullEmployees() : getFailedEmployees();

        // Assert the expected and returned lists are equal in the exact order
        assertReflectionEquals(expectedEmployees, actualEmployees);
    }

    @Then("^the successfulEmployees list will be empty$")
    public void the_failedEmployees_list_will_be_empty() throws Throwable {
        assertEquals("There should be no failed employees", getSuccessfullEmployees().size(), 0);
    }


    @Then("^the failedEmployees list will be empty$")
    public void the_successfulEmployees_list_will_be_empty() throws Throwable {
        assertEquals("There should be no failed employees", getFailedEmployees().size(), 0);
    }

    @Then("^the employee webservice will have no employee details$")
    public void the_employee_webservice_will_have_no_employee_details() throws Throwable {
        assertEquals("The employee web service should have no employees", employeeWebservice.getAllEmployees().size(), 0);
    }

    private List<Employee> getSuccessfullEmployees() {
        return getEmployees("successfulEmployees");
    }

    private List<Employee> getFailedEmployees() {
        return getEmployees("failedEmployees");
    }

    private List<Employee> getEmployees(String type) {
        List<Element> employeeElements = outputDocument.selectNodes("/BatchProcessorResponse/" + type + "/employee");
        List<Employee> employees = EmployeeElementHelper.getElementAsEmployee(employeeElements);
        return employees;
    }
}
