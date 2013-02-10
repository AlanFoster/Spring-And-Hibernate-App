package me.alanfoster.employee.service;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import me.alanfoster.services.employee.models.Employee;
import me.alanfoster.services.employee.service.EmployeeSearch;
import me.alanfoster.services.employee.service.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import java.util.List;

/**
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class EmployeeSearchSteps {
    /**
     * Basic SLF4J logger
     * @See {@link http://www.slf4j.org/}
     */
    private static final Logger logger = LoggerFactory.getLogger(EmployeeStepDefs.class);

    @Autowired
    private IEmployeeService employeeService;

    private List<Employee> searchResults;

    @When("^the search employee service is called with the following data$")
    public void the_search_employee_service_is_called_with_the_following_data(List<EmployeeSearch> searchList) throws Throwable {
        EmployeeSearch employeeSearch = searchList.get(0);
        searchResults = employeeService.search(employeeSearch);
    }

    @Then("^search employee service will return the following employees$")
    public void search_employee_service_will_return_the_following_employees(List<FlatEmployee> flatEmployees) throws Throwable {
        List<Employee> expectedEmployees = FlatEmployee.getEmployeeDataTableAsEmployee(flatEmployees);

        // Assert the expected and returned lists are equal in the SAME order
        assertReflectionEquals(expectedEmployees, searchResults);
    }

    @Then("^search employee service will return no employees$")
    public void search_employee_service_will_return_no_employees() throws Throwable {
        assertEquals("There should be no employees returned by the search", 0, searchResults.size());
    }



}
