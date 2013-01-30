package me.alanfoster.application.controllers.notification.config;

import me.alanfoster.application.controllers.core.config.ModelConfig;

/**
 * Stores all of the pages that are used in this application.
 * It is recommended to potentially split this up into smaller enums/constants as the application grows
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public enum NotificationModelConfig implements ModelConfig {
    Notification("notification");

    private String value;

    NotificationModelConfig(String value) {
        this.value = value;
    }

    @Override
    public String getModelName() {
        return this.value;
    }
}
