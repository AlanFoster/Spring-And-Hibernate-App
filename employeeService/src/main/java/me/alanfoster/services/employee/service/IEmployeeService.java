package me.alanfoster.services.employee.service;

import me.alanfoster.services.employee.models.impl.Job;
import me.alanfoster.services.employee.models.impl.JobTitleCount;
import me.alanfoster.services.me.alanfoster.services.core.service.ICrudService;
import me.alanfoster.services.employee.models.IEmployee;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface IEmployeeService extends ICrudService<IEmployee, Integer> {

}
