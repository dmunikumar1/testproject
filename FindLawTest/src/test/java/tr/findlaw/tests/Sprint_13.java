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

public class Sprint_13 extends FindlawBaseTest{

	
	/**
	 * Take an Existing Account which is having any one Blog product such as 41817449,41323281,41323282,41325003,41701486,41819162,41948733,41819163,41948732,40666911,40586465 as an Asset
	 * Select the product for which Renewal option is No          
	 * Submit For Approval process should be done   
	 * use Account: 1000592572 ,1000516447 ,1000080864   
	 */
	 @Test(enabled = true, description = "SOC-4542  When a AM, creates a renewal they should  be able to renew 90 days after renewal date but would go to approval (CPQ)")
	  public void soc_4542() {
     applicationLogin();
     accountpage = new Accountpage(driver);
     accountpage.searchExistAccount(45);
     contactsPage = new ContactsPage(driver);
     String ContactName= contactsPage.createNewContact();
     //contactsPage.searchExistContact(45);
     //String ContactName=  ExcelUtils.getDataByColumnName("Contacts", "Id");
     OpptyPage optypage = new OpptyPage(driver);
     optypage.createOpty();
     quotepage = new QuotePage(driver);
     String QuoteId=quotepage.createQuote();
	addproductspage = new AddProductsPage(driver);
	int ctrl = 13; 
	addproductspage.addProducts(ctrl,45);
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
	validateesignpage.validateEsignForRenew();
		
		 
	 }
	 
	 /*
		 * Take an Existing Account which have any one Blog product such as 41817449,41323281,41323282,41325003,41701486,41819162,41948733,41819163,41948732, 40666911,40586465 as an Asset
		 * Select the product for which Renewal option is Yes           
		 * Submit For Approval process is not needed for this scenario
		 * Use Account no:1004240911,1000025703 product:41325003.          
		 */	
	 @Test(enabled = true, description = "SOC-4542 When a AM, creates a renewal they should only be able to renew 90 days prior to renewal date (CPQ)")
     public void soc_4542_Renew() {
			applicationLogin();
			accountpage = new Accountpage(driver);	
			accountpage.searchExistAccount(46);
			contactsPage = new ContactsPage(driver);
			//contactsPage.searchExistContact(46);
			//String ContactName= ExcelUtils.getDataByColumnName("Contacts", "Id"); 
			String ContactName= contactsPage.createNewContact();
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			String QuoteId= quotepage.createQuote();
			addproductspage = new AddProductsPage(driver);
			int ctrl = 5; 
			addproductspage.addProducts(ctrl,46);
			searchquotepage = new SearchQuotePage(driver);
			searchquotepage.searchQuote(QuoteId);
			contact28page = new Contact28Page(driver);
			contact28page.addContact28(ContactName);
			searchquotepage = new SearchQuotePage(driver);
			searchquotepage.searchQuote(QuoteId);
			ExcelUtils.writeData("C:\\Users\\UX010052\\Documents\\Workspace\\FindLawTest\\src\\test\\resources\\testdata\\TestData.xls", "SOC_5389_Quote", QuoteId);
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
			validateesignpage.validateEsignForRenew(); 
	 }
	 
	 	/*
		 * Take any Existing Account and add any Blog products ex: social promotion product:41817449 twice to the cart
		 * Take any account and add any one of the engagement builder products like: 42089522,42089521,41180810,42089524
		 * Add above mentioned products twice and click on Reprice, validate the error message                    
		 */
	
	 @Test(enabled = true, description = "SOC-4544 Implement the max quantity business rule associated to Blogs and Enhancement product model products")
     public void soc_4544() {
			applicationLogin();
			accountpage = new Accountpage(driver);	
			accountpage.searchExistAccount(47);
			contactsPage = new ContactsPage(driver);
			contactsPage.createNewContact();
			//contactsPage.searchExistContact(47);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			String QuoteId= quotepage.createQuote();
			searchquotepage = new SearchQuotePage(driver);
			searchquotepage.searchQuote(QuoteId);
			addproductspage = new AddProductsPage(driver);
			int ctrl = 9; 
			addproductspage.addProducts(ctrl,47); 
	        
	 }
	 
	 /*
		 * Take any Existing Account which is not having below mentioned asset.
		 * 
		 * 41819164- Findlaw Engagement Builder product 
		 *  
		 * finalize the cart and complete the order
		 */
	
	 @Test(enabled = true, description = "SOC-4544 Implement the max quantity business rule associated to Blogs and Enhancement product model products")
  public void soc_4544_Order() {
			applicationLogin();
			accountpage = new Accountpage(driver);	
			accountpage.searchExistAccount(48);
			contactsPage = new ContactsPage(driver);
			String ContactName= contactsPage.createNewContact();
			//contactsPage.searchExistContact(48);
			//String ContactName= ExcelUtils.getDataByColumnName("Contacts", "Id");
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			String QuoteId= quotepage.createQuote();
			addproductspage = new AddProductsPage(driver);
			int ctrl = 3;  
			addproductspage.addProducts(ctrl,48); 
			contact28page = new Contact28Page(driver);
			contact28page.addContact28(ContactName);			
			ExcelUtils.writeData("C:\\Users\\UX010052\\Documents\\Workspace\\FindLawTest\\src\\test\\resources\\testdata", "SOC_5389_Quote", QuoteId);
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
			int ctrlval = 17;
			findlawordertoolpage.cobProcess(ctrlval);
			applicationLogin();
			searchquotepage = new SearchQuotePage(driver);
			searchquotepage.searchQuote(QuoteId);
			validateesignpage = new ValidateEsignPage(driver);
			validateesignpage.validateOrder();  
	        
	 }

}
