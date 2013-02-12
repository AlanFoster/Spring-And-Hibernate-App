package me.alanfoster.application;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Sets up the test runner for the Cucumber JVM behaviour tests
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
        tags = "~@Ignore",
        glue = {"me"},
        format = {"pretty", "html:target/cucumber"}
)
public class Cucumber_IT {
}