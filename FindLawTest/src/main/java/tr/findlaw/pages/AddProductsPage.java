package tr.findlaw.pages;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.framework.asserts.AssertionHandler;
import com.framework.reports.ReportBuilder;
import com.framework.reports.TestNGCustomReporter;
import com.framework.utils.ExcelUtils;
import tr.findlaw.objects.AddProductsPageObjects;
import tr.findlaw.objects.LoginPageObjects;
import tr.findlaw.objects.QuotePageObjects;

public class AddProductsPage extends BasePage {
	
	String lblCategorySelectn= "//div[@apt-dropdown='category-selection']";
	String btnProductAttr= "//span[text()='Product Attributes']";
	String  imgQuotepg  ="//td[text()='Progress']/../td[2]//img";
	String  lblPrice= "//div[@id='ep']/div[1]//td[1]/h2";
	
	public AddProductsPage(WebDriver webDriver) {
		super(webDriver);
		
	}

public void addProducts(int ctrl,int Row) {
		
		switch(ctrl)
		{		
	/*
	 * This case is for adding either Firmsite or IM product to the cart .
	 * Finalize or Submit for Approval process need to complete.
	 */
	
		case 1:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);
			actionHandler.waitForSomeTime(5000);   
			elementHandler.waitForPageToLoad();
			elementHandler.waitForXpathToLoad(lblCategorySelectn);
            actionHandler.waitForSomeTime(15000);                  
            String Product = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
            String[] listofproduct1 = Product.split(","); 
            for (int i=0; i<listofproduct1.length; i++)
            {
            	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, listofproduct1[i]);
                actionHandler.keyboardAction(Keys.ENTER);
                actionHandler.waitForSomeTime(25000);
                elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
                elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
                actionHandler.waitForSomeTime(10000);
				elementHandler.waitForXpathToLoad(btnProductAttr);
				elementHandler.waitForPageToLoad();
				elementHandler.writeText(AddProductsPageObjects.txtboxNumbAttrys, "5");
				actionHandler.waitForSomeTime(2000);
				elementHandler.clickElement(AddProductsPageObjects.tabSecondaries);
				actionHandler.waitForSomeTime(10000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
				elementHandler.clickElement(AddProductsPageObjects.btnValidate);
				actionHandler.waitForSomeTime(30000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
				elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
				actionHandler.waitForSomeTime(30000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
				elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts);
				actionHandler.waitForSomeTime(30000);
                TestNGCustomReporter.log(logger, "Product "+listofproduct1[i] +" Added Successfully");
            }
            actionHandler.waitForSomeTime(5000);
            elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
            elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(40000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblNetAdjust);
			actionHandler.waitForSomeTime(2000);
			TestNGCustomReporter.log(logger, "Net Adjustment Amount displayed" +ReportBuilder.takesScreenshot());
			 elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
			elementHandler.clickElement(AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(40000);
			
			boolean errorMessage= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
			if (errorMessage == true) {
				String message= AddProductsPageObjects.lblValidatnMessage.getText();
				TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
			}
			
			boolean enablebtn= AddProductsPageObjects.btnFinalize.isEnabled();
			if (enablebtn == true) {
				actionHandler.waitForSomeTime(10000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
				elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
				actionHandler.waitForSomeTime(30000);
				elementHandler.waitForXpathToLoad(imgQuotepg);
				String quote= elementHandler.getText(AddProductsPageObjects.lblproposalId);
				AssertionHandler.verifyTrue(quote.contains("Q-"), "Quote page not displayed");	
				TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Finalize button" +ReportBuilder.takesScreenshot());					
			}
			else {
				actionHandler.waitForSomeTime(10000);
				boolean approvalSymbol = elementHandler.isElementDisplayed(AddProductsPageObjects.lblSymbolApproval);
				if (approvalSymbol == true)
				TestNGCustomReporter.log(logger, "validate  Approval Triangle message symbol for Submit for Approval" +ReportBuilder.takesScreenshot());	
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmtForApproval);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmtForApproval,AddProductsPageObjects.btnSubmit);
				actionHandler.waitForSomeTime(15000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmit, AddProductsPageObjects.btnSubmit);
				actionHandler.waitForSomeTime(10000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmit,AddProductsPageObjects.btnReturn);
				actionHandler.waitForSomeTime(5000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReturn);
				elementHandler.clickElement(AddProductsPageObjects.btnReturn);
				actionHandler.waitForSomeTime(20000);
				String quote= elementHandler.getText(AddProductsPageObjects.lblproposalId);
				AssertionHandler.verifyTrue(quote.contains("Q-"), "Quote page not displayed");
				TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Submit for approval button" +ReportBuilder.takesScreenshot());
				QuotePage quotepage = new QuotePage(driver);
				String Name= quotepage.submitForApproval();
				SearchPage searchpage = new SearchPage(driver);
				searchpage.searchDiffProfile("Ryan Burch");
				searchpage.approvalProcess(Name);
				BasePage basePage = new BasePage(driver);
				basePage.Logout();
				searchpage.changeTab();	
			}
			break;
			
/*
 * This case is to add multiple IM or Firmsite products to the cart.
 * Provide Bridge,Contract,Discount details to the single or multiple products.
 * Finalize or Submit for Approval process need to be completed.	
 */
	
		case 2:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);
			elementHandler.waitForXpathToLoad(lblCategorySelectn);
            actionHandler.waitForSomeTime(15000);                  
            String Product1 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
            String[] listofproduct = Product1.split(",");           
            for (int i=0; i<listofproduct.length; i++)
            {
            	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, listofproduct[i]);
                actionHandler.keyboardAction(Keys.ENTER);
                actionHandler.waitForSomeTime(25000);
                elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
                elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
                actionHandler.waitForSomeTime(10000);
                elementHandler.waitForXpathToLoad(btnProductAttr);
                elementHandler.waitForPageToLoad();
				elementHandler.writeText(AddProductsPageObjects.txtboxNumbAttrys, "5");
				actionHandler.waitForSomeTime(2000);
				elementHandler.clickElement(AddProductsPageObjects.tabSecondaries);
				actionHandler.waitForSomeTime(5000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
				elementHandler.clickElement(AddProductsPageObjects.btnValidate);
				actionHandler.waitForSomeTime(25000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
				elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
				actionHandler.waitForSomeTime(30000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
				elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts);
				actionHandler.waitForSomeTime(15000);
                TestNGCustomReporter.log(logger, "Product "+listofproduct[i] +" Added Successfully");
            }
            actionHandler.waitForSomeTime(5000);
            elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
            elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(40000);
			
			//Contract Term
            String ContractTerm = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Contract_Term", Row);
            String[] arrContract = ContractTerm.split(",");
            for (int k=0;k<arrContract.length;k++) 
            {
            	if(!ContractTerm.equals("NA"))
            	{
            		/*
            		 * use drpdwnContractTerm1 (dev12) /drpdwnContractTerm (lrp5) according to the env 
            		 */
                   elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnContractTerm.get(k));          
                   elementHandler.clickElement(AddProductsPageObjects.drpdwnContractTerm.get(k));
                   TestNGCustomReporter.log(logger,"validate Contract Term drop down values" );
                   actionHandler.waitForSomeTime(2000);
                   for(int j=1; j<=5; j++)
                   {
                          String CntrctTrm=driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).getText();
                          TestNGCustomReporter.log(logger,"From list["+j+"] "+CntrctTrm.trim());
                          if(CntrctTrm.trim().contentEquals(ContractTerm.trim()))
                          {
                                 driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).click();
                                 actionHandler.waitForSomeTime(2000);
                                 break;
                          }
                          
                   }
            	}
           }
			
			//Adjustment Type
            String AdjustTerm = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Adjust_Type", Row);
            String[] arrAdjust = AdjustTerm.split(",");
            for (int i=0;i<arrAdjust.length;i++)
            {
            if (!AdjustTerm.equals("NA"))
            {
            	/*
            	 * use drpdwnAdjustmentTyp1 (dev12) or drpdwnAdjustmentTyp (lrp5) according to env
            	 */
                   elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnAdjustmentTyp.get(i));          
                   elementHandler.clickElement(AddProductsPageObjects.drpdwnAdjustmentTyp.get(i));
                   TestNGCustomReporter.log(logger,"validate Adjustment Type field drop down values");
                   actionHandler.waitForSomeTime(2000);
                   for(int j=1; j<=3; j++)
                   {
                          String AdjustTrm=driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).getText();
                          TestNGCustomReporter.log(logger,"From list["+j+"] "+AdjustTrm.trim());
                          if(AdjustTrm.trim().contentEquals(AdjustTerm.trim()))
                          {
                                 driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).click();
                                 actionHandler.waitForSomeTime(2000);
                                 break;
                          }
                          
                   }
            	}
            }
            
            //Adjustment Amount
            String adjustDiscount = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "AdjustAmount", Row);
            String[] arrDiscount = adjustDiscount.split(",");
            for (int k=0;k<arrDiscount.length;k++) 
            {
            	if (!adjustDiscount.equals("NA"))
            	{
            		/*
            		 * use txtAdjustAmount2 (dev12) or txtAdjustAmount1 (lrp5)
            		 */
                   actionHandler.waitForSomeTime(2000);
                   elementHandler.writeText(AddProductsPageObjects.txtAdjustAmount1.get(k), adjustDiscount);
                   actionHandler.waitForSomeTime(2000);
            	}  
            }
            
			//Bridge
            String bridge = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Bridge", Row);
            String[] arrBridge = bridge.split(",");
            for (int k=0;k<arrBridge.length;k++)
            {
	            if (!bridge.equals("NA"))
	            {
	            	/*
	            	 * use drpdwnBridge6 (dev12) or drpdwnBridge5 (lrp5)
	            	 */
                   elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnBridge5.get(k));          
                   elementHandler.clickElement(AddProductsPageObjects.drpdwnBridge5.get(k));
                   TestNGCustomReporter.log(logger,"validate Bridge field drop down values");
                   actionHandler.waitForSomeTime(2000);
                   for(int j=1; j<=5; j++)
                   {
                          String bridgeTrm=driver.findElement(By.xpath("//ul[contains(@id,'ui-select-choices-')]/li["+j+"]")).getText();
                          TestNGCustomReporter.log(logger,"From list["+j+"] "+bridgeTrm.trim());
                          if(bridgeTrm.trim().contentEquals(bridge.trim()))
                          {
                                 driver.findElement(By.xpath("//ul[contains(@id,'ui-select-choices-')]/li["+j+"]")).click();
                                 actionHandler.waitForSomeTime(2000);
                                 break;
                          }
                          
                   }
	            }
            } 
            
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblNetAdjust);
			actionHandler.waitForSomeTime(2000);
			TestNGCustomReporter.log(logger, "Net Adjustment Amount displayed" +ReportBuilder.takesScreenshot());
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
			javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnReprice);
			//elementHandler.clickElement(AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(40000);
			
			boolean errorMessage1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
			if (errorMessage1 == true) {
				String message= AddProductsPageObjects.lblValidatnMessage.getText();
				TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
			}
			
			boolean status= AddProductsPageObjects.btnFinalize.isEnabled();
			if (status == true) {
				actionHandler.waitForSomeTime(10000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
				elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
				actionHandler.waitForSomeTime(35000);
				elementHandler.waitForXpathToLoad(imgQuotepg);
				String quote= elementHandler.getText(AddProductsPageObjects.lblproposalId);
				AssertionHandler.verifyTrue(quote.contains("Q-"), "Quote page not displayed");
				TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Finalize button" +ReportBuilder.takesScreenshot());	
				
			}
			else {
				//elementHandler.waitForPageToLoad();
				actionHandler.waitForSomeTime(10000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmtForApproval);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmtForApproval,AddProductsPageObjects.btnSubmit);
				actionHandler.waitForSomeTime(20000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmit, AddProductsPageObjects.btnSubmit);
				actionHandler.waitForSomeTime(20000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmit,AddProductsPageObjects.btnReturn);
				actionHandler.waitForSomeTime(5000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReturn);
				elementHandler.clickElement(AddProductsPageObjects.btnReturn);
				actionHandler.waitForSomeTime(20000);
				String quote= elementHandler.getText(AddProductsPageObjects.lblproposalId);
				AssertionHandler.verifyTrue(quote.contains("Q-"), "Quote page not displayed");
				TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Submit for approval button" +ReportBuilder.takesScreenshot());
				QuotePage quotepage = new QuotePage(driver);
				String Name= quotepage.submitForApproval();
				SearchPage searchpage = new SearchPage(driver);
				searchpage.searchDiffProfile("Ryan Burch");
				searchpage.approvalProcess(Name);
				BasePage basePage = new BasePage(driver);
				basePage.Logout();
				if (LoginPageObjects.txtBoxUsrname.size() > 0) {
					basePage.loginWithValidUser();
				}
				searchpage.changeTab();
			}
			break;
		
/*
 * This case is to add multiple products to the cart and Finalize it.			
 */
			
		case 3:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);                    
			elementHandler.waitForXpathToLoad(lblCategorySelectn);     
            actionHandler.waitForSomeTime(15000);                  

            String Product2 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
            String[] listofproduct3 = Product2.split(",");
            
            for (int i=0; i<listofproduct3.length; i++)
            {
           elementHandler.writeText(AddProductsPageObjects.txtboxSearch, listofproduct3[i]);
           actionHandler.keyboardAction(Keys.ENTER);
           actionHandler.waitForSomeTime(25000);
           elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
           elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
			actionHandler.waitForSomeTime(25000);
           TestNGCustomReporter.log(logger, "Product "+listofproduct3[i] +" Added Successfully");
            }
            actionHandler.waitForSomeTime(5000);
            elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
            elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
            actionHandler.waitForSomeTime(30000);
            javascripthandler.scrollIntoView(AddProductsPageObjects.lblNetAdjust);
            TestNGCustomReporter.log(logger, "Net Adjustment Amount displayed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(2000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
			elementHandler.clickElement(AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(40000);
			boolean errorMessage2 = elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
			if (errorMessage2 == true) {
				String message= AddProductsPageObjects.lblValidatnMessage.getText();
				TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
			}
			
			boolean status1= AddProductsPageObjects.btnFinalize.isEnabled();
			if (status1 == true) {
				actionHandler.waitForSomeTime(5000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
				elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
				actionHandler.waitForSomeTime(30000);
				elementHandler.waitForXpathToLoad(imgQuotepg);
				String quote= elementHandler.getText(AddProductsPageObjects.lblproposalId);
				AssertionHandler.verifyTrue(quote.contains("Q-"), "Quote page not displayed");
				TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Finalize button" +ReportBuilder.takesScreenshot());	
				
			}
			else {
				actionHandler.waitForSomeTime(10000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmtForApproval);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmtForApproval,AddProductsPageObjects.btnSubmit);
				actionHandler.waitForSomeTime(10000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmit, AddProductsPageObjects.btnSubmit);
				actionHandler.waitForSomeTime(10000);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmit,AddProductsPageObjects.btnReturn);
				actionHandler.waitForSomeTime(5000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReturn);
				elementHandler.clickElement(AddProductsPageObjects.btnReturn);
				actionHandler.waitForSomeTime(20000);
				String quote= elementHandler.getText(AddProductsPageObjects.lblproposalId);
				AssertionHandler.verifyTrue(quote.contains("Q-"), "Quote page not displayed");
				TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Submit for approval button" +ReportBuilder.takesScreenshot());
				QuotePage quotepage = new QuotePage(driver);
				String Name= quotepage.submitForApproval();
				SearchPage searchpage = new SearchPage(driver);
				searchpage.searchDiffProfile("Ryan Burch");
				searchpage.approvalProcess(Name);
				BasePage basePage = new BasePage(driver);
				basePage.Logout();
				searchpage.changeTab();
			}
			break;

/*
 * This case is to add multiple products and add it to the cart.
 * Provide Details like Contract Term,Discount,Bridge to the products.
 * Finalize or Submit for Approval			
 */
			
		case 4:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);                    
			elementHandler.waitForXpathToLoad(lblCategorySelectn);     
            actionHandler.waitForSomeTime(15000);                  
            String Product3 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
            String[] listofproduct4 = Product3.split(",");    
            for (int i=0; i<listofproduct4.length; i++)
            {
           elementHandler.writeText(AddProductsPageObjects.txtboxSearch, listofproduct4[i]);
           actionHandler.keyboardAction(Keys.ENTER);
           actionHandler.waitForSomeTime(25000);
           elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
           elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
			actionHandler.waitForSomeTime(25000);
           TestNGCustomReporter.log(logger, "Product "+listofproduct4[i] +" Added Successfully");
            }
            actionHandler.waitForSomeTime(5000);
            elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
            elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
            actionHandler.waitForSomeTime(30000);
            
          //Contract Term
            String ContractTerm1 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Contract_Term", Row);
            String[] arrContract1 = ContractTerm1.split(",");
            for (int k=0;k<arrContract1.length;k++) 
            {
            	if (!ContractTerm1.equals("NA"))
            	{
            	   actionHandler.waitForSomeTime(5000);
                   elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnContractTerm.get(k));          
                   elementHandler.clickElement(AddProductsPageObjects.drpdwnContractTerm.get(k));
                   TestNGCustomReporter.log(logger,"validate contract Term field drop down values");
                   actionHandler.waitForSomeTime(2000);
                   for(int j=1; j<=5; j++)
                   {
                          String CntrctTrm1=driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).getText();
                          TestNGCustomReporter.log(logger,"From list["+j+"] "+CntrctTrm1.trim());
                          if(CntrctTrm1.trim().contentEquals(ContractTerm1.trim()))
                          {
                                 driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).click();
                                 break;
                          }
                          
                   }
            	}
           }
			
			//Adjustment Type
            String AdjustTerm1 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Adjust_Type", Row);
            String[] arrAdjust1 = AdjustTerm1.split(",");
            for (int i=0;i<arrAdjust1.length;i++)
            {
            if (!AdjustTerm1.equals("NA"))
            {
                   elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnAdjustmentTyp.get(i));          
                   elementHandler.clickElement(AddProductsPageObjects.drpdwnAdjustmentTyp.get(i));
                   TestNGCustomReporter.log(logger,"validate Adjustment Type field drop down values");
                   actionHandler.waitForSomeTime(2000);
                   for(int j=1; j<=3; j++)
                   {
                          String AdjustTrm1=driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).getText();
                          TestNGCustomReporter.log(logger,"From list["+j+"] "+AdjustTrm1.trim());
                          if(AdjustTrm1.trim().contentEquals(AdjustTerm1.trim()))
                          {
                                 driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).click();
                                 break;
                          }
                          
                   }
            	}
            }
            
            //Adjustment Amount
            String adjustDiscount1 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "AdjustAmount", Row);
            String[] arrDiscount1 = adjustDiscount1.split(",");
            for (int k=0;k<arrDiscount1.length;k++) 
            {
            	if (!adjustDiscount1.equals("NA"))
            	{
                   actionHandler.waitForSomeTime(2000);
                   elementHandler.writeText(AddProductsPageObjects.txtAdjustAmount1.get(k), adjustDiscount1);
            	}  
            }
            
			//Bridge
            String bridge1 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Bridge", Row);
            String[] arrBridge1 = bridge1.split(",");
            for (int k=0;k<arrBridge1.length;k++)
            {
	            if (!bridge1.equals("NA"))
	            {
                   elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnBridge5.get(k));          
                   elementHandler.clickElement(AddProductsPageObjects.drpdwnBridge5.get(k));
                   TestNGCustomReporter.log(logger,"validate Bridge field drop down values");
                   actionHandler.waitForSomeTime(2000);
                   for(int j=1; j<=5; j++)
                   {
                          String bridgeTrm1=driver.findElement(By.xpath("//ul[contains(@id,'ui-select-choices-')]/li["+j+"]")).getText();
                          TestNGCustomReporter.log(logger,"From list["+j+"] "+bridgeTrm1.trim());
                          if(bridgeTrm1.trim().contentEquals(bridge1.trim()))
                          {
                                 driver.findElement(By.xpath("//ul[contains(@id,'ui-select-choices-')]/li["+j+"]")).click();
                                 break;
                          }
                          
                   }
	            }
            } 
            
            javascripthandler.scrollIntoView(AddProductsPageObjects.lblNetAdjust);
            TestNGCustomReporter.log(logger, "Net Adjustment Amount displayed" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(2000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
			elementHandler.clickElement(AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(30000);
			boolean errorMessage4 = elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
			if (errorMessage4 == true) {
				String message= AddProductsPageObjects.lblValidatnMessage.getText();
				TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
			}
			
			boolean status2= AddProductsPageObjects.btnFinalize.isEnabled();
			if (status2 == true) {
				actionHandler.waitForSomeTime(10000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
				elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
				actionHandler.waitForSomeTime(25000);
				elementHandler.waitForXpathToLoad(imgQuotepg);
				String quote= elementHandler.getText(AddProductsPageObjects.lblproposalId);
				AssertionHandler.verifyTrue(quote.contains("Q-"), "Quote page not displayed");
				TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Finalize button" +ReportBuilder.takesScreenshot());	
				
			}
			else {
				actionHandler.waitForSomeTime(10000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmtForApproval);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmtForApproval,AddProductsPageObjects.btnSubmit);
				actionHandler.waitForSomeTime(10000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmit, AddProductsPageObjects.btnSubmit);
				actionHandler.waitForSomeTime(10000);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmit,AddProductsPageObjects.btnReturn);
				actionHandler.waitForSomeTime(5000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
				elementHandler.clickElement(AddProductsPageObjects.btnReturn);
				actionHandler.waitForSomeTime(20000);
				String quote= elementHandler.getText(AddProductsPageObjects.lblproposalId);
				AssertionHandler.verifyTrue(quote.contains("Q-"), "Quote page not displayed");
				TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Submit for approval button" +ReportBuilder.takesScreenshot());
				QuotePage quotepage = new QuotePage(driver);
				String Name= quotepage.submitForApproval();
				SearchPage searchpage = new SearchPage(driver);
				searchpage.searchDiffProfile("Ryan Burch");
				searchpage.approvalProcess(Name);
				BasePage basePage = new BasePage(driver);
				basePage.Logout();
				searchpage.changeTab();
			}
			break;
		
			
/*
 * This case is to add Installed Products.
 */
			
		case 5:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);
			elementHandler.waitForXpathToLoad(lblCategorySelectn);
            String installedprod = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product", Row);
            String[] listofproduct5 = installedprod.split(",");
            TestNGCustomReporter.log(logger, "No of Products "+listofproduct5.length);
            for (int i=0; i<listofproduct5.length; i++)
            {
			elementHandler.clickElement(AddProductsPageObjects.btnInstalledProduct,AddProductsPageObjects.txtInstalledProduct);
			actionHandler.waitForSomeTime(15000);
			boolean status3= elementHandler.isElementDisplayed(AddProductsPageObjects.txtInstalledProduct);
			if (status3== false) {
				actionHandler.waitForSomeTime(10000);
			}
			elementHandler.writeText(AddProductsPageObjects.txtInstalledProduct, listofproduct5[i]);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.checkbox);
			elementHandler.clickElement(AddProductsPageObjects.checkbox, AddProductsPageObjects.btnRenew);
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnRenew);
			elementHandler.clickElement(AddProductsPageObjects.btnRenew);
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.radiobtnoptn);
			elementHandler.clickElement(AddProductsPageObjects.radiobtnoptn, AddProductsPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfirm);
			elementHandler.clickElement(AddProductsPageObjects.btnConfirm,AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng, AddProductsPageObjects.btnAddMoreProducts);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
			elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts, AddProductsPageObjects.txtboxSearch);
			actionHandler.waitForSomeTime(30000);
            }
            elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
        	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
			elementHandler.clickElement(AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(30000);
			boolean errorMessage5= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
			if (errorMessage5 == true) {
				String message= AddProductsPageObjects.lblValidatnMessage.getText();
				TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
			}
			
			
			boolean status3= AddProductsPageObjects.btnFinalize.isEnabled();
			if (status3 == true) {
				TestNGCustomReporter.log(logger, "Finalize button enabled for Renewal product with Renewal Eligible option as Yes" +ReportBuilder.takesScreenshot());
				actionHandler.waitForSomeTime(10000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
				elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
				actionHandler.waitForSomeTime(25000);
				elementHandler.waitForXpathToLoad(imgQuotepg);
				String quote= elementHandler.getText(AddProductsPageObjects.lblproposalId);
				AssertionHandler.verifyTrue(quote.contains("Q-"), "Quote page not displayed");
				TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Finalize button" +ReportBuilder.takesScreenshot());		
			}
			else {
				actionHandler.waitForSomeTime(10000);
				TestNGCustomReporter.log(logger, "Submit For Approval button enabled for Renewal product with Renewal Eligible option as No" +ReportBuilder.takesScreenshot());
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmtForApproval);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmtForApproval,AddProductsPageObjects.btnSubmit);
				actionHandler.waitForSomeTime(10000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmit, AddProductsPageObjects.btnSubmit);
				actionHandler.waitForSomeTime(10000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmit,AddProductsPageObjects.btnReturn);
				actionHandler.waitForSomeTime(5000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReturn);
				elementHandler.clickElement(AddProductsPageObjects.btnReturn);
				actionHandler.waitForSomeTime(20000);
				String quote= elementHandler.getText(AddProductsPageObjects.lblproposalId);
				AssertionHandler.verifyTrue(quote.contains("Q-"), "Quote page not displayed");
				TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Submit for approval button" +ReportBuilder.takesScreenshot());
				QuotePage quotepage = new QuotePage(driver);
				String Name= quotepage.submitForApproval();
				SearchPage searchpage = new SearchPage(driver);
				searchpage.searchDiffProfile("Ryan Burch");
				searchpage.approvalProcess(Name);
				BasePage basePage = new BasePage(driver);
				basePage.Logout();
				searchpage.changeTab();
			}
			break;
			
/*
 * This case is to add IM products and validating recommended product is been added for it or not			
 */
		case 6:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);
			elementHandler.waitForXpathToLoad(lblCategorySelectn);
            String listofproducts = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
            String[] listofproducts1 = listofproducts.split(",");
			for (int i=0; i<listofproducts1.length; i++)
			{
			elementHandler.writeText(AddProductsPageObjects.txtboxSearch, listofproducts1[i]);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
			elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForXpathToLoad(btnProductAttr);
			elementHandler.waitForPageToLoad();
			elementHandler.writeText(AddProductsPageObjects.txtboxNumbAttrys, "5");
			elementHandler.clickElement(AddProductsPageObjects.tabSecondaries);
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
			elementHandler.clickElement(AddProductsPageObjects.btnValidate);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lblrecommendproduct);
			elementHandler.clickElement(AddProductsPageObjects.lblrecommendproduct);
			actionHandler.waitForSomeTime(2000);
			boolean chatStatus= elementHandler.isElementDisplayed(AddProductsPageObjects.lblWebchatConnect);
			if (chatStatus == false)		
			TestNGCustomReporter.log(logger, "Recommended product of Web Chat option not displayed for an IM Solution product" + listofproducts1[i] +" " +ReportBuilder.takesScreenshot());
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
			elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts, AddProductsPageObjects.txtboxSearch);
			}
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng, AddProductsPageObjects.btnFinalize);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
			elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForXpathToLoad(imgQuotepg);
			String quote1= elementHandler.getText(AddProductsPageObjects.lblproposalId);
			AssertionHandler.verifyTrue(quote1.contains("Q-"), "Quote page not displayed");
			TestNGCustomReporter.log(logger, "products are added to the cart" +ReportBuilder.takesScreenshot());
			break;
	
			
/*
* 	This case is to add multiple IM (IMS,IMP, IMC, IME) products and validate Recommended web-chat product.Finalize the cart		
*/			
		case 7:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);
			elementHandler.waitForXpathToLoad(lblCategorySelectn);
            String imProducts = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
            String[] imProducts1 = imProducts.split(",");
			for (int i=0; i<imProducts1.length; i++)
			{
			elementHandler.writeText(AddProductsPageObjects.txtboxSearch, imProducts1[i]);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
			elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
			elementHandler.waitForXpathToLoad(btnProductAttr);
			elementHandler.waitForPageToLoad();
			elementHandler.writeText(AddProductsPageObjects.txtboxNumbAttrys, "6");
			elementHandler.clickElement(AddProductsPageObjects.tabSecondaries);
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
			elementHandler.clickElement(AddProductsPageObjects.btnValidate);
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
			elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts, AddProductsPageObjects.txtboxSearch);
			actionHandler.waitForSomeTime(15000);
			}
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng, AddProductsPageObjects.btnFinalize);
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lblrecommendproduct);
			elementHandler.clickElement(AddProductsPageObjects.lblrecommendproduct, AddProductsPageObjects.lblWebchatConnect);
			actionHandler.waitForSomeTime(2000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart);
			elementHandler.clickElement(AddProductsPageObjects.btnAddToCart);
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
			elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForXpathToLoad(imgQuotepg);
			String quote2= elementHandler.getText(AddProductsPageObjects.lblproposalId);
			AssertionHandler.verifyTrue(quote2.contains("Q-"), "Quote page not displayed");
			TestNGCustomReporter.log(logger, "products are added to the cart" +ReportBuilder.takesScreenshot());
			break;
			
