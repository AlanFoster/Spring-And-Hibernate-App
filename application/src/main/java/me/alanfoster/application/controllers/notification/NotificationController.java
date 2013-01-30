package me.alanfoster.application.controllers.notification;

import me.alanfoster.application.controllers.notification.config.NotificationModelConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A class for handling generic form response notifications
 * IE for showing the user success/failure of forms
 *
 * This class will take advantage of the POST/REDIRECT/GET pattern
 * by using FlashAttributes
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@Controller
public class NotificationController {
    @RequestMapping(value = "/formResult")
    public String searchEmployee() {
        return NotificationModelConfig.Notification.getModelName();
    }
}