package me.alanfoster.services.employee.models.impl;

import javax.persistence.*;

/**
 * Represents a basic Job Model
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@javax.persistence.Entity
@Table(name = "jobs")
public class Job {
    @Id
    @Column(name = "jobId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer jobId;
    @Column(name = "jobTitle")
    private String jobTitle;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public static Job findJob(Integer jobId) {
        Job job = new Job();
        job.setJobId(jobId);
        return job;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}