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

public class Sprint_11 extends FindlawBaseTest{
/*
 * Take an existing Account which is not having IM Products:41777796,41822784,41892830 as an Asset and also follow rule for adding IMS and IME products.
 * so select an account which have less no of assets.
 * Add them to cart and based on the condition it will be Finalize or Submit For Approval
 */
	 @Test(enabled = false, description = "SOC-1416 P1R1 Approvals - Phase 1 - Actual Segment Approval Flow and Basic Dimensions - Findlaw Segment")
    public void soc_1416() {
			applicationLogin();
			accountpage = new Accountpage(driver);
			accountpage.searchExistAccount(25);
			contactsPage = new ContactsPage(driver);
			contactsPage.createNewContact();
			contactsPage.searchExistContact(25);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			quotepage.createQuote();		
			addproductspage = new AddProductsPage(driver); 
			addproductspage.validateApprovalFinalizeButton(25); 	 
	 }
	
	 
/*
 * Take any Existing account which is not having Firmsite 333C product as an Asset and add it to the cart
 * Finalize the cart and complete the order 
 */
@Test(enabled = false, description = "SOC-4077 Firmsite 333C Interface to send Order data from CPQ to ESI for FindLaw")
	   public void soc_4077() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(26);
		contactsPage = new ContactsPage(driver);
		String ContactName = contactsPage.createNewContact();
		// contactsPage.searchExistContact(26);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId = quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		int ctrl = 2;
		addproductspage.addProducts(ctrl, 26);
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
		int ctrlval = 24;
		findlawordertoolpage.cobProcess(ctrlval);
		applicationLogin();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		validateesignpage = new ValidateEsignPage(driver);
		validateesignpage.validateOrder(); 		 
	 }
	 	 
/*
 * Take any Existing Account which is having Firmsite Renewal only product: 40483699/40483693 as an Asset
 * 1003616993
 * Accounts for firmsite 333C renewal:1000016706,1000025703,1000028150,1000033760,1000080864
 * Account for Firmsite 444C Renewal- 1000027753
 * Add that product to the cart and Submit for approval process with complete the order
 */
	 @Test(enabled = false, description = "SOC-4116 Testing Only -As an AM, I can create renewal order for renewal only products FS, & renewals for Firmsite 111C, 222C, 333C, 444C & renewals IM Solutions (exclude IMR)")
	  public void soc_4116_RenewalOnly() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(27);
		contactsPage = new ContactsPage(driver);
		String ContactName = contactsPage.createNewContact();
		// contactsPage.searchExistContact(27);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId = quotepage.createQuote();
		String filepath = "/src/test/resources/testdata/TestData.xlsx";
		ExcelUtils.writeData(filepath, "createQuote()", QuoteId);
		addproductspage = new AddProductsPage(driver);
		int ctrl = 5;
		addproductspage.addProducts(ctrl, 27);
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
		validateesignpage.validateEsignForRenew(); 	 
	 }
		 
/*
 * Take any Existing Account which is having Firmsite product:40483699 as an Asset
 * 
 * 	Add that product,Finalize the cart and complete the order 
 */
	  @Test(enabled = false, description = "SOC-4116 Testing Only -As an AM, I can create renewal order for renewal only products FS, & renewals for Firmsite 111C, 222C, 333C, 444C & renewals IM Solutions (exclude IMR)\")")
      public void soc_4116_Firmsite() {
      applicationLogin();
      accountpage = new Accountpage(driver);
      accountpage.searchExistAccount(28);
      contactsPage = new ContactsPage(driver);
      String ContactName=contactsPage.createNewContact();
      //String ContactName= ExcelUtils.getDataByColumnName("Contacts", "Id");
      //contactsPage.searchExistContact(28);
      OpptyPage optypage = new OpptyPage(driver);
      optypage.createOpty(); 
      quotepage = new QuotePage(driver);
      String QuoteId=quotepage.createQuote();
      addproductspage = new AddProductsPage(driver);
      int ctrl = 5; 
      addproductspage.addProducts(ctrl,28);                      
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
		validateesignpage.validateEsignForRenew();                        
      }
	  	 	  
