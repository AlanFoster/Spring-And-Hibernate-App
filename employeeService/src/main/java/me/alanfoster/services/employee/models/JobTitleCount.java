package me.alanfoster.services.employee.models;

/**
 * A basic model that stores the jobTitle and the total count of
 * employees that have that jobTitle.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class JobTitleCount {
    /**
     * The Job Title name.
     */
    private String jobTitle;
    /**
     * The total count of employees that have this job title currently.
     */
    private Long count;

    /**
     * Create a new instance of the JobTitleCount object.
     */
    public JobTitleCount() {
    }

    /**
     * Create a new instance of the JobTitleCount object.
     *
     * @param jobTitle The Job Title name
     * @param count    The total count of employees that have this job title currently
     */
    public JobTitleCount(final String jobTitle, final Long count) {
        setJobTitle(jobTitle);
        setCount(count);
    }

    /**
     * Accesss method.
     *
     * @return The job title
     */
    public final String getJobTitle() {
        return jobTitle;
    }

    /**
     * Mutator method.
     *
     * @param jobTitle The job title
     */
    public final void setJobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * Accessor method
     *
     * @return The count of people that have this job
     */
    public final Long getCount() {
        return count;
    }

    /**
     * Mutator method.
     *
     * @param count The count of people that have this job
     */
    public final void setCount(final Long count) {
        this.count = count;
    }

    @Override
    public final String toString() {
        // Auto generated
        return "JobTitleCount{" +
                "jobTitle='" + jobTitle + '\'' +
                ", count=" + count +
                '}';
    }
}
