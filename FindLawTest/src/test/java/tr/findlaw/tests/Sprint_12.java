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
import tr.findlaw.pages.MasterContactsPage;
import tr.findlaw.pages.OpptyPage;
import tr.findlaw.pages.PasswordHoldersPage;
import tr.findlaw.pages.QuotePage;
import tr.findlaw.pages.SearchQuotePage;
import tr.findlaw.pages.SendEsignPage;
import tr.findlaw.pages.ValidateEsignPage;

public class Sprint_12 extends FindlawBaseTest {
			
/*
 * Take any Existing Account which is not having any Blog products as an asset add Blog products:41819162,41817449 to the cart
 * follow rule to add Blog products: 41817449,41819162
 * Finalize the cart and complete the order	
 */
	 @Test(enabled = false, description = "SOC-4524 CPQ Configuration for Material for Blogs Product model")
	  public void soc_4524() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(38);
		contactsPage = new ContactsPage(driver);
		String ContactName = contactsPage.createNewContact(); 
		/*contactsPage = new ContactsPage(driver);
		contactsPage.searchExistContact(38);
		String ContactName = ExcelUtils.getDataByColumnName("Contacts", "Id");*/
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId = quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		int ctrl = 3;
		addproductspage.addProducts(ctrl, 38);
		contact28page = new Contact28Page(driver);
		contact28page.addContact28(ContactName);
		//String QuoteId = "Q-00429810";
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
		int ctrlval = 24;		//10
		findlawordertoolpage.cobProcess(ctrlval);
		applicationLogin();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		validateesignpage = new ValidateEsignPage(driver);
		validateesignpage.validateOrder();   	 
	 }
	 
/*
 * Take any Existing Account which is not having this product and add Call Tracking product: 40586465 to the cart
 * 
 * 	Finalize the cart and complete the order 
 */
	 @Test(enabled = false, description = "SOC-4528 Analysis and Development for integration SFDC & SAP")
	public void SOC_4528() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(39);
		contactsPage = new ContactsPage(driver);
		String ContactName = contactsPage.createNewContact();
		/*contactsPage = new ContactsPage(driver);
		contactsPage.searchExistContact(39);
		String ContactName= ExcelUtils.getDataByColumnName("Contacts", "Id");	*/	 
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId = quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		int ctrl = 4;
		addproductspage.addProducts(ctrl, 39);
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
		int ctrlval = 11;
		findlawordertoolpage.cobProcess(ctrlval);
		applicationLogin();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		validateesignpage = new ValidateEsignPage(driver);
		validateesignpage.validateOrder();  	 
	 }
	 
	 
/*
 * Take any Existing Account which is not having this product and add Animation Essential product: 41323281 to the cart
 *  follow rule to add this product
 *  Firmsite 333c/444c product should be present as an asset.
 * 	Finalize the cart and complete the order
 */
	 @Test(enabled = false, description = "SOC-4530 Testing Only- Ability to discount by percentage for Blogs model products (CPQ)")
     public void soc_4530() {
         applicationLogin();
         accountpage = new Accountpage(driver);
         accountpage.searchExistAccount(40);
         contactsPage = new ContactsPage(driver);
         String ContactName= contactsPage.createNewContact();
         //contactsPage.searchExistContact(40);
         OpptyPage optypage = new OpptyPage(driver);
         optypage.createOpty(); 
         quotepage = new QuotePage(driver);
         String QuoteId=quotepage.createQuote();
         addproductspage = new AddProductsPage(driver);
         addproductspage.validateGuidance(40);
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
		int ctrlval = 12;
		findlawordertoolpage.cobProcess(ctrlval);
		applicationLogin();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		validateesignpage = new ValidateEsignPage(driver);
		validateesignpage.validateOrder(); 	
	 }	 
	
