package me.alanfoster.application.controllers.notification.config;

import me.alanfoster.application.controllers.core.config.RequestMappingConfig;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public enum NotificationRequestMappingConfig implements RequestMappingConfig {
    Notification("/formResult");

    private String value;

    NotificationRequestMappingConfig(String value) {
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