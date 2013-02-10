package me.alanfoster.application.controllers.employee.config;

import me.alanfoster.application.controllers.core.config.ModelConfig;

/**
 * This enum stores a subset of the Models which are related to the
 * Employee application.
 * <br />
 * This has been created to give a nice senseof static typing, rather
 * than dealing with a lot of Strings which can be absolutely anything.
 * Plus the String datatype is final, so you can't even start using your
 * own datatype which extends String in an attempt to acquire more
 * static typing.
 * {@inheritDoc}
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public enum EmployeeModelConfig implements ModelConfig {
    /**
     * The index
     */
    Index("employee.index"),
    /**
     * Add an employee
     */
    Add("employee.add"),
    /**
     * Edit an employee
     */
    Edit("employee.edit"),
    /**
     * Search for an employee
     */
    Search("employee.search");

    /**
     * The model name which the view resolver can make use of
     */
    private String value;

    /**
     * Construct a new employee model config with the given model name
     * @param value The model name which the view resolver can make use of
     */
    private EmployeeModelConfig(final String value) {
        this.value = value;
    }

    @Override
    public String getModelName() {
        return this.value;
    }
}