/*
 * Take any Existing Account which is not having this product and add Mobile Essential product:41325003 to the cart
 *  follow rule to add this product : Firmsite 333c/444c product should be present as an asset.
 * 	 Finalize the cart and complete the order
 */
	 @Test(enabled = false, description = "SOC-4531 Testing Only- Ability to bridge some of the products within the Blogs product model for only 30,60,90 days at 100 percent (UI will show 1,2 3 month option) (CPQ)")
     public void SOC_4531() {
         applicationLogin();
         accountpage = new Accountpage(driver);
         accountpage.searchExistAccount(41);
         contactsPage = new ContactsPage(driver);
         String ContactName= contactsPage.createNewContact();
         //contactsPage.searchExistContact(41);
         OpptyPage optypage = new OpptyPage(driver);
         optypage.createOpty(); 
         quotepage = new QuotePage(driver);
         String QuoteId=quotepage.createQuote();
         addproductspage = new AddProductsPage(driver);
         int ctrl = 4;
         addproductspage.addProducts(ctrl,41);   
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
		int ctrlval = 13;
		findlawordertoolpage.cobProcess(ctrlval);
		applicationLogin();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		validateesignpage = new ValidateEsignPage(driver);
		validateesignpage.validateOrder(); 	
	 }
	 
	 /*
	  *Take any Existing Account which is not having any blog products and add blog products:41091534,30405072 or 41166362,30405072 and add it to the cart 
	  * validate the contract term for both. If 41091534 product is not available then use 41166362 product. 
	  */
 @Test(enabled = true, description = "SOC-4532 Testing Only - Ability to chose contract terms available 1,2, 3, for some Blogs product model products (CPQ)")
 public void soc_4532() {
     applicationLogin(); 
     accountpage = new Accountpage(driver);
     accountpage.searchExistAccount(42);
     contactsPage = new ContactsPage(driver);
     String ContactName= contactsPage.createNewContact();
     //contactsPage.searchExistContact(42);
     OpptyPage optypage = new OpptyPage(driver);
     optypage.createOpty(); 
     quotepage = new QuotePage(driver);
     String QuoteId=quotepage.createQuote();
     addproductspage = new AddProductsPage(driver);
     int ctrl = 4; 
     addproductspage.addProducts(ctrl,42);   
	contact28page = new Contact28Page(driver);
	contact28page.addContact28(ContactName);
	passwordholderspage = new PasswordHoldersPage(driver);
	passwordholderspage.addPasswordHolders(2);
	mastercontactspage = new MasterContactsPage(driver);
	mastercontactspage.addmasterContacts();
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
	int ctrlval = 14;  //update cob
	findlawordertoolpage.cobProcess(ctrlval);
	applicationLogin();
	searchquotepage = new SearchQuotePage(driver);
	searchquotepage.searchQuote(QuoteId);
	validateesignpage = new ValidateEsignPage(driver);
	validateesignpage.validateOrder(); 	
		 }
		 

 /*
  * Take any Existing Account which is not having these products and add Blog_CPQ product:41817449,30405072 to the cart
  * follow rule to add product: 41817449 (any IMC/IMR/IME/IMS product to be present as an asset)
  * Finalize the cart,Validate year 2-3 for all products
  */
		 @Test(enabled = false, description = "SOC-4534 Testing only- Ability to determine the price for year 2 & 3  (CPQ) for Blog product model products")
	     public void soc_4534() {
             applicationLogin();
             accountpage = new Accountpage(driver);
             accountpage.searchExistAccount(43);
             contactsPage = new ContactsPage(driver);
             contactsPage.createNewContact();
             //contactsPage.searchExistContact(43);
             OpptyPage optypage = new OpptyPage(driver);
             optypage.createOpty(); 
             quotepage = new QuotePage(driver);
             quotepage.createQuote();
             addproductspage = new AddProductsPage(driver);
             addproductspage.validateYear23Blogprod(43);                                                 
	     }
		 

/*
 * Take any Existing Account which is having IMP product:41915678 as an Asset and add it
 * 
 * 	 Validate all the columns in the cart
 */
		 @Test(enabled = false, description = "SOC-5457 Adjust Column Display for Installed Products in Cart")
	     public void soc_5457() {
	         applicationLogin();
	         accountpage = new Accountpage(driver);
	         accountpage.searchExistAccount(44);
	         contactsPage = new ContactsPage(driver);
	         contactsPage.createNewContact();
	         //contactsPage.searchExistContact(44);
	         OpptyPage optypage = new OpptyPage(driver);
	         optypage.createOpty(); 
	         quotepage = new QuotePage(driver);
	         quotepage.createQuote();
	         addproductspage = new AddProductsPage(driver);
	         int ctrl = 11; 
	         addproductspage.addProducts(ctrl,44);   	 
		 }
	 
}
