package tr.findlaw.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MasterContactsPageObjects {

	@FindBy(xpath = "//td[@id='topButtonRow']/input[@title='Master Contacts']")
	public static WebElement btnMasterContacts;
	
	@FindBy(xpath = "//div[@class='pbSubsection']//tbody/tr[1]/td[2]/input")
	public static WebElement txtboxmcFirstName;
	
	@FindBy(xpath = "//div[@class='pbSubsection']//tbody/tr[1]/td[3]/input")
	public static WebElement txtboxmcLastName;
	
	@FindBy(xpath = "//div[@class='pbSubsection']//tbody/tr[1]/td[4]/input")
	public static WebElement txtboxmcEmail;
	
	@FindBy(xpath = "//div[@class='pbHeader']//td/input[1]")
	public static WebElement btnSave;
}
