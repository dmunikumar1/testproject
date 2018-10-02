package com.framework.cucumber.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.framework.config.GlobalProperties;
import com.framework.config.PropertiesRepository;
import com.framework.driverfactory.DriverFactory;
import com.framework.reports.CucumberReports;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CucumberSetup {
	public static Scenario globalscenario;
	protected static WebDriver driver = null;
	public List<String> CucumberOpts = new ArrayList<String>();


	public static String browserType;

	/*@Before
	public void setUp(Scenario scenario) {
		browserType = GlobalProperties.BrowserType;
		driver = DriverFactory.getDriver(browserType);
		manageDriver(driver);
		CucumberReports.setDriver(driver);
		globalscenario = scenario;
	}
	
	

	@After
	public void tearDown(Scenario scenario) throws IOException {
		// if (scenario.isFailed()) {
		globalscenario = scenario;
		File screenshot = takeScreenShots("screenshot");
		InputStream screenshotStream = new FileInputStream(screenshot);
		globalscenario.embed(IOUtils.toByteArray(screenshotStream), "image/png");
		// }
		if (driver != null) {
			DriverFactory.removeDriver(driver);
		}
	}
	
	public static byte[] attachScreenshot() {
		File screenshot;
		byte [] screenshotArray = null;
		try {
			screenshot = takeScreenShots("screenshot");
			InputStream screenshotStream = new FileInputStream(screenshot);
			screenshotArray = IOUtils.toByteArray(screenshotStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return screenshotArray;
		
	}

	public static File takeScreenShots(String picture) throws IOException {
		File temp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(temp, new File(GlobalProperties.ScreenShotPath + File.separator + picture));
		return temp;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	private void manageDriver(WebDriver webDriver) {
		webDriver.manage().window().maximize();
		webDriver.manage().deleteAllCookies();
		if (!GlobalProperties.BrowserType.equals(GlobalProperties.FIREFOX)) 
			webDriver.manage().window().maximize();
		webDriver.manage().timeouts().implicitlyWait(PropertiesRepository.getInt("global.implicit.wait"),
				TimeUnit.SECONDS);
	}*/

}
