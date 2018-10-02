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
import tr.findlaw.pages.SearchPage;
import tr.findlaw.pages.SearchQuotePage;
import tr.findlaw.pages.SendEsignPage;
import tr.findlaw.pages.ValidateEsignPage;

public class Sprint_9 extends FindlawBaseTest {
   	
	/*
	 * Take any Existing Account which is not having IM products :41777796,41822784,41892830 as an Asset  and add them to the cart
	 * 
	 * Finalize the cart and validate Year 2-3 price for them 
	 */
	
@Test(enabled = false, priority=1, description = "SOC-4108 Ability to determine the price for year 2 & 3 for new of IM Solution & FirmsitesAs a sales rep, I should be see the price for year 2 & 3 for new of IM Solution & Firmsites")
     public void soc_4108_IMProducts() {
         applicationLogin(); 
         accountpage = new Accountpage(driver);
         accountpage.searchExistAccount(15);
         contactsPage = new ContactsPage(driver);
         contactsPage.createNewContact();
         //contactsPage.searchExistContact(15);
         OpptyPage optypage = new OpptyPage(driver);
         optypage.createOpty(); 
         quotepage = new QuotePage(driver);
         String QuoteId=  quotepage.createQuote();
         addproductspage = new AddProductsPage(driver);
         String year23[]= addproductspage.year23IMProducts(15,QuoteId); 
         addproductspage. validateYear23IM(year23);
     	
     }
    

/*
 * Take any Existing Account which is not having Firmsite products: 40483693,40483696,40483699,40485592 as an Asset 
 * 
 * Add them to the cart and validate Year 2-3 price for them
 * 
 */
     @Test(enabled = false, priority=2, description = "SOC-4108 Ability to determine the price for year 2 & 3 for new of IM Solution & FirmsitesAs a sales rep, I should be see the price for year 2 & 3 for new of IM Solution & Firmsites")
     public void soc_4108_FirmsiteProducts() {
    	 applicationLogin();
         accountpage = new Accountpage(driver);
         accountpage.searchExistAccount(16);
         contactsPage = new ContactsPage(driver);
         contactsPage.createNewContact();
         //contactsPage.searchExistContact(16);
         OpptyPage optypage = new OpptyPage(driver);
         optypage.createOpty();
         quotepage = new QuotePage(driver);
         String QuoteId=  quotepage.createQuote();
    	 //String QuoteId= ExcelUtils.getDataByColumnNameWithRowIndex("Quote", "Name", 16);
    	 searchquotepage = new SearchQuotePage(driver);
 		 searchquotepage.searchQuote(QuoteId);
         addproductspage = new AddProductsPage(driver);
         addproductspage.validateYear23Firmsite(16,QuoteId);                                            
     }
     
     
/*
 * Take any Existing Account which is having 41053157 FNDLW PREMIUM PROFILE - NFC as an Asset
 * Add Firmsite products: 40483693,40483696 and 41056102 (FNDLW FOCUS PAGE ) to the Cart and validate the Error Messages
 * This scenario covers SOC_5388, SOC_5451
 * //Use Account No:1000786395 for this scenario or as mentioned in the QC
 */
     @Test(enabled = true, description = "SOC-4449 Implement the business rules associated to Firmsite product model products")
     public void soc_4449() {
		applicationLogin();
		accountpage = new Accountpage(driver);		
		accountpage.searchExistAccount(17);
		contactsPage = new ContactsPage(driver);
		contactsPage.createNewContact();
		//contactsPage.searchExistContact(17);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		int ctrl = 8; 
		addproductspage.addProducts(ctrl,17);        		   
     }
     
     

/*
 * Take any Existing Account which is not having IMR product as an Asset:42124052 and that to the cart
 *    
 * Finalize the Cart and complete the order   
 */
     @Test(enabled = false, description = "SOC-5390 Testing Only of Story SOC-4775 Configure and Validate IM Solution ( IMR) & pricing in US)")
     public void soc_5390() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(19);
		contactsPage = new ContactsPage(driver);
		String ContactName= contactsPage.createNewContact();
		//contactsPage.searchExistContact(19);
		//String ContactName=ExcelUtils.getDataByColumnNameWithRowIndex("Contacts", "Id", 19);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId= quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		int ctrl = 1; 
		addproductspage.addProducts(ctrl,19); 
		searchquotepage = new SearchQuotePage(driver);
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
		int ctrlval = 24;  //6
		findlawordertoolpage.cobProcess(ctrlval);
		applicationLogin();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		validateesignpage = new ValidateEsignPage(driver);
		validateesignpage.validateOrder();          
            		   
     }
     
