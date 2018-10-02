package tr.findlaw.pages;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import org.openqa.selenium.WebDriver;
import com.framework.asserts.AssertionHandler;
import com.framework.reports.ReportBuilder;
import com.framework.reports.TestNGCustomReporter;
import com.framework.utils.ExcelUtils;
import com.framework.utils.StringUtils;
import tr.findlaw.objects.OpportunityPageObjects;

public class OpptyPage extends BasePage {

	String optyDetail= "//h2[text()='Opportunity Detail']";
	String newOwner= "//input[@id='newOwn']";
	
	public OpptyPage(WebDriver webDriver) {
		super(webDriver);

	}

	public void createOpty() {
		elementHandler.waitForElementTobeClickable(OpportunityPageObjects.linkOpty);
		elementHandler.clickElement(OpportunityPageObjects.linkOpty);
		elementHandler.clickElement(OpportunityPageObjects.btnNewOpty);
		if(OpportunityPageObjects.btnContinue.size()>0) {
		elementHandler.waitForElementTobeClickable(OpportunityPageObjects.btnContinue.get(0));
		elementHandler.clickElement(OpportunityPageObjects.btnContinue.get(0));
		}
		elementHandler.waitForElementTobeClickable(OpportunityPageObjects.txtOpptyName);
		String OptyName = StringUtils.testDataModification(ExcelUtils.getDataByColumnName("Opty", "OptyName"));
		elementHandler.writeText(OpportunityPageObjects.txtOpptyName, OptyName);
		elementHandler.selectByValue(OpportunityPageObjects.dropdwnStage, "75%");
		nextDateDuedate2();
		javascripthandler.scrollIntoView(OpportunityPageObjects.lblPriceBook);
		String PriceBook = ExcelUtils.getDataByColumnName("Opty", "PriceBook");
		/*
		 * as in Devlcrm12 is having 2 same options for Standard price Book so adding below piece of code from 43 to 50
		 */
		//elementHandler.writeText(OpportunityPageObjects.txtPriceBook, PriceBook);
		elementHandler.clickElement(OpportunityPageObjects.lblpriceBook);
		actionHandler.waitForSomeTime(2000);
		windowHandler.switchToLatestWindow(OpportunityPageObjects.frameSearch);
		windowHandler.switchToFrame(OpportunityPageObjects.frameSearch);
		elementHandler.clickElement(OpportunityPageObjects.txtSearch);
		elementHandler.writeText(OpportunityPageObjects.txtSearch,PriceBook);
		elementHandler.clickElement(OpportunityPageObjects.btnGo);
		driver.switchTo().defaultContent();
		actionHandler.waitForSomeTime(2000);
		windowHandler.switchToFrame(OpportunityPageObjects.frameResult);
		elementHandler.clickElement(OpportunityPageObjects.lnkPriceBook);
		windowHandler.switchToLatestWindow(OpportunityPageObjects.lblpriceBook);
		javascripthandler.scrollIntoView(OpportunityPageObjects.lblOpptyInfo);
		elementHandler.clickElement(OpportunityPageObjects.btnSave);
		elementHandler.waitForXpathToLoad(optyDetail);
		String OpptyName= elementHandler.getText(OpportunityPageObjects.lblOpptyName);
		AssertionHandler.verifyNotEquals(OpptyName, "", "Opportunity value is blank");
		TestNGCustomReporter.log(logger, "Opportunity got created: " +OpptyName+ " " +ReportBuilder.takesScreenshot());
			
	}

	public void nextDateDuedate2() {
		String CloseDate;
		Calendar cal = Calendar.getInstance();
		// adding seven days to current date
		cal.add(Calendar.DAY_OF_MONTH, 7);
		Date nextweek = cal.getTime();
		System.out.println(nextweek);
		System.out.println("Next week date will be " + toMMddyyyy(nextweek));
		CloseDate = toMMddyyyy(nextweek);
		elementHandler.writeText(OpportunityPageObjects.txtCloseDt, CloseDate);
	}

	public static String toMMddyyyy(Date day) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String date = formatter.format(day);
		return date;
	}
	
	public void createOptywtOwnerName() {
		elementHandler.waitForElementTobeClickable(OpportunityPageObjects.linkOpty);
		elementHandler.clickElement(OpportunityPageObjects.linkOpty);
		elementHandler.clickElement(OpportunityPageObjects.btnNewOpty);
		boolean status= OpportunityPageObjects.btnContinue.get(0).isEnabled();
		if (status== true) {
		elementHandler.waitForElementTobeClickable(OpportunityPageObjects.btnContinue.get(0));
		elementHandler.clickElement(OpportunityPageObjects.btnContinue.get(0));
		}
		elementHandler.waitforElement(OpportunityPageObjects.txtOpptyName);
		String OptyName = StringUtils.testDataModification(ExcelUtils.getDataByColumnName("Opty", "OptyName"));
		elementHandler.writeText(OpportunityPageObjects.txtOpptyName, OptyName);
		elementHandler.selectByValue(OpportunityPageObjects.dropdwnStage, "75%");
		nextDateDuedate2();
		javascripthandler.scrollIntoView(OpportunityPageObjects.lblPriceBook);
		String PriceBook = ExcelUtils.getDataByColumnName("Opty", "PriceBook");
		elementHandler.writeText(OpportunityPageObjects.txtPriceBook, PriceBook);
		javascripthandler.scrollIntoView(OpportunityPageObjects.lblOpptyInfo);
		elementHandler.clickElement(OpportunityPageObjects.btnSave);
		elementHandler.waitForXpathToLoad(optyDetail);
		String OpptyName= elementHandler.getText(OpportunityPageObjects.lblOpptyName);
		elementHandler.clickElement(OpportunityPageObjects.lblChange, OpportunityPageObjects.lblowner);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForXpathToLoad(newOwner);
		String Owner= ExcelUtils.getDataByColumnName("Quote", "OwnerName");
		elementHandler.writeText(OpportunityPageObjects.txtowner, Owner, OpportunityPageObjects.btnSave1);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(OpportunityPageObjects.btnSave1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyNotEquals(OpptyName, "", "Opportunity value is blank");
		TestNGCustomReporter.log(logger, "Opportunity got created: " +OpptyName+ " " +ReportBuilder.takesScreenshot());
			
	}

}
