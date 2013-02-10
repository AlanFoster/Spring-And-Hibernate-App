package me.alanfoster.services.employee.service;

import me.alanfoster.services.employee.models.Employee;
import me.alanfoster.services.me.alanfoster.services.core.service.ICrudService;

import java.util.List;

/**
 * The employee service web service.
 * This class implements the ICrudService interface as it provides
 * basic CRUD operations for manipulating Employees and provides
 * an additoinal advanced search functionality.
 * <br />
 * For operations related to Jobs, see the IJobService interface
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see IJobService
 */
public interface IEmployeeService extends ICrudService<Employee, Integer> {
    /**
     * A generic way to find employee details.
     *
     * @param employeeSearch The search criteria
     * @return The list of employees which match the criteria
     */
    List<Employee> search(EmployeeSearch employeeSearch);
}
