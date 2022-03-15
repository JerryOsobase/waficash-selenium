package admin;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import agent.RegisterAsAnAgentTest;
import pageObject.admin.Settings;
import pageObject.admin.SideMenu;
import resources.base;

public class SettingsTest extends base{
	SideMenu sm;
	SoftAssert soft;
	AdminLoginTest alt;
	Settings s;
	CouponTest ct;
	
	@BeforeClass
	public void initialize() throws IOException {
		driver= InitializeBrowser();
		driver.get(prop.getProperty("AdminOpUrl"));
		driver.manage().window().maximize();
	}
	
	@Test(priority=1, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void ValidateReportPage(HashMap<String, String> data) {
	//Verify user is taken to the Report page
		alt = new AdminLoginTest();
		alt.ValidateSuccessfulAdminLogin1(driver, data);
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 s = new Settings(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getSettings());
		 soft.assertTrue(s.getSaveChangesButton().isDisplayed());
		 soft.assertTrue(s.getSettingsHeader().getText().contains("Settings"));
		 soft.assertAll();
	}
	
	@Test(priority=2)
	public void ValidateField() {
		//Verify user can view the following on the Business profile page; Business Name, Business Address, Support Phone Number, Support Email Address, Primary Contact Name, Primary Contact Phone Number, Business Website, Primary Contact Email Address
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 s = new Settings(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getSettings());
		List<String> originalFields= s.getFieldLabel().stream().map(a->a.getText()).collect(Collectors.toList());
		String[] expectedFields= {"Business Name", "Business Address", "Support Phone Number *", "Support Email Address *", "Business Website *", "Primary Contact Name *", 
				"Primary Contact Phone Number *",  "Primary Contact Email Address *"};
			List<String> fieldsArray= Arrays.asList(expectedFields);
			Assert.assertEquals(originalFields, fieldsArray);
	
	}
	
	@Test(priority=3)
	public void ValidateBusinessNameEdit() {
		//Verify user is unable to edit the Business name
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 s = new Settings(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getSettings());
		 Assert.assertTrue(s.getBusinessNameField().getAttribute("readOnly").equals("true"));
	}
	
	@Test(priority=4)
	public void ValidateBusinessAddressEdit() throws InterruptedException {
		//Verify user can edit the Business address 
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 s = new Settings(driver);
		 ct = new CouponTest();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getSettings());
			 String businessAddress = "business"+ct.setCouponCode();
			s.getBusinessAddressField().sendKeys(Keys.chord(Keys.COMMAND,"a"), businessAddress);
			 s.getSaveChangesButton().click();
			 Thread.sleep(3000);
			 soft.assertEquals(s.getPromptMessage().getText(), "Success\n"
			 		+ "Business profile updated successfully");
			soft.assertEquals(s.getBusinessAddressField().getAttribute("value"), businessAddress);
			s.getBusinessAddressField().sendKeys(Keys.chord(Keys.COMMAND,"a"), "1A, Hughes Avenue, Yaba, Lagos");
			s.getSaveChangesButton().click();
			soft.assertAll();
	}
	
