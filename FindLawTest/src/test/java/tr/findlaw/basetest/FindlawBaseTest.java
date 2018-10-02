package tr.findlaw.basetest;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;


import com.accessibility.AccessibilityScanner;
import com.accessibility.Result;
import com.deque.axe.AXE;

import com.framework.config.PropertiesRepository;
import com.framework.handlers.ElementHandler;
import com.framework.reports.ReportBuilder;
import com.framework.reports.TestNGCustomReporter;
import com.framework.test.BaseTest;
import com.framework.utils.ExcelUtils;

import junit.framework.Assert;
import tr.findlaw.pages.Accountpage;
import tr.findlaw.pages.AddProductsPage;
import tr.findlaw.pages.BasePage;
import tr.findlaw.pages.Contact28Page;
import tr.findlaw.pages.ContactsPage;
import tr.findlaw.pages.EsignProcessPage;
import tr.findlaw.pages.FindLawOrderToolPage;
import tr.findlaw.pages.GenerateDocumentPage;
import tr.findlaw.pages.MasterContactsPage;
import tr.findlaw.pages.OpptyPage;
import tr.findlaw.pages.PasswordHoldersPage;
import tr.findlaw.pages.QuotePage;
import tr.findlaw.pages.SearchPage;
import tr.findlaw.pages.SearchQuotePage;
import tr.findlaw.pages.SendEsignPage;
import tr.findlaw.pages.ValidateEsignPage;

public class FindlawBaseTest extends BaseTest {
	private static Logger logger = LogManager.getLogger(FindlawBaseTest.class);
	protected ElementHandler elementHandler;
	private static final URL scriptUrl = FindlawBaseTest.class.getResource("/axe.min.js");
	public TestName testName = new TestName();
	
	/***
	 * Loading excel test data file and creating excel report file
	 */
	static {
		try {
			ExcelUtils.setExcel(System.getProperty("user.dir") + PropertiesRepository.getString("testdata.excel.file"));
		} catch (Exception e) {
			logger.error("Unable to load/create excel files", e);
		}
	}

	// Variables for the excel test report
	protected String inputData;
	protected String outputData;
	protected String profileUser;
	protected String userName;
	protected String password;
	
	
	// Page class objects
	protected BasePage basePage;
	protected Accountpage accountpage;
	protected ContactsPage contactsPage;
	protected OpptyPage opptypage;
	protected QuotePage quotepage;
	protected AddProductsPage addproductspage;
	protected Contact28Page contact28page;
	protected GenerateDocumentPage generateDocumentPage;
	protected SendEsignPage sendesignPage;
	protected EsignProcessPage esignprocesspage;
	protected ValidateEsignPage validateesignpage;
	protected SearchQuotePage searchquotepage;
	protected FindLawOrderToolPage findlawordertoolpage;
	protected SearchPage searchpage;
	protected PasswordHoldersPage passwordholderspage;
	protected MasterContactsPage mastercontactspage;
	/***
	 * 
	 * Application login
	 * @throws IOException 
	 */
	protected void applicationLogin() {
	
			driver.get(PropertiesRepository.getString("com.findlaw.url"));
			TestNGCustomReporter.log(logger, "Launched the url");
			JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();

			JSONArray violations = responseJSON.getJSONArray("violations");

			if (violations.length() == 0) {
				Assert.assertTrue("No violations found", true);
			} else {
				AXE.writeResults(testName.getMethodName(), responseJSON);
				Assert.assertTrue(AXE.report(violations), false);
			}
			/*basePage = new BasePage(driver);
			basePage.loginWithValidUser();*/
		
	}
	/*
	 * Webmail Login
	 * 
	 * */
	
	protected void webmailLogin() {
		driver.get(PropertiesRepository.getString("com.webmail.url"));
		TestNGCustomReporter.log(logger, "Launched the url");
		basePage = new BasePage(driver);
		basePage.loginWithValidUserWebmail();
	}
	
	
	
}
