package tr.findlaw.pages;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import com.framework.asserts.AssertionHandler;
import com.framework.reports.ReportBuilder;
import com.framework.reports.TestNGCustomReporter;
import com.framework.utils.ExcelUtils;

import tr.findlaw.objects.AccountPageObjects;
import tr.findlaw.objects.BasePageObjects;
import tr.findlaw.objects.FindLawOrderToolPageObjects;
import tr.findlaw.objects.SafeLoginpageObjects;

public class Accountpage extends BasePage {

	public Accountpage(WebDriver webDriver) {
		super(webDriver);
		
	}

	public void searchExistAccount(int Row) {
		String accountNo = ExcelUtils.getDataByColumnNameWithRowIndex("Account", "Id", Row);
		elementHandler.writeText(BasePageObjects.txtBoxSearch, accountNo, BasePageObjects.btnSearch);
		elementHandler.clickElement(BasePageObjects.btnSearch);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AccountPageObjects.linkAccountName);
		actionHandler.waitForSomeTime(5000);
		String accntNo= elementHandler.getText(AccountPageObjects.lblAccountNo);
		AssertionHandler.verifyEquals(accntNo, accountNo, "Account number not matched with the Excel sheet provided value");
		TestNGCustomReporter.log(logger, "Account Detail page displayed for:"+accountNo  +ReportBuilder.takesScreenshot());
	}
	
	public void searchExistAccounts(String user) {
		elementHandler.writeText(BasePageObjects.txtBoxSearch, user, BasePageObjects.btnSearch);
		elementHandler.clickElement(BasePageObjects.btnSearch);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AccountPageObjects.linkAccountName);
		actionHandler.waitForSomeTime(5000);
		String accntNo= elementHandler.getText(AccountPageObjects.lblAccountNo);
		AssertionHandler.verifyEquals(accntNo, user, "Account number not matched with the Excel sheet provided value");
		TestNGCustomReporter.log(logger, "Account Detail page displayed for:" +user +ReportBuilder.takesScreenshot());
	}
	
	public ArrayList<String> getAssetSequence(int Row) {
		String accountNo = ExcelUtils.getDataByColumnNameWithRowIndex("Account", "Id", Row);
		elementHandler.writeText(BasePageObjects.txtBoxSearch, accountNo, BasePageObjects.btnSearch);
		elementHandler.clickElement(BasePageObjects.btnSearch);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AccountPageObjects.linkAccountName);
		actionHandler.waitForSomeTime(5000);
		String accntNo= elementHandler.getText(AccountPageObjects.lblAccountNo);
		AssertionHandler.verifyEquals(accntNo, accountNo, "Account number not matched with the Excel sheet provided value");
		TestNGCustomReporter.log(logger, "Account Detail page displayed for:"+accountNo  +ReportBuilder.takesScreenshot());
		elementHandler.clickElement(AccountPageObjects.lnkAssetLine);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AccountPageObjects.lnkGotoList);
		actionHandler.waitForSomeTime(2000);
		TestNGCustomReporter.log(logger, "Asset Line Items Sold to dispayed for the account"+ReportBuilder.takesScreenshot());
		int size= AccountPageObjects.lnkAssets.size();
		ArrayList<String> assetText= new ArrayList<String>();
		for (int i=0;i<size;i++) 
		{
			String text= elementHandler.getText(AccountPageObjects.lnkAssets.get(i));
			if (text.contains("PPC")) 
			{
				assetText.add(text);
			}
		}
		return assetText;
	
	}

	
	public void validateLinksInAccount(int Row) {
		String accountNo = ExcelUtils.getDataByColumnNameWithRowIndex("Account", "Id", Row);
		elementHandler.writeText(BasePageObjects.txtBoxSearch, accountNo, BasePageObjects.btnSearch);
		elementHandler.clickElement(BasePageObjects.btnSearch);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AccountPageObjects.linkAccountName);
		actionHandler.waitForSomeTime(5000);
		String accntNo= elementHandler.getText(AccountPageObjects.lblAccountNo);
		AssertionHandler.verifyEquals(accntNo, accountNo, "Account number not matched with the Excel sheet provided value");
		TestNGCustomReporter.log(logger, "Account Detail page displayed for:"+accountNo  +ReportBuilder.takesScreenshot());
		javascripthandler.scrollIntoView(AccountPageObjects.lnkResourcePage, AccountPageObjects.lnkCOB);
		actionHandler.waitForSomeTime(2000);
		String Resoucepg= elementHandler.getText(AccountPageObjects.lnkResourcePage);
		AssertionHandler.verifyTrue(Resoucepg.contains("FindLaw Resource Page"), "Findlaw Resource Page link not displayed under Custom Links Section");
		actionHandler.waitForSomeTime(2000);
		String cob= elementHandler.getText(AccountPageObjects.lnkCOB);
		AssertionHandler.verifyTrue(cob.contains("Client Order Brief (COB)"), "Client Order Brief (COB) link not displayed under Custom Links Section");
		elementHandler.clickElement(AccountPageObjects.lnkCOB);
		String windowHand= windowHandler.getParentWindow();
		windowHandler.switchToLatestWindow(SafeLoginpageObjects.iframe);
		String userName = ExcelUtils.getDataByColumnName("Login", "Safe_UserId");
		String password = ExcelUtils.getDataByColumnName("Login", "Safe_Password");
		windowHandler.switchToFrame(SafeLoginpageObjects.iframe, SafeLoginpageObjects.txtSAFEUserId);
		elementHandler.writeText(SafeLoginpageObjects.txtSAFEUserId, userName, SafeLoginpageObjects.txtSAFEPassword);
		elementHandler.writeText(SafeLoginpageObjects.txtSAFEPassword, password, SafeLoginpageObjects.btnLogin);
		elementHandler.clickElement(SafeLoginpageObjects.btnLogin);
		actionHandler.waitForSomeTime(25000);
		windowHandler.refereshWebPage();
		windowHandler.switchToParentFrame(FindLawOrderToolPageObjects.txtOrderId);
		actionHandler.waitForSomeTime(5000);
		boolean order= elementHandler.isElementDisplayed(FindLawOrderToolPageObjects.txtOrderId);
		AssertionHandler.verifyTrue(order, "Order Id field not displayed");
		driver.close();
		windowHandler.switchToLatestWindow(AccountPageObjects.lnkResourcePage);
		//windowHandler.switchToParentWindow(windowHand);
		
	}
	
	
	
	
	
	
	
} 
  