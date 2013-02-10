package me.alanfoster.application.controllers.employee.config;

import me.alanfoster.application.controllers.core.config.RequestMappingConfig;

/**
 * Enum to hold the request mappings for the employee related operations
 * {@inheritDoc}
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public enum EmployeeRequestMappingConfig implements RequestMappingConfig {
    /**
     * The request mapping for employee search
     */
    Search("/employees/search"),
    /**
     * The request mappign for employee add
     */
    Add("/employees/add"),
    /**
     * The request mapping for edit employee
     */
    Edit("/employees/edit/{employeeId}"),
    /**
     * Teh request mappign to delete an employee
     */
    Delete("/employees/delete/{employeeId}");

    /**
     * The request mapping value
     */
    private String value;

    /**
     * Create a new request mapping config option
     * @param value The request mapping url
     */
    private EmployeeRequestMappingConfig(final String value) {
        this.value = value;
    }

    @Override
    public String getRequestMapping() {
        return value;
    }

    @Override
    public String getRedirectRequestMapping() {
        return "redirect:" + value;
    }
}
