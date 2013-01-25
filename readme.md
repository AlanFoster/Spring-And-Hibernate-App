Spring and Hibernate Test
=========================

This is a basic application to test Spring and Hibernate.

Installing
----------

This project uses [Maven](http://maven.apache.org/) for building.
In order to install the required dependencies you can run the following command

    mvn install

Running
-------

As this application uses [Maven](http://maven.apache.org/) it is recommended to use the supplied Jetty plugin for running this application

    mvn jetty:run

However, as a simple war is created during the mvn install process it's also very easy to deploy into different containers!

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

TODO

Useful Links
------------
- [Maven](http://maven.apache.org/)
- [Integration with Eclipse](http://viralpatel.net/blogs/generate-dynamic-web-project-maven-eclipse-wtp)
- [Intellij Hibernate JPA Facet](http://www.jetbrains.com/idea/features/jpa_hibernate.html)