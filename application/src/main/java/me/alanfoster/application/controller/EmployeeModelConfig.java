package me.alanfoster.application.controller;

import java.lang.String;

/**
 * Stores all of the config related to this application
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public final class EmployeeModelConfig {
    private EmployeeModelConfig() {}

    /**
     * Stores all of the pages that are used in this application.
     * It is recommended to potentially split this up into smaller enums/constants as the application grows
     *
     * @author Alan Foster
     * @version 1.0.0-SNAPSHOT
     */
    public enum EmployeeModel implements ModelConfig {
        Index("employee.index"),
        Add("employee.add"),
        Edit("employee.edit"),
        Search("employee.search");

        private String value;
        EmployeeModel(String value) {
            this.value = value;
        }

        @Override
        public String getModel() {
            return this.value;
        }
    }

    // TODO Add the Employee restful pages
}
