package me.alanfoster.application;

import com.google.common.base.Function;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static junit.framework.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Basic Cucumber EmployeeStepDefs
 * 
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class StepDefs {
	private static final Logger logger = LoggerFactory
			.getLogger(StepDefs.class);
	/**
	 * The base website url, generally http://localhost:8080
	 */
	@Value("${core.baseUrl}")
	private String siteBase;

	@Autowired
	private WebDriver webDriver;

	@Autowired
	private SeleniumHelper seleniumHelper;

	@Before
	public final void clearBrowser() {
		webDriver.get(siteBase);
	}

	@After
	public final void saveScreenshot(final Scenario scenario) {
		scenario.embed(seleniumHelper.takeScreenshot(), "image/png");
	}

	@When("^I visit the home page$")
	@Given("^I am on the home page$")
	public final void I_visit_the_homepage() throws Throwable {
		webDriver.get(siteBase);
	}

	@Then("^the browser title should be '(.*)'$")
	public final void the_browser_title_should_be_Employee(
			final String expectedTitle) throws Throwable {
		assertEquals("The Browser title should be as expected", expectedTitle,
				webDriver.getTitle());
	}

	@Then("^I should redirected to the employees page$")
	public final void I_should_redirected_to_the_employees_page()
			throws Throwable {
		assertEquals("The Browser title should be as expected", "Employee",
				webDriver.getTitle());
	}

	@When("^I put in the following information$")
	public final void I_put_in_the_following_information(
			final List<EmployeeFormDetail> employeeFormDetails)
			throws Throwable {
		EmployeeFormDetail employeeFormDetail = employeeFormDetails.get(0);

		// Input the information
		findElementPoll(By.name("firstName"), webDriver).sendKeys(
				employeeFormDetail.getFirstName());
		findElementPoll(By.name("secondName"), webDriver).sendKeys(
				employeeFormDetail.getSecondName());
		findElementPoll(By.name("jobTitle"), webDriver).sendKeys(
				employeeFormDetail.getJobTitle());
		findElementPoll(By.name("deskId"), webDriver).sendKeys(
				employeeFormDetail.getDeskId());
	}

	@When("^I press 'Add Employee'$")
	public final void I_press_Add_Employee() throws Throwable {
		WebElement submitButton = webDriver.findElement(By
				.cssSelector("input[type='submit'][value = 'Add Employee']"));
		submitButton.submit();
	}

	@When("^I click the '(.+)' navigation link$")
	public final void I_click_the_addEmployee_navigation_link(
			final String idLink) throws Throwable {
		// Find the link and click it
		webDriver.findElement(By.id(idLink)).click();
	}

	@Then("^the content title will be '(.+)'$")
	public final void the_content_title_will_be_Add_Employee(
			final String expectedText) throws Throwable {
		assertEquals("The title should be as expected", expectedText,
				findElementPoll(By.tagName("h2"), webDriver).getText());
	}

	@Then("^the alert will say '(.+)'$")
	public final void the_alert_will_say(final String expectedAlert)
			throws Throwable {
		assertEquals("The alert should be as expected", expectedAlert,
				findElementPoll(By.className("alert"), webDriver).getText());
	}

	/**
	 * Helper to wait for an element within a loaded page Created because the
	 * selenium tests randomly failed otherwise
	 * 
	 * @param by
	 *            The search requirement
	 * @param webDriver
	 *            The webDriver instance
	 * @return The element if found
	 */
	private WebElement findElementPoll(final By by, final WebDriver webDriver) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);

		WebElement foundElement = wait
				.until(new Function<WebDriver, WebElement>() {
					@Override
					public WebElement apply(final WebDriver webDriver) {
						return webDriver.findElement(by);
					}
				});
		return foundElement;
	}

	/**
	 * Represents the basic models for the form that users need to fill in when
	 * creating a new employee IE, it represents the raw form models itself, and
	 * not an explicit Employee POJO - even though it may share similar
	 * properties Also note, all of the data types are strings to represent the
	 * fact that all of the input fields will contain text
	 */
	public class EmployeeFormDetail {
		private String firstName;
		private String secondName;
		private String jobTitle;
		private String deskId;

		public final String getFirstName() {
			return firstName;
		}

		public final void setFirstName(final String firstName) {
			this.firstName = firstName;
		}

		public final String getSecondName() {
			return secondName;
		}

		public final void setSecondName(final String secondName) {
			this.secondName = secondName;
		}

		public final String getJobTitle() {
			return jobTitle;
		}

		public final void setJobTitle(final String jobTitle) {
			this.jobTitle = jobTitle;
		}

		public final String getDeskId() {
			return deskId;
		}

		public final void setDeskId(final String deskId) {
			this.deskId = deskId;
		}
	}

}