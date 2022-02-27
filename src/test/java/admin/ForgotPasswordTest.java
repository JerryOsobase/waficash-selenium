package admin;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObject.admin.AdminLogin;
import resources.base;

public class ForgotPasswordTest extends base {
	AdminLogin al;
	@BeforeClass
	public void initialize() throws IOException {
		driver= InitializeBrowser();
		driver.get(prop.getProperty("AdminOpUrl"));
		driver.manage().window().maximize();
	}
	
	@Test(priority=1, dataProvider="getData", dataProviderClass=AdminLoginTest.class)
	public void ValidateForgotPasswordLink(HashMap<String, String> data) {
		//Verify the Forgot password link is clickable
		al = new AdminLogin(driver);
		al.getEmailAddressField().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("emailAddress"));
		al.getContinueButton().click();
		al.getForgotPassword().click();
		Assert.assertEquals(al.getForgotPasswordHeader().getText(), "Recover Password");	
		
	}
	
	@Test(priority=2, dataProvider="invalidGetData", dataProviderClass=AdminLoginTest.class)
	public void InvalidEmailAddressFormat(HashMap<String, String> data) {
	//Check system response when user input an email with a wrong format
		al = new AdminLogin(driver);
		al.getForgotPasswordEmailField().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("invalidEmailAddress"));
		Assert.assertFalse(al.getResetButton().isEnabled());
	}
	
	@Test(priority=3, dataProvider="invalidGetData", dataProviderClass=AdminLoginTest.class)
	public void InvalidEmailAddressNotFound(HashMap<String, String> data) throws InterruptedException {
		//Check system response when user input an email not in the system
		al = new AdminLogin(driver);
		al.getForgotPasswordEmailField().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("invalidEmailResetPassword"));
		al.getResetButton().click();
	    Assert.assertEquals(al.getResetPasswordMessage().getText(), "A password reset has been mailed to "
	    		+ "you if an account with that email exists");  
	}
	
	@Test(priority=4, dataProvider="getData", dataProviderClass=AdminLoginTest.class)
	public void ValidateSuccessfulResetPassword(HashMap<String, String> data) {
		//Verify user can reset password
		al = new AdminLogin(driver);
		al.getForgotPasswordEmailField().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("emailAddress"));
		al.getResetButton().click();
	    Assert.assertEquals(al.getResetPasswordMessage().getText(), "A password reset has been mailed to "
	    		+ "you if an account with that email exists");  
	}
	
	@Test(priority=5)
	public void ValidateBackToLoginLink() {
		//Verify user can reset password
		al = new AdminLogin(driver);
		al.getBacktoLoginLink().click();
		Assert.assertTrue(al.getEmailAddressField().isDisplayed());
	}
	
	@AfterClass
	public void terminate() {
	driver.close();
	}
}
