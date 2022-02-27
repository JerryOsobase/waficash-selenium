package agent;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.agent.BusinessAddress;
import pageObject.agent.FormPreview;
import pageObject.agent.GuarantorInformation;
import pageObject.agent.Homepage;
import pageObject.agent.IdentityInformation;
import pageObject.agent.PersonalInformation;
import pageObject.agent.ResidentialAddress;
import resources.base;

public class GuarantorInformationTest extends base{

		RegisterAsAnAgentTest rt;
		PersonalInformation pi;
		Homepage hp;
		BusinessAddress ba;
		IdentityInformation id;
		ResidentialAddress ra;
		SoftAssert soft;
		GuarantorInformation gi;
		FormPreview fp;
		
		@BeforeClass
		public void initialize() throws IOException {
			driver= InitializeBrowser();
			driver.get(prop.getProperty("AgentUrl"));
			driver.manage().window().maximize();
			hp = new Homepage(driver);
			hp.getRegistrationButton().click();
			 
		}	
		@AfterMethod()
		public void refreshPage() throws InterruptedException {
			driver.navigate().refresh();
			
		}
		
		@Test(priority=1, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class)
		public void ValidateGuarantorInformationPage(HashMap <String, String> data) {
			//Verify user is taken to the Guarantor information page
			 rt =new RegisterAsAnAgentTest();
		
			rt.ValidateAcceptConsentForm1(driver, data);
			 pi = new PersonalInformation(driver);  
			 JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", pi.getguarantorInformation());
			 gi = new GuarantorInformation(driver);
		     Assert.assertTrue(gi.getfirstName().isDisplayed());
			
		}
		
		@Test(priority=2, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class)
		public void EmptyFirstNameField(HashMap <String, String> data) {
			//Verify user is unable to continue application if the First name field is empty
			 pi = new PersonalInformation(driver);  
			 JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", pi.getguarantorInformation());
			gi = new GuarantorInformation(driver);
			gi.getlastName().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("lastname"));
			gi.getemail().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("guarantorEmail"));
			gi.getphoneNumber().sendKeys( Keys.chord(Keys.COMMAND,"a"), data.get("guarantorPhone"));
			Assert.assertFalse(gi.getcontinueButton().isEnabled());
			
		}
		
		@Test(priority=3, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class)
		public void EmptylastNameField(HashMap <String, String> data) {
			//Verify user is unable to continue application if the Last name field is empty
			 pi = new PersonalInformation(driver);  
			 JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", pi.getguarantorInformation());
			gi = new GuarantorInformation(driver);
			gi.getfirstName().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("firstname"));
			gi.getemail().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("guarantorEmail"));
			gi.getphoneNumber().sendKeys(Keys.chord(Keys.COMMAND,"a"),data.get("guarantorPhone"));
			Assert.assertFalse(gi.getcontinueButton().isEnabled());
			
		}
		
		@Test(priority=4, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class)
		public void EmptyEmailField(HashMap <String, String> data) {
			//Verify user is unable to continue application if the email address field is empty
			 pi = new PersonalInformation(driver);  
			 JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", pi.getguarantorInformation());
			gi = new GuarantorInformation(driver);
			gi.getfirstName().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("firstname"));
			gi.getlastName().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("lastname"));
			gi.getphoneNumber().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("guarantorPhone"));
			Assert.assertFalse(gi.getcontinueButton().isEnabled());
			
		}
		
		@Test(priority=5, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class)
		public void EmptyPhoneNumberField(HashMap <String, String> data) {
			//Verify user is unable to continue application if the phone number field is empty
			 pi = new PersonalInformation(driver);  
			 JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", pi.getguarantorInformation());
			gi = new GuarantorInformation(driver);
			gi.getfirstName().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("firstname"));
			gi.getlastName().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("lastname"));
			gi.getemail().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("guarantorEmail"));
			Assert.assertFalse(gi.getcontinueButton().isEnabled());
			
		}
		
		@Test(priority=6, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class)
		public void InvalidEmailField(HashMap <String, String> data) {
			//Check system response when user input an email with the wrong format
			 pi = new PersonalInformation(driver);  
			 JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", pi.getguarantorInformation());
			gi = new GuarantorInformation(driver);
			gi.getfirstName().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("firstname"));
			gi.getlastName().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("lastname"));
			gi.getphoneNumber().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("guarantorPhone"));
			gi.getemail().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("Invalidemailaddress"));
			Assert.assertFalse(gi.getcontinueButton().isEnabled());
			
		}
		
		@Test(priority=7, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class)
		public void InvalidPhoneNumberField(HashMap <String, String> data) {
			/*Check system response when user input phone number with the wrong format (i.e contains alphabets or 
			special characters, or it less than or more than 11 numbers*/
			 pi = new PersonalInformation(driver);  
			 JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", pi.getguarantorInformation());
			gi = new GuarantorInformation(driver);
			gi.getfirstName().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("firstname"));
			gi.getlastName().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("lastname"));
			gi.getphoneNumber().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("Invalidphonenumber"));
			gi.getemail().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("guarantorEmail"));
			Assert.assertFalse(gi.getcontinueButton().isEnabled());
			
		}
		
		@Test(priority=8)
		public void ValidatePreviewButton() {
			//Verify user can view the Preview and Submit button and it is disabled if user has not filled all required forms
			 pi = new PersonalInformation(driver);  
			 JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", pi.getguarantorInformation());
			gi = new GuarantorInformation(driver);
			soft=new SoftAssert();
			soft.assertTrue(gi.getpreviewButton().isDisplayed());
			soft.assertFalse(gi.getpreviewButton().isEnabled());
			
			soft.assertAll();
		}
		
		@Test(priority=9)
		public void ValidateBackButton() {
		//Check system response when user click on the Back button
			 pi = new PersonalInformation(driver);  
			 JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", pi.getguarantorInformation());
			gi = new GuarantorInformation(driver);
			gi.getbackButton().click();
			ra = new ResidentialAddress(driver);
			Assert.assertTrue(ra.getaddress().isDisplayed());
			
		}
		
		@Test(priority=10, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class)
		public void SuccessfulGuarantorForm(HashMap <String, String> data) throws InterruptedException {
			//Verify user is able to contiue application
			 pi = new PersonalInformation(driver);  
			 JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", pi.getguarantorInformation());
			gi = new GuarantorInformation(driver);
			gi.getfirstName().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("guarantorname"));
			gi.getlastName().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("guarantorname"));
			gi.getphoneNumber().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("guarantorPhone"));
			gi.getemail().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("guarantorEmail"));
			gi.getcontinueButton().click();
			fp = new FormPreview(driver);	
			Assert.assertTrue(fp.getproceedButton().isDisplayed());
			
		}
		
		@AfterClass
		public void terminate() {
			driver.close();
		}
	}

