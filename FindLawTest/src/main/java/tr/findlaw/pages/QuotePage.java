package tr.findlaw.pages;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.Assertion;

import com.framework.asserts.AssertionHandler;
import com.framework.reports.ReportBuilder;
import com.framework.reports.TestNGCustomReporter;
import com.framework.utils.ExcelUtils;
import com.framework.utils.StringUtils;
import tr.findlaw.objects.AddProductsPageObjects;
import tr.findlaw.objects.OpportunityPageObjects;
import tr.findlaw.objects.QuotePageObjects;
import tr.findlaw.objects.SafeLoginpageObjects;

public class QuotePage extends BasePage{
	
	String lblQuoteP1="//div[@id='ep']/div[1]/table/tbody/tr/td[1]/h2";
	String lblNewOwner= "//h3[text()='Select New Owner']";
	String  lblIncrement ="//td[@id='bodyCell']/div[4]/div[2]/div[2]//tr[8]/td[4]/div";
	String  imgQuotepg  ="//td[text()='Progress']/../td[2]//img";
	
	public QuotePage(WebDriver webDriver) {
		super(webDriver);
		
	}
	
	public String createQuote() { 	
		elementHandler.waitforElement(QuotePageObjects.btnCreateQuote);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(QuotePageObjects.btnCreateQuote);
		actionHandler.waitForSomeTime(10000);
		/*
		* below piece of code is added for dev12 sandbox from line 41 to 45
		*/
		boolean btnContinue= elementHandler.isElementDisplayed(QuotePageObjects.btnContinue);
		if (btnContinue == true) {
			elementHandler.clickElement(QuotePageObjects.btnContinue,QuotePageObjects.textproposalname);
			actionHandler.waitForSomeTime(10000);
		}
		String ProposalName = StringUtils.testDataModification(ExcelUtils.getDataByColumnName("Quote", "ProposalName"));
		elementHandler.waitForElementTobeClickable(QuotePageObjects.textproposalname);
		elementHandler.writeText(QuotePageObjects.textproposalname, ProposalName);
		elementHandler.clickElement(QuotePageObjects.btnsave,QuotePageObjects.linkChange);
		actionHandler.waitForSomeTime(8000);
		elementHandler.clickElement(QuotePageObjects.linkChange,QuotePageObjects.txtOwner);
		actionHandler.waitForSomeTime(5000);
		String OwnerName= ExcelUtils.getDataByColumnName("Quote", "OwnerName");
		elementHandler.writeText(QuotePageObjects.txtOwner, OwnerName);
		elementHandler.clickElement(QuotePageObjects.btnSave);
		elementHandler.waitForElementTobeClickable(QuotePageObjects.btnEdit);
		elementHandler.clickElement(QuotePageObjects.btnEdit);
		javascripthandler.scrollIntoView(QuotePageObjects.lblApprovalSeg);
		elementHandler.selectByVisibleText(QuotePageObjects.drpdwnApprovalSegment, "FindLaw");
		elementHandler.clickElement(QuotePageObjects.btnSave1);
		elementHandler.waitForXpathToLoad(lblQuoteP1);
		actionHandler.waitForSomeTime(5000);		
		String QuoteId= elementHandler.getText(QuotePageObjects.lblProposalId);
		AssertionHandler.verifyNotEquals(QuoteId, "");
		TestNGCustomReporter.log(logger, "Quote is successfully created: " +QuoteId+ " " +ReportBuilder.takesScreenshot());
		return QuoteId;
	}
	
