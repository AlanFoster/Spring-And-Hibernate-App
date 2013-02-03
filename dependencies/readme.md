Maven Dependency Poms
=====================

This folder/module is used for storing logically grouped dependencies that will be reused many times in this application.

The maven packaging type for these poms is 'pom', IE, they build no jar/wars, but when referenced can be used to import the jars as expected.

To use one of these dependencies you should reference it in your module like so

```xml
        <dependency>
            <groupId>me.alanfoster.employee.dependencies</groupId>
            <artifactId>DEPENDENCY_NAME</artifactId>
            <version>${project.version}</version>
            <type>pom</type>
        </dependency>
```

*Remember to update the artifactId with the specific dependecy you are after*

Testing Dependencies
--------------------

If you are using one of these core pom files for testing then you should still supply the scope element and set it to 'test' as normal

For example

```xml
        <dependency>
            <groupId>me.alanfoster.employee.dependencies</groupId>
            <artifactId>DEPENDENCY_NAME</artifactId>
            <version>${project.version}</version>
            <type>pom</type>
            <scope>test</scope>
        </dependency>
```

Why?
----

I have created this collection of 'Master Depdencies' in order to reduce the amount of duplicated dependency blocks for DRY purposes, and to avoid the potential versioning hell that always happens :)