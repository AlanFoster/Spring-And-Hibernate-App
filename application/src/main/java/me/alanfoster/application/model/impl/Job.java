package me.alanfoster.application.model.impl;

import me.alanfoster.application.model.IJob;

import javax.persistence.*;

/**
 * Represents a basic Job Model
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@Entity
@Table(name = "jobs")
public class Job implements IJob {
    @Id
    @Column(name = "jobId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer jobId;
    @Column(name = "jobTitle")
    private String jobTitle;

    @Override
    public Integer getJobId() {
        return jobId;
    }

    @Override
    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    @Override
    public String getJobTitle() {
        return jobTitle;
    }

    @Override
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
