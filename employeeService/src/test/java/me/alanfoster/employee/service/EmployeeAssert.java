package me.alanfoster.employee.service;

import me.alanfoster.services.employee.models.IEmployee;
import me.alanfoster.services.employee.models.impl.Job;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class EmployeeAssert {
    public static void assertEqual(FlatEmployee expectedEmployee, IEmployee actualEmployee) {
        assertEqual(expectedEmployee.getAsEmployee(), actualEmployee);
    }

    public static void assertEqual(IEmployee expectedEmployee, IEmployee actualEmployee) {
        assertEqualEmployee(expectedEmployee, actualEmployee);
        assertEqualJob(expectedEmployee, actualEmployee);
    }

    public static void assertEqualEmployee(IEmployee expectedEmployee, IEmployee actualEmployee) {
        assertNotNull("The retrieved employee should not be null", actualEmployee);
        assertEquals("The employee id should be as expected", expectedEmployee.getId(), actualEmployee.getId());
        assertEquals("The first name should be as expected", expectedEmployee.getFirstName(), actualEmployee.getFirstName());
        assertEquals("The second name should be as expected", expectedEmployee.getSecondName(), actualEmployee.getSecondName());
        assertEquals("The desk id should be as expected", expectedEmployee.getDeskId(), actualEmployee.getDeskId());
    }

    public static void assertEqualJob(IEmployee expectedEmployee, IEmployee actualEmployee) {
        Job expectedJob = expectedEmployee.getJob();
        Job actualJob = actualEmployee.getJob();
        assertEquals("The job id should be as expected", expectedJob.getJobId(), actualJob.getJobId());
        assertEquals("The job title should be as expected", expectedJob.getJobTitle(), actualJob.getJobTitle());
    }
}