	public String createQuoteForGLI(int Row) { 	
		elementHandler.waitforElement(QuotePageObjects.btnCreateQuote);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(QuotePageObjects.btnCreateQuote);
		actionHandler.waitForSomeTime(2000);
		/*
		 * below piece of code is added for dev12 sandbox from line 41 to 45
		 */
		boolean btnContinue= elementHandler.isElementDisplayed(QuotePageObjects.btnContinue);
		if (btnContinue == true) {
			elementHandler.clickElement(QuotePageObjects.btnContinue,QuotePageObjects.textproposalname);
			actionHandler.waitForSomeTime(10000);
		}
		String ProposalName = StringUtils.testDataModification(ExcelUtils.getDataByColumnName("Quote", "ProposalName"));
		elementHandler.writeText(QuotePageObjects.textproposalname, ProposalName);
		elementHandler.clickElement(QuotePageObjects.btnsave,QuotePageObjects.linkChange);
		actionHandler.waitForSomeTime(8000);
		elementHandler.clickElement(QuotePageObjects.linkChange,QuotePageObjects.txtOwner);
		actionHandler.waitForSomeTime(5000);
		String OwnerName= ExcelUtils.getDataByColumnName("Quote", "OwnerName");
		elementHandler.writeText(QuotePageObjects.txtOwner, OwnerName);
		elementHandler.clickElement(QuotePageObjects.btnSave);
		elementHandler.waitForElementTobeClickable(QuotePageObjects.btnEdit);
		elementHandler.clickElement(QuotePageObjects.btnEdit);
		javascripthandler.scrollIntoView(QuotePageObjects.lblApprovalSeg);
		elementHandler.selectByVisibleText(QuotePageObjects.drpdwnApprovalSegment, "FindLaw");
		elementHandler.clickElement(QuotePageObjects.btnSave1);
		elementHandler.waitForXpathToLoad(lblQuoteP1);
		actionHandler.waitForSomeTime(2000);
		/*
		 * below piece of code is added due to GLI is getting implementing in Findlaw.
		 */
		String accountno= ExcelUtils.getDataByColumnNameWithRowIndex("Account", "Id",Row);
		elementHandler.clickElement(QuotePageObjects.btnEdit);
		javascripthandler.scrollIntoView(QuotePageObjects.lookupSoldToSSD);
		elementHandler.clickElement(QuotePageObjects.lookupSoldToSSD);
		actionHandler.waitForSomeTime(2000);
		windowHandler.switchToLatestWindow(OpportunityPageObjects.frameSearch);
		windowHandler.switchToFrame(OpportunityPageObjects.frameSearch);
		elementHandler.clickElement(OpportunityPageObjects.txtSearch);
		elementHandler.writeText(OpportunityPageObjects.txtSearch,accountno);
		elementHandler.clickElement(QuotePageObjects.radiobtnAllFields);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(OpportunityPageObjects.btnGo);
		driver.switchTo().defaultContent();
		actionHandler.waitForSomeTime(2000);
		windowHandler.switchToFrame(OpportunityPageObjects.frameResult);
		elementHandler.clickElement(QuotePageObjects.lnkAccountname);
		actionHandler.waitForSomeTime(2000);		
		windowHandler.switchToLatestWindow(QuotePageObjects.lookupSoldToSSD);
		actionHandler.waitForSomeTime(2000);		
		
		elementHandler.clickElement(QuotePageObjects.lookupBillToSSD);
		actionHandler.waitForSomeTime(2000);
		windowHandler.switchToLatestWindow(OpportunityPageObjects.frameSearch);
		windowHandler.switchToFrame(OpportunityPageObjects.frameSearch);
		elementHandler.clickElement(OpportunityPageObjects.txtSearch);
		elementHandler.writeText(OpportunityPageObjects.txtSearch,accountno);
		elementHandler.clickElement(QuotePageObjects.radiobtnAllFields);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(OpportunityPageObjects.btnGo);
		driver.switchTo().defaultContent();
		actionHandler.waitForSomeTime(2000);
		windowHandler.switchToFrame(OpportunityPageObjects.frameResult);
		elementHandler.clickElement(QuotePageObjects.lnkAccountname);
		actionHandler.waitForSomeTime(2000);		
		windowHandler.switchToLatestWindow(QuotePageObjects.lookupBillToSSD);
		actionHandler.waitForSomeTime(2000);		
		
		elementHandler.clickElement(QuotePageObjects.lookupShipToSSD);
		actionHandler.waitForSomeTime(2000);
		windowHandler.switchToLatestWindow(OpportunityPageObjects.frameSearch);
		windowHandler.switchToFrame(OpportunityPageObjects.frameSearch);
		elementHandler.clickElement(OpportunityPageObjects.txtSearch);
		elementHandler.writeText(OpportunityPageObjects.txtSearch,accountno);
		elementHandler.clickElement(QuotePageObjects.radiobtnAllFields);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(OpportunityPageObjects.btnGo);
		driver.switchTo().defaultContent();
		actionHandler.waitForSomeTime(2000);
		windowHandler.switchToFrame(OpportunityPageObjects.frameResult);
		elementHandler.clickElement(QuotePageObjects.lnkAccountname);
		actionHandler.waitForSomeTime(2000);		
		windowHandler.switchToLatestWindow(QuotePageObjects.lookupShipToSSD);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(QuotePageObjects.btnSave1);
		actionHandler.waitForSomeTime(5000);
		String QuoteId= elementHandler.getText(QuotePageObjects.lblProposalId);
		AssertionHandler.verifyNotEquals(QuoteId, "");
		TestNGCustomReporter.log(logger, "Quote is successfully created: " +QuoteId+ " " +ReportBuilder.takesScreenshot());
		return QuoteId;	
	}
	
	
	public String createQuotewoOwnerchange() {
		elementHandler.waitforElement(QuotePageObjects.btnCreateQuote);
		elementHandler.clickElement(QuotePageObjects.btnCreateQuote,QuotePageObjects.textproposalname);
		actionHandler.waitForSomeTime(5000);
		String ProposalName = StringUtils.testDataModification(ExcelUtils.getDataByColumnName("Quote", "ProposalName"));
		elementHandler.writeText(QuotePageObjects.textproposalname, ProposalName);
		elementHandler.clickElement(QuotePageObjects.btnsave);
		actionHandler.waitForSomeTime(5000);
		String QuoteId= elementHandler.getText(QuotePageObjects.lblProposalId);
		AssertionHandler.verifyNotEquals(QuoteId, "", "Quote ID value is blank");
		javascripthandler.scrollIntoView(QuotePageObjects.lblApprlSegDefault);
		String ArrpvSegDeft= elementHandler.getText(QuotePageObjects.lblApprlSegDefault);
		String Segment= ExcelUtils.getDataByColumnName("Quote", "Apprv_Seg");
		AssertionHandler.verifyEquals(ArrpvSegDeft, Segment);
		TestNGCustomReporter.log(logger, "Approval Segment Default value is displayed as:" +ArrpvSegDeft +ReportBuilder.takesScreenshot());
		String ApprvSeg= elementHandler.getText(QuotePageObjects.lblApprvSeg);
		AssertionHandler.verifyEquals(ApprvSeg, Segment);
		TestNGCustomReporter.log(logger, "Approval Segment value is displayed as:" +ApprvSeg +ReportBuilder.takesScreenshot());
		javascripthandler.scrollIntoView(QuotePageObjects.lblProposalId);
		TestNGCustomReporter.log(logger, "Quote is successfully created: " +QuoteId+ " " +ReportBuilder.takesScreenshot());
		return QuoteId;		
	}
	
