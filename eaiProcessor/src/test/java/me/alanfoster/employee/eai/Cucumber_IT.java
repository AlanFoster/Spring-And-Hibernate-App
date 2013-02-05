package me.alanfoster.employee.eai;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import me.alanfoster.employee.service.FlatEmployee;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Sets up the test runner for the Cucumber JVM Integration/Behaviour tests
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@RunWith(cucumber.api.junit.Cucumber.class)
@cucumber.api.junit.Cucumber.Options(
        tags = "~@Ignore",
        glue = {"me"},
        format = {"pretty", "html:target/cucumber"}
)
public class Cucumber_IT {

}