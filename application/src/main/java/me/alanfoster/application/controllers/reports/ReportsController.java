package me.alanfoster.application.controllers.reports;

import me.alanfoster.application.controllers.reports.config.ReportsModelConfig;
import me.alanfoster.services.employee.models.impl.JobTitleCount;
import me.alanfoster.services.employee.service.IEmployeeService;
import me.alanfoster.services.employee.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * This controller is used to for managing Reports.
 * This controller is more devised to test the ability of using
 * Controllers that return pure JSON when interacted with
 * Which can then be used for ajax applications
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@Controller
@SessionAttributes
public class ReportsController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IJobService jobService;

    @RequestMapping(value = "/reports")
    public String searchEmployee() {
        return ReportsModelConfig.Reports.getModelName();
    }

    @RequestMapping(value = "/reports/jobs/titleCounts", method = RequestMethod.GET)
    public @ResponseBody List<JobTitleCount> getJobTitleCounts() {
        return jobService.getJobTitleCounts();
    }
}
