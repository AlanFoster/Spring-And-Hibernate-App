package me.alanfoster.employee.webservice;

import me.alanfoster.services.employee.models.Employee;
import me.alanfoster.services.employee.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * Concrete implementation of the IEmployeeWebservice.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@WebService(
        endpointInterface =
                "me.alanfoster.employee.webservice.IEmployeeWebservice",
        serviceName = "EmployeeWebService",
        portName = "EmployeeWebservicePort")
public class EmployeeWebservice implements IEmployeeWebservice {
    /**
     * The employeee service.
     */
    @Autowired
    private IEmployeeService employeeService;

    /**
     * Mutator Method.
     *
     * @param employeeService The employee service
     */
    public final void setEmployeeService(
            final IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /********************************************************************
     * Create Operations
     ********************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    public final Integer createEmployee(
            @WebParam(name = "employee") final Employee employee) {
        return employeeService.create(employee);
    }


    /********************************************************************
     * Read Operations
     ********************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    public final Employee getEmployee(
            @WebParam(name = "employeeId") final Integer employeeId) {
        return (Employee) employeeService.get(employeeId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Employee> getAllEmployees() {
        return (List) employeeService.getAll();
    }

    /********************************************************************
     * Update Operations
     ********************************************************************/


    /**
     * {@inheritDoc}
     */
    @Override
    public final void updateEmployee(
            @WebParam(name = "object") final Employee employee) {
        employeeService.update(employee);
    }

    /********************************************************************
     * Delete Operations
     ********************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    public final void deleteEmployee(
            @WebParam(name = "employee") final Employee employee) {
        employeeService.delete(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void deleteEmployeeByEmployeeId(
            @WebParam(name = "employeeId") final Integer employeeId) {
        employeeService.delete(employeeId);
    }
}
