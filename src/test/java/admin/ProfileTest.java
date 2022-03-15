package admin;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import agent.RegisterAsAnAgentTest;
import pageObject.admin.Profile;
import pageObject.admin.SideMenu;
import resources.base;

public class ProfileTest extends base {
	SideMenu sm;
	SoftAssert soft;
	AdminLoginTest alt;
    Profile pr;
    CouponTest	ct;
    
	@BeforeClass
	public void initialize() throws IOException {
		driver= InitializeBrowser();
		driver.get(prop.getProperty("AdminOpUrl"));
		driver.manage().window().maximize();
	}
	
	@Test(priority=1, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void ValidateProfilePage(HashMap<String, String> data) {
	//Verify user is taken to the profile page
		alt = new AdminLoginTest();
		alt.ValidateSuccessfulAdminLogin1(driver, data);
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pr = new Profile(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getProfile());
		 soft.assertTrue(pr.getChangePassword().isDisplayed());
		 soft.assertEquals(pr.getProfileHeader().getText(), "Profile Information");
		 soft.assertAll();
		
	}
	
	@Test(priority=2)
	public void ValidateProfileInformation() {
		//Verify user can view the following on the profile page; (User profile picture, First name, Middle name, Last name, Phone number, Gender, Email, Role, Change password button, Save changes button)
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pr = new Profile(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getProfile());
		 List<String> originalFieldLabel= pr.getFieldLabel().stream().map(a->a.getText()).collect(Collectors.toList());
			String[] expectedFieldLabel= {"User's Picture *", "First Name *", "Middle Name", "Last Name *", "Phone Number *", 
					"Gender", "Email *", "Role", "Password"};
				List<String> expectedLabelArray= Arrays.asList(expectedFieldLabel);
				soft.assertEquals(originalFieldLabel, expectedLabelArray);
				soft.assertTrue(pr.getChangePassword().isDisplayed());
				soft.assertTrue(pr.getSaveChanges().isDisplayed());
				soft.assertAll();
				
	}
	
	@Test(priority=3)
	public void ValidateProfileImageUpload() throws InterruptedException {
		//Verify user can upload image successfully
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pr = new Profile(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getProfile());
		 pr.getProfilePicUpload().findElement(By.xpath("following-sibling::button")).click();
		 pr.getFirstNameField().sendKeys(Keys.chord(Keys.COMMAND,"a"), "Test");
		 soft.assertFalse(pr.getSaveChanges().isEnabled());
		 pr.getProfilePicUpload().sendKeys(System.getProperty("user.dir")+"//src//main//java//resources//Image//download (1).jpeg");
		 pr.getSaveChanges().click();
		 Thread.sleep(3000);
		 soft.assertEquals(pr.getPromptMessage().getText(), "Success\n"
			 		+ "User profile updated successfully");
		 soft.assertAll();
	}
	
	@Test(priority=4)
	public void ValidateFirstNameEdit() throws InterruptedException {
		//Verify user is able to edit the first name
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pr = new Profile(driver);
		 ct = new CouponTest();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getProfile());
		 String firstName = "first"+ct.setCouponCode();
		 pr.getFirstNameField().sendKeys(Keys.chord(Keys.COMMAND,"a"), firstName);
		 pr.getSaveChanges().click();
		 Thread.sleep(3000);
		 soft.assertEquals(pr.getPromptMessage().getText(), "Success\n"
			 		+ "User profile updated successfully");
		soft.assertEquals(pr.getFirstNameField().getAttribute("value"), firstName); 
		soft.assertAll();
	}
	
