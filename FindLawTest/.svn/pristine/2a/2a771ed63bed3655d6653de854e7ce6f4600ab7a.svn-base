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

public class Sprint_14 extends FindlawBaseTest{	

/*
 * Take any Existing Account which is not having Firmsite 333C as an Asset and add that product
 * 
 * Finalize the cart,validate the Adjustment type and complete the order
 */
	 @Test(enabled = false, description = "SOC-5538 Within the Cart, update the Adjustment Type options for FindLaw")
	  public void SOC_5538() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(49);
		contactsPage = new ContactsPage(driver);
		String ContactName= contactsPage.createNewContact();
		//String ContactName = ExcelUtils.getDataByColumnName("Contacts", "Id");
		//contactsPage.searchExistContact(49);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId = quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		int ctrl = 2;
		addproductspage.addProducts(ctrl, 49);
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
		int ctrlval = 24; // 1
		findlawordertoolpage.cobProcess(ctrlval);
		applicationLogin();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		validateesignpage = new ValidateEsignPage(driver);
		validateesignpage.validateOrder();   
		 
	 }
	  
/*
 * Take any Existing Account which is not having IMS or IME products:41777796,41822784 as an Asset
 * 
 * Add both IMS and IME product to the cart and validate the submit for Approval and Finalize buttons
 * 
 * Finalize the cart and complete the order
 */	
	 @Test(enabled = false, description = "SOC-5900 Implement approval rule for 1 year contract term for IMS and IME for new products")
	  public void soc_5900() {
	  applicationLogin();
	    accountpage = new Accountpage(driver);
	    accountpage.searchExistAccount(50); 
	    contactsPage = new ContactsPage(driver);
	    String ContactName= contactsPage.createNewContact();
	    //String ContactName=  ExcelUtils.getDataByColumnName("Contacts", "Id");
	    //contactsPage.searchExistContact(50);
	    OpptyPage optypage = new OpptyPage(driver);
	    optypage.createOpty(); 
	    quotepage = new QuotePage(driver);
	    String QuoteId=quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		int ctrl = 18; 
		addproductspage.addProducts(ctrl,50); 
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
		int ctrlval = 24;	//19
		findlawordertoolpage.cobProcess(ctrlval);
		applicationLogin();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		validateesignpage = new ValidateEsignPage(driver);
		validateesignpage.validateOrder();   
		 
	 }	 
	 
}
