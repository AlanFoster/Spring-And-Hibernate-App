package me.alanfoster.services.employee.dao.impl;

import me.alanfoster.services.employee.dao.IJobDAO;
import me.alanfoster.services.employee.models.impl.Job;
import me.alanfoster.services.employee.models.impl.JobTitleCount;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@Repository
public class HibernateJobDAO implements IJobDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Job> getJobs() {
        // TODO Grab from database correctly
        List<Job> jobs = new LinkedList<Job>();
        jobs.add(getJob(1, "HR"));
        jobs.add(getJob(2, "Operations"));
        jobs.add(getJob(3, "Engineer"));
        jobs.add(getJob(4, "Senior Engineer"));
        jobs.add(getJob(5, "Executive Engineer"));
        jobs.add(getJob(6, "Developer"));
        jobs.add(getJob(7, "Tester"));

        return (List) jobs;
    }

    public Job getJob(Integer id, String jobTitle) {
        Job job = new Job();
        job.setJobId(id);
        job.setJobTitle(jobTitle);
        return job;
    }

    @Override
    public List<JobTitleCount> getJobTitleCounts() {
        Query query = sessionFactory.getCurrentSession().createQuery("select distinct new  me.alanfoster.services.employee.models.impl.JobTitleCount(employee.job.jobTitle, count(employee)) from Employee as employee group by employee.job.jobTitle");
        List<JobTitleCount> result = (List<JobTitleCount>) query.list();
        return result;
    }
}
