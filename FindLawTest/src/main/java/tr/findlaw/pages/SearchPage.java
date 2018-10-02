package tr.findlaw.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.framework.asserts.AssertionHandler;
import com.framework.reports.ReportBuilder;
import com.framework.reports.TestNGCustomReporter;
import com.framework.utils.ExcelUtils;
import tr.findlaw.objects.BasePageObjects;
import tr.findlaw.objects.SearchPageObjects;

public class SearchPage extends BasePage{

	String lblView= "//select[@id='fcf']";
	String btnInput ="//input[@id='phSearchInput']";
	String lblApprover= "//label[text()='Approver Comments']/../..//textarea";
	
	public SearchPage(WebDriver webDriver) {
		super(webDriver);	
	}
	
	public void searchDiffProfile(String user) {
		elementHandler.waitForIdToLoad(BasePageObjects.txtBoxSearch);
		elementHandler.writeText(BasePageObjects.txtBoxSearch, user, BasePageObjects.btnSearch);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement( BasePageObjects.btnSearch, SearchPageObjects.linkUser);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(SearchPageObjects.linkUser);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForIdToLoad(SearchPageObjects.drpdwnarrow);
		elementHandler.clickElement(SearchPageObjects.drpdwnarrow, SearchPageObjects.linkUserDetail);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(SearchPageObjects.linkUserDetail,SearchPageObjects.btnLogin1);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(SearchPageObjects.btnLogin1,BasePageObjects.userNavigationLbl);
		boolean navigation= elementHandler.isElementDisplayed(BasePageObjects.userNavigationLbl);
		AssertionHandler.verifyTrue(navigation, "user profile is not able to login successfully");		
		TestNGCustomReporter.log(logger, "User logged in successfully" +ReportBuilder.takesScreenshot());
	}
	
	public void clickCOBfromProposals(String QuoteId) {
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(SearchPageObjects.btnproposals, SearchPageObjects.lblproposals);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForXpathToLoad(lblView);
		//select swati proposal view from the drop down list
		elementHandler.selectByIndex(SearchPageObjects.drpdwnView, 20);
		actionHandler.waitForSomeTime(5000);
		boolean status= elementHandler.isElementDisplayed(SearchPageObjects.btnGo);
		if (status == true)
		elementHandler.clickElement(SearchPageObjects.btnGo);
		actionHandler.waitForSomeTime(5000);
		/*boolean status1= driver.findElement(By.xpath("//span[text()='"+QuoteId+"']/../../../..//td[8]/div/a/img")).isEnabled();
		if (status1 == true) {
			elementHandler.clickElement(driver.findElement(By.xpath("//span[text()='"+QuoteId+"']/../../../..//td[8]/div/a/img")));
			actionHandler.waitForSomeTime(5000);
		}
		else {*/
			elementHandler.waitForElementTobeClickable(SearchPageObjects.lblCreatedBy);
			javascripthandler.highlightElement(SearchPageObjects.lblCreatedBy);
			actionHandler.waitForSomeTime(4000);
			elementHandler.clickElement(SearchPageObjects.lblCreatedBy);
			actionHandler.waitForSomeTime(8000);
			elementHandler.clickElement(driver.findElement(By.xpath("//span[text()='"+QuoteId+"']/../../../..//td[8]/div/a/img")));
			actionHandler.waitForSomeTime(5000);
		//}	
	}
	
	
	public void approvalProcess(String name) {
		elementHandler.waitForXpathToLoad(btnInput);
		elementHandler.writeText(SearchPageObjects.txtInput, name, SearchPageObjects.btnSearch);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(SearchPageObjects.btnSearch, SearchPageObjects.lnkApprvReject);
		elementHandler.waitForElementTobeClickable(SearchPageObjects.lnkApprvReject);
		elementHandler.clickElement(SearchPageObjects.lnkApprvReject, SearchPageObjects.txtApprvComments);
		actionHandler.waitForSomeTime(8000);
		String AprvCmt= ExcelUtils.getDataByColumnName("Quote", "Comments");
		elementHandler.waitForXpathToLoad(lblApprover);
		elementHandler.writeText(SearchPageObjects.txtApprvComments, AprvCmt, SearchPageObjects.btnApprove);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(SearchPageObjects.btnApprove);
		actionHandler.waitForSomeTime(5000);
		TestNGCustomReporter.log(logger, "Approval process completed" +ReportBuilder.takesScreenshot());
		
	}
	
	public void changeTab() {
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForElementTobeClickable(SearchPageObjects.tabChange);
		elementHandler.clickElement(SearchPageObjects.tabChange, SearchPageObjects.tabSales);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(SearchPageObjects.tabSales);
		actionHandler.waitForSomeTime(5000);
		TestNGCustomReporter.log(logger, "Changed the tab successfully" +ReportBuilder.takesScreenshot());	
	}
	
}
		


	
	  
		
	


