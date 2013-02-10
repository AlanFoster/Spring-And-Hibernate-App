package me.alanfoster.employee.eai;

import me.alanfoster.services.employee.models.Employee;
import me.alanfoster.services.employee.models.Job;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * A basic helper class to convert to/from Employee/Elements
 * If things the objects involved were to get more complex, it
 * would be a good idea to make use of a library like xstream etc
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class EmployeeElementHelper {
     private EmployeeElementHelper() { }

    /**
     * Returns a list of Element as List of Employee
     * @param elements The elements to convert
     * @return The new list of employees, in the same sort as the List type provided
     */
    public static List<Employee> getElementAsEmployee(List<Element> elements) {
        List<Employee> employeeList = new LinkedList<Employee>();

        for(Element element : elements) {
            Employee employee = getElementAsEmployee(element);
            employeeList.add(employee);
        }
        return employeeList;
    }

    /**
     * Returns an element as an employee
     * @param element The the element to convert
     * @return The new employee object
     */
    public static Employee getElementAsEmployee(Element element) {
        Employee employee = new Employee();
        employee.setId(getNodeValueAsInteger(element, "./id"));
        employee.setFirstName(getNodeValueAsText(element, "./firstName"));
        employee.setSecondName(getNodeValueAsText(element, "./secondName"));
        employee.setDeskId(getNodeValueAsInteger(element, "./deskId"));

        Job job = new Job();
        job.setJobId(getNodeValueAsInteger(element, "./job/jobId"));
        job.setJobTitle(getNodeValueAsText(element, ".//job/jobTitle"));

        employee.setJob(job);

        return employee;
    }

    /**
     * Converts an employee to an element
     * @param employee The employee object
     * @return The new element object (Not attached to a root element/Document)
     */
    public static Element getEmployeeAsElement(Employee employee) {
        Element employeeElement = DocumentHelper.createElement("employee");

        employeeElement.addElement("id").setText(String.valueOf(employee.getId()));
        employeeElement.addElement("firstName").setText(employee.getFirstName());
        employeeElement.addElement("secondName").setText(employee.getSecondName());
        employeeElement.addElement("deskId").setText(String.valueOf(employee.getDeskId()));

        Element jobElement = employeeElement.addElement("job");
        jobElement.addElement("jobId").setText(String.valueOf(employee.getJob().getJobId()));
        jobElement.addElement("jobTitle").setText(employee.getJob().getJobTitle());

        return employeeElement;
    }


    /**
     * Get the node value as a string
     * @param parent The parent node
     * @param nodeName The node to get the text of
     * @return The text in string format
     */
    private static String getNodeValueAsText(Element parent, String nodeName) {
        Node selectedNode = parent.selectSingleNode(nodeName);
        // Handle null cases
        return selectedNode == null ? null : selectedNode.getText();
    }

    /**
     * Get the node value as an integer.
     * Note this method intenrally uses Integer.parseInt, IE normal parsing exceptions apply
     * @param parent The parent node
     * @param nodeName The node to get the text of
     * @return The text in integer format
     */
    private static Integer getNodeValueAsInteger(Element parent, String nodeName) {
        String stringValue = getNodeValueAsText(parent, nodeName);
        // Handle null cases
        return stringValue == null || stringValue.isEmpty() || stringValue.equals("null") ? null : Integer.parseInt(stringValue);
    }
}
