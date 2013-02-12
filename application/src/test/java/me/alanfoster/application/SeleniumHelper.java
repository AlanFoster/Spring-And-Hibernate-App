package me.alanfoster.application;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class SeleniumHelper {
	private static final Logger logger = LoggerFactory
			.getLogger(SeleniumHelper.class);

	private WebDriver webDriver;
	private String testOutputLocation;

	public SeleniumHelper(final WebDriver webDriver,
			final String testOutputLocation) {
		this.webDriver = webDriver;
	}

	public final byte[] takeScreenshot() {
		return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
	}

	public final void takeScreenshot(final String fileName) {
		File screenShot = ((TakesScreenshot) webDriver)
				.getScreenshotAs(OutputType.FILE);
		File outputLocation = new File(testOutputLocation + File.pathSeparator
				+ fileName);
		try {
			FileUtils.copyFile(screenShot, outputLocation);
		} catch (IOException e) {
			logger.error(
					"Failed to take selenium screenshot with the filename '{}'"
							+ "to the output location '{}' on the page '{}'",
					new Object[] { fileName, outputLocation,
							webDriver.getCurrentUrl() }, e);
		}
	}
}
