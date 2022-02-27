package agent;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class PaymentTest extends base {
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
	public void ValidatePaymentPage(HashMap <String, String> data) {
		//Verify user is taken to the payment page
		 rt =new RegisterAsAnAgentTest();
	
		rt.ValidateAcceptConsentForm1(driver, data);
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getpayment());
		pa = new Payment(driver);
		Assert.assertTrue(pa.getcouponCheckbox().isDisplayed());
		
}
	@Test(priority=2, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class)
	public void InvalidCouponCode(HashMap <String, String> data) {
		//Check system response when user input an invalid coupon code
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getpayment());
		pa = new Payment(driver);
		pa.getcouponCheckbox().click();
		pa.getcouponField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("invalidCoupon"));
		pa.getcouponVerifyButton().click();
		Assert.assertEquals(pa.getInvalidcouponMessage().getText(), "Invalid Coupon Code");
	}
	
	@Test(priority=3, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class)
	public void validCouponCode(HashMap <String, String> data) throws InterruptedException {
		//Check system response when user input a valid coupon code
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getpayment());
		pa = new Payment(driver);
		pa.getcouponField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("validCoupon"));
		pa.getcouponVerifyButton().click();
		Thread.sleep(3000);
		Assert.assertEquals(pa.getPromptMessage().getText(), "Success\n"
				+ "Coupon applied successfully"); 
		
	}
	
	@Test(priority=4)
	public void ValidateCouponCodeRemoval() {
		//Verify user can remove coupon 
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getpayment());
		pa = new Payment(driver);
		pa.getCouponRemoveButton().click();
		pa.getCouponConfirmationRemoveButton().click();
		Assert.assertEquals(pa.getPrice().getText(), "₦12,500");
		
	}
	

	@Test(priority=5)
	public void ValidateBackButton() {
		//Check system response when user click on the Back button
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getpayment());
		pa = new Payment(driver);
		pa.getbackButton().click();
		fp = new FormPreview(driver);	
		Assert.assertTrue(fp.getproceedButton().isDisplayed());
	}
	
	@Test(priority=6, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class, dependsOnMethods="ValidatePaymentPage")
	public void SuccessfulPayment(HashMap <String, String> data) {
		//Verify user can complete application after payment
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getpayment());
		pa = new Payment(driver);
		pa.getsubmitButton().click();
		driver.switchTo().frame(pa.getPaystackPopUpFrame());
		pa.getsuccessTestCard().click();
		pa.getpaystackSubmitButton().click();
		driver.switchTo().defaultContent();
		soft = new SoftAssert();
		soft.assertEquals(pa.getsuccessfulSubmission().getText(),"Form Submission Successful");
		soft.assertEquals(pa.getsuccessfulSubmissionMessage().getText(), "A report will be sent to your email address (" +data.get("emailaddress")+ ") as soon as it is ready.");
		
		soft.assertAll();
	}
	
	@Test(priority=7, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class, dependsOnMethods="ValidatePaymentPage")
	public void SuccessfulCouponPayment(HashMap <String, String> data) {
		//Verify user can complete application after payment using coupon code
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getpayment());
		pa = new Payment(driver);
		pa.getcouponCheckbox().click();
		pa.getcouponField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("validCoupon"));
		pa.getcouponVerifyButton().click();
		pa.getsubmitButton().click();          
		soft = new SoftAssert();
		soft.assertEquals(pa.getsuccessfulSubmission().getText(),"Form Submission Successful");
		soft.assertEquals(pa.getsuccessfulSubmissionMessage().getText(), "A report will be sent to your email address (" +data.get("emailaddress")+ ") as soon as it is ready.");
		
		soft.assertAll();
	}
	
	@AfterClass
	public void terminate() {
		driver.close();
	}
	
}
