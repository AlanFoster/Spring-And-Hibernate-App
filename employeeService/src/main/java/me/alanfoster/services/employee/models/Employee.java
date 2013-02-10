package me.alanfoster.services.employee.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * Represents a basic Employee Model
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@javax.persistence.Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "firstName")
    @NotEmpty
    private String firstName;
    @Column(name = "secondName")
    @NotEmpty
    private String secondName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jobId")
    @Valid
    @NotNull
    private Job job;
    @Column(name="deskId")
    @Range(min = 1)
    @NotNull
    private Integer deskId;

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

    public Integer getDeskId() {
        return deskId;
    }

    public void setDeskId(Integer deskId) {
        this.deskId = deskId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = (Job) job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", job=" + job +
                ", deskId=" + deskId +
                '}';
    }
}
