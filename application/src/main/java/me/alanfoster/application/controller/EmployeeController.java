package me.alanfoster.application.controller;

import me.alanfoster.application.config.Config;
import me.alanfoster.application.model.IEmployee;
import me.alanfoster.application.model.impl.Employee;
import me.alanfoster.application.model.impl.Job;
import me.alanfoster.application.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@Controller
@SessionAttributes
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private IEmployeeService employeeService;

    /**
     * Handle adding an employee
     *
     * @param employee
     * @param result
     * @return The redirect to follow - employees.html
     */
    // TODO Use Validation annotations
    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("employee") Employee employee, BindingResult result) {
        employeeService.create(employee);
        return "redirect:employees.html";
    }

    @RequestMapping(value = "/edit/{employeeId}")
    public String addContact(Map<String, Object> map, @PathVariable("employeeId") Integer employeeId) {
        logger.info("Received Request for /edit/{}", new Object[] { employeeId });
        IEmployee employee = employeeService.get(employeeId);
        // Log this and let our presentation layer deal with this scenario
        if(employee == null) {
            logger.debug("Employee Object for employee id {} was null", new Object[] { employeeId });
        }

        map.put("employee", employee);
        return "employeeDetail";
    }


    @RequestMapping("/employees")
    public String showEmployees(Map<String, Object> map) {
        logger.info("Received Request for /employees");

        // We need to instantiate a new Employee object for the presentation layer to make use of
        Employee employee = new Employee();
        employee.setJob(new Job());
        map.put("employee", employee);
        map.put("employees", employeeService.getAll());
        map.put("jobTitles", employeeService.getJobTitles());
        // map.put("deskIds", employeeService.getUniqueDeskIds());

        return "employee";
    }

}