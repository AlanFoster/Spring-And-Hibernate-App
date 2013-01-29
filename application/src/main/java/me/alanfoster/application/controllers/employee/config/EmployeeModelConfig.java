package me.alanfoster.application.controllers.employee.config;

import me.alanfoster.application.controllers.core.config.ModelConfig;

/**
 * Stores all of the pages that are used in this application.
 * It is recommended to potentially split this up into smaller enums/constants as the application grows
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public enum EmployeeModelConfig implements ModelConfig {
    Index("employee.index"),
    Add("employee.add"),
    Edit("employee.edit"),
    Search("employee.search");

    private String value;

    EmployeeModelConfig(String value) {
        this.value = value;
    }

    @Override
    public String getModel() {
        return this.value;
    }
}