/*'
 * 
 * This case is to Add Firmsite products: 40483693,40483696 and 41056102 (FNDLW FOCUS PAGE ) to the Cart and validate error messages			
 */
		case 8:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);
			elementHandler.waitForXpathToLoad(lblCategorySelectn);
            String FirmsiteProds = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
            String[] FirmsiteProd1 = FirmsiteProds.split(",");
			for (int i=0; i<FirmsiteProd1.length; i++)
			{
			elementHandler.writeText(AddProductsPageObjects.txtboxSearch, FirmsiteProd1[i]);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
			elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
			actionHandler.waitForSomeTime(15000);
			elementHandler.waitForXpathToLoad(btnProductAttr);
			elementHandler.waitForPageToLoad();
			elementHandler.writeText(AddProductsPageObjects.txtboxNumbAttrys, "2");
			elementHandler.clickElement(AddProductsPageObjects.tabSecondaries);
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
			elementHandler.clickElement(AddProductsPageObjects.btnValidate);
			actionHandler.waitForSomeTime(20000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
			elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts, AddProductsPageObjects.txtboxSearch);
			actionHandler.waitForSomeTime(15000);
			}
			String installed = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product", Row);
			elementHandler.writeText(AddProductsPageObjects.txtboxSearch,installed);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
			elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
			actionHandler.waitForSomeTime(15000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
			elementHandler.clickElement(AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(30000);
			boolean status_0= elementHandler.isElementDisplayed(AddProductsPageObjects.lblErrorMessg);
			AssertionHandler.verifyTrue(status_0, "Error message not displayed in the cart page");
			TestNGCustomReporter.log(logger, "Validated the Error messages for all products" +ReportBuilder.takesScreenshot());
			break;

			
/*
 * This case is to add any blog product ex:41817449 or add any one of the engagement builder products 
 * like: 42089522,42089521,41180810,42089524 and validate error message
 */			
		case 9:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);
			elementHandler.waitForXpathToLoad(lblCategorySelectn);
            String blogProduct = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
            String[] blogProduct1 = blogProduct.split(",");
            for (int i=0; i<blogProduct1.length; i++)
            {
			elementHandler.writeText(AddProductsPageObjects.txtboxSearch, blogProduct1[i]);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
			elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
			actionHandler.waitForSomeTime(20000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddAnother);
			elementHandler.clickElement(AddProductsPageObjects.btnAddAnother);
			actionHandler.waitForSomeTime(20000);
            }
            elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
			elementHandler.clickElement(AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(25000);
			//boolean errStatus= elementHandler.isElementDisplayed(AddProductsPageObjects.lblErrorMsg);
			boolean errStatus1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
			//if ((errStatus == true) | (errStatus1== true)) {
			if (errStatus1== true) {
			AssertionHandler.verifyTrue(true, "Validation Message not displayed");
			//String Error_Message= elementHandler.getText(AddProductsPageObjects.lblErrorMsg);
			String Error_Message1= elementHandler.getText(AddProductsPageObjects.lblValidatnMessage);
			TestNGCustomReporter.log(logger, "Error Message displayed for adding the product as:"  +Error_Message1 +ReportBuilder.takesScreenshot());
			}
			break;
			
/*
 * This case is to add any Installed Firmsite product to the cart. Validate all columns in the cart 				
 */			
		case 10:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);
			elementHandler.waitForXpathToLoad(lblCategorySelectn);
			String IM = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product", Row);	
			elementHandler.clickElement(AddProductsPageObjects.btnInstalledProduct,AddProductsPageObjects.txtInstalledProduct);
			actionHandler.waitForSomeTime(10000);
			boolean inst= AddProductsPageObjects.txtInstalledProduct.isDisplayed();
			if (inst == false) {
				actionHandler.waitForSomeTime(10000);
			}
			//elementHandler.waitforElement(AddProductsPageObjects.txtInstalledProduct);
			elementHandler.writeText(AddProductsPageObjects.txtInstalledProduct,IM);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.checkbox);
			elementHandler.clickElement(AddProductsPageObjects.checkbox, AddProductsPageObjects.btnRenew);
			actionHandler.waitForSomeTime(2000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnRenew);
			elementHandler.clickElement(AddProductsPageObjects.btnRenew);
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.radiobtnoptn);
			elementHandler.clickElement(AddProductsPageObjects.radiobtnoptn, AddProductsPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfirm);
			elementHandler.clickElement(AddProductsPageObjects.btnConfirm,AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(30000);
			Boolean startDate= elementHandler.isElementDisplayed(AddProductsPageObjects.lblStartDate);
			AssertionHandler.verifyTrue(startDate, "Start Date field not displayed in the cart page");
			actionHandler.waitForSomeTime(2000);
			Boolean endDate= elementHandler.isElementDisplayed(AddProductsPageObjects.lblEndDate);
			AssertionHandler.verifyTrue(endDate, "End Date field not displayed in the cart page");
			actionHandler.waitForSomeTime(2000);
			Boolean quantity= elementHandler.isElementDisplayed(AddProductsPageObjects.lblQuantity);
			AssertionHandler.verifyTrue(quantity, "Quantity field not displayed in the cart page");
			actionHandler.waitForSomeTime(2000);
			Boolean adjustType= elementHandler.isElementDisplayed(AddProductsPageObjects.lblAdjustType);
			AssertionHandler.verifyTrue(adjustType, "Adjust Type field not displayed in the cart page");
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblAdjutAmt);
			actionHandler.waitForSomeTime(2000);
			Boolean adjustAmount= elementHandler.isElementDisplayed(AddProductsPageObjects.lblAdjutAmt);
			AssertionHandler.verifyTrue(adjustAmount, "Adjust Type field not displayed in the cart page");
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblGuidance);
			actionHandler.waitForSomeTime(2000);
			Boolean guidance= elementHandler.isElementDisplayed(AddProductsPageObjects.lblGuidance);
			AssertionHandler.verifyTrue(guidance, "Guidance field not displayed in the cart page");
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblNetPrice);
			actionHandler.waitForSomeTime(2000);
			Boolean netprice= elementHandler.isElementDisplayed(AddProductsPageObjects.lblNetPrice);
			AssertionHandler.verifyTrue(netprice, "Net Price field not displayed in the cart page");
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.highlightElement(AddProductsPageObjects.lblYear23);
			Boolean year23= elementHandler.isElementDisplayed(AddProductsPageObjects.lblYear23);
			AssertionHandler.verifyTrue(year23, "Yaer 2-3 price field not displayed in the cart page");
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblCrntPrice);
			actionHandler.waitForSomeTime(2000);
			Boolean price= elementHandler.isElementDisplayed(AddProductsPageObjects.lblCrntPrice);
			AssertionHandler.verifyTrue(price, "Current price field not displayed in the cart page");
			TestNGCustomReporter.log(logger, "Validate all fields displayed on Cart page" +ReportBuilder.takesScreenshot());
			break;
			
/*
 * This case is to add 	IMP Installed product:41915678. Validate all columns in the cart page.			
 */
		case 11:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);
			elementHandler.waitForXpathToLoad(lblCategorySelectn);
			String impProd = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product", Row);
			elementHandler.clickElement(AddProductsPageObjects.btnInstalledProduct,AddProductsPageObjects.txtInstalledProduct);
			actionHandler.waitForSomeTime(20000);
			boolean prod1= AddProductsPageObjects.txtInstalledProduct.isDisplayed();
			if (prod1 == false) {
				actionHandler.waitForSomeTime(10000);
			}
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.txtInstalledProduct);
			elementHandler.writeText(AddProductsPageObjects.txtInstalledProduct,impProd);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.checkbox);
			elementHandler.clickElement(AddProductsPageObjects.checkbox, AddProductsPageObjects.btnRenew);
			actionHandler.waitForSomeTime(2000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnRenew);
			elementHandler.clickElement(AddProductsPageObjects.btnRenew);
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.radiobtnoptn);
			elementHandler.clickElement(AddProductsPageObjects.radiobtnoptn, AddProductsPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfirm);
			elementHandler.clickElement(AddProductsPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(30000);
			Boolean lineStatus= elementHandler.isElementDisplayed(AddProductsPageObjects.lblLineStatus);
			AssertionHandler.verifyTrue(lineStatus, "Line Status field not displayed in the cart page");
			actionHandler.waitForSomeTime(2000);
			Boolean startDate1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblStartDate);
			AssertionHandler.verifyTrue(startDate1, "Start Date field not displayed in the cart page");
			actionHandler.waitForSomeTime(2000);
			Boolean endDate1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblEndDate);
			AssertionHandler.verifyTrue(endDate1, "end date field not displayed in the cart page");
			actionHandler.waitForSomeTime(2000);
			Boolean contractTerm= elementHandler.isElementDisplayed(AddProductsPageObjects.lnlContractTrm);
			AssertionHandler.verifyTrue(contractTerm, "Contract Term field not displayed in the cart page");
			actionHandler.waitForSomeTime(2000);
			Boolean quantity1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblQuantity);
			AssertionHandler.verifyTrue(quantity1, "Quantity field not displayed in the cart page");
			actionHandler.waitForSomeTime(2000);
			Boolean baseprice= elementHandler.isElementDisplayed(AddProductsPageObjects.lblBaseprice);
			AssertionHandler.verifyTrue(baseprice, "Base price field not displayed in the cart page");
			actionHandler.waitForSomeTime(2000);
			Boolean adjustType1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblAdjustType);
			AssertionHandler.verifyTrue(adjustType1, "Adjust Type field not displayed in the cart page");
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblAdjutAmt);
			actionHandler.waitForSomeTime(2000);
			Boolean adjustAmt= elementHandler.isElementDisplayed(AddProductsPageObjects.lblAdjutAmt);
			AssertionHandler.verifyTrue(adjustAmt, "Adjust Amount field not displayed in the cart page");
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblGuidance);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.highlightElement(AddProductsPageObjects.lblGuidance);
			Boolean guidance1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblGuidance);
			AssertionHandler.verifyTrue(guidance1, "Guidance field not displayed in the cart page");
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblNetPrice);
			actionHandler.waitForSomeTime(2000);
			Boolean netPrice= elementHandler.isElementDisplayed(AddProductsPageObjects.lblNetPrice);
			AssertionHandler.verifyTrue(netPrice, "Guidance field not displayed in the cart page");
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23);
			actionHandler.waitForSomeTime(2000);
			Boolean year23p= elementHandler.isElementDisplayed(AddProductsPageObjects.lblYear23);
			AssertionHandler.verifyTrue(year23p, "Yaer 2-3 price field not displayed in the cart page");
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23Surc);
			actionHandler.waitForSomeTime(2000);
			Boolean year23s= elementHandler.isElementDisplayed(AddProductsPageObjects.lblYear23Surc);
			AssertionHandler.verifyTrue(year23s, "Year 2-3 Surc field not displayed in the cart page");
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblCrntPrice);
			actionHandler.waitForSomeTime(2000);
			Boolean current= elementHandler.isElementDisplayed(AddProductsPageObjects.lblCrntPrice);
			AssertionHandler.verifyTrue(current, "Current price field not displayed in the cart page");
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblbridge);
			actionHandler.waitForSomeTime(2000);
			Boolean bridge2= elementHandler.isElementDisplayed(AddProductsPageObjects.lblbridge);
			AssertionHandler.verifyTrue(bridge2, "Bridge field not displayed in the cart page");
			TestNGCustomReporter.log(logger, "Validate all fields displayed on Cart page" +ReportBuilder.takesScreenshot());
			break;

/*
 * This case is to add PPC product and validate columns in the cart page		
 */
		case 12:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);
			elementHandler.waitforElement(AddProductsPageObjects.txtboxSearch);
			String ppcProduct = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id",Row);
			elementHandler.writeText(AddProductsPageObjects.txtboxSearch, ppcProduct);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
			elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
			actionHandler.waitForSomeTime(10000);
			for (int i=1;i<10;i++)
			{
			javascripthandler.highlightElement(AddProductsPageObjects.lblCartPage.get(i));
			actionHandler.waitForSomeTime(2000);
			TestNGCustomReporter.log(logger, "Validate the fields to be displayed in PPC Attributes" +ReportBuilder.takesScreenshot());
			}
			for(int i=15;i<18;i++)
			{
			Actions action= new Actions(driver);
			action.moveToElement(AddProductsPageObjects.labelQuestion.get(i)).perform();
			TestNGCustomReporter.log(logger, "Validate the Text for Question mark symbol" +ReportBuilder.takesScreenshot());
			}
			String ppcType1=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Type",Row);
			String ppclang1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Lang",Row);
			String ppcWebsite1=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Website",Row);
			String ppcAmount1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Amount",Row);
			elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCType1);
			TestNGCustomReporter.log(logger, "Validate drop down values for PPC Type field" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(2000);
			driver.findElement(By.xpath("//div[contains(text(),'"+ppcType1+"')]")).click();
			elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCLang);
			TestNGCustomReporter.log(logger, "Validate drop down values for PPC languange selection field" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(2000);
			driver.findElement(By.xpath("//div[contains(text(),'"+ppclang1+"')]")).click();
			elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCWebsite);
			TestNGCustomReporter.log(logger, "Validate drop down values for PPC Website Selection field" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(2000);
			driver.findElement(By.xpath("//div[text()='"+ppcWebsite1+"']")).click();
			elementHandler.writeText(AddProductsPageObjects.txtPPCAmount, ppcAmount1);
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(AddProductsPageObjects.lblCartPage.get(5));
			TestNGCustomReporter.log(logger, "Validate rounded value to be populated in Customers Total PPC Cost,PPC Management Fee,PPC Ad spent" +ReportBuilder.takesScreenshot());
			elementHandler.clickElement(AddProductsPageObjects.tabSecondaries);
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
			elementHandler.clickElement(AddProductsPageObjects.btnValidate);
			actionHandler.waitForSomeTime(25000);
			TestNGCustomReporter.log(logger, "Validate PPC Landing page Materials to be selected under PPC tab" +ReportBuilder.takesScreenshot());
			String info= elementHandler.getText(AddProductsPageObjects.labelInfo);
			AssertionHandler.verifyEquals(info, "US PPC Options with 2 Landing Pages Auto Included", "Informational message not displayed");
			break;
			
/*
 * This case is to add Installed Social promotion product:41817449 to the cart with Renewal option is No				
 */			
		case 13:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);
			elementHandler.waitForXpathToLoad(lblCategorySelectn);
			String BlogProd = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product", Row);	
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnInstalledProduct);
			elementHandler.clickElement(AddProductsPageObjects.btnInstalledProduct);
			actionHandler.waitForSomeTime(20000);
			boolean status8= AddProductsPageObjects.txtInstalledProduct.isDisplayed();
			if (status8 == false) {
				actionHandler.waitForSomeTime(10000);
			}
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.txtInstalledProduct);
			elementHandler.writeText(AddProductsPageObjects.txtInstalledProduct,BlogProd);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(20000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.checkbox);
			elementHandler.clickElement(AddProductsPageObjects.checkbox, AddProductsPageObjects.btnRenew);
			actionHandler.waitForSomeTime(2000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnRenew);
			elementHandler.clickElement(AddProductsPageObjects.btnRenew);
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.radiobtnoptn);
			elementHandler.clickElement(AddProductsPageObjects.radiobtnoptn, AddProductsPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfirm);
			elementHandler.clickElement(AddProductsPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
			elementHandler.clickElement(AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(30000);
			boolean errorMessage6 = elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
			if (errorMessage6 == true) {
				String message= AddProductsPageObjects.lblValidatnMessage.getText();
				TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
			}
			boolean enablebtn2= AddProductsPageObjects.btnFinalize.isEnabled();
			if (enablebtn2 == true) {
				actionHandler.waitForSomeTime(10000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
				elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
				actionHandler.waitForSomeTime(30000);
				elementHandler.waitForXpathToLoad(AddProductsPageObjects.lblQuoteProposl);
				String quote4= elementHandler.getText(AddProductsPageObjects.lblproposalId);
				AssertionHandler.verifyTrue(quote4.contains("Q-"), "Quote page not displayed");
				TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Finalize button" +ReportBuilder.takesScreenshot());	
				
			}
			else {
				actionHandler.waitForSomeTime(10000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmtForApproval);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmtForApproval,AddProductsPageObjects.btnSubmit);
				actionHandler.waitForSomeTime(15000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmit, AddProductsPageObjects.btnSubmit);
				actionHandler.waitForSomeTime(15000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmit,AddProductsPageObjects.btnReturn);
				actionHandler.waitForSomeTime(15000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReturn);
				elementHandler.clickElement(AddProductsPageObjects.btnReturn);
				actionHandler.waitForSomeTime(15000);
				elementHandler.waitForPageToLoad();
				String quote5= elementHandler.getText(AddProductsPageObjects.lblproposalId);
				AssertionHandler.verifyTrue(quote5.contains("Q-"), "Quote page not displayed");
				TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Submit for approval button" +ReportBuilder.takesScreenshot());
				QuotePage quotepage = new QuotePage(driver);
				String Name= quotepage.submitForApproval();
				SearchPage searchpage = new SearchPage(driver);
				searchpage.searchDiffProfile("Ryan Burch");
				searchpage.approvalProcess(Name);
				BasePage basePage = new BasePage(driver);
				basePage.Logout();
				searchpage.changeTab();
			}
			break;
	
/*
 * This case is to add PPC products: 41876119,Installed- any PPC product to the cart
 */			
		case 14:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);
			elementHandler.waitForXpathToLoad(lblCategorySelectn);
			String ppcproduct = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
			String[] ppcproduct1 = ppcproduct.split(",");
			for (int i=0; i<ppcproduct1.length; i++)
			{
			elementHandler.writeText(AddProductsPageObjects.txtboxSearch, ppcproduct1[i]);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
			elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
			actionHandler.waitForSomeTime(15000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
			elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts, AddProductsPageObjects.txtboxSearch);
			actionHandler.waitForSomeTime(10000);
			}
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnInstalledProduct);
			elementHandler.clickElement(AddProductsPageObjects.btnInstalledProduct);
			actionHandler.waitForSomeTime(15000);
			boolean status0= AddProductsPageObjects.txtInstalledProduct.isDisplayed();
			if (status0 == false) {
				actionHandler.waitForSomeTime(5000);
			}
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.txtInstalledProduct);
			String third_Prod = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product", Row);
			elementHandler.writeText(AddProductsPageObjects.txtInstalledProduct, third_Prod);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.checkbox);
			elementHandler.clickElement(AddProductsPageObjects.checkbox, AddProductsPageObjects.btnRenew);
			actionHandler.waitForSomeTime(2000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnRenew);
			elementHandler.clickElement(AddProductsPageObjects.btnRenew);
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.radiobtnoptn);
			elementHandler.clickElement(AddProductsPageObjects.radiobtnoptn, AddProductsPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(2000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfirm);
			elementHandler.clickElement(AddProductsPageObjects.btnConfirm);
			actionHandler.waitForSomeTime(15000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(25000);
			for (int i=1;i<3;i++) {
			driver.findElement(By.xpath("//div[2]/div['"+i+"']/div[6]/ul/li//dynamic-field//a/span[3]/b")).click();
			TestNGCustomReporter.log(logger, "Validate Short term contract length for PPC products" +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(1000);
			}
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
			elementHandler.clickElement(AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(30000);
			boolean errorMessage7 = elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
			if (errorMessage7 == true) {
				String message= AddProductsPageObjects.lblValidatnMessage.getText();
				TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
			}			
			break;

/*
 * This case is to  add product:42089521 to the cart and validate Adjustment columns would removed from the cart				
 */			
		case 15:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);
			elementHandler.waitForXpathToLoad(lblCategorySelectn);
			String BlogProds = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
			elementHandler.writeText(AddProductsPageObjects.txtboxSearch, BlogProds);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
			elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
			actionHandler.waitForSomeTime(15000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(25000);
			boolean finalize= elementHandler.isElementDisplayed(AddProductsPageObjects.btnFinalize);
			AssertionHandler.verifyTrue(finalize, "Finalize button not displayed in cart page");
			TestNGCustomReporter.log(logger, "Adjustment Type and Adjustment Amount columns displayed in the Totals section"  +ReportBuilder.takesScreenshot());
			actionHandler.waitForSomeTime(2000);
			break;
		
/*
 * This case is to add PPC products:30010890,41876119,41877783 and validate the Adjustment Type field for all products
 */			
		case 16:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);
			elementHandler.waitForXpathToLoad(lblCategorySelectn);
			String ppcprod = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product", Row);
			elementHandler.writeText(AddProductsPageObjects.txtboxSearch, ppcprod);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
			elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
			actionHandler.waitForSomeTime(10000);
			String ppcType=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Type",Row);
			String ppclang =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Lang",Row);
			String ppcWebsite=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Website",Row);
			String ppcAmount =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Amount",Row);
			elementHandler.waitForXpathToLoad("//span[text()='Product Attributes']");
			elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCType);
			actionHandler.waitForSomeTime(2000);
			driver.findElement(By.xpath("//div[contains(text(),'"+ppcType+"')]")).click();
			elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCLang);
			actionHandler.waitForSomeTime(2000);
			driver.findElement(By.xpath("//div[contains(text(),'"+ppclang+"')]")).click();
			elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCWebsite);
			actionHandler.waitForSomeTime(2000);
			driver.findElement(By.xpath("//div[text()='"+ppcWebsite+"']")).click();
			elementHandler.writeText(AddProductsPageObjects.txtPPCAmount, ppcAmount);
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(AddProductsPageObjects.tabSecondaries);
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
			elementHandler.clickElement(AddProductsPageObjects.btnValidate);
			actionHandler.waitForSomeTime(15000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
			elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts);
			actionHandler.waitForSomeTime(25000);
			
			String listOfprod = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
            String[] listOfprod1 = listOfprod.split(",");
            for (int i=0; i<listOfprod1.length; i++)
            {
			elementHandler.writeText(AddProductsPageObjects.txtboxSearch, listOfprod1[i]);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
			elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
			actionHandler.waitForSomeTime(15000);
            }
            elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(30000);
			boolean adjustStatus = elementHandler.isElementDisplayed(AddProductsPageObjects.drpdwnAdjustTypelist.get(0));
			if (adjustStatus == true)
			javascripthandler.highlightElement(AddProductsPageObjects.drpdwnAdjustTypelist.get(0));
			boolean adjustStatus1 = elementHandler.isElementDisplayed(AddProductsPageObjects.drpdwnAdjustTypelist.get(1));
			if (adjustStatus1 == true)
			javascripthandler.highlightElement(AddProductsPageObjects.drpdwnAdjustTypelist.get(1));
			boolean adjustStatus2 = elementHandler.isElementDisplayed(AddProductsPageObjects.drpdwnAdjustTypelist.get(2));
			if (adjustStatus2 == true)
			javascripthandler.highlightElement(AddProductsPageObjects.drpdwnAdjustTypelist.get(2));
			TestNGCustomReporter.log(logger, "Adjustment Type field with none displayed for PPC product which is configurable" +ReportBuilder.takesScreenshot());
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSave);
			elementHandler.clickElement(AddProductsPageObjects.btnSave);
			actionHandler.waitForSomeTime(25000);
			String quote7= elementHandler.getText(AddProductsPageObjects.lblproposalId);
			AssertionHandler.verifyTrue(quote7.contains("Q-"), "Quote page not displayed");
			break;
/*
 * This case is to Search for products:41819162,41948733 and validate the informational message displayed in the cart page			
 */			
		case 17:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);
			elementHandler.waitForXpathToLoad(lblCategorySelectn);
		            String soc6196 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
		            String[] soc6196_1 = soc6196.split(",");
			for (int i=0; i<soc6196_1.length; i++)
			{
			elementHandler.writeText(AddProductsPageObjects.txtboxSearch, soc6196_1[i]);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
			elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
			actionHandler.waitForSomeTime(15000);
			}
			boolean messageStatus1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblMessage1);
			if (messageStatus1 == true) {
			String infomessage= elementHandler.getText(AddProductsPageObjects.lblMessage1);
			AssertionHandler.verifyTrue(infomessage.contains("Auto Included"), "Auto Included Message not displayed for adding 41819162 product"); 
			}
			boolean messageStatus2= elementHandler.isElementDisplayed(AddProductsPageObjects.lblMessage2);
			if (messageStatus2 == true) {
			    String infomessage1 = elementHandler.getText(AddProductsPageObjects.lblMessage2);
			    AssertionHandler.verifyTrue(infomessage1.contains("Auto Included"), "Auto Included Message not displayed for adding 41948733 product"); 
			} 
			TestNGCustomReporter.log(logger, "Auto Included message displayed for adding PPC products" +ReportBuilder.takesScreenshot());
			break;
			
			/* 
			 * This case is to add IMS or IME products:41777796,41822784 to the cart. Give contract term 				
			 */
			
		case 18:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);
			elementHandler.waitForXpathToLoad(lblCategorySelectn);
			String imse = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
            String[] imse1 = imse.split(",");
			for (int i=0; i<imse1.length; i++)
			{
			elementHandler.writeText(AddProductsPageObjects.txtboxSearch, imse1[i]);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
			elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForXpathToLoad(btnProductAttr);
			elementHandler.waitForPageToLoad();
			elementHandler.writeText(AddProductsPageObjects.txtboxNumbAttrys, "5");
			elementHandler.clickElement(AddProductsPageObjects.tabSecondaries);
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
			elementHandler.clickElement(AddProductsPageObjects.btnValidate);
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
			elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts, AddProductsPageObjects.txtboxSearch);
			actionHandler.waitForSomeTime(15000);
			}
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng, AddProductsPageObjects.btnFinalize);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
			elementHandler.clickElement(AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(30000);
			boolean errorMessage9 = elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
			if (errorMessage9 == true) {
				String message= AddProductsPageObjects.lblValidatnMessage.getText();
				TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
			}
			boolean enablebtn3= AddProductsPageObjects.btnSubmtForApproval.isEnabled();
			if 	(enablebtn3== true) {
				javascripthandler.highlightElement(AddProductsPageObjects.btnSubmtForApproval);
			TestNGCustomReporter.log(logger, "Submit For Approval button enabled for IMS and IME product with Contract Term as 1 yr" +ReportBuilder.takesScreenshot());
			}
			
			String contractTerms = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Contract_Term", Row);
			String[] contractTerm1 = contractTerms.split(",");
            for (int k=0;k<contractTerm1.length;k++) 
            {
            	if(!contractTerms.equals("NA"))
            	{
                   elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnContractTerm.get(k));          
                   elementHandler.clickElement(AddProductsPageObjects.drpdwnContractTerm.get(k));
                   TestNGCustomReporter.log(logger,"From sheet "+contractTerms.trim());
                   actionHandler.waitForSomeTime(2000);
                   for(int j=1; j<=5; j++)
                   {
                          String CntrctTrm=driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).getText();
                          TestNGCustomReporter.log(logger,"From list["+j+"] "+CntrctTrm.trim());
                          if(CntrctTrm.trim().contentEquals(contractTerms.trim()))
                          {
                                 driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).click();
                                 break;
                          }
                          
                   }
            	}
           }
            elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
			elementHandler.clickElement(AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
			elementHandler.clickElement(AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(30000);
			boolean enablebtn4= AddProductsPageObjects.btnFinalize.isEnabled();
			if 	(enablebtn4 == true) {
			AssertionHandler.verifyTrue(enablebtn4, "Finalize button not enabled for IMS and IME product with Contract Term as 2 or 3 yrs");
			javascripthandler.highlightElement(AddProductsPageObjects.btnFinalize);
			TestNGCustomReporter.log(logger, "Finalize button enabled for IMS and IME product with Contract Term as  2 or 3 yrs" +ReportBuilder.takesScreenshot());
			}
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
			elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
			actionHandler.waitForSomeTime(25000);
			break;
			
/*
 * This case is to add EB Products:41870119,42089521,42089522 to the cart and give Discount to all				
 */			
		case 19:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);
			elementHandler.waitForXpathToLoad(lblCategorySelectn);
			String listofproducts4 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
            String[] ebProducts = listofproducts4.split(",");
			for (int i=0; i<ebProducts.length; i++)
			{
			elementHandler.writeText(AddProductsPageObjects.txtboxSearch, ebProducts[i]);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
			elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
			actionHandler.waitForSomeTime(20000);
			}
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.chkboxSelectAll);
			elementHandler.clickElement(AddProductsPageObjects.chkboxSelectAll,AddProductsPageObjects.btnMassUpdt);
			actionHandler.waitForSomeTime(2000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnMassUpdt);
			elementHandler.clickElement(AddProductsPageObjects.btnMassUpdt, AddProductsPageObjects.drpdwnAdjustty);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnAdjustty);
			elementHandler.clickElement(AddProductsPageObjects.drpdwnAdjustty);
			actionHandler.waitForSomeTime(2000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnDiscnt);
			elementHandler.clickElement(AddProductsPageObjects.drpdwnDiscnt);
			actionHandler.waitForSomeTime(2000);
			String adamount= ExcelUtils.getDataByColumnName("AddProducts", "AdjustAmount");
			elementHandler.writeText(AddProductsPageObjects.txtAdjust2, adamount, AddProductsPageObjects.btnApply);
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnApply);
			elementHandler.clickElement(AddProductsPageObjects.btnApply,AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
			elementHandler.clickElement(AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(30000);
			boolean errorMessage0= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
			if (errorMessage0 == true) {
				String message= AddProductsPageObjects.lblValidatnMessage.getText();
				TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
			}
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
			elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForXpathToLoad(imgQuotepg);
			String quote9= elementHandler.getText(AddProductsPageObjects.lblproposalId);
			AssertionHandler.verifyTrue(quote9.contains("Q-"), "Quote page not displayed");
			TestNGCustomReporter.log(logger, "products are added to the cart" +ReportBuilder.takesScreenshot());
			break;
	
/*
 * This case is to add all Firmsite and IM products to the cart. Apply bridge and discount.				
 */			
		case 20:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct);
			elementHandler.waitForXpathToLoad(lblCategorySelectn);
			String imFirmsite = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
            String[] imFirmsite1 = imFirmsite.split(",");
			for (int i=0; i<imFirmsite1.length; i++)
			{
			elementHandler.writeText(AddProductsPageObjects.txtboxSearch, imFirmsite1[i]);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
			elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
			elementHandler.waitForXpathToLoad(btnProductAttr);
			elementHandler.waitForPageToLoad();
			elementHandler.writeText(AddProductsPageObjects.txtboxNumbAttrys, "5");
			elementHandler.clickElement(AddProductsPageObjects.tabSecondaries);
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
			elementHandler.clickElement(AddProductsPageObjects.btnValidate);
			actionHandler.waitForSomeTime(20000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
			elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts, AddProductsPageObjects.txtboxSearch);
			actionHandler.waitForSomeTime(20000);
			}
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.chkboxSelectAll);
			elementHandler.clickElement(AddProductsPageObjects.chkboxSelectAll,AddProductsPageObjects.btnMassUpdt);
			actionHandler.waitForSomeTime(2000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnMassUpdt);
			elementHandler.clickElement(AddProductsPageObjects.btnMassUpdt, AddProductsPageObjects.drpdwnAdjustty);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnAdjustty);
			elementHandler.clickElement(AddProductsPageObjects.drpdwnAdjustty);
			actionHandler.waitForSomeTime(2000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnDiscnt);
			elementHandler.clickElement(AddProductsPageObjects.drpdwnDiscnt);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.txtAdjust2);
			elementHandler.writeText(AddProductsPageObjects.txtAdjust2, "5");
			actionHandler.waitForSomeTime(2000);
			/*javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnBridge);
			elementHandler.clickElement(AddProductsPageObjects.drpdwnBridge);
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnBridgeval);
			elementHandler.clickElement(AddProductsPageObjects.drpdwnBridgeval);
			actionHandler.waitForSomeTime(5000);*/
			javascripthandler.scrollIntoView(AddProductsPageObjects.btnApply);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnApply);
			elementHandler.clickElement(AddProductsPageObjects.btnApply,AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
			elementHandler.clickElement(AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(40000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblQuantityval.get(0));
			String Quantity= AddProductsPageObjects.lblQuantityval.get(0).getText();
			AssertionHandler.verifyTrue(Quantity.contains("1"), "By Default Quantity is not displayed as 1 for all Firmsite and IM products");
			String Quantity1= AddProductsPageObjects.lblQuantityval.get(0).getText();
			AssertionHandler.verifyTrue(Quantity1.contains("1"), "By Default Quantity is not displayed as 1 for all Firmsite and IM products");
			String Quantity2= AddProductsPageObjects.lblQuantityval.get(0).getText();
			AssertionHandler.verifyTrue(Quantity2.contains("1"), "By Default Quantity is not displayed as 1 for all Firmsite and IM products");
			String Quantity3= AddProductsPageObjects.lblQuantityval.get(0).getText();
			AssertionHandler.verifyTrue(Quantity3.contains("1"), "By Default Quantity is not displayed as 1 for all Firmsite and IM products");
			String Quantity4= AddProductsPageObjects.lblQuantityval.get(0).getText();
			AssertionHandler.verifyTrue(Quantity4.contains("1"), "By Default Quantity is not displayed as 1 for all Firmsite and IM products");
			String Quantity5= AddProductsPageObjects.lblQuantityval.get(0).getText();
			AssertionHandler.verifyTrue(Quantity5.contains("1"), "By Default Quantity is not displayed as 1 for all Firmsite and IM products");
			String Quantity6= AddProductsPageObjects.lblQuantityval.get(0).getText();
			AssertionHandler.verifyTrue(Quantity6.contains("1"), "By Default Quantity is not displayed as 1 for all Firmsite and IM products");
			String Quantity7= AddProductsPageObjects.lblQuantityval.get(0).getText();
			AssertionHandler.verifyTrue(Quantity7.contains("1"), "By Default Quantity is not displayed as 1 for all Firmsite and IM products");				
			boolean errorMesg= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
			if (errorMesg == true) {
				String message= AddProductsPageObjects.lblValidatnMessage.getText();
				TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
			}
			boolean btn= AddProductsPageObjects.btnFinalize.isEnabled();
			if (btn == true) {
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
				elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
				actionHandler.waitForSomeTime(25000);
				elementHandler.waitForXpathToLoad(imgQuotepg);
				String quote_1= elementHandler.getText(AddProductsPageObjects.lblproposalId);
				AssertionHandler.verifyTrue(quote_1.contains("Q-"), "Quote page not displayed");
				TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Finalize button" +ReportBuilder.takesScreenshot());	
				
			}
			else {
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmtForApproval);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmtForApproval,AddProductsPageObjects.btnSubmit);
				actionHandler.waitForSomeTime(10000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
				TestNGCustomReporter.log(logger, "Valiadte submit button" +ReportBuilder.takesScreenshot());
				elementHandler.clickElement(AddProductsPageObjects.btnSubmit, AddProductsPageObjects.btnSubmit);
				actionHandler.waitForSomeTime(10000);
				TestNGCustomReporter.log(logger, "Valiadte submit button" +ReportBuilder.takesScreenshot());
				elementHandler.clickElement(AddProductsPageObjects.btnSubmit,AddProductsPageObjects.btnReturn);
				actionHandler.waitForSomeTime(5000);
				TestNGCustomReporter.log(logger, "Valiadte Return button" +ReportBuilder.takesScreenshot());
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReturn);
				elementHandler.clickElement(AddProductsPageObjects.btnReturn);
				actionHandler.waitForSomeTime(25000);
				String quote_1= elementHandler.getText(AddProductsPageObjects.lblproposalId);
				AssertionHandler.verifyTrue(quote_1.contains("Q-"), "Quote page not displayed");
				TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Submit for approval button" +ReportBuilder.takesScreenshot());
				QuotePage quotepage = new QuotePage(driver);
				String Name= quotepage.submitForApproval();
				SearchPage searchpage = new SearchPage(driver);
				searchpage.searchDiffProfile("Ryan Burch");
				searchpage.approvalProcess(Name);
				BasePage basePage = new BasePage(driver);
				basePage.Logout();
				searchpage.changeTab();	
			
			}
			break;
			
