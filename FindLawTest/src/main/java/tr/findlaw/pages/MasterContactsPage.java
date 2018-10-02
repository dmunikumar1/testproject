package tr.findlaw.pages;

import org.openqa.selenium.WebDriver;

import com.framework.asserts.AssertionHandler;
import com.framework.reports.TestNGCustomReporter;
import com.framework.utils.ExcelUtils;
import com.framework.utils.StringUtils;
import tr.findlaw.objects.MasterContactsPageObjects;
import tr.findlaw.objects.QuotePageObjects;

public class MasterContactsPage extends BasePage {

	public MasterContactsPage(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}

	public void addmasterContacts() {
		elementHandler.waitForElementTobeClickable(MasterContactsPageObjects.btnMasterContacts);
		elementHandler.clickElement(MasterContactsPageObjects.btnMasterContacts,MasterContactsPageObjects.btnSave);
		actionHandler.waitForSomeTime(8000);
		String Firstname= StringUtils.testDataModification(ExcelUtils.getDataByColumnName("MasterContacts", "First_Name"));
		elementHandler.writeText(MasterContactsPageObjects.txtboxmcFirstName, Firstname, MasterContactsPageObjects.txtboxmcLastName);
		actionHandler.waitForSomeTime(2000);
		String LastName= StringUtils.testDataModification(ExcelUtils.getDataByColumnName("MasterContacts", "Last_Name"));
		elementHandler.writeText(MasterContactsPageObjects.txtboxmcLastName, LastName, MasterContactsPageObjects.txtboxmcEmail);
		actionHandler.waitForSomeTime(2000);
		String Email= ExcelUtils.getDataByColumnName("MasterContacts", "Email");
		elementHandler.writeText(MasterContactsPageObjects.txtboxmcEmail, Email, MasterContactsPageObjects.btnSave);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(MasterContactsPageObjects.btnSave);
		actionHandler.waitForSomeTime(10000);
		boolean quote= elementHandler.isElementDisplayed(QuotePageObjects.lblQuoteproposal);
		AssertionHandler.verifyTrue(quote, "Quote page not displayed");		
		TestNGCustomReporter.log(logger, "Master Contacts saved successfully");
	}
	
	
	}
