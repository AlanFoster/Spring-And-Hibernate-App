package me.alanfoster.services.employee.models;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

/**
 * Represents a basic Job Model.
 * <br />
 * Note, All fields are adorned with JSR-303 annotations.
 * Therefore all classes which interact with this entity should adhere
 * to the data restrictions placed on it.
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@javax.persistence.Entity
@Table(name = "jobs")
public class Job {
    /**
     * The unique job id for this job entity.
     */
    @Id
    @Column(name = "jobId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Range(min = 1)
    @NotNull
    private Integer jobId;
    /**
     * The job title associated with this job.
     */
    @Column(name = "jobTitle")
    @NotEmpty
    private String jobTitle;

    /**
     * Accessor method.
     *
     * @return The job id
     */
    public final Integer getJobId() {
        return jobId;
    }

    /**
     * Mutator method.
     *
     * @param jobId The job id
     */
    public final void setJobId(final Integer jobId) {
        this.jobId = jobId;
    }

    /**
     * The accessor method.
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

    @Override
    public final String toString() {
        // Auto generated
        return "Job{" +
                "jobId=" + jobId +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}