package tr.findlaw.tests;

import org.testng.annotations.Test;
import com.framework.utils.ExcelUtils;
import tr.findlaw.basetest.FindlawBaseTest;
import tr.findlaw.pages.Accountpage;
import tr.findlaw.pages.AddProductsPage;
import tr.findlaw.pages.Contact28Page;
import tr.findlaw.pages.ContactsPage;
import tr.findlaw.pages.EsignProcessPage;
import tr.findlaw.pages.GenerateDocumentPage;
import tr.findlaw.pages.OpptyPage;
import tr.findlaw.pages.QuotePage;
import tr.findlaw.pages.SearchQuotePage;
import tr.findlaw.pages.SendEsignPage;
import tr.findlaw.pages.ValidateEsignPage;

public class Sprint_26 extends FindlawBaseTest {
	//execute all scenario
	
/*
 * Take any two Existing account which have Directory product as an asset such as 30790796,  41680342, 41680342, 37005072, 40987607, 40987608, 41053156, 30790769, 41895338,  42027610
 * one Account will have Directory product with Yes option and other product will have No option.
 * Check Renewal Eligible of the product from Installed product section and add products with Yes and No option and check.
 * whether submit for approval will get triggered if option is NO and Finalize will get enable if option is Yes.
 * Consists of both Positive and Negative scenario.
 * Account with Yes option: 1004647310- 41895338, 30790796, 41053156.
 * Account with No option:1000499446- 30790796, 30790769,37005005,42027607,42027608,42027610.
 */
	
	@Test(enabled = false, description = "SOC_4396_Testing Only: When a user, creates a renewal they should only be able to renew 90 days prior to renewal date for directory product model product")
    public void soc_4396_Renew() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		String accounts = ExcelUtils.getDataByColumnNameWithRowIndex("Account", "Id",96);
		String[] listOfAccount = accounts.split(",");		
		for (int i=0; i<listOfAccount.length; i++) {		 
		accountpage.searchExistAccounts(listOfAccount[i]);
		contactsPage = new ContactsPage(driver);
		String ContactName = contactsPage.createNewContact();
		// contactsPage.searchExistContact(96);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId = quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		int ctrl = 5;
		addproductspage.addProducts(ctrl, 96);
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
	}
	
	
	/*
	 * take any Existing account which is not having PPC products as an asset.
	 * Add 30010890,41876119,41877783 products to the cart and validate contract term for all products.
	 */
	@Test(enabled = false, description = "SOC_4557_Testing Only Ability to chose contract terms available 1,2, 3, for PPC product model products (CPQ) ")
    public void soc_4557() {
		applicationLogin();
		accountpage = new Accountpage(driver);	 
		accountpage.searchExistAccount(97);
		contactsPage = new ContactsPage(driver);
		contactsPage.createNewContact();
		// contactsPage.searchExistContact(97);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		addproductspage.validateContractField(97);		
	}
	
	
	/*
	 * Take any Existing account which have PPC add to cart products in the asset.
	 * Renew products from installed products and finalize the cart and complete the order. 
	 * use Account: 1000022370, 1000014325, 1000013841, 1000000549
	 * take any 2 products from the list: 41343503, 41343504, 41343505, 41343506 
	 */
	@Test(enabled = false, description = "SOC_4569_Testing Only:I am able to renew PPC products 1, 2, 3 year")
    public void soc_4569() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		String accounts = ExcelUtils.getDataByColumnNameWithRowIndex("Account", "Id",98);
		String[] listOfAccount = accounts.split(",");		
		for (int i=0; i<listOfAccount.length; i++) {		 
		accountpage.searchExistAccounts(listOfAccount[i]);
		contactsPage = new ContactsPage(driver);
		String ContactName = contactsPage.createNewContact();
		//contactsPage.searchExistContact(98);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId = quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		int ctrl = 5;
		addproductspage.addProducts(ctrl, 98);
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
	}
	
	
	
