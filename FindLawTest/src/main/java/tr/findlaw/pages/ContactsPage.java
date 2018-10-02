package tr.findlaw.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.framework.asserts.AssertionHandler;
import com.framework.reports.ReportBuilder;
import com.framework.reports.TestNGCustomReporter;
import com.framework.utils.ExcelUtils;
import com.framework.utils.StringUtils;

import tr.findlaw.objects.BasePageObjects;
import tr.findlaw.objects.ContactsPageObjects;
import tr.findlaw.objects.AccountPageObjects;

public class ContactsPage extends BasePage{
	
	String  linkContacts1 ="//div[contains(@id,'DetailRD')]/div[2]/a[6]/span";
	String optylnk= "//span[text()='Opportunities']";
	String contact= "//span[text()='Contacts']";
	
	public ContactsPage(WebDriver webDriver) {
		super(webDriver);
		
	}
	
	public String createNewContact(){
		String record = ExcelUtils.getDataByColumnName("Contacts", "Record");
		String salutation = ExcelUtils.getDataByColumnName("Contacts", "Salutation");
		String firstname= StringUtils.testDataModification(ExcelUtils.getDataByColumnName("Contacts", "FirstName"));
		String lastName= StringUtils.testDataModification(ExcelUtils.getDataByColumnName("Contacts", "LastName"));
		String eMail = ExcelUtils.getDataByColumnName("Contacts", "Email");
		elementHandler.waitForXpathToLoad(linkContacts1);
		elementHandler.clickElement(ContactsPageObjects.linkContacts);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(ContactsPageObjects.btnNewContact);
		if (ContactsPageObjects.dropdnNewRecord.size()>0) {
		elementHandler.selectByVisibleText(ContactsPageObjects.dropdnNewRecord.get(0), record);
		elementHandler.waitForElementTobeClickable(ContactsPageObjects.btnContinue);
		elementHandler.clickElement(ContactsPageObjects.btnContinue);
		}
		TestNGCustomReporter.log(logger, "New Contact Page displayed");
		elementHandler.waitforElement(ContactsPageObjects.dropdnSalutation);
		elementHandler.selectByVisibleText(ContactsPageObjects.dropdnSalutation, salutation);
		elementHandler.writeText(ContactsPageObjects.txtboxFirstName, firstname);
		elementHandler.writeText(ContactsPageObjects.txtboxLastName, lastName);
		elementHandler.writeText(ContactsPageObjects.txtboxEmail, eMail);
		elementHandler.waitForElementTobeClickable(ContactsPageObjects.btnSave);
		elementHandler.clickElement(ContactsPageObjects.btnSave);
		actionHandler.waitForSomeTime(15000);
		elementHandler.waitForXpathToLoad(optylnk);
		String ContactName= elementHandler.getText(ContactsPageObjects.lblName);
		AssertionHandler.verifyNotEquals(ContactName, "", "Contact Name filed value not displayed");
		TestNGCustomReporter.log(logger, "New Contact created:" +ContactName+ " " +ReportBuilder.takesScreenshot());
		return firstname +" "+ lastName;
	}
	
	public void searchExistContact(int Row) {
		String ContactNo = ExcelUtils.getDataByColumnNameWithRowIndex("Contacts", "Id", Row);
		elementHandler.writeText(BasePageObjects.txtBoxSearch, ContactNo,BasePageObjects.btnSearch);
		elementHandler.clickElement(BasePageObjects.btnSearch);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(ContactsPageObjects.lnkContact);
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(optylnk);
		String ContactName= elementHandler.getText(ContactsPageObjects.lblName);
		AssertionHandler.verifyNotEquals(ContactName, "", "Contact Name filed value not displayed");
		TestNGCustomReporter.log(logger, "Contact searched successfully" +ReportBuilder.takesScreenshot());
	}
 
	public void searchExistContacts(String contact) {
		elementHandler.writeText(BasePageObjects.txtBoxSearch, contact);
		elementHandler.clickElement(BasePageObjects.btnSearch);
		elementHandler.clickElement(ContactsPageObjects.lnkContact);
		elementHandler.waitForXpathToLoad(optylnk);
		String ContactName= elementHandler.getText(ContactsPageObjects.lblName);
		AssertionHandler.verifyNotEquals(ContactName, "", "Contact Name filed value not displayed");
		TestNGCustomReporter.log(logger, "Contact searched successfully" +ReportBuilder.takesScreenshot());
	}

	//Temporary function
	public void searchExistContactwtCustomer() {
		String ContactNo = ExcelUtils.getDataByColumnName("Contacts", "Id");
		elementHandler.writeText(BasePageObjects.txtBoxSearch, ContactNo);
		TestNGCustomReporter.log(logger, "Entered Contact Number");
		elementHandler.clickElement(BasePageObjects.btnSearch);
		TestNGCustomReporter.log(logger, "Contact Page displayed");
		driver.findElement(By.xpath("//span[text()='Contacts']")).click();
		actionHandler.waitForSomeTime(2000);
		//driver.findElement(By.xpath("//a[text()='LITIGATION BOUTIQUE LLC']/../../th/a")).click();
		driver.findElement(By.xpath("//a[text()='GABLE & GOTWALS']/../../th/a")).click();
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(optylnk);
	}
	
	public String createCnctfordiffuser(){
		String record = ExcelUtils.getDataByColumnName("Contacts", "Record");
		String salutation = ExcelUtils.getDataByColumnName("Contacts", "Salutation");
		String firstname= StringUtils.testDataModification(ExcelUtils.getDataByColumnName("Contacts", "FirstName"));
		String lastName= StringUtils.testDataModification(ExcelUtils.getDataByColumnName("Contacts", "LastName"));
		String eMail = ExcelUtils.getDataByColumnName("Contacts", "Email");
		elementHandler.clickElement(AccountPageObjects.linkAccountName);
		TestNGCustomReporter.log(logger, "Account Detail page displayed" +ReportBuilder.takesScreenshot());
		elementHandler.waitForXpathToLoad(contact);
		elementHandler.clickElement(ContactsPageObjects.linkContacts1);
		elementHandler.clickElement(ContactsPageObjects.btnNewContact);
		if (ContactsPageObjects.dropdnNewRecord.size()>0) {
		elementHandler.selectByVisibleText(ContactsPageObjects.dropdnNewRecord.get(0), record);
		elementHandler.waitForElementTobeClickable(ContactsPageObjects.btnContinue);
		elementHandler.clickElement(ContactsPageObjects.btnContinue);
		}
		TestNGCustomReporter.log(logger, "New Contact Page displayed");
		elementHandler.waitforElement(ContactsPageObjects.dropdnSalutation);
		elementHandler.selectByVisibleText(ContactsPageObjects.dropdnSalutation, salutation);
		elementHandler.writeText(ContactsPageObjects.txtboxFirstName, firstname);
		elementHandler.writeText(ContactsPageObjects.txtboxLastName, lastName);
		elementHandler.writeText(ContactsPageObjects.txtboxEmail, eMail);
		elementHandler.waitForElementTobeClickable(ContactsPageObjects.btnSave);
		elementHandler.clickElement(ContactsPageObjects.btnSave);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForXpathToLoad(optylnk);
		String ContactName= elementHandler.getText(ContactsPageObjects.lblName);
		AssertionHandler.verifyNotEquals(ContactName, "");
		TestNGCustomReporter.log(logger, "New Contact created:" +ContactName+ " " +ReportBuilder.takesScreenshot());
		return firstname +" "+ lastName;
	}
	

}
