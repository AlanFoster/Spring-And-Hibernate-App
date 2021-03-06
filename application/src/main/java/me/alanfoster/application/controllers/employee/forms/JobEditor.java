package me.alanfoster.application.controllers.employee.forms;

import me.alanfoster.services.employee.models.Job;
import me.alanfoster.services.employee.service.IJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyEditorSupport;

/**
 * This class is used to allow for Spring to understand how to deal with
 * an 'exotic' type.
 * <br />
 * This class will need to be registered with the WebDataBinder instance,
 * and will be instantiated on each request.
 * <br />
 * An alternative to extending PropertyEditorSupport and the potential
 * overhead of instantiating this class multiple times is using a Generic
 * Spring Converter which was introduced in Spring 3.0
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see org.springframework.core.convert.converter.GenericConverter
 * @see {@link http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/validation.html#core-convert}
 */
public class JobEditor extends PropertyEditorSupport {
    /**
     * Basic SLF4J logger.
     *
     * @See {@link http://www.slf4j.org/}
     */
    private static final Logger logger = LoggerFactory.getLogger(JobEditor.class);

    /**
     * The job service to interact with to get the required job instance.
     */
    private IJobService jobService;

    /**
     * Instantiate the JobEditor which will interact with the jobservice.
     *
     * @param jobService The job service to request a job mapping from
     */
    public JobEditor(final IJobService jobService) {
        this.jobService = jobService;
    }

    /**
     * Create a job instance from a text id
     *
     * @param stringJobId The job id as a string
     */
    @Override
    public final void setAsText(final String stringJobId) {
        logger.info("Setting as text for jobId {}", new Object[]{stringJobId});
        Integer jobId = Integer.parseInt(stringJobId);
        Job job = (jobId == -1) ? new Job() : jobService.get(jobId);
        super.setValue(job);
    }
}