package agent;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.agent.BusinessAddress;
import pageObject.agent.FormPreview;
import pageObject.agent.GuarantorInformation;
import pageObject.agent.Homepage;
import pageObject.agent.IdentityInformation;
import pageObject.agent.Payment;
import pageObject.agent.PersonalInformation;
import pageObject.agent.ResidentialAddress;
import resources.base;

public class FormPreviewTest extends base {

	RegisterAsAnAgentTest rt;
	PersonalInformation pi;
	Homepage hp;
	BusinessAddress ba;
	IdentityInformation id;
	ResidentialAddress ra;
	SoftAssert soft;
	GuarantorInformation gi;
	FormPreview fp;
	Payment pa;
	
	@BeforeClass
	public void initialize() throws IOException {
		driver= InitializeBrowser();
		driver.get(prop.getProperty("AgentUrl"));
		driver.manage().window().maximize();
		hp = new Homepage(driver);
		hp.getRegistrationButton().click();
		 
	}	
	
	@Test(priority=1, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class)
	public void ValidateFormPreviewPage(HashMap <String, String> data) {
		//Verify user is taken to the Business Address page
		 rt =new RegisterAsAnAgentTest();
	
		rt.ValidateAcceptConsentForm1(driver, data);
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getFormPreview());
		fp = new FormPreview(driver); 
		Assert.assertTrue(fp.getproceedButton().isDisplayed());
		
}
	
	@Test(priority=2)
	public void ValidatePersonalInformationEditButton() {
		//Check system response when user click on Edit beside Personal information
		fp = new FormPreview(driver);
		for(int e=0; e<fp.getEditHeader().size(); e++) {
			if(fp.getEditHeader().get(e).getText().contains("Personal Information")) {
				for(int f=0; f<fp.getEditButton().size();f++) {
					fp.getEditButton().get(e).click();
				}
				break;
			}
		}
		soft = new SoftAssert();
		pi = new PersonalInformation(driver);
		soft.assertTrue(pi.getemail().isDisplayed());
		pi.getpreviewButton().click();
		soft.assertTrue(fp.getproceedButton().isDisplayed());
		
		soft.assertAll();
		
	}
	
	@Test(priority=3)
	public void ValidateIdentityInformationEditButton() {
		//Check system response when user click on Edit beside Identity information
		fp = new FormPreview(driver);
		for(int e=0; e<fp.getEditHeader().size(); e++) {
			if(fp.getEditHeader().get(e).getText().contains("Identity Information")) {
				for(int f=0; f<fp.getEditButton().size();f++) {
					fp.getEditButton().get(e).click();
				}
				break;
			}
		}
		soft = new SoftAssert();
		id = new IdentityInformation(driver);
		soft.assertTrue(id.getidType().isDisplayed());
		id.getpreviewButton().click();
		soft.assertTrue(fp.getproceedButton().isDisplayed());
		
		soft.assertAll();
		
	}
	
	@Test(priority=4)
	public void ValidateBusinessAddressEditButton() {
		//Check system response when user click on Edit beside Business Address
		fp = new FormPreview(driver);
		soft = new SoftAssert();
		Boolean staleElement = true;
		while(staleElement){
			  try{
		for(int e=0; e<fp.getEditHeader().size(); e++) {
			if(fp.getEditHeader().get(e).getText().contains("Business Address")) {
				for(int f=0; f<fp.getEditButton().size();f++) {
					fp.getEditButton().get(e).click();
				}
				break;
			}
		}
		ba = new BusinessAddress(driver);
		soft.assertTrue(ba.getlandmark().isDisplayed());
		
				  ba = new BusinessAddress(driver);
			 soft.assertTrue(ba.getlandmark().isDisplayed());
			    ba.getpreviewButton().click(); 
			     staleElement = false;
			  } catch(StaleElementReferenceException e){
			    staleElement = true;
			  }
			}	
		soft.assertTrue(fp.getproceedButton().isDisplayed());
		soft.assertAll();
		
	}
	
	@Test(priority=5)
	public void ValidateResidentialAddressEditButton() {
		//Check system response when user click on Edit beside Residential Address
		fp = new FormPreview(driver);
		for(int e=0; e<fp.getEditHeader().size(); e++) {
			if(fp.getEditHeader().get(e).getText().contains("Residential Address")) {
				for(int f=0; f<fp.getEditButton().size();f++) {
					fp.getEditButton().get(e).click();
				}
				break;
			}
		}
		soft = new SoftAssert();
		ra = new ResidentialAddress(driver);
		soft.assertTrue(ra.getlandmark().isDisplayed());
		ra.getpreviewButton().click();
		soft.assertTrue(fp.getproceedButton().isDisplayed());
		
		soft.assertAll();
		
	}
	
	@Test(priority=6)
	public void ValidateGuarantorInformationEditButton() {
		//Check system response when user click on Edit beside Guarantor Personal information
		fp = new FormPreview(driver);
		for(int e=0; e<fp.getEditHeader().size(); e++) {
			if(fp.getEditHeader().get(e).getText().contains("Guarantor Personal Information")) {
				for(int f=0; f<fp.getEditButton().size();f++) {
					fp.getEditButton().get(e).click();
				}
				break;
			}
		}
		soft = new SoftAssert();
		gi = new GuarantorInformation(driver);
		soft.assertTrue(gi.getemail().isDisplayed());
		gi.getpreviewButton().click();
		soft.assertTrue(fp.getproceedButton().isDisplayed());
		
		soft.assertAll();
		
	}
	
	@Test(priority=7)
	public void ValidateBackButton() {
	//Check system response when user click on the Back button
		fp = new FormPreview(driver);
		fp.getbackButton().click();
		gi = new GuarantorInformation(driver);
		Assert.assertTrue(gi.getemail().isDisplayed());
		
	}
	

	@Test(priority=8)
	public void ValidateProceedButton() throws InterruptedException {
		//Check system response when user click on the "Proceed to payment" button
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getFormPreview());
		fp = new FormPreview(driver); 
		fp.getproceedButton().click();
		soft = new SoftAssert();
		soft.assertEquals(fp.getconfirmationMessage().getText(), "You won't be able to change your information after submission!");
		executor.executeScript("arguments[0].click()", fp.getconfirmationCancelButton());
		soft.assertTrue(fp.getproceedButton().isDisplayed());
		fp.getproceedButton().click();
		executor.executeScript("arguments[0].click()", fp.getconfirmationProceedButton());
		pa = new Payment(driver);
		soft.assertTrue(pa.getcouponCheckbox().isDisplayed());
		soft.assertAll();
	}

	@AfterClass
	public void terminate() {
		driver.close();
	}
}

