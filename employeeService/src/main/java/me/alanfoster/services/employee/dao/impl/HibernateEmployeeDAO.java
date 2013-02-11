package me.alanfoster.services.employee.dao.impl;

import me.alanfoster.services.employee.dao.IEmployeeDAO;
import me.alanfoster.services.employee.models.Employee;
import me.alanfoster.services.employee.service.search.ISearchCriteria;
import me.alanfoster.services.employee.service.search.ISearchCriteriaBetween;
import me.alanfoster.services.employee.service.search.ISearchCriteriaEq;
import me.alanfoster.services.employee.service.search.ISearchCriteriaLike;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Provides a concrete implementation of the the basic IEmployeeDAO interface.
 * This class expects the Hibernate sessionFactory to be injected in
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@Repository
public class HibernateEmployeeDAO implements IEmployeeDAO {
    /**
     * The hibernate session factory, autowired by spring.
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Accessor method.
     *
     * @return The session factory instance
     */
    public final SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Mutator method.
     *
     * @param sessionFactory The new session factory
     */
    public final void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Integer create(final Employee employee) {
        return (Integer) sessionFactory.getCurrentSession().save(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Employee get(final Integer key) {
        return (Employee) sessionFactory.getCurrentSession().get(
                Employee.class, key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Employee> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Employee")
                .list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void update(final Employee employee) {
        sessionFactory.getCurrentSession().update(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void delete(final Employee employee) {
        sessionFactory.getCurrentSession().delete(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void delete(final Integer key) {
        delete(get(key));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Employee> search(final List<ISearchCriteria> criterias) {
        Criteria hibernateCriteria = sessionFactory.getCurrentSession()
                .createCriteria(Employee.class);

        // Iterate the list of critera and add it to the the hibernate criteria
        // object
        for (ISearchCriteria criteria : criterias) {
            // Is there a better way to do this?
            if (criteria instanceof ISearchCriteriaLike) {
                ISearchCriteriaLike searchCriteriaLike =
                        (ISearchCriteriaLike) criteria;
                if (searchCriteriaLike.isCaseInsensitive()) {
                    hibernateCriteria.add(Restrictions.ilike(
                            searchCriteriaLike.getFieldName(),
                            searchCriteriaLike.getRequiredValue()));
                } else {
                    hibernateCriteria.add(Restrictions.like(
                            searchCriteriaLike.getFieldName(),
                            searchCriteriaLike.getRequiredValue()));
                }
            } else if (criteria instanceof ISearchCriteriaBetween) {
                ISearchCriteriaBetween searchCriteriaBetween =
                        (ISearchCriteriaBetween) criteria;
                hibernateCriteria.add(Restrictions.between(
                        searchCriteriaBetween.getFieldName(),
                        searchCriteriaBetween.getMin(),
                        searchCriteriaBetween.getMax()));
            } else if (criteria instanceof ISearchCriteriaEq) {
                ISearchCriteriaEq searchCriteriaEq = (ISearchCriteriaEq) criteria;
                hibernateCriteria.add(Restrictions.eq(
                        searchCriteriaEq.getFieldName(),
                        searchCriteriaEq.getValue()));
            }
        }

        List<Employee> matchingEmployees = hibernateCriteria.list();

        return matchingEmployees;
    }
}