	@Test(priority=5, dataProvider="invalidGetData", dataProviderClass=RegisterAsAnAgentTest.class)
	public void InvalidSupportPhoneNumberFormat(HashMap<String, String> data) {
		//Check system response when user input a wrong phone number format in the support phoneNumber field(i.e less or more than 11 numbers, alphabet or special characters)
		  sm = new SideMenu(driver);
		  soft = new  SoftAssert();
			 s = new Settings(driver);
			 JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getSettings());
		 s.getSupportPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("Invalidphonenumber"));
		 Assert.assertFalse(s.getSaveChangesButton().isEnabled());
	}
	
	@Test(priority=6)
	public void ValidateSupportPhoneNumberEdit() throws InterruptedException {
		//Verify user can edit the Support Phone number 
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 s = new Settings(driver);
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getSettings());
		 String phoneNumber = "28383930042";
		 String phoneNumber1 = "08093772785";
		s.getSupportPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), phoneNumber);
		 s.getSaveChangesButton().click();
		 Thread.sleep(3000);
		 soft.assertEquals(s.getPromptMessage().getText(), "Success\n"
			 		+ "Business profile updated successfully");
		soft.assertEquals(s.getSupportPhoneNumberField().getAttribute("value"), phoneNumber); 
		s.getSupportPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), phoneNumber1);
		 s.getSaveChangesButton().click();
		 Thread.sleep(3000);
		 soft.assertEquals(s.getPromptMessage().getText(), "Success\n"
			 		+ "Business profile updated successfully");
		soft.assertEquals(s.getSupportPhoneNumberField().getAttribute("value"), phoneNumber1); 
		soft.assertAll();
	}
	
	@Test(priority=7, dataProvider="invalidGetData", dataProviderClass=RegisterAsAnAgentTest.class)
	public void InvalidSupportEmailFormat(HashMap<String, String> data) {
		//Check system response when user input a wrong email format in the Support Email address
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		s = new Settings(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getSettings());
		s.getSupportEmailField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("Invalidemailaddress"));
		Assert.assertFalse(s.getSaveChangesButton().isEnabled());
	}
	
	@Test(priority=8)
	public void ValidateSupportEmailEdit() throws InterruptedException {
		//Verify user can edit the Support Email address 
				sm = new SideMenu(driver);
				soft = new  SoftAssert();
				 s = new Settings(driver);
				 JavascriptExecutor executor = (JavascriptExecutor) driver;
				 executor.executeScript("arguments[0].click();", sm.getSettings());
				 String email = "test@gmail.com";
				 String email1 = "freebrowsingadmin+lslb@gmail.com";
				s.getSupportEmailField().sendKeys(Keys.chord(Keys.COMMAND,"a"), email);
				 s.getSaveChangesButton().click();
				 Thread.sleep(3000);
				 soft.assertEquals(s.getPromptMessage().getText(), "Success\n"
					 		+ "Business profile updated successfully");
				soft.assertEquals(s.getSupportEmailField().getAttribute("value"), email); 
				s.getSupportEmailField().sendKeys(Keys.chord(Keys.COMMAND,"a"), email1);
				 s.getSaveChangesButton().click();
				 Thread.sleep(3000);
				 soft.assertEquals(s.getPromptMessage().getText(), "Success\n"
					 		+ "Business profile updated successfully");
				soft.assertEquals(s.getSupportEmailField().getAttribute("value"), email1); 
				soft.assertAll();
	}
	
	@Test(priority=9)
	public void ValidatePrimaryContactNameEdit() throws InterruptedException {
		//Verify user can edit the Primary Contact name 
				sm = new SideMenu(driver);
				soft = new  SoftAssert();
				 s = new Settings(driver);
				 JavascriptExecutor executor = (JavascriptExecutor) driver;
				 executor.executeScript("arguments[0].click();", sm.getSettings());
				 String name = "Jerry Test";
				 String name1 = "Onyeka Ijeh";
				s.getPrimaryContactNameField().sendKeys(Keys.chord(Keys.COMMAND,"a"), name);
				 s.getSaveChangesButton().click();
				 Thread.sleep(3000);
				 soft.assertEquals(s.getPromptMessage().getText(), "Success\n"
					 		+ "Business profile updated successfully");
				soft.assertEquals(s.getPrimaryContactNameField().getAttribute("value"), name); 
				s.getPrimaryContactNameField().sendKeys(Keys.chord(Keys.COMMAND,"a"), name1);
				 s.getSaveChangesButton().click();
				 Thread.sleep(3000);
				 soft.assertEquals(s.getPromptMessage().getText(), "Success\n"
					 		+ "Business profile updated successfully");
				soft.assertEquals(s.getPrimaryContactNameField().getAttribute("value"), name1); 
				soft.assertAll();
	}
	
	@Test(priority=10, dataProvider="invalidGetData", dataProviderClass=RegisterAsAnAgentTest.class)
	public void InvalidPrimaryContactPhoneNumberFormat(HashMap<String, String> data) {
		//Check system response when user input a wrong phone number format in the primary contact phoneNumber field(i.e less or more than 11 numbers, alphabet or special characters)
		sm = new SideMenu(driver);
		  soft = new  SoftAssert();
			 s = new Settings(driver);
			 JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getSettings());
		 s.getPrimaryContactNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("Invalidphonenumber"));
		 Assert.assertFalse(s.getSaveChangesButton().isEnabled());
	}
	
	@Test(priority=11)
	public void ValidatePrimaryContactPhoneNumberEdit() throws InterruptedException {
		//Verify user can edit the Primary Contact phone number
				sm = new SideMenu(driver);
				soft = new  SoftAssert();
				 s = new Settings(driver);
				 JavascriptExecutor executor = (JavascriptExecutor) driver;
				 executor.executeScript("arguments[0].click();", sm.getSettings());
				 String number = "09823749021";
				 String number1 = "08093772785";
				s.getPrimaryContactNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), number);
				 s.getSaveChangesButton().click();
				 Thread.sleep(3000);
				 soft.assertEquals(s.getPromptMessage().getText(), "Success\n"
					 		+ "Business profile updated successfully");
				soft.assertEquals(s.getPrimaryContactNumberField().getAttribute("value"), number); 
				s.getPrimaryContactNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), number1);
				 s.getSaveChangesButton().click();
				 Thread.sleep(3000);
				 soft.assertEquals(s.getPromptMessage().getText(), "Success\n"
					 		+ "Business profile updated successfully");
				soft.assertEquals(s.getPrimaryContactNumberField().getAttribute("value"), number1); 
				soft.assertAll();
	}
	
	@Test(priority=12)
	public void ValidateBusinessWebsiteEdit() throws InterruptedException {
		//Verify user can edit the Business website
				sm = new SideMenu(driver);
				soft = new  SoftAssert();
				 s = new Settings(driver);
				 JavascriptExecutor executor = (JavascriptExecutor) driver;
				 executor.executeScript("arguments[0].click();", sm.getSettings());
				 String site = "https://test.com";
				 String site1 = "https://lslb.com";
				s.getBusinessWebsiteField().sendKeys(Keys.chord(Keys.COMMAND,"a"), site);
				 s.getSaveChangesButton().click();
				 Thread.sleep(3000);
				 soft.assertEquals(s.getPromptMessage().getText(), "Success\n"
					 		+ "Business profile updated successfully");
				soft.assertEquals(s.getBusinessWebsiteField().getAttribute("value"), site); 
				s.getBusinessWebsiteField().sendKeys(Keys.chord(Keys.COMMAND,"a"), site1);
				 s.getSaveChangesButton().click();
				 Thread.sleep(3000);
				 soft.assertEquals(s.getPromptMessage().getText(), "Success\n"
					 		+ "Business profile updated successfully");
				soft.assertEquals(s.getBusinessWebsiteField().getAttribute("value"), site1); 
				soft.assertAll();
	}
	
	@Test(priority=13, dataProvider="invalidGetData", dataProviderClass=RegisterAsAnAgentTest.class)
	public void InvalidPrimaryContactEmailFormat(HashMap<String, String> data) {
		//Check system response when user input a wrong email format in the primary contact field
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		s = new Settings(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getSettings());
		s.getPrimaryContactEmailField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("Invalidemailaddress"));
		Assert.assertFalse(s.getSaveChangesButton().isEnabled());
	}
	
	@Test(priority=14)
	public void ValidatePrimaryContactEmailEdit() throws InterruptedException {
		//Verify user can edit the Primary Contact Email Address 
				sm = new SideMenu(driver);
				soft = new  SoftAssert();
				 s = new Settings(driver);
				 JavascriptExecutor executor = (JavascriptExecutor) driver;
				 executor.executeScript("arguments[0].click();", sm.getSettings());
				 String email = "test@gmail.com";
				 String email1 = "freebrowsingadmin+lslb@gmail.com";
				s.getPrimaryContactEmailField().sendKeys(Keys.chord(Keys.COMMAND,"a"), email);
				 s.getSaveChangesButton().click();
				 Thread.sleep(3000);
				 soft.assertEquals(s.getPromptMessage().getText(), "Success\n"
					 		+ "Business profile updated successfully");
				soft.assertEquals(s.getPrimaryContactEmailField().getAttribute("value"), email); 
				s.getPrimaryContactEmailField().sendKeys(Keys.chord(Keys.COMMAND,"a"), email1);
				 s.getSaveChangesButton().click();
				 Thread.sleep(3000);
				 soft.assertEquals(s.getPromptMessage().getText(), "Success\n"
					 		+ "Business profile updated successfully");
				soft.assertEquals(s.getPrimaryContactEmailField().getAttribute("value"), email1); 
				soft.assertAll();
	}
	
	@AfterClass
	public void terminate() {
		driver.close();
	}

}
