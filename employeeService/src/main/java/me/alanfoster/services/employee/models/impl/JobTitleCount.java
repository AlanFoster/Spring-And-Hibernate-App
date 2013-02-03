package me.alanfoster.services.employee.models.impl;

/**
 * A basic model that stores the jobTitle and the total count of employees that have that jobTitle
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class JobTitleCount {
    /**
     * The Job Title name
     */
    private String jobTitle;
    /**
     * The total count of employees that have this job title currently
     */
    private Long count;

    /**
     * Create a new instance of the JobTitleCount object
     */
    public JobTitleCount() {
    }

    /**
     * Create a new instance of the JobTitleCount object
     *
     * @param jobTitle The Job Title name
     * @param count    The total count of employees that have this job title currently
     */
    public JobTitleCount(String jobTitle, Long count) {
        setJobTitle(jobTitle);
        setCount(count);
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "JobTitleCount{" +
                "jobTitle='" + jobTitle + '\'' +
                ", count=" + count +
                '}';
    }
}