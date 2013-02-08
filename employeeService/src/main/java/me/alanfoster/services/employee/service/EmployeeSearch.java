package me.alanfoster.services.employee.service;

/**
 * Represents a generic object which is used to for generic
 * searching in a dataset
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class EmployeeSearch {
    private Integer id;
    private String firstName;
    private String secondName;
    private String jobTitle;
    private Integer minDeskId;
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

}