	/*take any Existing account which have OTC products as an asset : 41903514,41903515,30405072,41948732.
	 * Add any one product and check whether Terminate option is disabled or not.
	 * use account: 1004438494,1003675326 ,1003606615
	 */
	@Test(enabled = false, description = "SOC_7334_Within Installed Products, I should not see the terminate(aka lapse) button when I have selected a OTC Product.")
    public void soc_7334() {
		applicationLogin();
		accountpage = new Accountpage(driver);	 
		accountpage.searchExistAccount(99);
		contactsPage = new ContactsPage(driver);
		contactsPage.createNewContact();
		// contactsPage.searchExistContact(99);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		addproductspage.validateTerminateOptionPPC(99);		
	}
	
	/*
	 * Take any Existing account which is not having DIGITAL MKTG ACCEL: 41767150 , Bundle 1 and Bundle 4 as an asset.
	 * Add ADDITIONAL LANDING PAGE OTC: 41876119, ESPAÑOL ADDTL LANDING PAGE OTC: 41877783 and 41767150 into the cart
	 * and validate Approval triggered or not.
	 * use Account: 1000747607,1000425203,1000014325
	 */
	
	@Test(enabled = false, description = "SOC_6770_Approval Rule for PPC Additional Landing Page - DMA")
    public void soc_6770() {
		applicationLogin();
		accountpage = new Accountpage(driver);	 
		accountpage.searchExistAccount(100);
		contactsPage = new ContactsPage(driver);
		contactsPage.createNewContact();
		//contactsPage.searchExistContact(100);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		addproductspage.validateApprovalRule(100);		
	}
	
	/*
	 * Take any Existing account which is having DIGITAL MKTG ACCEL: 41767150 as an asset. Bundle 1 and Bundle 4 should not be present as an asset
	 * Add 41767150 from Installed and 41876119,41877783 into the cart and validate error message 
	 * use Account: 1000039659,1002053618 
	 */
	@Test(enabled = false, description = "SOC_6770_Approval Rule for PPC Additional Landing Page - DMA")
    public void soc_6770_Exist() {
		applicationLogin();
		accountpage = new Accountpage(driver);	 
		accountpage.searchExistAccount(101);
		contactsPage = new ContactsPage(driver);
		contactsPage.createNewContact();
		//contactsPage.searchExistContact(101);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		addproductspage.validateApprovalRuleExist(101);		
	}
	
	
	/*
	 * Take any Existing Account which is not having these mentioned products such as 41986777,  40542345, 40485592. Add these products into cart
	 * Validate Bridge field and give Discount as 100 for all products, complete the approval process.
	 * validate Bridge and Bridge Discount details for all products in LineItem section. 
	 * Use Account: 1003517589,1003473338,1004015381.
	 * Error message displayed while adding Firmsite 444C product. Execution is on hold.
	 */
	
	@Test(enabled = false, description = "SOC_4512_Testing Only: Ability to bridge for remaining Engagement Builder & Blog products for only 30,60,90 days at 100% (UI will show 1,2 3 month option) (CPQ) ")
    public void soc_4512_New() {
		applicationLogin();
		accountpage = new Accountpage(driver);	 
		accountpage.searchExistAccount(102);
		contactsPage = new ContactsPage(driver);
		contactsPage.createNewContact();
		//contactsPage.searchExistContact(102);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId= quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		addproductspage.addBridgeDiscount(102);
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		quotepage.validateBridgeDiscount();
	}
	
