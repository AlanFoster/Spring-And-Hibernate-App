package me.alanfoster.services.employee.models;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @Range(min = 1)
    @NotNull
    private Integer jobId;
    @Column(name = "jobTitle")
    @NotEmpty
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

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}