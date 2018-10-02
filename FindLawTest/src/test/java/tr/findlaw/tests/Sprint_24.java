package tr.findlaw.tests;

import org.testng.annotations.Test;

import tr.findlaw.basetest.FindlawBaseTest;
import tr.findlaw.pages.Accountpage;
import tr.findlaw.pages.AddProductsPage;
import tr.findlaw.pages.ContactsPage;
import tr.findlaw.pages.OpptyPage;
import tr.findlaw.pages.QuotePage;

public class Sprint_24 extends FindlawBaseTest{

/*
 * take Existing account which have PPC products as an asset.
 * Select any one of the PPC product from installed products and validate terminate button is enabled or disabled.	
 * use Accounts: 1000027379,1004876784,1000014794
 * Materials: 41343504,41343503,41343505,41343506
 */
	@Test(enabled = false, description = "SOC_6882_SOC_4401_Within Installed Products, I should not see the terminate option when I have selected a PPC product")
    public void soc_6882() {
		 	applicationLogin();
			accountpage = new Accountpage(driver);
			accountpage.searchExistAccount(91);
			contactsPage = new ContactsPage(driver);
			contactsPage.createNewContact();
			// contactsPage.searchExistContact(91);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			quotepage.createQuote();   
			addproductspage = new AddProductsPage(driver);
			addproductspage.validateTerminateOptionPPC(91);			
	 }

/*
 * Take any Existing account which have products with  MYR types :M01, M02, M03 , R01, R02, R03,  C01, C02, C03, C04, C05, C06, C07, C08, C09, C10, C11
 * terminate those products in the cart page and add any new product and reprice the cart page.validate submit for approval button
 * use account:1003268127 (Please check sheet mentioned in Jira for this Test case)
 * Product: 41053158
 * PLAN TYPE = R01	, 1004343305-  30572896, 1003268127- 41053158	
 */
	@Test(enabled = true, description = "SOC_7222_Lapsing Approval Rule")
    public void soc_7222() {
		 	applicationLogin();
			accountpage = new Accountpage(driver);
			accountpage.searchExistAccount(92);
			contactsPage = new ContactsPage(driver);
			contactsPage.createNewContact();
			// contactsPage.searchExistContact(92);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			quotepage.createQuote();   
			addproductspage = new AddProductsPage(driver);
			addproductspage.validateSubmitForApprovalOption(92);			
	 }
	
	
	
	
}
