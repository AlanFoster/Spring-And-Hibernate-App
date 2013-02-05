package me.alanfoster.employee.eai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements a basic EAI (Enterprise Application Integration) pattern
 * Which picks up a file from a drop box, interacts with a webservice
 * then drops it into the result folder.
 * <br />
 * For anything more advanced it's worth investing time looking into
 * Apache Camel
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class BatchProcessor implements IBatchProcessor {
    /**
     * Basic SLF4J logger
     * @See {@link http://www.slf4j.org/}
     */
    private static final Logger logger = LoggerFactory.getLogger(BatchProcessor.class);

    /**
     * The input foldet to poll
     */
    private String dropBoxInput;

    /**
     * The output folder to place responses
     */
    private String dropBoxOutput;

    /**
     * {@inheritDoc}
     */
    @Override
    public void poll() {
        System.out.println("Setting that stuff now");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInputDropBox(String dropBoxInput) {
        this.dropBoxInput = dropBoxInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOutputDropBox(String dropBoxOutput) {
        this.dropBoxOutput = dropBoxOutput;
    }
}