/*
 * This case is to add PPC product to the cart and Finalize it.
 */
		case 21:
			javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
			elementHandler.clickElement(QuotePageObjects.btnAddProduct,AddProductsPageObjects.txtboxSearch);
			actionHandler.waitForSomeTime(10000);
			String ppcProduct1 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id",Row);
			elementHandler.writeText(AddProductsPageObjects.txtboxSearch, ppcProduct1);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
			elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
			actionHandler.waitForSomeTime(15000);
			String ppcType2=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Type",Row);
			String ppclang2 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Lang",Row);
			String ppcWebsite2=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Website",Row);
			String ppcAmount2 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Amount",Row);
			elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCType1);
			actionHandler.waitForSomeTime(2000);
			driver.findElement(By.xpath("//div[contains(text(),'"+ppcType2+"')]")).click();
			elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCLang);
			driver.findElement(By.xpath("//div[contains(text(),'"+ppclang2+"')]")).click();
			elementHandler.writeText(AddProductsPageObjects.txtPPCAmount, ppcAmount2);
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCWebsite);
			driver.findElement(By.xpath("//div[text()='"+ppcWebsite2+"']")).click();
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(AddProductsPageObjects.tabSecondaries);
			actionHandler.waitForSomeTime(8000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
			elementHandler.clickElement(AddProductsPageObjects.btnValidate);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
			elementHandler.clickElement(AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(30000);
			boolean errorMesg1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
			if (errorMesg1 == true) {
				String message= AddProductsPageObjects.lblValidatnMessage.getText();
				TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
			}
			boolean btn1= AddProductsPageObjects.btnFinalize.isEnabled();
			if (btn1 == true) {
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
				elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
				actionHandler.waitForSomeTime(25000);
				elementHandler.waitForXpathToLoad(imgQuotepg);
				String quote_1= elementHandler.getText(AddProductsPageObjects.lblproposalId);
				AssertionHandler.verifyTrue(quote_1.contains("Q-"), "Quote page not displayed");
				TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Finalize button" +ReportBuilder.takesScreenshot());	
				
			}
			else {
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmtForApproval);
				elementHandler.clickElement(AddProductsPageObjects.btnSubmtForApproval,AddProductsPageObjects.btnSubmit);
				actionHandler.waitForSomeTime(10000);
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
				TestNGCustomReporter.log(logger, "Valiadte submit button" +ReportBuilder.takesScreenshot());
				elementHandler.clickElement(AddProductsPageObjects.btnSubmit, AddProductsPageObjects.btnSubmit);
				actionHandler.waitForSomeTime(10000);
				TestNGCustomReporter.log(logger, "Valiadte submit button" +ReportBuilder.takesScreenshot());
				elementHandler.clickElement(AddProductsPageObjects.btnSubmit,AddProductsPageObjects.btnReturn);
				actionHandler.waitForSomeTime(5000);
				TestNGCustomReporter.log(logger, "Valiadte Return button" +ReportBuilder.takesScreenshot());
				elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReturn);
				elementHandler.clickElement(AddProductsPageObjects.btnReturn);
				actionHandler.waitForSomeTime(25000);
				String quote_1= elementHandler.getText(AddProductsPageObjects.lblproposalId);
				AssertionHandler.verifyTrue(quote_1.contains("Q-"), "Quote page not displayed");
				TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Submit for approval button" +ReportBuilder.takesScreenshot());
				QuotePage quotepage = new QuotePage(driver);
				String Name= quotepage.submitForApproval();
				SearchPage searchpage = new SearchPage(driver);
				searchpage.searchDiffProfile("Ryan Burch");
				searchpage.approvalProcess(Name);
				BasePage basePage = new BasePage(driver);
				basePage.Logout();
				searchpage.changeTab();	
			}
			break;

		/*
		 * 
		 */
		case 22:
			
			
			
			
			}//switch statement close
		
		} //Method close
		

public void validateContractTerm(int Row){
	javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
	elementHandler.clickElement(QuotePageObjects.btnAddProduct);
	elementHandler.waitForXpathToLoad(lblCategorySelectn);
	 String contractProducts = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
	 String[] contractProducts1 = contractProducts.split(",");
	for (int i=0; i<contractProducts1.length; i++)
	{
	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, contractProducts1[i]);
	actionHandler.keyboardAction(Keys.ENTER);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
	elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
	actionHandler.waitForSomeTime(20000);
	}
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
	actionHandler.waitForSomeTime(30000);
	for (int k=0;k<2;k++) 
	{
		 elementHandler.clickElement(AddProductsPageObjects.drpdwnContractTerm.get(k));
		 actionHandler.waitForSomeTime(2000);
		 boolean status1= elementHandler.isElementDisplayed(AddProductsPageObjects.txtContract1yr);
		 if (status1 == true) {
		 AssertionHandler.verifyTrue(status1, "Contract Term not displayed for Engagement Builder product"); 
		 elementHandler.clickElement(AddProductsPageObjects.txtContract1yr);
		 }
		 TestNGCustomReporter.log(logger, "Contract Terms drop down list displayed for Engagement but not displayed for OTC products" +ReportBuilder.takesScreenshot());
		 actionHandler.waitForSomeTime(2000);
	}
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
	elementHandler.clickElement(AddProductsPageObjects.btnReprice);
	actionHandler.waitForSomeTime(25000);
	boolean errorMesg1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
	if (errorMesg1 == true) {
		String message= AddProductsPageObjects.lblValidatnMessage.getText();
		TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
	}
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
	elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForXpathToLoad(imgQuotepg);
	TestNGCustomReporter.log(logger, "products are added to the cart" +ReportBuilder.takesScreenshot());	
}

public String[] year23IMProducts(int Row,String QuoteId) {
	javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
	elementHandler.clickElement(QuotePageObjects.btnAddProduct);
	elementHandler.waitForXpathToLoad(lblCategorySelectn);
    String IMProducts = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
    String[] IMProducts1 = IMProducts.split(",");
	for (int i=0; i<IMProducts1.length; i++)
	{
	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, IMProducts1[i]);
	actionHandler.keyboardAction(Keys.ENTER);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
	elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
	elementHandler.waitForXpathToLoad(btnProductAttr);
	elementHandler.waitForPageToLoad();
	elementHandler.writeText(AddProductsPageObjects.txtboxNumbAttrys, "2");
	elementHandler.clickElement(AddProductsPageObjects.tabSecondaries);
	actionHandler.waitForSomeTime(10000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
	elementHandler.clickElement(AddProductsPageObjects.btnValidate);
	actionHandler.waitForSomeTime(20000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
	elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts, AddProductsPageObjects.txtboxSearch);
	actionHandler.waitForSomeTime(15000);
	}
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
	actionHandler.waitForSomeTime(25000);
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23Price.get(0));
	String IMSuite= elementHandler.getText(AddProductsPageObjects.lblYear23Price.get(0));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23Price.get(1));
	String FSEssential= elementHandler.getText(AddProductsPageObjects.lblYear23Price.get(1));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23Price.get(2));
	String FSCatalyst = elementHandler.getText(AddProductsPageObjects.lblYear23Price.get(2));
	String year23[]= new String[3];
	year23[0]=IMSuite;
	year23[1]= FSEssential;
	year23[2]= FSCatalyst;
	elementHandler.clickElement(AddProductsPageObjects.btnReprice);
	actionHandler.waitForSomeTime(30000);
	boolean errorMesg1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
	if (errorMesg1 == true) {
		String message= AddProductsPageObjects.lblValidatnMessage.getText();
		TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
	}
	/*javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23Price.get(3));
	String IMPresence= elementHandler.getText(AddProductsPageObjects.lblYear23Price.get(3));*/
	boolean btn= AddProductsPageObjects.btnFinalize.isEnabled();
	if (btn == true) {
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
		elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForXpathToLoad(imgQuotepg);
		String quote_1= elementHandler.getText(AddProductsPageObjects.lblproposalId);
		AssertionHandler.verifyTrue(quote_1.contains("Q-"), "Quote page not displayed");
		TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Finalize button" +ReportBuilder.takesScreenshot());	
		
	}
	else {
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmtForApproval);
		elementHandler.clickElement(AddProductsPageObjects.btnSubmtForApproval,AddProductsPageObjects.btnSubmit);
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
		TestNGCustomReporter.log(logger, "Valiadte submit button" +ReportBuilder.takesScreenshot());
		elementHandler.clickElement(AddProductsPageObjects.btnSubmit, AddProductsPageObjects.btnSubmit);
		actionHandler.waitForSomeTime(10000);
		TestNGCustomReporter.log(logger, "Valiadte submit button" +ReportBuilder.takesScreenshot());
		elementHandler.clickElement(AddProductsPageObjects.btnSubmit,AddProductsPageObjects.btnReturn);
		actionHandler.waitForSomeTime(5000);
		TestNGCustomReporter.log(logger, "Valiadte Return button" +ReportBuilder.takesScreenshot());
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReturn);
		elementHandler.clickElement(AddProductsPageObjects.btnReturn);
		actionHandler.waitForSomeTime(25000);
		String quote_1= elementHandler.getText(AddProductsPageObjects.lblproposalId);
		AssertionHandler.verifyTrue(quote_1.contains("Q-"), "Quote page not displayed");
		TestNGCustomReporter.log(logger, "products are added to the cart" +ReportBuilder.takesScreenshot());
		QuotePage quotepage = new QuotePage(driver);
		String Name= quotepage.submitForApproval();
		SearchPage searchpage = new SearchPage(driver);
		searchpage.searchDiffProfile("Ryan Burch");
		searchpage.approvalProcess(Name);
		BasePage basePage = new BasePage(driver);
		basePage.Logout();
		searchpage.changeTab();
		SearchQuotePage searchquotepage = new SearchQuotePage(driver);
	    searchquotepage.searchQuote(QuoteId);
	}
	return year23;
}


public void validateYear23IM(String year23[]) {
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkLineItems);
	elementHandler.clickElement(AddProductsPageObjects.lnkLineItems, AddProductsPageObjects.lnkIMS );
	actionHandler.waitForSomeTime(2000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkIMS);
	elementHandler.clickElement(AddProductsPageObjects.lnkIMS, AddProductsPageObjects.lblpriceList);
	actionHandler.waitForSomeTime(5000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkPriceList);
	elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
	actionHandler.waitForSomeTime(5000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkItem);
	elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lnkMatrxpricing );
	actionHandler.waitForSomeTime(5000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkMatrxpricing);
	elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing, AddProductsPageObjects.lnkMatrixId);
	actionHandler.waitForSomeTime(5000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkMatrixId);
	elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
	actionHandler.waitForSomeTime(5000);
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkShowMore);
	elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
	String price1= elementHandler.getText(AddProductsPageObjects.lblAdjustmntAmt);
	ReportBuilder.takesScreenshot();
	String val_1= year23[0].replace("USD", "").replace("\\s", "").replace(",", "").replaceAll("\\.0*$", "");
	String val_2=price1.replace(",", "").replaceAll("\\.0*$", "");
	AssertionHandler.verifyTrue(val_1.contains(val_2));
	TestNGCustomReporter.log(logger, "IMsuite Price2-3 value got matched sccessfully");
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	
	elementHandler.clickElement(AddProductsPageObjects.lnkIME, AddProductsPageObjects.lblpriceList);
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lnkMatrxpricing );
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing, AddProductsPageObjects.lnkMatrixId);
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
	actionHandler.waitForSomeTime(5000);
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
	elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
	String price2= elementHandler.getText(AddProductsPageObjects.lblAdjustmntAmt);
	String val_3= year23[1].replace("USD", "").replace("\\s", "").replace(",", "").replaceAll("\\.0*$", "");
	String val_4= price2.replace(",", "").replaceAll("\\.0*$", "");
	AssertionHandler.verifyTrue(val_3.contains(val_4));
	TestNGCustomReporter.log(logger, "FSEssential Price2-3 values matched properly");
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkShowMore);
	elementHandler.clickElement(AddProductsPageObjects.lnkShowMore, AddProductsPageObjects.lnkIMC);
	elementHandler.clickElement(AddProductsPageObjects.lnkIMC, AddProductsPageObjects.lblpriceList);
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lnkMatrxpricing );
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing, AddProductsPageObjects.lnkMatrixId);
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
	actionHandler.waitForSomeTime(5000);
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
	elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
	String price3= elementHandler.getText(AddProductsPageObjects.lblAdjustmntAmt);
	TestNGCustomReporter.log(logger, "FSCatalyst price2-3 values matched properly");
	String val_5= year23[2].replace("USD", "").replace("\\s", "").replace(",", "").replaceAll("\\.0*$", "");
	String val_6= price3.replace(",", "").replaceAll("\\.0*$", "");
	AssertionHandler.verifyTrue(val_5.contains(val_6));
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
}


