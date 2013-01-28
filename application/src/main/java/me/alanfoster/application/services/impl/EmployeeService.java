package me.alanfoster.application.services.impl;

import me.alanfoster.application.dao.IEmployeeDAO;
import me.alanfoster.application.model.IEmployee;
import me.alanfoster.application.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeDAO employeeDAO;

    @Transactional
    @Override
    public Integer create(IEmployee employee) {
        return employeeDAO.create(employee);
    }

    @Transactional
    @Override
    public IEmployee get(Integer key) {
        return employeeDAO.get(key);
    }

    @Transactional
    @Override
    public List<IEmployee> getAll() {
        return employeeDAO.getAll();
    }

    @Transactional
    @Override
    public void update(IEmployee employee) {
        employeeDAO.update(employee);
    }

    @Transactional
    @Override
    public void delete(IEmployee employee) {
        employeeDAO.delete(employee);
    }

    @Transactional
    @Override
    public void delete(Integer key) {
        employeeDAO.delete(key);
    }

    @Override
    public Map<Integer, String> getJobTitles() {
        // TODO This should be database driven of course
        Map<Integer, String> jobTitles = new LinkedHashMap<Integer, String>();
        jobTitles.put(1, "HR");
        jobTitles.put(2, "Operations");
        jobTitles.put(3, "Developer");
        jobTitles.put(4, "Tester");

        return jobTitles;
    }
}
