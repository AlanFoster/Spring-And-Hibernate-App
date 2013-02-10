package me.alanfoster.employee.eai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The main entry poitn for kicking off the batch processor from command line.
 * <br />
 * The batch processor can be called with the following command
 * <pre>
 * {@code
 *     mvn exec:java -Dexec.mainClass="me.alanfoster.employee.eai.Main"
 * }
 * </pre>
 * <strong>Note this batch processor relies on the external hosted employee webservice
 * to be running</strong>
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class Main {
    /**
     * Basic SLF4J logger
     * @See {@link http://www.slf4j.org/}
     */
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Main entry point
     * @param args None applicable
     */
    public static void main(String[] args) {
        logger.info("==================================================");
        logger.info("==                                              ==");
        logger.info("==            EAI EMS Batch Processor           ==");
        logger.info("==                  1.0.0-SNAPSHOT              ==");
        logger.info("==                                              ==");
        logger.info("==================================================");
        // Instantiate the spring application context
        ApplicationContext context = new ClassPathXmlApplicationContext("eai-context.xml");
        // Ask spring for our batch processor so we can kick it off
        IBatchProcessor batchProcessor = (IBatchProcessor) context.getBean("batchProcessor");
        // Begin one off poll
        batchProcessor.poll();

        logger.info("Batch finished");
        logger.info("==================================================");
    }
}
