package me.alanfoster.application.model.impl;

import me.alanfoster.application.model.IEmployee;

import javax.persistence.*;

/**
 * Represents a basic Employee Model
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@Entity
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
    @Column(name = "jobTitle")
   // @OneToOne(fetch = FetchType.EAGER)
   // @JoinColumn(name = "jobId")
    private String jobTitle;
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
    public String getJobTitle() {
        return jobTitle;
    }

    @Override
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", deskId=" + deskId +
        '}';
    }
}