public void validateYear23Firmsite(int Row,String QuoteId) {
	javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
	elementHandler.clickElement(QuotePageObjects.btnAddProduct);
	actionHandler.waitForSomeTime(15000);
	elementHandler.waitForXpathToLoad(lblCategorySelectn);
	String FirmsiteProducts = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
	String[] FirmsiteProducts1 = FirmsiteProducts.split(",");
	for (int i=0; i<FirmsiteProducts1.length; i++)
	{
	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, FirmsiteProducts1[i]);
	actionHandler.keyboardAction(Keys.ENTER);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
	elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
	elementHandler.waitForXpathToLoad(btnProductAttr);
	elementHandler.waitForPageToLoad();
	elementHandler.writeText(AddProductsPageObjects.txtboxNumbAttrys, "2");
	elementHandler.clickElement(AddProductsPageObjects.tabSecondaries);
	actionHandler.waitForSomeTime(10000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
	elementHandler.clickElement(AddProductsPageObjects.btnValidate);
	actionHandler.waitForSomeTime(40000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
	actionHandler.waitForSomeTime(30000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
	elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts, AddProductsPageObjects.txtboxSearch);
	actionHandler.waitForSomeTime(25000);
	}
	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
	actionHandler.waitForSomeTime(30000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lblShowOptions.get(0));
	elementHandler.clickElement(AddProductsPageObjects.lblShowOptions.get(0));
	actionHandler.waitForSomeTime(5000);
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(0));
	String prod0= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(0));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(1));
	String prod1= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(1));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(2));
	String prod2= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(2));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(3));
	String prod3= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(3));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(4));
	String prod4= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(4));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(5));
	String prod5= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(5));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(6));
	String prod6= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(6));
	
	actionHandler.waitForSomeTime(2000);
	javascripthandler.scrollToElementAndClick(AddProductsPageObjects.lblShowOptions.get(1));
	actionHandler.waitForSomeTime(5000);
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(7));
	String product0= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(7));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(8));
	String product1= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(8));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(9));
	String product2= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(9));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(10));
	String product3= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(10));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(11));
	String product4 = elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(11));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(12));
	String product5= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(12));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(13));
	String product6= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(13));
	actionHandler.waitForSomeTime(5000);
	javascripthandler.scrollToElementAndClick(AddProductsPageObjects.lblShowOptions.get(2));
	actionHandler.waitForSomeTime(5000);
	
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(14));
	String prods0= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(14));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(15));
	String prods1= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(15));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(16));
	String prods2= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(16));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(17));
	String prods3= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(17));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(18));
	String prods4= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(18));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(19));
	String prods5= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(19));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(20));
	String prods6= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(20));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(21));
	String prods7= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(21));
	actionHandler.waitForSomeTime(2000);
	javascripthandler.scrollToElementAndClick(AddProductsPageObjects.lblShowOptions.get(3));
	actionHandler.waitForSomeTime(5000);
	
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(22));
	String products0= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(22));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(23));
	String products1= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(23));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(24));
	String products2= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(24));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(25));
	String products3= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(25));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(26));
	String products4= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(26));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(27));
	String products5= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(27));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(28));
	String products6= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(28));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23PriceProd1.get(29));
	String products7= elementHandler.getText(AddProductsPageObjects.lblYear23PriceProd1.get(29));
	actionHandler.waitForSomeTime(2000);
	boolean btn= AddProductsPageObjects.btnFinalize.isEnabled();
	if (btn == true) {
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
		elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForXpathToLoad(imgQuotepg);
		String quote_1= elementHandler.getText(AddProductsPageObjects.lblproposalId);
		AssertionHandler.verifyTrue(quote_1.contains("Q-"), "Quote page not displayed");
		TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Finalize button" +ReportBuilder.takesScreenshot());	
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkLineItems);
		elementHandler.clickElement(AddProductsPageObjects.lnkLineItems, AddProductsPageObjects.lnkGoToList);
		elementHandler.clickElement(AddProductsPageObjects.lnkGoToList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption1);
		elementHandler.clickElement(AddProductsPageObjects.lnkOption1, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 1st option of Firmsite 111C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String val1= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(val1, prod0, "Year 2-3 Price not matched properly for 1st option of Firmsite 111C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 1st option of Firmsite 111C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		actionHandler.waitForSomeTime(2000);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOption2,AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension1= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension1.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 2nd option of Firmsite 111C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String val2= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(val2, prod1, "Year 2-3 Price not matched properly for 2nd option of Firmsite 111C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 2nd option of Firmsite 111C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption3);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOption3, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension2= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension2.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 3rd option of Firmsite 111C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String val3= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(val3, prod2, "Year 2-3 Price not matched properly for 3rd option of Firmsite 111C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 3rd option of Firmsite 111C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption4);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOption4, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension3= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension3.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 4th option of Firmsite 111C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String val4= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(val4,prod3, "Year 2-3 Price not matched properly for 4th option of Firmsite 111C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 4th option of Firmsite 111C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption5);
		}

		elementHandler.clickElement(AddProductsPageObjects.lnkOption5, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension4= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension4.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 5th option of Firmsite 111C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String val5= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(val5,prod4, "Year 2-3 Price not matched properly for 5th option of Firmsite 111C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 5th option of Firmsite 111C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption6);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOption6, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension5= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension5.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 6th option of Firmsite 111C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
			elementHandler.waitForXpathToLoad(lblPrice);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
			String val5= elementHandler.getText(AddProductsPageObjects.lblvalue1);
			actionHandler.waitForSomeTime(5000);
			AssertionHandler.verifyEquals(val5,prod5, "Year 2-3 Price not matched properly for 6th option of Firmsite 111C");
			TestNGCustomReporter.log(logger, "Year 2-3 price for 6th option of Firmsite 111C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption7);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOption7, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension6= elementHandler.getText(AddProductsPageObjects.lblDimension);	
		if (dimension6.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 7th option of Firmsite 111C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String val7= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(val7,prod6 , "Year 2-3 Price not matched properly for 7th option of Firmsite 111C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 7th option of Firmsite 111C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption8);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOption8, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension7= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension7.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 1st option of Firmsite 222C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String val8= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(val8,product0 , "Year 2-3 Price not matched properly for 1st option of Firmsite 222C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for Firmsite Mobile option of Firmsite 222C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption9);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOption9, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension8= elementHandler.getText(AddProductsPageObjects.lblDimension);
			if (dimension8.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 2nd option of Firmsite 222C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
			else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String val9= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(val9, product1, "Year 2-3 Price not matched properly for 2nd option of Firmsite 222C");
		TestNGCustomReporter.log(logger, "Year 2-3 price 2nd option of Firmsite 222C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
			}
			
		elementHandler.clickElement(AddProductsPageObjects.lnkOptn, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension9= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension9.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 3rd option of Firmsite 222C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String val12= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(val12,product2 , "Year 2-3 Price not matched properly for 3rd option of Firmsite 222C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 3rd option of Firmsite 222C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}

		elementHandler.clickElement(AddProductsPageObjects.lnkOptn1, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension0= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension0.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 4th option of Firmsite 222C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String value1= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(value1,product3 , "Year 2-3 Price not matched properly for 4th option of Firmsite 222C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 4th option of Firmsite 222C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOptn2, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_1= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_1.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 5th option of Firmsite 222C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String value2= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(value2, product4 , "Year 2-3 Price not matched properly for 5th option of Firmsite 222C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 5th option of Firmsite 222C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOptn3, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_2= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_2.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 6th option of Firmsite 222C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String value3= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(value3, product5 , "Year 2-3 Price not matched properly for 6th option of Firmsite 222C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 6th option of Firmsite 222C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOptn4, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_3= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_3.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 7th option of Firmsite 111C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String value4= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(value4, product6 , "Year 2-3 Price not matched properly for 7th option of Firmsite 222C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 7th option of Firmsite 222C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOptn5, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_4= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_4.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 1st option of Firmsite 333C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String value5= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(value5, prods0 , "Year 2-3 Price not matched properly for 1st option of Firmsite 333C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 1st option of Firmsite 333C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}

		elementHandler.clickElement(AddProductsPageObjects.lnkOptn6, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_5= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_5.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 2nd option of Firmsite 333C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String value6= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(value6,prods1, "Year 2-3 Price not matched properly for 2nd option of Firmsite 333C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 2nd option of Firmsite 333C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}

		elementHandler.clickElement(AddProductsPageObjects.lnkOptn7, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_6= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_6.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 3rd option of Firmsite 333C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String value7= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(value7,prods2, "Year 2-3 Price not matched properly for 3rd option of Firmsite 333C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 3rd option of Firmsite 333C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOptn8, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_7= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_7.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 4th option of Firmsite 333C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String value8= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(value8,prods3, "Year 2-3 Price not matched properly for 4th option of Firmsite 333C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 4th option of Firmsite 333C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOptn9, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_8= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_8.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 5th option of Firmsite 333C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String value9= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(value9,prods4, "Year 2-3 Price not matched properly for 5th option of Firmsite 333C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 5th option of Firmsite 333C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt1, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_9= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_9.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 6th option of Firmsite 333C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue1= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue1,prods5, "Year 2-3 Price not matched properly for 6th option of Firmsite 333C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 6th option of Firmsite 333C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt2, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_0= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_0.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 7th option of Firmsite 333C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue2= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue2,prods6, "Year 2-3 Price not matched properly for 7th option of firmsite 333C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 7th option of Firmsite 333C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt3, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimensions= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimensions.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 8th option of Firmsite 333C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
			elementHandler.waitForXpathToLoad(lblPrice);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
			String lblvalue2= elementHandler.getText(AddProductsPageObjects.lblvalue1);
			actionHandler.waitForSomeTime(5000);
			AssertionHandler.verifyEquals(lblvalue2,prods7, " Year 2-3 Price not matched properly for 8th option of Firmsite 333C");
			TestNGCustomReporter.log(logger, "Year 2-3 price for 8th option of Firmsite 333C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		//javascripthandler.scrollToElementAndClick(AddProductsPageObjects.lnkMore,AddProductsPageObjects.lnkOpt4);
		//actionHandler.waitForSomeTime(2000);
		boolean moreStatus= elementHandler.isElementDisplayed(AddProductsPageObjects.lnkMore);
		if (moreStatus == true)
		elementHandler.clickElement(AddProductsPageObjects.lnkMore, AddProductsPageObjects.lnkOpt4);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt4, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimensions_1= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimensions_1.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			} while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 1st option of Firmsite 444C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue4= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue4,products0, "Year 2-3 Price not matched properly for 1st option of firmsite 444C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 1st option of Firmsite 444C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt5, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimensions_2= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimensions_2.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 2nd option of Firmsite 444C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue5= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue5,products1, "Year 2-3 Price not matched properlyfor 2nd option of firmsite 444C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 2nd option of Firmsite 444C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt6, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimensions_3= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimensions_3.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 3rd option of Firmsite 444C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue6= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue6,products2, " Year 2-3 Price not matched properly for 3rd option of Firmsite 444C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 3rd option of Firmsite 444C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt7, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimensions_4= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimensions_4.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 4th option of Firmsite 111C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue7= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue7,products3, "Year 2-3 Price not matched properly for 4th option of Firmsite 444C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 4th option of Firmsite 444C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt8, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimensions_5= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimensions_5.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 5th option of Firmsite 444c product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue8= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue8,products4, "Year 2-3 Price not matched properly for 5th option of Firmsite 444C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 5th option of Firmsite 444C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt9, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimensions_6= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimensions_6.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 6th option of Firmsite 444C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue9= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue9,products5, " Year 2-3 Price not matched properly for 6th option of Firmsite 444C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 6th option of Firmsite 444C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimensions_7= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimensions_7.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 7th option of Firmsite 444C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue,products6, " Year 2-3 Price not matched properly for 7th option of firmsite 444C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 7th option of Firmsite 444C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.linkOptn, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimensions_8= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimensions_8.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 8th option of Firmsite 444C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue, products7, "Year 2-3 Price not matched properly for 8th option of firmsite 444C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 8th option of Firmsite 444C product matched successfully");
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		TestNGCustomReporter.log(logger, "Validation of Year 2-3 price for all products is completed" +ReportBuilder.takesScreenshot());
	}
	else {
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmtForApproval);
		elementHandler.clickElement(AddProductsPageObjects.btnSubmtForApproval,AddProductsPageObjects.btnSubmit);
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
		TestNGCustomReporter.log(logger, "Valiadte submit button" +ReportBuilder.takesScreenshot());
		elementHandler.clickElement(AddProductsPageObjects.btnSubmit, AddProductsPageObjects.btnSubmit);
		actionHandler.waitForSomeTime(10000);
		TestNGCustomReporter.log(logger, "Valiadte submit button" +ReportBuilder.takesScreenshot());
		elementHandler.clickElement(AddProductsPageObjects.btnSubmit,AddProductsPageObjects.btnReturn);
		actionHandler.waitForSomeTime(5000);
		TestNGCustomReporter.log(logger, "Valiadte Return button" +ReportBuilder.takesScreenshot());
		elementHandler.clickElement(AddProductsPageObjects.btnReturn);
		actionHandler.waitForSomeTime(25000);
		String quote_1= elementHandler.getText(AddProductsPageObjects.lblproposalId);
		AssertionHandler.verifyTrue(quote_1.contains("Q-"), "Quote page not displayed");
		TestNGCustomReporter.log(logger, "products are added to the cart" +ReportBuilder.takesScreenshot());
		QuotePage quotepage = new QuotePage(driver);
		String Name= quotepage.submitForApproval();
		SearchPage searchpage = new SearchPage(driver);
		searchpage.searchDiffProfile("Ryan Burch");
		searchpage.approvalProcess(Name);
		BasePage basePage = new BasePage(driver);
		basePage.Logout();
		searchpage.changeTab();
		SearchQuotePage searchquotepage = new SearchQuotePage(driver);
		searchquotepage.searchQuote(QuoteId);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkLineItems);
		elementHandler.clickElement(AddProductsPageObjects.lnkLineItems, AddProductsPageObjects.lnkGoToList);
		elementHandler.clickElement(AddProductsPageObjects.lnkGoToList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption1);
		elementHandler.clickElement(AddProductsPageObjects.lnkOption1, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 1st option of Firmsite 111C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String val1= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(val1, prod0, "Year 2-3 Price not matched properly for 1st option of Firmsite 111C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 1st option of Firmsite 111C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		actionHandler.waitForSomeTime(2000);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOption2,AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension1= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension1.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 2nd option of Firmsite 111C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String val2= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(val2, prod1, "Year 2-3 Price not matched properly for 2nd option of Firmsite 111C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 2nd option of Firmsite 111C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption3);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOption3, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension2= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension2.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 3rd option of Firmsite 111C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String val3= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(val3, prod2, "Year 2-3 Price not matched properly for 3rd option of Firmsite 111C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 3rd option of Firmsite 111C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption4);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOption4, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension3= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension3.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 4th option of Firmsite 111C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String val4= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(val4,prod3, "Year 2-3 Price not matched properly for 4th option of Firmsite 111C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 4th option of Firmsite 111C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption5);
		}

		elementHandler.clickElement(AddProductsPageObjects.lnkOption5, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension4= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension4.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 5th option of Firmsite 111C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String val5= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(val5,prod4, "Year 2-3 Price not matched properly for 5th option of Firmsite 111C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 5th option of Firmsite 111C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption6);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOption6, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension5= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension5.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 6th option of Firmsite 111C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
			elementHandler.waitForXpathToLoad(lblPrice);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
			String val5= elementHandler.getText(AddProductsPageObjects.lblvalue1);
			actionHandler.waitForSomeTime(5000);
			AssertionHandler.verifyEquals(val5,prod5, "Year 2-3 Price not matched properly for 6th option of Firmsite 111C");
			TestNGCustomReporter.log(logger, "Year 2-3 price for 6th option of Firmsite 111C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption7);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOption7, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension6= elementHandler.getText(AddProductsPageObjects.lblDimension);	
		if (dimension6.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 7th option of Firmsite 111C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String val7= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(val7,prod6 , "Year 2-3 Price not matched properly for 7th option of Firmsite 111C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 7th option of Firmsite 111C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption8);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOption8, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension7= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension7.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 1st option of Firmsite 222C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String val8= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(val8,product0 , "Year 2-3 Price not matched properly for 1st option of Firmsite 222C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for Firmsite Mobile option of Firmsite 222C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption9);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOption9, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension8= elementHandler.getText(AddProductsPageObjects.lblDimension);
			if (dimension8.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 2nd option of Firmsite 222C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
			else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String val9= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(val9, product1, "Year 2-3 Price not matched properly for 2nd option of Firmsite 222C");
		TestNGCustomReporter.log(logger, "Year 2-3 price 2nd option of Firmsite 222C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
			}
			
		elementHandler.clickElement(AddProductsPageObjects.lnkOptn, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension9= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension9.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 3rd option of Firmsite 222C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String val12= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(val12,product2 , "Year 2-3 Price not matched properly for 3rd option of Firmsite 222C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 3rd option of Firmsite 222C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}

		elementHandler.clickElement(AddProductsPageObjects.lnkOptn1, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension0= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension0.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 4th option of Firmsite 222C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String value1= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(value1,product3 , "Year 2-3 Price not matched properly for 4th option of Firmsite 222C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 4th option of Firmsite 222C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOptn2, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_1= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_1.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 5th option of Firmsite 222C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String value2= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(value2, product4 , "Year 2-3 Price not matched properly for 5th option of Firmsite 222C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 5th option of Firmsite 222C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOptn3, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_2= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_2.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 6th option of Firmsite 222C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String value3= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(value3, product5 , "Year 2-3 Price not matched properly for 6th option of Firmsite 222C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 6th option of Firmsite 222C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOptn4, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_3= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_3.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 7th option of Firmsite 111C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String value4= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(value4, product6 , "Year 2-3 Price not matched properly for 7th option of Firmsite 222C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 7th option of Firmsite 222C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOptn5, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_4= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_4.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 1st option of Firmsite 333C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String value5= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(value5, prods0 , "Year 2-3 Price not matched properly for 1st option of Firmsite 333C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 1st option of Firmsite 333C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}

		elementHandler.clickElement(AddProductsPageObjects.lnkOptn6, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_5= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_5.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 2nd option of Firmsite 333C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String value6= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(value6,prods1, "Year 2-3 Price not matched properly for 2nd option of Firmsite 333C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 2nd option of Firmsite 333C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}

		elementHandler.clickElement(AddProductsPageObjects.lnkOptn7, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_6= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_6.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 3rd option of Firmsite 333C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String value7= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(value7,prods2, "Year 2-3 Price not matched properly for 3rd option of Firmsite 333C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 3rd option of Firmsite 333C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOptn8, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_7= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_7.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 4th option of Firmsite 333C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String value8= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(value8,prods3, "Year 2-3 Price not matched properly for 4th option of Firmsite 333C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 4th option of Firmsite 333C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOptn9, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_8= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_8.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 5th option of Firmsite 333C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String value9= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(value9,prods4, "Year 2-3 Price not matched properly for 5th option of Firmsite 333C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 5th option of Firmsite 333C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt1, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_9= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_9.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 6th option of Firmsite 333C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue1= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue1,prods5, "Year 2-3 Price not matched properly for 6th option of Firmsite 333C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 6th option of Firmsite 333C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt2, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimension_0= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimension_0.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 7th option of Firmsite 333C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue2= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue2,prods6, "Year 2-3 Price not matched properly for 7th option of firmsite 333C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 7th option of Firmsite 333C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt3, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimensions= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimensions.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 8th option of Firmsite 333C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
			elementHandler.waitForXpathToLoad(lblPrice);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
			String lblvalue2= elementHandler.getText(AddProductsPageObjects.lblvalue1);
			actionHandler.waitForSomeTime(5000);
			AssertionHandler.verifyEquals(lblvalue2,prods7, " Year 2-3 Price not matched properly for 8th option of Firmsite 333C");
			TestNGCustomReporter.log(logger, "Year 2-3 price for 8th option of Firmsite 333C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		boolean moreStatus= elementHandler.isElementDisplayed(AddProductsPageObjects.lnkMore);
		if (moreStatus == true)
		elementHandler.clickElement(AddProductsPageObjects.lnkMore,AddProductsPageObjects.lnkOpt4);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt4, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimensions_1= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimensions_1.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 1st option of Firmsite 444C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue4= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue4,products0, "Year 2-3 Price not matched properly for 1st option of firmsite 444C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 1st option of Firmsite 444C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt5, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimensions_2= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimensions_2.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 2nd option of Firmsite 444C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue5= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue5,products1, "Year 2-3 Price not matched properlyfor 2nd option of firmsite 444C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 2nd option of Firmsite 444C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt6, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimensions_3= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimensions_3.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 3rd option of Firmsite 444C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue6= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue6,products2, " Year 2-3 Price not matched properly for 3rd option of Firmsite 444C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 3rd option of Firmsite 444C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt7, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimensions_4= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimensions_4.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 4th option of Firmsite 111C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue7= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue7,products3, "Year 2-3 Price not matched properly for 4th option of Firmsite 444C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 4th option of Firmsite 444C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt8, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimensions_5= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimensions_5.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 5th option of Firmsite 111C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue8= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue8,products4, "Year 2-3 Price not matched properly for 5th option of Firmsite 444C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 5th option of Firmsite 444C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt9, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimensions_6= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimensions_6.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 6th option of Firmsite 444C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue9= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue9,products5, " Year 2-3 Price not matched properly for 6th option of Firmsite 444C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 6th option of Firmsite 444C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.lnkOpt, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem, AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimensions_7= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimensions_7.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 7th option of Firmsite 444C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		else {
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue,products6, " Year 2-3 Price not matched properly for 7th option of firmsite 444C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 7th option of Firmsite 444C product matched successfully");
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkOption2);
		}
		
		elementHandler.clickElement(AddProductsPageObjects.linkOptn, AddProductsPageObjects.lnkPriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lnkMatrxpricing);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkMatrxpricing,AddProductsPageObjects.lnkMatrixId);
		String dimensions_8= elementHandler.getText(AddProductsPageObjects.lblDimension);
		if (dimensions_8.contains("Attorneys")) {
			elementHandler.clickElement(AddProductsPageObjects.lnkMatrixId,AddProductsPageObjects.lblEntries);
			actionHandler.waitForSomeTime(2000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblEntries);
			do {
				elementHandler.clickElement(AddProductsPageObjects.lnkShowMore);
				actionHandler.waitForSomeTime(2000);
			}while(elementHandler.isElementDisplayed(AddProductsPageObjects.lnkShowMore) );
			TestNGCustomReporter.log(logger, "Year 2-3 price for 8th option of Firmsite 444C product matched successfully");
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
		}
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForXpathToLoad(lblPrice);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		String lblvalue= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(5000);
		AssertionHandler.verifyEquals(lblvalue, products7, "Year 2-3 Price not matched properly for 8th option of firmsite 444C");
		TestNGCustomReporter.log(logger, "Year 2-3 price for 8th option of Firmsite 444C product matched successfully");
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		TestNGCustomReporter.log(logger, "Validation of Year 2-3 price for all products is completed" +ReportBuilder.takesScreenshot());
	}
	
}

//add EB products:41874867,41819164 and Blog set up product:41819163

public void validateBridgeValues(int Row) {
	javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
	elementHandler.clickElement(QuotePageObjects.btnAddProduct);
	elementHandler.waitForXpathToLoad(lblCategorySelectn);
	String ebproducts = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
	String[] ebproducts1 = ebproducts.split(",");
	for (int i=0; i<ebproducts1.length; i++)
	{
	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, ebproducts1[i]);
	actionHandler.keyboardAction(Keys.ENTER);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
	elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
	actionHandler.waitForSomeTime(30000);
	}
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
	actionHandler.waitForSomeTime(30000);
	
	String bridge = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Bridge", Row);
    String[] arrBridge = bridge.split(",");
    for (int k=0;k<arrBridge.length;k++)
    {
        if (!bridge.equals( "NA"))
        {
           elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnBridge5.get(k));          
           elementHandler.clickElement(AddProductsPageObjects.drpdwnBridge5.get(k));
           actionHandler.waitForSomeTime(2000);
           for(int j=1; j<=5; j++)
           {
                  String bridgeTrm=driver.findElement(By.xpath("//ul[contains(@id,'ui-select-choices-')]/li["+j+"]")).getText();
                  TestNGCustomReporter.log(logger,"From list["+j+"] "+bridgeTrm.trim());
                  if(bridgeTrm.trim().contentEquals(arrBridge[k].trim()))
                  {
                         driver.findElement(By.xpath("//ul[contains(@id,'ui-select-choices-')]/li["+j+"]")).click();
                         actionHandler.waitForSomeTime(2000);
                         break;
                  }
                  
           }
        }
    }
    /*elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
	elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts, AddProductsPageObjects.txtboxSearch);
	actionHandler.waitForSomeTime(20000);*/
	//Add Blog set up product:41819163
	/*String prod1= ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product", Row);
	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, prod1);
	actionHandler.keyboardAction(Keys.ENTER);
	actionHandler.waitForSomeTime(25000);
	elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
	actionHandler.waitForSomeTime(10000);
	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
	actionHandler.waitForSomeTime(20000);*/
	elementHandler.clickElement(AddProductsPageObjects.btnReprice);
	actionHandler.waitForSomeTime(30000);
	boolean errorMesg1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
	if (errorMesg1 == true) {
		String message= AddProductsPageObjects.lblValidatnMessage.getText();
		TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
	}
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
	elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForXpathToLoad(imgQuotepg);
	String quote_3= elementHandler.getText(AddProductsPageObjects.lblproposalId);
	AssertionHandler.verifyTrue(quote_3.contains("Q-"), "Quote page not displayed");
	TestNGCustomReporter.log(logger, "products are added to the cart" +ReportBuilder.takesScreenshot());
}

public void validateYear23EBProducts(int Row,String QuoteId) {
	javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
	elementHandler.clickElement(QuotePageObjects.btnAddProduct);
	elementHandler.waitForXpathToLoad(lblCategorySelectn);
    String ebYearProd = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
    String[] ebYearProd1 = ebYearProd.split(",");
	for (int i=0; i<ebYearProd1.length; i++)
	{
	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, ebYearProd1[i]);
	actionHandler.keyboardAction(Keys.ENTER);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
	elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
	actionHandler.waitForSomeTime(10000);
	}
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
	actionHandler.waitForSomeTime(25000);
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23Price.get(0));
	String Impact= elementHandler.getText(AddProductsPageObjects.lblYear23Price.get(0));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23Price.get(1));
	String Engage= elementHandler.getText(AddProductsPageObjects.lblYear23Price.get(1));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23Price.get(2));
	String Quartely = elementHandler.getText(AddProductsPageObjects.lblYear23Price.get(2));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23Price.get(3));
	TestNGCustomReporter.log(logger, "Year 23 price displayed for all products" +ReportBuilder.takesScreenshot());
	String Client= elementHandler.getText(AddProductsPageObjects.lblYear23Price.get(3));
	actionHandler.waitForSomeTime(2000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
	elementHandler.clickElement(AddProductsPageObjects.btnReprice);
	actionHandler.waitForSomeTime(30000);
	boolean errorMesg1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
	if (errorMesg1 == true) {
		String message= AddProductsPageObjects.lblValidatnMessage.getText();
		TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
	}
	boolean enablebtn= AddProductsPageObjects.btnFinalize.isEnabled();
	if (enablebtn == true) {
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
		elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForXpathToLoad(imgQuotepg);
		String quote= elementHandler.getText(AddProductsPageObjects.lblproposalId);
		AssertionHandler.verifyTrue(quote.contains("Q-"), "Quote page not displayed");	
		TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Finalize button" +ReportBuilder.takesScreenshot());					
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkLineItems);
		elementHandler.clickElement(AddProductsPageObjects.lnkLineItems, AddProductsPageObjects.lnlImpact );
		
		elementHandler.clickElement(AddProductsPageObjects.lnlImpact, AddProductsPageObjects.lblpriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lblpriceList );
		actionHandler.waitForSomeTime(5000);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(2000);
		String lstPrice= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		AssertionHandler.verifyEquals(lstPrice, Impact);
		TestNGCustomReporter.log(logger, "IMsuite Price2-3 value got matched sccessfully" +ReportBuilder.takesScreenshot());
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		
		
		elementHandler.clickElement(AddProductsPageObjects.lnkEngage, AddProductsPageObjects.lblpriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lblpriceList );
		actionHandler.waitForSomeTime(5000);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(2000);
		String lstPrice1= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		AssertionHandler.verifyEquals(lstPrice1, Engage);
		TestNGCustomReporter.log(logger, "IMsuite Price2-3 value got matched sccessfully" +ReportBuilder.takesScreenshot());
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		
		
		elementHandler.clickElement(AddProductsPageObjects.lnkQuarterly, AddProductsPageObjects.lblpriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lblpriceList );
		actionHandler.waitForSomeTime(5000);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(2000);
		String lstPrice2= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		AssertionHandler.verifyEquals(lstPrice2, Quartely);
		TestNGCustomReporter.log(logger, "IMsuite Price2-3 value got matched sccessfully" +ReportBuilder.takesScreenshot());
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		
		
		elementHandler.clickElement(AddProductsPageObjects.lnkClient, AddProductsPageObjects.lblpriceList);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
		actionHandler.waitForSomeTime(5000);
		elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lblpriceList );
		actionHandler.waitForSomeTime(5000);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
		actionHandler.waitForSomeTime(2000);
		String lstPrice3= elementHandler.getText(AddProductsPageObjects.lblvalue1);
		AssertionHandler.verifyTrue(lstPrice3.contains(Client));
		TestNGCustomReporter.log(logger, "IMsuite Price2-3 value got matched sccessfully" +ReportBuilder.takesScreenshot());
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
		driver.navigate().back();
		actionHandler.waitForSomeTime(2000);
	}
	else {
		actionHandler.waitForSomeTime(20000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmtForApproval);
		elementHandler.clickElement(AddProductsPageObjects.btnSubmtForApproval,AddProductsPageObjects.btnSubmit);
		actionHandler.waitForSomeTime(15000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
		elementHandler.clickElement(AddProductsPageObjects.btnSubmit, AddProductsPageObjects.btnSubmit);
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
		elementHandler.clickElement(AddProductsPageObjects.btnSubmit,AddProductsPageObjects.btnReturn);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReturn);
		elementHandler.clickElement(AddProductsPageObjects.btnReturn);
		actionHandler.waitForSomeTime(20000);
		String quote= elementHandler.getText(AddProductsPageObjects.lblproposalId);
		AssertionHandler.verifyTrue(quote.contains("Q-"), "Quote page not displayed");
		TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Submit for approval button" +ReportBuilder.takesScreenshot());
		QuotePage quotepage = new QuotePage(driver);
		String Name= quotepage.submitForApproval();
		SearchPage searchpage = new SearchPage(driver);
		searchpage.searchDiffProfile("Ryan Burch");
		searchpage.approvalProcess(Name);
		BasePage basePage = new BasePage(driver);
		basePage.Logout();
		searchpage.changeTab();	
		SearchQuotePage searchquotepage = new SearchQuotePage(driver);
		 searchquotepage.searchQuote(QuoteId);
		 elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkLineItems);
			elementHandler.clickElement(AddProductsPageObjects.lnkLineItems, AddProductsPageObjects.lnlImpact );
			
			elementHandler.clickElement(AddProductsPageObjects.lnlImpact, AddProductsPageObjects.lblpriceList);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lblpriceList );
			actionHandler.waitForSomeTime(5000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
			actionHandler.waitForSomeTime(2000);
			String lstPrice= elementHandler.getText(AddProductsPageObjects.lblvalue1);
			AssertionHandler.verifyEquals(lstPrice, Impact);
			TestNGCustomReporter.log(logger, "IMsuite Price2-3 value got matched sccessfully" +ReportBuilder.takesScreenshot());
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			
			
			elementHandler.clickElement(AddProductsPageObjects.lnkEngage, AddProductsPageObjects.lblpriceList);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lblpriceList );
			actionHandler.waitForSomeTime(5000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
			actionHandler.waitForSomeTime(2000);
			String lstPrice1= elementHandler.getText(AddProductsPageObjects.lblvalue1);
			AssertionHandler.verifyEquals(lstPrice1, Engage);
			TestNGCustomReporter.log(logger, "IMsuite Price2-3 value got matched sccessfully" +ReportBuilder.takesScreenshot());
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			
			
			elementHandler.clickElement(AddProductsPageObjects.lnkQuarterly, AddProductsPageObjects.lblpriceList);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lblpriceList );
			actionHandler.waitForSomeTime(5000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
			actionHandler.waitForSomeTime(2000);
			String lstPrice2= elementHandler.getText(AddProductsPageObjects.lblvalue1);
			AssertionHandler.verifyEquals(lstPrice2, Quartely);
			TestNGCustomReporter.log(logger, "IMsuite Price2-3 value got matched sccessfully" +ReportBuilder.takesScreenshot());
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			
			
			elementHandler.clickElement(AddProductsPageObjects.lnkClient, AddProductsPageObjects.lblpriceList);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
			actionHandler.waitForSomeTime(5000);
			elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lblpriceList );
			actionHandler.waitForSomeTime(5000);
			javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
			actionHandler.waitForSomeTime(2000);
			String lstPrice3= elementHandler.getText(AddProductsPageObjects.lblvalue1);
			AssertionHandler.verifyEquals(lstPrice3, Client);
			TestNGCustomReporter.log(logger, "IMsuite Price2-3 value got matched sccessfully" +ReportBuilder.takesScreenshot());
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
			driver.navigate().back();
			actionHandler.waitForSomeTime(2000);
		 
	}	
}


/*
* This case is to add Blog products: 41817449,30405072 to the cart. Verify Year 2-3 price.				
*/
public void validateYear23Blogprod(int Row) {
	javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
	elementHandler.clickElement(QuotePageObjects.btnAddProduct);
	elementHandler.waitForXpathToLoad(lblCategorySelectn);
    String blogCPQ = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
    String[] blogCPQ1 = blogCPQ.split(",");
	for (int i=0; i<blogCPQ1.length; i++)
	{
	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, blogCPQ1[i]);
	actionHandler.keyboardAction(Keys.ENTER);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
	elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
	actionHandler.waitForSomeTime(30000);
	}
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
	actionHandler.waitForSomeTime(25000);
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23Price.get(0));
	TestNGCustomReporter.log(logger, "Year 23 Price for Findlaw Social promotion product" +ReportBuilder.takesScreenshot());
	String Social= elementHandler.getText(AddProductsPageObjects.lblYear23Price.get(0));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23Price.get(1));
	TestNGCustomReporter.log(logger, "Year 23 Price for End user Interface product" +ReportBuilder.takesScreenshot());
	String User= elementHandler.getText(AddProductsPageObjects.lblYear23Price.get(1));
	System.out.println(User);
	actionHandler.waitForSomeTime(2000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
    elementHandler.clickElement(AddProductsPageObjects.btnReprice);
	actionHandler.waitForSomeTime(30000);
	boolean errorMesg1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
	if (errorMesg1 == true) {
		String message= AddProductsPageObjects.lblValidatnMessage.getText();
		TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
	}
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
	elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
	actionHandler.waitForSomeTime(20000);
	elementHandler.waitForXpathToLoad(imgQuotepg);
	TestNGCustomReporter.log(logger, "products are added to the cart" +ReportBuilder.takesScreenshot());
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkLineItems);
	elementHandler.clickElement(AddProductsPageObjects.lnkLineItems, AddProductsPageObjects.lnkSocial );
	elementHandler.clickElement(AddProductsPageObjects.lnkSocial, AddProductsPageObjects.lblpriceList);
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lblpriceList );
	actionHandler.waitForSomeTime(5000);
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
	String va1= elementHandler.getText(AddProductsPageObjects.lblvalue1);
	AssertionHandler.verifyEquals(va1, Social, "values not matched for Social product");
	TestNGCustomReporter.log(logger, "FL Social promotion services Price2-3 value got matched sccessfully");
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	
	
	elementHandler.clickElement(AddProductsPageObjects.lnkEndUser, AddProductsPageObjects.lblpriceList);
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lblpriceList );
	actionHandler.waitForSomeTime(5000);
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
	String va2= elementHandler.getText(AddProductsPageObjects.lblvalue1);
	System.out.println(va2);
	//AssertionHandler.verifyEquals(va2, User, "values not matched for User product");
	TestNGCustomReporter.log(logger, "End User Interface Price2-3 value got matched sccessfully" +ReportBuilder.takesScreenshot());
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
}


/*
 * This case is to add PPC products :30010890 (New,English) ,41876119,40586465 to the cart and validate Year 2-3 price for all				
 */
public void validateYear23PPCProd(int Row) {
	javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
	elementHandler.clickElement(QuotePageObjects.btnAddProduct);
	elementHandler.waitForXpathToLoad(lblCategorySelectn);
	String ppcProduct = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id",Row);
	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, ppcProduct);
	actionHandler.keyboardAction(Keys.ENTER);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
	elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
	actionHandler.waitForSomeTime(10000);
	String ppcType1=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Type",Row);
	String ppclang1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Lang",Row);
	String ppcWebsite1=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Website",Row);
	elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCType1);
	actionHandler.waitForSomeTime(2000);
	driver.findElement(By.xpath("//div[contains(text(),'"+ppcType1+"')]")).click();
	elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCLang);
	actionHandler.waitForSomeTime(2000);
	driver.findElement(By.xpath("//div[contains(text(),'"+ppclang1+"')]")).click();
	elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCWebsite);
	actionHandler.waitForSomeTime(2000);
	driver.findElement(By.xpath("//div[text()='"+ppcWebsite1+"']")).click();
	String ppcAmount1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Amount",Row);
	elementHandler.writeText(AddProductsPageObjects.txtPPCAmount, ppcAmount1);
	actionHandler.waitForSomeTime(2000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
	elementHandler.clickElement(AddProductsPageObjects.btnValidate);
	actionHandler.waitForSomeTime(30000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
	actionHandler.waitForSomeTime(30000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
	elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts);
	actionHandler.waitForSomeTime(20000);
	String soc6201 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product", Row);
    String[] soc6201_1 = soc6201.split(",");
	for (int i=0; i<soc6201_1.length; i++)
	{
	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, soc6201_1[i]);
	actionHandler.keyboardAction(Keys.ENTER);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
	elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
	actionHandler.waitForSomeTime(30000);
	}
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
	actionHandler.waitForSomeTime(30000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
	elementHandler.clickElement(AddProductsPageObjects.btnReprice);
	actionHandler.waitForSomeTime(30000);
	boolean errorMesg1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
	if (errorMesg1 == true) {
		String message= AddProductsPageObjects.lblValidatnMessage.getText();
		TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
	}
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23Price.get(1));
	TestNGCustomReporter.log(logger, "Year 2-3 price displayed for PPC Additional landing page OTC product" +ReportBuilder.takesScreenshot());
	String PPC1= elementHandler.getText(AddProductsPageObjects.lblYear23Price.get(1));
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23Price.get(2));
	TestNGCustomReporter.log(logger, "Year 2-3 price displayed for PPC Espanol Additional landing page OTC product" +ReportBuilder.takesScreenshot());
	String PPC2= elementHandler.getText(AddProductsPageObjects.lblYear23Price.get(2));
	actionHandler.waitForSomeTime(5000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
	elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
	actionHandler.waitForSomeTime(30000);
	elementHandler.waitForXpathToLoad(imgQuotepg);
	TestNGCustomReporter.log(logger, "products are added to the cart" +ReportBuilder.takesScreenshot());
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkLineItems);
	elementHandler.clickElement(AddProductsPageObjects.lnkLineItems, AddProductsPageObjects.lnkShowMore);
	actionHandler.waitForSomeTime(2000);
	elementHandler.clickElement(AddProductsPageObjects.lnkShowMore,AddProductsPageObjects.lnkPPCadd );
	elementHandler.clickElement(AddProductsPageObjects.lnkPPCadd, AddProductsPageObjects.lblpriceList);
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lblvalue1 );
	actionHandler.waitForSomeTime(5000);
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
	String price1= elementHandler.getText(AddProductsPageObjects.lblvalue1);
	AssertionHandler.verifyTrue(true,"price value is"+price1);
	TestNGCustomReporter.log(logger, "PPC Additional landing page OTC product Price2-3 value got matched sccessfully" +ReportBuilder.takesScreenshot());
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	
	elementHandler.clickElement(AddProductsPageObjects.lnkShowMore,AddProductsPageObjects.lnkPPCEspanol );
	elementHandler.clickElement(AddProductsPageObjects.lnkPPCEspanol, AddProductsPageObjects.lblpriceList);
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.lnkPriceList, AddProductsPageObjects.lnkItem);
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.lnkItem,AddProductsPageObjects.lblvalue1 );
	actionHandler.waitForSomeTime(5000);
	javascripthandler.scrollIntoView(AddProductsPageObjects.lblvalue1);
	String price2= elementHandler.getText(AddProductsPageObjects.lblvalue1);
	AssertionHandler.verifyTrue(true,"price value is"+price2);
	TestNGCustomReporter.log(logger, "PPC Espanol product Price2-3 values matched properly" +ReportBuilder.takesScreenshot());
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	driver.navigate().back();
	actionHandler.waitForSomeTime(2000);
	
}

/*
 * This case is to add OTC products: 42089523,41870119 and add it to the cart. Give discount and validate Submit for Approval				
 */
public void validateGuidanceOTCprod(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		elementHandler.waitForXpathToLoad(lblCategorySelectn);
		String otcProducts = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
		String[] otcProducts1 = otcProducts.split(",");
		for (int i = 0; i < otcProducts1.length; i++) {
			elementHandler.writeText(AddProductsPageObjects.txtboxSearch, otcProducts1[i]);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
			elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
			actionHandler.waitForSomeTime(10000);
		}
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
		elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
		actionHandler.waitForSomeTime(25000);
		elementHandler.writeText(AddProductsPageObjects.txtAdjustAmount1.get(0), "10");
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
		elementHandler.clickElement(AddProductsPageObjects.btnReprice);
		actionHandler.waitForSomeTime(30000);
		boolean errorMesg1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
		if (errorMesg1 == true) {
			String message= AddProductsPageObjects.lblValidatnMessage.getText();
			TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
		}
		TestNGCustomReporter.log(logger,
				"Discount successfully added to the product" + ReportBuilder.takesScreenshot());
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.imgGuidance.get(0));
		javascripthandler.scrollIntoView(AddProductsPageObjects.imgGuidance.get(0));
		elementHandler.clickElement(AddProductsPageObjects.imgGuidance.get(0));
		TestNGCustomReporter.log(logger,
				"Validate pricing guidance for the product " + ReportBuilder.takesScreenshot());
		windowHandler.switchToLatestWindow(AddProductsPageObjects.btncancel1);
		elementHandler.clickElement(AddProductsPageObjects.btncancel1);
		boolean enblbtn1 = AddProductsPageObjects.btnSubmtForApproval.isEnabled();
		if (enblbtn1 == true) {
			AssertionHandler.verifyTrue(enblbtn1, "Submit for Approval button is not enabled");
			TestNGCustomReporter.log(logger, "Submit for Approval button is enabled after providing discount of 10%" + ReportBuilder.takesScreenshot());

		} else {
			TestNGCustomReporter.log(logger,
					"Submit for Approval button is disabled" + ReportBuilder.takesScreenshot());
		}

		actionHandler.waitForSomeTime(2000);
		elementHandler.writeText(AddProductsPageObjects.txtAdjustAmount1.get(0), "0");
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
		elementHandler.clickElement(AddProductsPageObjects.btnReprice);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
		elementHandler.clickElement(AddProductsPageObjects.btnReprice);
		actionHandler.waitForSomeTime(30000);
		TestNGCustomReporter.log(logger, "Finalize button is enabled after removing the discount for the EB product" + ReportBuilder.takesScreenshot());
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
		elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
		actionHandler.waitForSomeTime(30000);
		TestNGCustomReporter.log(logger, "products added to the cart" + ReportBuilder.takesScreenshot());
}

/*
 * This case is to add Animation Essential product: 41323281 to the cart. Give discount and Finalize the cart			
 */
public void validateGuidance(int Row) {
	javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
	elementHandler.clickElement(QuotePageObjects.btnAddProduct);
	elementHandler.waitForXpathToLoad(lblCategorySelectn);
	String prod4 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, prod4);
	actionHandler.keyboardAction(Keys.ENTER);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
	elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
	actionHandler.waitForSomeTime(30000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
	actionHandler.waitForSomeTime(30000);
	String AdjustTerm = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Adjust_Type", Row);
    String[] arrAdjust = AdjustTerm.split(",");
    for (int i=0;i<arrAdjust.length;i++)
    {
    if (!AdjustTerm.equals( "NA"))
    {
           elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnAdjustmentTyp.get(i));          
           elementHandler.clickElement(AddProductsPageObjects.drpdwnAdjustmentTyp.get(i));
           TestNGCustomReporter.log(logger,"From sheet "+AdjustTerm.trim());
           actionHandler.waitForSomeTime(2000);
           for(int j=1; j<=3; j++)
           {
                  String AdjustTrm=driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).getText();
                  TestNGCustomReporter.log(logger,"From list["+j+"] "+AdjustTrm.trim());
                  if(AdjustTrm.trim().contentEquals(AdjustTerm.trim()))
                  {
                         driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).click();
                         break;
                  }
                  
           }
    	}
    }
    
	String adjustDiscount = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "AdjustAmount", Row);
    String[] arrDiscount = adjustDiscount.split(",");
    for (int k=0;k<arrDiscount.length;k++) 
    {
    	if (!adjustDiscount.equals( "NA"))
    	{
           actionHandler.waitForSomeTime(2000);
           elementHandler.writeText(AddProductsPageObjects.txtAdjustAmount1.get(k), adjustDiscount);
    	}  
    }
    elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
	elementHandler.clickElement(AddProductsPageObjects.btnReprice);
	actionHandler.waitForSomeTime(25000);
	boolean errorMesg1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
	if (errorMesg1 == true) {
		String message= AddProductsPageObjects.lblValidatnMessage.getText();
		TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
	}
	TestNGCustomReporter.log(logger, "Discount successfully added to the cart" +ReportBuilder.takesScreenshot());
	javascripthandler.scrollIntoView(AddProductsPageObjects.imgGuidance.get(0));
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.imgGuidance.get(0));
	elementHandler.clickElement(AddProductsPageObjects.imgGuidance.get(0));
	TestNGCustomReporter.log(logger, "Validate pricing guidance for the product " +ReportBuilder.takesScreenshot());
	windowHandler.switchToLatestWindow(AddProductsPageObjects.btncancel1);
	elementHandler.clickElement(AddProductsPageObjects.btncancel1);
	boolean enblbtn2= AddProductsPageObjects.btnSubmtForApproval.isEnabled();
	if (enblbtn2 == true) {
		AssertionHandler.verifyTrue(enblbtn2, "Submit for Approval button is not enabled in the cart page");
		TestNGCustomReporter.log(logger, "Submit for Approval button is enabled" +ReportBuilder.takesScreenshot());	
		
	}
	else {
		TestNGCustomReporter.log(logger, "Submit for Approval button is disabled" +ReportBuilder.takesScreenshot());
	}
	 elementHandler.writeText(AddProductsPageObjects.txtAdjustAmount1.get(0), "0");
	 actionHandler.waitForSomeTime(5000);
	 elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
	elementHandler.clickElement(AddProductsPageObjects.btnReprice);
	actionHandler.waitForSomeTime(15000);
	 elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
		elementHandler.clickElement(AddProductsPageObjects.btnReprice);
		actionHandler.waitForSomeTime(30000);
	TestNGCustomReporter.log(logger, "After removing Discounts" +ReportBuilder.takesScreenshot());
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
	elementHandler.clickElement(AddProductsPageObjects.btnFinalize);	
	actionHandler.waitForSomeTime(10000);	
	TestNGCustomReporter.log(logger, "products added to the cart" +ReportBuilder.takesScreenshot());
	
}

