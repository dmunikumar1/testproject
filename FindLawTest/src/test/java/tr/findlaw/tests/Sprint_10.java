package tr.findlaw.tests;


import org.testng.annotations.Test;
import tr.findlaw.basetest.FindlawBaseTest;
import tr.findlaw.pages.Accountpage;
import tr.findlaw.pages.AddProductsPage;
import tr.findlaw.pages.ContactsPage;
import tr.findlaw.pages.OpptyPage;
import tr.findlaw.pages.QuotePage;
import tr.findlaw.pages.SearchQuotePage;

public class Sprint_10 extends FindlawBaseTest{

/*
 * Take any Existing Account and create a Quote
 * 
 * Validate the Approval Segment Field value
 */
	 @Test(enabled = true, description = "SOC-4705 Approval Segment to be defaulted to FindLaw for FindLaw user")
     public void soc_4705() {
         applicationLogin();
         accountpage = new Accountpage(driver);
         accountpage.searchExistAccount(22);
         contactsPage = new ContactsPage(driver);
         contactsPage.createNewContact();
         //contactsPage.searchExistContact(22);
         OpptyPage optypage = new OpptyPage(driver);
         optypage.createOptywtOwnerName();  
         quotepage = new QuotePage(driver);
         quotepage.createQuotewoOwnerchange(); 
		 
	 }
	 
/*
 * Take any Existing Account which is not having any IM Products:41822784,41892830,41915678,41777796 as an Asset
 * 
 * 	Add them to the cart and validate the Margin Type for all 
 */
	// 1000290260, 1000331114, 1003179608
	 @Test(enabled = true, description = "SOC-5176 Update Margin Type for IM Solution Products")
     public void soc_5176() {
         applicationLogin();
         accountpage = new Accountpage(driver);
         accountpage.searchExistAccount(23);
         contactsPage = new ContactsPage(driver);
         contactsPage.createNewContact();
        // contactsPage.searchExistContact(23);
         OpptyPage optypage = new OpptyPage(driver);
         optypage.createOpty(); 
         quotepage = new QuotePage(driver);
         String QuoteId =quotepage.createQuote();
         addproductspage = new AddProductsPage(driver);
         int ctrl = 1; 
         addproductspage.addProducts(ctrl,23);
         //String QuoteId= ExcelUtils.getDataByColumnNameWithRowIndex("Quote", "Name", 23);
         searchquotepage = new SearchQuotePage(driver);
		 searchquotepage.searchQuote(QuoteId);
         quotepage = new QuotePage(driver);
         quotepage.ValidateMarginTyp();	 
	 }

	

}
