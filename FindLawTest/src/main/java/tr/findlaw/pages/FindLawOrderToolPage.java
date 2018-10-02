package tr.findlaw.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.framework.asserts.AssertionHandler;
import com.framework.reports.ReportBuilder;
import com.framework.reports.TestNGCustomReporter;
import com.framework.utils.ExcelUtils;
import junit.framework.Assert;
import tr.findlaw.objects.FindLawOrderToolPageObjects;
import tr.findlaw.objects.QuotePageObjects;
import tr.findlaw.objects.SafeLoginpageObjects;

public class FindLawOrderToolPage extends BasePage {

	String lblCustomerInfo ="//a[text()='Customer Information']";
	
	
	public FindLawOrderToolPage(WebDriver webDriver) {
		super(webDriver);
		
	}

	public void loginWithSafeuserId(String QuoteId) {
		String userName = ExcelUtils.getDataByColumnName("Login", "Safe_UserId");
		String password = ExcelUtils.getDataByColumnName("Login", "Safe_Password");
		windowHandler.switchToFrame(SafeLoginpageObjects.iframe, SafeLoginpageObjects.txtSAFEUserId);
		elementHandler.writeText(SafeLoginpageObjects.txtSAFEUserId, userName, SafeLoginpageObjects.txtSAFEPassword);
		elementHandler.writeText(SafeLoginpageObjects.txtSAFEPassword, password, SafeLoginpageObjects.btnLogin);
		elementHandler.clickElement(SafeLoginpageObjects.btnLogin);
		actionHandler.waitForSomeTime(25000);
		windowHandler.refereshWebPage();
		windowHandler.switchToParentFrame(FindLawOrderToolPageObjects.txtOrderId);
		actionHandler.waitForSomeTime(5000);
		boolean order= elementHandler.isElementDisplayed(FindLawOrderToolPageObjects.txtOrderId);
		AssertionHandler.verifyTrue(order, "Order Id field not displayed");
		elementHandler.waitForIdToLoad(FindLawOrderToolPageObjects.txtOrderId);
		elementHandler.writeText(FindLawOrderToolPageObjects.txtOrderId, QuoteId,FindLawOrderToolPageObjects.btnGO);
		elementHandler.clickElement(FindLawOrderToolPageObjects.btnGO);
		actionHandler.waitForSomeTime(25000);
		
		
	}
	//click COB button from middle of the page
	public void clickCOB() {
		javascripthandler.scrollIntoView(QuotePageObjects.btnCOB);
		elementHandler.clickElement(QuotePageObjects.btnCOB);
		actionHandler.waitForSomeTime(25000);
	}
	
	//click COB button on top of the Quote page
	public void clickCOBbutton() {
		String handler= windowHandler.getParentWindow();
		System.out.println(handler);
		elementHandler.clickElement(QuotePageObjects.btnCOB1);
		windowHandler.switchToLatestWindow(SafeLoginpageObjects.iframe);
		actionHandler.waitForSomeTime(25000);	
	}
	
	public void cobProcess(int ctrlval) {
		switch(ctrlval)
		{
	
/*
 * This case is for Firmsite 333C product: 40483699	
 */
		case 1:
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(20000);
			actionHandler.waitForSomeTime(20000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFmCnctEmail);
			String Email = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFmCnctEmail, Email,
					FindLawOrderToolPageObjects.txtFmCnctPhone);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFmCnctPhone);
			String Phone = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFmCnctPhone, Phone,
					FindLawOrderToolPageObjects.drpdwnPractiseArea);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnPractiseArea);
			actionHandler.waitForSomeTime(2000);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnPractiseArea, 2,
					FindLawOrderToolPageObjects.drpdwnLegalContent2);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnLegalContent2);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnLegalContent2, 2,
					FindLawOrderToolPageObjects.txtURL);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtURL);
			String URL = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtURL, URL,
					FindLawOrderToolPageObjects.txtadditionalinfo);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtadditionalinfo);
			String Additional = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtadditionalinfo, Additional,
					FindLawOrderToolPageObjects.drpdwnCustomerGoal);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal,
					FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1,
					FindLawOrderToolPageObjects.txtGeographic);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtGeographic);
			String Geographic = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtGeographic, Geographic,
					FindLawOrderToolPageObjects.txtClient);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtClient);
			String Client = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtClient, Client,
					FindLawOrderToolPageObjects.tabproductQuestion);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.tabproductQuestion);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion,
					FindLawOrderToolPageObjects.btnExpand.get(0));

			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnExpand.get(0));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand.get(0), FindLawOrderToolPageObjects.btnExpand1);
			actionHandler.waitForSomeTime(1000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand1
					);
			actionHandler.waitForSomeTime(2000);

			String title1= elementHandler.getText(FindLawOrderToolPageObjects.Expandsubbtntitle);
			System.out.println("title:" +title1);
			boolean value= title1.contains("41056103 - Starter Profile");
			if (value == true) {
				System.out.println("started with 1st product");
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(3));
				String Attorney_Name = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(3), Attorney_Name,
						FindLawOrderToolPageObjects.txtinputs2.get(5));

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(5));
				String Primary_area = ExcelUtils.getDataByColumnName("COB", "Primary_area");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(5), Primary_area,
						FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1));

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1));
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1),
						FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1,
						FindLawOrderToolPageObjects.txtinputs2.get(6));

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(6));
				String Prac_Area1 = ExcelUtils.getDataByColumnName("COB", "Prac_Area1");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(6), Prac_Area1,
						FindLawOrderToolPageObjects.drpdwnSelect.get(6));

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(6));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(6), 1,
						FindLawOrderToolPageObjects.drpdwnSelect.get(7));

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(7));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(7), 1,
						FindLawOrderToolPageObjects.txtinputs2.get(7));

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(7));
				String Email_Add = ExcelUtils.getDataByColumnName("COB", "Email_Add");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(7), Email_Add,
						FindLawOrderToolPageObjects.btnExpand2);
				
				
			}else {
				
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(6));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(6), 1,
					FindLawOrderToolPageObjects.drpdwnSelect.get(7));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(7));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(7), 2,
					FindLawOrderToolPageObjects.btnExpand2);

			}
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpand2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand2);
			
			String title2= elementHandler.getText(FindLawOrderToolPageObjects.Expandsubbtn1title);
			System.out.println("title:" +title2);
			boolean value1= title2.contains("40588593 - Firmsite III");
			if (value1 == true) {
			System.out.println("started with 2nd product");
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(8));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(8), 2,
					FindLawOrderToolPageObjects.drpdwnSelect.get(9));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(9));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(9), 2,
					FindLawOrderToolPageObjects.btnSave);
			
			
			}else {
		
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(5));
				String Attorney_Name = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(5), Attorney_Name,
						FindLawOrderToolPageObjects.txtinputs2.get(5));
				actionHandler.waitForSomeTime(5000);
				
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(5));
				String Primary_area = ExcelUtils.getDataByColumnName("COB", "Primary_area");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(5), Primary_area,
						FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1));
				actionHandler.waitForSomeTime(5000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1));
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1),
						FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1,
						FindLawOrderToolPageObjects.txtinputs2.get(6));
				actionHandler.waitForSomeTime(5000);

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(6));
				String Prac_Area1 = ExcelUtils.getDataByColumnName("COB", "Prac_Area1");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(6), Prac_Area1,
						FindLawOrderToolPageObjects.drpdwnSelect.get(8));

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(8));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(8), 1,
						FindLawOrderToolPageObjects.drpdwnSelect.get(9));

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(9));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(9), 2,
						FindLawOrderToolPageObjects.btnSave);

				/*javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(7));
				String Email_Add = ExcelUtils.getDataByColumnName("COB", "Email_Add");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(7), Email_Add,
						FindLawOrderToolPageObjects.btnSave);*/
				
			}
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(5000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(5000);
			
			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(5000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String Status=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status.contains("COB Completed"));
			break;
	
/*
 * This case is for IMC product:41892830			
 */
		case 2:
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(20000);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabgeneralQues);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFmCnctEmail);
			String Cont_Email = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFmCnctEmail, Cont_Email,
					FindLawOrderToolPageObjects.txtFmCnctPhone);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFmCnctPhone);
			String Phone1 = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFmCnctPhone, Phone1,
					FindLawOrderToolPageObjects.drpdwnLegalContent1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnLegalContent1);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnLegalContent1, 1,
					FindLawOrderToolPageObjects.txtFirstName);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFirstName);
			String FirstName = ExcelUtils.getDataByColumnName("COB", "First_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFirstName, FirstName,
					FindLawOrderToolPageObjects.txtLastName);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtLastName);
			String LastName = ExcelUtils.getDataByColumnName("COB", "Last_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtLastName, LastName,
					FindLawOrderToolPageObjects.txtEmailaddr);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtEmailaddr);
			String Email2 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtEmailaddr, Email2,
					FindLawOrderToolPageObjects.tabproductQuestion);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.tabproductQuestion);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion,
					FindLawOrderToolPageObjects.btnExpand.get(0));
			
			elementHandler.waitForXpathToLoad("//product/expandable/div/button");
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand.get(0), FindLawOrderToolPageObjects.btnExpand1);
			actionHandler.waitForSomeTime(1000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand1,
					FindLawOrderToolPageObjects.drpdwnNewDomain);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnNewDomain);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnNewDomain, 1,
					FindLawOrderToolPageObjects.btnBlog);
					
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnBlog);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnBlog,
					FindLawOrderToolPageObjects.drpdwnStrategy);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnStrategy);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnStrategy, 1,
					FindLawOrderToolPageObjects.txtURLCustomer);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtURLCustomer);
			String url = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtURLCustomer, url,
					FindLawOrderToolPageObjects.btnLocal);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnLocal);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnLocal,
					FindLawOrderToolPageObjects.drpdwnState);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnState);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnState, 1,
					FindLawOrderToolPageObjects.drpdwnCountry);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCountry);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnCountry, 1,
					FindLawOrderToolPageObjects.drpdwnblog);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnblog);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnblog, 1,
					FindLawOrderToolPageObjects.btnCusInpt);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnCusInpt);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnCusInpt,
					FindLawOrderToolPageObjects.txtCustomerprac);
			
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtCustomerprac);
			String Cusprac = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtCustomerprac, Cusprac,
					FindLawOrderToolPageObjects.txtCustomermkt);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtCustomermkt);
			String Cusmarkt = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtCustomermkt, Cusmarkt,
					FindLawOrderToolPageObjects.txtGeoStratgy);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtGeoStratgy);
			String GeoStrat = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtGeoStratgy, GeoStrat,
					FindLawOrderToolPageObjects.btnFirmsite);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnFirmsite);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnFirmsite,
					FindLawOrderToolPageObjects.drpdwnNewDomain1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnNewDomain1);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnNewDomain1, 1,
					FindLawOrderToolPageObjects.drpdwnCustOperatn);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustOperatn);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnCustOperatn, 1,
					FindLawOrderToolPageObjects.btnStarteProfile);	
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnStarteProfile);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnStarteProfile,
					FindLawOrderToolPageObjects.txtAttorneyName1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtAttorneyName1);
			String Attr = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtAttorneyName1, Attr,
					FindLawOrderToolPageObjects.txtPrimaryArea1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtPrimaryArea1);
			String PracArea = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtPrimaryArea1, PracArea,
					FindLawOrderToolPageObjects.drpdwnFLPractiseArea);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnFLPractiseArea);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnFLPractiseArea,FindLawOrderToolPageObjects.drpdwnFLPractiseArea1);
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnFLPractiseArea1,FindLawOrderToolPageObjects.drpdwnSelectMult );
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnSelectMult, FindLawOrderToolPageObjects.txtPracArea1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtPracArea1);
			String PracArea1 = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtPracArea1, PracArea1,
					FindLawOrderToolPageObjects.drpdwnSelect.get(8));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(8));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(8), 1,
					FindLawOrderToolPageObjects.drpdwnSelect.get(9));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(9));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(9), 1,
					FindLawOrderToolPageObjects.txtinputs2.get(12));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(12));
			String Email4 = ExcelUtils.getDataByColumnName("COB", "Email_Add");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(12), Email4,
					FindLawOrderToolPageObjects.btnSave);
			
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(5000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(5000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(5000);
			TestNGCustomReporter.log(logger, "COB Process completed +ReportBuilder.takesScreenshot()");
			actionHandler.waitForSomeTime(5000);
			String Status1=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status1.contains("COB Completed"));
			break;
			
/*
 * This case is for Firmsite 333C product- has not been used till now			
 */
		case 3:
			
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(20000);
			actionHandler.waitForSomeTime(50000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFmCnctEmail);
			String Email1 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFmCnctEmail, Email1,
					FindLawOrderToolPageObjects.txtFmCnctPhone);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFmCnctPhone);
			String Phone2 = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFmCnctPhone, Phone2,
					FindLawOrderToolPageObjects.drpdwnPractiseArea);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnPractiseArea);
			actionHandler.waitForSomeTime(2000);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnPractiseArea, 2,
					FindLawOrderToolPageObjects.drpdwnLegalContent);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnLegalContent);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnLegalContent, 2,
					FindLawOrderToolPageObjects.txtURL);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtURL);
			String URL1 = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtURL, URL1,
					FindLawOrderToolPageObjects.txtadditionalinfo);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtadditionalinfo);
			String Additional1 = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtadditionalinfo, Additional1,
					FindLawOrderToolPageObjects.drpdwnCustomerGoal);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal,
					FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1,
					FindLawOrderToolPageObjects.txtGeographic);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtGeographic);
			String Geographic1 = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtGeographic, Geographic1,
					FindLawOrderToolPageObjects.txtClient);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtClient);
			String Client1 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtClient, Client1,
					FindLawOrderToolPageObjects.tabproductQuestion);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.tabproductQuestion);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion,
					FindLawOrderToolPageObjects.btnExpand.get(0)
					);

			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnExpand.get(0));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand.get(0), FindLawOrderToolPageObjects.btnExpand1);
			actionHandler.waitForSomeTime(1000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand1);
			actionHandler.waitForSomeTime(2000);
			
			if (FindLawOrderToolPageObjects.txtAttorneyName2.size()>0) {
			elementHandler.writeText(FindLawOrderToolPageObjects.txtAttorneyName2.get(0), "Testing Test",
					FindLawOrderToolPageObjects.txtPrimaryArea2);
			
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtPrimaryArea2);
			String Pri_area = ExcelUtils.getDataByColumnName("COB", "Primary_area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtPrimaryArea2, Pri_area,
					FindLawOrderToolPageObjects.drpdwnPractisearea);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnPractisearea);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnPractisearea);
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnPractisevalues.get(2),FindLawOrderToolPageObjects.txtPractisearea2);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtPractisearea2);
			String Prac_area = ExcelUtils.getDataByColumnName("COB", "Prac_Area1");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtPractisearea2, Prac_area,
					FindLawOrderToolPageObjects.drpdwnFirmAccepts1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnFirmAccepts1);
			actionHandler.waitForSomeTime(2000);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnFirmAccepts1, 1,
					FindLawOrderToolPageObjects.drpdwnFirmOffers1);
			
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnFirmOffers1);
			actionHandler.waitForSomeTime(2000);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnFirmOffers1, 1,
					FindLawOrderToolPageObjects.txtEmailadd);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtEmailadd);
			String txt_Email = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtEmailadd, txt_Email,
					FindLawOrderToolPageObjects.btnExpand2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpand2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand2,FindLawOrderToolPageObjects.drpdwnNewDomain2);
			
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnNewDomain2);
			actionHandler.waitForSomeTime(2000);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnNewDomain2, 1,
					FindLawOrderToolPageObjects.drpdownGeoStratgy1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdownGeoStratgy1);
			actionHandler.waitForSomeTime(2000);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdownGeoStratgy1, 1,
					FindLawOrderToolPageObjects.txtThoughtsCustomer);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtThoughtsCustomer);
			String cust = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtThoughtsCustomer, cust,
					FindLawOrderToolPageObjects.btnSave);

			}
			else{
			
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnNewDomain);
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnNewDomain, 1,
						FindLawOrderToolPageObjects.drpdwnStrategy);

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnStrategy);
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnStrategy, 1,
						FindLawOrderToolPageObjects.txtThoughtsCustomer);

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtThoughtsCustomer);
				String Customer1 = ExcelUtils.getDataByColumnName("COB", "Customer");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtThoughtsCustomer, Customer1,
						FindLawOrderToolPageObjects.btnExpand2);
				elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand2,
						FindLawOrderToolPageObjects.txtAttorneyName);

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtAttorneyName);
				String Attorney_Name1 = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtAttorneyName, Attorney_Name1,
						FindLawOrderToolPageObjects.txtPrimaryArea);

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtPrimaryArea);
				String Primary_area1 = ExcelUtils.getDataByColumnName("COB", "Primary_area");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtPrimaryArea, Primary_area1,
						FindLawOrderToolPageObjects.drpdwnFndlwpractiseArea);

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnFndlwpractiseArea);
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnFndlwpractiseArea,
						FindLawOrderToolPageObjects.drpdwnpractiseAreavalue);
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnpractiseAreavalue,
						FindLawOrderToolPageObjects.txtFndlwpractiseArea1);

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFndlwpractiseArea1);
				String Prac_Area2 = ExcelUtils.getDataByColumnName("COB", "Prac_Area1");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtFndlwpractiseArea1, Prac_Area2,
						FindLawOrderToolPageObjects.drpdwnfirmAccept);

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnfirmAccept);
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnfirmAccept, 1,
						FindLawOrderToolPageObjects.drpdwnfirmOffers);

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnfirmOffers);
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnfirmOffers, 1,
						FindLawOrderToolPageObjects.txtEmailAddress);

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtEmailAddress);
				String Email_Add1 = ExcelUtils.getDataByColumnName("COB", "Email_Add");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtEmailAddress, Email_Add1,
						FindLawOrderToolPageObjects.btnSave);	
				
			}
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave,FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(2000);
			
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(5000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			TestNGCustomReporter.log(logger, "COB Process completed +ReportBuilder.takesScreenshot()");
			actionHandler.waitForSomeTime(5000);
			String Status3=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status3.contains("COB Completed"));
			break;
		
