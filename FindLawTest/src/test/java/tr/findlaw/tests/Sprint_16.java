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

public class Sprint_16 extends FindlawBaseTest {
	 
	 	/*
		 * Take any Existing Account which does not have below product as an Asset
		 * Select " 42089521-FNDLW REPUTATION ADVISOR" product  
		 * Change the quantity for the product,finalize the cart and complete the order
		 */
	
	 @Test(enabled = true, description = "SOC-6057 Max quantity rule for additional products within Engagement builder model")
  public void soc_6057_Order() {
			applicationLogin();
			accountpage = new Accountpage(driver);	
			accountpage.searchExistAccount(54);
			contactsPage = new ContactsPage(driver);
			String ContactName= contactsPage.createNewContact();
			//contactsPage.searchExistContact(54);
			//String ContactName= ExcelUtils.getDataByColumnName("Contacts", "Id");
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			String QuoteId= quotepage.createQuote();
			searchquotepage = new SearchQuotePage(driver);
			searchquotepage.searchQuote(QuoteId);
			addproductspage = new AddProductsPage(driver);
			int ctrl = 3; 
			addproductspage.addProducts(ctrl,54); 
			contact28page = new Contact28Page(driver);
			contact28page.addContact28(ContactName);
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
			validateesignpage.validateEsign();
			searchquotepage = new SearchQuotePage(driver);
			searchquotepage.searchQuote(QuoteId);
			findlawordertoolpage = new FindLawOrderToolPage(driver);
			findlawordertoolpage.clickCOB();
			findlawordertoolpage.loginWithSafeuserId(QuoteId);
			int ctrlval = 20;
			findlawordertoolpage.cobProcess(ctrlval);
			applicationLogin();
			searchquotepage = new SearchQuotePage(driver);
			searchquotepage.searchQuote(QuoteId);
			validateesignpage = new ValidateEsignPage(driver);
			validateesignpage.validateOrder();  
	        
	 }

	
/*
 * Take any Existing Account which have any one of the PPC product such as 41689180,41876119,41343504,41343503,41343505,41343506 as an asset and add  product 41876119 from the cart and 
 * also from Installed - add any one of the PPC products present as an asset such as 41689180,41876119,41343504,41343503,41343505,41343506 to the cart 
 * 	validate short term contract length for all and Finalize the cart.
 * Use Account:1000027379,1000516447
 */
	 @Test(enabled = true, description = "SOC-4558 Ability to chose a short term contract length ( 3-11 months)")
	  public void soc_4558() {
				applicationLogin();
				accountpage = new Accountpage(driver);	
				accountpage.searchExistAccount(55);
				contactsPage = new ContactsPage(driver);
				contactsPage.createNewContact();
				//contactsPage.searchExistContact(55);
				OpptyPage optypage = new OpptyPage(driver);
				optypage.createOpty();
				quotepage = new QuotePage(driver);
				String QuoteId= quotepage.createQuote();
				searchquotepage = new SearchQuotePage(driver);
				searchquotepage.searchQuote(QuoteId);
				addproductspage = new AddProductsPage(driver);
				int ctrl = 14; 
				addproductspage.addProducts(ctrl,55);     
		 }
}
