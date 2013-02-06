package me.alanfoster.employee.eai;

import me.alanfoster.employee.webservice.IEmployeeWebservice;
import me.alanfoster.services.employee.models.impl.Employee;
import me.alanfoster.services.employee.models.impl.Job;
import org.apache.cxf.helpers.FileUtils;
import org.dom4j.*;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.jdbc.odbc.ee.ObjectPool;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
        File[] files = new File(dropBoxInput).listFiles();
        // Pick up all files and process them
        for (File file : files) {
            processFile(file);
        }
    }

    /**
     * Proceesses a single file - IE parses it, calls the webservice with it and creates the response file
     * @param file The file to process
     */
    public void processFile(File file) {
        String fileContent = FileUtils.getStringFromFile(file).trim();
        String fileName = file.getName();

        Document document = null;
        try {
            document = getStringAsDocument(fileContent);
        } catch (DocumentException documentException) {
            logger.error("Couldn't successfully parse the xml content for {}", new Object[]{ fileName });
            return;
        }

        // The tuple of success/failed employees
        Tuple<List<Employee>, List<Employee>> employeeTuple = null;

        try {
            employeeTuple = processDocument(document);
        } catch(Exception e) {
            logger.error("Unsuccesfully processed document", e);
            // create a fail file
            //Document response = createFailureResonse();
            // write it
            return;
        }

        // Create and write success file
        Document response = createSuccessResponse(employeeTuple);
        writeResponse(fileName, response);
    }

    /**
     * Create the fail response
     * @return The failure response
     */
    public Document createFailureResponse() {
        return createResponse(false, null);
    }

    /**
     * Create the success response
     * @param employeeTuple The employee tuple to show the results of in the file
     * @return The document response
     */
    public Document createSuccessResponse(Tuple<List<Employee>, List<Employee>> employeeTuple) {
        return createResponse(true, employeeTuple);
    }

    /**
     * Creates a basic response, consisting of a state and a list of success and unsuccessful employees
     * @param success True or false, this will afffect the stateElement that is outputted
     * @param employeeTuple The tuple of employee, where Item1 is success and Item is failed employees
     * @return The document response
     */
    private Document createResponse(boolean success, Tuple<List<Employee>, List<Employee>> employeeTuple) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("BatchProcessorResponse");

        Element stateElement = root.addElement("state").addText(success ? "success" : "failure");

        // Add the employees
        if(employeeTuple != null) {
            List<Employee> succesfulEmployees = employeeTuple.getItemOne();
            Element successfulEmployeesElement = root.addElement("succesfulEmployees");
            addEmployeesToElement(successfulEmployeesElement, succesfulEmployees);

            List<Employee> failedEmployees = employeeTuple.getItemOne();
            Element failedEmployeesElement = root.addElement("failedEmployees");
            addEmployeesToElement(failedEmployeesElement, failedEmployees);
        }

        return document;
    }


    /**
     * Adds a list of employees to a parent element
     * @param parent The parent element to add the employees to
     * @param employees The list of employees
     */
    public void addEmployeesToElement(Element parent, List<Employee> employees) {
        for(Employee employee : employees) {
            parent.add(getEmployeeAsElement(employee));
        }
    }

    /**
     * Writes a document ot the required file name (Within the output dropbox)
     * @param fileName The file name to create the response under (This should include the file extension)
     * @param response The document response to write in the file
      */
    public void writeResponse(String fileName, Document response) {
        try {
            String responseFileName = fileName;
            File dropBoxOutputFolder = new File(dropBoxOutput);
            if(!dropBoxOutputFolder.exists()) {
                dropBoxOutputFolder.mkdirs();
            }

            File responseFile = new File(dropBoxOutputFolder.getAbsolutePath() + File.separator + responseFileName);
            XMLWriter writer = new XMLWriter(new FileWriter(responseFile), OutputFormat.createPrettyPrint());
            writer.write(response);
            writer.close();

        } catch (IOException e) {
            logger.error("Couldn't successfully write the file {} to {}", new Object[] { response.asXML(), fileName  }, e);
        }
    }

    /**
     * Processes a document and returns all of the employees that have failed adding to the service
     * @param document The document to process
     * @return A tuple containing the list of succeeded and failed employees.
     *          Both of these list will not be null
     * @throws Exception Checked exception for when the XSLT could not perform correctly
     */
    public Tuple<List<Employee>, List<Employee>> processDocument(Document document) throws Exception {
        // Transform the xml into a more easily readable format
        Document transformedDocument = getLegacyAsNew(document);

        List<Employee> succeededEmployees = new ArrayList<Employee>();
        List<Employee> failedEmployees = new ArrayList<Employee>();

        List<Element> employeeNodes = transformedDocument.selectNodes("/employees/employee");
        for (Element employeeElement : employeeNodes) {
            Employee employee = getElementAsEmployee(employeeElement);
            try {
                employeeWebservice.createEmployee(employee);
            } catch(Exception e) {
                logger.error("Failed adding employee {}", new Object[] { employee }, e);
                failedEmployees.add(employee);
                continue;
            }
            // Add it to the list of successful employees
            succeededEmployees.add(employee);
        }

        Tuple<List<Employee>, List<Employee>> response =  new Tuple<List<Employee>, List<Employee>>(succeededEmployees, failedEmployees);
        return response;
    }

    /**
     * Returns an element as an employee
     * @param element The the element to convert
     * @return The new employee object
     */
    public Employee getElementAsEmployee(Element element) {
        Employee employee = new Employee();
        employee.setFirstName(getNodeValueAsText(element, "./firstName"));
        employee.setSecondName(getNodeValueAsText(element, "./secondName"));
        employee.setDeskId(getNodeValueAsInteger(element, "./deskId"));

        Job job = new Job();
        job.setJobId(getNodeValueAsInteger(element, "./job/jobId"));
        job.setJobTitle(getNodeValueAsText(element, ".//job/jobTitle"));

        employee.setJob(job);

        return employee;
    }

    /**
     * Converts an employee to an element
     * @param employee The employee object
     * @return The new element object (Not attached to a root element/Document)
     */
    public Element getEmployeeAsElement(Employee employee) {
        Element employeeElement = DocumentHelper.createElement("employee");

        employeeElement.addElement("id").setText(String.valueOf(employee.getId()));
        employeeElement.addElement("firstName").setText(employee.getFirstName());
        employeeElement.addElement("secondName").setText(employee.getFirstName());
        employeeElement.addElement("deskId").setText(String.valueOf(employee.getDeskId()));

        Element jobElement = employeeElement.addElement("job");
        jobElement.addElement("jobId").setText(String.valueOf(employee.getJob().getJobId()));
        jobElement.addElement("jobTitle").setText(employee.getJob().getJobTitle());

        return employeeElement;
    }


    /**
     * Get the node value as a string
     * @param parent The parent node
     * @param nodeName The node to get the text of
     * @return The text in string format
     */
    public String getNodeValueAsText(Element parent, String nodeName) {
        return parent.selectSingleNode(nodeName).getText();
    }

    /**
     * Get the node value as an integer.
     * Note this method intenrally uses Integer.parseInt, IE normal parsing exceptions apply
     * @param parent The parent node
     * @param nodeName The node to get the text of
     * @return The text in integer format
     */
    public Integer getNodeValueAsInteger(Element parent, String nodeName) {
        return Integer.parseInt(getNodeValueAsText(parent, nodeName));
    }


    /**
     * Get an XML string as a Document
     * @param xml The xml to parse
     * @return The Document
     * @throws DocumentException Parsing may not be possible, see DocumentException documentation
     */
    public Document getStringAsDocument(String xml) throws DocumentException {
        return DocumentHelper.parseText(xml);
    }

    /**
     * Converst an old document into the new format
     * @param document The old document
     * @return A new sanitised output
     * @throws Exception If the transform was not successful
     */
    public Document getLegacyAsNew(Document document) throws Exception {
        String xsltLocation = "/transformInput.xslt";
        return applyTransform(document, xsltLocation);
    }

    /**
     * Applies an xslt ot the given document at the xsltLocation
     * @param document The doucment to apply the xslt transform on
     * @param xsltLocation The xslt location
     *                     Note :: This method uses getResourcesAsStream, so it should be relative to the classpath
     * @return The newly transformed document
     * @throws Exception If the transform was not successful
     */
    public Document applyTransform(Document document, String xsltLocation) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        InputStream stream = BatchProcessor.class.getResourceAsStream(xsltLocation);
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(stream));

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