/*
 * 
 * This case is for Engagement and OTC products:42089522,30572846			
 */
		case 4:
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(20000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFmCnctEmail);
			String email = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFmCnctEmail, email,
					FindLawOrderToolPageObjects.txtFmCnctPhone);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFmCnctPhone);
			String phone = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFmCnctPhone, phone,
					FindLawOrderToolPageObjects.txturl1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txturl1);
			String url1= ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txturl1, url1,
					FindLawOrderToolPageObjects.txtspecialinst);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtspecialinst);
			String special= ExcelUtils.getDataByColumnName("COB", "special_Inst");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtspecialinst, special,
					FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal2,
					FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1,
					FindLawOrderToolPageObjects.txtinputs2.get(3));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(3));
			String url2= ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(3), url2,
			FindLawOrderToolPageObjects.txtinputs.get(2));
			
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(2));
			String url3= ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(2), url3,
			FindLawOrderToolPageObjects.txtinputs.get(3));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(3));
			String url4= ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(3), url4,
			FindLawOrderToolPageObjects.btnSave);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave,FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(2000);
			
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(5000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(2000);
			actionHandler.waitForSomeTime(5000);
			String Status4=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status4.contains("COB Completed"));
			break;
	
/*
 * This case is for all IM products IMS,IMP, IMC, IME and Webchat product
 * 			
 */
		case 5:
			actionHandler.waitForSomeTime(35000);
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(25000);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabgeneralQues);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFmCnctEmail);
			String Cont_Email1 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFmCnctEmail, Cont_Email1,
					FindLawOrderToolPageObjects.txtFmCnctPhone);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFmCnctPhone);
			String Phone3 = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFmCnctPhone, Phone3,
					FindLawOrderToolPageObjects.drpdwnLegalContent1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnLegalContent1);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnLegalContent1, 1,
					FindLawOrderToolPageObjects.txtURL2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtURL2);
			String urlw = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtURL2, urlw,
					FindLawOrderToolPageObjects.txtadditionalinfo1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtadditionalinfo1);
			String add_info = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtadditionalinfo1, add_info,
					FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal2,
					FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1,
					FindLawOrderToolPageObjects.txtaddnlgeographic);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtaddnlgeographic);
			String add_geo = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtaddnlgeographic, add_geo,
					FindLawOrderToolPageObjects.txtClient1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtClient1);
			String Client2 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtClient1, Client2,
					FindLawOrderToolPageObjects.txtFirstName);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFirstName);
			String First_Name = ExcelUtils.getDataByColumnName("COB", "First_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFirstName, First_Name,
					FindLawOrderToolPageObjects.txtLastName);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtLastName);
			String Last_Name = ExcelUtils.getDataByColumnName("COB", "Last_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtLastName, Last_Name,
					FindLawOrderToolPageObjects.txtEmailaddr);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtEmailaddr);
			String Email3 = ExcelUtils.getDataByColumnName("COB", "Email_Add");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtEmailaddr, Email3,
					FindLawOrderToolPageObjects.txtApproved);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtApproved);
			String Approved = ExcelUtils.getDataByColumnName("COB", "Approved");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtApproved, Approved,
					FindLawOrderToolPageObjects.tabproductQuestion);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.tabproductQuestion);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion,
					FindLawOrderToolPageObjects.buttonExpand1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.buttonExpand1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.buttonExpand1,
					FindLawOrderToolPageObjects.txtAttorneyName3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtAttorneyName3);
			String Attr_Name = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtAttorneyName3, Attr_Name,
					FindLawOrderToolPageObjects.txtPrimaryArea2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtPrimaryArea2);
			String Pri_Area = ExcelUtils.getDataByColumnName("COB", "Primary_area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtPrimaryArea2, Pri_Area,
					FindLawOrderToolPageObjects.drpdwnPractisearea);
			
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnPractisearea);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnPractisearea,
					FindLawOrderToolPageObjects.drpdwnpractiseAreavalue);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnpractiseAreavalue);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnpractiseAreavalue,
					FindLawOrderToolPageObjects.txtPractisearea2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtPractisearea2);
			String Pri_Area1 = ExcelUtils.getDataByColumnName("COB", "Primary_area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtPractisearea2, Pri_Area1,
					FindLawOrderToolPageObjects.drpdwnFirmAccepts1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnFirmAccepts1);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnFirmAccepts1, 1,
					FindLawOrderToolPageObjects.drpdwnFirmAccepts2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnFirmAccepts2);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnFirmAccepts2, 1,
					FindLawOrderToolPageObjects.drpdwnFirmOffers2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnFirmOffers2);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnFirmOffers2, 1,
					FindLawOrderToolPageObjects.txtEmail2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtEmail2);
			String email1 = ExcelUtils.getDataByColumnName("COB", "Primary_area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtEmail2, email1,
					FindLawOrderToolPageObjects.btnPPC);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnPPC);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnPPC,
					FindLawOrderToolPageObjects.drpdwnCampaign);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCampaign);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnCampaign, 1,
					FindLawOrderToolPageObjects.txtpracarea);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtpracarea);
			String prac_area = ExcelUtils.getDataByColumnName("COB", "Primary_area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtpracarea, prac_area,
					FindLawOrderToolPageObjects.drpdwnClient);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnClient);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnClient, 1,
					FindLawOrderToolPageObjects.drpdwnTollFree);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnTollFree);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnTollFree, 2,
					FindLawOrderToolPageObjects.txtdestPhone);
			
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtdestPhone);
			String Dest_Phone = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtdestPhone, Dest_Phone,
					FindLawOrderToolPageObjects.txtspecialinst1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtspecialinst1);
			String Spe_Inst = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtspecialinst1, Spe_Inst,
					FindLawOrderToolPageObjects.btnBlog);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnBlog);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnBlog,
					FindLawOrderToolPageObjects.drpdwnBlogFocus);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnBlogFocus);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnBlogFocus, 3,
					FindLawOrderToolPageObjects.txturl4);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txturl4);
			String urlp = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txturl4, urlp,
					FindLawOrderToolPageObjects.btnLocal);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnLocal);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnLocal,
					FindLawOrderToolPageObjects.drpdwnState1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnState1);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnState1, 3,
					FindLawOrderToolPageObjects.drpdwnCountry1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCountry1);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnCountry1, 2,
					FindLawOrderToolPageObjects.drpdwnBlogFocus1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnBlogFocus1);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnBlogFocus1, 2,
					FindLawOrderToolPageObjects.btnCusInpt);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnCusInpt);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnCusInpt,
					FindLawOrderToolPageObjects.txtShownClient);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtShownClient);
			String Client3 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtShownClient, Client3,
					FindLawOrderToolPageObjects.txtCustomer);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtCustomer);
			String Customer2 = ExcelUtils.getDataByColumnName("COB", "Customer");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtCustomer, Customer2,
					FindLawOrderToolPageObjects.txtCustmarkt);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtCustmarkt);
			String Customer3 = ExcelUtils.getDataByColumnName("COB", "Customer");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtCustmarkt, Customer3,
					FindLawOrderToolPageObjects.txtGeoStart);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtGeoStart);
			String Geo_Start = ExcelUtils.getDataByColumnName("COB", "Strategy");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtGeoStart, Geo_Start,
					FindLawOrderToolPageObjects.buttonExpand2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.buttonExpand2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.buttonExpand2,
					FindLawOrderToolPageObjects.btnsubExpand1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnsubExpand1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnsubExpand1,
					FindLawOrderToolPageObjects.txtAttorney);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtAttorney);
			String Attorney = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtAttorney, Attorney,
					FindLawOrderToolPageObjects.txtPriarea);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtPriarea);
			String area1 = ExcelUtils.getDataByColumnName("COB", "Primary_area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtPriarea, area1,
					FindLawOrderToolPageObjects.drpdwnpracarea);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnpracarea);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnpracarea,
					FindLawOrderToolPageObjects.drpdwnpractiseAreavalue);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnpractiseAreavalue);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnpractiseAreavalue,
					FindLawOrderToolPageObjects.txtpracarea1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtpracarea1);
			String area2 = ExcelUtils.getDataByColumnName("COB", "Primary_area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtpracarea1, area2,
					FindLawOrderToolPageObjects.drpdwnCustomer);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomer);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnCustomer, 1,
					FindLawOrderToolPageObjects.drpdwnFirmAccept);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnFirmAccept);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnFirmAccept, 1,
					FindLawOrderToolPageObjects.drpdwnFirmOffer1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnFirmOffer1);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnFirmOffer1, 1,
					FindLawOrderToolPageObjects.txtEmail);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtEmail);
			String email2 = ExcelUtils.getDataByColumnName("COB", "Email_Add");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtEmail, email2,
					FindLawOrderToolPageObjects.btnPPC1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnPPC1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnPPC1,
					FindLawOrderToolPageObjects.drpdwnCampaign1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCampaign1);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnCampaign1, 1,
					FindLawOrderToolPageObjects.txtpracarea2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtpracarea2);
			String area3 = ExcelUtils.getDataByColumnName("COB", "Prac_Area1");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtpracarea2, area3,
					FindLawOrderToolPageObjects.drpdwnClient2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnClient2);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnClient2, 1,
					FindLawOrderToolPageObjects.drpdwnTolFree1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnTolFree1);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnTolFree1, 1,
					FindLawOrderToolPageObjects.txtDestPh);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtDestPh);
			String phone4 = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtDestPh, phone4,
					FindLawOrderToolPageObjects.txtsplInst);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtsplInst);
			String inst = ExcelUtils.getDataByColumnName("COB", "special_Inst");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtsplInst, inst,
					FindLawOrderToolPageObjects.btnBlog2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnBlog2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnBlog2,
					FindLawOrderToolPageObjects.drpdwnBlogFocus2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnBlogFocus2);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnBlogFocus2, 2,
					FindLawOrderToolPageObjects.txturl3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txturl3);
			String url5 = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txturl3, url5,
					FindLawOrderToolPageObjects.btnLocal2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnLocal2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnLocal2,
					FindLawOrderToolPageObjects.drpdwnState2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnState2);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnState2, 1,
					FindLawOrderToolPageObjects.drpdwnCountry2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCountry2);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnCountry2, 1,
					FindLawOrderToolPageObjects.drpdwnBlogFucs);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnBlogFucs);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnBlogFucs, 1,
					FindLawOrderToolPageObjects.btnCusInp2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnCusInp2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnCusInp2,
					FindLawOrderToolPageObjects.txtClient4);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtClient4);
			String Client5 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtClient4, Client5,
					FindLawOrderToolPageObjects.txtCust2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtCust2);
			String cust = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtCust2, cust,
					FindLawOrderToolPageObjects.txtCustmarkt1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtCustmarkt1);
			String cust1 = ExcelUtils.getDataByColumnName("COB", "special_Inst");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtCustmarkt1, cust1,
					FindLawOrderToolPageObjects.txtGeoStart2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtGeoStart2);
			String geo = ExcelUtils.getDataByColumnName("COB", "Strategy");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtGeoStart2, geo,
					FindLawOrderToolPageObjects.buttonExpand3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.buttonExpand3);
			elementHandler.clickElement(FindLawOrderToolPageObjects.buttonExpand3,
					FindLawOrderToolPageObjects.btnsub1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnsub1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnsub1,
					FindLawOrderToolPageObjects.drpdwnCustomer1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomer1);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnCustomer1, 1,
					FindLawOrderToolPageObjects.btnBlog3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnBlog3);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnBlog3,
					FindLawOrderToolPageObjects.drpdwnBlogFocus3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnBlogFocus3);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnBlogFocus3, 3,
					FindLawOrderToolPageObjects.txtURLCust);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtURLCust);
			String url_cust = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtURLCust, url_cust,
					FindLawOrderToolPageObjects.btnLocal4);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnLocal4);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnLocal4,
					FindLawOrderToolPageObjects.drpdwnState3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnState3);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnState3, 1,
					FindLawOrderToolPageObjects.drpdwnCountry3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCountry3);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnCountry3, 1,
					FindLawOrderToolPageObjects.drpdwnBlogFcs);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnBlogFcs);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnBlogFcs, 1,
					FindLawOrderToolPageObjects.btnCusInp3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnCusInp3);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnCusInp3,
					FindLawOrderToolPageObjects.txtCust3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtCust3);
			String cust2 = ExcelUtils.getDataByColumnName("COB", "Customer");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtCust3, cust2,
					FindLawOrderToolPageObjects.txtCustMarkt);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtCustMarkt);
			String mrkt = ExcelUtils.getDataByColumnName("COB", "Customer");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtCustMarkt, mrkt,
					FindLawOrderToolPageObjects.txtgeostrat);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtgeostrat);
			String strat = ExcelUtils.getDataByColumnName("COB", "Strategy");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtgeostrat, strat,
					FindLawOrderToolPageObjects.btnFirmsite1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnFirmsite1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnFirmsite1,
					FindLawOrderToolPageObjects.drpdwnNewDomain3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnNewDomain3);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnNewDomain3, 1,
					FindLawOrderToolPageObjects.drpdwnCustOpe);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustOpe);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnCustOpe, 1,
					FindLawOrderToolPageObjects.btnStrProfile);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnStrProfile);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnStrProfile,
					FindLawOrderToolPageObjects.txtAttrName2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtAttrName2);
			String Attrname = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtAttrName2, Attrname,
					FindLawOrderToolPageObjects.txtPriPracarea);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtPriPracarea);
			String Prac_area = ExcelUtils.getDataByColumnName("COB", "Prac_Area1");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtPriPracarea, Prac_area,
					FindLawOrderToolPageObjects.drpdwnPracarea);
			
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnPracarea);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnPracarea,
					FindLawOrderToolPageObjects.drpdwnFLPractiseArea1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnFLPractiseArea1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnFLPractiseArea1,
					FindLawOrderToolPageObjects.txtFndLwPracarea);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFndLwPracarea);
			String Prac_area1 = ExcelUtils.getDataByColumnName("COB", "Prac_Area1");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFndLwPracarea, Prac_area1,
					FindLawOrderToolPageObjects.drpdwnFirmAccept1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnFirmAccept1);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnFirmAccept1, 1,
					FindLawOrderToolPageObjects.drpdwnFirmOffr);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnFirmOffr);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnFirmOffr, 1,
					FindLawOrderToolPageObjects.txteml1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txteml1);
			String eml = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txteml1, eml,
					FindLawOrderToolPageObjects.buttonExpand4);
			//for now commenting bec of the test data
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.buttonExpand4);
			elementHandler.clickElement(FindLawOrderToolPageObjects.buttonExpand4,
					FindLawOrderToolPageObjects.btnsubexpand);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnsubexpand);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnsubexpand,
					FindLawOrderToolPageObjects.drpdwnCustElect);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustElect);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnCustElect, 1,
					FindLawOrderToolPageObjects.btnPPC3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnPPC3);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnPPC3,
					FindLawOrderToolPageObjects.drpdwnClient4);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnClient4);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnClient4, 1,
					FindLawOrderToolPageObjects.btnCusInp4);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnCusInp4);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnCusInp4,
					FindLawOrderToolPageObjects.txtCust5);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtCust5);
			elementHandler.clickElement(FindLawOrderToolPageObjects.txtCust5,
					FindLawOrderToolPageObjects.txtCustmkt);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtCustmkt);
			String mrkt1 = ExcelUtils.getDataByColumnName("COB", "Primary_area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtCustmkt, mrkt1,
					FindLawOrderToolPageObjects.txtGeoStrat);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtGeoStrat);
			String geo_stat = ExcelUtils.getDataByColumnName("COB", "Strategy");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtGeoStrat, geo_stat,
					FindLawOrderToolPageObjects.btnFirmsite3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnFirmsite3);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnFirmsite3,
					FindLawOrderToolPageObjects.drpdwnDomain);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnDomain);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnDomain, 1,
					FindLawOrderToolPageObjects.drpdwnproMang);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnproMang);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnproMang, 1,
					FindLawOrderToolPageObjects.btnStrtprofile);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnStrtprofile);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnStrtprofile,
					FindLawOrderToolPageObjects.drpdwnHablaEs);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnHablaEs);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnHablaEs, 1,
					FindLawOrderToolPageObjects.txtAttorney5);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtAttorney5);
			String attrn = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtAttorney5, attrn,
					FindLawOrderToolPageObjects.drpdwnFrmAttorney);
			
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnFrmAttorney);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnFrmAttorney, 1,
					FindLawOrderToolPageObjects.txtPriarea5);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtPriarea5);
			String area4 = ExcelUtils.getDataByColumnName("COB", "Primary_area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtPriarea5, area4,
					FindLawOrderToolPageObjects.drpdwnPracarea3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnPracarea3);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnPracarea3,
					FindLawOrderToolPageObjects.drpdwnFLPractiseArea1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnFLPractiseArea1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnFLPractiseArea1,
					FindLawOrderToolPageObjects.drpdwnFrmAccept);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnFrmAccept);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnFrmAccept, 1,
					FindLawOrderToolPageObjects.drpdwnFrmoffr);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnFrmoffr);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnFrmoffr, 1,
					FindLawOrderToolPageObjects.txtEmail6);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtEmail6);
			String Email6 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtEmail6, Email6,
					FindLawOrderToolPageObjects.drpdwnContentFrm);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnContentFrm);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnContentFrm, 1,
					FindLawOrderToolPageObjects.drpdwnContentFrm1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnContentFrm1);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnContentFrm1, 1,
					FindLawOrderToolPageObjects.drpdwnStateMetro);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnStateMetro);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnStateMetro, 1,
					FindLawOrderToolPageObjects.drpdwnPA);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnPA);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnPA, 1,
					FindLawOrderToolPageObjects.buttonExpand5);
			//till here
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.buttonExpand5);
			elementHandler.clickElement(FindLawOrderToolPageObjects.buttonExpand5,
					FindLawOrderToolPageObjects.drpdwnSpanish);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSpanish);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSpanish, 1,
					FindLawOrderToolPageObjects.drpdwnDesiredPrac);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnDesiredPrac);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnDesiredPrac,
					FindLawOrderToolPageObjects.drpdwnFLPractiseArea1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnFLPractiseArea1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnFLPractiseArea1,
					FindLawOrderToolPageObjects.txtChatTrans);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtChatTrans);
			String Chat = ExcelUtils.getDataByColumnName("COB", "Chat_Trans");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtChatTrans, Chat,
					FindLawOrderToolPageObjects.drpdwnLawFirm);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnLawFirm);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnLawFirm, 1,
					FindLawOrderToolPageObjects.drpdwnCustChat);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustChat);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnCustChat, 1,
					FindLawOrderToolPageObjects.btnSave);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(5000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(5000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String Status5=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status5.contains("COB Completed"));
			break;
	
/*
 * This case is for IMR product:42124052			
 */
		case 6:
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(50000);
			actionHandler.waitForSomeTime(50000);
			elementHandler.waitForXpathToLoad("//ul/li[3]/a");
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFmCnctEmail);
			String Cont_mail = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFmCnctEmail, Cont_mail,
					FindLawOrderToolPageObjects.txtFmCnctPhone);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFmCnctPhone);
			String Phone_val = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFmCnctPhone, Phone_val,
					FindLawOrderToolPageObjects.drpdwnLegalContent1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnLegalContent1);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnLegalContent1, 1,
					FindLawOrderToolPageObjects.txtinputs.get(0));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(0));
			String inpt1 = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(0), inpt1,
					FindLawOrderToolPageObjects.txtinputs.get(1));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(1));
			String inpt2 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(1), inpt2,
					FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal2,FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1,FindLawOrderToolPageObjects.txtaddnlgeographic);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtaddnlgeographic);
			String geoinput = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtaddnlgeographic, geoinput,
					FindLawOrderToolPageObjects.txtinputs.get(2));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(2));
			String inpt3 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(2), inpt3,
					FindLawOrderToolPageObjects.tabproductQuestion);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.tabproductQuestion);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion,FindLawOrderToolPageObjects.btnExpand.get(0));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpand.get(0));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand.get(0),FindLawOrderToolPageObjects.btnExpand1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpand1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(3));
			String inpt4 = ExcelUtils.getDataByColumnName("COB", "Input");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(3), inpt4,
					FindLawOrderToolPageObjects.txtinputs.get(4));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(4));
			String inpt5 = ExcelUtils.getDataByColumnName("COB", "Input");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(4), inpt5,
					FindLawOrderToolPageObjects.txtinputs2.get(5));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(5));
			String inpt6 = ExcelUtils.getDataByColumnName("COB", "Input");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(5), inpt6,
					FindLawOrderToolPageObjects.drpdwnPractisearea);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnPractisearea);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnPractisearea);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnPractisevalues.get(2));
			//elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnPractisevalues.get(0), 2,
					//FindLawOrderToolPageObjects.drpdwnSelect.get(1));
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnPractisevalues.get(2));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(1));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(1), 1,
					FindLawOrderToolPageObjects.drpdwnSelect.get(2));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(2));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(2), 1,
					FindLawOrderToolPageObjects.drpdwnSelect.get(3));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(3));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(3), 1,
					FindLawOrderToolPageObjects.txtinputs2.get(6));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(6));
			String inpt7 = ExcelUtils.getDataByColumnName("COB", "Input");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(6), inpt7,
					FindLawOrderToolPageObjects.btnBlog);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnBlog);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnBlog);
					
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(4));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(4), 2,
					FindLawOrderToolPageObjects.btnLocal);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnLocal);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnLocal);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(5));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(5), 2,
					FindLawOrderToolPageObjects.drpdwnSelect.get(6));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(6));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(6), 2,
					FindLawOrderToolPageObjects.drpdwnSelect.get(7));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(7));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(7), 2,
					FindLawOrderToolPageObjects.btnSave);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(8000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(8000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(4000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(4000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String Status6=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status6.contains("COB Completed"));
			break;
			
/*
 * This case is to validate COB Error message for EB product: 30572896			
 */
		case 7:
			elementHandler.waitForXpathToLoad("//cob/div/div[3]/h3");
			boolean Msg= elementHandler.isElementDisplayed(FindLawOrderToolPageObjects.lblMessage);
			if(Msg == true){
				AssertionHandler.verifyTrue(Msg, "Informstional Message not displayed");
				String Message= elementHandler.getText(FindLawOrderToolPageObjects.lblMessage);
				TestNGCustomReporter.log(logger, "Informational Message displayed as:" +Message +ReportBuilder.takesScreenshot());
			}
			else {
				TestNGCustomReporter.log(logger, "COB Process required for the current Quote");
			}
			break;
			
/*
 * This case is for EB Products:41870119,42089521,42089522 and also for OTC products:42089523,41870119		
 */
		case 8:
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(20000);
			elementHandler.waitForXpathToLoad("//ul/li[3]/a");
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(1));
			String Cont_mail1 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(1), Cont_mail1,
					FindLawOrderToolPageObjects.txtinputs2.get(2));

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(2));
			String Phone_val1 = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(2), Phone_val1,
					FindLawOrderToolPageObjects.txtinputs.get(0));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(0));
			String url_1 = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(0), url_1,
					FindLawOrderToolPageObjects.txtinputs.get(1));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(1));
			String addinfo = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(1), addinfo,
					FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal2, 
					FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1, 
					FindLawOrderToolPageObjects.txtinputs2.get(3));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(3));
			String geo1 = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(3), geo1,
					FindLawOrderToolPageObjects.txtinputs.get(2));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(2));
			String client = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(2), client,
					FindLawOrderToolPageObjects.txtinputs.get(3));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(3));
			String spl = ExcelUtils.getDataByColumnName("COB", "special_Inst");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(3), spl,
					FindLawOrderToolPageObjects.btnSave);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(8000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(8000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(4000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(4000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String Status7=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status7.contains("COB Completed"));
			break;
		
/*
 * This case is for EB products:41874867,41819164 and Blog set up product:41819163			
 */
		case 9:
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(60000);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabgeneralQues);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(1));
			String email0 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(1), email0,
					FindLawOrderToolPageObjects.drpdwnLegalContent1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnLegalContent1);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnLegalContent1, 1, 
					FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal2, 
					FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1, 
					FindLawOrderToolPageObjects.txtinputs2.get(3));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(3));
			String geo2 = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(3), geo2,
					FindLawOrderToolPageObjects.txtinputs.get(2));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(2));
			String client1 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(2), client1,
					FindLawOrderToolPageObjects.tabproductQuestion);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.tabproductQuestion);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpnd);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpnd);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(4));
			String cus = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(4), cus,
					FindLawOrderToolPageObjects.btnExpnd1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpnd1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpnd1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnBlog);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnBlog);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(1));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(1), 1, 
					FindLawOrderToolPageObjects.drpdwnSelect.get(2));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(2));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(2), 1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(3));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(3), 1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(4));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(4), 1
					);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(5));
			String cus1 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(5), cus1,
					FindLawOrderToolPageObjects.btnSave);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(15000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(15000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(10000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String Status8=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status8.contains("COB Completed"));
			break;
	
/*
 * This case is for Blog products:41819162,41817449 to the cart			
 */
		case 10:
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(50000);
			//elementHandler.waitForXpathToLoad("//ul/li[3]/a");
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabgeneralQues);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(1));
			String email11 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(1), email11,
					FindLawOrderToolPageObjects.drpdwnLegalContent1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnLegalContent1);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnLegalContent1, 1, 
					FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal2, 
					FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1, 
					FindLawOrderToolPageObjects.txtinputs2.get(3));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(3));
			String geo4 = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(3), geo4,
					FindLawOrderToolPageObjects.txtinputs.get(2));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(2));
			String client2 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(2), client2,
					FindLawOrderToolPageObjects.tabproductQuestion);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.tabproductQuestion);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpnd);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpnd);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(4));
			String cus2 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(4), cus2,
					FindLawOrderToolPageObjects.btnSave);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(15000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(15000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(10000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String Status9=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status9.contains("COB Completed"));
			break;
			
/*
 * This case is for Call Tracking product: 40586465			
 */
		case 11:
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(50000);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabgeneralQues);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(0));
			String urlval = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(0), urlval);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(1));
			String urlval1 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(1), urlval1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			actionHandler.waitForSomeTime(1000);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			actionHandler.waitForSomeTime(1000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(0));
			String addinfo1 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(0), addinfo1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(2));
			String Client4 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(2), Client4);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFirstName);
			String FrstName = ExcelUtils.getDataByColumnName("COB", "First_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFirstName, FrstName);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtLastName);
			String lstname = ExcelUtils.getDataByColumnName("COB", "Last_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtLastName, lstname);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtEmailaddr);
			String Email0 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtEmailaddr, Email0,FindLawOrderToolPageObjects.tabproductQuestion);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.tabproductQuestion);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion);
			actionHandler.waitForSomeTime(1000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpand.get(0));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand.get(0));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(0));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(0), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(1));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(1), 1,FindLawOrderToolPageObjects.btnSave );
			
			/*javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(0));
			String phoneno = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(0), phoneno,FindLawOrderToolPageObjects.btnSave);*/
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(15000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(15000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(10000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String Status0=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status0.contains("COB Completed"));
			break;
		
/*
 * This case is for Animation Essential product: 41323281			
 */
		case 12:
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(50000);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabgeneralQues);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(1));
			String emailv = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(1), emailv);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(2));
			String phone2 = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(2), phone2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(0));
			String url0 = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(0), url0);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(1));
			String addinfop = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(1), addinfop);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(3));
			String geop = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(3), geop);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(2));
			String clintv = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(2), clintv,FindLawOrderToolPageObjects.btnSave);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(15000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(15000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(10000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String Status_1=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status_1.contains("COB Completed"));
			break;
			
/*
 * This case is for Mobile Essential product:41325003 			
 */
		case 13:
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(50000);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabgeneralQues);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(1));
			String emailp = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(1), emailp);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(2));
			String phone9 = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(2), phone9);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(0));
			String urlt = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(0), urlt);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(1));
			String addinfoo = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(1), addinfoo);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(3));
			String geoi = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(3), geoi);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(2));
			String sucess = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(2), sucess,FindLawOrderToolPageObjects.tabproductQuestion);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.tabproductQuestion);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpand.get(0));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand.get(0));
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(0));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(0), 2, FindLawOrderToolPageObjects.btnSave);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(15000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(15000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(10000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String Status_2=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status_2.contains("COB Completed"));
			break;
		
/*
 * This case is for CPQ products:41091534,304050		
 */
		case 14:
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(50000);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabgeneralQues);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(0));
			String urli = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(0), urli);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(1));
			String schedule = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(1), schedule);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(0));
			String geograph = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(0), geograph);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(2));
			String success = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(2), success,FindLawOrderToolPageObjects.btnSave);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(15000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(15000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(10000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String Status_3=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status_3.contains("COB Completed"));
			break;
		
/*
 * This case is for all Firmsite and IM products			
 */
		case 15:
			actionHandler.waitForSomeTime(80000);
			actionHandler.waitForSomeTime(80000);
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(50000);
			actionHandler.waitForSomeTime(50000);
			
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabgeneralQues);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(1));
			String emailf = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(1), emailf);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(2));
			String phoneg = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(2), phoneg);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(0));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(0), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(1));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(1), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(0));
			String urlj = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(0), urlj);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(1));
			String addfo = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(1), addfo);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(3));
			String addso = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(3), addso);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(2));
			String clientr = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(2), clientr);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFirstName);
			String First_Name1 = ExcelUtils.getDataByColumnName("COB", "First_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFirstName, First_Name1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtLastName);
			String last_Name = ExcelUtils.getDataByColumnName("COB", "Last_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtLastName, last_Name);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtEmailaddr);
			String Email_Add1 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtEmailaddr, Email_Add1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(7));
			String addsof = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(7), addsof,FindLawOrderToolPageObjects.tabproductQuestion);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.tabproductQuestion);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandbtn1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandbtn1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandsubbtn);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn);
			String title0= elementHandler.getText(FindLawOrderToolPageObjects.Expandsubbtntitle);
			System.out.println("title:" +title0);
			boolean value_title= title0.contains("41056103 - Starter Profile");
			if (value_title ==true) {
				System.out.println("started with 1st product");
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(3));
			String Attrny = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(3), Attrny);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(9));
			String Primary = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(9), Primary);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1));
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1));
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(10));
			String Area = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(10), Area);
					
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(6));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(6), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(7));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(7), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(11));
			String Email_add = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(11), Email_add);
			}
			
			else {
				System.out.println("started for wrong prod");
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(6));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(6), 1 );
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(7));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(7), 2 );
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(8));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(8), 1 );
				actionHandler.waitForSomeTime(2000);
			}
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandsubbtn1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn1);
			String title_2= elementHandler.getText(FindLawOrderToolPageObjects.Expandsubbtn1title);
			boolean value_2= title_2.contains("40475630 - Firmsite I");
			if (value_2 == true){
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(8));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(8), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(9));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(9), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(10));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(10), 1 );
			actionHandler.waitForSomeTime(2000);
			}
			
			else {
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(4));
				String Attrny = ExcelUtils.getDataByColumnName("COB", "Client");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(4), Attrny);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(10));
				String Primary = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(10), Primary);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1));
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1));
				actionHandler.waitForSomeTime(2000);
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(11));
				String Area = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(11), Area);
						
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(9));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(9), 1 );
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(10));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(10), 1 );
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(12));
				String Email_add = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(12), Email_add);
					
			}
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandbtn2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandbtn2);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandsubbtn2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn2);
			
			String title5= elementHandler.getText(FindLawOrderToolPageObjects.Expandsubbtn2);
			boolean value2= title5.contains("41056103 - Starter Profile");
			if (value2 == true) {
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(7));
			String Attrny_Name = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(7), Attrny_Name);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(15));
			String Prac_area2 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(15), Prac_area2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(2));
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(2));
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(16));
			String Prac_area3 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(16), Prac_area3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(11));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(11), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(12));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(12), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(16));
			String Prac_area4 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(16), Prac_area4);
			}
			else {
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(11));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(11), 1 );
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(12));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(12), 2 );
				actionHandler.waitForSomeTime(2000);
			}
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandsubbtn3);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn3);
			
			String title6= elementHandler.getText(FindLawOrderToolPageObjects.Expandsubbtn3);
			boolean value3= title6.contains("40588593 - Firmsite III");
			if(value3 == true) {
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(13));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(13), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(14));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(14), 1 );
			actionHandler.waitForSomeTime(2000);
			}
			else {
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(9));
				String Attrny_Name = ExcelUtils.getDataByColumnName("COB", "Client");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(9), Attrny_Name);

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(14));
				String Prac_area2 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(14), Prac_area2);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(2));
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(2));
				actionHandler.waitForSomeTime(2000);
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(15));
				String Prac_area3 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(15), Prac_area3);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(13));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(13), 1 );
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(14));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(14), 1 );
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(16));
				String Prac_area4 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(16), Prac_area4);
			}
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandbtn3);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandbtn3);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandsubbtn4);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn4);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(12));
			String Attrny_Name1 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(12), Attrny_Name1);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(18));
			String Prac_area5 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(18), Prac_area5);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(3));
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(3));
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(19));
			String Prac_area6 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(19), Prac_area6);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(15));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(15), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(16));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(16), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(17));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(17), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(21));
			String emailu = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(21), emailu);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(15));
			String ppc = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(15), ppc);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(16));
			String campaign = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(16), campaign);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnPPC);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnPPC);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(17));
			String subarea = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(17), subarea);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(18));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(18), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(24));
			String contact_Phone = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(24), contact_Phone);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(25));
			String Instruction = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(25), Instruction);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnBlog);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnBlog);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(19));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(19), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(26));
			String ExistBlog = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(26), ExistBlog);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnLocal);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnLocal);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(20));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(20), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(21));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(21), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(22));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(22), 1 );
			actionHandler.waitForSomeTime(2000);
			
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnCusInpt);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnCusInpt);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(19));
			String Clieant7 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(19), Clieant7);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(20));
			String Practise = ExcelUtils.getDataByColumnName("COB", "Customer");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(20), Practise);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(27));
			String marketng = ExcelUtils.getDataByColumnName("COB", "Customer");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(27), marketng);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(21));
			String geography = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(21), geography);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandbtn4);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandbtn4);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandsubbtn5);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn5);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(22));
			String Attrny_Name4 = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(22), Attrny_Name4);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(29));
			String Prac_area7 = ExcelUtils.getDataByColumnName("COB", "Prac_Area1");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(29), Prac_area7);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(4));
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(4));
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(30));
			String pracarea = ExcelUtils.getDataByColumnName("COB", "Prac_Area1");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(30), pracarea);
			
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(23));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(23), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(24));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(24), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(25));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(25), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(32));
			String Emailadd = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(32), Emailadd);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(25));
			String ClientPPc = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(25), ClientPPc);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(26));
			String Campaign = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(26), Campaign);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnPPClst.get(1));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnPPClst.get(1));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(27));
			String subprac = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(27), subprac);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(26));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(26), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(35));
			String Cont_Phone = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(35), Cont_Phone);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(36));
			String Spec_Inst = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(36), Spec_Inst);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnBloglst.get(1));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnBloglst.get(1));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(27));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(27), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(37));
			String blog_url = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(37), blog_url);
			
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnLocalst.get(1));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnLocalst.get(1));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(28));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(28), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(29));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(29), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(30));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(30), 1 );
			actionHandler.waitForSomeTime(2000);
			
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnCusInptlst.get(1));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnCusInptlst.get(1));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(29));
			String Firmsite = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(29), Firmsite);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(30));
			String Practise1 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(30), Practise1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(38));
			String adverst = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(38), adverst);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(31));
			String geogra = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(31), geogra);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandbtn5);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandbtn5);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandsubbtn6);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn6);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(31));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(31), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnBloglst.get(2));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnBloglst.get(2));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(32));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(32), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(39));
			String url_cus = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(39), url_cus);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnLocalst.get(2));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnLocalst.get(2));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(33));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(33), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(34));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(34), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(35));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(35), 1 );
			actionHandler.waitForSomeTime(2000);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnCusInptlst.get(2));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnCusInptlst.get(2));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(33));
			String Diff_Prac = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(33), Diff_Prac);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(40));
			String advert = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(40), advert);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(34));
			String Attrny_Name5 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(34), Attrny_Name5);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnFirmsitelst.get(0));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnFirmsitelst.get(0));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(36));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(36), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(37));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(37), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnStarteProfile);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnStarteProfile);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(35));
			String Attr_name = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(35), Attr_name);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(43));
			String Seo = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(43), Seo);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(5));
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(5));
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(44));
			String category = ExcelUtils.getDataByColumnName("COB", "Customer");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(44), category);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(38));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(38), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(39));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(39), 1 );
			actionHandler.waitForSomeTime(2000);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(45));
			String Email_Addr = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(45), Email_Addr);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandbtn6);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandbtn6);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandsubbtn7);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn7);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(40));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(40), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnBloglst.get(3));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnBloglst.get(3));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(41));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(41), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(46));
			String Exist_Blog = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(46), Exist_Blog);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnLocalst.get(3));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnLocalst.get(3));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(42));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(42), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(43));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(43), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(44));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(44), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnCusInptlst.get(3));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnCusInptlst.get(3));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(39));
			String Customer1 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(39), Customer1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(47));
			String marketing = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(47), marketing);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(40));
			String Strategy = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(40), Strategy);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnFirmsitelst.get(1));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnFirmsitelst.get(1));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(45));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(45), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(46));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(46), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnStarteProfilelst.get(1));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnStarteProfilelst.get(1));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(41));
			String Attrny_Name8 = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(41), Attrny_Name8);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(50));
			String Prac_area0 = ExcelUtils.getDataByColumnName("COB", "Prac_Area1");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(50), Prac_area0);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(6));
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(6));
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(51));
			String Prac_area9 = ExcelUtils.getDataByColumnName("COB", "Prac_Area1");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(51), Prac_area9);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(47));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(47), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(48));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(48), 1 );
			actionHandler.waitForSomeTime(2000);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(52));
			String Email_adder = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(52), Email_adder);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandbtn7);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandbtn7);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandsubbtn8);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn8);
			String title7= elementHandler.getText(FindLawOrderToolPageObjects.Expandsubbtn8);
			boolean value4= title7.contains("40592656 - Firmsite II");
			if (value4 == true) {
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(49));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(49), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(50));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(50), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(51));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(51), 1 );
			actionHandler.waitForSomeTime(2000);
			}
			else {
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(44));
				String Attrny_Name0 = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(44), Attrny_Name0);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(54));
				String Primary1 = ExcelUtils.getDataByColumnName("COB", "Prac_Area1");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(54), Primary1);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(7));
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(7));
				actionHandler.waitForSomeTime(2000);
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(55));
				String Emailladd3 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(55), Emailladd3);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(49));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(49), 1 );
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(50));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(50), 1 );
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(56));
				String Straegy = ExcelUtils.getDataByColumnName("COB", "Prac_Area1");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(56), Straegy);
			}
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandsubbtn9);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn9);
			String title8= elementHandler.getText(FindLawOrderToolPageObjects.Expandsubbtn9);
			boolean value5= title8.contains("41056103 - Starter Profile");
			if (value5 == true) {
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(45));
			String Attrny_Name0 = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(45), Attrny_Name0);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(55));
			String Primary1 = ExcelUtils.getDataByColumnName("COB", "Prac_Area1");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(55), Primary1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(7));
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(7));
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(56));
			String Emailladd3 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(56), Emailladd3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(52));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(52), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(53));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(53), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(57));
			String Straegy = ExcelUtils.getDataByColumnName("COB", "Prac_Area1");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(57), Straegy);
			}
			else {
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(51));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(51), 1 );
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(52));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(52), 2 );
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(53));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(53), 1 );
				actionHandler.waitForSomeTime(2000);	
			}
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandbtn8);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandbtn8);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandsubbtn0);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn0);
			String title3= elementHandler.getText(FindLawOrderToolPageObjects.Expandsubbtn0);
			actionHandler.waitForSomeTime(2000);
			boolean value6= title3.contains("40588594 - Firmsite IV");
			if (value6 == true) {
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(54));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(54), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(55));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(55), 1 );
			actionHandler.waitForSomeTime(2000);
			}
			else {
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(48));
				String First_name = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(48), First_name);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(59));
				String Seo1 = ExcelUtils.getDataByColumnName("COB", "Customer");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(59), Seo1);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(8));
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(8));
				actionHandler.waitForSomeTime(2000);
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(60));
				String Prac_area09 = ExcelUtils.getDataByColumnName("COB", "Customer");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(60), Prac_area09);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(54));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(54), 1 );
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(55));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(55), 1 );
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(61));
				String email_add = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(61),email_add);
			}
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandsubbtn01);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn01);
			String title4= elementHandler.getText(FindLawOrderToolPageObjects.Expandsubbtn01);
			actionHandler.waitForSomeTime(2000);
			boolean value7= title4.contains("41056103 - Starter Profile");
			if (value7 == true) {
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(50));
			String First_name = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(50), First_name);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(59));
			String Seo1 = ExcelUtils.getDataByColumnName("COB", "Customer");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(59), Seo1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(8));
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(8));
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(60));
			String Prac_area09 = ExcelUtils.getDataByColumnName("COB", "Customer");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(60), Prac_area09);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(56));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(56), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(57));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(57), 1 );
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(61));
			String email_add = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(61), email_add,FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(5000);
			}
			else {
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(56));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(56), 1 );
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(57));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(57), 2,FindLawOrderToolPageObjects.btnSave );
				actionHandler.waitForSomeTime(5000);	
			}
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(15000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(15000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(20000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String Status_4=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status_4.contains("COB Completed"));
			break;
			
/*
 * for SAP scenario			
 */
		case 16:
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(50000);
			actionHandler.waitForSomeTime(50000);
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabgeneralQues);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(1));
			String email_1 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(1), email_1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(2));
			String phone_1 = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(2), phone_1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(0));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(0), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(1));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(1), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(0));
			String url_2 = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(0), url_2);
			//driver.findElement(By.xpath("//tab[3]//div[11]//ul[@id='notSubmitted']/li[2]/textarea")).sendKeys("TEST");
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(1));
			String Add_Info = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(1), Add_Info);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(3));
			String Geo_1 = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(3), Geo_1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(2));
			String Client_1 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(2), Client_1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(4));
			String FN = ExcelUtils.getDataByColumnName("COB", "First_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(4), FN);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(5));
			String LN = ExcelUtils.getDataByColumnName("COB", "Last_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(5), LN);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(6));
			String Eml = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(6), Eml
					);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.tabproductQuestion);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion);
			
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandbtn1);
			actionHandler.waitForSomeTime(2000);
			
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(3));
			String Geo_3 = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(3), Geo_3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(8));
			String Attrny_Name = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(8), Attrny_Name
					);
			
			driver.findElement(By.xpath("//ul[@id='notSubmitted']/li//div[1]/span")).click();
			/*javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1));
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1));*/
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(9));
			String Prac_Area = ExcelUtils.getDataByColumnName("COB", "Prac_Area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(9), Prac_Area
					);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(6));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(6), 1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(7));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(7), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(10));
			String Email_2 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(10), Email_2
					);
			
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(5));
			String Geo_2 = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(5), Geo_2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(8));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(8), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(9));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(9), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandbtn2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandbtn2);
			actionHandler.waitForSomeTime(2000);
			
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn2);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(10));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(10), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnBlog);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnBlog);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(11));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(11), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(11));
			String URL_3 = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(11), URL_3
					);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnLocal);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnLocal);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(12));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(12), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(13));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(13), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(14));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(14), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnCusInpt);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnCusInpt);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(9));
			String Practise2 = ExcelUtils.getDataByColumnName("COB", "Prac_Area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(9), Practise2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(12));
			String Marketing = ExcelUtils.getDataByColumnName("COB", "Prac_Area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(12), Marketing
					);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(10));
			String Strategy_2 = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(10), Strategy_2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnFirmsite);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnFirmsite);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(15));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(15), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(16));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(16), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnStarteProfile);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnStarteProfile);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(11));
			String Attorney_name = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(11), Attorney_name);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(15));
			String primary_area = ExcelUtils.getDataByColumnName("COB", "Primary_area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(15), primary_area
					);
			
			driver.findElement(By.xpath("//ul[@id='notSubmitted']/li//div[1]/span")).click();
			/*javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(2));
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(2));*/
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(16));
			String Prac_area2 = ExcelUtils.getDataByColumnName("COB", "Prac_Area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(16), Prac_area2
					);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(17));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(17), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(18));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(18), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(17));
			String Email9 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(17), Email9
					);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(15000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(15000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(10000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String Status_5=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status_5.contains("COB Completed"));
			break;

/*
 * This case is for Engagement builder product: 41819164
 */
		case 17:
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(80000);
			actionHandler.waitForSomeTime(20000);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabgeneralQues);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(1));
			String email_2 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(1), email_2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(0));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(0), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(3));
			String Geo0 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(3), Geo0);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(2));
			String Client6 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(2), Client6,
					FindLawOrderToolPageObjects.tabproductQuestion);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.tabproductQuestion);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion);
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand.get(0));
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnBlog);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(1));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(1), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(2));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(2), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(3));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(3), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(15000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(15000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(10000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String Status_6=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status_6.contains("COB Completed"));
			//driver.close();
			//windowHandler.switchToLatestWindow(QuotePageObjects.btnCOB1);
			break;
			
/*
 * This case is for SAP scenario			
 */
		case 18:
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(20000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFmCnctEmail);
			String Email30 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFmCnctEmail, Email30,
					FindLawOrderToolPageObjects.txtFmCnctPhone);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFmCnctPhone);
			String Phone67 = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFmCnctPhone, Phone67,
					FindLawOrderToolPageObjects.drpdwnPractiseArea);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnPractiseArea);
			actionHandler.waitForSomeTime(2000);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnPractiseArea, 2,
					FindLawOrderToolPageObjects.drpdwnLegalContent2);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnLegalContent2);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnLegalContent2, 2,
					FindLawOrderToolPageObjects.txtURL);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtURL);
			String URL4 = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtURL, URL4,
					FindLawOrderToolPageObjects.txtadditionalinfo);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtadditionalinfo);
			String Additional0 = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtadditionalinfo, Additional0,
					FindLawOrderToolPageObjects.drpdwnCustomerGoal);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal,
					FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1,
					FindLawOrderToolPageObjects.txtGeographic);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtGeographic);
			String Geographic0 = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtGeographic, Geographic0,
					FindLawOrderToolPageObjects.txtClient);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtClient);
			String Client0 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtClient, Client0,
					FindLawOrderToolPageObjects.tabproductQuestion);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.tabproductQuestion);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion,
					FindLawOrderToolPageObjects.btnExpand.get(0));

			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand.get(0), FindLawOrderToolPageObjects.btnExpand1);
			actionHandler.waitForSomeTime(1000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand1,
					FindLawOrderToolPageObjects.drpdwnNewDomain);
			actionHandler.waitForSomeTime(2000);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnNewDomain);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnNewDomain, 1,
					FindLawOrderToolPageObjects.drpdwnStrategy);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnStrategy);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnStrategy, 1,
					FindLawOrderToolPageObjects.txtThoughtsCustomer);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtThoughtsCustomer);
			String Customer0 = ExcelUtils.getDataByColumnName("COB", "Customer");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtThoughtsCustomer, Customer0,
					FindLawOrderToolPageObjects.btnExpand2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand2,
					FindLawOrderToolPageObjects.txtAttorneyName);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtAttorneyName);
			String Attorney_Name0 = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtAttorneyName, Attorney_Name0,
					FindLawOrderToolPageObjects.txtPrimaryArea);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtPrimaryArea);
			String Primary_area0 = ExcelUtils.getDataByColumnName("COB", "Primary_area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtPrimaryArea, Primary_area0,
					FindLawOrderToolPageObjects.drpdwnFndlwpractiseArea);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnFndlwpractiseArea);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnFndlwpractiseArea,
					FindLawOrderToolPageObjects.drpdwnpractiseAreavalue);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnpractiseAreavalue,
					FindLawOrderToolPageObjects.txtFndlwpractiseArea1);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFndlwpractiseArea1);
			String Prac_Area10 = ExcelUtils.getDataByColumnName("COB", "Prac_Area1");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFndlwpractiseArea1, Prac_Area10,
					FindLawOrderToolPageObjects.drpdwnfirmAccept);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnfirmAccept);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnfirmAccept, 1,
					FindLawOrderToolPageObjects.drpdwnfirmOffers);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnfirmOffers);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnfirmOffers, 1,
					FindLawOrderToolPageObjects.txtEmailAddress);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtEmailAddress);
			String Email_Add0 = ExcelUtils.getDataByColumnName("COB", "Email_Add");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtEmailAddress, Email_Add0,
					FindLawOrderToolPageObjects.btnSave);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(5000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(5000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String Status_7=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status_7.contains("COB Completed"));
			break;
		
/*
 * This case is for IMS or IME products:41777796,41822784			
 */
		case 19:			
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(50000);
			actionHandler.waitForSomeTime(50000);
			actionHandler.waitForSomeTime(20000);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabgeneralQues);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(1));
			String email_3 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(1), email_3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(2));
			String Phone_1 = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(2), Phone_1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(0));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(0), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(3));
			String First_Name2 = ExcelUtils.getDataByColumnName("COB", "First_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(3), First_Name2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(4));
			String Last_Name2 = ExcelUtils.getDataByColumnName("COB", "Last_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(4), Last_Name2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(5));
			String email_4 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(5), email_4);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(6));
			String Request = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(6), Request);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.tabproductQuestion);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandbtn1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandbtn1);
			actionHandler.waitForSomeTime(2000);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandsubbtn);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(0));
			String Attorny_name = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(0), Attorny_name);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(8));
			String Prac_area3 = ExcelUtils.getDataByColumnName("COB", "Prac_Area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(8), Prac_area3);
				
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal2,
					FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1
					);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(9));
			String Prac_area4 = ExcelUtils.getDataByColumnName("COB", "Prac_Area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(9), Prac_area4);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(1));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(1), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(2));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(2), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(3));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(3), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(11));
			String Email_9 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(11), Email_9);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(3));
			String Client_2 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(3), Client_2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(4));
			String Campaign1 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(4), Campaign1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnPPC);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnPPC);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(5));
			String Sub_Prac = ExcelUtils.getDataByColumnName("COB", "Prac_Area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(5), Sub_Prac);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(4));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(4), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(14));
			String Phone_No = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(14), Phone_No);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(15));
			String Special_Inst = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(15), Special_Inst);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnBlog);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnBlog);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(5));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(5), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(16));
			String URL_4 = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(16), URL_4);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnLocal);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnLocal);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(6));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(6), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(7));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(7), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(8));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(8), 2);
			
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnCusInpt);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnCusInpt);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(7));
			String Client_0 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(7), Client_0);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(8));
			String Sub_Prac1 = ExcelUtils.getDataByColumnName("COB", "Prac_Area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(8), Sub_Prac1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(17));
			String marketng1 = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(17), marketng1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(9));
			String Geostart = ExcelUtils.getDataByColumnName("COB", "Prac_Area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(9), Geostart);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandbtn2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandbtn2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandsubbtn2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(10));
			String Attorny_name1 = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(10), Attorny_name1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(19));
			String Prac_area8 = ExcelUtils.getDataByColumnName("COB", "Prac_Area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(19), Prac_area8);
				
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1));
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1),
					FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1
					);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(20));
			String Prac_area90 = ExcelUtils.getDataByColumnName("COB", "Prac_Area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(20), Prac_area90);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(9));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(9), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(10));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(10), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(11));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(11), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(22));
			String Email_10 = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(22), Email_10);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(13));
			String Client_3 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(13), Client_3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(14));
			String Campaign2 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(14), Campaign2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnPPClst.get(1));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnPPClst.get(1));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(15));
			String Sub_Prac2 = ExcelUtils.getDataByColumnName("COB", "Prac_Area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(15), Sub_Prac2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(12));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(12), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(25));
			String Phone_No1 = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(25), Phone_No1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(26));
			String Special_Inst1 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(26), Special_Inst1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnBloglst.get(1));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnBloglst.get(1));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(13));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(13), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(27));
			String URL_5 = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(27), URL_5);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnLocalst.get(1));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnLocalst.get(1));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(14));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(14), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(15));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(15), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(16));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(16), 2);
			
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnCusInptlst.get(1));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnCusInptlst.get(1));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(17));
			String Client_4 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(17), Client_4);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(18));
			String Sub_Prac3 = ExcelUtils.getDataByColumnName("COB", "Prac_Area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(18), Sub_Prac3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(28));
			String marketng2 = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(28), marketng2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(19));
			String Geostart1 = ExcelUtils.getDataByColumnName("COB", "Prac_Area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(19), Geostart1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(5000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(5000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String Status_8=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status_8.contains("COB Completed"));
			driver.close();
			windowHandler.switchToLatestWindow(QuotePageObjects.btnCOB1);
			break;
			
/*
 * This case is for 42089521-FNDLW REPUTATION ADVISOR" product				
 */
		case 20:
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(50000);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabgeneralQues);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(1));
			String emailh = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(1), emailh);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(2));
			String phonep = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(2), phonep);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(0));
			String urlk = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(0), urlk);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(1));
			String addinfok = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(1), addinfok);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(15000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(15000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(10000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String Status_9=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status_9.contains("COB Completed"));
			break;
			
/*
 * 
 * This case is for PPC products: 41876119, 41877783			
 */
		case 21:
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(50000);
			actionHandler.waitForSomeTime(50000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpnd);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpnd);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(0));
			String domain = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(0), domain);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(0));
			String subPracArea = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(0), subPracArea);
			
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpnd1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpnd1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(1));
			String domain1 = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(1), domain1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(1));
			String subPracArea1 = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(1), subPracArea1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(15000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(15000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(10000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String Status_0=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Status_0.contains("COB Completed"));
			break;
		
/*
 * This case is for US PPC product: 30010890			
 */
		case 22:
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(50000);
			actionHandler.waitForSomeTime(50000);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabgeneralQues);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(2));
			String phone0 = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(2), phone0);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(3));
			String prac_area1 = ExcelUtils.getDataByColumnName("COB", "Prac_Area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(3), prac_area1);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(0));
			String url9 = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(0), url9);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(1));
			String info = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(1), info);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal2,
					FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1
					);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(4));
			String addInfo = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(4), addInfo);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(2));
			String client3 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(2), client3);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(5));
			String fstName = ExcelUtils.getDataByColumnName("COB", "First_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(5), fstName);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(6));
			String lstName = ExcelUtils.getDataByColumnName("COB", "Last_Name");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(6), lstName);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(7));
			String emailAdd = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(7), emailAdd);
			
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpnd);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpnd);
			actionHandler.waitForSomeTime(2000);
			
			String title_1= elementHandler.getText(FindLawOrderToolPageObjects.Expandsubbtntitle);
			System.out.println("title:" +title_1);
			boolean value_1= title_1.contains("FindLaw PPC - Call Tracking Number");
			if (value_1 == true) {
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandsubbtn);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(0));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(0), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(1));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(1), 2);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(8));
			String phoneNo = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(8), phoneNo);
			actionHandler.waitForSomeTime(5000);
			}
			else {
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandsubbtn);
				elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn);
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(8));
				String domainPPC = ExcelUtils.getDataByColumnName("COB", "New_Domain");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(8), domainPPC);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(3));
				String subPrac = ExcelUtils.getDataByColumnName("COB", "Prac_Area");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(3), subPrac);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(4));
				String client4 = ExcelUtils.getDataByColumnName("COB", "Client");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(4), client4);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(5));
				String specific = ExcelUtils.getDataByColumnName("COB", "Client");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(5), specific);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(6));
				String note = ExcelUtils.getDataByColumnName("COB", "Geographic");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(6), note);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(7));
				String clientPast = ExcelUtils.getDataByColumnName("COB", "Geographic");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(7), clientPast);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(8));
				String campaign1 = ExcelUtils.getDataByColumnName("COB", "Geographic");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(8), campaign1);
				
			}
			
			String title_3= elementHandler.getText(FindLawOrderToolPageObjects.Expandsubbtn1title);
			System.out.println("title:" +title_3);
			boolean value_3= title_3.contains("FindLaw PPC - Advertisement Costs");
			
			if (value_3 == true) {
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandsubbtn1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn1);
			actionHandler.waitForSomeTime(2000);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(9));
			String domainPPC = ExcelUtils.getDataByColumnName("COB", "New_Domain");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(9), domainPPC);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(3));
			String subPrac = ExcelUtils.getDataByColumnName("COB", "Prac_Area");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(3), subPrac);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(4));
			String client4 = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(4), client4);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(5));
			String specific = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(5), specific);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(6));
			String note = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(6), note);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(7));
			String clientPast = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(7), clientPast);
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(8));
			String campaign1 = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(8), campaign1);
			}
			
			else {
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.Expandsubbtn1);
				elementHandler.clickElement(FindLawOrderToolPageObjects.Expandsubbtn1);
				actionHandler.waitForSomeTime(2000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(0));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(0), 2);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(1));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(1), 2);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(10));
				String phoneNo = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(10), phoneNo);
				actionHandler.waitForSomeTime(5000);
				
			}
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(15000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(15000);

			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(10000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String StatusCOB=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(StatusCOB.contains("COB Completed"));
			break;
			
/*
 * This case is for Firmsite 333C product and selecting the COB button from Proposals tab			
 */
			case 23:
				elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(20000);
			actionHandler.waitForSomeTime(20000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFmCnctEmail);
			String emailg = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFmCnctEmail, emailg,
					FindLawOrderToolPageObjects.txtFmCnctPhone);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtFmCnctPhone);
			String phoneq = ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtFmCnctPhone, phoneq,
					FindLawOrderToolPageObjects.drpdwnPractiseArea);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnPractiseArea);
			actionHandler.waitForSomeTime(2000);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnPractiseArea, 2,
					FindLawOrderToolPageObjects.drpdwnLegalContent2);
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnLegalContent2);
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnLegalContent2, 2,
					FindLawOrderToolPageObjects.txtURL);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtURL);
			String uRL = ExcelUtils.getDataByColumnName("COB", "URL");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtURL, uRL,
					FindLawOrderToolPageObjects.txtadditionalinfo);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtadditionalinfo);
			String additional = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtadditionalinfo, additional,
					FindLawOrderToolPageObjects.drpdwnCustomerGoal);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal,
					FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1,
					FindLawOrderToolPageObjects.txtGeographic);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtGeographic);
			String geographic = ExcelUtils.getDataByColumnName("COB", "Geographic");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtGeographic, geographic,
					FindLawOrderToolPageObjects.txtClient);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtClient);
			String clientj = ExcelUtils.getDataByColumnName("COB", "Client");
			elementHandler.writeText(FindLawOrderToolPageObjects.txtClient, clientj,
					FindLawOrderToolPageObjects.tabproductQuestion);

			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.tabproductQuestion);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion,
					FindLawOrderToolPageObjects.btnExpand.get(0));

			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnExpand.get(0));
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand.get(0), FindLawOrderToolPageObjects.btnExpand1);
			actionHandler.waitForSomeTime(1000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand1
					);
			actionHandler.waitForSomeTime(2000);

			String titlew= elementHandler.getText(FindLawOrderToolPageObjects.Expandsubbtntitle);
			System.out.println("title:" +titlew);
			boolean valueq= titlew.contains("41056103 - Starter Profile");
			if (valueq == true) {
				System.out.println("started with 1st product");
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(3));
				String Attorney_Name = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(3), Attorney_Name,
						FindLawOrderToolPageObjects.txtinputs2.get(5));

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(5));
				String Primary_area = ExcelUtils.getDataByColumnName("COB", "Primary_area");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(5), Primary_area,
						FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1));

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1));
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1),
						FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1,
						FindLawOrderToolPageObjects.drpdwnSelect.get(6));

				/*javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(6));
				String Prac_Area1 = ExcelUtils.getDataByColumnName("COB", "Prac_Area1");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(6), Prac_Area1,
						FindLawOrderToolPageObjects.drpdwnSelect.get(6));*/

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(6));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(6), 1,
						FindLawOrderToolPageObjects.drpdwnSelect.get(7));

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(7));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(7), 1,
						FindLawOrderToolPageObjects.txtinputs2.get(6));

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(6));
				String Email_Add = ExcelUtils.getDataByColumnName("COB", "Email_Add");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(6), Email_Add,
						FindLawOrderToolPageObjects.btnExpand2);
				
				
			}else {
				
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(6));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(6), 1,
					FindLawOrderToolPageObjects.drpdwnSelect.get(7));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(7));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(7), 2,
					FindLawOrderToolPageObjects.btnExpand2);

			}
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpand2);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand2);
			
			String titleo= elementHandler.getText(FindLawOrderToolPageObjects.Expandsubbtn1title);
			System.out.println("title:" +titleo);
			boolean valuep= titleo.contains("40588593 - Firmsite III");
			if (valuep == true) {
			System.out.println("started with 2nd product");
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(8));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(8), 2,
					FindLawOrderToolPageObjects.drpdwnSelect.get(9));
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(9));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(9), 2,
					FindLawOrderToolPageObjects.btnSave);
			
			
			}else {
		
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(5));
				String Attorney_Name = ExcelUtils.getDataByColumnName("COB", "Attorney_Name");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(5), Attorney_Name,
						FindLawOrderToolPageObjects.txtinputs2.get(5));
				actionHandler.waitForSomeTime(5000);
				
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(5));
				String Primary_area = ExcelUtils.getDataByColumnName("COB", "Primary_area");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(5), Primary_area,
						FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1));
				actionHandler.waitForSomeTime(5000);
				
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1));
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(1),
						FindLawOrderToolPageObjects.drpdwnCustomerGoal1);
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal1,
						FindLawOrderToolPageObjects.drpdwnSelect.get(8));
				actionHandler.waitForSomeTime(5000);

				/*javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(6));
				String Prac_Area1 = ExcelUtils.getDataByColumnName("COB", "Prac_Area1");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(6), Prac_Area1,
						FindLawOrderToolPageObjects.drpdwnSelect.get(8));*/

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(8));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(8), 1,
						FindLawOrderToolPageObjects.drpdwnSelect.get(9));

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(9));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(9), 2,
						FindLawOrderToolPageObjects.txtinputs2.get(6));

				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(6));
				String Email_Add = ExcelUtils.getDataByColumnName("COB", "Email_Add");
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(6), Email_Add,
						FindLawOrderToolPageObjects.btnSave);
				
			}
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(5000);

			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(5000);
			
			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(5000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String Statusf=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(Statusf.contains("COB Completed"));
			break;
			
/*
 * general method for Firmsite and IM products
 */
			case 24:
				elementHandler.waitForXpathToLoad(lblCustomerInfo);
				actionHandler.waitForSomeTime(60000);
				elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabgeneralQues);
				elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
				
				int textSize= FindLawOrderToolPageObjects.txtinputs2.size();
				int areaSize= FindLawOrderToolPageObjects.txtinputs.size();
				int drpdwnSize= FindLawOrderToolPageObjects.drpdwnSelect.size();
				int dropSize= FindLawOrderToolPageObjects.drpdwnCustomerGoal3.size();
				//int values = FindLawOrderToolPageObjects.drpdwnPractisevalues.size();
				
				String Phonej= ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
				String emailm = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
				String text1 = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
				
				for (int i=0;i<textSize;i++)
				{
					String text= FindLawOrderToolPageObjects.lblText.get(i).getText();	
					System.out.println(text);
				if (FindLawOrderToolPageObjects.lblText.get(i).getText().contains("Email(s)"))  {
					javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(i));
					elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), emailm);
					actionHandler.waitForSomeTime(2000);
				}
				
				else 
					if (FindLawOrderToolPageObjects.lblText.get(i).getText().contains("Email address")) {
					javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(i));
					elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), emailm);
					actionHandler.waitForSomeTime(2000);
				}
				
				else 
					if (FindLawOrderToolPageObjects.lblText.get(i).getText().contains("Phone(s)")) {
					javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(i));
					elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), Phonej);
					actionHandler.waitForSomeTime(2000);
				}
				else 
					if ((!FindLawOrderToolPageObjects.lblText.get(i).getText().equals("Email(s)"))|(!FindLawOrderToolPageObjects.lblText.get(i).getText().equals("Phone(s)"))) {
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(i));
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), text1);
				actionHandler.waitForSomeTime(2000);
				}			
				}
				
				String text3 = ExcelUtils.getDataByColumnName("COB", "URL");
				for (int i=0;i<areaSize;i++) {
					javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(i));
					elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(i), text3);
					actionHandler.waitForSomeTime(2000);
				}
				
				for (int i=0;i<drpdwnSize;i++) {
					javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(i));
					elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(i), 2);
					actionHandler.waitForSomeTime(2000);
				}
				
				for (int i=0;i<dropSize;i++) {
					javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(i));
					elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(i));
					elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(i));
					actionHandler.waitForSomeTime(2000);
					elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnPractisevalues.get(2));
					actionHandler.waitForSomeTime(2000);
				}
		
				//started with product questions tab
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.tabproductQuestion);
				elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabproductQuestion);
				elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion
						);
				
			int status= FindLawOrderToolPageObjects.btnExpand.size();
			if (status > 0) 
				{
					for (int i=0;i<status;i++)
					{
					javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpand.get(i));
					actionHandler.waitForSomeTime(2000);
					elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnExpand.get(i));
					javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.btnExpand.get(i));
					//elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand.get(i));
					actionHandler.waitForSomeTime(2000);
					}
				}
						int status1= FindLawOrderToolPageObjects.btnExpands.size();
						if (status1 >0) {
							for (int j=0;j<status1;j++) {
								javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpands.get(j));
								elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnExpands.get(j));
								javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.btnExpands.get(j));
								//elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpands.get(j));
								actionHandler.waitForSomeTime(2000);
							}
						}
								int status2= FindLawOrderToolPageObjects.btnExpandsubs.size();
								if (status2 >0) {
									for (int k=0;k<status2;k++) {
										javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpandsubs.get(k));
										elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnExpandsubs.get(k));
										javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.btnExpandsubs.get(k));
										//elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpandsubs.get(k));
										actionHandler.waitForSomeTime(2000);
									}
								}
										int status3= FindLawOrderToolPageObjects.expandBtn3.size();
										if (status3 >0) {
											for (int l=0;l<status3;l++) {
												javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.expandBtn3.get(l));
												elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.expandBtn3.get(l));
												javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.expandBtn3.get(l));
												//elementHandler.clickElement(FindLawOrderToolPageObjects.expandBtn3.get(l));
												actionHandler.waitForSomeTime(2000);	
											}
										}
									
										int status4= FindLawOrderToolPageObjects.expandBtn3.size();
										if (status4 >0) {
											for (int l=0;l<status4;l++) {
												javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.expandBtn3.get(l));
												elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.expandBtn3.get(l));
												javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.expandBtn3.get(l));
												//elementHandler.clickElement(FindLawOrderToolPageObjects.expandBtn3.get(l));
												actionHandler.waitForSomeTime(2000);	
											}
										}
										
			int textSize1= FindLawOrderToolPageObjects.txtinputs2.size();
			int areaSize1= FindLawOrderToolPageObjects.txtinputs.size();
			int drpdwnSize1= FindLawOrderToolPageObjects.drpdwnSelect.size();
			int dropSize1= FindLawOrderToolPageObjects.drpdwnCustomerGoal3.size();
			//int values1 = FindLawOrderToolPageObjects.drpdwnPractisevalues.size();
			
			if (textSize1>0) {
			for (int i=textSize;i<textSize1;i++) {
				
				if (FindLawOrderToolPageObjects.lblText.get(i).getText().contains("Email")) {
					javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.txtinputs2.get(i));
					elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), emailm);
					actionHandler.waitForSomeTime(2000);
				}
				else
					if (FindLawOrderToolPageObjects.lblText.get(i).getText().contains("Phone")) {
				javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.txtinputs2.get(i));	
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), Phonej);
				actionHandler.waitForSomeTime(2000);
				}
					else
						if ((!FindLawOrderToolPageObjects.lblText.get(i).getText().equals("Email"))|(!FindLawOrderToolPageObjects.lblText.get(i).getText().equals("Phone"))){
				javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.txtinputs2.get(i));
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), text1);
				actionHandler.waitForSomeTime(2000);
				
			}
			}
		}
			
			if (areaSize1>0) {
			for (int j=areaSize;j<areaSize1;j++) {
				javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.txtinputs.get(j));
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(j), text3);
				actionHandler.waitForSomeTime(2000);
			}
		}
			
			if (drpdwnSize1>0) {
			for (int k=drpdwnSize;k<drpdwnSize1;k++) {
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(k));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(k), 2);
				actionHandler.waitForSomeTime(2000);
			}
		}
			
			if (dropSize1>0) {
			for (int l=dropSize;l<dropSize1;l++) {
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(l));
				elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(l));
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(l));
				actionHandler.waitForSomeTime(2000);
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnPractisevalues.get(3));
				actionHandler.waitForSomeTime(2000);
			}
		}
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(9000);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnSubmitOrder);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(8000);
			
			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnConfirm);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(5000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(5000);
			String StatusCob=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(StatusCob.contains("COB Completed"));
			break;
			
/*
 * This case is for multiple IM products in the cart page			
 */
			case 25:
				elementHandler.waitForXpathToLoad(lblCustomerInfo);
				actionHandler.waitForSomeTime(50000);
				elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabgeneralQues);
				elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
				
				int textSize_1= FindLawOrderToolPageObjects.txtinputs2.size();
				int areaSize_1= FindLawOrderToolPageObjects.txtinputs.size();
				int drpdwnSize_1= FindLawOrderToolPageObjects.drpdwnSelect.size();
				int dropSize_1= FindLawOrderToolPageObjects.drpdwnCustomerGoal3.size();
				
				String Phonep= ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
				String emailk = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
				String text_v = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
				
				for (int i=0;i<textSize_1;i++)
				{
					String text= FindLawOrderToolPageObjects.lblText.get(i).getText();	
					/*System.out.println(text);*/
				if (FindLawOrderToolPageObjects.lblText.get(i).getText().contains("Email(s)"))  {
					javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(i));
					elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), emailk);
					actionHandler.waitForSomeTime(2000);
				}
				
				else 
					if (FindLawOrderToolPageObjects.lblText.get(i).getText().contains("Email address")) {
					javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(i));
					elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), emailk);
					actionHandler.waitForSomeTime(2000);
				}
				
				else 
					if (FindLawOrderToolPageObjects.lblText.get(i).getText().contains("Phone(s)")) {
					javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(i));
					elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), Phonep);
					actionHandler.waitForSomeTime(2000);
				}
				else 
					if ((!FindLawOrderToolPageObjects.lblText.get(i).getText().equals("Email(s)"))|(!FindLawOrderToolPageObjects.lblText.get(i).getText().equals("Phone(s)"))) {
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(i));
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), text_v);
				actionHandler.waitForSomeTime(2000);
				}			
				}
				
				String text_p = ExcelUtils.getDataByColumnName("COB", "URL");
				for (int i=0;i<areaSize_1;i++) {
					javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(i));
					elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(i), text_p);
					actionHandler.waitForSomeTime(2000);
				}
				
				for (int i=0;i<drpdwnSize_1;i++) {
					javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(i));
					elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(i), 2);
					actionHandler.waitForSomeTime(2000);
				}
				
				for (int i=0;i<dropSize_1;i++) {
					javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(i));
					elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(i));
					elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(i));
					actionHandler.waitForSomeTime(2000);
					elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnPractisevalues.get(3));
					actionHandler.waitForSomeTime(2000);
				}
		
				//started with product questions tab
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.tabproductQuestion);
				elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabproductQuestion);
				elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion
						);
				
			int status_1= FindLawOrderToolPageObjects.btnExpand.size();
			if (status_1 > 0) 
				{
					for (int i=0;i<8;i++)
					{
					javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpand.get(i));
					actionHandler.waitForSomeTime(2000);
					elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnExpand.get(i));
					javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.btnExpand.get(i));
					actionHandler.waitForSomeTime(2000);
					}
				}
			
								int status_4= FindLawOrderToolPageObjects.btnExpandsubs.size();
								if (status_4 >0) {
									for (int k=0;k<19;k++) {
										javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpandsubs.get(k));
										elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnExpandsubs.get(k));
										javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.btnExpandsubs.get(k));
										actionHandler.waitForSomeTime(2000);
									}
								}
										
			int textSize_2= FindLawOrderToolPageObjects.txtinputs2.size();
			int areaSize_2= FindLawOrderToolPageObjects.txtinputs.size();
			int drpdwnSize_2= FindLawOrderToolPageObjects.drpdwnSelect.size();
			int dropSize_2= FindLawOrderToolPageObjects.drpdwnCustomerGoal3.size();
			
			if (textSize_2>0) {
			for (int i=textSize_1;i<textSize_2;i++) {
				
				if (FindLawOrderToolPageObjects.lblText.get(i).getText().contains("Email")) {
					String timer = elementHandler.getText(FindLawOrderToolPageObjects.lblTimer);
					String[] timerval = timer.split(":");
					int tim2 = Integer.parseInt(timerval[1].trim());
					if ((tim2 > 5) | (tim2 < 7)) {
					actionHandler.waitForSomeTime(15000);
					javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.txtinputs2.get(i));
					elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), emailk);
					
					}else {
						//actionHandler.waitForSomeTime(12000);	
						javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.txtinputs2.get(i));
						elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), emailk);
						
					}
				}
				else
					if (FindLawOrderToolPageObjects.lblText.get(i).getText().contains("Phone")) {
						String timer = elementHandler.getText(FindLawOrderToolPageObjects.lblTimer);
						String[] timerval = timer.split(":");
						int tim2 = Integer.parseInt(timerval[1].trim());
						if ((tim2 > 5) | (tim2 < 7)) {
							actionHandler.waitForSomeTime(15000);
							javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.txtinputs2.get(i));
							elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), Phonep);
							
						} else {
							//actionHandler.waitForSomeTime(12000);
							javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.txtinputs2.get(i));
							elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), Phonep);
							
						}
					}
					else
						if ((!FindLawOrderToolPageObjects.lblText.get(i).getText().equals("Email"))|(!FindLawOrderToolPageObjects.lblText.get(i).getText().equals("Phone"))){
							String timer= elementHandler.getText(FindLawOrderToolPageObjects.lblTimer);
							String[] timerval= timer.split(":");
							int tim2 = Integer.parseInt(timerval[1].trim());
							if ((tim2 >5) | (tim2 < 7)) {
							actionHandler.waitForSomeTime(15000);
							elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.txtinputs2.get(i));
							javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.txtinputs2.get(i));
							elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), text_v);
							
							}else {
								//actionHandler.waitForSomeTime(12000);
								javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.txtinputs2.get(i));
								elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), text_v);
									
							}
							
				
			}
			}
		}
			
			if (areaSize_2>0) {
			for (int j=areaSize_1;j<areaSize_2;j++) {
				String timer= elementHandler.getText(FindLawOrderToolPageObjects.lblTimer);
				String[] timerval= timer.split(":");
				int tim2 = Integer.parseInt(timerval[1].trim());
				if ((tim2 >5) | (tim2 < 7)) {
				actionHandler.waitForSomeTime(15000);
				javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.txtinputs.get(j));
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(j), text_v);
				
				}else {
					//actionHandler.waitForSomeTime(12000);
					javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.txtinputs.get(j));
					elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(j), text_v);
					
				}
			}
		}
			
			if (drpdwnSize_2>0) {
			for (int k=drpdwnSize_1;k<drpdwnSize_2;k++) {
				String timer= elementHandler.getText(FindLawOrderToolPageObjects.lblTimer);
				String[] timerval= timer.split(":");
				int tim2 = Integer.parseInt(timerval[1].trim());
				if ((tim2 >5) | (tim2 < 7)) {
				actionHandler.waitForSomeTime(15000);
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(k));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(k), 2);
				;
				}else {
					//actionHandler.waitForSomeTime(12000);
					javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(k));
					elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(k), 2);
					
				}
			}
		}
			
			if (dropSize_2>0) {
			for (int l=dropSize_1;l<dropSize_2;l++) {
				System.out.println(dropSize_2 +"size of drop down");
				String timer= elementHandler.getText(FindLawOrderToolPageObjects.lblTimer);
				String[] timerval= timer.split(":");
				int tim2 = Integer.parseInt(timerval[1].trim());
				if ((tim2 >5) | (tim2 < 7)) {
				actionHandler.waitForSomeTime(15000);
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(l));
				elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(l));
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(l));
				actionHandler.waitForSomeTime(2000);
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnPractisevalues.get(3));
				actionHandler.waitForSomeTime(2000);
				}else {
					//actionHandler.waitForSomeTime(12000);
					javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(l));
					elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(l));
					elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(l));
					actionHandler.waitForSomeTime(2000);
					elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnPractisevalues.get(3));
					
				}
				
			}
		}
			
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnSave);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnSubmitOrder);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
			actionHandler.waitForSomeTime(10000);
			
			windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnConfirm);
			elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(8000);
			TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(8000);
			String StatusCOB1=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
			Assert.assertTrue(StatusCOB1.contains("COB Completed"));
			break;
			
			
		case 26:			
			elementHandler.waitForXpathToLoad(lblCustomerInfo);
			actionHandler.waitForSomeTime(60000);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabgeneralQues);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabgeneralQues);
			
			int textSizes= FindLawOrderToolPageObjects.txtinputs2.size();
			int areaSizes= FindLawOrderToolPageObjects.txtinputs.size();
			int drpdwnSizes= FindLawOrderToolPageObjects.drpdwnSelect.size();
			int dropSizes= FindLawOrderToolPageObjects.drpdwnCustomerGoal3.size();
			
			String Phonek= ExcelUtils.getDataByColumnName("COB", "Contact_Phone");
			String emailj = ExcelUtils.getDataByColumnName("COB", "Contact_Email");
			String text5 = ExcelUtils.getDataByColumnName("COB", "Additional_Info");
			
			for (int i=0;i<textSizes;i++)
			{
				String text= FindLawOrderToolPageObjects.lblText.get(i).getText();	
				System.out.println(text);
			if (FindLawOrderToolPageObjects.lblText.get(i).getText().contains("Email(s)"))  {
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(i));
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), emailj);
				actionHandler.waitForSomeTime(2000);
			}
			
			else 
				if (FindLawOrderToolPageObjects.lblText.get(i).getText().contains("Email address")) {
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(i));
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), emailj);
				actionHandler.waitForSomeTime(2000);
			}
			
			else 
				if (FindLawOrderToolPageObjects.lblText.get(i).getText().contains("Phone(s)")) {
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(i));
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), Phonek);
				actionHandler.waitForSomeTime(2000);
			}
			else 
				if ((!FindLawOrderToolPageObjects.lblText.get(i).getText().equals("Email(s)"))|(!FindLawOrderToolPageObjects.lblText.get(i).getText().equals("Phone(s)"))) {
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs2.get(i));
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), text5);
			actionHandler.waitForSomeTime(2000);
			}			
			}
			
			String text9 = ExcelUtils.getDataByColumnName("COB", "URL");
			for (int i=0;i<areaSizes;i++) {
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.txtinputs.get(i));
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(i), text9);
				actionHandler.waitForSomeTime(2000);
			}
			
			for (int i=0;i<drpdwnSizes;i++) {
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(i));
				elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(i), 2);
				actionHandler.waitForSomeTime(2000);
			}
			
			for (int i=0;i<dropSizes;i++) {
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(i));
				elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(i));
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(i));
				actionHandler.waitForSomeTime(2000);
				elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnPractisevalues.get(3));
				actionHandler.waitForSomeTime(2000);
			}
	
			//started with product questions tab
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.tabproductQuestion);
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.tabproductQuestion);
			elementHandler.clickElement(FindLawOrderToolPageObjects.tabproductQuestion
					);
			
		int status_2= FindLawOrderToolPageObjects.btnExpand.size();
		if (status_2 > 0) 
			{
				for (int i=0;i<status_2;i++)
				{
				javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpand.get(i));
				actionHandler.waitForSomeTime(2000);
				elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnExpand.get(i));
				javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.btnExpand.get(i));
				//elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpand.get(i));
				actionHandler.waitForSomeTime(2000);
				}
			}
					int status_3= FindLawOrderToolPageObjects.btnExpands.size();
					if (status_3 >0) {
						for (int j=0;j<status_3;j++) {
							javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpands.get(j));
							elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnExpands.get(j));
							javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.btnExpands.get(j));
							//elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpands.get(j));
							actionHandler.waitForSomeTime(2000);
						}
					}
							int status_5= FindLawOrderToolPageObjects.btnExpandsubs.size();
							if (status_5 >0) {
								for (int k=0;k<status_5;k++) {
									javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnExpandsubs.get(k));
									elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnExpandsubs.get(k));
									javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.btnExpandsubs.get(k));
									//elementHandler.clickElement(FindLawOrderToolPageObjects.btnExpandsubs.get(k));
									actionHandler.waitForSomeTime(2000);
								}
							}
									int status_6= FindLawOrderToolPageObjects.expandBtn3.size();
									if (status_6 >0) {
										for (int l=0;l<status_6;l++) {
											javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.expandBtn3.get(l));
											elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.expandBtn3.get(l));
											javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.expandBtn3.get(l));
											//elementHandler.clickElement(FindLawOrderToolPageObjects.expandBtn3.get(l));
											actionHandler.waitForSomeTime(2000);	
										}
									}
								
									
		int textSizeInp= FindLawOrderToolPageObjects.txtinputs2.size();
		int areaSizeInp= FindLawOrderToolPageObjects.txtinputs.size();
		int drpdwnSizeSlc= FindLawOrderToolPageObjects.drpdwnSelect.size();
		int dropSizeSlc= FindLawOrderToolPageObjects.drpdwnCustomerGoal3.size();
		//int values1 = FindLawOrderToolPageObjects.drpdwnPractisevalues.size();
		
		if (textSizeInp>0) {
		for (int i=textSizes;i<textSizeInp;i++) {
			
			if (FindLawOrderToolPageObjects.lblText.get(i).getText().contains("Email")) {
				javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.txtinputs2.get(i));
				elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), emailj);
				actionHandler.waitForSomeTime(2000);
			}
			else
				if (FindLawOrderToolPageObjects.lblText.get(i).getText().contains("Phone")) {
			javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.txtinputs2.get(i));	
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), Phonek);
			actionHandler.waitForSomeTime(2000);
			}
				else
					if ((!FindLawOrderToolPageObjects.lblText.get(i).getText().equals("Email"))|(!FindLawOrderToolPageObjects.lblText.get(i).getText().equals("Phone"))){
			javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.txtinputs2.get(i));
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs2.get(i), text5);
			actionHandler.waitForSomeTime(2000);
			
		}
		}
	}
		
		if (areaSizeInp>0) {
		for (int j=areaSizes;j<areaSizeInp;j++) {
			javascripthandler.scrollToElementAndClick(FindLawOrderToolPageObjects.txtinputs.get(j));
			elementHandler.writeText(FindLawOrderToolPageObjects.txtinputs.get(j), text5);
			actionHandler.waitForSomeTime(2000);
		}
	}
		
		if (drpdwnSizeSlc>0) {
		for (int k=drpdwnSizes;k<drpdwnSizeSlc;k++) {
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnSelect.get(k));
			elementHandler.selectByIndex(FindLawOrderToolPageObjects.drpdwnSelect.get(k), 2);
			actionHandler.waitForSomeTime(2000);
		}
	}
		
		if (dropSizeSlc>0) {
		for (int l=dropSizes;l<dropSizeSlc;l++) {
			javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(l));
			elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(l));
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnCustomerGoal3.get(l));
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(FindLawOrderToolPageObjects.drpdwnPractisevalues.get(3));
			actionHandler.waitForSomeTime(2000);
		}
	}
		
		javascripthandler.scrollIntoView(FindLawOrderToolPageObjects.btnSave);
		elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnSave);
		elementHandler.clickElement(FindLawOrderToolPageObjects.btnSave);
		actionHandler.waitForSomeTime(9000);
		elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnSubmitOrder);
		elementHandler.clickElement(FindLawOrderToolPageObjects.btnSubmitOrder);
		actionHandler.waitForSomeTime(8000);
		
		windowHandler.switchToLatestWindow(FindLawOrderToolPageObjects.btnConfirm);
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForElementTobeClickable(FindLawOrderToolPageObjects.btnConfirm);
		elementHandler.clickElement(FindLawOrderToolPageObjects.btnConfirm);
		actionHandler.waitForSomeTime(5000);
		TestNGCustomReporter.log(logger, "COB Process completed" +ReportBuilder.takesScreenshot());
		actionHandler.waitForSomeTime(5000);
		String StatusCob4=elementHandler.getText(FindLawOrderToolPageObjects.lblStatus);
		Assert.assertTrue(StatusCob4.contains("COB Completed"));
		break;

		

		
			
		
		
		default:
			TestNGCustomReporter.log(logger, "COB Page not displayed for this scenario");
		
		
		}
		}	
}
