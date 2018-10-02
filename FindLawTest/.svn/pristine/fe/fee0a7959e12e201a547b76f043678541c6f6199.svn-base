package tr.findlaw.tests;

import org.testng.annotations.Test;
import com.framework.utils.ExcelUtils;
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


public class SAP_Scenarios  extends FindlawBaseTest{
		
	// Add Firmsite and IM product, 40483699 - contract-myr-1 yr,discount 10%,bridge- 2 months
	//41892830 -IMC- contract 1yr, dicount-10%, bridge-1 month, ex: Q-00247425
	@Test(enabled = false, description = "SAP Scenario 1st")
	public void SAP_4719() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(65);
		contactsPage = new ContactsPage(driver);
		//contactsPage.searchExistContact(65);
		//String ContactName = ExcelUtils.getDataByColumnName("Contacts", "Id");
		String ContactName= contactsPage.createNewContact();
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId = quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		int ctrl = 41;
		addproductspage.addProducts(ctrl, 65);
		SearchQuotePage searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
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
		int ctrlval = 24;	//16
		findlawordertoolpage.cobProcess(ctrlval);
		applicationLogin();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		validateesignpage = new ValidateEsignPage(driver);
		validateesignpage.validateOrder();

	}

	 //42089521- contract 1yr- myr,discount 10,,ex: Q-00261687
	 @Test(enabled = false, description = "SAP scenario 2")
	    public void SAP_4469() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(66);
		contactsPage = new ContactsPage(driver);
		String ContactName = contactsPage.createNewContact();
		//String ContactName= ExcelUtils.getDataByColumnName("Contacts", "Id");
		//contactsPage.searchExistContact(66);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId = quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		int ctrl = 42; 
		addproductspage.addProducts(ctrl,66); 
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
		int ctrlval = 9;
		findlawordertoolpage.cobProcess(ctrlval);
		applicationLogin();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		validateesignpage = new ValidateEsignPage(driver);
		validateesignpage.validateOrder();   
		 
	 }
	
	 //any account with engagement renewal product-41819164,contract 1yr, discount-10%,Q-00274640  
	 @Test(enabled = false, description = "SAP 3rd scenario")
     public void SAP_5389() {
			applicationLogin();
			accountpage = new Accountpage(driver);
			accountpage.searchExistAccount(67);
			contactsPage = new ContactsPage(driver);
			String ContactName= contactsPage.createNewContact();
			//contactsPage.searchExistContact(67);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			String QuoteId= quotepage.createQuote();	
			addproductspage = new AddProductsPage(driver);
			int ctrl = 19; 
			addproductspage.addProducts(ctrl,67);          
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
	 }
	 
	 @Test(enabled = false, description = "SAP scenario 4th")
	    public void SAP_5571() {		
		applicationLogin();		
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(68);		
		contactsPage = new ContactsPage(driver);
		String ContactName = contactsPage.createNewContact();
		//String ContactName= ExcelUtils.getDataByColumnName("Contacts", "Id");
		//contactsPage.searchExistContact(68);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId = quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		int ctrl = 42;
		addproductspage.addProducts(ctrl,68); 
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
		int ctrlval = 9;
		findlawordertoolpage.cobProcess(ctrlval);
		applicationLogin();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		validateesignpage = new ValidateEsignPage(driver);
		validateesignpage.validateOrder();   
		 
	 }
	 
	 @Test(enabled = false, description = "SAP scenario 5th")
	    public void SAP_4730() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(69);
		contactsPage = new ContactsPage(driver);
		String ContactName = contactsPage.createNewContact();
		//String ContactName= ExcelUtils.getDataByColumnName("Contacts", "Id");
		//contactsPage.searchExistContact(69);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId = quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		int ctrl = 42; 
		addproductspage.addProducts(ctrl,69); 
		contact28page = new Contact28Page(driver);
		contact28page.addContact28(ContactName);
		// String ProposalName=ExcelUtils.getDataByColumnName("Quote", "Name");
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
		int ctrlval = 9;
		findlawordertoolpage.cobProcess(ctrlval);
		applicationLogin();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		validateesignpage = new ValidateEsignPage(driver);
		validateesignpage.validateOrder();   
		 
	 } 
	 
}
