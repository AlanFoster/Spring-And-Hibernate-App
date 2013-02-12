package me.alanfoster.services.employee.models;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * Represents a basic Employee Model.
 * <br />
 * Note, All fields are adorned with JSR-303 annotations.
 * Therefore all classes which interact with this entity should adhere
 * to the data restrictions placed on it.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@javax.persistence.Entity
@Table(name = "Employees")
public class Employee {
    /**
     * The unique employee id.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * The employee's first name.
     */
    @Column(name = "firstName")
    @NotEmpty
    private String firstName;
    /**
     * The employee's second name.
     */
    @Column(name = "secondName")
    @NotEmpty
    private String secondName;
    /**
     * The job assigned to this employee.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jobId")
    @Valid
    @NotNull
    private Job job;
    /**
     * The deks id that this employee is assigned to.
     */
    @Column(name = "deskId")
    @Range(min = 1)
    @NotNull
    private Integer deskId;

    /**
     * Accessor method.
     *
     * @return The first name
     */
    public final String getFirstName() {
        return firstName;
    }

    /**
     * Mutator method.
     *
     * @param firstName the first name
     */
    public final void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Accessor method.
     *
     * @return The second name
     */
    public final String getSecondName() {
        return secondName;
    }

    /**
     * Mutator method.
     *
     * @param secondName The second name
     */
    public final void setSecondName(final String secondName) {
        this.secondName = secondName;
    }

    /**
     * Access method.
     *
     * @return The desk id
     */
    public final Integer getDeskId() {
        return deskId;
    }

    /**
     * Mutator method.
     *
     * @param deskId The desk id
     */
    public final void setDeskId(final Integer deskId) {
        this.deskId = deskId;
    }

    /**
     * Accessor method.
     *
     * @return The id
     */
    public final Integer getId() {
        return id;
    }

    /**
     * Mutator Method.
     *
     * @param id The employee id
     */
    public final void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Accessor method.
     *
     * @return The employee's job
     */
    public final Job getJob() {
        return job;
    }

    /**
     * Mutator method.
     *
     * @param job The employee's job
     */
    public final void setJob(final Job job) {
        this.job = (Job) job;
    }

    @Override
    public final String toString() {
        // Auto Generated
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", job=" + job +
                ", deskId=" + deskId +
                '}';
    }
}