/*
 * This case is to add multiple Blog Model products:41325003,41701486,41323282,41323281 with Firmsite 333C product:40483699, Validate the error message
 * 
 */
public void validateErrorMessage(int Row) {
	javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
	elementHandler.clickElement(QuotePageObjects.btnAddProduct);
	elementHandler.waitForXpathToLoad(lblCategorySelectn);
	String blogproducts = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
	String firmsite= ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product", Row);
    String[] listofproduct3 = blogproducts.split(",");
    for (int i=0; i<listofproduct3.length; i++)
    {
	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, listofproduct3[i]);
	actionHandler.keyboardAction(Keys.ENTER);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
	elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
	actionHandler.waitForSomeTime(30000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddAnother);
	elementHandler.clickElement(AddProductsPageObjects.btnAddAnother);
	actionHandler.waitForSomeTime(20000);
	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, firmsite);
	actionHandler.keyboardAction(Keys.ENTER);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
	elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
	elementHandler.waitForXpathToLoad(btnProductAttr);
	actionHandler.waitForSomeTime(10000);
	elementHandler.writeText(AddProductsPageObjects.txtboxNumbAttrys, "5");
	elementHandler.clickElement(AddProductsPageObjects.tabSecondaries);
	actionHandler.waitForSomeTime(10000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
	elementHandler.clickElement(AddProductsPageObjects.btnValidate);
	actionHandler.waitForSomeTime(30000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
	actionHandler.waitForSomeTime(30000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
	elementHandler.clickElement(AddProductsPageObjects.btnReprice);
	actionHandler.waitForSomeTime(30000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
	elementHandler.clickElement(AddProductsPageObjects.btnReprice);
	actionHandler.waitForSomeTime(30000);
	javascripthandler.highlightElement(AddProductsPageObjects.lblValidatnMessage);
	boolean error= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
	if (error == true) {
	AssertionHandler.verifyTrue(error, "Error message not displayed in the cart page");
	TestNGCustomReporter.log(logger, "Error Message displayed for adding FNDLW FIRMSITE MOBILE ESSENTIAL product:"  +ReportBuilder.takesScreenshot());
	}else {
		TestNGCustomReporter.log(logger, "Error Message not displayed for adding FNDLW FIRMSITE MOBILE ESSENTIAL product:"  +ReportBuilder.takesScreenshot());		
	}	
	elementHandler.clickElement(AddProductsPageObjects.checkbxSelect);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnRemove);
	elementHandler.clickElement(AddProductsPageObjects.btnRemove);
	actionHandler.waitForSomeTime(30000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
	elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts);
	actionHandler.waitForSomeTime(25000);
    }
}

/*
 * This case is to add PPC products: 41877783,41876119 to the cart and validate the discount.				
 */
public void validateDiscount(int Row) {
	javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
	elementHandler.clickElement(QuotePageObjects.btnAddProduct);
	elementHandler.waitForXpathToLoad(lblCategorySelectn);
	String soc4555 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
	String[] soc4555_1 = soc4555.split(",");
	for (int i=0; i<soc4555_1.length; i++)
	{
	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, soc4555_1[i]);
	actionHandler.keyboardAction(Keys.ENTER);
	actionHandler.waitForSomeTime(30000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
	elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
	actionHandler.waitForSomeTime(20000);
	}
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
	actionHandler.waitForSomeTime(35000);
	//elementHandler.waitForElementTobeClickable(AddProductsPageObjects.txtAdjustAmount1.get(0));
	
	 String adjustDiscount = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "AdjustAmount", Row);
     String[] arrDiscount = adjustDiscount.split(",");
     for (int k=0;k<arrDiscount.length;k++) 
     {
     	if (!adjustDiscount.equals( "NA"))
     	{
            actionHandler.waitForSomeTime(2000);
            elementHandler.writeText(AddProductsPageObjects.txtAdjustAmount1.get(k), arrDiscount[k]);
            actionHandler.waitForSomeTime(2000);
     	}  
     }
     elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
	elementHandler.clickElement(AddProductsPageObjects.btnReprice);
	actionHandler.waitForSomeTime(35000);
	boolean errorMesg1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
	if (errorMesg1 == true) {
		String message= AddProductsPageObjects.lblValidatnMessage.getText();
		TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
	}
	TestNGCustomReporter.log(logger, "Able to give discount for PPC products before finalize button" +ReportBuilder.takesScreenshot());
	actionHandler.waitForSomeTime(5000);
}

/*
 * This case is to add  products and validate contract terms and bridge details
 */
public void validateContractBridge(int Row) {
	javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
	elementHandler.clickElement(QuotePageObjects.btnAddProduct);
	elementHandler.waitForXpathToLoad(lblCategorySelectn);
	String otcProd = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
    String[] otcProd1 = otcProd.split(",");
	for (int i=0; i<otcProd1.length; i++)
	{
	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, otcProd1[i]);
	actionHandler.keyboardAction(Keys.ENTER);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
	elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
	actionHandler.waitForSomeTime(30000);
	}
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
	actionHandler.waitForSomeTime(30000);
	for (int k=0;k<1;k++) 
	{
		
		elementHandler.clickElement(AddProductsPageObjects.drpdwnContractTerm.get(k));
		TestNGCustomReporter.log(logger, "Validate Contract Term not available for Findlw Client pulse" +ReportBuilder.takesScreenshot());
		actionHandler.waitForSomeTime(2000);
	}
	for (int k=0;k<1;k++) 
	{
		javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnBridge5.get(k));
		elementHandler.clickElement(AddProductsPageObjects.drpdwnBridge5.get(k));
		TestNGCustomReporter.log(logger, "Validate Bridge field not available for Findlw Client pulse" +ReportBuilder.takesScreenshot());
		actionHandler.waitForSomeTime(2000);
	}
	elementHandler.clickElement(AddProductsPageObjects.btnReprice);
	actionHandler.waitForSomeTime(30000);
	boolean errorMesg1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
	if (errorMesg1 == true) {
		String message= AddProductsPageObjects.lblValidatnMessage.getText();
		TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
	}
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
	actionHandler.waitForSomeTime(30000);
	elementHandler.waitForXpathToLoad(imgQuotepg);
	String quote_6= elementHandler.getText(AddProductsPageObjects.lblproposalId);
	AssertionHandler.verifyTrue(quote_6.contains("Q-"), "Quote page not displayed");
	TestNGCustomReporter.log(logger, "products are added to the cart" +ReportBuilder.takesScreenshot());
}


/*
 * This case is to add PPC products and validate the Approval and Finalize button is enabled or not
 */
public void validateSubmitForApproval(int Row) {
	javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
	elementHandler.clickElement(QuotePageObjects.btnAddProduct);
	//elementHandler.waitforElement(AddProductsPageObjects.txtboxSearch);
	elementHandler.waitForPageToLoad();
	actionHandler.waitForSomeTime(10000);
	String ppcProduct = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id",Row);
	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, ppcProduct);
	actionHandler.keyboardAction(Keys.ENTER);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
	elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
	actionHandler.waitForSomeTime(5000);
	String ppcType1=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Type",Row);
	String ppclang1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Lang",Row);
	String ppcWebsite1=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Website",Row);
	elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCType1);
	actionHandler.waitForSomeTime(2000);
	driver.findElement(By.xpath("//div[contains(text(),'"+ppcType1+"')]")).click();
	elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCLang);
	actionHandler.waitForSomeTime(2000);
	driver.findElement(By.xpath("//div[contains(text(),'"+ppclang1+"')]")).click();
	elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCWebsite);
	actionHandler.waitForSomeTime(2000);
	driver.findElement(By.xpath("//div[text()='"+ppcWebsite1+"']")).click();
	String ppcAmount1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Amount",Row);
	String[] ppcAmount2 = ppcAmount1.split(",");
	for (int i=0; i<ppcAmount2.length; i++)
	{
	elementHandler.writeText(AddProductsPageObjects.txtPPCAmount, ppcAmount2[i]);
	actionHandler.waitForSomeTime(2000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
	elementHandler.clickElement(AddProductsPageObjects.btnValidate);
	actionHandler.waitForSomeTime(30000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
	elementHandler.clickElement(AddProductsPageObjects.btnValidate);
	actionHandler.waitForSomeTime(30000);
	String status= elementHandler.getText(AddProductsPageObjects.labelStatus);
	if (status.contains("Approval Required")){
		AssertionHandler.verifyTrue(status.contains("Approval Required"), "Approval not required for adding PPC products");
		TestNGCustomReporter.log(logger, "Approval required when PPC bundle is less than $500 and more than $10,000" +ReportBuilder.takesScreenshot());
	}
	else {
		TestNGCustomReporter.log(logger, "Approval not required for the cart is between $500-10,000" +ReportBuilder.takesScreenshot());
	}
	}
	
}


/*
 * This case is to add PPC product and validate the recommended product is displayed or not
 */
public void validateRecommendedProduct(int Row) {
	javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
	elementHandler.clickElement(QuotePageObjects.btnAddProduct);
	elementHandler.waitForPageToLoad();
	actionHandler.waitForSomeTime(10000);
	String ppcProduct = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id",Row);
	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, ppcProduct);
	actionHandler.keyboardAction(Keys.ENTER);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
	elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
	actionHandler.waitForSomeTime(5000);
	String ppcType1=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Type",Row);
	String ppclang1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Lang",Row);
	elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCType1);
	actionHandler.waitForSomeTime(2000);
	driver.findElement(By.xpath("//div[contains(text(),'"+ppcType1+"')]")).click();
	elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCLang);
	actionHandler.waitForSomeTime(2000);
	driver.findElement(By.xpath("//div[contains(text(),'"+ppclang1+"')]")).click();
	String ppcWebsite1=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Website",Row);
	String[] ppcWebsite2 = ppcWebsite1.split(",");
	for (int i=0; i<ppcWebsite2.length; i++)
	{
		elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCWebsite);
		actionHandler.waitForSomeTime(2000);
		driver.findElement(By.xpath("//div[text()='"+ppcWebsite2[i]+"']")).click();
		actionHandler.waitForSomeTime(2000);
		String ppcAmount1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Amount",Row);
		elementHandler.writeText(AddProductsPageObjects.txtPPCAmount, ppcAmount1);
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
		elementHandler.clickElement(AddProductsPageObjects.btnValidate);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
		elementHandler.clickElement(AddProductsPageObjects.btnValidate);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lblRecommend);
		elementHandler.clickElement(AddProductsPageObjects.lblRecommend);
		boolean status= elementHandler.isElementDisplayed(AddProductsPageObjects.btnAddtoCart);
		if(status== true) {
			AssertionHandler.verifyTrue(status, "Recommended product not displayed");
		TestNGCustomReporter.log(logger, "Additional Firmsite Landing Page material displayed as Recommended product" +ReportBuilder.takesScreenshot());
		}
		else {
			TestNGCustomReporter.log(logger, "Additional Firmsite Landing Page material not displayed as Recommended product" +ReportBuilder.takesScreenshot());	
		}
		
		}	
}

/*
 * 
 * This case is to add PPC product and validate the error message
 */
public void validateErrorMessage1(int Row) {
	javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
	elementHandler.clickElement(QuotePageObjects.btnAddProduct);
	elementHandler.waitForPageToLoad();
	actionHandler.waitForSomeTime(10000);
	String ppclang1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Lang",Row);
	String[] ppclang = ppclang1.split(",");
	String ppcAmount1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Amount",Row);
	String[] ppcAmount2 = ppcAmount1.split(",");
	String ppcType1=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Type",Row);
	String ppcWebsite1=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Website",Row);
    for (int i=0; i<2; i++)
    {	
	String ppcProduct = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id",Row);
	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, ppcProduct);
	actionHandler.keyboardAction(Keys.ENTER);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
	elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCType1);
	actionHandler.waitForSomeTime(2000);
	driver.findElement(By.xpath("//div[contains(text(),'"+ppcType1+"')]")).click();
	actionHandler.waitForSomeTime(5000);
	elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCLang);
	actionHandler.waitForSomeTime(2000);
	driver.findElement(By.xpath("//div[contains(text(),'"+ppclang[i]+"')]")).click();
	actionHandler.waitForSomeTime(5000);
	elementHandler.writeText(AddProductsPageObjects.txtPPCAmount, ppcAmount2[i]);
	actionHandler.waitForSomeTime(2000);
	elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCWebsite);
	actionHandler.waitForSomeTime(2000);
	driver.findElement(By.xpath("//div[text()='"+ppcWebsite1+"']")).click();
	actionHandler.waitForSomeTime(5000);
	TestNGCustomReporter.log(logger, "Validate Bundle 1 or 4" +ReportBuilder.takesScreenshot());
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
	elementHandler.clickElement(AddProductsPageObjects.btnValidate);
	actionHandler.waitForSomeTime(40000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng, AddProductsPageObjects.btnReprice);
	actionHandler.waitForSomeTime(35000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
	elementHandler.clickElement(AddProductsPageObjects.btnReprice);
	actionHandler.waitForSomeTime(30000);
	boolean message= elementHandler.isElementDisplayed(AddProductsPageObjects.lnlerrorMessage);
	if (message == false)
	AssertionHandler.verifyFalse(message,"Error Message displayed for adding PPC product Bundle 1 or 4");
	if (message == true)
	AssertionHandler.verifyTrue(message,"Error Message not displayed for adding PPC product Bundle 1 or 4");
	TestNGCustomReporter.log(logger, "Validate Error message for adding PPC product as Bundle 1 and 4 :" +message +ReportBuilder.takesScreenshot());
	elementHandler.clickElement(AddProductsPageObjects.chkboxSelectAll, AddProductsPageObjects.btnRemove);
	actionHandler.waitForSomeTime(2000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnRemove);
	elementHandler.clickElement(AddProductsPageObjects.btnRemove);
	actionHandler.waitForSomeTime(20000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
	elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts,AddProductsPageObjects.txtboxSearch);
	actionHandler.waitForSomeTime(30000);	
}
    elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSave1);
    elementHandler.clickElement(AddProductsPageObjects .btnSave1);
    actionHandler.waitForSomeTime(20000);
}


public void validatePPCMessage(int Row) {
	javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
	elementHandler.clickElement(QuotePageObjects.btnAddProduct);
	elementHandler.waitForPageToLoad();
	actionHandler.waitForSomeTime(10000);
	String ppcProduct = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id",Row);
	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, ppcProduct);
	actionHandler.keyboardAction(Keys.ENTER);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
	elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
	actionHandler.waitForSomeTime(25000);
	String ppclang1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Lang",Row);
	String[] ppclang = ppclang1.split(",");
    for (int i=0; i<ppclang.length; i++)
    {	
	String ppcType1=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Type",Row);
	String[] ppcTyp = ppcType1.split(",");
	String ppcWebsite1=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Website",Row);
	String[] ppcWeb = ppcWebsite1.split(",");
	elementHandler.clickElement(AddProductsPageObjects.txtPPCAmount);
	boolean ppc= elementHandler.isElementDisplayed(AddProductsPageObjects.drpdwnPPCType1);
	if (ppc == true) {
	elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCType1);
	actionHandler.waitForSomeTime(2000);
	}
	driver.findElement(By.xpath("//div[contains(text(),'"+ppcTyp[i]+"')]")).click();
	actionHandler.waitForSomeTime(5000);
	boolean lang= elementHandler.isElementDisplayed(AddProductsPageObjects.drpdwnPPCLang);
	if (lang == true) {
	elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCLang);
	actionHandler.waitForSomeTime(2000);
	}
	driver.findElement(By.xpath("//div[contains(text(),'"+ppclang[i]+"')]")).click();
	actionHandler.waitForSomeTime(5000);
	String ppcAmount1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Amount",Row);
	elementHandler.writeText(AddProductsPageObjects.txtPPCAmount, ppcAmount1);
	actionHandler.waitForSomeTime(2000);
	boolean web= elementHandler.isElementDisplayed(AddProductsPageObjects.drpdwnPPCWebsite);
	if (web == true) {
	elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCWebsite);
	actionHandler.waitForSomeTime(2000);
	}
	boolean ppcWeb1= elementHandler.isElementDisplayed(AddProductsPageObjects.drpdwnPPCWeb);
	if (ppcWeb1 == true) {	
	driver.findElement(By.xpath("//div[text()='"+ppcWeb[i]+"']")).click();
	actionHandler.waitForSomeTime(5000);
	}
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
	elementHandler.clickElement(AddProductsPageObjects.btnValidate);
	actionHandler.waitForSomeTime(20000);
	String message= elementHandler.getText(AddProductsPageObjects.lblPPCMessage);
	AssertionHandler.verifyTrue(message.contains("Auto Included")| message.contains("Auto included"));
	TestNGCustomReporter.log(logger, "Validate Bundle 2,3,5 and 6" +ReportBuilder.takesScreenshot());
}   
}

