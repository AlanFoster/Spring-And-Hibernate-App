package me.alanfoster.employee.eai;

import me.alanfoster.employee.webservice.IEmployeeWebservice;

/**
 * Interface for a basic EAI (Enterprise Application Integration) pattern
 * Which picks up a file from a drop box, interacts with a webservice
 * then drops it into the result folder.

 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface IBatchProcessor {
    /**
     * Polls the required input folder.
     * The input folder to check will be set by the setDropBoxInput method within this interface
     */
    void poll();

    /**
     * Set the input dropbox location
     * @param input The string location
     */
    void setDropBoxInput(final String input);

    /**
     * Set the output (Result) dropbox location
     * @param output The string location
     */
    void setDropBoxOutput(final String output);

    /**
     * Set the webservice instance that the batch processor should interact with
     * @param employeeWebservice The webservice to interact with
     */
    void setEmployeeWebservice(final IEmployeeWebservice employeeWebservice);
}
