package me.alanfoster.services.employee.service.impl;

import me.alanfoster.services.employee.dao.IEmployeeDAO;
import me.alanfoster.services.employee.models.Employee;
import me.alanfoster.services.employee.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Implements the IEmployeeService interface in order to provide a concrete implementation
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see IEmployeeService
 */
@Service
public class EmployeeService implements IEmployeeService {
    /**
     * Basic SLF4J logger
     * @See {@link http://www.slf4j.org/}
     */
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private IEmployeeDAO employeeDAO;



    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Integer create(Employee employee) {
        return employeeDAO.create(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Employee get(Integer key) {
        return employeeDAO.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void update(Employee employee) {
        employeeDAO.update(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void delete(Employee employee) {
        employeeDAO.delete(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void delete(Integer key) {
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
    public List<Employee> search(EmployeeSearch employeeSearch) {
        logger.info("Employee Search request {}", new Object[] { employeeSearch });

        // Create a new instance of the SearchCriteriaCreator and compose our search criteria
        List<ISearchCriteria> searchCriterias = new SearchCriteriaCreator()
                .ilike("firstName", employeeSearch.getFirstName())
                .ilike("secondName", employeeSearch.getSecondName())
                .eq("id", employeeSearch.getId())
                .between("deskId", employeeSearch.getMinDeskId(), employeeSearch.getMaxDeskId())
        // Finally get all of the search criteria as a list
        .getSearchCriterias();

        logger.info("DAO Search Criteria is : {}", new Object[] { searchCriterias });

        return employeeDAO.search(searchCriterias);
    }

    /**
     * Represents a simple search criteria creator. It provides a nice simple DSL in order
     * to produce criterias which can be passed to DAOs for searching.
     * <br />
     * Note:: All operations which are passed null or empty as a string will NOT be added
     * to the internally tracked list of search criteria objects.
     * <br />
     * When you have finished chaining this object to create the criteria, you can retrieve
     * the completed search criterias with getSearchCriterias();
     *
     * @author Alan Foster
     * @version 1.0.0-SNAPSHOT
     * @see IEmployeeService
     */
    public static class SearchCriteriaCreator {
        private List<ISearchCriteria> searchCriterias;

        public SearchCriteriaCreator() {
            this.searchCriterias = new ArrayList<ISearchCriteria>();
        }

        /**
         * Adds a case <strong>sensitive</strong> requirement to the search
         *
         * @param fieldName The name of the field represented in the object
         * @param value     The value which the field should be (Including % for wild card)
         * @return The SearchCriteriaCreator object in order to allow chaining
         */
        private SearchCriteriaCreator like(String fieldName, String value) {
            return addLike(fieldName, value, true);
        }

        /**
         * Adds a case <strong>insensitive</strong> requirement to the search
         *
         * @param fieldName The name of the field represented in the object
         * @param value     The value which the field should be (Including % for wild card)
         * @return The SearchCriteriaCreator object in order to allow chaining
         */
        private SearchCriteriaCreator ilike(String fieldName, String value) {
            return addLike(fieldName, value, true);
        }

        /**
         * Adds a wild card requirement to the search
         * Used internally by this class. You should use the ilike and like operations if you wish
         * to make use of this search requirement
         *
         * @param fieldName       The name of the field represented in the object
         * @param value           The value which the field should be (Including % for wild card)
         * @param caseInsenstiive whether the search should be caseinsenstiive or not
         * @return The SearchCriteriaCreator object in order to allow chaining
         */
        private SearchCriteriaCreator addLike(String fieldName, String value, boolean caseInsenstiive) {
            if (value != null && !value.isEmpty()) {
                searchCriterias.add(new SearchCriteriaLike(fieldName, value, caseInsenstiive));
            }
            return this;
        }

        /**
         * Adds a search criteria for the field name value to be between the min and max value given
         * Note, if the max value is null then this class assumes that the maxValue is equal to
         * the min value
         *
         * @param fieldName The name of the field represented in the object
         * @param minValue  The minimum value
         * @param maxValue  The maximum value. If the maximum value is null then this will equal the min value
         * @return The SearchCriteriaCreator object in order to allow chaining
         */
        public SearchCriteriaCreator between(String fieldName, Integer minValue, Integer maxValue) {
            if (minValue != null) {
                // Deal with the max value being null
                maxValue = maxValue == null ? minValue : maxValue;

                searchCriterias.add(new SearchCriteriaBetween(fieldName, minValue, maxValue));
            }
            return this;
        }

        /**
         * Adds a search criteria for the field value to be exactly that given
         *
         * @param fieldName The name of the field represented in the object
         * @param value  The value the field should be equal to
         * @return The SearchCriteriaCreator object in order to allow chaining
         */
        public SearchCriteriaCreator eq(String fieldName, Object value) {
            if (value != null) {
                searchCriterias.add(new SearchCriteriaEq(fieldName, value));
            }
            return this;
        }



        /**
         * When you have finished chaining this object to create the criteria, you can retrieve
         * the completed search criterias with this operation
         *
         * @return The list of search criteria
         */
        public List<ISearchCriteria> getSearchCriterias() {
            return this.searchCriterias;
        }
    }


}
