package tr.findlaw.tests;

import org.testng.annotations.Test;
import tr.findlaw.basetest.FindlawBaseTest;
import tr.findlaw.pages.Accountpage;
import tr.findlaw.pages.AddProductsPage;
import tr.findlaw.pages.Contact28Page;
import tr.findlaw.pages.ContactsPage;
import tr.findlaw.pages.GenerateDocumentPage;
import tr.findlaw.pages.OpptyPage;
import tr.findlaw.pages.QuotePage;
import tr.findlaw.pages.SearchQuotePage;

public class Sprint_23 extends FindlawBaseTest{

/*
 * Take any Existing Account and add Directory product: 41680342 from the catalog and validate the validation message
 * 
 */
	 @Test(enabled = false, description = "SOC_7084_Validation message in the cart when a PA/GEO product is added from the catalog and not PA/GEO button")
     public void soc_7084() {
		 applicationLogin();
			accountpage = new Accountpage(driver);
			accountpage.searchExistAccount(86);
			contactsPage = new ContactsPage(driver);
			contactsPage.createNewContact();
			// contactsPage.searchExistContact(86);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			quotepage.createQuote();   
			addproductspage = new AddProductsPage(driver);
			int ctrl = 9; 
			addproductspage.addProducts(ctrl,86);				
	 }
	 
	 
	 
	 /*
	  * Take existing account which don't have any products as an asset or should not have any bundle (existing PPC subscription) as an asset
	  * Add Existing PPc English spent , Existing PPC Espanol  spent , PPC additional landing page (41876119) and validate the validation messaes.
	  * use Account: 1004437387,1004437480, 1004890048
	  * Regression Defect raised- 05/23/2018
	  */
	 @Test(enabled = false, description = "SOC_6897_Update Validation Rule Message text for Additional PPC Spend and PPC Additional Landing Pages")
     public void soc_6897() {
		 applicationLogin();
			accountpage = new Accountpage(driver);
			accountpage.searchExistAccount(87);
			contactsPage = new ContactsPage(driver);
			contactsPage.createNewContact();
			// contactsPage.searchExistContact(87);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			quotepage.createQuote();   
			addproductspage = new AddProductsPage(driver);
			addproductspage.validationMessage(87);		
	 }
	 
	 
	 /*
	  * This scenario combined with SOC_2500,SOC_5879
	  * Take any Existing Account and validate the links and after creation of a quote validate the COB button.
	  */
	 @Test(enabled = false, description = "SOC_7085_FindLaw Resource Page link to be added to Account Page custom link sectio")
     public void soc_7085() {
		 	applicationLogin();
			accountpage = new Accountpage(driver);
			accountpage.validateLinksInAccount(88);
			contactsPage = new ContactsPage(driver);
			contactsPage.createNewContact();
			//contactsPage.searchExistContact(88);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			quotepage.createQuote();   
			quotepage.validateCOBButton();			
	 } 
	
/*
 * Take any Existing account and add EB product and add all directory products from Custom page and validate the Year 2-3 Disc/Surc field value.	  
 */
	 @Test(enabled = false, description = "SOC_7116_For Directory Prodcuts the 2-3 Disc/Surch Column Should not be calculating it should be 0 ")
     public void soc_7116() {
		 	applicationLogin();
			/*accountpage = new Accountpage(driver);
			accountpage.searchExistAccount(89);
			contactsPage = new ContactsPage(driver);
			contactsPage.createNewContact();*/
		 	contactsPage = new ContactsPage(driver);
			contactsPage.searchExistContact(89);
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
			quotepage = new QuotePage(driver);
			quotepage.createQuote();   
			addproductspage = new AddProductsPage(driver);
			addproductspage.validateYear23Disc(89);				
	 } 
	
	 /*
	  * Take any Existing Account and add EB product and complete till Generate the document and submit the order.
	  *  Delete the file which is placed under "tr.findlaw.executables.FileDownloads" package
	  *  Change the path for the file in FileDownload and FileUpload.exe
	  */
	 @Test(enabled = true, description = "SOC_7056_Submit Order Button Message should be updated for FindLaw Orders")
     public void soc_7056() {
		 	applicationLogin();
			accountpage = new Accountpage(driver);
			accountpage.searchExistAccount(90);
			contactsPage = new ContactsPage(driver);
			String ContactName= contactsPage.createNewContact();
		 	/*contactsPage = new ContactsPage(driver);		 	
			contactsPage.searchExistContact(90);*/
			OpptyPage optypage = new OpptyPage(driver);
			optypage.createOpty();
		 	/*String ContactName = "FNYAIK_6 LNYDBD_33";
		 	String QuoteId="Q-00538034";*/
			quotepage = new QuotePage(driver);
			String QuoteId= quotepage.createQuote(); 
			searchquotepage = new SearchQuotePage(driver);
			searchquotepage.searchQuote(QuoteId);
			addproductspage = new AddProductsPage(driver);
			addproductspage.addProducts(3,90);
			searchquotepage = new SearchQuotePage(driver);
			searchquotepage.searchQuote(QuoteId);
			contact28page = new Contact28Page(driver);
			contact28page.addContact28(ContactName);
			quotepage.addWetSignature();	 	
		 	generateDocumentPage = new GenerateDocumentPage(driver);
			String filename= generateDocumentPage.generateDocumentAndSave();
			System.out.println(filename);
			quotepage = new QuotePage(driver);
			quotepage.attachFile();	
			quotepage.submirOrder();
	 }
	 
	 
}
