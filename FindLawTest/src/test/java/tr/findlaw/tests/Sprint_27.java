package tr.findlaw.tests;

import org.testng.annotations.Test;
import tr.findlaw.basetest.FindlawBaseTest;
import tr.findlaw.pages.Accountpage;
import tr.findlaw.pages.AddProductsPage;
import tr.findlaw.pages.ContactsPage;
import tr.findlaw.pages.OpptyPage;
import tr.findlaw.pages.QuotePage;

public class Sprint_27 extends FindlawBaseTest {
	//all scenarios need to execute
	/*
	 * Take any Existing account which have Firmsite 333C product as an asset and add IMS 41777796,Foreign language iv 40542345 product into the cart
	 * validate any error message displayed after adding products into the cart.
	 */
	@Test(enabled = false, description = "SOC_7667_Need to update Mutually Exclusive rule for a couple IM products because they contained the Foreign Language materials.")
    public void soc_7667() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(109);
		contactsPage = new ContactsPage(driver);
		contactsPage.createNewContact();
		//contactsPage.searchExistContact(109);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty( );
		quotepage = new QuotePage(driver);
		quotepage.createQuote();
		addproductspage = new AddProductsPage(driver);
		addproductspage.validateInfoMessage(109);
	}
		
	
@Test(enabled = false, description = "SOC_7798_Create a rule that bridging is not eligible for FindLaw Renewals")
    public void soc_7798() {
	applicationLogin();
	accountpage = new Accountpage(driver);
	accountpage.searchExistAccount(110);
	contactsPage = new ContactsPage(driver);
	contactsPage.createNewContact();
	//contactsPage.searchExistContact(109);
	OpptyPage optypage = new OpptyPage(driver);
	optypage.createOpty();
	quotepage = new QuotePage(driver);
	quotepage.createQuote();
	addproductspage = new AddProductsPage(driver);
	addproductspage.validatebridge(110);
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}

