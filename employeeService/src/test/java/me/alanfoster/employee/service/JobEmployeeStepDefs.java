package me.alanfoster.employee.service;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import me.alanfoster.services.employee.models.Job;
import me.alanfoster.services.employee.models.JobTitleCount;
import me.alanfoster.services.employee.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import java.util.List;

import static junit.framework.Assert.*;
import static org.unitils.reflectionassert.ReflectionAssert.*;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class JobEmployeeStepDefs {
    @Autowired
    private IJobService jobService;
    private List<JobTitleCount> jobTitleCounts;
    private List<Job> jobs;

    @Given("^there is an job service$")
    public void there_is_an_job_service() throws Throwable {
        // Autowired
        assertNotNull("Employee service should not be null", jobService);
    }

    @When("^the get job title counts operation is called$")
    public void the_get_job_title_counts_operation_is_called() throws Throwable {
        jobTitleCounts = jobService.getJobTitleCounts();
    }

    @Then("^there will be no job titles returned$")
    public void there_will_be_no_job_titles_returned() throws Throwable {
        assertNotNull("The returned job title counts should not be null", jobTitleCounts);
        assertEquals("There should be no job title counts returned", 0, jobTitleCounts.size());
    }

    @Then("^the job title counts returned will be$")
    public void the_job_title_counts_returned_will_be(List<JobTitleCount> expectedJobTitleCounts) throws Throwable {
        // Assert the expected and returned lists are equal in any order
       assertReflectionEquals(expectedJobTitleCounts, jobTitleCounts, ReflectionComparatorMode.LENIENT_ORDER);
    }

    public static void assertEqual(JobTitleCount expected, JobTitleCount actual) {
        assertEquals("The job title should be the same", expected.getJobTitle(), actual.getJobTitle());
        assertEquals("The count should be the same", expected.getCount(), actual.getCount());
    }

    @When("^the get jobs operation is called$")
    public void the_get_jobs_operation_is_called() throws Throwable {
        jobs = jobService.getJobs();
    }

    @Then("^the job returned jobs will be$")
    public void the_job_returned_jobs_will_be(List<Job> expectedJobs) throws Throwable {
        // Assert the expected and returned lists are equal in the SAME order
        assertReflectionEquals(expectedJobs, jobs);
    }
}