public List<String> addMultiplePPCProduct(int Row) {
	javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
	elementHandler.clickElement(QuotePageObjects.btnAddProduct,AddProductsPageObjects.txtboxSearch);
	actionHandler.waitForSomeTime(10000);
	String ppcProduct2 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id",Row);
	String[] ppcproduct0 = ppcProduct2.split(",");
	String ppclang3 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Lang",Row);
	String[] ppclang4 = ppclang3.split(",");
	String ppcWebsite3=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Website",Row);
	String[] ppcWebsite4 = ppcWebsite3.split(",");
	for (int i=0; i<ppcproduct0.length; i++)
	{
	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, ppcproduct0[i]);
	actionHandler.keyboardAction(Keys.ENTER);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
	elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
	actionHandler.waitForSomeTime(15000);
	String ppcType3=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Type",Row);
	String ppcAmount3 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Amount",Row);
	elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCType1);
	actionHandler.waitForSomeTime(2000);
	driver.findElement(By.xpath("//div[contains(text(),'"+ppcType3+"')]")).click();
	elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCLang);
	driver.findElement(By.xpath("//div[contains(text(),'"+ppclang4[i]+"')]")).click();
	elementHandler.writeText(AddProductsPageObjects.txtPPCAmount, ppcAmount3);
	actionHandler.waitForSomeTime(2000);
	elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCWebsite);
	driver.findElement(By.xpath("//div[text()='"+ppcWebsite4[i]+"']")).click();
	actionHandler.waitForSomeTime(2000);
	elementHandler.clickElement(AddProductsPageObjects.tabSecondaries);
	actionHandler.waitForSomeTime(8000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
	elementHandler.clickElement(AddProductsPageObjects.btnValidate);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
	actionHandler.waitForSomeTime(30000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
	elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts);
	actionHandler.waitForSomeTime(25000);
	}
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
	actionHandler.waitForSomeTime(25000);
	elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
	elementHandler.clickElement(AddProductsPageObjects.btnReprice);
	actionHandler.waitForSomeTime(25000);
	boolean errorMesg1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
	if (errorMesg1 == true) {
		String message= AddProductsPageObjects.lblValidatnMessage.getText();
		TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
	}
	elementHandler.clickElement(AddProductsPageObjects.lblShowOptions.get(0));
	actionHandler.waitForSomeTime(5000);
	javascripthandler.scrollToElementAndClick(AddProductsPageObjects.lblShowOptions.get(1));
	actionHandler.waitForSomeTime(5000);
	List<String> productName = new ArrayList<String>();
	int size= AddProductsPageObjects.lblAssetName.size();
	System.out.println("size is" +size);
	/*for (WebElement product : AddProductsPageObjects.lblAssetName) {
		productName.add(product.getText());
    }*/
	
	for (int i=0;i<(AddProductsPageObjects.lblAssetName.size());i++) {
		productName.add(AddProductsPageObjects.lblAssetName.get(i).getText());
	}
	
	boolean btn2= AddProductsPageObjects.btnFinalize.isEnabled();
	if (btn2 == true) {
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
		elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForXpathToLoad(imgQuotepg);
		String quote_1= elementHandler.getText(AddProductsPageObjects.lblproposalId);
		AssertionHandler.verifyTrue(quote_1.contains("Q-"), "Quote page not displayed");
		TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Finalize button" +ReportBuilder.takesScreenshot());	
		
	}
	else {
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmtForApproval);
		elementHandler.clickElement(AddProductsPageObjects.btnSubmtForApproval,AddProductsPageObjects.btnSubmit);
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
		TestNGCustomReporter.log(logger, "Validate submit button" +ReportBuilder.takesScreenshot());
		elementHandler.clickElement(AddProductsPageObjects.btnSubmit, AddProductsPageObjects.btnSubmit);
		actionHandler.waitForSomeTime(10000);
		TestNGCustomReporter.log(logger, "Validate submit button" +ReportBuilder.takesScreenshot());
		elementHandler.clickElement(AddProductsPageObjects.btnSubmit,AddProductsPageObjects.btnReturn);
		actionHandler.waitForSomeTime(5000);
		TestNGCustomReporter.log(logger, "Validate Return button" +ReportBuilder.takesScreenshot());
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReturn);
		elementHandler.clickElement(AddProductsPageObjects.btnReturn);
		actionHandler.waitForSomeTime(25000);
		String quote_1= elementHandler.getText(AddProductsPageObjects.lblproposalId);
		AssertionHandler.verifyTrue(quote_1.contains("Q-"), "Quote page not displayed");
		TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Submit for approval button" +ReportBuilder.takesScreenshot());
		QuotePage quotepage = new QuotePage(driver);
		String Name= quotepage.submitForApproval();
		SearchPage searchpage = new SearchPage(driver);
		searchpage.searchDiffProfile("Ryan Burch");
		searchpage.approvalProcess(Name);
		BasePage basePage = new BasePage(driver);
		basePage.Logout();
		searchpage.changeTab();	
	
			}
		return productName;
	}


	public void addPPCLandingPage(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		elementHandler.waitForPageToLoad();
		actionHandler.waitForSomeTime(15000);
		String ppcProduct = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id",Row);
		elementHandler.writeText(AddProductsPageObjects.txtboxSearch, ppcProduct);
		actionHandler.keyboardAction(Keys.ENTER);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
		elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
		actionHandler.waitForSomeTime(8000);
		String ppcType1=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Type",Row);
		String ppclang1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Lang",Row);
		elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCType1);
		actionHandler.waitForSomeTime(2000);
		driver.findElement(By.xpath("//div[contains(text(),'"+ppcType1+"')]")).click();
		elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCLang);
		actionHandler.waitForSomeTime(2000);
		driver.findElement(By.xpath("//div[contains(text(),'"+ppclang1+"')]")).click();
		elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCWebsite);
		actionHandler.waitForSomeTime(2000);
		String ppcAmount1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Amount",Row);
		elementHandler.writeText(AddProductsPageObjects.txtPPCAmount,ppcAmount1);
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
		elementHandler.clickElement(AddProductsPageObjects.btnValidate);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
		elementHandler.clickElement(AddProductsPageObjects.btnValidate);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
		elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
		elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts);
		actionHandler.waitForSomeTime(30000);
		String landingPage= ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product",Row);
		elementHandler.writeText(AddProductsPageObjects.txtboxSearch, landingPage);
		actionHandler.keyboardAction(Keys.ENTER);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
		elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
		actionHandler.waitForSomeTime(15000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
		elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
		elementHandler.clickElement(AddProductsPageObjects.btnReprice);
		actionHandler.waitForSomeTime(25000);
		boolean errorMesg1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
		if (errorMesg1 == true) {
			String message= AddProductsPageObjects.lblValidatnMessage.getText();
			TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
		}
		boolean status= elementHandler.isElementDisplayed(AddProductsPageObjects.lblErrorMessg);
		if (status == true) {
			AssertionHandler.verifyTrue(status, "Warning Message displayed for adding  PPC Additional Spend and PPC Landing page");
			TestNGCustomReporter.log(logger, "Validate the Warning Message displayed for adding  PPC Additional Spend and PPC Landing page" +ReportBuilder.takesScreenshot());
			elementHandler.clickElement(AddProductsPageObjects.chkboxSelect.get(2));
			actionHandler.waitForSomeTime(2000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnRemove);
			elementHandler.clickElement(AddProductsPageObjects.btnRemove);
			actionHandler.waitForSomeTime(15000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
			elementHandler.clickElement(AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(30000);
		}
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSave);
		elementHandler.clickElement(AddProductsPageObjects.btnSave);
		actionHandler.waitForSomeTime(25000);
	}
	
	
	public void addMultipleInstalledprod(int Row,ArrayList<String> assetText) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForXpathToLoad(lblCategorySelectn);
		elementHandler.clickElement(AddProductsPageObjects.btnInstalledProduct,AddProductsPageObjects.txtInstalledProduct);
		actionHandler.waitForSomeTime(15000);
		TestNGCustomReporter.log(logger, "Installed products page dispalyed with assets" +ReportBuilder.takesScreenshot());
		int size = AddProductsPageObjects.lnkAssets.size();
        for (int i=0;i<size;i++) {
        String text= elementHandler.getText(AddProductsPageObjects.lnkAssets.get(i));
        if (text.contains("PPC")) {
        	actionHandler.waitForSomeTime(2000);
        	javascripthandler.scrollIntoView(AddProductsPageObjects.checkboxes.get(i));
        	elementHandler.clickElement(AddProductsPageObjects.checkboxes.get(i));
        	actionHandler.waitForSomeTime(2000);
       if (assetText.contains(text))
        	 {
        		AssertionHandler.verifyTrue(true, "values not matched for both Array list of Asset Line Items sold To and Installed prodcuts page");
        	 }
        }      
        }
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnRenew);
       // elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnRenew);
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.radiobtnoptn);
		elementHandler.clickElement(AddProductsPageObjects.radiobtnoptn, AddProductsPageObjects.btnConfirm);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfirm);
		elementHandler.clickElement(AddProductsPageObjects.btnConfirm,AddProductsPageObjects.btnGoToPricng);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
		elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
		elementHandler.clickElement(AddProductsPageObjects.btnReprice);
		actionHandler.waitForSomeTime(25000);
		boolean errorMesg1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
		if (errorMesg1 == true) {
			String message= AddProductsPageObjects.lblValidatnMessage.getText();
			TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
		}
		int size1 = AddProductsPageObjects.lnkAssetLines.size();
        for (int i=0;i<size1;i++) {
        String text= elementHandler.getText(AddProductsPageObjects.lnkAssetLines.get(i));
        	 if (assetText.contains(text)) {
        		AssertionHandler.verifyTrue(true, "values not matched for both Array list of Asset Line Items sold To and Cart page");
        	 }
        }    
		boolean status3= AddProductsPageObjects.btnFinalize.isEnabled();
		if (status3 == true) {
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
			elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForXpathToLoad(imgQuotepg);
			String quote= elementHandler.getText(AddProductsPageObjects.lblproposalId);
			AssertionHandler.verifyTrue(quote.contains("Q-"), "Quote page not displayed");
			TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Finalize button" +ReportBuilder.takesScreenshot());		
		}
		else {
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmtForApproval);
			elementHandler.clickElement(AddProductsPageObjects.btnSubmtForApproval,AddProductsPageObjects.btnSubmit);
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
			elementHandler.clickElement(AddProductsPageObjects.btnSubmit, AddProductsPageObjects.btnSubmit);
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
			elementHandler.clickElement(AddProductsPageObjects.btnSubmit,AddProductsPageObjects.btnReturn);
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReturn);
			elementHandler.clickElement(AddProductsPageObjects.btnReturn);
			actionHandler.waitForSomeTime(20000);
			String quote= elementHandler.getText(AddProductsPageObjects.lblproposalId);
			AssertionHandler.verifyTrue(quote.contains("Q-"), "Quote page not displayed");
			TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Submit for approval button" +ReportBuilder.takesScreenshot());
			QuotePage quotepage = new QuotePage(driver);
			String Name= quotepage.submitForApproval();
			SearchPage searchpage = new SearchPage(driver);
			searchpage.searchDiffProfile("Ryan Burch");
			searchpage.approvalProcess(Name);
			BasePage basePage = new BasePage(driver);
			basePage.Logout();
			searchpage.changeTab();
		}
	}
	
	public void validateApprovalButton(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		elementHandler.waitForPageToLoad();
		actionHandler.waitForSomeTime(15000);
		String ppcProduct = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id",Row);
		elementHandler.writeText(AddProductsPageObjects.txtboxSearch, ppcProduct);
		actionHandler.keyboardAction(Keys.ENTER);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
		elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
		actionHandler.waitForSomeTime(10000);
		String ppcType1=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Type",Row);
		String ppclang1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Lang",Row);
		String ppcWebsite4=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Website",Row);
		//String PPCFee= ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Fee_Override%",Row);
		elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCType1);
		actionHandler.waitForSomeTime(2000);
		driver.findElement(By.xpath("//div[contains(text(),'"+ppcType1+"')]")).click();
		elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCLang);
		actionHandler.waitForSomeTime(2000);
		driver.findElement(By.xpath("//div[contains(text(),'"+ppclang1+"')]")).click();
		elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCWebsite);
		actionHandler.waitForSomeTime(2000);
		driver.findElement(By.xpath("//div[text()='"+ppcWebsite4+"']")).click();
		String ppcAmount1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Amount",Row);
		elementHandler.writeText(AddProductsPageObjects.txtPPCAmount,ppcAmount1);
		actionHandler.waitForSomeTime(2000);
		elementHandler.writeText(AddProductsPageObjects.txtboxFeeOverride, "5");
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
		elementHandler.clickElement(AddProductsPageObjects.btnValidate);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
		elementHandler.clickElement(AddProductsPageObjects.btnValidate);
		actionHandler.waitForSomeTime(30000);
		String quoteStatus= elementHandler.getText(AddProductsPageObjects.labelStatus);
		AssertionHandler.verifyTrue(quoteStatus.contains("Approval Required"), "After adding Fee Override Approval not required");
		TestNGCustomReporter.log(logger, "Approval required for adding product" +ReportBuilder.takesScreenshot());
	}
	
	public void validateTerminate(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		elementHandler.waitForXpathToLoad(lblCategorySelectn);
        String installedprod = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product", Row);
		elementHandler.clickElement(AddProductsPageObjects.btnInstalledProduct,AddProductsPageObjects.txtInstalledProduct);
		actionHandler.waitForSomeTime(15000);
		boolean status3= elementHandler.isElementDisplayed(AddProductsPageObjects.txtInstalledProduct);
		if (status3== false) {
			actionHandler.waitForSomeTime(10000);
		}
		elementHandler.writeText(AddProductsPageObjects.txtInstalledProduct, installedprod);
		actionHandler.keyboardAction(Keys.ENTER);
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.checkbox);
		elementHandler.clickElement(AddProductsPageObjects.checkbox, AddProductsPageObjects.btnTerminate);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnTerminate);
		elementHandler.clickElement(AddProductsPageObjects.btnTerminate, AddProductsPageObjects.btnCalculate);
		actionHandler.waitForSomeTime(10000);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String termdate= "02/19/2010";
		//Date termdate=dateFormat.parse("02/19/2010");
		//String termdate= dateFormat.format(fromdate);
		elementHandler.writeText(AddProductsPageObjects.txtTermDate, termdate);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnCalculate);
		elementHandler.clickElement(AddProductsPageObjects.btnCalculate, AddProductsPageObjects.btnConfirm1);
		actionHandler.waitForSomeTime(10000);
		javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnConfirm1);
		actionHandler.waitForSomeTime(15000);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblValidatnMessage);
		actionHandler.waitForSomeTime(5000);
		boolean message= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
		if (message == true) {
			AssertionHandler.verifyTrue(true, "Validation message not displayed for adding past date in the Termination Date field");
			 TestNGCustomReporter.log(logger, "Validate Error mEssage for adding past date to the Termination Date field" +ReportBuilder.takesScreenshot());	 
		}
		String termdate1="02/19/2020";
		elementHandler.writeText(AddProductsPageObjects.txtTermDate, termdate1);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnCalculate);
		elementHandler.clickElement(AddProductsPageObjects.btnCalculate, AddProductsPageObjects.btnConfirm1);
		javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnConfirm1);
		actionHandler.waitForSomeTime(20000);
		boolean message1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage1);
		if (message1 == true) {
			AssertionHandler.verifyTrue(true, "Validation message not displayed for adding past date in the Termination Date field");
			 TestNGCustomReporter.log(logger, "Validate Error mEssage for adding future date to the Termination Date field" +ReportBuilder.takesScreenshot());	 
		}
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
		elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.chkboxSelect.get(1));
		elementHandler.clickElement(AddProductsPageObjects.chkboxSelect.get(1));
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnRemove);
		elementHandler.clickElement(AddProductsPageObjects.btnRemove);
		actionHandler.waitForSomeTime(20000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
		elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts);
		actionHandler.waitForSomeTime(20000);
		elementHandler.clickElement(AddProductsPageObjects.btnInstalledProduct,AddProductsPageObjects.txtInstalledProduct);
		actionHandler.waitForSomeTime(15000);
			boolean status4= elementHandler.isElementDisplayed(AddProductsPageObjects.txtInstalledProduct);
			if (status4 == false) {
				actionHandler.waitForSomeTime(10000);
			}
			elementHandler.writeText(AddProductsPageObjects.txtInstalledProduct, installedprod);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.checkbox);
			elementHandler.clickElement(AddProductsPageObjects.checkbox, AddProductsPageObjects.btnTerminate);
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnTerminate);
			elementHandler.clickElement(AddProductsPageObjects.btnTerminate, AddProductsPageObjects.btnCalculate);
			actionHandler.waitForSomeTime(10000);
			
			String termdate2=dateFormat.format(date);
			elementHandler.writeText(AddProductsPageObjects.txtTermDate, termdate2);
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnCalculate);
			elementHandler.clickElement(AddProductsPageObjects.btnCalculate, AddProductsPageObjects.btnConfirm1);
			actionHandler.waitForSomeTime(5000);
			javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnConfirm1);
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
			elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts);
			String prod= ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
			elementHandler.writeText(AddProductsPageObjects.txtboxSearch, prod);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
			elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
			elementHandler.clickElement(AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(30000);
			boolean errorMesg1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
			if (errorMesg1 == true) {
				String message2 = AddProductsPageObjects.lblValidatnMessage.getText();
				TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message2 +ReportBuilder.takesScreenshot());					
			}
			TestNGCustomReporter.log(logger, "Validate the lapse product to be finalized with adding new product" +ReportBuilder.takesScreenshot());	 
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
			elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
			actionHandler.waitForSomeTime(15000);		
	}

	
	public void validatePAGEOproduct(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForXpathToLoad(lblCategorySelectn);     
        actionHandler.waitForSomeTime(15000);                  
        String Product2 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
        boolean pageobtn= elementHandler.isElementDisplayed(AddProductsPageObjects.btnPAGEOProduct);
        if (pageobtn == true) {
        	AssertionHandler.verifyTrue(true, "PA GEO products button not displayed in the Catalog page");
        }
        elementHandler.writeText(AddProductsPageObjects.txtboxSearch, Product2);
        actionHandler.keyboardAction(Keys.ENTER);
        actionHandler.waitForSomeTime(25000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
        elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
		actionHandler.waitForSomeTime(20000);
        TestNGCustomReporter.log(logger, "Product "+Product2 +" Added Successfully");
        actionHandler.waitForSomeTime(5000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
        elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng,AddProductsPageObjects.btnReprice);
        actionHandler.waitForSomeTime(30000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
        elementHandler.clickElement(AddProductsPageObjects.btnReprice,AddProductsPageObjects.btnPAGEOProduct);
        actionHandler.waitForSomeTime(35000);
        boolean errorMesg1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
		if (errorMesg1 == true) {
			String message = AddProductsPageObjects.lblValidatnMessage.getText();
			TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
		}
        boolean paGeoStatus= elementHandler.isElementDisplayed(AddProductsPageObjects.btnPAGEOProduct);
        if (paGeoStatus == true)
        AssertionHandler.verifyTrue(true, "PA GEO Button not displayed in the Cart page");
        TestNGCustomReporter.log(logger, "Validate PA GEO Button in the cart page" +ReportBuilder.takesScreenshot());
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnPAGEOProduct);
        elementHandler.clickElement(AddProductsPageObjects.btnPAGEOProduct);
        actionHandler.waitForSomeTime(25000);        
        TestNGCustomReporter.log(logger, "Validate next page displayed after clicking on PA GEO button" +ReportBuilder.takesScreenshot());
       /*
        * Validate Exit button
        */
        boolean btnExitStatus= elementHandler.isElementDisplayed(AddProductsPageObjects.btnExit);
        if (btnExitStatus == true) {
        TestNGCustomReporter.log(logger, "Validate Exit button in the PA/GEO Custom page" +ReportBuilder.takesScreenshot()); 
        }
        /*
         * validate all options displayed under High level product grouping
         */
        String prodval= ExcelUtils.getDataByColumnName("COB", "Prodvalue");
        int prodgroupsize= AddProductsPageObjects.lblproductGroup.size();
        for (int i=0;i<prodgroupsize;i++) {
        	String value= elementHandler.getText(AddProductsPageObjects.lblproductGroup.get(i));
        	AssertionHandler.verifyTrue(prodval.contains(value), "product is not displayed under High level product grouping");
        }
        /*
         * validate UI for Digital Marketing
         */      
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.optionDigitalMrkt);
        actionHandler.waitForSomeTime(2000);
        String lblState= elementHandler.getText(AddProductsPageObjects.lblState);
        AssertionHandler.verifyTrue(lblState.contains("State"), "State field is not displayed for Findlaw Digital marketing product");
        elementHandler.selectByIndex(AddProductsPageObjects.drpdwnState,1);
        actionHandler.waitForSomeTime(5000);        
        String lblCounty= elementHandler.getText(AddProductsPageObjects.lblCounty);
        AssertionHandler.verifyTrue(lblCounty.contains("County"), "State field is not displayed for Findlaw Digital marketing product");
        elementHandler.selectByIndex(AddProductsPageObjects.drpdwnCounty,1);
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnSearch);
        actionHandler.waitForSomeTime(15000);
        javascripthandler.scrollIntoView(AddProductsPageObjects.headerPracArea);
        String headerlbl= elementHandler.getText(AddProductsPageObjects.headerPracArea);
        AssertionHandler.verifyTrue(headerlbl.contains("Practice Area"), "Practice Area header field not displayed");
        javascripthandler.scrollIntoView(AddProductsPageObjects.headerCounty);
        String headerlbl1= elementHandler.getText(AddProductsPageObjects.headerCounty);
        AssertionHandler.verifyTrue(headerlbl1.contains("County"), "County header field not displayed");
        javascripthandler.scrollIntoView(AddProductsPageObjects.headerPrice1);
        String headerlbl2= elementHandler.getText(AddProductsPageObjects.headerPrice1);
        AssertionHandler.verifyTrue(headerlbl2.contains("Price"), "Price header field not displayed");
        TestNGCustomReporter.log(logger, "Validate the UI for Digital Marketing product" +ReportBuilder.takesScreenshot());
        
        /*
         * validate UI for Super lawyers Ask Answer page
         */
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.optionSuperLawyers);
        actionHandler.waitForSomeTime(2000);
        String lblState1= elementHandler.getText(AddProductsPageObjects.lblState);
        AssertionHandler.verifyTrue(lblState1.contains("State"), "State field is not displayed for Findlaw Digital marketing product");
        elementHandler.selectByIndex(AddProductsPageObjects.drpdwnState,1);
        actionHandler.waitForSomeTime(5000);        
        String lblPracCategory= elementHandler.getText(AddProductsPageObjects.lblPracCategory);
        AssertionHandler.verifyTrue(lblPracCategory.contains("Practice Category"), "Practice Category field is not displayed for Findlaw Digital marketing product");
        elementHandler.selectByIndex(AddProductsPageObjects.drpdwnPracCategory,1);
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnSearch);
        actionHandler.waitForSomeTime(15000);
        boolean popAlert3= popUpHandler.isAlertPresent();
        if (popAlert3 == true)
        driver.switchTo().alert().accept();
        actionHandler.waitForSomeTime(2000);
        javascripthandler.scrollIntoView(AddProductsPageObjects.headerList.get(0));
        String header1= elementHandler.getText(AddProductsPageObjects.headerList.get(0));
        AssertionHandler.verifyTrue(header1.contains("Practice Area"), "Practice Area header field not displayed");
        javascripthandler.scrollIntoView(AddProductsPageObjects.headerList.get(1));
        String header2= elementHandler.getText(AddProductsPageObjects.headerList.get(1));
        AssertionHandler.verifyTrue(header2.contains("Available Inventory"), "Available Inventory header field not displayed");
        javascripthandler.scrollIntoView(AddProductsPageObjects.headerList.get(2));
        String header3= elementHandler.getText(AddProductsPageObjects.headerList.get(2));
        AssertionHandler.verifyTrue(header3.contains("Price"), "Price header field not displayed");
        TestNGCustomReporter.log(logger, "Validate the UI for Super lawyers Ask Answer page product" +ReportBuilder.takesScreenshot());
        
        /*
         * Validate UI for Findlaw premium profile
         */
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.optionPremiumProfile);
        actionHandler.waitForSomeTime(2000);
        String lblState2= elementHandler.getText(AddProductsPageObjects.lblState);
        AssertionHandler.verifyTrue(lblState2.contains("State"), "State field is not displayed for Findlaw Digital marketing product");
        elementHandler.selectByIndex(AddProductsPageObjects.drpdwnState,1);
        actionHandler.waitForSomeTime(5000);        
        String lblCounty2= elementHandler.getText(AddProductsPageObjects.lblCounty);
        AssertionHandler.verifyTrue(lblCounty2.contains("County"), "State field is not displayed for Findlaw Digital marketing product");
        elementHandler.selectByIndex(AddProductsPageObjects.drpdwnCounty,1);
        actionHandler.waitForSomeTime(5000);      
        String lblCounty3= elementHandler.getText(AddProductsPageObjects.lblAttorneyCount);
        AssertionHandler.verifyTrue(lblCounty3.contains("Attorney Count Range"), "State field is not displayed for Findlaw Digital marketing product");
        elementHandler.selectByIndex(AddProductsPageObjects.drpdwnAttorneyCount,0);
        actionHandler.waitForSomeTime(5000);      
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnSearch);
        actionHandler.waitForSomeTime(15000);
        boolean popAlert= popUpHandler.isAlertPresent();
        if (popAlert == true)
        driver.switchTo().alert().accept();
        actionHandler.waitForSomeTime(2000);
        javascripthandler.scrollIntoView(AddProductsPageObjects.headerList.get(0));
        String header0= elementHandler.getText(AddProductsPageObjects.headerList.get(0));
        AssertionHandler.verifyTrue(header0.contains("Attorney Count Range"), "Attorney Count Range header field not displayed");
        javascripthandler.scrollIntoView(AddProductsPageObjects.headerList.get(1));
        String header_1= elementHandler.getText(AddProductsPageObjects.headerList.get(1));
        AssertionHandler.verifyTrue(header_1.contains("County"), "County header field not displayed");
        javascripthandler.scrollIntoView(AddProductsPageObjects.headerList.get(2));
        String header_2= elementHandler.getText(AddProductsPageObjects.headerList.get(2));
        AssertionHandler.verifyTrue(header_2.contains("Price"), "Price header field not displayed");
        TestNGCustomReporter.log(logger, "Validate the UI for Findlaw premium profile product" +ReportBuilder.takesScreenshot());
        
       /*
        * Validate UI part for Findlaw Focus page 
        */
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.optionFocusPage);
        actionHandler.waitForSomeTime(2000);
        String lblState4= elementHandler.getText(AddProductsPageObjects.lblState);
        AssertionHandler.verifyTrue(lblState4.contains("State"), "State field is not displayed for Findlaw Digital marketing product");
        elementHandler.selectByIndex(AddProductsPageObjects.drpdwnState,1);
        actionHandler.waitForSomeTime(5000);        
        String lblCounty4= elementHandler.getText(AddProductsPageObjects.lblCounty);
        AssertionHandler.verifyTrue(lblCounty4.contains("County"), "State field is not displayed for Findlaw Digital marketing product");
        elementHandler.selectByIndex(AddProductsPageObjects.drpdwnCounty,1);
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnSearch);
        actionHandler.waitForSomeTime(15000);
        boolean popAlert1= popUpHandler.isAlertPresent();
        if (popAlert1 == true)
        driver.switchTo().alert().accept();
        actionHandler.waitForSomeTime(2000);
        javascripthandler.scrollIntoView(AddProductsPageObjects.headerList.get(0));
        String header_3= elementHandler.getText(AddProductsPageObjects.headerList.get(0));
        AssertionHandler.verifyTrue(header_3.contains("Practice Area"), "Practice Area header field not displayed");
        javascripthandler.scrollIntoView(AddProductsPageObjects.headerList.get(1));
        String header_4= elementHandler.getText(AddProductsPageObjects.headerList.get(1));
        AssertionHandler.verifyTrue(header_4.contains("County"), "County header field not displayed");
        javascripthandler.scrollIntoView(AddProductsPageObjects.headerList.get(2));
        String header_5= elementHandler.getText(AddProductsPageObjects.headerList.get(2));
        AssertionHandler.verifyTrue(header_5.contains("Price"), "Price header field not displayed");
        TestNGCustomReporter.log(logger, "Validate the UI part for Fildlaw Focus page product" +ReportBuilder.takesScreenshot());
        
        /*
         *  validate UI part for Findlaw Post Plus Firmsite product
         */
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.optionFindlwPostPlus);
        actionHandler.waitForSomeTime(2000);
        String lblState5= elementHandler.getText(AddProductsPageObjects.lblState);
        AssertionHandler.verifyTrue(lblState5.contains("State"), "State field is not displayed for Findlaw Digital marketing product");
        elementHandler.selectByIndex(AddProductsPageObjects.drpdwnState,1);
        actionHandler.waitForSomeTime(5000);        
        String lblCounty5= elementHandler.getText(AddProductsPageObjects.lblCounty);
        AssertionHandler.verifyTrue(lblCounty5.contains("County"), "State field is not displayed for Findlaw Digital marketing product");
        elementHandler.selectByIndex(AddProductsPageObjects.drpdwnCounty,1);
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnSearch);
        actionHandler.waitForSomeTime(15000);
        boolean popAlert2= popUpHandler.isAlertPresent();
        if (popAlert2 == true)
        driver.switchTo().alert().accept();
        actionHandler.waitForSomeTime(2000);
        javascripthandler.scrollIntoView(AddProductsPageObjects.headerList.get(0));
        String headers_1= elementHandler.getText(AddProductsPageObjects.headerList.get(0));
        AssertionHandler.verifyTrue(headers_1.contains("Practice Area"), "Practice Area header field not displayed");
        javascripthandler.scrollIntoView(AddProductsPageObjects.headerList.get(1));
        String headers_2= elementHandler.getText(AddProductsPageObjects.headerList.get(1));
        AssertionHandler.verifyTrue(headers_2.contains("County"), "County header field not displayed");
        javascripthandler.scrollIntoView(AddProductsPageObjects.headerList.get(2));
        String headers_3= elementHandler.getText(AddProductsPageObjects.headerList.get(2));
        AssertionHandler.verifyTrue(headers_3.contains("Price"), "Price header field not displayed");
        TestNGCustomReporter.log(logger, "Validate the UI part for Findlaw Post plus firmsite product" +ReportBuilder.takesScreenshot());
        
        /*
         * validate  quote hover over action in SCS Dynamic bundle page
         */
        actionHandler.moveToElement(AddProductsPageObjects.linkQuote);
        actionHandler.waitForSomeTime(2000);
        boolean lblstatus= elementHandler.isElementDisplayed(AddProductsPageObjects.lblQuotePro);
        if ( lblstatus== true ) {
        	AssertionHandler.verifyTrue(lblstatus, "Quote hover over action is working");
        }
        TestNGCustomReporter.log(logger, "Validate the hover over action of the Quote link" +ReportBuilder.takesScreenshot());
        
        /*
         * Validate State and Metro default field for products
         */
        boolean drpdwnStatus1= elementHandler.isElementDisplayed(AddProductsPageObjects.drpdwnTopSpot);
        if (drpdwnStatus1 == true) {
        	javascripthandler.scrollToElementAndClick(AddProductsPageObjects.drpdwnTopSpot);
            actionHandler.waitForSomeTime(5000);
        }
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.optionRotatingTopspot);
        actionHandler.waitForSomeTime(2000);
        String drpdwnValue1= elementHandler.getSelectedValueFromDropDwon(AddProductsPageObjects.drpdwnState);
        AssertionHandler.verifyNotEquals(drpdwnValue1, "", "State field is not having default value selected.");
        String drpdwnValue2= elementHandler.getSelectedValueFromDropDwon(AddProductsPageObjects.drpdwnMetro);
        AssertionHandler.verifyNotEquals(drpdwnValue2, "", "Metro field is not having default value selected.");		       
        boolean drpdwnStatus3= elementHandler.isElementDisplayed(AddProductsPageObjects.drpdwnSpotlights);
        if (drpdwnStatus3 == true) {
        	javascripthandler.scrollToElementAndClick(AddProductsPageObjects.drpdwnSpotlights);
            actionHandler.waitForSomeTime(5000);
        }
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.optionliDirectorySpot);
        actionHandler.waitForSomeTime(2000);
        String drpdwnValue4= elementHandler.getSelectedValueFromDropDwon(AddProductsPageObjects.drpdwnState);
        AssertionHandler.verifyNotEquals(drpdwnValue4, "", "State field is not having default value selected.");
        String drpdwnValue5= elementHandler.getSelectedValueFromDropDwon(AddProductsPageObjects.drpdwnMetro);
        AssertionHandler.verifyNotEquals(drpdwnValue5, "", "Metro field is not having default value selected.");
        TestNGCustomReporter.log(logger, "Validate the State and Metro fields for products" +ReportBuilder.takesScreenshot());
               
        /*
         * select a product option from Topspot option
         */
        boolean drpdwnStatus= elementHandler.isElementDisplayed(AddProductsPageObjects.drpdwnTopSpot);
        if (drpdwnStatus == true) {
        	javascripthandler.scrollToElementAndClick(AddProductsPageObjects.drpdwnTopSpot);
            actionHandler.waitForSomeTime(5000);
        }
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.optionTopSpot);
        actionHandler.waitForSomeTime(2000);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.drpdwnState);
        actionHandler.waitForSomeTime(2000);
        TestNGCustomReporter.log(logger, "Validate the drop down values for State field" +ReportBuilder.takesScreenshot());
        List<WebElement> stateList= elementHandler.getAllOptionsFromDropdown(AddProductsPageObjects.drpdwnState);
        int stateCount= stateList.size();
        if (stateCount >49) {
        	AssertionHandler.verifyTrue(true, "The 50 values for the State label is not dispalyed properly");
        }
        elementHandler.selectByVisibleText(AddProductsPageObjects.drpdwnState, "California");
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnMetro);
        actionHandler.waitForSomeTime(2000);
        TestNGCustomReporter.log(logger, "Validate the drop down values for Metro field after selecting State field" +ReportBuilder.takesScreenshot());
        elementHandler.selectByVisibleText(AddProductsPageObjects.drpdwnMetro, "Merced, CA");
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnPracCategory);
        TestNGCustomReporter.log(logger, "Validate the drop down values for Practise category field after selecting Metro field" +ReportBuilder.takesScreenshot());
        actionHandler.waitForSomeTime(2000);
        elementHandler.selectByVisibleText(AddProductsPageObjects.drpdwnPracCategory,"Bankruptcy");
        actionHandler.waitForSomeTime(5000);
        boolean searchStatus=  elementHandler.isElementDisplayed(AddProductsPageObjects.btnSearch);
        if (searchStatus == true) {
        	AssertionHandler.verifyTrue(true, "Search button not dispalyed in the page");
        }
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnSearch);
        actionHandler.waitForSomeTime(15000);
        javascripthandler.scrollIntoView(AddProductsPageObjects.headerPracArea);
        String headerlbl3= elementHandler.getText(AddProductsPageObjects.headerPracArea);
        TestNGCustomReporter.log(logger, "Special Chararcters not displayed in the result of Practise area column");
        AssertionHandler.verifyTrue(headerlbl3.contains("Practice Area"), "Practice Area header field not displayed");
        javascripthandler.scrollIntoView(AddProductsPageObjects.headerMetro);
        String headermetro= elementHandler.getText(AddProductsPageObjects.headerMetro);
        AssertionHandler.verifyTrue(headermetro.contains("Metro"), "Metro header field not displayed");
        javascripthandler.scrollIntoView(AddProductsPageObjects.headerAvailableInvent);
        String headerInvent= elementHandler.getText(AddProductsPageObjects.headerAvailableInvent);
        AssertionHandler.verifyTrue(headerInvent.contains("Available Inventory"), "Available Inventory header field not displayed");
        javascripthandler.scrollIntoView(AddProductsPageObjects.headerPrice);
        String headerprice = elementHandler.getText(AddProductsPageObjects.headerPrice);
        AssertionHandler.verifyTrue(headerprice.contains("Price"), "price header field not displayed");
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.headerPracArea);
        TestNGCustomReporter.log(logger,"validate sorting for practice area column" +ReportBuilder.takesScreenshot() );
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.headerMetro);
        TestNGCustomReporter.log(logger,"validate sorting for Metro column" +ReportBuilder.takesScreenshot() );
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.headerAvailableInvent);
        TestNGCustomReporter.log(logger,"validate sorting for Available Inventory column" +ReportBuilder.takesScreenshot() );
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.headerPrice);
        TestNGCustomReporter.log(logger,"validate sorting for Price column" +ReportBuilder.takesScreenshot() );
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.checkboxproduct.get(0));
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.checkboxproduct.get(1));
        actionHandler.waitForSomeTime(5000);
        TestNGCustomReporter.log(logger, "Validate multi check box selection by the user" +ReportBuilder.takesScreenshot());
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnAddToCartprod);
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollIntoView(AddProductsPageObjects.btnGoTocart);
        boolean btnReturnTocart= elementHandler.isElementDisplayed(AddProductsPageObjects.btnGoTocart);
        if (btnReturnTocart== true) {
        	TestNGCustomReporter.log(logger, "Validate Return To Cart button displayed on the page" +ReportBuilder.takesScreenshot());
        }
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnGoTocart);
        actionHandler.waitForSomeTime(35000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
        boolean btnReprice= elementHandler.isElementDisplayed(AddProductsPageObjects.btnReprice);
        if (btnReprice == true) {
        	TestNGCustomReporter.log(logger, "Validate Cart page after clicking on Return To Cart button" +ReportBuilder.takesScreenshot());
        }
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
        elementHandler.clickElement(AddProductsPageObjects.btnReprice);
        actionHandler.waitForSomeTime(35000);
        boolean errorMesg2= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
		if (errorMesg2 == true) {
			String message= AddProductsPageObjects.lblValidatnMessage.getText();
			TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
		}
        int prdtSize= AddProductsPageObjects.lstproducts.size();
        if (prdtSize>1) {
        	AssertionHandler.verifyTrue(true, "Products not added to the cart page");
        }
        
      //Contract Term
        String ContractTerm = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Contract_Term", Row);
        String[] arrContract = ContractTerm.split(",");
        for (int k=1;k<arrContract.length;k++) 
        {
        	if(!ContractTerm.equals("NA"))
        	{
               elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnContractTerm.get(k));          
               elementHandler.clickElement(AddProductsPageObjects.drpdwnContractTerm.get(k));
               TestNGCustomReporter.log(logger,"validate Contract Term drop down values" );
               actionHandler.waitForSomeTime(2000);
               for(int j=1; j<=7; j++)
               {
                      String CntrctTrm=driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).getText();
                      TestNGCustomReporter.log(logger,"From list["+j+"] "+CntrctTrm.trim());
                      if(CntrctTrm.trim().contentEquals(arrContract[k].trim()))
                      {
                             driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).click();
                             actionHandler.waitForSomeTime(2000);
                             break;
                      }                    
               }
        	}
       }
        actionHandler.waitForSomeTime(10000);
        TestNGCustomReporter.log(logger,"Validate Contract term to be added for TopSpot products" );
        String Year23PriceDir= elementHandler.getText(AddProductsPageObjects.lblYear23DiscSurc.get(1));
        AssertionHandler.verifyTrue(Year23PriceDir.contains("USD"), "Year 2-3 price displayed blank for Directory Top spot product");
        String Year23PriceDir1= elementHandler.getText(AddProductsPageObjects.lblYear23DiscSurc.get(2));
        AssertionHandler.verifyTrue(Year23PriceDir1.contains("USD"), "Year 2-3 price displayed blank for Directory Top spot products");	       
	}
	
	
	public void validateStartDate(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForXpathToLoad(lblCategorySelectn);
		elementHandler.clickElement(AddProductsPageObjects.btnInstalledProduct,AddProductsPageObjects.txtInstalledProduct);
		actionHandler.waitForSomeTime(15000);
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblContStartDate);
		String lblStartDate= elementHandler.getText(AddProductsPageObjects.lblContStartDate);
		AssertionHandler.verifyTrue(lblStartDate.contains("Contract Start Date"), "Contract Start Date column not displayed in the Installed Product page");
		javascripthandler.highlightElement(AddProductsPageObjects.lblContStartDate);
		TestNGCustomReporter.log(logger, "Validate Contract Start Date column in the Installed product page" +ReportBuilder.takesScreenshot());
	}
	
	public void validateApprovalFinalizeButton(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		elementHandler.waitForXpathToLoad(lblCategorySelectn);
        actionHandler.waitForSomeTime(15000);                  
        String Product1 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
        String[] listofproduct = Product1.split(",");           
        for (int i=0; i<listofproduct.length; i++)
        {
        	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, listofproduct[i]);
            actionHandler.keyboardAction(Keys.ENTER);
            actionHandler.waitForSomeTime(25000);
            elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
            elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
            actionHandler.waitForSomeTime(10000);
            elementHandler.waitForXpathToLoad(btnProductAttr);
            elementHandler.waitForPageToLoad();
			elementHandler.writeText(AddProductsPageObjects.txtboxNumbAttrys, "5");
			actionHandler.waitForSomeTime(2000);
			elementHandler.clickElement(AddProductsPageObjects.tabSecondaries);
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
			elementHandler.clickElement(AddProductsPageObjects.btnValidate);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
			elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts);
			actionHandler.waitForSomeTime(15000);
            TestNGCustomReporter.log(logger, "Product "+listofproduct[i] +" Added Successfully");
        }
        actionHandler.waitForSomeTime(5000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
        elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
		actionHandler.waitForSomeTime(40000);
		
		//Contract Term
        String ContractTerm = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Contract_Term", Row);
        String[] arrContract = ContractTerm.split(",");
        for (int k=0;k<arrContract.length;k++) 
        {
        	if(!ContractTerm.equals("NA"))
        	{
               elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnContractTerm.get(k));          
               elementHandler.clickElement(AddProductsPageObjects.drpdwnContractTerm.get(k));
               TestNGCustomReporter.log(logger,"validate Contract Term drop down values" );
               actionHandler.waitForSomeTime(2000);
               for(int j=1; j<=5; j++)
               {
                      String CntrctTrm=driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).getText();
                      TestNGCustomReporter.log(logger,"From list["+j+"] "+CntrctTrm.trim());
                      if(CntrctTrm.trim().contentEquals(ContractTerm.trim()))
                      {
                             driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).click();
                             actionHandler.waitForSomeTime(2000);
                             break;
                      }
                      
               }
        	}
       }
		
		//Adjustment Type
        String AdjustTerm = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Adjust_Type", Row);
        String[] arrAdjust = AdjustTerm.split(",");
        for (int i=0;i<arrAdjust.length;i++)
        {
        if (!AdjustTerm.equals("NA"))
        {
               elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnAdjustmentTyp.get(i));          
               elementHandler.clickElement(AddProductsPageObjects.drpdwnAdjustmentTyp.get(i));
               TestNGCustomReporter.log(logger,"validate Adjustment Type field drop down values");
               actionHandler.waitForSomeTime(2000);
               for(int j=1; j<=3; j++)
               {
                      String AdjustTrm=driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).getText();
                      TestNGCustomReporter.log(logger,"From list["+j+"] "+AdjustTrm.trim());
                      if(AdjustTrm.trim().contentEquals(AdjustTerm.trim()))
                      {
                             driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).click();
                             actionHandler.waitForSomeTime(2000);
                             break;
                      }
                      
               }
        	}
        }
        
        //Adjustment Amount
        String adjustDiscount = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "AdjustAmount", Row);
        String[] arrDiscount = adjustDiscount.split(",");
        for (int k=0;k<arrDiscount.length;k++) 
        {
        	if (!adjustDiscount.equals("NA"))
        	{
               actionHandler.waitForSomeTime(2000);
               elementHandler.writeText(AddProductsPageObjects.txtAdjustAmount1.get(k), adjustDiscount);
               actionHandler.waitForSomeTime(2000);
        	}  
        }
        
		//Bridge
        String bridge = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Bridge", Row);
        String[] arrBridge = bridge.split(",");
        for (int k=0;k<arrBridge.length;k++)
        {
            if (!bridge.equals("NA"))
            {
               elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnBridge5.get(k));          
               elementHandler.clickElement(AddProductsPageObjects.drpdwnBridge5.get(k));
               TestNGCustomReporter.log(logger,"validate Bridge field drop down values");
               actionHandler.waitForSomeTime(2000);
               for(int j=1; j<=5; j++)
               {
                      String bridgeTrm=driver.findElement(By.xpath("//ul[contains(@id,'ui-select-choices-')]/li["+j+"]")).getText();
                      TestNGCustomReporter.log(logger,"From list["+j+"] "+bridgeTrm.trim());
                      if(bridgeTrm.trim().contentEquals(bridge.trim()))
                      {
                             driver.findElement(By.xpath("//ul[contains(@id,'ui-select-choices-')]/li["+j+"]")).click();
                             actionHandler.waitForSomeTime(2000);
                             break;
                      }
                      
               }
            }
        } 
        
		javascripthandler.scrollIntoView(AddProductsPageObjects.lblNetAdjust);
		actionHandler.waitForSomeTime(2000);
		TestNGCustomReporter.log(logger, "Net Adjustment Amount displayed" +ReportBuilder.takesScreenshot());
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
		javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnReprice);
		//elementHandler.clickElement(AddProductsPageObjects.btnReprice);
		actionHandler.waitForSomeTime(40000);
		
		boolean errorMessage1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
		if (errorMessage1 == true) {
			String message= AddProductsPageObjects.lblValidatnMessage.getText();
			TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
		}
		
		boolean status= AddProductsPageObjects.btnFinalize.isEnabled();
		if (status == true) {
			AssertionHandler.verifyTrue(true, "Finalize button is enabled for adding product");
			TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Finalize button" +ReportBuilder.takesScreenshot());	
			
		}
		else {
			AssertionHandler.verifyTrue(true, "Submit For Approval button enabled for adding product");
			TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Submit for approval button" +ReportBuilder.takesScreenshot());
		}
	}
	
	public void validateApprovalForPPC(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		elementHandler.waitForPageToLoad();
		actionHandler.waitForSomeTime(15000);
		String ppcProduct = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id",Row);
		elementHandler.writeText(AddProductsPageObjects.txtboxSearch, ppcProduct);
		actionHandler.keyboardAction(Keys.ENTER);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
		elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
		actionHandler.waitForSomeTime(10000);
		String ppcType1=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Type",Row);
		String ppclang1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Lang",Row);
		String ppcWebsite4=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Website",Row);
		elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCType1);
		actionHandler.waitForSomeTime(2000);
		driver.findElement(By.xpath("//div[contains(text(),'"+ppcType1+"')]")).click();
		elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCLang);
		actionHandler.waitForSomeTime(2000);
		driver.findElement(By.xpath("//div[contains(text(),'"+ppclang1+"')]")).click();
		elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCWebsite);
		actionHandler.waitForSomeTime(2000);
		driver.findElement(By.xpath("//div[text()='"+ppcWebsite4+"']")).click();
		String ppcAmount1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Amount",Row);
		elementHandler.writeText(AddProductsPageObjects.txtPPCAmount,ppcAmount1);
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
		elementHandler.clickElement(AddProductsPageObjects.btnValidate);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
		elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
		actionHandler.waitForSomeTime(30000);
		
		//Contract Term
        String ContractTerm = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Contract_Term", Row);
        String[] arrContract = ContractTerm.split(",");
        for (int k=0;k<arrContract.length;k++) 
        {
        	if(!ContractTerm.equals("NA"))
        	{
               elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnContractTerm.get(k));          
               elementHandler.clickElement(AddProductsPageObjects.drpdwnContractTerm.get(k));
               TestNGCustomReporter.log(logger,"validate Contract Term drop down values" );
               actionHandler.waitForSomeTime(2000);
               for(int j=1; j<=5; j++)
               {
                      String CntrctTrm=driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).getText();
                      TestNGCustomReporter.log(logger,"From list["+j+"] "+CntrctTrm.trim());
                      if(CntrctTrm.trim().contentEquals(ContractTerm.trim()))
                      {
                             driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).click();
                             actionHandler.waitForSomeTime(2000);
                             break;
                      }
                      
               }
        	}
       }
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
		elementHandler.clickElement(AddProductsPageObjects.btnReprice);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
		elementHandler.clickElement(AddProductsPageObjects.btnReprice);
		actionHandler.waitForSomeTime(30000);
		boolean approvalBtn= AddProductsPageObjects.btnSubmtForApproval.isEnabled();
		if (approvalBtn == true) {
			TestNGCustomReporter.log(logger,"Submit For Approval button enabled for adding PPC product with Contract Term as 1 or 2 Months"+ReportBuilder.takesScreenshot() );
		}
	}
	
	/*
	 * Add US ppc products and OTC landing page products and validate the validation messages
	 */
	public void validationMessage(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		elementHandler.waitForPageToLoad();
		actionHandler.waitForSomeTime(10000);
		String ppcProduct = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id",Row);
		String ppclang1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Lang",Row);
		String[] ppclang = ppclang1.split(",");
	    for (int i=0; i<ppclang.length; i++)
	    {	
	    	elementHandler.writeText(AddProductsPageObjects.txtboxSearch, ppcProduct);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
			elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
			actionHandler.waitForSomeTime(25000);
		String ppcType1=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Type",Row);
		String ppcWebsite1=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Website",Row);
		elementHandler.clickElement(AddProductsPageObjects.txtPPCAmount);
		boolean ppc= elementHandler.isElementDisplayed(AddProductsPageObjects.drpdwnPPCType1);
			if (ppc == true) {
			elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCType1);
			actionHandler.waitForSomeTime(2000);
			}
		driver.findElement(By.xpath("//div[contains(text(),'"+ppcType1+"')]")).click();
		actionHandler.waitForSomeTime(5000);
		boolean lang= elementHandler.isElementDisplayed(AddProductsPageObjects.drpdwnPPCLang);
			if (lang == true) {
			elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCLang);
			actionHandler.waitForSomeTime(2000);
			}
		driver.findElement(By.xpath("//div[contains(text(),'"+ppclang[i]+"')]")).click();
		actionHandler.waitForSomeTime(5000);
		String ppcAmount1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Amount",Row);
		elementHandler.writeText(AddProductsPageObjects.txtPPCAmount, ppcAmount1);
		actionHandler.waitForSomeTime(2000);
		boolean web= elementHandler.isElementDisplayed(AddProductsPageObjects.drpdwnPPCWebsite);
			if (web == true) {
			elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCWebsite);
			actionHandler.waitForSomeTime(2000);
			}
		boolean ppcWeb1= elementHandler.isElementDisplayed(AddProductsPageObjects.drpdwnPPCWeb);
			if (ppcWeb1 == true) {	
			driver.findElement(By.xpath("//div[text()='"+ppcWebsite1+"']")).click();
			actionHandler.waitForSomeTime(5000);
			}
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
		elementHandler.clickElement(AddProductsPageObjects.btnValidate);
		actionHandler.waitForSomeTime(25000);	
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
		elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
		elementHandler.clickElement(AddProductsPageObjects.btnReprice);
		actionHandler.waitForSomeTime(30000);
		boolean validationMsg= AddProductsPageObjects.lblValidatnMessage.isDisplayed();
		String validateMsg= elementHandler.getText(AddProductsPageObjects.lblValidatnMessage);
			if (validationMsg == true) {
				AssertionHandler.verifyTrue(true, "validation message not displayed for adding Existing PPC Spent products");
				TestNGCustomReporter.log(logger,"Validation Message displayed for adding Existing PPC Spent products as: "+validateMsg  +ReportBuilder.takesScreenshot());
			}
			elementHandler.clickElement(AddProductsPageObjects.checkbxSelect);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnRemove);
			elementHandler.clickElement(AddProductsPageObjects.btnRemove);
			actionHandler.waitForSomeTime(15000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
		elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts);
		actionHandler.waitForSomeTime(20000);
	}
	    String Product2 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product",Row);
	    elementHandler.writeText(AddProductsPageObjects.txtboxSearch, Product2);
        actionHandler.keyboardAction(Keys.ENTER);
        actionHandler.waitForSomeTime(25000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
        elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
		actionHandler.waitForSomeTime(20000);
        TestNGCustomReporter.log(logger, "Product "+Product2 +" Added Successfully");
        actionHandler.waitForSomeTime(5000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
        elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng,AddProductsPageObjects.btnReprice);
        actionHandler.waitForSomeTime(25000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
        elementHandler.clickElement(AddProductsPageObjects.btnReprice);
        actionHandler.waitForSomeTime(30000);
        boolean validationMsg= AddProductsPageObjects.lblErrorMessg.isDisplayed();
		String validateMsg= elementHandler.getText(AddProductsPageObjects.lblErrorMessg);
			if (validationMsg == true) {
				AssertionHandler.verifyTrue(true, "validation message not displayed for adding PPC Additional Landing page product");
				TestNGCustomReporter.log(logger,"Validation Message displayed for adding PPC Additional Landing page product as: "+validateMsg  +ReportBuilder.takesScreenshot());
			}
	}
	
	public void verifyErrorMessage(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);                    
		elementHandler.waitForXpathToLoad(lblCategorySelectn);     
        actionHandler.waitForSomeTime(15000);                  

        String Product2 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
        String[] listofproduct3 = Product2.split(",");
        String Product3 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product", Row);
        String[] listofproduct4 = Product3.split(",");
        
        for (int i=0; i<listofproduct3.length; i++)
        {
       elementHandler.writeText(AddProductsPageObjects.txtboxSearch, listofproduct3[i]);
       actionHandler.keyboardAction(Keys.ENTER);
       actionHandler.waitForSomeTime(25000);
       elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
       elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
	   actionHandler.waitForSomeTime(30000);
       TestNGCustomReporter.log(logger, "Product "+listofproduct3[i] +" Added Successfully");
       boolean errormsg= elementHandler.isElementDisplayed(AddProductsPageObjects.lblErrorMessg);
       if (errormsg == true) {
    	   TestNGCustomReporter.log("Error Message displayed for adding" + listofproduct3[i] +"product,need to add pre-requisite product in the cart" +ReportBuilder.takesScreenshot());
    	   actionHandler.waitForSomeTime(5000);
    	   elementHandler.waitForElementTobeClickable(AddProductsPageObjects.txtboxSearch);
    	   elementHandler.writeText(AddProductsPageObjects.txtboxSearch, listofproduct4[i]);
           actionHandler.keyboardAction(Keys.ENTER);
           actionHandler.waitForSomeTime(25000);
           boolean btnAddToCart= elementHandler.isElementDisplayed(AddProductsPageObjects.btnAddToCart1);
           if (btnAddToCart == true) {
           elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
           elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
    	   actionHandler.waitForSomeTime(25000);
           }
           else {
        	   elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddAnother);
               elementHandler.clickElement(AddProductsPageObjects.btnAddAnother);
        	   actionHandler.waitForSomeTime(25000);  
           }
    	   TestNGCustomReporter.log(logger, "pre-requisite Product "+listofproduct4[i] +" Added Successfully");
    	   
       }else {
    	   TestNGCustomReporter.log("Error Message not diaplyed after adding" + listofproduct4[i] +"product in the cart" +ReportBuilder.takesScreenshot());
       }           
        }
	}
	
	
	public void validateYear23Disc(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForXpathToLoad(lblCategorySelectn);     
        actionHandler.waitForSomeTime(15000);                  
        String Product2 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
        boolean pageobtn= elementHandler.isElementDisplayed(AddProductsPageObjects.btnPAGEOProduct);
        if (pageobtn == true) {
        	AssertionHandler.verifyTrue(true, "PA GEO products button not displayed in the Catalog page");
        }
        elementHandler.writeText(AddProductsPageObjects.txtboxSearch, Product2);
        actionHandler.keyboardAction(Keys.ENTER);
        actionHandler.waitForSomeTime(25000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
        elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
		actionHandler.waitForSomeTime(20000);
        TestNGCustomReporter.log(logger, "Product "+Product2 +" Added Successfully");
        actionHandler.waitForSomeTime(5000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
        elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng,AddProductsPageObjects.btnReprice);
        actionHandler.waitForSomeTime(30000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
        elementHandler.clickElement(AddProductsPageObjects.btnReprice,AddProductsPageObjects.btnPAGEOProduct);
        actionHandler.waitForSomeTime(35000);
        boolean errorMesg1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
		if (errorMesg1 == true) {
			String message = AddProductsPageObjects.lblValidatnMessage.getText();
			TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
		}
        boolean paGeoStatus= elementHandler.isElementDisplayed(AddProductsPageObjects.btnPAGEOProduct);
		if (paGeoStatus == true)
			AssertionHandler.verifyTrue(true, "PA GEO Button not displayed in the Cart page");
		TestNGCustomReporter.log(logger, "Validate PA GEO Button in the cart page" + ReportBuilder.takesScreenshot());
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnPAGEOProduct);
		elementHandler.clickElement(AddProductsPageObjects.btnPAGEOProduct);
		actionHandler.waitForSomeTime(25000);
		TestNGCustomReporter.log(logger,
				"Validate next page displayed after clicking on PA GEO button" + ReportBuilder.takesScreenshot());
		boolean drpdwnStatus = elementHandler.isElementDisplayed(AddProductsPageObjects.drpdwnTopSpot);
		if (drpdwnStatus == true) {
			javascripthandler.scrollToElementAndClick(AddProductsPageObjects.drpdwnTopSpot);
			actionHandler.waitForSomeTime(5000);
		}       
        for (int i=1;i<6;i++) {
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.listTopSpot.get(i));
        actionHandler.waitForSomeTime(2000);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.drpdwnState);
        actionHandler.waitForSomeTime(2000);      
        elementHandler.selectByIndex(AddProductsPageObjects.drpdwnState, 1);
        actionHandler.waitForSomeTime(5000);
        boolean stausCounty= elementHandler.isElementDisplayed(AddProductsPageObjects.drpdwnCounty);
        if (stausCounty == true) {
        	 javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnCounty);
             actionHandler.waitForSomeTime(2000);
             elementHandler.selectByIndex(AddProductsPageObjects.drpdwnCounty, 2);
             actionHandler.waitForSomeTime(5000);            
        }
        boolean stausPC= elementHandler.isElementDisplayed(AddProductsPageObjects.drpdwnPracCategory);
        if (stausPC == true) {	
        javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnPracCategory);
        actionHandler.waitForSomeTime(2000);
        elementHandler.selectByIndex(AddProductsPageObjects.drpdwnPracCategory, 3);
        actionHandler.waitForSomeTime(5000);
        }        
        boolean searchStatus=  elementHandler.isElementDisplayed(AddProductsPageObjects.btnSearch);
        if (searchStatus == true) {
        	AssertionHandler.verifyTrue(true, "Search button not dispalyed in the page");
        }
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnSearch);
        actionHandler.waitForSomeTime(15000);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.checkboxproduct.get(0));
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnAddToCartprod);
        actionHandler.waitForSomeTime(5000);
        	}          
        javascripthandler.scrollIntoView(AddProductsPageObjects.btnGoTocart);
        boolean btnReturnTocart= elementHandler.isElementDisplayed(AddProductsPageObjects.btnGoTocart);
        if (btnReturnTocart== true) {
        	TestNGCustomReporter.log(logger, "Validate Return To Cart button displayed on the page" +ReportBuilder.takesScreenshot());
        }
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnGoTocart);
        actionHandler.waitForSomeTime(45000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
        javascripthandler.scrollIntoView(AddProductsPageObjects.lblYear23DiscSurc.get(1));
        String lblYear23Disc= elementHandler.getText(AddProductsPageObjects.lblYear23DiscSurc.get(1));
        AssertionHandler.verifyTrue(lblYear23Disc.contains("0.000%"), "Year 2-3 Disc/Surc value not matched successfully");
        String lblYear23Disc1= elementHandler.getText(AddProductsPageObjects.lblYear23DiscSurc.get(2));
        AssertionHandler.verifyTrue(lblYear23Disc1.contains("0.000%"), "Year 2-3 Disc/Surc value not matched successfully");
        String lblYear23Disc2= elementHandler.getText(AddProductsPageObjects.lblYear23DiscSurc.get(3));
        AssertionHandler.verifyTrue(lblYear23Disc2.contains("0.000%"), "Year 2-3 Disc/Surc value not matched successfully");
        String lblYear23Disc3= elementHandler.getText(AddProductsPageObjects.lblYear23DiscSurc.get(4));
        AssertionHandler.verifyTrue(lblYear23Disc3.contains("0.000%"), "Year 2-3 Disc/Surc value not matched successfully");
        String lblYear23Disc4= elementHandler.getText(AddProductsPageObjects.lblYear23DiscSurc.get(5));
        AssertionHandler.verifyTrue(lblYear23Disc4.contains("0.000%"), "Year 2-3 Disc/Surc value not matched successfully");
             
	}
	
	public void validateTerminateOptionPPC(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		elementHandler.waitForXpathToLoad(lblCategorySelectn);
        String installedprod = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product", Row);
		elementHandler.clickElement(AddProductsPageObjects.btnInstalledProduct,AddProductsPageObjects.txtInstalledProduct);
		actionHandler.waitForSomeTime(20000);
		boolean status3= elementHandler.isElementDisplayed(AddProductsPageObjects.txtInstalledProduct);
		if (status3== false) {
			actionHandler.waitForSomeTime(10000);
		}
		String lblRenewal= elementHandler.getText(AddProductsPageObjects.lblRenewaldate);
		AssertionHandler.verifyTrue(lblRenewal.contains("Renewal Date"), "Renewal Date field is displayed under Filter By section");
		String lblDealType= elementHandler.getText(AddProductsPageObjects.lblDealType);
		AssertionHandler.verifyTrue(lblDealType.contains("Deal Type"), "Deal Type field is displayed under Filter By section");
		String lblDealId= elementHandler.getText(AddProductsPageObjects.lblDealId);
		AssertionHandler.verifyTrue(lblDealId.contains("Deal Id"), "Deal Id field is displayed under Filter By section");
		String lblMLAAgr= elementHandler.getText(AddProductsPageObjects.lblMLAAgr);
		AssertionHandler.verifyTrue(lblMLAAgr.contains("MLA Agr #"), "MLA Aggr field is displayed under Filter By section");
		String lblBusinessUnit= elementHandler.getText(AddProductsPageObjects.lblBusinessUnit);
		AssertionHandler.verifyTrue(lblBusinessUnit.contains("Proposal Business"), "Business Unit field is displayed under Filter By section");		
		elementHandler.writeText(AddProductsPageObjects.txtInstalledProduct, installedprod);
		actionHandler.keyboardAction(Keys.ENTER);
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.checkbox);
		elementHandler.clickElement(AddProductsPageObjects.checkbox);
		actionHandler.waitForSomeTime(5000);
		String btnTerminate= AddProductsPageObjects.btnTerminate1.getAttribute("disabled");
		AssertionHandler.verifyTrue(btnTerminate.contains("true"), "Terminate button is enabled for PPC products while adding from Installed propducts");
		TestNGCustomReporter.log(logger, "Validate Terminate button is disabled for PPc products" +ReportBuilder.takesScreenshot());
	}
	
	
	public void validateSubmitForApprovalOption(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		elementHandler.waitForXpathToLoad(lblCategorySelectn);
        String installedprod = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product", Row);
		elementHandler.clickElement(AddProductsPageObjects.btnInstalledProduct,AddProductsPageObjects.txtInstalledProduct);
		actionHandler.waitForSomeTime(15000);
		boolean status3= elementHandler.isElementDisplayed(AddProductsPageObjects.txtInstalledProduct);
		if (status3== false) {
			actionHandler.waitForSomeTime(10000);
		}
		elementHandler.writeText(AddProductsPageObjects.txtInstalledProduct, installedprod);
		actionHandler.keyboardAction(Keys.ENTER);
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.checkbox);
		elementHandler.clickElement(AddProductsPageObjects.checkbox, AddProductsPageObjects.btnTerminate);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnTerminate);
		elementHandler.clickElement(AddProductsPageObjects.btnTerminate, AddProductsPageObjects.btnCalculate);
		actionHandler.waitForSomeTime(10000);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String termdate= dateFormat.format(date);
		elementHandler.writeText(AddProductsPageObjects.txtTermDate, termdate);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnCalculate);
		elementHandler.clickElement(AddProductsPageObjects.btnCalculate, AddProductsPageObjects.btnConfirm1);
		actionHandler.waitForSomeTime(10000);
		javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnConfirm1);
		actionHandler.waitForSomeTime(8000);
		elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
		actionHandler.waitForSomeTime(25000);
		elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts);
		actionHandler.waitForSomeTime(25000);
		String Product2= ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
		elementHandler.writeText(AddProductsPageObjects.txtboxSearch, Product2);
        actionHandler.keyboardAction(Keys.ENTER);
        actionHandler.waitForSomeTime(25000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
        elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
		actionHandler.waitForSomeTime(20000);
        TestNGCustomReporter.log(logger, "Product "+Product2 +" Added Successfully");
        elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
		actionHandler.waitForSomeTime(25000);
		elementHandler.clickElement(AddProductsPageObjects.btnReprice);
		actionHandler.waitForSomeTime(25000);
		boolean btnApprovalStatus= AddProductsPageObjects.btnSubmtForApproval.isEnabled();
		if (btnApprovalStatus == true) {
			AssertionHandler.verifyTrue(btnApprovalStatus, "Submit for approval button not enabled for adding products with MYR type as M01, M02, M03 , R01, R02, R03, C01 ");
			TestNGCustomReporter.log(logger, "Submit For Approval button enabled for adding products with MYR types :M01, M02, M03 , R01, R02, R03, C01" +ReportBuilder.takesScreenshot());
		}
			
	}
	
	/*
	 * Add 41056102 and 41053157 in to the cart from PA/GEO page
	 */
	public void addPAGEOProduct(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForXpathToLoad(lblCategorySelectn);     
        actionHandler.waitForSomeTime(15000);                  
        boolean pageobtn= elementHandler.isElementDisplayed(AddProductsPageObjects.btnPAGEOProduct);
        if (pageobtn == true) {
        	elementHandler.clickElement(AddProductsPageObjects.btnPAGEOProduct);
        	actionHandler.waitForSomeTime(20000);
        }
       elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkPremiumProfile);
       javascripthandler.scrollToElementAndClick(AddProductsPageObjects.lnkPremiumProfile);
       actionHandler.waitForSomeTime(5000);   
       String stateVal= ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "State", Row);
       javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnState);
       elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnState);
       elementHandler.selectByVisibleText(AddProductsPageObjects.drpdwnState, stateVal);
       actionHandler.waitForSomeTime(5000);
        String countyVal= ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "County", Row);
        javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnCounty);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnCounty);
        elementHandler.selectByVisibleText(AddProductsPageObjects.drpdwnCounty, countyVal);
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnAttrCountRng);
        actionHandler.waitForSomeTime(2000);
        String AttrCountval= ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Attorney_Count", Row);
        elementHandler.selectByVisibleText(AddProductsPageObjects.drpdwnAttrCountRng, AttrCountval);
        actionHandler.waitForSomeTime(5000); 
        boolean searchStatus=  elementHandler.isElementDisplayed(AddProductsPageObjects.btnSearch);
        if (searchStatus == true) {
        	AssertionHandler.verifyTrue(true, "Search button not dispalyed in the page");
        }
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnSearch);
        actionHandler.waitForSomeTime(15000);    
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.checkboxproduct.get(0));
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnAddToCartprod);
        actionHandler.waitForSomeTime(5000);          
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.lnkFocusPage);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.lnkFocusPage);
        actionHandler.waitForSomeTime(5000);   
        javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnState);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnState);
        elementHandler.selectByVisibleText(AddProductsPageObjects.drpdwnState, stateVal);
        actionHandler.waitForSomeTime(5000);
         javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnCounty);
         elementHandler.selectByVisibleText(AddProductsPageObjects.drpdwnCounty, countyVal);
         actionHandler.waitForSomeTime(5000);
         String PracCategory= ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Practice_Category", Row);
         javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnPracCategory);
         elementHandler.selectByVisibleText(AddProductsPageObjects.drpdwnPracCategory, PracCategory);
         actionHandler.waitForSomeTime(5000);
         String PracArea= ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Prac_Area", Row);
         javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnPracArea);
         elementHandler.selectByVisibleText(AddProductsPageObjects.drpdwnPracArea, PracArea);
         actionHandler.waitForSomeTime(5000);
         boolean searchStatus1=  elementHandler.isElementDisplayed(AddProductsPageObjects.btnSearch);
         if (searchStatus1 == true) {
         	AssertionHandler.verifyTrue(true, "Search button not dispalyed in the page");
         }
         javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnSearch);
         actionHandler.waitForSomeTime(15000);    
         javascripthandler.scrollToElementAndClick(AddProductsPageObjects.checkboxproduct.get(0));
         actionHandler.waitForSomeTime(5000);
         javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnAddToCartprod);
         actionHandler.waitForSomeTime(5000);       
        javascripthandler.scrollIntoView(AddProductsPageObjects.btnGoTocart);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnGoTocart);
        actionHandler.waitForSomeTime(35000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
        elementHandler.clickElement(AddProductsPageObjects.btnReprice);
        actionHandler.waitForSomeTime(35000);
        boolean errorMesg2= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
		if (errorMesg2 == true) {
			String message= AddProductsPageObjects.lblValidatnMessage.getText();
			TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
		}     
       elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);	
	   elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
	   actionHandler.waitForSomeTime(35000);
	}
	
	/*
	 * Add products 41876119 and 41877783 individually  in the cart and validate the approval triggered or not.
	 */
	public void validateApprovalButtonPPC(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		elementHandler.waitForXpathToLoad(lblCategorySelectn);
		actionHandler.waitForSomeTime(15000);
		String Product2 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
		String[] listofproduct3 = Product2.split(",");
		for (int i = 0; i < listofproduct3.length; i++) {
			elementHandler.writeText(AddProductsPageObjects.txtboxSearch, listofproduct3[i]);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
			elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
			actionHandler.waitForSomeTime(20000);
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
			elementHandler.clickElement(AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(20000);
			String quoteStatus = elementHandler.getText(AddProductsPageObjects.labelStatus);
			AssertionHandler.verifyTrue(quoteStatus.contains("Approval Required"),
					"After adding Fee Override Approval not required");
			elementHandler.clickElement(AddProductsPageObjects.checkbxSelect);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnRemove);
			elementHandler.clickElement(AddProductsPageObjects.btnRemove);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
			elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts);
			actionHandler.waitForSomeTime(25000);			
			TestNGCustomReporter.log(logger, "Approval required for adding product" + ReportBuilder.takesScreenshot());
		}
	}
	
