package me.alanfoster.services.employee.models;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface IJob {
    Integer getJobId();
    void setJobId(Integer jobId);

    String getJobTitle();
    void setJobTitle(String jobTitle);
}