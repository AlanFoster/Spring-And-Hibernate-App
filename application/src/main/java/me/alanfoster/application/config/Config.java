package me.alanfoster.application.config;

import java.lang.Override;
import java.lang.String;

/**
 * Stores all of the config related to this application
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public final class Config {
    private Config() {}

    /**
     * Stores all of the pages that are used in this application.
     * It is recommended to potentially split this up into smaller enums/constants as the application grows
     *
     * @author Alan Foster
     * @version 1.0.0-SNAPSHOT
     */
    public enum Pages {
        Employees("/employees");

        private String value;
        Pages(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
