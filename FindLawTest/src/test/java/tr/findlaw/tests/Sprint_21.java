package tr.findlaw.tests;

import java.util.ArrayList;
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

public class Sprint_21 extends FindlawBaseTest {

	/*
	 * Take any Existing Account and validate the Payment option value	
	 */
	@Test(enabled = false, description = "SOC_6761_Implementing max quantity rule for Existing PPC with Firmsite")
	public void soc_6761() {
		applicationLogin();
		accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(76);
		contactsPage = new ContactsPage(driver);
		contactsPage.createNewContact();
		// contactsPage.searchExistContact(76);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty();
		quotepage = new QuotePage(driver);
		quotepage.createQuote();
		quotepage.validatePaymentOption();
	}	
		 
	
	 /*
	* Take any Existing Account which is having individual PPC products as an asset and have less no of PPC products in the asset line items sold to and also in Installed products.
	* take the sequence of the products from Asset line Items (Sold To) and validate the same in cart page,Installed products page and Asset Line Items in Quote Page
	* take these accounts: 1000013841,1000014325,1000014794,1000027379,1000027753,1000028150
	* used account: 1000014325,1000027444,1000013841
	* Regression Defect raised- 5/11/2018
	*/
		 @Test(enabled = false, description = "SOC_6817_Testing Only PPC Assets Grouped ")
	     public void soc_6817() {	 
			applicationLogin();
			accountpage = new Accountpage(driver);
			ArrayList<String> assetText= accountpage.getAssetSequence(77);
			accountpage.searchExistAccount(77);
			contactsPage = new ContactsPage(driver);
			contactsPage.createNewContact();
			//contactsPage.searchExistContact(77);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			String QuoteId= quotepage.createQuote();
			addproductspage = new AddProductsPage(driver);
			addproductspage.addMultipleInstalledprod(77,assetText);
			searchquotepage = new SearchQuotePage(driver);
		    searchquotepage.searchQuote(QuoteId);
			quotepage.validateAssetLine(assetText);
		}	 
	 
 /*
* Scenario 1: Account should have bundle 1(FNDLW PPC ADVERTISEMENT COSTS,FNDLW PPC CALL TRACKING NUMBER,FNDLW PPC LANDING PAGE,FNDLW PPC ADVERTISING) as an asset and no IM products as an asset.Add product 30010890 (Existing ppc,english) and 41876119. able to finalize
* Scenario 2: Account should have bundle 5(FNDLW PPC ESPAÑOL ADVERTISEMENT COSTS, FNDLW PPC ESPAÑOL CALL TRACKING NUMBER, FNDLW PPC ESPAÑOL ADVERTISING) as an asset and no IM products as an asset. Add product 30010890 (Existing ppc,espanol),and 41876119 and validate warning message for 41876119
* Scenario 3: Account should have any IM product as an asset and no Bundles as an asset. Add product 41876119 and 30010890 (Existing ppc,english) and validate warning message for 30010890
* use: Bundle1:1000000549,  Bundle5: 1000027444, IM product: 1004240911
* 
*/	 
		 @Test(enabled = false, description = "SOC_6035_Prerequisite Validations for PPC ")
	     public void soc_6035() {	 
			applicationLogin();
	        String Account = ExcelUtils.getDataByColumnNameWithRowIndex("Account", "Id", 78);
	        String[] listofaccount = Account.split(",");
            for (int i=0; i<listofaccount.length; i++)
            {
			accountpage = new Accountpage(driver);	
			accountpage.searchExistAccounts(listofaccount[i]);
			/*String contactName= ExcelUtils.getDataByColumnNameWithRowIndex("Contacts", "Id", 78);
			String[] listOfContact = contactName.split(",");
            for (int j=0; j<listOfContact.length; j++)
            {*/
			contactsPage = new ContactsPage(driver);
			contactsPage.createNewContact();
			//contactsPage.searchExistContacts(listOfContact[j]);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			quotepage.createQuote();
			addproductspage = new AddProductsPage(driver);
			addproductspage.addPPCLandingPage(78);
           // }
            }	
		}
		 
		
/*
 * Take any Existing Account which have any one of Bundle 1,2,3,4,5,6 as an Asset and renew them and complete the order.
 * Execution is on hold due to assets not available in the application.	
 * use Account: Bundle1:1000013841,Bundle2: 1004436907	
 * Regression Defect raised by Rishu- 5/11/2018 
 */
	@Test(enabled = false, description = "SOC_6366_PPC New Bundle Renewal Integration  ")
	public void soc_6366() {
		applicationLogin();
		String Account = ExcelUtils.getDataByColumnNameWithRowIndex("Account", "Id", 79);
		String[] listofaccount = Account.split(",");
		for (int i = 0; i < listofaccount.length; i++) {
			accountpage = new Accountpage(driver);
			accountpage.searchExistAccounts(listofaccount[i]);
			/*
			 * String[] listOfContact = ContactName.split(","); for (int
			 * j=0;j<listOfContact.length; j++) {
			 */
			contactsPage = new ContactsPage(driver);
			String ContactName = contactsPage.createNewContact();
			// contactsPage.searchExistContacts(listOfContact[j]);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			String QuoteId = quotepage.createQuote();
			addproductspage = new AddProductsPage(driver);
			addproductspage.addProducts(5, 79);
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
			// }
		}
	}	
	
/*
 * Take any Existing account which is having less no of assets and validate the error messages.
 * Add primary products 30790769,30790796,41895338,41817449,41180810 and secondary products are: 42027610,42027608,42027610,41819164,41819164		 
 * use Account: 1000425203,1004709247
 */
		 @Test(enabled = false, description = "soc-6605_Update the Prerequisite products for blog/engagement product model")
	     public void soc_6605() {	 
			applicationLogin();
			accountpage = new Accountpage(driver);	
			accountpage.searchExistAccount(80);
			contactsPage = new ContactsPage(driver);
			contactsPage.createNewContact();
			//contactsPage.searchExistContact(80);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			quotepage.createQuote();
			addproductspage = new AddProductsPage(driver);
			addproductspage.verifyErrorMessage(80);	
		}
	
/*
 * Take any existing account which is not having any US PPC product as an asset and firmsite product should be present as an asset 
 * and add PPC product to the cart and give some value to % override field 
 * use Account: 1000027379 
 */
		 @Test(enabled = false, description = "SOC-6861_PPC Management Fee Override % Field Approval required")
	     public void soc_6861() {	 
			applicationLogin();
			accountpage = new Accountpage(driver);	
			accountpage.searchExistAccount(81);
			contactsPage = new ContactsPage(driver);
			contactsPage.createNewContact();
			//contactsPage.searchExistContact(81);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			quotepage.createQuote();
			addproductspage = new AddProductsPage(driver);
			addproductspage.validateApprovalButton(81);	
		}
	
/*
 * Take any existing account which have any installed product as an asset and add that product and validate Terminate button.
 *	 use account: 1000013841, product: 41915678
 */
		 @Test(enabled = true, description = "SOC-6778_Lapsing Validation Rule")
	     public void soc_6778() {	 
			applicationLogin();
			accountpage = new Accountpage(driver);	
			accountpage.searchExistAccount(82);
			contactsPage = new ContactsPage(driver);
			contactsPage.createNewContact();
			//contactsPage.searchExistContact(82);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			quotepage.createQuote();
			addproductspage = new AddProductsPage(driver);
			addproductspage.validateTerminate(82);	
		}
		 
		 
}
