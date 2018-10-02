package tr.findlaw.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GenerateDocumentPageObjects {
	
	@FindBy(xpath = "//h1[text()='Generate Proposal']")
	public static WebElement lblGenrtPrpsl;
	
	@FindBy(xpath = "//h3[text()='Templates']/../..//tr[1]//input")
	public static WebElement radiobtnFindLaw;
	
	@FindBy(xpath = "//tbody/tr[9]/td[1]//input")
	public static WebElement radiobtnWestOrderForm;
	
	@FindBy(xpath = "//input[@id='j_id0:idGenerateProposal:j_id53:j_id58:j_id59']")
	public static WebElement btnGenerate;
	
	@FindBy(xpath = "//input[@value='Return']")
	public static WebElement btnReturn;
	
	@FindBy(xpath = "//div[@class='pbBody']//div[2]//tr[3]/td/a")
	public static WebElement lnkClickHere;
	
	@FindBy(xpath = "//button[@id='download']")
	public static WebElement btnDownload;
	
	@FindBy(xpath = "//td[text()='Progress']")
	public static WebElement lblProgress;
}

