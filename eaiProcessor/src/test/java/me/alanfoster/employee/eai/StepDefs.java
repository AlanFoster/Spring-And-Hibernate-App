package me.alanfoster.employee.eai;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import me.alanfoster.employee.service.FlatEmployee;
import me.alanfoster.employee.webservice.IEmployeeWebservice;
import me.alanfoster.services.employee.models.IEmployee;
import me.alanfoster.services.employee.models.impl.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.unitils.reflectionassert.ReflectionComparatorMode;
import org.unitils.thirdparty.org.apache.commons.io.FileUtils;

import javax.sql.DataSource;
import java.io.File;
import java.nio.file.Files;
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

    @Autowired
    private DataSource dataSource;

    @Value("${dropBox.input}")
    private String dropBoxInput;

    @Value("${dropBox.output}")
    private String dropBoxOutput;

    private String inputFileName;
    private File outputFile;

    @Autowired
    private IBatchProcessor batchProcessor;

    @Given("^there is an employee webservice$")
    public void there_is_an_employee_webservice() throws Throwable {
        // Autowired
        assertNotNull("Employee web service should not be null", employeeWebservice);
    }

    @Given("^the drop folder is empty$")
    public void the_drop_folder_is_empty() throws Exception {
        FileUtils.deleteDirectory(new File(dropBoxInput));
    }

    @When("^I drop the following XML payload into the drop folder$")
    public void I_drop_the_following_XML_payload_into_the_drop_folder(String xml) throws Throwable {
        // Create a temp file and keep track of its name, then place it into the drop box
        File inputFile = File.createTempFile("input", ".xml");
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
        assertEquals("The output directory should contain one file", files.length, 1);

        outputFile = files[0];
        assertEquals("The file should have the same input file name", outputFile.getName(), inputFileName);
    }

    @Then("^the xml result file will contain a successful response$")
    public void the_xml_result_file_will_contain_a_successful_response() throws Throwable {
    }

    @Then("^the result will contain be a success$")
    public void the_result_will_contain_be_a_success() throws Throwable {
    }

    @Then("^the failed employee list will be empty$")
    public void the_failed_employee_list_will_be_empty() throws Throwable {
    }

    @Then("^the xml result file will contain an unsuccessful response$")
    public void the_xml_result_file_will_contain_an_unsuccessful_response() throws Throwable {
    }

    @Then("^the xml result will contain one failed employee$")
    public void the_xml_result_will_contain_one_failed_employee() throws Throwable {
    }
}
