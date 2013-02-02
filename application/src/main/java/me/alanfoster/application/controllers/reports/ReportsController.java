package me.alanfoster.application.controllers.reports;

import me.alanfoster.application.controllers.reports.config.ReportsModelConfig;
import me.alanfoster.services.employee.models.impl.Job;
import me.alanfoster.services.employee.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.LinkedList;
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

    @RequestMapping(value = "/reports")
    public String searchEmployee() {
        return ReportsModelConfig.Reports.getModelName();
    }

    @RequestMapping(value = "/reports/jobs/titleCounts", method = RequestMethod.GET)
    public @ResponseBody List<JobTitleCount> getJobTitleCounts() {
        List<JobTitleCount> jobTitleCounts = new LinkedList<JobTitleCount>();
        jobTitleCounts.add(getCount("HR", 2));
        jobTitleCounts.add(getCount("Operations", 2));
        jobTitleCounts.add(getCount("Engineer", 3));
        jobTitleCounts.add(getCount("Executive Engineer", 1));

        return jobTitleCounts;
    }

    public JobTitleCount getCount(String title, Integer count) {
        JobTitleCount jobTitleCount = new JobTitleCount();
        jobTitleCount.setJobTitle(title);
        jobTitleCount.setCount(count);
        return jobTitleCount;
    }

    public static class JobTitleCount {
        private String jobTitle;
        private Integer count;

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }
}