	public void logoutUser() {
		elementHandler.waitForElementTobeClickable(QuotePageObjects.lblUserMenu);
		elementHandler.clickElement(QuotePageObjects.lblUserMenu, QuotePageObjects.linkLogout);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(QuotePageObjects.linkLogout);
		TestNGCustomReporter.log(logger, "Logout successfully for the user");
	}
	
public String createQuotefrSpecificuser() { 
		elementHandler.waitforElement(QuotePageObjects.btnCreateQuote1);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(QuotePageObjects.btnCreateQuote1,QuotePageObjects.textproposalname);
		String ProposalName = StringUtils.testDataModification(ExcelUtils.getDataByColumnName("Quote", "ProposalName"));
		elementHandler.writeText(QuotePageObjects.textproposalname, ProposalName);
		elementHandler.clickElement(QuotePageObjects.btnsave,QuotePageObjects.btnEdit);
		/*elementHandler.waitForElementTobeClickable(QuotePageObjects.linkChange);
		elementHandler.clickElement(QuotePageObjects.linkChange);
		elementHandler.waitForXpathToLoad(lblNewOwner);
		String OwnerName= ExcelUtils.getDataByColumnName("Quote", "OwnerName");
		elementHandler.writeText(QuotePageObjects.txtOwner, OwnerName);
		elementHandler.clickElement(QuotePageObjects.btnSave);*/
		elementHandler.waitForElementTobeClickable(QuotePageObjects.btnEdit);
		elementHandler.clickElement(QuotePageObjects.btnEdit);
		javascripthandler.scrollIntoView(QuotePageObjects.lblApprovalSeg);
		elementHandler.selectByVisibleText(QuotePageObjects.drpdwnApprovalSegment, "FindLaw");
		javascripthandler.scrollIntoView(QuotePageObjects.lblQuote);
		elementHandler.clickElement(QuotePageObjects.btnSave1);
		elementHandler.waitForXpathToLoad(lblQuoteP1);
		String QuoteId= elementHandler.getText(QuotePageObjects.lblProposalId);
		AssertionHandler.verifyNotEquals(QuoteId, "", "Proposal Id not displayed");
		TestNGCustomReporter.log(logger, "Quote is successfully created: " +QuoteId+ " " +ReportBuilder.takesScreenshot());
		return QuoteId;	
	}