/*
 *  Take any Existing Account which is not having EB products as an asset and should have less no of assets.
 *  Add EB Products:41870119,42089521,42089522 to the cart
 * 	Finalize the cart and complete the order 
 */
	 @Test(enabled = true, description = "SOC-4453 Testing Only -Integration testing for order Engagement builder product model product for CPQ,ESI, SFDC, ETL, FindLaw")
	    public void soc_4453() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(29);
		contactsPage = new ContactsPage(driver);
		//contactsPage.searchExistContact(29);
		//String ContactName=ExcelUtils.getDataByColumnName("Contacts", "Id");
		String ContactName = contactsPage.createNewContact();
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId = quotepage.createQuote();
		ExcelUtils.writeData("C:\\Users\\UX010052\\Documents\\Workspace\\FindLawTest\\src\\test\\resources\\testdata\\TestData.xls", "SOC_4453_Quote", QuoteId);
		addproductspage = new AddProductsPage(driver);
		int ctrl = 19; 
		addproductspage.addProducts(ctrl,29); 
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
		//String QuoteId= "Q-00419792";
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		findlawordertoolpage = new FindLawOrderToolPage(driver);
		findlawordertoolpage.clickCOB();
		findlawordertoolpage.loginWithSafeuserId(QuoteId);
		int ctrlval = 8;
		findlawordertoolpage.cobProcess(ctrlval);
		applicationLogin();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		validateesignpage = new ValidateEsignPage(driver);
		validateesignpage.validateOrder();   		 
	 }
	  	 
/*
 * Take any Existing Account and add EB products:41874867,41819164 and Blog set up product:41819163
 * need to see the cart page code
 * 	Finalize the cart and complete the order 
 */
	 @Test(enabled = false, description = "SOC-4469 Ability to bridge some of the products within the Engagement product model for only 30,60,90 days at 100 percent")
	    public void soc_4469() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(30);
		contactsPage = new ContactsPage(driver);
		String ContactName = contactsPage.createNewContact();
		//String ContactName= ExcelUtils.getDataByColumnName("Contacts", "Id");
		contactsPage = new ContactsPage(driver);
		//contactsPage.searchExistContact(30);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId = quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		addproductspage.validateBridgeValues(30);
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
		//String QuoteId= "Q-00420121";
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		findlawordertoolpage = new FindLawOrderToolPage(driver);
		findlawordertoolpage.clickCOB();
		findlawordertoolpage.loginWithSafeuserId(QuoteId);
		int ctrlval = 26;  //9
		findlawordertoolpage.cobProcess(ctrlval);
		applicationLogin();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		validateesignpage = new ValidateEsignPage(driver);
		validateesignpage.validateOrder();   	 
	 }

	 
/*
 * Take any existing Account and add EB products:41817448,41819164,42089522,42089523 
 * follow rules for adding these products.
 * 	Finalize the cart and validate year 2-3 price
 */
	 @Test(enabled = false, description = "SOC-4472 Testing Only- Ability to determine the price for year 2 & 3  (CPQ) for Engagement product model products")
	    public void soc_4472_New() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(31);
		contactsPage = new ContactsPage(driver);
		contactsPage.createNewContact();
		/*contactsPage = new ContactsPage(driver);
		contactsPage.searchExistContact(31);*/
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String quoteId= quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		addproductspage.validateYear23EBProducts(31,quoteId); 
	 }
	 
/*
 * Take any Existing Account which is having EB product:41256983 (Findlaw social media essential)or 41091549 (Essentials Plus — Florida (Westlaw PRO™)) as an Asset
 * EB products: 41915678,41256983,40666911,41003359
 * Finalize the cart and complete the order
 * 	 
 */
	 @Test(enabled = false, description = "SOC-4498 As an AM, I can create renewal order for renewal only products for Engagment builder model products")
	    public void soc_4498() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(32);
		contactsPage = new ContactsPage(driver);
		String ContactName = contactsPage.createNewContact();
		//contactsPage.searchExistContact(32);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId = quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		int ctrl = 5; 
		addproductspage.addProducts(ctrl,32); 
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
 		validateesignpage.validateEsignForRenew(); 
	 
	 }
	 
/*
 * Take any Existing Account and add EB products:42089521,41697107,41817448,41819164 to the cart 
 * 	 Validate Margin type for all
 */
	 @Test(enabled = false, description = "SOC-5171 DEV 4703 Approval Discount Set Up products assigned to a margin type engagement builder product model")
     public void soc_5171() {
         applicationLogin();
         accountpage = new Accountpage(driver); 
         accountpage.searchExistAccount(33);
         contactsPage = new ContactsPage(driver);
         contactsPage.createNewContact();
         //contactsPage.searchExistContact(33);
         OpptyPage optypage = new OpptyPage(driver);
         optypage.createOpty(); 
         quotepage = new QuotePage(driver);
         quotepage.createQuote();
         addproductspage = new AddProductsPage(driver);
         int ctrl = 3; 
         addproductspage.addProducts(ctrl,33);
         quotepage = new QuotePage(driver);
         quotepage.ValidateEBMarginTyp(33);	 
	 }
	 
