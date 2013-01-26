package me;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import me.alanfoster.application.model.IEmployee;
import me.alanfoster.application.model.impl.Employee;
import me.alanfoster.application.services.IEmployeeService;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    @Autowired
    private IEmployeeService employeeService;

    @Given("^there is an employee service$")
    public void there_is_an_employee_service() throws Throwable {
        // Autowired
        assertNotNull("Employee service should not be null", employeeService);
    }

    @When("^I add the following Employee$")
    public void I_add_the_following_Employee(List<Employee> employees) throws Throwable {
       Employee employee = employees.get(0);
       Integer employeeId = employeeService.create(employee);
    }

    @Then("^there will be '(\\d+)' employee in the employee service$")
    public void there_will_be_amount_employee_in_the_employee_service(int expectedAmount) throws Throwable {
        assertEquals("The total amount of employees should be correct", expectedAmount, employeeService.getAll().size());
    }

    @Then("^the employee with id '(\\d+)' will have the following details$")
    public void the_employee_with_id_will_have_the_following_details(int employeeId, List<Employee> employees) throws Throwable {
        Employee expectedEmployee = employees.get(0);
        IEmployee actualEmployee = employeeService.get(employeeId);

        assertNotNull("The retrieved employee should not be null", actualEmployee);
        assertEquals("The first name should be as expected", expectedEmployee.getFirstName(), actualEmployee.getFirstName());
        assertEquals("The second name should be as expected", expectedEmployee.getSecondName(), actualEmployee.getSecondName());
        assertEquals("The job title should be as expected", expectedEmployee.getJobTitle(), actualEmployee.getJobTitle());
        assertEquals("The desk id should be as expected", expectedEmployee.getDeskId(), actualEmployee.getDeskId());
    }
}