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

public class Sprint_18 extends FindlawBaseTest{
				 
 /*
 * Take any Existing Account which is not having any PPC products as an Asset and Search for PPC product:30010890,41876119,41877783
 * validate the Adjustment Type field for all products
 */
		 @Test(enabled = false, description = "SOC_6204_PPC Material that is configurable will have adjustment type set to None")
	     public void soc_6204() {		 
				applicationLogin();
				accountpage = new Accountpage(driver);	
				accountpage.searchExistAccount(61);
				contactsPage = new ContactsPage(driver);
				contactsPage.createNewContact();
				//contactsPage.searchExistContact(61);
				OpptyPage optypage = new OpptyPage(driver);
				optypage.createOpty();
				quotepage = new QuotePage(driver);
				quotepage.createQuote();
				addproductspage = new AddProductsPage(driver);
				int ctrl = 16; 
				addproductspage.addProducts(ctrl,61);        
		 }
 
 /*
  * Take any Existing Account which is not having any Blog products mentioned below and should have any one of Firmsite 111C/222C/333C/444C Renewal only product as an Asset  
  *  Search for products:41819162,41948733
  *  validate the informational message displayed in the cart page
  */
		 
	 @Test(enabled = false, description = "SOC_6196_Auto Include Set Up Fee for products in Blog/Engagement Product Model")
     public void soc_6196() {
			applicationLogin();
			accountpage = new Accountpage(driver);	
			accountpage.searchExistAccount(62);
			contactsPage = new ContactsPage(driver);
			contactsPage.createNewContact();
			//contactsPage.searchExistContact(62);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			quotepage.createQuote();
			addproductspage = new AddProductsPage(driver);
			int ctrl = 17; 
			addproductspage.addProducts(ctrl,62);        
	 }

	 
/*
 * take any Existing account which is not having any US PPC products and should have any firmsite product as an asset.
 * Add that product and validate all the fields in Product Attributes 
 */
	 @Test(enabled = false, description = "SOC_4547_CPQ Configure Material Data for PPC Product model & Pricing ")
     public void soc_4547() {	 
			applicationLogin();
			accountpage = new Accountpage(driver);	
			accountpage.searchExistAccount(64);
			contactsPage = new ContactsPage(driver);
			contactsPage.createNewContact();
			//contactsPage.searchExistContact(64);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			quotepage.createQuote();
			addproductspage = new AddProductsPage(driver);
			int ctrl = 12; 
			addproductspage.addProducts(ctrl,64);  
			       
	 } 
	 
/*
 * take any Existing account which is not having any US PPC products and should have any firmsite product as an asset.
 * 	Add that product and validate submit for approval if the cart is less than $500 and more than $10,000
 */
	 
	 @Test(enabled = false, description = "SOC_4550_CDC should not be able to purchase a single bundle with a quantity of less than $500/mon or more than $10K/mon without submitting for approval. ")
     public void SOC_4550() {	 
			applicationLogin();
			accountpage = new Accountpage(driver);	
			accountpage.searchExistAccount(70);
			contactsPage = new ContactsPage(driver);
			contactsPage.createNewContact();
			//contactsPage.searchExistContact(70);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			quotepage.createQuote();
			addproductspage = new AddProductsPage(driver);
			addproductspage.validateSubmitForApproval(70);  
			       
	 }
	 
/*
 * Take any Existing account which is having any one of  IMP, IME, IMS products in the Assets and not having any PPC product in the asset.
 * Add PPC products: 41876119, 41877783 to the cart and Finalize the cart
 * Use Account: 1000064599,1000027444,1003891133	 
 */
	 
	 @Test(enabled = true, description = "SOC_4554_intergration testing for order PPC product model for CPQ,ESI, SFDC, ETL, sap FindLaw")
     public void soc_4554() {	 
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(71);
		contactsPage = new ContactsPage(driver);
		String ContactName = contactsPage.createNewContact();
		// contactsPage.searchExistContact(71);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId = quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		int ctrl = 3;
		addproductspage.addProducts(ctrl, 71);
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		contact28page = new Contact28Page(driver);
		contact28page.addContact28(ContactName);
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
		int ctrlval = 21;
		findlawordertoolpage.cobProcess(ctrlval);
		applicationLogin();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		validateesignpage = new ValidateEsignPage(driver);
		validateesignpage.validateOrder();  
			       
	 } 
	 

}