	public void ValidateNetValue() {
		windowHandler.refereshWebPage();
		elementHandler.waitForXpathToLoad(lblIncrement);
		String NetValue= elementHandler.getText(QuotePageObjects.lblIncremental);
		TestNGCustomReporter.log(logger, "Net Annual Value is displayed as  : " +NetValue+ " "+ ReportBuilder.takesScreenshot());
	}
	
	public void ValidateMarginTyp() {
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkLineItems);
		elementHandler.clickElement(AddProductsPageObjects.lnkLineItems,AddProductsPageObjects.lnkGoToList);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkGoToList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkIMSsuite);
		actionHandler.waitForSomeTime(10000);
		String val1= ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Margin_Type", 23);
		String[] listOfMargin = val1.split(",");
		javascripthandler.highlightElement(AddProductsPageObjects.lblMaterialNo);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblMarginType);
		String Value1= elementHandler.getText(AddProductsPageObjects.lblMarginType);
		AssertionHandler.verifyEquals(Value1, listOfMargin[0]);
		TestNGCustomReporter.log(logger, "Margin Type for IMS product Verified successfully" +ReportBuilder.takesScreenshot());
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkIMEProgram );
		elementHandler.clickElement(AddProductsPageObjects.lnkIMEProgram);
		actionHandler.waitForSomeTime(10000);
		javascripthandler.highlightElement(AddProductsPageObjects.lblMaterialNo);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblMarginType);
		String Value2= elementHandler.getText(AddProductsPageObjects.lblMarginType);
		AssertionHandler.verifyEquals(Value2, listOfMargin[1]);
		TestNGCustomReporter.log(logger, "Margin Type for IME Essential product Verified successfully" +ReportBuilder.takesScreenshot());
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkIMCProgram);
		elementHandler.clickElement(AddProductsPageObjects.lnkIMCProgram );
		actionHandler.waitForSomeTime(10000);
		javascripthandler.highlightElement(AddProductsPageObjects.lblMaterialNo);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblMarginType);
		String Value3= elementHandler.getText(AddProductsPageObjects.lblMarginType);
		AssertionHandler.verifyEquals(Value3, listOfMargin[2]);
		TestNGCustomReporter.log(logger, "Margin Type for IMC Catalyst product Verified successfully" +ReportBuilder.takesScreenshot());
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkIMPprogram );
		elementHandler.clickElement(AddProductsPageObjects.lnkIMPprogram );
		actionHandler.waitForSomeTime(10000);
		javascripthandler.highlightElement(AddProductsPageObjects.lblMaterialNo);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblMarginType);
		String Value4= elementHandler.getText(AddProductsPageObjects.lblMarginType);
		AssertionHandler.verifyEquals(Value4, listOfMargin[3]);
		TestNGCustomReporter.log(logger, "Margin Type for IMP Presence product Verified successfully" +ReportBuilder.takesScreenshot());
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
	}
	
	
	public void ValidateEBMarginTyp(int Row) {
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkLineItems);
		elementHandler.clickElement(AddProductsPageObjects.lnkLineItems);
		actionHandler.waitForSomeTime(2000);
		String val1= ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Margin_Type", Row);
		String[] val2= val1.split(",");
		
		elementHandler.waitForElementTobeClickable(QuotePageObjects.lnkReputation);
		elementHandler.clickElement(QuotePageObjects.lnkReputation );
		actionHandler.waitForSomeTime(10000);
		javascripthandler.highlightElement(AddProductsPageObjects.lblMaterialNo);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblMarginType);
		String Value2= elementHandler.getText(AddProductsPageObjects.lblMarginType);
		AssertionHandler.verifyEquals(Value2, val2[0]);
		TestNGCustomReporter.log(logger, "Margin Type for Findlaw Reputation Advisor product Verified successfully" +ReportBuilder.takesScreenshot());
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		
		elementHandler.waitForElementTobeClickable(QuotePageObjects.lnkLegal);
		elementHandler.clickElement(QuotePageObjects.lnkLegal );
		actionHandler.waitForSomeTime(10000);
		javascripthandler.highlightElement(AddProductsPageObjects.lblMaterialNo);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblMarginType);
		String Value3= elementHandler.getText(AddProductsPageObjects.lblMarginType);
		AssertionHandler.verifyEquals(Value3, val2[1]);
		TestNGCustomReporter.log(logger, "Margin Type for Findlaw Legal Leader product Verified successfully" +ReportBuilder.takesScreenshot());
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		
		elementHandler.waitForElementTobeClickable(QuotePageObjects.lnkImpact);
		elementHandler.clickElement(QuotePageObjects.lnkImpact );
		actionHandler.waitForSomeTime(10000);
		javascripthandler.highlightElement(AddProductsPageObjects.lblMaterialNo);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblMarginType);
		String Value4= elementHandler.getText(AddProductsPageObjects.lblMarginType);
		AssertionHandler.verifyEquals(Value4, val2[2]);
		TestNGCustomReporter.log(logger, "Margin Type for Findlaw Impact product Verified successfully" +ReportBuilder.takesScreenshot());
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		
		elementHandler.waitForElementTobeClickable(QuotePageObjects.lnkBuild);
		elementHandler.clickElement(QuotePageObjects.lnkBuild );
		actionHandler.waitForSomeTime(10000);
		javascripthandler.highlightElement(AddProductsPageObjects.lblMaterialNo);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblMarginType);
		String Value5= elementHandler.getText(AddProductsPageObjects.lblMarginType);
		AssertionHandler.verifyEquals(Value5, val2[3]);
		TestNGCustomReporter.log(logger, "Margin Type for Builder product Verified successfully" +ReportBuilder.takesScreenshot());
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
	}
	
	
	public String submitForApproval() {
		elementHandler.waitForElementTobeClickable(QuotePageObjects.btnSales);
		elementHandler.clickElement(QuotePageObjects.btnSales, QuotePageObjects.lnkApptusMgnt);
		actionHandler.waitForSomeTime(4000);
		elementHandler.clickElement(QuotePageObjects.lnkApptusMgnt, QuotePageObjects.lnkApprovalReq);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(QuotePageObjects.lnkApprovalReq, QuotePageObjects.lnkName);
		actionHandler.waitForSomeTime(5000);
		String name= elementHandler.getText(QuotePageObjects.lnkName);
		AssertionHandler.verifyNotEquals(name, "", "name is blank");
		elementHandler.clickElement(QuotePageObjects.lnkName,QuotePageObjects.lblApprovalReq);
		actionHandler.waitForSomeTime(5000);
		javascripthandler.scrollIntoView(QuotePageObjects.lnkReassign);
		elementHandler.clickElement(QuotePageObjects.lnkReassign, QuotePageObjects.txtApprover);
		actionHandler.waitForSomeTime(5000);
		String Approver=ExcelUtils.getDataByColumnName("Quote", "OwnerName");		
		elementHandler.writeText(QuotePageObjects.txtApprover, Approver, QuotePageObjects.txtComments);
		String Comment=ExcelUtils.getDataByColumnName("Quote", "Comments");
		elementHandler.writeText(QuotePageObjects.txtComments, Comment, QuotePageObjects.btnReassign);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(QuotePageObjects.btnReassign);
		actionHandler.waitForSomeTime(5000);
		return name;
		
	}
	
	public void validatePaymentOption() {
		elementHandler.clickElement(QuotePageObjects.btnEdit);
		actionHandler.waitForSomeTime(5000);
		javascripthandler.scrollIntoView(QuotePageObjects.drpdwnPaymentOption);
		elementHandler.clickElement(QuotePageObjects.drpdwnPaymentOption);
		actionHandler.waitForSomeTime(2000);
		TestNGCustomReporter.log(logger, "Validate the payment Options for Findlaw user" +ReportBuilder.takesScreenshot());
		String paymentOption= "Payment Express Auto EFT/Auto Charge";
		List<WebElement> options= elementHandler.getAllOptionsFromDropdown(QuotePageObjects.drpdwnPaymentOption);
		for(WebElement we:options)  
        {  
         for (int i=0; i<options.size(); i++){
        	 AssertionHandler.verifyNotEquals(we.getText(), paymentOption, "payment Option Auto EFT not removed from the drop down list for Findlaw user");
           }
         } 
		
	}
	
	public void validateAssetLine(List<String> assetText) {
		elementHandler.clickElement(AddProductsPageObjects.lnkLineItems);
		actionHandler.waitForSomeTime(2000);
		List<String> list = new ArrayList<String>();
		if (elementHandler.isElementDisplayed(AddProductsPageObjects.lnkGoToList)) {
			elementHandler.clickElement(AddProductsPageObjects.lnkGoToList);
			actionHandler.waitForSomeTime(5000);	
			for (WebElement list1 : AddProductsPageObjects.lnkOptions) {
				list.add(list1.getText());
		    }
		}
		
		for (WebElement list1 : AddProductsPageObjects.lblProducts) {
			list.add(list1.getText());
	    }
		
		for(int i=0;i<assetText.size();i++){
			if(assetText.contains(list.get(i))){
				AssertionHandler.verifyTrue(true, "Array list does not contains the value");
				TestNGCustomReporter.log(logger, "Validate the Asset Line Items sequencing" +ReportBuilder.takesScreenshot());
			}else{
				TestNGCustomReporter.log(logger, "Asset Line Items sequencing not matched properly" +ReportBuilder.takesScreenshot());
			}
		
	}

	}
	
	public void validateCOBButton() {
		boolean COBstatus= elementHandler.isElementDisplayed(QuotePageObjects.btnCOB1);
		if (COBstatus == false) {
			AssertionHandler.verifyTrue(true, "COB Button not revomed from the top of the screen");
		}
	}
	
	
	public void addWetSignature() {
		elementHandler.clickElement(QuotePageObjects.btnEdit);
		actionHandler.waitForSomeTime(2000);
		javascripthandler.scrollIntoView(QuotePageObjects.chkbxWetSignature);
		elementHandler.clickElement(QuotePageObjects.chkbxWetSignature);
		actionHandler.waitForSomeTime(2000);
		javascripthandler.scrollIntoView(QuotePageObjects.btnSave1);
		elementHandler.clickElement(QuotePageObjects.btnSave1);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForXpathToLoad(imgQuotepg);
	}
	
	public void attachFile() {
		elementHandler.waitForElementTobeClickable(QuotePageObjects.lnkNotesndAttachment);
		elementHandler.clickElement(QuotePageObjects.lnkNotesndAttachment);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(QuotePageObjects.btnAttachFile);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForElementTobeClickable(QuotePageObjects.btnFile);
		elementHandler.clickElement(QuotePageObjects.btnFile);
		String root= System.getProperty("user.dir");
		try {
			Runtime.getRuntime().exec(root.replace("\\", "/") +"/src/main/java/tr/findlaw/executables/FileUpload.exe");
		}catch(IOException e){
			e.printStackTrace();			
		}
		actionHandler.waitForSomeTime(4000);
		elementHandler.waitForElementTobeClickable(QuotePageObjects.btnAttach);
		elementHandler.clickElement(QuotePageObjects.btnAttach);
		AssertionHandler.verifyTrue(QuotePageObjects.lblFilename.getText().contains(".pdf"), "failed to upload file after generating the document");
		TestNGCustomReporter.log(logger, "Attaching file"+ReportBuilder.takesScreenshot());
		elementHandler.clickElement(QuotePageObjects.btnDone,QuotePageObjects.btnEdit);
		actionHandler.waitForSomeTime(5000);
	}
	
	public void submirOrder() {
		elementHandler.waitForElementTobeClickable(QuotePageObjects.btnSubmitOrder);
		elementHandler.clickElement(QuotePageObjects.btnSubmitOrder);
		actionHandler.waitForSomeTime(8000);
		TestNGCustomReporter.log(logger, "Validate Submit Order message" +ReportBuilder.takesScreenshot());
		driver.switchTo().alert().accept();
		actionHandler.waitForSomeTime(2000);
		TestNGCustomReporter.log(logger, "Clicked on OK button" +ReportBuilder.takesScreenshot());		
	}
	
	
	public void validateBridgeDiscount() {
		elementHandler.clickElement(AddProductsPageObjects.lnkLineItems);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lblLineItem.get(0));
		actionHandler.waitForSomeTime(2000);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblMultiYr);
		String lblBridge= elementHandler.getText(AddProductsPageObjects.lblBridge1);
		AssertionHandler.verifyNotEquals(lblBridge, " ", "Bridge value is blank for 1st product");
		String lblbridgeDisc= elementHandler.getText(AddProductsPageObjects.lblBridgeDisc);
		AssertionHandler.verifyEquals(lblbridgeDisc, "100", "bridge Discountvalue not matched properly for 1st product");
		TestNGCustomReporter.log(logger, "Validate bridge and Bridge Discount for 1st Product" +ReportBuilder.takesScreenshot());
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		
		elementHandler.clickElement(AddProductsPageObjects.lblLineItem.get(1));
		actionHandler.waitForSomeTime(2000);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblMultiYr);
		String lblBridge1= elementHandler.getText(AddProductsPageObjects.lblBridge1);
		AssertionHandler.verifyNotEquals(lblBridge1, " ", "Bridge value is blank for 2nd product");
		String lblbridgeDisc1= elementHandler.getText(AddProductsPageObjects.lblBridgeDisc);
		AssertionHandler.verifyEquals(lblbridgeDisc1, "100", "bridge Discountvalue not matched properly for 2nd product");
		TestNGCustomReporter.log(logger, "Validate bridge and Bridge Discount for 1st Product" +ReportBuilder.takesScreenshot());
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		
		elementHandler.clickElement(AddProductsPageObjects.lblLineItem.get(2));
		actionHandler.waitForSomeTime(2000);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblMultiYr);
		String lblBridge2= elementHandler.getText(AddProductsPageObjects.lblBridge1);
		AssertionHandler.verifyNotEquals(lblBridge2, " ", "Bridge value is blank for 3rd product");
		String lblbridgeDisc2= elementHandler.getText(AddProductsPageObjects.lblBridgeDisc);
		AssertionHandler.verifyEquals(lblbridgeDisc2, "100", "bridge Discountvalue not matched properly for 3rd product");
		TestNGCustomReporter.log(logger, "Validate bridge and Bridge Discount for 1st Product" +ReportBuilder.takesScreenshot());
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
						
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
