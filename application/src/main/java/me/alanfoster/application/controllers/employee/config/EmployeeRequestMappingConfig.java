package me.alanfoster.application.controllers.employee.config;

import me.alanfoster.application.controllers.core.config.RequestMappingConfig;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public enum EmployeeRequestMappingConfig implements RequestMappingConfig {
    Search("/employees/search"),
    Add("/employees/add"),
    Edit("/employees/edit/{employeeId}"),
    Delete("/employees/delete/{employeeId}");

    private String value;

    EmployeeRequestMappingConfig(String value) {
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