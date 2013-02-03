package me.alanfoster.services.employee.dao.impl;

import me.alanfoster.services.employee.dao.IJobDAO;
import me.alanfoster.services.employee.models.impl.Job;
import me.alanfoster.services.employee.models.impl.JobTitleCount;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A concrete implementation for the IJobDAO interface
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see IJobDAO
 */
@Repository
public class HibernateJobDAO implements IJobDAO {
    /**
     * The hibernate session factory, autowired by spring
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Job> getJobs() {
        return sessionFactory.getCurrentSession().createQuery("from Job").list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<JobTitleCount> getJobTitleCounts() {
        Query query = sessionFactory.getCurrentSession().createQuery("select distinct new  me.alanfoster.services.employee.models.impl.JobTitleCount(employee.job.jobTitle, count(employee)) from Employee as employee group by employee.job.jobTitle");
        List<JobTitleCount> result = (List<JobTitleCount>) query.list();
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Job get(Integer jobId) {
        return (Job) sessionFactory.getCurrentSession().get(Job.class, jobId);
    }
}