/*
 * Take an Existing Account which is having EB (SUPER LAWYERS EXP ONLINE ATTY PROFILE) product: 30572896 as an Asset and add that to the cart
 *     
 * Finalize the cart and complete the order . Validate the COB message for adding Renewal product. 
 */
     @Test(enabled = false, description = "SOC-5391 Testing Only of Story SOC-4496 Configure assets for the existing customers with EB product model products")
     public void soc_5391() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(20);
		contactsPage = new ContactsPage(driver);
		String ContactName= contactsPage.createNewContact();
		//contactsPage.searchExistContact(20);
		//String ContactName= ExcelUtils.getDataByColumnNameWithRowIndex("Contacts", "Id", 20);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId= quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		int ctrl = 5; 
		addproductspage.addProducts(ctrl,20);  
		searchquotepage = new SearchQuotePage(driver);
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
		validateesignpage.validateEsign1();
		//String QuoteId= ExcelUtils.getDataByColumnNameWithRowIndex("Quote", "Name", 20);
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		findlawordertoolpage = new FindLawOrderToolPage(driver);
		findlawordertoolpage.clickCOB();
		findlawordertoolpage.loginWithSafeuserId(QuoteId);
		int ctrlval = 7;
		findlawordertoolpage.cobProcess(ctrlval);
		applicationLogin();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		validateesignpage = new ValidateEsignPage(driver);
		validateesignpage.validateOrder();
            		   
     }
     
    
/*
 * Take an Existing Account which is not having Firmsite 333C product:40483699  as an Asset and add that to cart
 *      
 * Finalize the cart and complete the order    
 *    
 */   
     @Test(enabled = false, description = "SOC-5392 Testing Only of Story SOC-814 As a FindLaw user, I should be able to launch the COB link or button  within the CPQ system.")
 	public void soc_5392() {
 		applicationLogin();
 		accountpage = new Accountpage(driver);
 		accountpage.searchExistAccount(21);
 		contactsPage = new ContactsPage(driver);
 		String ContactName=contactsPage.createNewContact();
 		//String ContactName= ExcelUtils.getDataByColumnNameWithRowIndex("Contacts", "Id",21);
 		//contactsPage.searchExistContact(21);
 		OpptyPage optypage = new OpptyPage(driver);
 		optypage.createOpty(); 
 		quotepage = new QuotePage(driver);
 		String QuoteId=quotepage.createQuote();
 		addproductspage = new AddProductsPage(driver);
 		int ctrl = 1; 
 		addproductspage.addProducts(ctrl,21);
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
 		//String QuoteId= ExcelUtils.getDataByColumnNameWithRowIndex("Quote", "Name",21);
 		searchquotepage = new SearchQuotePage(driver);
 		 searchquotepage.searchQuote(QuoteId);
 		 searchpage = new SearchPage(driver);
 		 searchpage.clickCOBfromProposals(QuoteId);
 		 findlawordertoolpage= new FindLawOrderToolPage(driver);
 		findlawordertoolpage.loginWithSafeuserId(QuoteId);
 		int ctrlval=24;		//23
 		findlawordertoolpage.cobProcess(ctrlval);
 		applicationLogin();
 		searchquotepage = new SearchQuotePage(driver);
 		searchquotepage.searchQuote(QuoteId);
 		validateesignpage = new ValidateEsignPage(driver);
 		validateesignpage.validateOrder();	
 	}
    
     
}
