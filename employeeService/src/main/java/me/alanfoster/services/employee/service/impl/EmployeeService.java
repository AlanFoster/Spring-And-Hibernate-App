package me.alanfoster.services.employee.service.impl;

import me.alanfoster.services.employee.dao.IEmployeeDAO;
import me.alanfoster.services.employee.models.Employee;
import me.alanfoster.services.employee.service.EmployeeSearch;
import me.alanfoster.services.employee.service.IEmployeeService;
import me.alanfoster.services.employee.service.search.ISearchCriteria;
import me.alanfoster.services.employee.service.search.SearchCriteriaCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implements the IEmployeeService interface in order to provide a concrete implementation.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see IEmployeeService
 */
@Service
public class EmployeeService implements IEmployeeService {
    /**
     * Basic SLF4J logger
     *
     * @See {@link http://www.slf4j.org/}
     */
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    /**
     * The IEmployeeDAO instance which is autowired by spring.
     */
    @Autowired
    private IEmployeeDAO employeeDAO;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public final Integer create(final Employee employee) {
        return employeeDAO.create(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public final Employee get(final Integer key) {
        return employeeDAO.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public final List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public final void update(final Employee employee) {
        employeeDAO.update(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public final void delete(final Employee employee) {
        employeeDAO.delete(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public final void delete(final Integer key) {
        employeeDAO.delete(key);
    }

    /**
     * {@inheritDoc}
     * Internal to this concrete implementation of the service we will construct a
     * list of ISearchCriteria in order to deal with null values that are passed in
     * from the external request etc. Which removes that sort of logic from the
     * data access layer, as it shouldn't know what our business requirements
     */
    @Transactional
    @Override
    public final List<Employee> search(final EmployeeSearch employeeSearch) {
        logger.info("Employee Search request {}", new Object[]{employeeSearch});

        // Create a new instance of the SearchCriteriaCreator and compose our search criteria
        List<ISearchCriteria> searchCriterias = new SearchCriteriaCreator()
                .ilike("firstName", employeeSearch.getFirstName())
                .ilike("secondName", employeeSearch.getSecondName())
                .between("id", employeeSearch.getMinEmployeeId(), employeeSearch.getMaxEmployeeId())
                .between("deskId", employeeSearch.getMinDeskId(), employeeSearch.getMaxDeskId())
                // Finally get all of the search criteria as a list
                .getSearchCriterias();

        logger.info("DAO Search Criteria is : {}", new Object[]{searchCriterias});

        return employeeDAO.search(searchCriterias);
    }


}
