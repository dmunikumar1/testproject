package tr.findlaw.pages;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.framework.asserts.AssertionHandler;
import com.framework.reports.ReportBuilder;
import com.framework.reports.TestNGCustomReporter;
import com.framework.utils.StringUtils;

import tr.findlaw.objects.GenerateDocumentPageObjects;
import tr.findlaw.objects.QuotePageObjects;

public class GenerateDocumentPage extends BasePage {

	public GenerateDocumentPage(WebDriver webDriver) {
		super(webDriver);

	}

public void generateDocument() {
	javascripthandler.scrollIntoView(QuotePageObjects.btnGenerate);
	elementHandler.clickElement(QuotePageObjects.btnGenerate, GenerateDocumentPageObjects.lblGenrtPrpsl);
	TestNGCustomReporter.log(logger, "clicked on generate button");	
	elementHandler.clickElement(GenerateDocumentPageObjects.radiobtnFindLaw);
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(GenerateDocumentPageObjects.btnGenerate, GenerateDocumentPageObjects.btnReturn);
	actionHandler.waitForSomeTime(10000);
	elementHandler.clickElement(GenerateDocumentPageObjects.btnReturn, QuotePageObjects.btnEdit);
	TestNGCustomReporter.log(logger, "Document Generated" +ReportBuilder.takesScreenshot());	
	actionHandler.waitForSomeTime(5000);
	boolean progress= elementHandler.isElementDisplayed(GenerateDocumentPageObjects.lblProgress);
	AssertionHandler.verifyTrue(progress, "Quote page not displayed");			
}

public String generateDocumentAndSave() {	
	javascripthandler.scrollIntoView(QuotePageObjects.btnGenerate);
	elementHandler.clickElement(QuotePageObjects.btnGenerate, GenerateDocumentPageObjects.lblGenrtPrpsl);
	TestNGCustomReporter.log(logger, "clicked on generate button");	
	elementHandler.clickElement(GenerateDocumentPageObjects.radiobtnFindLaw);
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(GenerateDocumentPageObjects.btnGenerate, GenerateDocumentPageObjects.lnkClickHere);
	actionHandler.waitForSomeTime(10000);
	String parent=driver.getWindowHandle();
	elementHandler.clickElement(GenerateDocumentPageObjects.lnkClickHere);
	actionHandler.waitForSomeTime(5000);
	String root= System.getProperty("user.dir");
	
	/*File file= new File(root.replace("\\", "/") +"/src/main/java/tr/findlaw/executables/FileDownloads/");
	actionHandler.waitForSomeTime(2000);
	AssertionHandler.verifyTrue(file.exists(), "failed to download report");
	TestNGCustomReporter.log(logger, "Downloaded report");
	if(file.exists()) {
		file.delete();
	}*/
	
	Set<String> allHandles = driver.getWindowHandles();
	Iterator<String> I1= allHandles.iterator();
	while(I1.hasNext())
	{ 
	 String child_window=I1.next();
	if(!parent.equals(child_window))
	{
	driver.switchTo().window(child_window);	 
	System.out.println(driver.switchTo().window(child_window).getTitle()); 
	}	 
	}
    actionHandler.waitForSomeTime(2000);
	Actions action = new Actions(driver); 
	action.sendKeys(Keys.chord(Keys.CONTROL,"s")).build().perform();
	actionHandler.waitForSomeTime(2000);
	String filename= StringUtils.testDataModification("FindlawOrderForm");
	
	try {
		Runtime.getRuntime().exec(root.replace("\\", "/") +"/src/main/java/tr/findlaw/executables/FileDownload.exe");
				//+ " " + root +"\\src\\main\\java\\tr\\findlaw\\executables\\FileDownloads\\" + filename + ".signed.pdf");
		actionHandler.waitForSomeTime(4000);
	}catch(IOException e){
		e.printStackTrace();		
	}
	File file1= new File(root.replace("\\", "/") +"//src//main//java//tr//findlaw//executables//FileDownloads//");
	actionHandler.waitForSomeTime(2000);
	AssertionHandler.verifyTrue(file1.exists(), "failed to download" +filename+ "report");
	TestNGCustomReporter.log(logger, "Downloaded" +filename+ "report");
	if(file1.exists()) {
		file1.delete();
	}
	actionHandler.waitForSomeTime(2000);
	driver.switchTo().window(parent);
	actionHandler.waitForSomeTime(5000);
	boolean btnStatus= elementHandler.isElementDisplayed(QuotePageObjects.btnEdit);
	AssertionHandler.verifyTrue(btnStatus, "Quote page not displayed");
	return filename;
}

}