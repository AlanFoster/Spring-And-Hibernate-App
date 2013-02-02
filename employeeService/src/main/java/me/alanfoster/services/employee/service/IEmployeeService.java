package me.alanfoster.services.employee.service;

import me.alanfoster.services.employee.models.impl.Job;
import me.alanfoster.services.me.alanfoster.services.core.service.IGenericService;
import me.alanfoster.services.employee.models.IEmployee;

import java.util.List;
import java.util.Map;
/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface IEmployeeService extends IGenericService<IEmployee, Integer> {
    List<Job> getJobs();
}
