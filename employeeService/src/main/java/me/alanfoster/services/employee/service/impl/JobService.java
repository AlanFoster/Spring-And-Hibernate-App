package me.alanfoster.services.employee.service.impl;

import me.alanfoster.services.employee.dao.IJobDAO;
import me.alanfoster.services.employee.models.impl.Job;
import me.alanfoster.services.employee.models.impl.JobTitleCount;
import me.alanfoster.services.employee.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@Service
public class JobService implements IJobService {
    @Autowired
    private IJobDAO jobDAO;

    @Transactional
    @Override
    public List<Job> getJobs() {
        return jobDAO.getJobs();
    }

    @Transactional
    @Override
    public List<JobTitleCount> getJobTitleCounts() {
        return jobDAO.getJobTitleCounts();
    }
}
