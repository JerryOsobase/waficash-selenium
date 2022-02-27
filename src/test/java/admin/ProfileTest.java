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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import agent.RegisterAsAnAgentTest;
import pageObject.admin.Companies;
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
	
	/*@Test(priority=2)
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
	}*/
	
	@Test(priority=7, dataProvider="invalidGetData", dataProviderClass=RegisterAsAnAgentTest.class)
	public void InvalidPhoneNumberFormat(HashMap<String, String> data) {
		//Check system response when user input a wrong phone number format (i.e less or more than 11 numbers, alphabet or special characters)
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pr = new Profile(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getProfile());
		 pr.getPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("Invalidphonenumber"));
		 Assert.assertFalse(pr.getSaveChanges().isEnabled());
	}
}
