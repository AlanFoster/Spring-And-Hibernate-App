package me.alanfoster.application.controllers.employee;

import me.alanfoster.application.controllers.employee.config.EmployeeModelConfig;
import me.alanfoster.application.controllers.employee.config.EmployeeRequestMappingConfig;
import me.alanfoster.application.controllers.notification.config.Notification;
import me.alanfoster.application.controllers.notification.config.NotificationRequestMappingConfig;
import me.alanfoster.services.employee.models.IEmployee;
import me.alanfoster.services.employee.models.impl.Employee;
import me.alanfoster.services.employee.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import me.alanfoster.employee.webservice.IEmployeeWebservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    private IEmployeeWebservice employeeWebservice;


    @RequestMapping("/")
    public String index(Map<String, Object> map) {
        logger.info("Received Request for /");
        return EmployeeModelConfig.Index.getModelName();
    }

    /**
     * Handle adding an employee
     *
     * @param employee
     * @param result
     * @return The redirect to follow - employees.html
     */
    // TODO Use Validation annotations
    @RequestMapping(value = "/employees/add", method = RequestMethod.POST)
    public String addEmployeePost(@ModelAttribute("employee") Employee employee, BindingResult result) {
        employeeService.create(employee);
        return EmployeeRequestMappingConfig.Search.getRedirectRequestMapping();
    }

    @RequestMapping("/employees/add")
    public String addEmployee(Map<String, Object> map) {
        logger.info("Received Request for /employees");

        map.put("employee", new Employee());
        map.put("employees", employeeService.getAll());
        map.put("jobTitles", employeeService.getJobTitles());

        return EmployeeModelConfig.Add.getModelName();
    }

    @RequestMapping(value = "/employees/edit/{employeeId}")
    public String editEmployee(Map<String, Object> map, @PathVariable("employeeId") Integer employeeId) {
        logger.info("Received Request for /edit/{}", new Object[]{employeeId});
        IEmployee employee = employeeService.get(employeeId);
        // Log this and let our presentation layer deal with this scenario
        if (employee == null) {
            logger.debug("Employee Object for employee id {} was null", new Object[]{employeeId});
        }

        map.put("employee", employee);
        map.put("jobTitles", employeeService.getJobTitles());
        return EmployeeModelConfig.Edit.getModelName();
    }

    @RequestMapping(value = "/employees/search")
    public String searchEmployee(Map<String, Object> map) {
        logger.info("Received Request for /search");
        map.put("employees", employeeService.getAll());
        return EmployeeModelConfig.Search.getModelName();
    }

    @RequestMapping(value = "/employees/edit/{employeeId}", method = RequestMethod.POST)
    public String editEmployeePost(Map<String, Object> map, @PathVariable("employeeId") Integer employeeId,
                                   @ModelAttribute("employee") Employee employee, BindingResult result,
                                   final RedirectAttributes redirectAttributes) {
        logger.info("Received Request for /edit/{}", new Object[]{employeeId});
        employeeService.update(employee);
        // Add a flash attribute to let the redirected page know of our success
        redirectAttributes.addFlashAttribute("formResult", Notification.SUCCESS.getValue());
        return NotificationRequestMappingConfig.Notification.getRedirectRequestMapping();
    }

    @RequestMapping(value = "/employees/delete/{employeeId}")
    public String deleteEmployeePost(Map<String, Object> map, @PathVariable("employeeId") Integer employeeId, final RedirectAttributes redirectAttributes) {
        logger.info("Received Request for /delete/{}", new Object[]{employeeId});

        // Try to delete the employee from the service, and if we can't log it and update the presentation layer to show a failure
        try {
            employeeService.delete(employeeId);
        } catch (Exception e) {
            logger.info("Unable to complete deletion of employee with id {}", new Object[]{employeeId}, e);
            // Add a flash attribute to let the redirected page know of our failure
            redirectAttributes.addFlashAttribute("formResult", Notification.ERROR.getValue());
            // TODO Is this formResultDetail presentation layer rather than business/controller layer?
            redirectAttributes.addFlashAttribute("formResultDetail", "Unable to delete this employee from the database. Please contact a System Administrator if this problem persists");
            return NotificationRequestMappingConfig.Notification.getRedirectRequestMapping();
        }

        redirectAttributes.addFlashAttribute("formResult", Notification.SUCCESS.getValue());
        return NotificationRequestMappingConfig.Notification.getRedirectRequestMapping();
    }
}