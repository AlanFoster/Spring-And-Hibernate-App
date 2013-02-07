Spring and Hibernate Test
=========================

This is a basic 3-tiered application written in Java, which makes use of Hibernate and Spring.
The application itself is a basic Employee Management System (EMS) which allows users to perform
basic CRUD (Create, Read, Update, Delete) operations

Getting Started
---------------

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
	
Hosting the Webservice
----------------------

Running the webservice within jetty is extremely easy.

Open up a command terminal from the root directory and execute the following commands

    cd application
    mvn jetty:run

You should see the following appear

	[INFO] Started Jetty Server
	[INFO] Starting scanner at interval of 1 seconds.

The webservice will be hosted on port 8090 - which is configurable with maven properties
You can see the main cxf operations at the root directory here :

And the webservice wsdl here :


	
Running the Web Application 
----------------------------

To run the main web application you can do so with the following commands

    cd application
    mvn jetty:run


After deployment is successful you can visit the application at [http://localhost:8080/](http://localhost:8080)

Debugging
---------

In order to take full advantage of your IDE you can start the jetty instance in debug mode using the following

    mvnDebug jetty:run

After running this command you should see the following output

    Preparing to Execute Maven in Debug Mode
    Listening for transport dt_socket at address: 8000
    
This means that the application will now be waiting for a remote debugger to attach on port 8000. 
It is only when the remote debugger has attached will the application continue to deploy

Development
------------

For an enojoyable developer experience is it recommended to use Intellij :)

Test Suite
-------

This project makes use of [CucumberJVM](https://github.com/cucumber/cucumber-jvm) in order to run Behaviour tests written in Gherkin syntax.

Currently these tests are hooked up to run during the maven verify/integration-tests stage.



Useful Links
------------
- [Maven](http://maven.apache.org/)
- [Integration with Eclipse](http://viralpatel.net/blogs/generate-dynamic-web-project-maven-eclipse-wtp)
- [Intellij Hibernate JPA Facet](http://www.jetbrains.com/idea/features/jpa_hibernate.html)