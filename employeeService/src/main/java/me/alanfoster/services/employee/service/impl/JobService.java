package me.alanfoster.services.employee.service.impl;

import me.alanfoster.services.employee.dao.IJobDAO;
import me.alanfoster.services.employee.models.Job;
import me.alanfoster.services.employee.models.JobTitleCount;
import me.alanfoster.services.employee.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implements the IJobService interface to provide the concrete implementation
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see IJobService
 */
@Service
public class JobService implements IJobService {
    /**
     * The IJobDAO instance which is autowired by spring
     */
    @Autowired
    private IJobDAO jobDAO;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public List<Job> getJobs() {
        return jobDAO.getJobs();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public List<JobTitleCount> getJobTitleCounts() {
        return jobDAO.getJobTitleCounts();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Job get(Integer jobId) {
        return jobDAO.get(jobId);
    }
}