	/*
	 * Take any Existing account which have any one of the product: 41076127,41684631, 41484905 as an asset.
	 * renew that product into the cart and validate the bridge dropdown is disbaled or not.
	 * Use Account :1003517589-41076127 , 1003473338- 41684631 , 1003441887- 41484905 
	 */
	@Test(enabled = false, description = "SOC_4512_Testing Only: Ability to bridge for remaining Engagement Builder & Blog products for only 30,60,90 days at 100% (UI will show 1,2 3 month option) (CPQ) ")
    public void soc_4512_Renew() {
		applicationLogin();
		accountpage = new Accountpage(driver);	 
		accountpage.searchExistAccount(103);
		contactsPage = new ContactsPage(driver);
		contactsPage.createNewContact();
		//contactsPage.searchExistContact(103);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		addproductspage.addProducts(5,103);
	}
	
	
	/*
	 * Take any Existing account which is not having 42532852, 42532853 , 42532854 as an asset.
	 * Add twice these products and validate the error message.
	 * use Account: 1004437387,1003473338,1000027379,1003441887.
	 * This scenario also covers SOC_4584, SOC_4582, SOC_7586
	 */
	@Test(enabled = false, description = "SOC_4590_4584_Implement the Max Quantity for Answer Services product model products")
    public void soc_4590() {
		applicationLogin();
		accountpage = new Accountpage(driver);	 
		accountpage.searchExistAccount(104);
		contactsPage = new ContactsPage(driver);
		contactsPage.createNewContact();
		//contactsPage.searchExistContact(104);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		addproductspage.addContract(104);
	}
	
	
	/*
	 * Take any Existing account which is not having 40542344,40542345 products as an asset.
	 * Add these products into the cart and validate the business rule validation message displayed in the cart or not.
	 */
	@Test(enabled = false, description = "SOC_7586_Implement the Prerequisite business rules associated to remaining Engagement Builder & Blog products (CPQ)")
    public void soc_7586() {
		applicationLogin();
		accountpage = new Accountpage(driver);	 
		accountpage.searchExistAccount(105);
		contactsPage = new ContactsPage(driver);
		contactsPage.createNewContact();
		//contactsPage.searchExistContact(105);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		addproductspage.validateBusinessRule(105);
	}
	
	
	/*
	 * Take any Existing account which have any one product 41230687,41267325,41267327,41267324,41751074 as an asset in account.
	 * User 1004673632-41751072 ,1003891133- 41751074, 1000173013- 41267324,41267325,41230689,41267327, 1000369190 - 41267326, 1000246958- 41230687 
	 */
	@Test(enabled = false, description = "SOC_7472_Testing Only- I should not be able to renew Conversion Solution Product Model Products ")
    public void soc_7472() {
		applicationLogin();
		accountpage = new Accountpage(driver);	 
		accountpage.searchExistAccount(106);
		contactsPage = new ContactsPage(driver);
		contactsPage.createNewContact();
		//contactsPage.searchExistContact(106);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		addproductspage.validateRenewButton(106);
	}
	
	
	/*
	 * 
	 * Take any Existing Account which have PPC renewal product: 41343507 as an Existing asset.
	 * Renew the product and complete the order.
	 */
	@Test(enabled = false, description = "SOC_4568_Testing Only: As an AM, I can create renewal order for \"renewal only products for PPC model products")
    public void soc_4568() {
    applicationLogin();
    accountpage = new Accountpage(driver);
    accountpage.searchExistAccount(107);
    contactsPage = new ContactsPage(driver);
    String ContactName=contactsPage.createNewContact();
   // contactsPage.searchExistContact(107);
    OpptyPage optypage = new OpptyPage(driver);
    optypage.createOpty(); 
    quotepage = new QuotePage(driver);
    String QuoteId=quotepage.createQuote();
    addproductspage = new AddProductsPage(driver); 	
    int ctrl = 5; 
    addproductspage.addProducts(ctrl,107); 
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
	 * Take any Existing account which is not having Directory 37005005, Findlaw post plus 41378928 products as an asset.
	 * add these products and give discount as 40% and validate submit for approval button enabled or not.
	 */
	@Test(enabled = true, description = "SOC_7051_Implement Renewal Approvals Financial model Rules")
	public void soc_7051() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(108);
		contactsPage = new ContactsPage(driver);
		contactsPage.createNewContact();
		//contactsPage.searchExistContact(108);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId = quotepage.createQuote();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		addproductspage = new AddProductsPage(driver);
		addproductspage.validateApprovalForPAGEO(108);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
