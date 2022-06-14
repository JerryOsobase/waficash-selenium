package admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.admin.AdminLogin;
import pageObject.admin.DashBoard;
import resources.base;

public class AdminLoginTest extends base {
	AdminLogin al;
	SoftAssert soft;
	DashBoard db;

	@BeforeClass
	public void initialize() throws IOException {
		driver= InitializeBrowser();
		driver.get(prop.getProperty("AdminOpUrl"));
		driver.manage().window().maximize();
	}
	
	@Test(priority=1)
	public void EmptyEmailAddressField() {
		//Check system reponse when the email address field is empty
		al = new AdminLogin(driver);
		Assert.assertFalse(al.getContinueButton().isEnabled());
	}
	
	/*@Test(priority=2, dataProvider="invalidGetData")
	public void InvalidEmailAddressNotFound(HashMap<String, String> data) throws InterruptedException {
		//Check system response when user input a wrong email address not in the system
		al = new AdminLogin(driver);
		al.getEmailAddressField().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("emailNotFound"));
		al.getContinueButton().click();
		Thread.sleep(3000);
	    Assert.assertEquals(al.getPromptMessage().getText(), "Error\n"
	    		+ "Couldn't find your account.");  
		
		
	}
	
	@Test(priority=3, dataProvider="invalidGetData")
	public void InvalidEmailAddressFormat(HashMap<String, String> data) {
		//Check system response when user input a wrong email format
		al = new AdminLogin(driver);
		al.getEmailAddressField().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("invalidEmailAddress"));
		Assert.assertFalse(al.getContinueButton().isEnabled());
	}
	
	@Test(priority=4, dataProvider="mergedData")
	public void ValidEmailAddress(HashMap<String, String> data) {
	//Check system reponse when user input a correct email address
		al = new AdminLogin(driver);
		al.getEmailAddressField().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("emailAddress"));
		al.getContinueButton().click();
		Assert.assertTrue(al.getPasswordField().isDisplayed());	
	}
	
	@Test(priority=5, dataProvider="mergedData")
	public void ValidateBackButton(HashMap<String, String> data) {
	//Check system response when user click on the back arrow
		al = new AdminLogin(driver);
		soft = new SoftAssert();
		soft.assertTrue(al.getBackLink().getText().contains(data.get("emailAddress")));
		al.getBackLink().click();
		soft.assertTrue(al.getEmailAddressField().isDisplayed());
		soft.assertAll();
	}*/
	
	@Test(priority=6, dataProvider="mergedData")
	public void ValidateSuccessfulAdminLogin(HashMap<String, String> data) throws InterruptedException {
		//Verify user is able to login
		al = new AdminLogin(driver);
		soft = new SoftAssert();
		al.getEmailAddressField().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("emailAddress"));
		al.getContinueButton().click();
		al.getPasswordField().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("invalidPassword"));
		soft.assertTrue(al.getPasswordField().getAttribute("type").equals("password"));
		al.getEyeIcon().click();
		soft.assertTrue(al.getPasswordField().getAttribute("type").equals("text"));
		al.getEyeIcon().click();
		al.getPasswordField().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("password"));
		al.getLoginButton().click();
		Thread.sleep(3000);
		soft.assertEquals(al.getPromptMessage().getText(), "Success\n"
				+ "Login successfully!");
		db = new DashBoard(driver);
		soft.assertEquals(db.getDashBoardHeader().getText(), "Dashboard");
		soft.assertAll();
	}
	
	@Test(priority=7, dependsOnMethods="ValidateSuccessfulAdminLogin")
	public void ValidateLogout() {
		//Verify user is able to login
		db = new DashBoard(driver);
		db.getProfileDropdown().click();
		db.getLogout().click();
		driver.navigate().back();
		al = new AdminLogin(driver);
		soft = new SoftAssert();
		soft.assertEquals(al.getPromptMessage().getText(), "Error\n"
				+ "Please login to continue");
		soft.assertTrue(al.getEmailAddressField().isDisplayed());
		soft.assertAll();
	}
	
	
	@AfterClass
	public void terminate() {
	driver.close();
	}
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@DataProvider
	public Object[][] mergedData() {
		HashMap <String, String> dp3= new HashMap <String, String>();
		dp3.putAll((HashMap<String, String>) getData()[0][0]);
		dp3.putAll((HashMap<String, String>) invalidGetData()[0][0]);
		/*HashMap <String, String> dp5= new HashMap <String, String>();
		//dp5.putAll((HashMap<String, String>) getData()[1][0]);
		dp5.putAll((HashMap<String, String>) invalidGetData()[1][0]);*/
		Object[][] o=new Object [1][];
		o[0] = new Object[] { dp3 };
		//o[1] = new Object[] { dp5 };
		return o;
	}
	
	@DataProvider
	public  Object[][] getData() {
		Object[][] data1=new Object[2][];
		Map<String, String> validData = Map.of("emailAddress", "base4jerry@gmail.com", "password", "Osobase4@", "agentNoCoupon", "jtest@gmail.com", 
				"lga", "Amuwo-Odofin LGA", "permitName", "test", "companyEmail", "cobili4@hbehs.com",
				"companyPhoneNumber", "84948940303", "singleCoupon", "12500", "multipleCoupon", "62500", "agentsearch", "Jerry");
		Map <String, String> dp1= new HashMap <>(validData);
		Map<String, String> validData1 = Map.of("emailAddress", "base4jerry@gmail.com", "password", "Osobase4@", "agentNoCoupon", "jtest@gmail.com", 
				"lga", "Yaba LCDA", "permitName", "online", "companyEmail", "cobili1284@hbehs.co", 
				"companyPhoneNumber", "84948940399", "singleCoupon", "12500", "multipleCoupon", "62500", "agentsearch", "Chioma");
		Map <String, String> dp3= new HashMap <>(validData1);
		data1[0]= new Object[] { dp1 };
		data1[1]= new Object[] { dp3 };
		return data1;
	}
	
	@DataProvider
	public Object[][] invalidGetData() {
		Object[][] data2=new Object[2][];
		Map<String, String> invalidData = Map.of("emailNotFound","tryme@gmail.com", "invalidEmailAddress", "jerry@gmail.c", 
				"invalidPassword", "OSOBASE4@", "invalidEmailResetPassword", "jtest@gmail.com", "invalidMultipleCouponQuantity", "0");
		Map <String, String> dp2= new HashMap <>(invalidData);
		
		Map<String, String> invalidData1 = Map.of("emailNotFound","justinbieber@gmail.com", "invalidEmailAddress", "jerrygmail.com", 
				"invalidPassword", "tesTing##", "invalidEmailResetPassword", "jtest+001@gmail.com", "invalidMultipleCouponQuantity", "1");
		Map <String, String> dp6= new HashMap <>(invalidData1);
	
		data2[0]= new Object[] { dp2 };
		data2[1]= new Object[] { dp6 };
		return data2;
	}
	
	public void ValidateSuccessfulAdminLogin1(WebDriver driver, HashMap<String, String> data){
		//Verify user is able to login
		al = new AdminLogin(driver);
		al.getEmailAddressField().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("emailAddress"));
		al.getContinueButton().click();
		al.getPasswordField().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("password"));
		al.getLoginButton().click();
	}
}
