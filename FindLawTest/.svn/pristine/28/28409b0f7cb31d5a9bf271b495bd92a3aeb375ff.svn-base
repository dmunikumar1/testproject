package tr.findlaw.tests;


import org.testng.annotations.Test;
import com.framework.utils.ExcelUtils;
import tr.findlaw.basetest.FindlawBaseTest;
import tr.findlaw.pages.Accountpage;
import tr.findlaw.pages.AddProductsPage;
import tr.findlaw.pages.ContactsPage;
import tr.findlaw.pages.OpptyPage;
import tr.findlaw.pages.QuotePage;

public class Sprint_19 extends FindlawBaseTest{
	
	/*
	 * Take any 2 Existing Account, one which is having Firmsite product and other one is not having firmsite product as an asset. Add US PPC product:30010890 Bundle 1 and 4 for each account.
	 * 	validate the error message for the account which is not having firmsite product and error message wont display for account which is having firmsite product.
	 */
	
		 @Test(enabled = true, description = "SOC_4570_Implement the max quantity rule for PPC products ")
	     public void soc_4570() {	 
				applicationLogin();
                String Accounts = ExcelUtils.getDataByColumnNameWithRowIndex("Account", "Id", 72);
                String[] Accounts1 = Accounts.split(",");
                for (int i=0; i<Accounts1.length; i++)
                {
				accountpage = new Accountpage(driver);	
				accountpage.searchExistAccounts(Accounts1[i]);
				contactsPage = new ContactsPage(driver);
				String ContactName= contactsPage.createNewContact();
				/*String ContactNo = ExcelUtils.getDataByColumnNameWithRowIndex("Contacts", "Id", 72);
				String[] ContactNo1 = ContactNo.split(",");
				for (int j=0; j<ContactName.; j++)
                {*/
			    contactsPage.searchExistContacts(ContactName);
				OpptyPage optypage = new OpptyPage(driver);
				optypage.createOpty();
				quotepage = new QuotePage(driver);
				quotepage.createQuote();
				addproductspage = new AddProductsPage(driver);
				addproductspage.validateErrorMessage1(72);
                //}
                }
		 }	 
		 
/*
 * Take any Existing Account which is not having US PPC product as an asset and add US PPC product and validate recommend product is getting added or not for respective scenarios
 * 		 
 */
		 @Test(enabled = false, description = "SOC_4571_Additional Landing pages will appear in the recommended product (only when a Firmsite or LawInfo site)")
	     public void soc_4571() {	 
				applicationLogin();
				accountpage = new Accountpage(driver);	
				accountpage.searchExistAccount(73);
				contactsPage = new ContactsPage(driver);
				contactsPage.createNewContact();
				//contactsPage.searchExistContact(73);
				OpptyPage optypage = new OpptyPage(driver);
				optypage.createOpty();
				quotepage = new QuotePage(driver);
				quotepage.createQuote();
				addproductspage = new AddProductsPage(driver);
				addproductspage.validateRecommendedProduct(73);  
				       
		 }
}
