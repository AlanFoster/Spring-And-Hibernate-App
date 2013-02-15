package me.alanfoster.application.controllers.notification;

import me.alanfoster.application.controllers.notification.config
        .NotificationModelConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * A class for handling generic notifications
 * IE for showing the user success/failure of forms.
 * <p/>
 * This class will take advantage of the POST/REDIRECT/GET pattern
 * by using FlashAttributes
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@Controller
@SessionAttributes
public class NotificationController {
    /**
     * Shows basic form result notifications.
     * Note, this relies on Flash attributes being provided
     * by the original sender
     *
     * @return the required model to render
     */
    @RequestMapping(value = "/formResult")
    public final String formResultNotification() {
        return NotificationModelConfig.Notification.getModelName();
    }

    /**
     * Shows basic 404 notifications.
     * by the original sender
     *
     * @return the required model to render
     */
    @RequestMapping(value = "/404")
    public final String pageNotFoundNotification() {
        return NotificationModelConfig.Notification.getModelName();
    }

    /**
     * Shows basic exception notifications.
     * by the original sender
     *
     * @return the required model to render
     */
    @RequestMapping(value = "/error")
    public final String errorNotification() {
        return NotificationModelConfig.Notification.getModelName();
    }

}
