package me.alanfoster.application.dao.impl;

import me.alanfoster.application.dao.IEmployeeDAO;
import me.alanfoster.application.model.IEmployee;
import me.alanfoster.application.model.impl.Employee;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@Repository
public class HibernateEmployeeDAO implements IEmployeeDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Integer create(IEmployee employee) {
        return (Integer) sessionFactory.getCurrentSession().save(employee);
    }

    @Override
    public IEmployee get(Integer key) {
        return (IEmployee) sessionFactory.getCurrentSession().get(Employee.class, key);
    }

    @Override
    public List<IEmployee> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Employee").list();
    }

    @Override
    public void update(IEmployee employee) {
        sessionFactory.getCurrentSession().update(employee);
    }

    @Override
    public void delete(IEmployee employee) {
        sessionFactory.getCurrentSession().delete(employee);
    }

    @Override
    public void delete(Integer key) {
       delete(get(key));
    }
}
