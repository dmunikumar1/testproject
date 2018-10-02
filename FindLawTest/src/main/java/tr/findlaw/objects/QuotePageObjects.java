package tr.findlaw.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QuotePageObjects {
 
	@FindBy(xpath="//input[@value='Create Quote/Proposal']/../../../../../../div[1]//td[2]/input[11]")
	public static WebElement btnCreateQuote;
	
	@FindBy(xpath="//input[@value='Create Quote/Proposal']/../../../../../../div[1]//td[2]/input[10]")
	public static WebElement btnCreateQuote1;
	
	@FindBy(xpath="//input[@value='Continue']")
	public static WebElement btnContinue;
	
	@FindBy(xpath="//label[text()='Sold To SSD']/../..//td[4]//img")
	public static WebElement lookupSoldToSSD;
	
	@FindBy(xpath="//a[contains(text(),'LEGAL')]")
	public static WebElement lnkLegal;
	
	@FindBy(xpath="//a[contains(text(),'IMPACT')]")
	public static WebElement lnkImpact;
	
	@FindBy(xpath="//input[@title='Attach File']")
	public static WebElement btnAttachFile;
	
	@FindBy(xpath="//label[3][text()='All Fields']/../input[2]")
	public static WebElement radiobtnAllFields;
	
	@FindBy(xpath="//div[@id='Source_System_Detail__c_body']//tr[2]/th/a")
	public static WebElement lnkAccountname;
	
	@FindBy(xpath="//label[text()='Ship to SSD']/../..//td[4]//img")
	public static WebElement lookupShipToSSD;
	
	@FindBy(xpath="//label[text()='Bill to SSD']/../..//td[4]//img")
	public static WebElement lookupBillToSSD;
	
	@FindBy(xpath="//td[@id='topButtonRow']/input[11]")
	public static WebElement btnCOB1;
	
	@FindBy(xpath="//input[@id='file']")
	public static WebElement btnFile;
	
	@FindBy(xpath="//input[@id='Attach']")
	public static WebElement btnAttach;
	
	@FindBy(xpath="//td[@id='topButtonRow']/input[@title='Submit Order']")
	public static WebElement btnSubmitOrder;
	
	@FindBy(xpath="//div[@id='ep']//div[3]//tr[1]/td[2]")
	public static WebElement lblFilename;
	
	@FindBy(xpath="//input[@title='Done']")
	public static WebElement btnDone;
	
	@FindBy(xpath="//span[text()='Notes & Attachments']")
	public static WebElement lnkNotesndAttachment;
	
	@FindBy(xpath="//label[text()='Wet Signature']/../../td[4]/input")
	public static WebElement chkbxWetSignature;
	
	@FindBy(xpath="//td[text()='Order Confirmation Contact']/../td[2]//a")
	public static WebElement lblOrderContact;
	
	@FindBy(xpath="//a[contains(text(),'BUILDER')]")
	public static WebElement lnkBuild;
	
	@FindBy(xpath="//a[contains(text(),'REPUTATION')]")
	public static WebElement lnkReputation;
	
	@FindBy(id="00N1300000BSTag")
	public static WebElement textproposalname;
	
	@FindBy(xpath="//a[contains(text(),'Social Promotion')]")
	public static WebElement lnkSocial;
	
	@FindBy(xpath="//input[@title='Save']")
	public static WebElement btnsave;
	
	@FindBy(xpath="//div[@id='Owner_ileinner']/a[text()='[Change]']")
	public static WebElement linkChange;
	
	@FindBy(xpath="//td[text()='Approval Segment']/../td[4]/div")
	public static WebElement lblApprvSeg;
	
	@FindBy(xpath="//td[text()='Approval Segment Default']/../td[4]/div")
	public static WebElement lblApprlSegDefault;
	
	@FindBy(xpath="//h3[text()='Select New Owner']")
	public static WebElement lblSelectNewOwner;
	
	@FindBy(id="newOwn")
	public static WebElement txtOwner; 
	
	@FindBy(xpath="//div[@id='ep']/div[1]/table/tbody/tr/td[1]/h2")
	public static WebElement lblQuoteproposal;
	
	@FindBy(xpath="//td[text()='Proposal ID']/../td[2]")
	public static WebElement lblProposalId;
	
	@FindBy(xpath="//input[@title='Save']")
	public static WebElement btnSave; 
	
	@FindBy(xpath="//input[@title='Edit']/../../../../../../div[1]/table/tbody/tr/td[2]/input[3]")
	public static WebElement btnEdit; 
	
	@FindBy(xpath="//label[text()='Approval Segment']")
	public static WebElement lblApprovalSeg; 
	
	@FindBy(id="00N1300000BSUqd")
	public static WebElement drpdwnApprovalSegment;
	
	@FindBy(xpath="//label[text()='Payment Option']/../../td[2]//span/select")
	public static WebElement drpdwnPaymentOption;
	
	@FindBy(xpath="//h2[text()='Quote/Proposal Edit']")
	public static WebElement lblQuote;	
	
	@FindBy(xpath="//span[text()='Sales']")
	public static WebElement btnSales;	
	
	@FindBy(xpath="//h2[text()='Approval Request Detail']")
	public static WebElement lblApprovalReq;
	
	@FindBy(xpath="//a[text()='Reassign']")
	public static WebElement lnkReassign;
	
	@FindBy(xpath="//tr[2]/td[1]//input")
	public static WebElement txtApprover;
	
	@FindBy(xpath="//tr[3]/td[1]//textarea")
	public static WebElement txtComments;
	
	@FindBy(xpath="//div[1]/table//input[@value='Reassign']")
	public static WebElement btnReassign;
	
	@FindBy(xpath="//a[text()='Apttus Approvals Management']")
	public static WebElement lnkApptusMgnt;
	
	@FindBy(xpath="//a[text()='Approval Requests']")
	public static WebElement lnkApprovalReq;
	
	@FindBy(xpath="//td[@id='bodyCell']//table/tbody/tr[2]/th/a")
	public static WebElement lnkName;
	
	@FindBy(xpath="//input[@title='Save']/../../../../../../div[1]/table/tbody/tr/td[2]/input[1]")
	public static WebElement btnSave1;
	
	@FindBy(xpath = "//div[@id='00N1300000BSUqm_ileinner']/a/img")
	public static WebElement btnAddProduct;
	
	@FindBy(xpath = "//td[text()='Add/Edit Products']")
	public static WebElement lblAddProducts;
	
	@FindBy(xpath = "//input[@id='CF00N1300000BSUr0']")
	public static WebElement txtContact28;
	
	@FindBy(xpath = "//td[@id='topButtonRow']/input[7]")
	public static WebElement btnPasswdHolders;
	
	@FindBy(xpath = "//img[@alt='Generate']")
	public static WebElement btnGenerate;
	
	@FindBy(xpath = "//img[@alt='Send For eSignatures']")
	public static WebElement btnEsignature;
	
	@FindBy(xpath = "//div[@id='Apttus_Proposal__Proposal__c_body']/table/tbody/tr[2]/th/a")
	public static WebElement lnkQuoteID;
	
	@FindBy(xpath = "//span[@id='01I13000000H0V2.00N1300000BSUbS-_help']")
	public static WebElement lblCheckeSgnStatus;
	
	@FindBy(xpath = "//img[@alt='Check eSignature Status']")
	public static WebElement btnValidateEsign;

	@FindBy(xpath = "//span[text()='Orders']")
	public static WebElement lnkOrders;
	
	@FindBy(xpath = "//div[@class='pbBody']//../../../../../../../div[12]/div[1]/div/div[2]/table/tbody/tr[2]/th/a")
	public static WebElement lnkOrderNumber;
	
	@FindBy(xpath = "//div[@id='Name_ileinner']")
	public static WebElement lblOrderNo;
	
	@FindBy(xpath = "//div[@id='00N0R000000Oeiw_ileinner']/a/img")
	public static WebElement btnCOB;
	
	@FindBy(xpath = "//a[@id='globalHeaderNameMink']/b")
	public static WebElement navigationArrow;
	
	@FindBy(id = "userNavButton")
	public static WebElement lblUserMenu;
	
	@FindBy(xpath = "//a[text()='Logout']")
	public static WebElement linkLogout;	
	
	@FindBy(xpath = "//td[@id='bodyCell']/div[4]/div[2]/div[2]//tr[8]/td[4]/div")
	public static WebElement lblIncremental;
}