/*
 * Take any Existing Account which is not having OTC products as an asset and follow rules to add OTC products:42089523,41870119 to the cart
 * 
 * 	Finalize the cart and complete the order.
 */
	 @Test(enabled = false, description = "SOC-5333 As a rep or AM, when I chose a OTC product the Contract Terms and Bridge should not have options to chose from for these fields")
     public void SOC_5333() {
         applicationLogin();
         accountpage = new Accountpage(driver);
         accountpage.searchExistAccount(34);
         contactsPage = new ContactsPage(driver);
         String ContactName=contactsPage.createNewContact();
         //contactsPage.searchExistContact(34);
         OpptyPage optypage = new OpptyPage(driver);
         optypage.createOpty();
         quotepage = new QuotePage(driver);
         String QuoteId= quotepage.createQuote();
         searchquotepage = new SearchQuotePage(driver);
  		 searchquotepage.searchQuote(QuoteId);
         addproductspage = new AddProductsPage(driver);
         addproductspage.validateContractBridge(34);
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
 		int ctrlval = 8;
 		findlawordertoolpage.cobProcess(ctrlval);
 		applicationLogin();
 		searchquotepage = new SearchQuotePage(driver);
 		searchquotepage.searchQuote(QuoteId);
 		validateesignpage = new ValidateEsignPage(driver);
 		validateesignpage.validateOrder();
	 }
	 

/*
 * Take any Existing Account which have any Firmsite product as an asset and add it to the cart.
 * 
 * Validate all the columns in the Cart page
 */
	 @Test(enabled = false, description = "SOC-5387 Testing Only of Story SOC-5222 (Configure columns with the cart)")
     public void soc_5387() {
         applicationLogin();
         accountpage = new Accountpage(driver);
         accountpage.searchExistAccount(35);
         contactsPage = new ContactsPage(driver);
         contactsPage.createNewContact();
         //contactsPage.searchExistContact(35);
         OpptyPage optypage = new OpptyPage(driver);
         optypage.createOpty(); 
         quotepage = new QuotePage(driver);
         quotepage.createQuote();
         addproductspage = new AddProductsPage(driver);
         int ctrl = 10; 
         addproductspage.addProducts(ctrl,35);   		 
	 }
	 
/*
 * Take any Existing Account and add OTC products: 42089523,41870119 and add it to the cart
 * 
 * 	Validate Finalize and Submit for Approval button based on the condition
 * 
 * Finalize the Cart and complete the order	
 */
	 @Test(enabled = false, description = "SOC-5536 Create discount approval rule for OTC products")
     public void soc_5536() {
         applicationLogin();
         accountpage = new Accountpage(driver);
         accountpage.searchExistAccount(36);
         contactsPage = new ContactsPage(driver);
         String ContactName= contactsPage.createNewContact();
         //contactsPage.searchExistContact(36);
         OpptyPage optypage = new OpptyPage(driver);
         optypage.createOpty(); 
         quotepage = new QuotePage(driver);
         String QuoteId=quotepage.createQuote();
         addproductspage = new AddProductsPage(driver);
         addproductspage.validateGuidanceOTCprod(36);   
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
	 	
/*
 * Take any Existing Account which is not having any Firmsite & IM products and add all IM and Firmsite products
 * 
 * Finalize the cart and complete the order 
 */
	 @Test(enabled = true, description = "SOC-4719 Milestone 2 QA End to End Process CPQ to  SAP Create Subscription Testing  All Firmsites and IM Solutions")
	  public void SOC_4719() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(37);
		contactsPage = new ContactsPage(driver);
		String ContactName = contactsPage.createNewContact();
		// contactsPage.searchExistContact(37);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId = quotepage.createQuote();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		ExcelUtils.writeData("C:\\Users\\UX010052\\Documents\\Workspace\\FindLawTest\\src\\test\\resources\\testdata",
				"SOC_4719_Quote", QuoteId);
		addproductspage = new AddProductsPage(driver);
		int ctrl = 20;
		addproductspage.addProducts(ctrl, 37);
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
		/*findlawordertoolpage = new FindLawOrderToolPage(driver);
		findlawordertoolpage.clickCOB();
		findlawordertoolpage.loginWithSafeuserId(QuoteId);
		int ctrlval = 26; // 15
		findlawordertoolpage.cobProcess(ctrlval);
		applicationLogin();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		validateesignpage = new ValidateEsignPage(driver);
		validateesignpage.validateOrder(); */
		 
	 }	
	
}
