package tr.findlaw.pages;

import org.openqa.selenium.WebDriver;

import com.framework.asserts.AssertionHandler;
import com.framework.reports.TestNGCustomReporter;
import com.framework.utils.ExcelUtils;
import com.framework.utils.StringUtils;

import tr.findlaw.objects.PasswordHoldersPageObjects;
import tr.findlaw.objects.QuotePageObjects;

public class PasswordHoldersPage extends BasePage{

	String rowButton ="//td[@id='topButtonRow']/input[7]";
	
	public PasswordHoldersPage(WebDriver webDriver) {
		super(webDriver);
		
	}

	public void addPasswordHolders(int ctrl1) {
		elementHandler.waitForXpathToLoad(rowButton);
		elementHandler.clickElement(QuotePageObjects.btnPasswdHolders, PasswordHoldersPageObjects.lblOnlineContacts);
		actionHandler.waitForSomeTime(8000);
		
		switch(ctrl1)
		{
		//for per-seat product	
		case 1:
			elementHandler.clickElement(PasswordHoldersPageObjects.btnPerSeatProducts,PasswordHoldersPageObjects.checkbox1);	
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(PasswordHoldersPageObjects.checkbox1,PasswordHoldersPageObjects.txtFirstName);
			actionHandler.waitForSomeTime(1000);
			String Firstname= StringUtils.testDataModification(ExcelUtils.getDataByColumnName("PassWordHolders", "First_Name"));
			elementHandler.writeText(PasswordHoldersPageObjects.txtFirstName, Firstname, PasswordHoldersPageObjects.txtLastName);
			actionHandler.waitForSomeTime(1000);
			String LastName= StringUtils.testDataModification(ExcelUtils.getDataByColumnName("PassWordHolders", "Last_Name"));
			elementHandler.writeText(PasswordHoldersPageObjects.txtLastName, LastName, PasswordHoldersPageObjects.drpdwnPosition);
			actionHandler.waitForSomeTime(1000);
			String Position= ExcelUtils.getDataByColumnName("PassWordHolders", "Position");
			elementHandler.selectByValue(PasswordHoldersPageObjects.drpdwnPosition, Position, PasswordHoldersPageObjects.drpdwnType);
			actionHandler.waitForSomeTime(1000);
			String Type= ExcelUtils.getDataByColumnName("PassWordHolders", "Type");
			elementHandler.selectByValue(PasswordHoldersPageObjects.drpdwnType, Type, PasswordHoldersPageObjects.drpdwnJuris);
			actionHandler.waitForSomeTime(1000);
			String Juris= ExcelUtils.getDataByColumnName("PassWordHolders", "Juris");
			elementHandler.selectByValue(PasswordHoldersPageObjects.drpdwnJuris, Juris, PasswordHoldersPageObjects.txtEmail1);
			actionHandler.waitForSomeTime(1000);
			String Email= ExcelUtils.getDataByColumnName("PassWordHolders", "Email");
			elementHandler.writeText(PasswordHoldersPageObjects.txtEmail1, Email, PasswordHoldersPageObjects.btnSave);
			actionHandler.waitForSomeTime(1000);
			elementHandler.clickElement(PasswordHoldersPageObjects.btnSave,QuotePageObjects.lblQuoteproposal);
			actionHandler.waitForSomeTime(15000);
			boolean quote= elementHandler.isElementDisplayed(QuotePageObjects.lblQuoteproposal);
			AssertionHandler.verifyTrue(quote, "Quote page not displayed");		
			TestNGCustomReporter.log(logger, "Password Holders saved successfully");
		
			//for adding Banded products
		case 2:
			
			elementHandler.clickElement(PasswordHoldersPageObjects.bandedCheckbox,PasswordHoldersPageObjects.bandedFirstname);
			actionHandler.waitForSomeTime(1000);
			String Firstname1= StringUtils.testDataModification(ExcelUtils.getDataByColumnName("PassWordHolders", "First_Name"));
			elementHandler.writeText(PasswordHoldersPageObjects.bandedFirstname, Firstname1, PasswordHoldersPageObjects.bandedLastname);
			actionHandler.waitForSomeTime(1000);
			String LastName1= StringUtils.testDataModification(ExcelUtils.getDataByColumnName("PassWordHolders", "Last_Name"));
			elementHandler.writeText(PasswordHoldersPageObjects.bandedLastname, LastName1, PasswordHoldersPageObjects.bandedPosition);
			actionHandler.waitForSomeTime(1000);
			String Position1= ExcelUtils.getDataByColumnName("PassWordHolders", "Position");
			elementHandler.selectByValue(PasswordHoldersPageObjects.bandedPosition, Position1, PasswordHoldersPageObjects.bandedType);
			actionHandler.waitForSomeTime(1000);
			String Type1= ExcelUtils.getDataByColumnName("PassWordHolders", "Type");
			elementHandler.selectByValue(PasswordHoldersPageObjects.bandedType, Type1, PasswordHoldersPageObjects.bandedJuris);
			actionHandler.waitForSomeTime(1000);
			String Juris1= ExcelUtils.getDataByColumnName("PassWordHolders", "Juris");
			elementHandler.selectByValue(PasswordHoldersPageObjects.bandedJuris, Juris1, PasswordHoldersPageObjects.bandedEmail);
			actionHandler.waitForSomeTime(1000);
			String Email1= ExcelUtils.getDataByColumnName("PassWordHolders", "Email");
			elementHandler.writeText(PasswordHoldersPageObjects.bandedEmail, Email1, PasswordHoldersPageObjects.btnSave);
			actionHandler.waitForSomeTime(1000);
			elementHandler.clickElement(PasswordHoldersPageObjects.btnSave,QuotePageObjects.lblQuoteproposal);
			actionHandler.waitForSomeTime(15000);
			boolean quote1= elementHandler.isElementDisplayed(QuotePageObjects.lblQuoteproposal);
			AssertionHandler.verifyTrue(quote1, "Quote page not displayed");		
			TestNGCustomReporter.log(logger, "Password Holders saved successfully");	
			
	}
	
	
	}
}
