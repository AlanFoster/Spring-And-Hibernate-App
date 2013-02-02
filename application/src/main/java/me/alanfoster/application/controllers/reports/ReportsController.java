package me.alanfoster.application.controllers.reports;

import me.alanfoster.application.controllers.reports.config.ReportsModelConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller is used to for managing Reports.
 * This controller is more devised to test the ability of using
 * Controllers that return pure JSON when interacted with
 * Which can then be used for ajax applications
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@Controller
public class ReportsController {
    @RequestMapping(value = "/reports")
    public String searchEmployee() {
        return ReportsModelConfig.Reports.getModelName();
    }
}
