package me.alanfoster.application.controllers.reports.config;

import me.alanfoster.application.controllers.core.config.ModelConfig;

/**
 * This enum stores a subset of the Models which are related to the
 * Employee application.
 * <br />
 * This has been created to give a nice senseof static typing, rather
 * than dealing with a lot of Strings which can be absolutely anything.
 * Plus the String datatype is final, so you can't even start using your
 * own datatype which extends String in an attempt to acquire more
 * static typing
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public enum ReportsModelConfig implements ModelConfig {
    /**
     * The reports model
     */
    Reports("reports");
    /**
     * The model name which the view resolver can make use of
     */
    private String value;

    /**
     * Construct a new employee model config with the given model name
     *
     * @param value The model name which the view resolver can make use of
     */
    ReportsModelConfig(final String value) {
        this.value = value;
    }

    @Override
    public String getModelName() {
        return this.value;
    }
}
