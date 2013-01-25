package me.alanfoster.application.controller;

import me.alanfoster.application.config.Config;
import me.alanfoster.application.model.impl.Employee;
import me.alanfoster.application.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

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
    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("employee") Employee employee, BindingResult result) {
        employeeService.create(employee);
        return "redirect:employees.html";
    }

    @RequestMapping("/employees")
    public String showEmployees(Map<String, Object> map) {
        logger.info("Received Request for /employees");

        map.put("employee", new Employee());
        map.put("employees", employeeService.getAll());

        return "employee";
    }
}