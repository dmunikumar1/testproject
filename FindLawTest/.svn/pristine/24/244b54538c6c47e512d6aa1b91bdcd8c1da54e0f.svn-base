 package tr.findlaw.pages;


import org.openqa.selenium.WebDriver;
import com.framework.asserts.AssertionHandler;
import com.framework.reports.ReportBuilder;
import com.framework.reports.TestNGCustomReporter;
import com.framework.utils.StringUtils;

import tr.findlaw.objects.EsignProcessPageObjects;

public class EsignProcessPage extends BasePage {

	String finishBtn= "//button[@id='action-bar-btn-finish']";
	
	public EsignProcessPage(WebDriver webDriver) {
		super(webDriver);
		
	}

	public void esignProcess() {
		
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitforElement(EsignProcessPageObjects.lblUserName);
		String window= windowHandler.getParentWindow();
		elementHandler.clickElement(EsignProcessPageObjects.lblReqstForEsign, EsignProcessPageObjects.lnkReviewDocument);
		elementHandler.clickElement(EsignProcessPageObjects.lnkReviewDocument);
		actionHandler.waitForSomeTime(5000);
		windowHandler.switchToLatestWindow(EsignProcessPageObjects.lblReviewDoc);
		elementHandler.clickElement(EsignProcessPageObjects.lblReviewDoc);
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitforElement(EsignProcessPageObjects.btnContinue);
		elementHandler.clickElement(EsignProcessPageObjects.btnContinue);
		actionHandler.waitForSomeTime(2000);
		boolean start= elementHandler.isElementDisplayed(EsignProcessPageObjects.btnStart);
		AssertionHandler.verifyTrue(start);
		elementHandler.clickElement(EsignProcessPageObjects.btnStart);
		actionHandler.waitForSomeTime(5000);
		javascripthandler.highlightElement(EsignProcessPageObjects.btnSign); 
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(EsignProcessPageObjects.btnSign);
		if(EsignProcessPageObjects.btnAdoptnSign.size()>0)	
		elementHandler.clickElement(EsignProcessPageObjects.btnAdoptnSign.get(0));
		TestNGCustomReporter.log(logger, "Clicked on Adopt n sign button");
		actionHandler.waitForSomeTime(5000);
        String Title = StringUtils.testDataModification("Title");
        boolean status= elementHandler.isElementDisplayed(EsignProcessPageObjects.txtTitle);
        if (status == true) {
        elementHandler.writeText(EsignProcessPageObjects.txtTitle, Title);
        }
        boolean status1= elementHandler.isElementDisplayed(EsignProcessPageObjects.txtTitle1);
        if (status1 == true) {
        elementHandler.writeText(EsignProcessPageObjects.txtTitle1, Title);
        }
        boolean status2= elementHandler.isElementDisplayed(EsignProcessPageObjects.txtTitle2);
        if (status2 == true) {
        elementHandler.writeText(EsignProcessPageObjects.txtTitle2, Title);
        }
        boolean status3= elementHandler.isElementDisplayed(EsignProcessPageObjects.txtTitle3);
        if (status3 == true) {
        elementHandler.writeText(EsignProcessPageObjects.txtTitle3, Title);
        }
        /*String status= elementHandler.getTextFromAttribute(EsignProcessPageObjects.btnNext, "aria-hidden");
        if (status.contentEquals("false")){
        elementHandler.clickElement(EsignProcessPageObjects.btnNext);
        String Test = StringUtils.testDataModification("Test");
        elementHandler.writeText(EsignProcessPageObjects.txtTitle, Test);
        }*/
        actionHandler.waitForSomeTime(5000);
		elementHandler.waitForXpathToLoad(finishBtn);
		elementHandler.clickElement(EsignProcessPageObjects.btnFinish);
		TestNGCustomReporter.log(logger, "Clicked on Finalize button");
		actionHandler.waitForSomeTime(5000);
		if (EsignProcessPageObjects.btnNoThanks.size()>0){
		elementHandler.clickElement(EsignProcessPageObjects.btnNoThanks.get(0));
		}
		TestNGCustomReporter.log(logger,"Esign Process Completed" +ReportBuilder.takesScreenshot());
		actionHandler.waitForSomeTime(2000);
		windowHandler.closeWindow(window);
		windowHandler.switchToLatestWindow();
		}
	

}