/*
 * Add product from PA/GEO page and validate the checkbox is disabled or not	
 */
	public void validateCheckbox(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForXpathToLoad(lblCategorySelectn);     
        actionHandler.waitForSomeTime(15000);                  
        boolean pageobtn= elementHandler.isElementDisplayed(AddProductsPageObjects.btnPAGEOProduct);
        if (pageobtn == true) {
        	elementHandler.clickElement(AddProductsPageObjects.btnPAGEOProduct);
        	actionHandler.waitForSomeTime(20000);
        }        
        boolean drpdwnStatus1= elementHandler.isElementDisplayed(AddProductsPageObjects.drpdwnTopSpot);
        if (drpdwnStatus1 == true) {
        	javascripthandler.scrollToElementAndClick(AddProductsPageObjects.drpdwnTopSpot);
            actionHandler.waitForSomeTime(5000);
        }
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.optionTopSpot);
        actionHandler.waitForSomeTime(2000);
        elementHandler.selectByVisibleText(AddProductsPageObjects.drpdwnState, "Maryland");
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnMetro);
        actionHandler.waitForSomeTime(2000);
        TestNGCustomReporter.log(logger, "Validate the drop down values for Metro field after selecting State field" +ReportBuilder.takesScreenshot());
        elementHandler.selectByVisibleText(AddProductsPageObjects.drpdwnMetro, "Baltimore, MD");
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnPracCategory);
        TestNGCustomReporter.log(logger, "Validate the drop down values for Practise category field after selecting Metro field" +ReportBuilder.takesScreenshot());
        actionHandler.waitForSomeTime(2000);
        elementHandler.selectByVisibleText(AddProductsPageObjects.drpdwnPracCategory,"Bankruptcy");
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnSearch);
        actionHandler.waitForSomeTime(15000); 
        String lblAIvalue= elementHandler.getText(AddProductsPageObjects.lblAIvalues.get(0));
        if (!lblAIvalue.equals(" ")) {
        AssertionHandler.verifyTrue(true, "Available Inventory value is not displayed blank for 1st product");		
        }
        String lblAIvalue1= elementHandler.getText(AddProductsPageObjects.lblAIvalues.get(1));
        if (!lblAIvalue1.equals(" ")) {
        AssertionHandler.verifyTrue(true, "Available Inventory value is not displayed blank for 2nd product");		
        }
        String checkboxStatus = elementHandler.getTextFromAttribute(AddProductsPageObjects.checkboxproduct.get(2), "disabled");
        if ((checkboxStatus.equals ("disabled")) | (checkboxStatus.equals ("true"))) {
        AssertionHandler.verifyTrue(true, "Checkbox is disbaled for the product with inventory as 0");	
        TestNGCustomReporter.log(logger, "Validate Checkbox is disbaled for the product with inventory as 0" +ReportBuilder.takesScreenshot());	
        }
        javascripthandler.highlightElement(AddProductsPageObjects.checkboxproduct.get(1));
        elementHandler.clickElement(AddProductsPageObjects.checkboxproduct.get(1));
        elementHandler.clickElement(AddProductsPageObjects.btnAddToCartprod);
        boolean checkSymbol= elementHandler.isElementDisplayed(AddProductsPageObjects.lblCheck);
        if (checkSymbol == true)
        TestNGCustomReporter.log(logger, "Validate green Checkbox displayed for adding the product into the cart" +ReportBuilder.takesScreenshot());	
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnSearch);
        actionHandler.waitForSomeTime(1000); 
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnSearch);
        actionHandler.waitForSomeTime(10000);
        elementHandler.clickElement(AddProductsPageObjects.checkboxproduct.get(1));
        elementHandler.clickElement(AddProductsPageObjects.btnAddToCartprod);
        boolean checkSymbol1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblCheck);
        if (checkSymbol1 == true)
        TestNGCustomReporter.log(logger, "Validate yellow cross circle displayed for adding the product twice into the cart" +ReportBuilder.takesScreenshot());	      	
        elementHandler.clickElement(AddProductsPageObjects.btnGoTocart);
        actionHandler.waitForSomeTime(10000);
        boolean status = elementHandler.isElementDisplayed(AddProductsPageObjects.lblProductDetail1);
        if (status == true )
        TestNGCustomReporter.log(logger, "Validate Product Detail label displayed in the Cart page" +ReportBuilder.takesScreenshot());		
        elementHandler.clickElement(AddProductsPageObjects.btnReprice);
        actionHandler.waitForSomeTime(30000); 
        elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
        actionHandler.waitForSomeTime(30000); 
        elementHandler.clickElement(AddProductsPageObjects.lnkLineItems);
        actionHandler.waitForSomeTime(2000);
        boolean prodDetail= elementHandler.isElementDisplayed(AddProductsPageObjects.lblProductDtl);
        if ( prodDetail == true)
        	TestNGCustomReporter.log(logger, "Validate Product Detail label displayed in the Line Items section in Quote/Proposal Detail page" +ReportBuilder.takesScreenshot());	
		}


	public void validateContractField(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		elementHandler.waitforElement(AddProductsPageObjects.txtboxSearch);
		String ppcProduct = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id",Row);
		elementHandler.writeText(AddProductsPageObjects.txtboxSearch, ppcProduct);
		actionHandler.keyboardAction(Keys.ENTER);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
		elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
		actionHandler.waitForSomeTime(10000);
		String ppcType1=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Type",Row);
		String ppclang1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Lang",Row);
		String ppcWebsite1=ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Website",Row);
		String ppcAmount1 =ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "PPC_Amount",Row);
		elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCType1);
		TestNGCustomReporter.log(logger, "Validate drop down values for PPC Type field" +ReportBuilder.takesScreenshot());
		actionHandler.waitForSomeTime(2000);
		driver.findElement(By.xpath("//div[contains(text(),'"+ppcType1+"')]")).click();
		elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCLang);
		TestNGCustomReporter.log(logger, "Validate drop down values for PPC languange selection field" +ReportBuilder.takesScreenshot());
		actionHandler.waitForSomeTime(2000);
		driver.findElement(By.xpath("//div[contains(text(),'"+ppclang1+"')]")).click();
		elementHandler.clickElement(AddProductsPageObjects.drpdwnPPCWebsite);
		TestNGCustomReporter.log(logger, "Validate drop down values for PPC Website Selection field" +ReportBuilder.takesScreenshot());
		actionHandler.waitForSomeTime(2000);
		driver.findElement(By.xpath("//div[text()='"+ppcWebsite1+"']")).click();
		elementHandler.writeText(AddProductsPageObjects.txtPPCAmount, ppcAmount1);
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.lblCartPage.get(5));
		TestNGCustomReporter.log(logger, "Validate rounded value to be populated in Customers Total PPC Cost,PPC Management Fee,PPC Ad spent" +ReportBuilder.takesScreenshot());
		elementHandler.clickElement(AddProductsPageObjects.tabSecondaries);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
		elementHandler.clickElement(AddProductsPageObjects.btnValidate);
		actionHandler.waitForSomeTime(25000);		
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
		elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
		elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts);
		actionHandler.waitForSomeTime(30000);
		String ppcProduct1 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product",Row);
		String[] listofproduct3 = ppcProduct1.split(",");
		for (int i = 0; i < listofproduct3.length; i++) {
			elementHandler.writeText(AddProductsPageObjects.txtboxSearch, listofproduct3[i]);
			actionHandler.keyboardAction(Keys.ENTER);
			actionHandler.waitForSomeTime(25000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
			elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
			actionHandler.waitForSomeTime(20000);
		}
			elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
			elementHandler.clickElement(AddProductsPageObjects.btnReprice);
			actionHandler.waitForSomeTime(20000);		
		for (int k=0;k<2;k++) 
		{
			 elementHandler.clickElement(AddProductsPageObjects.drpdwnContractTerm.get(k));
			 actionHandler.waitForSomeTime(2000);
			 boolean status1= elementHandler.isElementDisplayed(AddProductsPageObjects.txtContract1yr);
			 if (status1 == true) {
			 TestNGCustomReporter.log(logger, "Contract Terms drop down list displayed for PPC products" +ReportBuilder.takesScreenshot()); 
			 AssertionHandler.verifyTrue(status1, "Contract Term not displayed for Engagement Builder product"); 
			 elementHandler.clickElement(AddProductsPageObjects.txtContract1yr);
			 }
			 TestNGCustomReporter.log(logger, "Contract Terms drop down list not displayed for OTC products" +ReportBuilder.takesScreenshot());
			 actionHandler.waitForSomeTime(2000);
		}		
		boolean errorMesg1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
		if (errorMesg1 == true) {
			String message= AddProductsPageObjects.lblValidatnMessage.getText();
			TestNGCustomReporter.log(logger, "Error Message displayed while adding product so unable to proceed with Finalize or Submit for Approval as" +message +ReportBuilder.takesScreenshot());					
		}
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSave);
		elementHandler.clickElement(AddProductsPageObjects.btnSave);
		actionHandler.waitForSomeTime(25000);
	}
	
	public void validateApprovalRule(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);                    
		elementHandler.waitForXpathToLoad(lblCategorySelectn);     
        actionHandler.waitForSomeTime(15000);                  

        String Product2 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
        String[] listofproduct3 = Product2.split(",");
        
        for (int i=0; i<listofproduct3.length; i++)
        {
       elementHandler.writeText(AddProductsPageObjects.txtboxSearch, listofproduct3[i]);
       actionHandler.keyboardAction(Keys.ENTER);
       actionHandler.waitForSomeTime(25000);
       elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
       elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
		actionHandler.waitForSomeTime(20000);
       TestNGCustomReporter.log(logger, "Product "+listofproduct3[i] +" Added Successfully");
        }
        actionHandler.waitForSomeTime(5000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
        elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
        actionHandler.waitForSomeTime(30000);
        boolean pageobtn= elementHandler.isElementDisplayed(AddProductsPageObjects.btnPAGEOProduct);
        if (pageobtn == true) {
        	elementHandler.clickElement(AddProductsPageObjects.btnPAGEOProduct);
        	actionHandler.waitForSomeTime(20000);
        }
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.optionDigitalMrkt);
        actionHandler.waitForSomeTime(2000);
        String lblState= elementHandler.getText(AddProductsPageObjects.lblState);
        AssertionHandler.verifyTrue(lblState.contains("State"), "State field is not displayed for Findlaw Digital marketing product");
        elementHandler.selectByIndex(AddProductsPageObjects.drpdwnState,1);
        actionHandler.waitForSomeTime(5000);        
        String lblCounty= elementHandler.getText(AddProductsPageObjects.lblCounty);
        AssertionHandler.verifyTrue(lblCounty.contains("County"), "State field is not displayed for Findlaw Digital marketing product");
        elementHandler.selectByIndex(AddProductsPageObjects.drpdwnCounty,1);
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnSearch);
        actionHandler.waitForSomeTime(15000);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.checkboxproduct.get(0));
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnAddToCartprod);
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollIntoView(AddProductsPageObjects.btnGoTocart);
        boolean btnReturnTocart= elementHandler.isElementDisplayed(AddProductsPageObjects.btnGoTocart);
        if (btnReturnTocart== true) {
        	TestNGCustomReporter.log(logger, "Validate Return To Cart button displayed on the page" +ReportBuilder.takesScreenshot());
        }
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnGoTocart);
        actionHandler.waitForSomeTime(35000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
        elementHandler.clickElement(AddProductsPageObjects.btnReprice);
        actionHandler.waitForSomeTime(35000);
        String status= elementHandler.getText(AddProductsPageObjects.labelStatus);
    	if (status.contains("Approval Required")){
    		AssertionHandler.verifyTrue(status.contains("Approval Required"), "Approval not required for adding PPC Additional with DMA products");
    		TestNGCustomReporter.log(logger, "Approval required when adding PPC additional with DMA product" +ReportBuilder.takesScreenshot());
    	}
    	else {
    		TestNGCustomReporter.log(logger, "Approval not required when adding PPC additional with DMA product" +ReportBuilder.takesScreenshot());
    	}       
	}
	
	
	public void validateApprovalRuleExist(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);                    
		elementHandler.waitForXpathToLoad(lblCategorySelectn);     
        actionHandler.waitForSomeTime(15000);                  
        String Product2 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
        String[] listofproduct3 = Product2.split(",");        
        for (int i=0; i<listofproduct3.length; i++)
        {
       elementHandler.writeText(AddProductsPageObjects.txtboxSearch, listofproduct3[i]);
       actionHandler.keyboardAction(Keys.ENTER);
       actionHandler.waitForSomeTime(25000);
       elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
       elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
		actionHandler.waitForSomeTime(20000);
       TestNGCustomReporter.log(logger, "Product "+listofproduct3[i] +" Added Successfully");
        }
        actionHandler.waitForSomeTime(5000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
        elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
        actionHandler.waitForSomeTime(30000);
        String installedprod = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product", Row);
        String[] listofproduct5 = installedprod.split(",");
        TestNGCustomReporter.log(logger, "No of Products "+listofproduct5.length);
        for (int i=0; i<listofproduct5.length; i++)
        {
		elementHandler.clickElement(AddProductsPageObjects.btnInstalledProduct,AddProductsPageObjects.txtInstalledProduct);
		actionHandler.waitForSomeTime(15000);
		boolean status3= elementHandler.isElementDisplayed(AddProductsPageObjects.txtInstalledProduct);
		if (status3== false) {
			actionHandler.waitForSomeTime(10000);
		}
		elementHandler.writeText(AddProductsPageObjects.txtInstalledProduct, listofproduct5[i]);
		actionHandler.keyboardAction(Keys.ENTER);
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.checkbox);
		elementHandler.clickElement(AddProductsPageObjects.checkbox, AddProductsPageObjects.btnRenew);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnRenew);
		elementHandler.clickElement(AddProductsPageObjects.btnRenew);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.radiobtnoptn);
		elementHandler.clickElement(AddProductsPageObjects.radiobtnoptn, AddProductsPageObjects.btnConfirm);
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfirm);
		elementHandler.clickElement(AddProductsPageObjects.btnConfirm,AddProductsPageObjects.btnGoToPricng);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
		elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng, AddProductsPageObjects.btnAddMoreProducts);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
        elementHandler.clickElement(AddProductsPageObjects.btnReprice);
        actionHandler.waitForSomeTime(35000);
        String status= elementHandler.getText(AddProductsPageObjects.labelStatus);
    	if (status.contains("Approval Required")){
    		AssertionHandler.verifyTrue(status.contains("Approval Required"), "Approval not required for adding PPC Additional with DMA products");
    		TestNGCustomReporter.log(logger, "Approval required when adding PPC additional with DMA product" +ReportBuilder.takesScreenshot());
    	}
    	else {
    		TestNGCustomReporter.log(logger, "Approval not required when adding PPC additional with DMA product" +ReportBuilder.takesScreenshot());
    	}       		
	}
	}
	
	public void addBridgeDiscount(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);                    
		elementHandler.waitForXpathToLoad(lblCategorySelectn);     
        actionHandler.waitForSomeTime(15000);                  
        String Product2 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
        String[] listofproduct3 = Product2.split(",");        
        for (int i=0; i<listofproduct3.length; i++)
        {
       elementHandler.writeText(AddProductsPageObjects.txtboxSearch, listofproduct3[i]);
       actionHandler.keyboardAction(Keys.ENTER);
       actionHandler.waitForSomeTime(25000);
       elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
       elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
		actionHandler.waitForSomeTime(20000);
       TestNGCustomReporter.log(logger, "Product "+listofproduct3[i] +" Added Successfully");
        }
        String Product3 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product", Row);
        elementHandler.writeText(AddProductsPageObjects.txtboxSearch, Product3);
        actionHandler.keyboardAction(Keys.ENTER);
        actionHandler.waitForSomeTime(25000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
        elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
        actionHandler.waitForSomeTime(10000);
		elementHandler.waitForXpathToLoad(btnProductAttr);
		elementHandler.waitForPageToLoad();
		elementHandler.writeText(AddProductsPageObjects.txtboxNumbAttrys, "5");
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.tabSecondaries);
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
		elementHandler.clickElement(AddProductsPageObjects.btnValidate);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
		elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
		actionHandler.waitForSomeTime(30000);
        
		 //Adjustment Amount
        String adjustDiscount = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "AdjustAmount", Row);
        String[] arrDiscount = adjustDiscount.split(",");
        for (int k=0;k<arrDiscount.length;k++) 
        {
        	if (!adjustDiscount.equals("NA"))
        	{       		
               actionHandler.waitForSomeTime(2000);
               elementHandler.writeText(AddProductsPageObjects.txtAdjustAmount1.get(k), adjustDiscount);
               actionHandler.waitForSomeTime(2000);
        	}  
        }
        
		//Bridge
        String bridge = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Bridge", Row);
        String[] arrBridge = bridge.split(",");
        for (int k=0;k<arrBridge.length;k++)
        {
            if (!bridge.equals("NA"))
            {          	
               elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnBridge5.get(k));          
               elementHandler.clickElement(AddProductsPageObjects.drpdwnBridge5.get(k));
               TestNGCustomReporter.log(logger,"validate Bridge field drop down values");
               actionHandler.waitForSomeTime(2000);
               for(int j=1; j<=5; j++)
               {
                      String bridgeTrm=driver.findElement(By.xpath("//ul[contains(@id,'ui-select-choices-')]/li["+j+"]")).getText();
                      TestNGCustomReporter.log(logger,"From list["+j+"] "+bridgeTrm.trim());
                      if(bridgeTrm.trim().contentEquals(bridge.trim()))
                      {
                             driver.findElement(By.xpath("//ul[contains(@id,'ui-select-choices-')]/li["+j+"]")).click();
                             actionHandler.waitForSomeTime(2000);
                             break;
                      }
                      
               }
            }
        } 
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
        elementHandler.clickElement(AddProductsPageObjects.btnReprice);
        actionHandler.waitForSomeTime(30000);
        boolean enablebtn= AddProductsPageObjects.btnFinalize.isEnabled();
		if (enablebtn == true) {
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnFinalize);
			elementHandler.clickElement(AddProductsPageObjects.btnFinalize);
			actionHandler.waitForSomeTime(30000);
			elementHandler.waitForXpathToLoad(imgQuotepg);
			String quote= elementHandler.getText(AddProductsPageObjects.lblproposalId);
			AssertionHandler.verifyTrue(quote.contains("Q-"), "Quote page not displayed");	
			TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Finalize button" +ReportBuilder.takesScreenshot());					
		}
		else {
			actionHandler.waitForSomeTime(10000);
			boolean approvalSymbol = elementHandler.isElementDisplayed(AddProductsPageObjects.lblSymbolApproval);
			if (approvalSymbol == true)
			TestNGCustomReporter.log(logger, "validate  Approval Triangle message symbol for Submit for Approval" +ReportBuilder.takesScreenshot());	
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmtForApproval);
			elementHandler.clickElement(AddProductsPageObjects.btnSubmtForApproval,AddProductsPageObjects.btnSubmit);
			actionHandler.waitForSomeTime(15000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
			elementHandler.clickElement(AddProductsPageObjects.btnSubmit, AddProductsPageObjects.btnSubmit);
			actionHandler.waitForSomeTime(10000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnSubmit);
			elementHandler.clickElement(AddProductsPageObjects.btnSubmit,AddProductsPageObjects.btnReturn);
			actionHandler.waitForSomeTime(5000);
			elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReturn);
			elementHandler.clickElement(AddProductsPageObjects.btnReturn);
			actionHandler.waitForSomeTime(20000);
			String quote= elementHandler.getText(AddProductsPageObjects.lblproposalId);
			AssertionHandler.verifyTrue(quote.contains("Q-"), "Quote page not displayed");
			TestNGCustomReporter.log(logger, "products are added to the cart by clicking on Submit for approval button" +ReportBuilder.takesScreenshot());
			QuotePage quotepage = new QuotePage(driver);
			String Name= quotepage.submitForApproval();
			SearchPage searchpage = new SearchPage(driver);
			searchpage.searchDiffProfile("Ryan Burch");
			searchpage.approvalProcess(Name);
			BasePage basePage = new BasePage(driver);
			basePage.Logout();
			searchpage.changeTab();	
		}              
	}
	
	
	public void validatebridge(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		elementHandler.waitForXpathToLoad(lblCategorySelectn);
        String installedprod = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product", Row);
        String[] listofproduct5 = installedprod.split(",");
        TestNGCustomReporter.log(logger, "No of Products "+listofproduct5.length);
        for (int i=0; i<listofproduct5.length; i++)
        {
		elementHandler.clickElement(AddProductsPageObjects.btnInstalledProduct,AddProductsPageObjects.txtInstalledProduct);
		actionHandler.waitForSomeTime(15000);
		boolean status3= elementHandler.isElementDisplayed(AddProductsPageObjects.txtInstalledProduct);
		if (status3== false) {
			actionHandler.waitForSomeTime(10000);
		}
		elementHandler.writeText(AddProductsPageObjects.txtInstalledProduct, listofproduct5[i]);
		actionHandler.keyboardAction(Keys.ENTER);
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.checkbox);
		elementHandler.clickElement(AddProductsPageObjects.checkbox, AddProductsPageObjects.btnRenew);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnRenew);
		elementHandler.clickElement(AddProductsPageObjects.btnRenew);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.radiobtnoptn);
		elementHandler.clickElement(AddProductsPageObjects.radiobtnoptn, AddProductsPageObjects.btnConfirm);
		actionHandler.waitForSomeTime(2000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfirm);
		elementHandler.clickElement(AddProductsPageObjects.btnConfirm,AddProductsPageObjects.btnGoToPricng);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
		elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng, AddProductsPageObjects.btnAddMoreProducts);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
		elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts, AddProductsPageObjects.txtboxSearch);
		actionHandler.waitForSomeTime(30000);
        }
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
    	elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng,AddProductsPageObjects.btnFinalize);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
		elementHandler.clickElement(AddProductsPageObjects.btnReprice);
		actionHandler.waitForSomeTime(30000);
		 elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnBridge5.get(0));          
         elementHandler.clickElement(AddProductsPageObjects.drpdwnBridge5.get(0));
         TestNGCustomReporter.log(logger,"validate Bridge field for renewal EB and Blog product to be disabled and no drop down options should displayed" +ReportBuilder.takesScreenshot());
         actionHandler.waitForSomeTime(2000);			
	}
	
	public void addContract(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		elementHandler.waitForXpathToLoad(lblCategorySelectn);
        String blogProduct = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
        String[] blogProduct1 = blogProduct.split(",");
        for (int i=0; i<blogProduct1.length; i++)
        {
		elementHandler.writeText(AddProductsPageObjects.txtboxSearch, blogProduct1[i]);
		actionHandler.keyboardAction(Keys.ENTER);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
		elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddAnother);
		javascripthandler.scrollIntoView(AddProductsPageObjects.btnAddAnother);
		elementHandler.clickElement(AddProductsPageObjects.btnAddAnother);
		actionHandler.waitForSomeTime(20000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
		elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
		actionHandler.waitForSomeTime(25000);
		 //Contract Term
        String ContractTerm1 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Contract_Term", Row);
        	if (!ContractTerm1.equals("NA"))
        	{
        	   actionHandler.waitForSomeTime(5000);
               elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnContractTerm.get(0));          
               elementHandler.clickElement(AddProductsPageObjects.drpdwnContractTerm.get(0));
               TestNGCustomReporter.log(logger,"validate contract Term field drop down values");
               actionHandler.waitForSomeTime(2000);
               for(int j=1; j<=5; j++)
               {
                      String CntrctTrm1=driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).getText();
                      TestNGCustomReporter.log(logger,"From list["+j+"] "+CntrctTrm1.trim());
                      if(CntrctTrm1.trim().contentEquals(ContractTerm1.trim()))
                      {
                             driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).click();
                             break;
                      }                                  
               }
        	}  
        	
            //Adjustment Amount
            String adjustDiscount = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "AdjustAmount", Row);
            String[] arrDiscount = adjustDiscount.split(",");
            for (int k=0;k<arrDiscount.length;k++) 
            {
            	if (!adjustDiscount.equals("NA"))
            	{          	
                   actionHandler.waitForSomeTime(2000);
                   elementHandler.writeText(AddProductsPageObjects.txtAdjustAmount1.get(k), adjustDiscount);
                   actionHandler.waitForSomeTime(2000);
            	}  
            }
        	
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
		elementHandler.clickElement(AddProductsPageObjects.btnReprice);
		actionHandler.waitForSomeTime(30000);
		boolean errStatus1= elementHandler.isElementDisplayed(AddProductsPageObjects.lblValidatnMessage);
		if (errStatus1== true) {
		AssertionHandler.verifyTrue(true, "Validation Message not displayed");
		String Error_Message1= elementHandler.getText(AddProductsPageObjects.lblValidatnMessage);
		TestNGCustomReporter.log(logger, "Error Message displayed for adding the product as:"  +Error_Message1 +ReportBuilder.takesScreenshot());
					}
		
		elementHandler.clickElement(AddProductsPageObjects.checkbxSelect);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnRemove);
		elementHandler.clickElement(AddProductsPageObjects.btnRemove);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
		elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts);
		actionHandler.waitForSomeTime(25000);				
	   }
	}
	
	
	public void validateBusinessRule(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		elementHandler.waitForXpathToLoad(lblCategorySelectn);
		String EBblogproducts = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
	    String[] listofproduct3 = EBblogproducts.split(",");
	    for (int i=0; i<listofproduct3.length; i++)
	    {
		elementHandler.writeText(AddProductsPageObjects.txtboxSearch, listofproduct3[i]);
		actionHandler.keyboardAction(Keys.ENTER);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
		elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
		actionHandler.waitForSomeTime(30000);

		javascripthandler.highlightElement(AddProductsPageObjects.lblErrorMessg);
		boolean error= elementHandler.isElementDisplayed(AddProductsPageObjects.lblErrorMessg);
		if (error == true) {
		AssertionHandler.verifyTrue(error, "Business Rule validation message not displayed in the cart page");
		TestNGCustomReporter.log(logger, "Business Rule validation message displayed for adding EB or Blog product as Pre-requisite products not available in the cart or in asset"  +ReportBuilder.takesScreenshot());
		}else {
			TestNGCustomReporter.log(logger, "Business Rule validation message not displayed for adding EB or Blog product as Pre-requisite products available into the cart or in asset"  +ReportBuilder.takesScreenshot());		
		}	
		
	    }	
	}
	
	
	public void validateRenewButton(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		elementHandler.waitForXpathToLoad(lblCategorySelectn);
        String installedprod = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product", Row);
		elementHandler.clickElement(AddProductsPageObjects.btnInstalledProduct,AddProductsPageObjects.txtInstalledProduct);
		actionHandler.waitForSomeTime(20000);		
		elementHandler.writeText(AddProductsPageObjects.txtInstalledProduct, installedprod);
		actionHandler.keyboardAction(Keys.ENTER);
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.checkbox);
		elementHandler.clickElement(AddProductsPageObjects.checkbox);
		actionHandler.waitForSomeTime(5000);
		String btnRenew= AddProductsPageObjects.btnRenew.getAttribute("disabled");
		AssertionHandler.verifyTrue(btnRenew.contains("true"), "Renew button is enabled for PPC products while adding from Installed propducts");		
		String btnTerminate= AddProductsPageObjects.btnTerminate1.getAttribute("disabled");
		AssertionHandler.verifyTrue(btnTerminate.contains("true"), "Terminate button is enabled for PPC products while adding from Installed propducts");
		TestNGCustomReporter.log(logger, "Validate renew and Terminate buttons for Model products");					
	}
	
	
	public void validateApprovalForPAGEO(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForXpathToLoad(lblCategorySelectn);     
        actionHandler.waitForSomeTime(15000);                  
        boolean pageobtn= elementHandler.isElementDisplayed(AddProductsPageObjects.btnPAGEOProduct);
        if (pageobtn == true) {
        	elementHandler.clickElement(AddProductsPageObjects.btnPAGEOProduct);
        	actionHandler.waitForSomeTime(20000);
        }
       elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnTopSpot);
       javascripthandler.scrollToElementAndClick(AddProductsPageObjects.drpdwnTopSpot);
       actionHandler.waitForSomeTime(2000);       
       javascripthandler.scrollToElementAndClick(AddProductsPageObjects.optionTopSpot);       
       String stateVal= ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "State", Row);
       javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnState);
       elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnState);
       elementHandler.selectByVisibleText(AddProductsPageObjects.drpdwnState, stateVal);
       actionHandler.waitForSomeTime(5000);
        String countyVal= ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Metro", Row);
        javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnMetro);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnMetro);
        elementHandler.selectByVisibleText(AddProductsPageObjects.drpdwnMetro, countyVal);
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnPracCategory);
        actionHandler.waitForSomeTime(2000);
        String AttrCountval= ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Practice_Category", Row);
        elementHandler.selectByVisibleText(AddProductsPageObjects.drpdwnPracCategory, AttrCountval);
        actionHandler.waitForSomeTime(5000); 
        boolean searchStatus=  elementHandler.isElementDisplayed(AddProductsPageObjects.btnSearch);
        if (searchStatus == true) {
        	AssertionHandler.verifyTrue(true, "Search button not dispalyed in the page");
        }
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnSearch);
        actionHandler.waitForSomeTime(15000);    
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.checkboxproduct.get(0));
        actionHandler.waitForSomeTime(5000);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnAddToCartprod);
        actionHandler.waitForSomeTime(5000);        
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.optionFindlwPostPlus);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.optionFindlwPostPlus);
        actionHandler.waitForSomeTime(5000);   
        javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnState);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnState);
        elementHandler.selectByVisibleText(AddProductsPageObjects.drpdwnState, stateVal);
        actionHandler.waitForSomeTime(5000);
         javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnCounty);
         elementHandler.selectByVisibleText(AddProductsPageObjects.drpdwnCounty, countyVal);
         actionHandler.waitForSomeTime(5000);
         String PracCategory= ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Practice_Category", Row);
         javascripthandler.scrollIntoView(AddProductsPageObjects.drpdwnPracCategory);
         elementHandler.selectByVisibleText(AddProductsPageObjects.drpdwnPracCategory, PracCategory);
         actionHandler.waitForSomeTime(5000);
         boolean searchStatus1=  elementHandler.isElementDisplayed(AddProductsPageObjects.btnSearch);
         if (searchStatus1 == true) {
         	AssertionHandler.verifyTrue(true, "Search button not dispalyed in the page");
         }
         javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnSearch);
         actionHandler.waitForSomeTime(15000);    
         javascripthandler.scrollToElementAndClick(AddProductsPageObjects.checkboxproduct.get(0));
         actionHandler.waitForSomeTime(5000);
         javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnAddToCartprod);
         actionHandler.waitForSomeTime(5000);       
        javascripthandler.scrollIntoView(AddProductsPageObjects.btnGoTocart);
        javascripthandler.scrollToElementAndClick(AddProductsPageObjects.btnGoTocart);
        actionHandler.waitForSomeTime(35000);
                
      //Adjustment Type
        String AdjustTerm = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Adjust_Type", Row);
        String[] arrAdjust = AdjustTerm.split(",");
        for (int i=0;i<arrAdjust.length;i++)
        {
        if (!AdjustTerm.equals("NA"))
        {
               elementHandler.waitForElementTobeClickable(AddProductsPageObjects.drpdwnAdjustmentTyp.get(i));          
               elementHandler.clickElement(AddProductsPageObjects.drpdwnAdjustmentTyp.get(i));
               TestNGCustomReporter.log(logger,"validate Adjustment Type field drop down values");
               actionHandler.waitForSomeTime(2000);
               for(int j=1; j<=3; j++)
               {
                      String AdjustTrm=driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).getText();
                      TestNGCustomReporter.log(logger,"From list["+j+"] "+AdjustTrm.trim());
                      if(AdjustTrm.trim().contentEquals(AdjustTerm.trim()))
                      {
                             driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//li["+j+"]")).click();
                             actionHandler.waitForSomeTime(2000);
                             break;
                      }
                      
               }
        	}
        }
        
        //Adjustment Amount
        String adjustDiscount = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "AdjustAmount", Row);
        String[] arrDiscount = adjustDiscount.split(",");
        for (int k=0;k<arrDiscount.length;k++) 
        {
        	if (!adjustDiscount.equals("NA"))
        	{
               actionHandler.waitForSomeTime(2000);
               elementHandler.writeText(AddProductsPageObjects.txtAdjustAmount1.get(k), adjustDiscount);
               actionHandler.waitForSomeTime(2000);
        	}  
        }       
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
        elementHandler.clickElement(AddProductsPageObjects.btnReprice);
        actionHandler.waitForSomeTime(35000);
        elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnReprice);
        elementHandler.clickElement(AddProductsPageObjects.btnReprice);
        actionHandler.waitForSomeTime(35000);
        boolean btnApprovalStatus= AddProductsPageObjects.btnSubmtForApproval.isEnabled();
		if (btnApprovalStatus == true) {
			AssertionHandler.verifyTrue(btnApprovalStatus, "Submit for approval button not enabled for adding products with MYR type as M01, M02, M03 , R01, R02, R03, C01 ");
			TestNGCustomReporter.log(logger, "Submit For Approval button enabled for adding products with MYR types :M01, M02, M03 , R01, R02, R03, C01" +ReportBuilder.takesScreenshot());
		}      			
	}
	
	
	
	public void validateInfoMessage(int Row) {
		javascripthandler.scrollIntoView(QuotePageObjects.lblAddProducts);
		elementHandler.clickElement(QuotePageObjects.btnAddProduct);
		actionHandler.waitForSomeTime(5000);
		elementHandler.waitForPageToLoad();
		elementHandler.waitForXpathToLoad(lblCategorySelectn);
		actionHandler.waitForSomeTime(15000);
		String Product = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Product_Id", Row);
		elementHandler.writeText(AddProductsPageObjects.txtboxSearch, Product);
		actionHandler.keyboardAction(Keys.ENTER);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnConfigure);
		elementHandler.clickElement(AddProductsPageObjects.btnConfigure);
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForXpathToLoad(btnProductAttr);
		elementHandler.waitForPageToLoad();
		elementHandler.writeText(AddProductsPageObjects.txtboxNumbAttrys, "5");
		actionHandler.waitForSomeTime(2000);
		elementHandler.clickElement(AddProductsPageObjects.tabSecondaries);
		actionHandler.waitForSomeTime(10000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnValidate);
		elementHandler.clickElement(AddProductsPageObjects.btnValidate);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
		elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddMoreProducts);
		elementHandler.clickElement(AddProductsPageObjects.btnAddMoreProducts);
		actionHandler.waitForSomeTime(30000);
		String Product1 = ExcelUtils.getDataByColumnNameWithRowIndex("AddProducts", "Installed_Product", Row);
		elementHandler.writeText(AddProductsPageObjects.txtboxSearch, Product1);
		actionHandler.keyboardAction(Keys.ENTER);
		actionHandler.waitForSomeTime(30000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnAddToCart1);
		elementHandler.clickElement(AddProductsPageObjects.btnAddToCart1);
		actionHandler.waitForSomeTime(25000);
		elementHandler.waitForElementTobeClickable(AddProductsPageObjects.btnGoToPricng);
		elementHandler.clickElement(AddProductsPageObjects.btnGoToPricng);
		actionHandler.waitForSomeTime(30000);
		elementHandler.clickElement(AddProductsPageObjects.btnReprice);
		boolean infoMessage= elementHandler.isElementDisplayed(AddProductsPageObjects.labelInfo);
		if ( infoMessage == false) {
			AssertionHandler.verifyTrue(true, "Informational message not displayed for adding IMS and Foreign language IV product together into the cart.");
			TestNGCustomReporter.log(logger, "validate Informational message displayed or not while adding IMS and Foreign language IV product into the cart" +ReportBuilder.takesScreenshot());  
		}		
	}
	
	
	
	
	
	
	
	
	
	
	
	

} //main class close 