	@Test(priority=5)
	public void ValidateLastNameEdit() throws InterruptedException {
		//Verify user is able to edit the last name
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pr = new Profile(driver);
		 ct = new CouponTest();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getProfile());
		 String lastName = "last"+ct.setCouponCode();
		 pr.getLastNameField().sendKeys(Keys.chord(Keys.COMMAND,"a"), lastName);
		 pr.getSaveChanges().click();
		 Thread.sleep(3000);
		 soft.assertEquals(pr.getPromptMessage().getText(), "Success\n"
			 		+ "User profile updated successfully");
		soft.assertEquals(pr.getLastNameField().getAttribute("value"), lastName); 
		soft.assertAll();
	}
	
	@Test(priority=6)
	public void ValidatePhoneNumberEdit() throws InterruptedException {
		//Verify user is able to edit the phoneNumber
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pr = new Profile(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getProfile());
		 String phoneNumber = "28383930042";
		 String phoneNumber1 = "08138493645";
		 pr.getPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), phoneNumber);
		 pr.getSaveChanges().click();
		 Thread.sleep(3000);
		 soft.assertEquals(pr.getPromptMessage().getText(), "Success\n"
			 		+ "User profile updated successfully");
		soft.assertEquals(pr.getPhoneNumberField().getAttribute("value"), phoneNumber); 
		pr.getPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), phoneNumber1);
		 pr.getSaveChanges().click();
		 Thread.sleep(3000);
		 soft.assertEquals(pr.getPromptMessage().getText(), "Success\n"
			 		+ "User profile updated successfully");
		soft.assertEquals(pr.getPhoneNumberField().getAttribute("value"), phoneNumber1); 
		soft.assertAll();
	}
	
	@Test(priority=7, dataProvider="invalidGetData", dataProviderClass=RegisterAsAnAgentTest.class)
	public void InvalidPhoneNumberFormat(HashMap<String, String> data) {
		//Check system response when user input a wrong phone number format 
		  sm = new SideMenu(driver);
		 pr = new Profile(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getProfile());
		 pr.getPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("Invalidphonenumber"));
		 Assert.assertFalse(pr.getSaveChanges().isEnabled());
	}
	
	@Test(priority=8)
	public void ValidateGenderEdit() {
		//Verify user is unable to edit Gender
		 sm = new SideMenu(driver);
			soft = new  SoftAssert();
			 pr = new Profile(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getProfile());
			Assert.assertFalse(pr.getGenderField().isEnabled());
	}
	
	@Test(priority=9)
	public void ValidateEmailAddressEdit() throws InterruptedException {
		//Verify user is unable to edit email address
		 sm = new SideMenu(driver);
			 pr = new Profile(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getProfile());
			Assert.assertTrue(pr.getEmailAddressField().getAttribute("readOnly").equals("true"));
	}
	
	@Test(priority=10)
	public void ValidateConFirmPasswordField() {
		//Verify user is unable to view the Confirm password Field if user has not inputted a valid new password
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pr = new Profile(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getProfile());
		 pr.getChangePassword().click();
		 Assert.assertFalse(pr.getConfirmPasswordField().isDisplayed());
		 executor.executeScript("arguments[0].click();", pr.getPopUpCloseButton());
	}
	
	@Test(priority=11)
	public void ValidatePasswordTips() {
		//Check system response when user start typing on the New password field
		sm = new SideMenu(driver);
		 pr = new Profile(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getProfile());
		 pr.getChangePassword().click();
		 pr.getNewPasswordField().sendKeys("te");
		 List<String> originalpasswordTips= pr.getPasswordTips().stream().map(a->a.getText()).collect(Collectors.toList());
			String[] expectedpasswordTips= {"One lowercase letter", "One uppercase letter", 
					"One special character (allowed characters ! @ # $ % ^ & _ -)", "One number", "Eight characters"};
				List<String> expectedHeaderArray= Arrays.asList(expectedpasswordTips);
				Assert.assertEquals(originalpasswordTips, expectedHeaderArray);
				executor.executeScript("arguments[0].click();", pr.getPopUpCloseButton());
	}
	
	@Test(priority=12)
	public void EmptyCurrentPasswordField() {
		//Verify user is unable to change password if the current password field is empty
		sm = new SideMenu(driver);
		 pr = new Profile(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getProfile());
		 executor.executeScript("arguments[0].click();", pr.getChangePassword());
		 pr.getNewPasswordField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Testing4@");
		 pr.getConfirmPasswordField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Testing4@");
		 Assert.assertFalse(pr.getPopUpChangePassword().isEnabled());
		 executor.executeScript("arguments[0].click();", pr.getPopUpCloseButton());
	}
	
	@Test(priority=13, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void ValidateNewPasswordMatch(HashMap<String, String> data) {
		//Check system response if the new and confirm password does not match
		sm = new SideMenu(driver);
		 pr = new Profile(driver);
		 soft = new SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getProfile());
		 executor.executeScript("arguments[0].click();", pr.getChangePassword());
		 pr.getCurrentPasswordField().sendKeys(data.get("password"));
		 pr.getNewPasswordField().sendKeys("Testing4@");
		 soft.assertFalse(pr.getPopUpChangePassword().isEnabled());
		 pr.getConfirmPasswordField().sendKeys("Testing4");
		soft.assertEquals(pr.getConfirmPasswordField().findElement(By.xpath
				("following-sibling::p")).getText(), "Must be the same as new password");
		soft.assertFalse(pr.getPopUpChangePassword().isEnabled());
		soft.assertAll();	 
		executor.executeScript("arguments[0].click();", pr.getPopUpCloseButton());
	}
	
	@Test(priority=14, dataProvider="invalidGetData", dataProviderClass=AdminLoginTest.class)
	public void InvalidCurrentPassword(HashMap<String, String> data) throws InterruptedException {
		//Check system response when user input a wrong current password
		sm = new SideMenu(driver);
		 pr = new Profile(driver);
		 soft = new SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getProfile());
		 executor.executeScript("arguments[0].click();", pr.getChangePassword());
		 pr.getNewPasswordField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Testing4@");
		 pr.getConfirmPasswordField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Testing4@");
		 pr.getCurrentPasswordField().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("invalidPassword"));
		 pr.getPopUpChangePassword().click();
		 Thread.sleep(3000);
		soft.assertEquals(pr.getPromptMessage().getText(), "Error\n"
				+ "Current password does not match");
		soft.assertAll();
		 executor.executeScript("arguments[0].click();", pr.getPopUpCloseButton());
	}
	
	@AfterClass
	public void terminate() {
		driver.close();
	}
}
