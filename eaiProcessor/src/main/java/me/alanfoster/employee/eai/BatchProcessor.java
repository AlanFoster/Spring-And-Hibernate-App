package me.alanfoster.employee.eai;

import me.alanfoster.employee.webservice.IEmployeeWebservice;
import me.alanfoster.services.employee.models.impl.Employee;
import me.alanfoster.services.employee.models.impl.Job;
import org.apache.cxf.helpers.FileUtils;
import org.dom4j.*;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.List;

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
     *
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
     * The instance of the IEmployeeWebservice to interact with
     */
    private IEmployeeWebservice employeeWebservice;

    /**
     * {@inheritDoc}
     */
    @Override
    public void poll() {
        //   File file = FileUtils.getStringFromFile(dropBoxInput)
        File[] files = new File(dropBoxInput).listFiles();
        for (File file : files) {
            String fileContent = FileUtils.getStringFromFile(file).trim();
            Document legacyDocument = null;

            try {
                legacyDocument = getStringAsDocument(fileContent);
            } catch (DocumentException documentException) {
                logger.error("Couldn't successfully parse the xml content for {}", new Object[]{file.getName()});
                continue;
            }

            // Transform the xml
            Document transformedDocument = null;
            try {
                transformedDocument = getLegacyAsNew(legacyDocument);
            } catch (Exception e) {
                logger.error("Couldn't successfully transform the xml content for {}", new Object[]{file.getName()});
                continue;
            }

            List<Element> employeeNodes = transformedDocument.selectNodes("/employees/employee");

            for (Element employeeElement : employeeNodes) {
                Employee employee = getElementAsEmployee(employeeElement);
                employeeWebservice.createEmployee(employee);
            }
        }
    }

    public Employee getElementAsEmployee(Element element) {
        Employee employee = new Employee();
        employee.setFirstName(getNodeValueAsText(element, "firstName"));
        employee.setSecondName(getNodeValueAsText(element, "setSecondName"));
        employee.setDeskId(getNodeValueAsInteger(element, "deskId"));

        Job job = new Job();
        job.setJobId(getNodeValueAsInteger(element, "jobId"));
        job.setJobTitle(getNodeValueAsText(element, "jobTitle"));

        employee.setJob(job);

        return employee;
    }

    public String getNodeValueAsText(Element parent, String nodeName) {
        return parent.selectSingleNode(nodeName).getText();
    }

    public Integer getNodeValueAsInteger(Element parent, String nodeName) {
        return Integer.parseInt(getNodeValueAsText(parent, nodeName));
    }


    public Document getStringAsDocument(String xml) throws DocumentException {
        return DocumentHelper.parseText(xml);
    }

    public Document getLegacyAsNew(Document document) throws Exception {
        String xsltLocation = "transformInput.xslt";
        return applyTransform(document, xsltLocation);
    }

    private Document applyTransform(Document document, String xsltLocation) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(xsltLocation));

        DocumentSource source = new DocumentSource(document);
        DocumentResult result = new DocumentResult();
        transformer.transform(source, result);

        return result.getDocument();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDropBoxInput(String dropBoxInput) {
        this.dropBoxInput = dropBoxInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDropBoxOutput(String dropBoxOutput) {
        this.dropBoxOutput = dropBoxOutput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEmployeeWebservice(IEmployeeWebservice employeeWebservice) {
        this.employeeWebservice = employeeWebservice;
    }
}
