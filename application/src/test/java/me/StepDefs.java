package me;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static junit.framework.Assert.*;

import java.util.List;

/**
 * Basic Cucumber StepDefs
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class StepDefs {
    private static final Logger logger = LoggerFactory.getLogger(StepDefs.class);

    ///
    /// Website Application tests
    ///

    @Value("${core.baseUrl}")
    private String siteBase;

    @Autowired
   private WebDriver webDriver;

    @Autowired
    private SeleniumHelper seleniumHelper;

    @Before
    public void clearBrowser() {
        webDriver.get(siteBase);
    }

    @After
    public void saveScreenshot(Scenario scenario) {
        scenario.embed(seleniumHelper.takeScreenshot(), "image/png");
    }
    @When("^I visit the employees page$")
    public void I_visit_the_employees_page() throws Throwable {
        webDriver.get(siteBase + "/employees.html");
    }
    @Given("^I am on the employee page$")
    public void I_am_on_the_employee_page() throws Throwable {
        webDriver.get(siteBase + "/employees.html");
    }
    @Then("^the browser title should be '(.*)'$")
    public void the_browser_title_should_be_Employee(String expectedTitle) throws Throwable {
        assertEquals("The Browser title should be as expected", expectedTitle, webDriver.getTitle());
    }
    @Then("^I should redirected to the employees page$")
    public void I_should_redirected_to_the_employees_page() throws Throwable {
        assertEquals("The Browser title should be as expected", "Employee", webDriver.getTitle());
    }

    @When("^I put in the following information$")
    public void I_put_in_the_following_information(List<EmployeeFormDetail> employeeFormDetails) throws Throwable {
        EmployeeFormDetail employeeFormDetail = employeeFormDetails.get(0);

        // Input the information
        webDriver.findElement(By.name("firstName")).sendKeys(employeeFormDetail.getFirstName());
        webDriver.findElement(By.name("secondName")).sendKeys(employeeFormDetail.getSecondName());
        webDriver.findElement(By.name("jobTitle")).sendKeys(employeeFormDetail.getJobTitle());
        webDriver.findElement(By.name("deskId")).sendKeys(employeeFormDetail.getDeskId());
    }

    @When("^I press 'Add Employee'$")
    public void I_press_Add_Employee() throws Throwable {
        WebElement submitButton = webDriver.findElement(By.cssSelector("input[type='submit'][value = 'Add Employee']"));
        submitButton.submit();
    }

    /**
     * Represents the basic models for the form that users need to fill in when creating a new employee
     * IE, it represents the raw form models itself, and not an explicit Employee POJO - even though it may share similar properties
     * Also note, all of the data types are strings to represent the fact that all of the input fields will contain text
     */
    public class EmployeeFormDetail {
        private String firstName;
        private String secondName;
        private String jobTitle;
        private String deskId;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getSecondName() {
            return secondName;
        }

        public void setSecondName(String secondName) {
            this.secondName = secondName;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public String getDeskId() {
            return deskId;
        }

        public void setDeskId(String deskId) {
            this.deskId = deskId;
        }
    }

}