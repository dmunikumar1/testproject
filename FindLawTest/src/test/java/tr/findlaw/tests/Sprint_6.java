package tr.findlaw.tests;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.utils.ExcelUtils;
import tr.findlaw.basetest.FindlawBaseTest;
import tr.findlaw.objects.LoginPageObjects;
import tr.findlaw.pages.Accountpage;
import tr.findlaw.pages.AddProductsPage;
import tr.findlaw.pages.Contact28Page;
import tr.findlaw.pages.ContactsPage;
import tr.findlaw.pages.EsignProcessPage;
import tr.findlaw.pages.FindLawOrderToolPage;
import tr.findlaw.pages.GenerateDocumentPage;
import tr.findlaw.pages.OpptyPage;
import tr.findlaw.pages.QuotePage;
import tr.findlaw.pages.SearchPage;
import tr.findlaw.pages.SearchQuotePage;
import tr.findlaw.pages.SendEsignPage;
import tr.findlaw.pages.ValidateEsignPage;

public class Sprint_6 extends FindlawBaseTest{
	
	 /*
	 * Take any existing account which is not having Firmsite 333C as an Asset & add product Id-40483699,apply bride details
	 * Finalize the cart and complete the order.
	 */
	
	@Test(enabled = true, priority =1, description = "SOC-4102 Ability to bridge IM & Firmsites for only 30,60,90 days for 100 percent")
	public void soc_4102() {
		{
			applicationLogin();
		/*accountpage = new Accountpage(driver);
		accountpage.searchExistAccount(2);
		contactsPage = new ContactsPage(driver);
		String ContactName= contactsPage.createNewContact();
		//String ContactName= ExcelUtils.getDataByColumnNameWithRowIndex("Contacts", "Id",2);
		//contactsPage.searchExistContact(2);
		OpptyPage optypage = new OpptyPage(driver);
		optypage.createOpty(); 
		quotepage = new QuotePage(driver);
		//String QuoteId=quotepage.createQuote();
		String QuoteId=quotepage.createQuoteForGLI(2);
		ExcelUtils.writeData("C:\\Users\\UX010052\\Documents\\Workspace\\FindLawTest\\src\\test\\resources\\testdata\\TestData.xlsx", "soc_4102", QuoteId);
		addproductspage = new AddProductsPage(driver);
		int ctrl = 2; 
		addproductspage.addProducts(ctrl,2);
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
		int ctrlval=24;
		findlawordertoolpage.cobProcess(ctrlval);
		applicationLogin();
		searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		validateesignpage = new ValidateEsignPage(driver);
		validateesignpage.validateOrder();*/	
		}
	}
	
	
}