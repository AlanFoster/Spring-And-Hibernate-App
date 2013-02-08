package me.alanfoster.services.employee.dao.impl;

import me.alanfoster.services.employee.dao.IEmployeeDAO;
import me.alanfoster.services.employee.models.IEmployee;
import me.alanfoster.services.employee.models.impl.Employee;
import me.alanfoster.services.employee.service.EmployeeSearch;
import me.alanfoster.services.employee.service.ISearchCriteria;
import me.alanfoster.services.employee.service.ISearchCriteriaBetween;
import me.alanfoster.services.employee.service.ISearchCriteriaLike;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.Format;
import java.util.List;

/**
 * Provides a concrete implementation of the the basic IEmployeeDAO interface
 * This class expects the Hibernate sessionFactory to be injected in
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@Repository
public class HibernateEmployeeDAO implements IEmployeeDAO {
    /**
     * The hibernate session factory, autowired by spring
     */
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * {@inheritDoc
     */
    @Override
    public Integer create(IEmployee employee) {
        return (Integer) sessionFactory.getCurrentSession().save(employee);
    }

    /**
     * {@inheritDoc
     */
    @Override
    public IEmployee get(Integer key) {
        return (IEmployee) sessionFactory.getCurrentSession().get(Employee.class, key);
    }

    /**
     * {@inheritDoc
     */
    @Override
    public List<IEmployee> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Employee").list();
    }

    /**
     * {@inheritDoc
     */
    @Override
    public void update(IEmployee employee) {
        sessionFactory.getCurrentSession().update(employee);
    }

    /**
     * {@inheritDoc
     */
    @Override
    public void delete(IEmployee employee) {
        sessionFactory.getCurrentSession().delete(employee);
    }

    /**
     * {@inheritDoc
     */
    @Override
    public void delete(Integer key) {
        delete(get(key));
    }

    /**
     * {@inheritDoc
     */
    @Override
    public List<Employee> search(List<ISearchCriteria> criterias) {
        Criteria hibernateCriteria = sessionFactory.getCurrentSession().createCriteria(Employee.class);

        // Iterate the list of critera and add it to the the hibernate criteria object
        for (ISearchCriteria criteria : criterias) {
            // Is there a better way to do this?
            if(criteria instanceof ISearchCriteriaLike) {
                ISearchCriteriaLike searchCriteriaLike = (ISearchCriteriaLike) criteria;
                if(searchCriteriaLike.isCaseInsensitive()) {
                    hibernateCriteria.add(Restrictions.ilike(searchCriteriaLike.getFieldName(), searchCriteriaLike.getRequiredValue()));
                } else {
                    hibernateCriteria.add(Restrictions.like(searchCriteriaLike.getFieldName(), searchCriteriaLike.getRequiredValue()));
                }
            } else if(criteria instanceof ISearchCriteriaBetween) {
                ISearchCriteriaBetween searchCriteriaBetween = (ISearchCriteriaBetween) criteria;
                hibernateCriteria.add(Restrictions.between(searchCriteriaBetween.getFieldName(),searchCriteriaBetween.getMin(), searchCriteriaBetween.getMax()));
            }
        }

        List<Employee> matchingEmployees = hibernateCriteria.list();

        return matchingEmployees;
    }

    public void Test(ISearchCriteriaLike like) {

    }
}