package me.alanfoster.services.employee.service;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * Represents a generic object which is used to for generic
 * searching in a dataset
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class EmployeeSearch {
    @Range(min = 1)
    private Integer id;
    private String firstName;
    private String secondName;
    private String jobTitle;
    @Range(min = 1)
    private Integer minDeskId;
    @Range(min = 1)
    private Integer maxDeskId;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMinDeskId() {
        return minDeskId;
    }

    public void setMinDeskId(Integer minDeskId) {
        this.minDeskId = minDeskId;
    }

    public Integer getMaxDeskId() {
        return maxDeskId;
    }

    public void setMaxDeskId(Integer maxDeskId) {
        this.maxDeskId = maxDeskId;
    }

    @Override
    public String toString() {
        return "EmployeeSearch{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", minDeskId=" + minDeskId +
                ", maxDeskId=" + maxDeskId +
                '}';
    }
}
