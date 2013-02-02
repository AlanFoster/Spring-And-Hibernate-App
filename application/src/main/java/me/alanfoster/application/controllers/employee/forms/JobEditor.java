package me.alanfoster.application.controllers.employee.forms;

import me.alanfoster.services.employee.models.impl.Job;

import java.beans.PropertyEditorSupport;
import java.util.List;

/**
 * This class is used to allow for Spring to understand how to deal with
 * an 'exotic' type.
 * <br />
 * This class will need to be registered with the WebDataBinder instance,
 * and will be instantiated on each request.
 * <br />
 * An alternative to extending PropertyEditorSupport and the potential
 * overhead of instantiating this class multiple times is using a Generic
 * Spring Concvert  which was introduced in Spring 3.0
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see org.springframework.core.convert.converter.GenericConverter
 * @see {@link http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/validation.html#core-convert}
 */
public class JobEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String jobId) throws IllegalArgumentException {
        Job job = new Job();
        job.setJobId(Integer.valueOf(jobId));
        super.setValue(job);
    }
}