package me.alanfoster.application.controllers.reports.config;

import me.alanfoster.application.controllers.core.config.RequestMappingConfig;

/**
 * This enum stores a subset of the Request mapping URLs which are related
 * to the Employee application.
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
public enum ReportsRequestMappingConfig implements RequestMappingConfig {
    Reports("/reports");

    private String value;

    ReportsRequestMappingConfig(String value) {
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