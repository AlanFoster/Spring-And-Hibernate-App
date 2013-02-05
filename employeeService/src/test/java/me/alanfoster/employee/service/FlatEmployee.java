package me.alanfoster.employee.service;

import me.alanfoster.services.employee.models.IEmployee;
import me.alanfoster.services.employee.models.impl.Employee;
import me.alanfoster.services.employee.models.impl.Job;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a basic model for the representing an Employee during cucumber tests
 * Note, this is a <em>flat</em> representation of an employee and not the actual 'IEmployee' object
 * This object is useful as it provides a 1 to 1 mapping of cucumber's datatables
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see me.alanfoster.services.employee.models.IEmployee
 */
public class FlatEmployee {
    private Integer id;
    private String firstName;
    private String secondName;
    private Integer deskId;

    // Job details
    private Integer jobId;
    private String jobTitle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getDeskId() {
        return deskId;
    }

    public void setDeskId(Integer deskId) {
        this.deskId = deskId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    /**
     * Instance helper method that internally calls the static method getEmployeeDataTableAsIEmployee
     *
     * @return An IEmployee instance with equivalent data as the flat data structure
     */
    public IEmployee getAsEmployee() {
        return FlatEmployee.getEmployeeDataTableAsIEmployee(this);
    }

    /**
     * Converts the flat structure of a cucumber style employee datatable to an actual implementation of IEmployee.
     *
     * @return An IEmployee instance with equivalent data as the flat data structure
     */
    public static IEmployee getEmployeeDataTableAsIEmployee(FlatEmployee employeeDataTable) {
        IEmployee employee = new Employee();
        employee.setId(employeeDataTable.getId());
        employee.setFirstName(employeeDataTable.getFirstName());
        employee.setSecondName(employeeDataTable.getSecondName());
        employee.setDeskId(employeeDataTable.getDeskId());

        Job job = new Job();
        job.setJobId(employeeDataTable.getJobId());
        job.setJobTitle(employeeDataTable.getJobTitle());
        employee.setJob(job);

        return employee;
    }

    /**
     * Used to convert a list of flat employees into a list of IEmployee
     * @param employeeDataTable The datatable to convert
     * @return The list of non-flat employees
     */
    public static List<IEmployee> getEmployeeDataTableAsIEmployee(List<FlatEmployee> employeeDataTable) {
        List<IEmployee> employeeList = new ArrayList<IEmployee>(employeeDataTable.size());
        for(FlatEmployee flatEmployee : employeeDataTable) {
            employeeList.add(flatEmployee.getAsEmployee());
        }
        return employeeList;
    }


}