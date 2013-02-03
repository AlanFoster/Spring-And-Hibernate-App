package me.alanfoster.services.employee.service;

import me.alanfoster.services.employee.models.impl.Job;
import me.alanfoster.services.employee.models.impl.JobTitleCount;

import java.util.List;

/**
 * Interface for accessing the Job related operations.
 * Note, the emploee service will still of coures return employees with Job objects in them
 * This service is purely for separation of concerns
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface IJobService {
    /**
     * Get the entire list of jobs that are registered within the system
     *
     * @return the jobs
     */
    List<Job> getJobs();

    /**
     * Used to get a list of distinct jobTitles with an associated count of employees which have that job title currently
     *
     * @return The list of Job title + count objects
     */
    List<JobTitleCount> getJobTitleCounts();

    /**
     * Used to get a specific Job with the specific job id
     *
     * @param jobId The unique job id
     * @return The job instance matching the job id
     */
    Job get(Integer jobId);
}
