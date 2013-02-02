package me.alanfoster.application.controllers.employee.forms;

import me.alanfoster.services.employee.models.impl.Job;
import me.alanfoster.services.employee.service.IEmployeeService;

import java.beans.PropertyEditorSupport;
import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class JobEditor extends PropertyEditorSupport {
    private IEmployeeService employeeService;

    public JobEditor(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void setAsText(String jobId) throws IllegalArgumentException {
        Job job = new Job();
        job.setJobId(Integer.valueOf(jobId));
        super.setValue(job);
    }

    @Override
    public String getAsText() throws IllegalArgumentException {
        Job job = (Job) this.getValue();
        return String.valueOf(job.getJobTitle());
   }
}
