Spring and Hibernate Test
=========================

This is a basic 3-tiered application written in Java, which makes use of Hibernate and Spring.
The application itself is a basic Employee Management System (EMS) which allows users to perform
basic CRUD (Create, Read, Update, Delete) operations

Getting Started
---------------

#####Database#####

This application works with MySql by default with a database name of 'employee' - But is configurable by changing jdbc.properties

The install scripts can be found at

    /employeeService/src/main/resources/sql/db-schema.sql

And the rollback script can be found at

    /employeeService/src/main/resources/sql/rollback.sql


#####Installing/Building####

This project uses [Maven](http://maven.apache.org/) for all of its building and dependency management

In order to install the required dependencies into your local maven repository you can run the following command
from the root directory of your project (Once Maven is installed)

    mvn install
    
When running this install, it should be noted that the tests will automatically run.
If you don't wish to automatically run these tests then you can use

    mvn -Dmaven.test.skip=true install

This application also makes use of a Jetty maven plugin in order to host the Webservice and Application;
This will be the quickest way to get the application up and running - However, as a simple war is created during
the mvn install process it's also very easy to deploy into different containers!

This application is targeted to run under java 1.6

This application is also targeted to run under modern browsers - Firefox/Chrome are best suited for this application

Please also read the dependencies markdown file when developing too :)

Hosting the Webservice
----------------------

Running the webservice within jetty is extremely easy.

Open up a command terminal from the root directory and execute the following commands

    cd hostedWebservice
    mvn jetty:run

You should eventually see the following appear

	[INFO] Started Jetty Server
	[INFO] Starting scanner at interval of 1 seconds.

The webservice will be hosted on port 8090 - which is configurable with maven properties
You can see the main cxf operations at the root directory here :

    http://localhost:8090/

And the webservice wsdl here :

    http://localhost:8090/EmployeeWebservice?wsdl

Note - As part of the maven build process I have set it up so that the wsdl is generated automatically
This can be seen at the following location (relative to the root directory)

     /webservice/target/generated/wsdl/EmployeeWebservice.wsdl
 
	
Running the Web Application 
----------------------------

To run the main web application you can do so with the following commands

    cd application
    mvn jetty:run

After deployment is successful you can visit the application at [http://localhost:8080/](http://localhost:8080)

Tracking Issues
---------------

#####Logs####

This application makes use of the SLF4J framework; So it's business as usual when wanting to change the config levels

Extra :: This application *also* makes use of Spring's AOP to add additional logging (at debug level)

This will output some of the more important information for logging, such as method name, arguments, log time,
and any exceptions

Example spring bean config used :

```xml
    <!-- Use Spring AOP to bind logging to all of our services classes -->
    <bean id="customizableTraceInterceptor" class="org.springframework.aop.interceptor.CustomizableTraceInterceptor">
        <property name="enterMessage" value="$[targetClassShortName] :: Entering $[methodName]($[arguments])"/>
        <property name="exitMessage" value="$[targetClassShortName] :: Leaving $[methodName](): $[returnValue] :: Invocation Time $[invocationTime] ms"/>
        <property name="exceptionMessage" value="$[targetClassShortName] :: Exception thrown for $[methodName] with the following exception $[exception]" />
    </bean>

    <aop:config>
        <!-- Pointcut with any method defined in the services package -->
        <aop:advisor advice-ref="customizableTraceInterceptor" pointcut="execution(public * me.alanfoster.services..*.*(..))"/>
    </aop:config>

```

This will output something similar to the following when the logging levels are changed - it's very useful to have!


    [                          main] CustomizableTraceInterceptor   DEBUG EmployeeService :: Entering create(Employee{id=1, firstName='John', secondName='Smyth', job=Job{jobId=1, jobTitle='HR'}, deskId=1})
    ....
    [                          main] CustomizableTraceInterceptor   DEBUG EmployeeService :: Leaving create(): 1 :: Invocation Time 24 ms


#####Debugging####

In order to take full advantage of your IDE you can start the jetty instance in debug mode using the following

    mvnDebug jetty:run

After running this command you should see the following output

    Preparing to Execute Maven in Debug Mode
    Listening for transport dt_socket at address: 8000
    
This means that the application will now be waiting for a remote debugger to attach on port 8000. 
It is only when the remote debugger has attached will the application continue to deploy

Note; You can also inspect the SLF4J log files which will be outputted to `/target/logs/*.log`

Development
------------

- **IntelliJ**
    For an enojoyable developer experience is it recommended to use Intellij :)
    IntelliJ has great facets for Hibernate and Spring, so make sure you wire them up! (Tutorial Included in the Useful links section)

- **Eclipse**
    You can of course use Eclipse too, but you'll probably want to make life easier by setting up the facets manually.
   
    Some useful plugins for Eclipse are 
	     
    - *m2e* - Maven Integration plugin
	- *WTP* - Supports automatically handling of web facets
		 
You can also use the following commands to get you started, YMMV

    mvn idea:idea

Or

	mvn eclipse:eclipse

Test Suite
-------

This project makes use of [CucumberJVM](https://github.com/cucumber/cucumber-jvm) in order to run Behaviour tests written in Gherkin syntax.

Currently these tests are hooked up to run during the maven verify/integration-tests stage. Currently these tests also log during maven install.

To see the results of the tests, they can be found under

    [directory]/target/cucumber
	
For example, the EAI testing will be under 

    eaiProcessor\target\cucumber
	
The application itself also has some basic Selenium tests which will take screenshots during the steps, these have also been written with Gherkin

As far as possible I used integration testing; So I have used a real in memory database which executes the same install and rollback scripts 
that would be ran on a production server, which has worked out really great and saved a lot of potential issues

Useful Links
------------
- [Maven](http://maven.apache.org/)
- [Integration with Eclipse](http://viralpatel.net/blogs/generate-dynamic-web-project-maven-eclipse-wtp)
- [Intellij Hibernate JPA Facet](http://www.jetbrains.com/idea/features/jpa_hibernate.html)