package tr.findlaw.tests;

import org.testng.annotations.Test;
import tr.findlaw.basetest.FindlawBaseTest;
import tr.findlaw.pages.Accountpage;
import tr.findlaw.pages.AddProductsPage;
import tr.findlaw.pages.Contact28Page;
import tr.findlaw.pages.ContactsPage;
import tr.findlaw.pages.EsignProcessPage;
import tr.findlaw.pages.FindLawOrderToolPage;
import tr.findlaw.pages.GenerateDocumentPage;
import tr.findlaw.pages.OpptyPage;
import tr.findlaw.pages.QuotePage;
import tr.findlaw.pages.SearchQuotePage;
import tr.findlaw.pages.SendEsignPage;
import tr.findlaw.pages.ValidateEsignPage;

public class Sprint_25 extends FindlawBaseTest {

	/* 
	 * Integration scenario- only order creation is doing in SFDC
	 * Take any Existing account which is not having these products as an asset and add 41056102 (FNDLW PREMIUM PROFILE - NFC) and 41053157 (FNDLW FOCUS PAGE - NFC) in to the cart from PA/GEO page
	 * Finalize and Complete the order
	 * Use Account: 1000014325,1000022370,1004601163
	 */
	
	 @Test(enabled = false, description = "SOC_7018_Testing Only for Material # 41056102 FNDLW Focus Page- NFC & Material # 41053157 FNDLW PREMIUM PROFILE – NFC")
     public void soc_7018() {	 
			applicationLogin();
			accountpage = new Accountpage(driver);	
			accountpage.searchExistAccount(93);
		    contactsPage = new ContactsPage(driver);
			String ContactName= contactsPage.createNewContact();
			//contactsPage.searchExistContact(93);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			String QuoteId= quotepage.createQuote();
			addproductspage = new AddProductsPage(driver);
			addproductspage.addPAGEOProduct(93); 
			contact28page = new Contact28Page(driver);
			contact28page.addContact28(ContactName);
			searchquotepage = new SearchQuotePage(driver);
			searchquotepage.searchQuote(QuoteId);
			generateDocumentPage = new GenerateDocumentPage(driver);
			generateDocumentPage.generateDocument();
			sendesignPage = new SendEsignPage(driver);
			sendesignPage.sendForEsign();
			webmailLogin();
			esignprocesspage = new EsignProcessPage(driver);
			esignprocesspage.esignProcess();
			applicationLogin();
			searchquotepage = new SearchQuotePage(driver);
			searchquotepage.searchQuote(QuoteId);
			validateesignpage = new ValidateEsignPage(driver);
			validateesignpage.validateEsign();
			findlawordertoolpage = new FindLawOrderToolPage(driver);
			findlawordertoolpage.clickCOB();
			findlawordertoolpage.loginWithSafeuserId(QuoteId);
			int ctrlval = 24; // need to check
			findlawordertoolpage.cobProcess(ctrlval);
			applicationLogin();
			searchquotepage = new SearchQuotePage(driver);
			searchquotepage.searchQuote(QuoteId);
			validateesignpage = new ValidateEsignPage(driver);
			validateesignpage.validateOrder();
			
	 }	
	 
	
/*
 * Take any Existing account which is having IMS,IME and IMP as an asset and does not have Bundle 1/4 as an asset.
 * Add products 41876119 and 41877783 individually into the cart and validate the approval triggered or not.
 * use: 1004437332,1004436781 - have IM products as an asset and it does not have Bundle 1/4
 */	 
	 @Test(enabled = false, description = "SOC_6684_Approval Rule for PPC Additional Landing Page")
     public void soc_6684() {	 
			applicationLogin();
			accountpage = new Accountpage(driver);	
			accountpage.searchExistAccount(94);
			contactsPage = new ContactsPage(driver);
			contactsPage.createNewContact();
			//contactsPage.searchExistContact(94);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			quotepage.createQuote();
			addproductspage = new AddProductsPage(driver);
			addproductspage.validateApprovalButtonPPC(94);          
	 }	
	 
	
/*
 * Take any Existing account and add any products from PA/GEO page which have inventory as 0 and check whether checkbox is disabled or not 	  
 * combining SOC_7490, SOC_7349, SOC_6623
 */
	 @Test(enabled = true, description = "SOC_6854_Unable to add PA/GEO product to the cart if the inventory is sold out")
     public void soc_6854() {	 
			applicationLogin();
			accountpage = new Accountpage(driver);	
			accountpage.searchExistAccount(95);
			contactsPage = new ContactsPage(driver);
			contactsPage.createNewContact();
			//contactsPage.searchExistContact(95);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			quotepage.createQuote();
			addproductspage = new AddProductsPage(driver);
			addproductspage.validateCheckbox(95);          
	 }
}
