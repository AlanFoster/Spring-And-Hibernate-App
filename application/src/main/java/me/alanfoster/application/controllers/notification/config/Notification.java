package me.alanfoster.application.controllers.notification.config;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public enum Notification {
    SUCCESS("success"),
    ERROR("error");

    private String value;

    Notification(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
