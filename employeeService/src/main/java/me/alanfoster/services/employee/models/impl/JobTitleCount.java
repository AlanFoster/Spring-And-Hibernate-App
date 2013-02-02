package me.alanfoster.services.employee.models.impl;

import javax.persistence.Entity;

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