package tr.findlaw.pages;

import org.openqa.selenium.WebDriver;
import com.framework.asserts.AssertionHandler;
import com.framework.reports.ReportBuilder;
import com.framework.reports.TestNGCustomReporter;
import com.framework.utils.ExcelUtils;
import tr.findlaw.objects.BasePageObjects;
import tr.findlaw.objects.QuotePageObjects;

public class SearchQuotePage  extends BasePage {

	String lblQuote2="//input[@title='Edit']/../../../../../../div[1]/table/tbody/tr/td[2]/input[3]";
	public SearchQuotePage(WebDriver webDriver) {
		super(webDriver);
	
	}
	
	public void searchQuote(String ProposalName) {
		elementHandler.writeText(BasePageObjects.txtBoxSearch, ProposalName);
		elementHandler.clickElement(BasePageObjects.btnSearch, QuotePageObjects.lnkQuoteID);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(QuotePageObjects.lnkQuoteID);
		elementHandler.waitForXpathToLoad(lblQuote2);
		boolean edit= elementHandler.isElementDisplayed(QuotePageObjects.btnEdit);
		AssertionHandler.verifyTrue(edit, "Edit button not displayed in the Quote page");
		TestNGCustomReporter.log(logger, "Quote ID Searched" +ReportBuilder.takesScreenshot());
	}
	
	public void searchExistQuote(int Row) {
		String QuoteId = ExcelUtils.getDataByColumnNameWithRowIndex("Quote", "Name", Row);
		elementHandler.writeText(BasePageObjects.txtBoxSearch, QuoteId);
		elementHandler.clickElement(BasePageObjects.btnSearch);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(QuotePageObjects.lnkQuoteID);
		elementHandler.waitForXpathToLoad(lblQuote2);
		boolean edit= elementHandler.isElementDisplayed(QuotePageObjects.btnEdit);
		AssertionHandler.verifyTrue(edit, "Edit button not displayed in the Quote page");
		TestNGCustomReporter.log(logger, "Quote ID Searched" +ReportBuilder.takesScreenshot());
	}
}
