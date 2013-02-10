package me.alanfoster.services.employee.dao;

import me.alanfoster.services.core.dao.ICrudDAO;
import me.alanfoster.services.employee.models.Employee;
import me.alanfoster.services.employee.service.ISearchCriteria;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface IEmployeeDAO extends ICrudDAO<Employee, Integer> {
    /**
     * A generic way to find employee details
     * @param criterias The list search criteria
     * @return The list of employees which match the criteria
     */
    public List<Employee> search(List<ISearchCriteria> criterias);
}
