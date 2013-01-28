package me.alanfoster.application.model;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface IEmployee {
    Integer getId();
    void setId(Integer id);

    String getFirstName();
    void setFirstName(String firstName);

    String getSecondName();
    void setSecondName(String secondName);

    IJob getJob();
    void setJob(IJob job);

    Integer getDeskId();
    void setDeskId(Integer deskId);
}
