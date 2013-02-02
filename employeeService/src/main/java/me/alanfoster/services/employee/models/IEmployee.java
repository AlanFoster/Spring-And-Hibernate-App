package me.alanfoster.services.employee.models;

import me.alanfoster.services.employee.models.impl.Job;

import java.io.Serializable;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface IEmployee extends Serializable {
    Integer getId();
    void setId(Integer id);

    String getFirstName();
    void setFirstName(String firstName);

    String getSecondName();
    void setSecondName(String secondName);

    IJob getJob();
    void setJob(Job job);

    Integer getDeskId();
    void setDeskId(Integer deskId);


}
