package me.alanfoster.application.controllers.notification.config;

/**
 * A simple enum to denote the success or failure of an operation when redirecting to the Notification page.
 * It was expected that enum may need to be expanded to hold more types of notification - so of course a success boolean of true/false isn't rigorous enough
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public enum Notification {
    /**
     * Sucess Notification
     */
    SUCCESS("success"),
    /**
     * Error Notification
     */
    ERROR("error");

    /**
     * The string value of the enum
     */
    private String value;

    /**
     * Adds a new notification object with the string value of the notificatoin
     * @param value The value of hte notification
     */
    Notification(final String value) {
        this.value = value;
    }

    /**
     * Access method to get the notification value
     * @return The notification value
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
