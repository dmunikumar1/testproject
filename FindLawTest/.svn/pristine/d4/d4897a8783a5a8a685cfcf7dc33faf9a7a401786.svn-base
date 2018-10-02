package tr.findlaw.tests;

import org.testng.annotations.Test;
import tr.findlaw.basetest.FindlawBaseTest;
import tr.findlaw.pages.Accountpage;
import tr.findlaw.pages.AddProductsPage;
import tr.findlaw.pages.ContactsPage;
import tr.findlaw.pages.OpptyPage;
import tr.findlaw.pages.QuotePage;
import tr.findlaw.pages.SearchQuotePage;

public class Sprint_22 extends FindlawBaseTest{	

/* need to execute
 * Take any Existing account and add any (add to cart option) product:41819164 and then validate the PA/GEO products button in the cart page and add products from PA/GEO page
 * contains total 26 scenarios. UI part changed so need to modify scripts.
 * Use Account: 1000027444,1000080864,1004438494
 * State and Metro based filter will work for specific customers as ETL has not loaded customers into database.(5/8/2018)
 */
	@Test(enabled = true, description = "SOC -6627_SOC-6619_SOC-6620_SOC-6621_SOC-6624-SOC-6633-SOC-6622-SOC-6625-SOC-6353-SOC-6994-SOC-4389-SOC_7224-SOC_7035-SOC_6353-SOC_6999-SOC_7383-SOC_7167-SOC-7044-SOC-7022-SOC-7017-SOC-7016-SOC_7369-SOC-7043-SOC-7324-SOC_7021-SOC_6996")
	public void soc_6627() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(83);
		contactsPage = new ContactsPage(driver);
		contactsPage.createNewContact();
		//contactsPage.searchExistContact(83);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		String QuoteId = quotepage.createQuote();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		addproductspage = new AddProductsPage(driver);
		addproductspage.validatePAGEOproduct(83);
	}

	
	/*
	 * Take any Existing account which have Assets present in Installed product and
	 * validate the Start Date column.
	 */
	@Test(enabled = false, description = "SOC-6752 Updated Installed Product Page")
	public void soc_6752() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(84);
		contactsPage = new ContactsPage(driver);
		contactsPage.createNewContact();
		// contactsPage.searchExistContact(84);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		addproductspage.validateStartDate(84);
	}
		
		
	/*
	 * Take any Existing account which is having 1 firmsite products to add US PPC product in the cart.
	 * Add that product and give contract term as 1 or 2 months and validate submit for approval.
	 * use: 1000064599
	 */
	@Test(enabled = false, description = "SOC-6687_Additional Short Term Options of 1 and 2 Month for PPC & requiring approval")
	public void soc_6687() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(85);
		contactsPage = new ContactsPage(driver);
		contactsPage.createNewContact();
		// contactsPage.searchExistContact(85);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		addproductspage.validateApprovalForPPC(85);
	}
			
			
}
