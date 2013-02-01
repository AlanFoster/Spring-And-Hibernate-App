package me.alanfoster.employee.webservice;

import me.alanfoster.services.employee.models.impl.Employee;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * This webservice offers the functionality to directly manipulate employees that are persisted.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see me.alanfoster.services.employee.models.impl.Employee
 */
@WebService(
        name = "EmployeeWebservice",
        targetNamespace = "http://www.alanfoster.me/")
@SOAPBinding(
        style = SOAPBinding.Style.RPC,
        use = SOAPBinding.Use.LITERAL)
public interface IEmployeeWebservice {
    /* Create Operations */

    /**
     * Creates a new employee
     *
     * @param employee The employee on the system to add
     * @return The new unique id associated with this employee (The employee Id)
     */
    Integer createEmployee(@WebParam(name = "employee") Employee employee);

    /* Read Operations */

    /**
     * Get an existing Employee from the given key
     *
     * @param employeeId The employeeId key that is assigned to each saved Employee object
     * @return The required Employee Object
     */
    Employee getEmployee(@WebParam(name = "employeeId") Integer employeeId);

    /**
     * Get all of the available Employees that have been persisted
     *
     * @return A list of Employee objects.
     *         Note, this will be an onordered list!
     */
  //  @XmlJavaTypeAdapter(EmployeeListAdapter.class)
    List<Employee> getAllEmployees();

    /* Update Operations */

    /**
     * updates an existing Employee object
     *
     * @param employee The employee to update within the system
     */
    void updateEmployee(@WebParam(name = "object") Employee employee);

    /* Delete Operations */

    /**
     * Delete an existing employee from the system using the Employee object.
     *
     * @param employee The employee to remove from the system
     * @see #deleteEmployeeByEmployeeId(Integer)
     */
    void deleteEmployee(@WebParam(name = "employee") Employee employee);

    /**
     * Delete an employee based on the employee id key
     *
     * @param employeeId The employeeId key that is assigned to each saved Employee object
     */
    void deleteEmployeeByEmployeeId(@WebParam(name = "employeeId") Integer employeeId);
}
