package me.alanfoster.employee.service;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import me.alanfoster.services.employee.models.IEmployee;
import me.alanfoster.services.employee.models.impl.Employee;
import me.alanfoster.services.employee.models.impl.Job;
import me.alanfoster.services.employee.service.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

import static junit.framework.Assert.*;
import static me.alanfoster.employee.service.EmployeeAssert.*;

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

    @Autowired
    private DataSource dataSource;

    @Given("^there is an employee service$")
    public void there_is_an_employee_service() throws Throwable {
        // Autowired
        assertNotNull("Employee service should not be null", employeeService);
    }

    @Given("^an employee with the following details$")
    @When("^the create employee service is called with the following data$")
    public void I_add_the_following_Employee(List<FlatEmployee> employees) throws Throwable {
        IEmployee employee = employees.get(0).getAsEmployee();
        Integer employeeId = employeeService.create(employee);
    }

    @Then("^there will be '(\\d+)' employee in the employee service$")
    public void there_will_be_amount_employee_in_the_employee_service(int expectedAmount) throws Throwable {
        assertEquals("The total amount of employees should be correct", expectedAmount, employeeService.getAll().size());
    }

    @Then("^the employee with id '(\\d+)' will have the following details$")
    public void the_employee_with_id_will_have_the_following_details(int employeeId, List<FlatEmployee> employees) throws Throwable {
        FlatEmployee expectedEmployee = employees.get(0);
        IEmployee actualEmployee = employeeService.get(employeeId);

        assertEqual(expectedEmployee, actualEmployee);
    }

    @When("^the update employee service is called with the following data$")
    public void the_update_employee_service_is_called_with_the_following_data(List<FlatEmployee> employees) throws Throwable {
        IEmployee employee = employees.get(0).getAsEmployee();
        employeeService.update(employee);
    }

    @When("^the delete employee service is called with the employee id '(\\d+)'$")
    public void the_delete_employee_service_is_called_with_the_employee_id_(int employeeId) throws Throwable {
        employeeService.delete(employeeId);
    }
}