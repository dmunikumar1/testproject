package tr.findlaw.pages;

import org.openqa.selenium.WebDriver;
import com.framework.asserts.AssertionHandler;
import com.framework.reports.ReportBuilder;
import com.framework.reports.TestNGCustomReporter;
import tr.findlaw.objects.OrderDetailPageObjects;
import tr.findlaw.objects.QuotePageObjects;

public class ValidateEsignPage extends BasePage {

	String lblOrder1= "//div[@id='Name_ileinner']";
	public ValidateEsignPage(WebDriver webDriver) {
		super(webDriver);
	
	}
	
	public void validateEsign() {	
		elementHandler.clickElement(QuotePageObjects.btnValidateEsign);
		actionHandler.waitForSomeTime(80000);
		actionHandler.waitForSomeTime(80000);
		boolean status1= elementHandler.isElementDisplayed(QuotePageObjects.btnEdit);
		if (status1 == false) {
			actionHandler.waitForSomeTime(50000);
		}
		windowHandler.refereshWebPage();
		windowHandler.refereshWebPage();
		windowHandler.refereshWebPage();
		windowHandler.refereshWebPage();
		windowHandler.refereshWebPage();
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(QuotePageObjects.lnkOrders);
		elementHandler.clickElement(QuotePageObjects.lnkOrders);
		elementHandler.clickElement(QuotePageObjects.lnkOrderNumber);
		TestNGCustomReporter.log(logger, "Order Detail page displayed");
		elementHandler.waitForXpathToLoad(lblOrder1);
		String OrderNo= elementHandler.getText(QuotePageObjects.lblOrderNo);
		TestNGCustomReporter.log(logger, "Order No has been generated : " +OrderNo+ " "+ ReportBuilder.takesScreenshot());
		String sapStatus= elementHandler.getText(OrderDetailPageObjects.lblSAPStaus);
		AssertionHandler.verifyTrue(sapStatus.contains("Ready for COB"), "Quote status is pending and not reaching Findlaw Order Tool");
		elementHandler.waitForElementTobeClickable(OrderDetailPageObjects.lnkQuoteId);
		elementHandler.clickElement(OrderDetailPageObjects.lnkQuoteId, QuotePageObjects.lblQuoteproposal);
		actionHandler.waitForSomeTime(10000);
	}
	
	/*
	 * used for specific scenario
	 */
	public void validateEsign1() {	
		elementHandler.clickElement(QuotePageObjects.btnValidateEsign);
		actionHandler.waitForSomeTime(80000);
		actionHandler.waitForSomeTime(80000);
		boolean status1= elementHandler.isElementDisplayed(QuotePageObjects.btnEdit);
		if (status1 == false) {
			actionHandler.waitForSomeTime(50000);
		}
		windowHandler.refereshWebPage();
		windowHandler.refereshWebPage();
		windowHandler.refereshWebPage();
		windowHandler.refereshWebPage();
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(QuotePageObjects.lnkOrders);
		elementHandler.clickElement(QuotePageObjects.lnkOrders);
		elementHandler.clickElement(QuotePageObjects.lnkOrderNumber);
		TestNGCustomReporter.log(logger, "Order Detail page displayed");
		elementHandler.waitForXpathToLoad(lblOrder1);
		String OrderNo= elementHandler.getText(QuotePageObjects.lblOrderNo);
		TestNGCustomReporter.log(logger, "Order No has been generated : " +OrderNo+ " "+ ReportBuilder.takesScreenshot());
		String sapStatus= elementHandler.getText(OrderDetailPageObjects.lblSAPStaus);
		AssertionHandler.verifyTrue(sapStatus.contains("Order Created"), "Quote status is pending and not reaching Findlaw Order Tool");
		elementHandler.waitForElementTobeClickable(OrderDetailPageObjects.lnkQuoteId);
		elementHandler.clickElement(OrderDetailPageObjects.lnkQuoteId, QuotePageObjects.lblQuoteproposal);
		actionHandler.waitForSomeTime(10000);
	}
	
	public void validateOrder() {		
		windowHandler.refereshWebPage();
		elementHandler.waitForElementTobeClickable(QuotePageObjects.lnkOrders);
		elementHandler.clickElement(QuotePageObjects.lnkOrders);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(QuotePageObjects.lnkOrderNumber);
		TestNGCustomReporter.log(logger, "Order Detail page displayed");
		elementHandler.waitForXpathToLoad(lblOrder1);
		String OrderNo= elementHandler.getText(QuotePageObjects.lblOrderNo);
		String sapStatus= elementHandler.getText(OrderDetailPageObjects.lblSAPStaus);
		AssertionHandler.verifyNotEquals(sapStatus, "Pending", "Order not created successfully and sent to SAP");
		TestNGCustomReporter.log(logger, "COB Process has Completed for Order: " +OrderNo+ " "+ ReportBuilder.takesScreenshot());

	}
	
/*
 * used for Renewal scenarios	
 */
	public void validateEsignForRenew() {	
		elementHandler.clickElement(QuotePageObjects.btnValidateEsign);
		actionHandler.waitForSomeTime(80000);
		actionHandler.waitForSomeTime(80000);
		boolean status1= elementHandler.isElementDisplayed(QuotePageObjects.btnEdit);
		if (status1 == false) {
			actionHandler.waitForSomeTime(50000);
		}
		windowHandler.refereshWebPage();
		windowHandler.refereshWebPage();
		windowHandler.refereshWebPage();
		windowHandler.refereshWebPage();
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(QuotePageObjects.lnkOrders);
		elementHandler.clickElement(QuotePageObjects.lnkOrders);
		elementHandler.clickElement(QuotePageObjects.lnkOrderNumber);
		TestNGCustomReporter.log(logger, "Order Detail page displayed");
		elementHandler.waitForXpathToLoad(lblOrder1);
		String OrderNo= elementHandler.getText(QuotePageObjects.lblOrderNo);
		TestNGCustomReporter.log(logger, "Order No has been generated : " +OrderNo+ " "+ ReportBuilder.takesScreenshot());
		String sapStatus= elementHandler.getText(OrderDetailPageObjects.lblSAPStaus);
		AssertionHandler.verifyNotEquals(sapStatus, "Pending", "Order created but status is still pending");
		elementHandler.waitForElementTobeClickable(OrderDetailPageObjects.lnkQuoteId);
		elementHandler.clickElement(OrderDetailPageObjects.lnkQuoteId, QuotePageObjects.lblQuoteproposal);
		actionHandler.waitForSomeTime(10000);
	}
	
}
