package me.alanfoster.employee.service;

import me.alanfoster.services.employee.models.Employee;
import me.alanfoster.services.employee.models.Job;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class EmployeeAssert {
    public static void assertEqual(FlatEmployee expectedEmployee, Employee actualEmployee) {
        assertEqual(expectedEmployee.getAsEmployee(), actualEmployee);
    }

    public static void assertEqual(Employee expectedEmployee, Employee actualEmployee) {
        assertEqualEmployee(expectedEmployee, actualEmployee);
        assertEqualJob(expectedEmployee, actualEmployee);
    }

    public static void assertEqualEmployee(Employee expectedEmployee, Employee actualEmployee) {
        assertNotNull("The retrieved employee should not be null", actualEmployee);
        assertEquals("The employee id should be as expected", expectedEmployee.getId(), actualEmployee.getId());
        assertEquals("The first name should be as expected", expectedEmployee.getFirstName(), actualEmployee.getFirstName());
        assertEquals("The second name should be as expected", expectedEmployee.getSecondName(), actualEmployee.getSecondName());
        assertEquals("The desk id should be as expected", expectedEmployee.getDeskId(), actualEmployee.getDeskId());
    }

    public static void assertEqualJob(Employee expectedEmployee, Employee actualEmployee) {
        Job expectedJob = expectedEmployee.getJob();
        Job actualJob = actualEmployee.getJob();
        assertEquals("The job id should be as expected", expectedJob.getJobId(), actualJob.getJobId());
        assertEquals("The job title should be as expected", expectedJob.getJobTitle(), actualJob.getJobTitle());
    }
}
