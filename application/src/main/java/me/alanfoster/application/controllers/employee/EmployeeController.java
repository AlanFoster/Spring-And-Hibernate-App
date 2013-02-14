package me.alanfoster.application.controllers.employee;

import me.alanfoster.application.controllers.
        employee.config.EmployeeModelConfig;
import me.alanfoster.application.controllers.employee.config.
        EmployeeRequestMappingConfig;
import me.alanfoster.application.controllers.employee.forms.JobEditor;
import me.alanfoster.application.controllers.notification.config.Notification;
import me.alanfoster.application.controllers.notification.config.
        NotificationRequestMappingConfig;
import me.alanfoster.services.employee.models.Employee;
import me.alanfoster.services.employee.models.Job;
import me.alanfoster.services.employee.service.EmployeeSearch;
import me.alanfoster.services.employee.service.IEmployeeService;
import me.alanfoster.services.employee.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@Controller
@SessionAttributes
public class EmployeeController {
    /**
     * Basic SLF4J logger.
     *
     * @See {@link http://www.slf4j.org/}
     */
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    /**
     * The employee service.
     */
    @Autowired
    private IEmployeeService employeeService;

    /**
     * The job service.
     */
    @Autowired
    private IJobService jobService;

    /**
     * Register our custom binders to the WebDataBinder object This allows for
     * custom property editors to be registered.
     *
     * @param binder The binder which is automatically injected by spring
     * @see JobEditor
     */
    @InitBinder
    public final void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(Job.class, new JobEditor(jobService));
    }

    /**
     * Maps the application index to the employee welcome page.
     *
     * @return the index model
     */
    @RequestMapping("/")
    public final String index() {
        logger.info("Received Request for /");
        return EmployeeModelConfig.Index.getModelName();
    }

    /**
     * Binds jobs to the model attribute.
     *
     * @return The list of jobs
     */
    @ModelAttribute("jobs")
    public final List<Job> populateJobs() {
        return jobService.getJobs();
    }

    /********************************************************************
     * Get Methods
     ********************************************************************/

    /**
     * The get page for searching for an employee.
     *
     * @param map The map to bind model attributes to
     * @return the required model to render
     */
    @RequestMapping(value = "/employees/search")
    public final String searchEmployee(final Map<String, Object> map) {
        logger.info("Received Get Request for /search");

        // Load all of the default information to show, including a new employee
        // search object
        map.put("employees", employeeService.getAll());
        map.put("employeeSearchCriteria", new EmployeeSearch());

        return EmployeeModelConfig.Search.getModelName();
    }

    /**
     * The post page for searching for an employee, presumably to be used via
     * XHR.
     *
     * @param employeeSearchCriteria The criteria to search for
     * @return the list of employees, as JSON, that match the search request
     */
    @RequestMapping(value = "/employees/search", method = RequestMethod.POST)
    @ResponseBody
    public final List<Employee> searchEmployeePost(@RequestBody final EmployeeSearch employeeSearchCriteria) {
        logger.info("Received XHR request for searchEmployee");

        List<Employee> employees = employeeService.search(employeeSearchCriteria);
        logger.info("Received employees from employee service {}", new Object[]{employees});

        return employees;
    }


    /*********************************************************************
     * Create Methods
     *********************************************************************/

    /**
     * Handle adding an employee.
     *
     * @param employee The validated employee object
     * @param result   The binding result for form validation
     * @return The redirect to follow
     */
    @RequestMapping(value = "/employees/add", method = RequestMethod.POST)
    public final String addEmployeePost(
            @Valid @ModelAttribute("employee") final Employee employee,
            final BindingResult result) {
        logger.info("Received post request for /employees/add");

        // If the form has errors, log it, and redirect back to the previous
        // page
        if (result.hasErrors()) {
            logger.debug("Form for add employee had errors - {}",
                    new Object[]{result.getAllErrors()});
            return EmployeeModelConfig.Add.getModelName();
        }

        employeeService.create(employee);
        return EmployeeRequestMappingConfig.Search.getRedirectRequestMapping();
    }

    /**
     * The get page for adding an employee.
     *
     * @param map The map to bind model attributes to
     * @return the required model to show
     */
    @RequestMapping("/employees/add")
    public final String addEmployee(final Map<String, Object> map) {
        logger.info("Received Request for /employees");

        Employee employee = new Employee();
        employee.setJob(new Job());

        map.put("employee", employee);
        map.put("employees", employeeService.getAll());

        return EmployeeModelConfig.Add.getModelName();
    }

    /**
     * ****************************************************************
     * Edit Methods
     * *****************************************************************
     */

    /**
     * Get method for editing an employee.
     *
     * @param map        The map to bind model attributes to
     * @param employeeId The employee id to edit
     * @return the required model to render
     */
    @RequestMapping(value = "/employees/edit/{employeeId}")
    public final String editEmployee(final Map<String, Object> map,
                                     @PathVariable("employeeId") final Integer employeeId) {
        logger.info("Received Request for /edit/{}",
                new Object[]{employeeId});
        Employee employee = employeeService.get(employeeId);
        // Log this and let our presentation layer deal with this scenario
        if (employee == null) {
            logger.debug("Employee Object for employee id {} was null",
                    new Object[]{employeeId});
        }

        map.put("employee", employee);

        return EmployeeModelConfig.Edit.getModelName();
    }

    /**
     * The post method for editing an empoloyee
     *
     * @param employeeId         The employee id to edit
     * @param employee           The new employee object
     * @param result             The binding result for form validation
     * @param redirectAttributes Attributes to send to the redirect page
     *                           This is the basic get post redirected pattern
     * @return the required model to render
     */
    @RequestMapping(value = "/employees/edit/{employeeId}", method = RequestMethod.POST)
    public final String editEmployeePost(
            @PathVariable("employeeId") final Integer employeeId,
            @Valid @ModelAttribute("employee") final Employee employee,
            final BindingResult result,
            final RedirectAttributes redirectAttributes) {
        logger.info("Received Request for /edit/{}",
                new Object[]{employeeId});

        // If the form has errors, log it, and redirect back to the previous
        // page
        if (result.hasErrors()) {
            logger.debug("Form for edit employee had errors - {}",
                    new Object[]{result.getAllErrors()});
            return EmployeeModelConfig.Edit.getModelName();
        }

        employeeService.update(employee);
        // Add a flash attribute to let the redirected page know of our success
        redirectAttributes.addFlashAttribute("formResult",
                Notification.SUCCESS.getValue());
        return NotificationRequestMappingConfig.Notification
                .getRedirectRequestMapping();
    }

    /**
     * ******************************************************************
     * Delete Methods
     * ******************************************************************
     */

    /**
     * Deletes an employee.
     *
     * @param map                The map to bind model attributes to
     * @param employeeId         the employee id to delete
     * @param redirectAttributes Attributes to send to the redirect page
     *                           This is the basic get post redirected pattern
     * @return the required model to render
     */
    @RequestMapping(value = "/employees/delete/{employeeId}")
    public final String deleteEmployeePost(final Map<String, Object> map,
                                           @PathVariable("employeeId") final Integer employeeId,
                                           final RedirectAttributes redirectAttributes) {
        logger.info("Received Request for /delete/{}",
                new Object[]{employeeId});

        // Try to delete the employee from the service, and if we can't log it
        // and update the presentation layer to show a failure
        try {
            employeeService.delete(employeeId);
        } catch (Exception e) {
            logger.info("Unable to complete deletion of employee with id {}",
                    new Object[]{employeeId}, e);
            // Add a flash attribute to let the redirected page know of our
            // failure
            redirectAttributes.addFlashAttribute("formResult",
                    Notification.ERROR.getValue());
            redirectAttributes
                    .addFlashAttribute(
                            "formResultDetail",
                            "Unable to delete this employee from the database. "
                                    + "Please contact a System Administrator if this problem persists");
            return NotificationRequestMappingConfig.Notification
                    .getRedirectRequestMapping();
        }

        redirectAttributes.addFlashAttribute("formResult",
                Notification.SUCCESS.getValue());
        return NotificationRequestMappingConfig.Notification
                .getRedirectRequestMapping();
    }
}