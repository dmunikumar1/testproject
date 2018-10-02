package tr.findlaw.tests;

import org.testng.annotations.Test;
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

public class Sprint_8 extends FindlawBaseTest{
	      
  /*
   * Take any existing Account which is not having any of the mentioned products as an asset and add products 42089522 and 41870119     
   * Finalize the cart and Complete the Order.    
   *  because the product: 30572846 not available, using  product: 41870119 
   */
@Test(enabled = true, description = "SOC-4470 Ability to chose contract terms available 1,2, 3, for some Engagement product model products (CPQ)")
   public void soc_4470() {
       applicationLogin();
       accountpage = new Accountpage(driver);
       accountpage.searchExistAccount(13);
       contactsPage = new ContactsPage(driver);
       String ContactName=contactsPage.createNewContact();
       //String ContactName= ExcelUtils.getDataByColumnNameWithRowIndex("Contacts", "Id",13);
       //contactsPage.searchExistContact(13);
       OpptyPage optypage = new OpptyPage(driver);
       optypage.createOpty(); 
       quotepage = new QuotePage(driver);
       String QuoteId=quotepage.createQuote();
       addproductspage = new AddProductsPage(driver);
       addproductspage.validateContractTerm(13); 
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
       validateesignpage.validateEsign();
      // String QuoteId= ExcelUtils.getDataByColumnNameWithRowIndex("Quote", "Name",13);
       searchquotepage = new SearchQuotePage(driver);
       searchquotepage.searchQuote(QuoteId);
       findlawordertoolpage = new FindLawOrderToolPage(driver);
       findlawordertoolpage.clickCOB();
       findlawordertoolpage.loginWithSafeuserId(QuoteId);
       int ctrlval= 4;
       findlawordertoolpage.cobProcess(ctrlval);
       applicationLogin();
       searchquotepage = new SearchQuotePage(driver);
       searchquotepage.searchQuote(QuoteId);
       validateesignpage = new ValidateEsignPage(driver);
       validateesignpage.validateOrder();         
       }

/*
 *  Take any Existing Account which is not having Firmsite 333C product as an Asset  
 *    
 *  Finalize the Cart and complete the Order , Validate Net Value
 */
@Test(enabled = true, description = "SOC-4993 CPQ Order sent to Findlaw to contain the “Net Annual Value”")
    public void soc_4993() {
       applicationLogin();
       accountpage = new Accountpage(driver);
       accountpage.searchExistAccount(14);
       contactsPage = new ContactsPage(driver);
       String ContactName=contactsPage.createNewContact();
      // String ContactName= ExcelUtils.getDataByColumnNameWithRowIndex("Contacts", "Id",14);
       //contactsPage.searchExistContact(14);
       OpptyPage optypage = new OpptyPage(driver);
       optypage.createOpty(); 
       quotepage = new QuotePage(driver);
       String QuoteId=quotepage.createQuote();
       addproductspage = new AddProductsPage(driver);
       int ctrl = 1; 
       addproductspage.addProducts(ctrl,14);
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
       validateesignpage.validateEsign();
       findlawordertoolpage = new FindLawOrderToolPage(driver);
       findlawordertoolpage.clickCOB();
       findlawordertoolpage.loginWithSafeuserId(QuoteId);
       int ctrlval= 24;
       findlawordertoolpage.cobProcess(ctrlval);
       applicationLogin();
       searchquotepage = new SearchQuotePage(driver);
       searchquotepage.searchQuote(QuoteId);
       quotepage.ValidateNetValue();                                        
       }

}
