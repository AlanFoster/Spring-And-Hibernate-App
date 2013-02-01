package me.alanfoster.employee.webservice;

import me.alanfoster.services.employee.models.IEmployee;
import me.alanfoster.services.employee.models.impl.Employee;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@WebService(
        endpointInterface = "me.alanfoster.employee.webservice.IEmployeeWebservice",
        serviceName = "EmployeeWebService",
        portName = "EmployeeWebservicePort")
public class EmployeeWebservice implements IEmployeeWebservice {
    // @AutoWired
    //  private IEmployeeService employeeService;

    @Override
    public final Integer createEmployee(@WebParam(name = "employee") final Employee employee) {
        //employeeService.create(employee);
        return 0;
    }

    @Override
    public final Employee get(@WebParam(name = "employeeId") final Integer employeeId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public final List<Employee> getAllEmployees() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public final void updateEmployee(@WebParam(name = "object") final Employee employee) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public final void deleteEmployee(@WebParam(name = "employee") final Employee employee) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteEmployeeByEmployeeId(@WebParam(name = "employeeId") final Integer employeeId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
