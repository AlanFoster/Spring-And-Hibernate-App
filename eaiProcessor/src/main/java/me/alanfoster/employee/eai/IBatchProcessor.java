package me.alanfoster.employee.eai;

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
public interface IBatchProcessor {
    /**
     * Polls the required input folder.
     * The input folder to check will be set by the setInputDropBox method within this interface
     */
    void poll();

    /**
     * Set the input dropbox location
     * @param input The string location
     */
    void setInputDropBox(String input);

    /**
     * Set the output (Result) dropbox location
     * @param output The string location
     */
    void setOutputDropBox(String output);

}
