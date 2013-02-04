package me.alanfoster.application.controllers.help;

import me.alanfoster.application.controllers.help.config.HelpModelConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@Controller
public class HelpController {
    /**
     * Basic SLF4J logger
     * @See {@link http://www.slf4j.org/}
     */
    private static final Logger logger = LoggerFactory.getLogger(HelpController.class);

    @RequestMapping("/help")
    public String help() {
        logger.info("Received Request for /help");
        return HelpModelConfig.Help.getModelName();
    }
}
