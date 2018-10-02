package tr.findlaw.objects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPageObjects { 

	@FindBy(xpath = "//div[@id='Account_body']/table/tbody/tr[2]/th/a")
	public static WebElement linkAccountName;

	@FindBy(xpath = "//td[text()='SAP Account Number']/../td[4]/div")
	public static WebElement lblAccountNo;
	
	
	@FindBy(xpath = "//span[text()='Asset Line Items (Sold To)']")
	public static WebElement lnkAssetLine;
	
	@FindBy(xpath = "//h3[text()='Asset Line Items (Sold To)']/../../../../../..//div/a[2]")
	public static WebElement lnkGotoList;
	
	@FindBy(xpath = "//div[38]/table/tbody/tr/td[2]//tr[10]//td[3]/a")
	public static WebElement lnkResourcePage;
	
	@FindBy(xpath = "//div[38]/table/tbody/tr/td[2]//tr[11]//td[3]/a")
	public static WebElement lnkCOB;
	
	@FindBy(xpath = "//table/tbody//th/a")
	public static List <WebElement> lnkAssets;
}



