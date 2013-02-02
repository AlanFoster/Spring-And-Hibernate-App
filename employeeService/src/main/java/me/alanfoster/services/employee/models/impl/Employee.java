package me.alanfoster.services.employee.models.impl;

import me.alanfoster.services.employee.models.IEmployee;

import javax.persistence.*;

/**
 * Represents a basic Employee Model
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@javax.persistence.Entity
@Table(name = "Employees")
public class Employee implements IEmployee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "secondName")
    private String secondName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jobId")
    private Job job;
    @Column(name="deskId")
    private Integer deskId;

    /**
     * @inheritDoc
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getSecondName() {
        return secondName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public Integer getDeskId() {
        return deskId;
    }

    @Override
    public void setDeskId(Integer deskId) {
        this.deskId = deskId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Job getJob() {
        return job;
    }

    @Override
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
