package agent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.agent.ConsentForm;
import pageObject.agent.Homepage;
import pageObject.agent.PersonalInformation;
import pageObject.agent.RegistrationLandingPage;
import resources.base;

public class RegisterAsAnAgentTest extends base{
RegistrationLandingPage rlp;
Homepage hp;
ConsentForm cf;
PersonalInformation pi;
SoftAssert soft;


	@BeforeClass
	public void initialize() throws IOException {
		driver= InitializeBrowser();
		driver.get(prop.getProperty("AgentUrl"));
		driver.manage().window().maximize();
		hp = new Homepage(driver);
		hp.getRegistrationButton().click();
		
	}
	
	@AfterMethod()
	public void refreshPage() {
		driver.navigate().refresh();
		
	}
	

	
	
	@Test(priority=1)
	public void ValidateRegistrationForm() {
		//Verify user can view the Register as an Agent form
		rlp = new RegistrationLandingPage(driver);
		soft = new SoftAssert();
		soft.assertTrue(rlp.getformHeader().isDisplayed());
		soft.assertEquals(rlp.getformHeader().getText(), "Register as an Agent"); 
		soft.assertTrue(rlp.getEmail().isDisplayed());
		soft.assertTrue(rlp.getphoneNumber().isDisplayed());
		soft.assertTrue(rlp.getcompany().isDisplayed());
		soft.assertTrue(rlp.getcontinueButton().isDisplayed());
		soft.assertAll();
	}
	
	@Test(priority=2, dataProvider="mergedData")
	public void EmptyEmailField(HashMap <String, String> data) {
		//Verify the continue button is disabled if the email address field is empty
		rlp = new RegistrationLandingPage(driver);
		rlp.getphoneNumber().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("phonenumber"));
		rlp.getpermitCategory().stream().filter(v->v.getText().equals(data.get("permitcategory"))).findAny().get().click();  
		WebElement dropdown = rlp.getcompany();
		Select d = new Select (dropdown);
		d.selectByVisibleText(data.get("company"));
		Assert.assertFalse(rlp.getcontinueButton().isEnabled());
	}
	
	@Test(priority=3, dataProvider="mergedData")
	public void InvalidEmailAddress(HashMap <String, String> data) {
	//Verify user is unable to continue application if the user input an email with a wrong format
		rlp = new RegistrationLandingPage(driver);
		rlp.getEmail().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("Invalidemailaddress"));
		rlp.getphoneNumber().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("phonenumber"));
		rlp.getpermitCategory().stream().filter(v->v.getText().equals(data.get("permitcategory"))).findAny().get().click();  
		WebElement dropdown = rlp.getcompany();
		Select d = new Select (dropdown);
		d.selectByVisibleText(data.get("company"));
		Assert.assertFalse(rlp.getcontinueButton().isEnabled());
	}
	
	@Test(priority=4, dataProvider="mergedData")
	public void EmptyPhoneNumberField(HashMap <String, String> data) {
		//Verify the continue button is disabled if the phone number field is empty
		rlp = new RegistrationLandingPage(driver);
		rlp.getEmail().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("emailaddress"));
		rlp.getpermitCategory().stream().filter(v->v.getText().equals(data.get("permitcategory"))).findAny().get().click();  
		WebElement dropdown = rlp.getcompany();
		Select d = new Select (dropdown);
		d.selectByVisibleText(data.get("company"));
		Assert.assertFalse(rlp.getcontinueButton().isEnabled());
	}
	
	@Test(priority=5, dataProvider="mergedData")
	public void InvalidPhoneNumber(HashMap <String, String> data) {
		//Verify the user is unable to continue application if the user a phone number format 
		rlp = new RegistrationLandingPage(driver);
		rlp.getEmail().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("emailaddress"));
		rlp.getphoneNumber().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("Invalidphonenumber"));
		rlp.getpermitCategory().stream().filter(v->v.getText().equals(data.get("permitcategory"))).findAny().get().click();  
		WebElement dropdown = rlp.getcompany();
		Select d = new Select (dropdown);
		d.selectByVisibleText(data.get("company"));
		Assert.assertFalse(rlp.getcontinueButton().isEnabled());
	
	}
	
	@Test(priority=6, dataProvider="mergedData")
	public void EmptyPermitCategory(HashMap <String, String> data) {
		//Verify the user is unable to continue application if no permit category is selected
		rlp = new RegistrationLandingPage(driver);
		rlp.getEmail().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("emailaddress"));
		rlp.getphoneNumber().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("phonenumber"));
		Assert.assertFalse(rlp.getcontinueButton().isEnabled());
	}
	
	@Test(priority=7, dataProvider="mergedData")
	public void EmptyCompany(HashMap <String, String> data) {
		//Verify user is unable to continue application if no company is selected
		rlp = new RegistrationLandingPage(driver);
		rlp.getEmail().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("emailaddress"));
		rlp.getphoneNumber().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("phonenumber"));
		rlp.getpermitCategory().stream().filter(v->v.getText().equals(data.get("permitcategory"))).findAny().get().click();  
		Assert.assertFalse(rlp.getcontinueButton().isEnabled());
	}
	
	@Test(priority=8, dataProvider="mergedData")
	public void ValidateConsentForm(HashMap <String, String> data) {
	//Verify user is taken to the consent form page
		//Verify user can reject consent
		rlp = new RegistrationLandingPage(driver);
		rlp.getEmail().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("emailaddress"));
		rlp.getphoneNumber().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("phonenumber"));
		rlp.getpermitCategory().stream().filter(v->v.getText().equals(data.get("permitcategory"))).findAny().get().click();  
		WebElement dropdown = rlp.getcompany();
		Select d = new Select (dropdown);
		d.selectByVisibleText(data.get("company"));
		rlp.getcontinueButton().click();
		cf = new ConsentForm (driver);
		soft = new SoftAssert();
		soft.assertTrue(cf.getpageHeader().isDisplayed());
		soft.assertEquals(cf.getpageHeader().getText(), "Consent");
		soft.assertTrue(cf.getcancelButton().isDisplayed());
		soft.assertEquals(cf.getcancelButton().getText(), "Cancel");
		cf.getcancelButton().click();
		soft.assertTrue(rlp.getformHeader().isDisplayed());
		soft.assertEquals(rlp.getformHeader().getText(), "Register as an Agent"); 
		
		soft.assertAll();
		
	}
	
	@Test(priority=9, dataProvider="mergedData")
	public void ValidateAcceptConsentForm(HashMap <String, String> data) {
	//Verify user can accept consent
	
		rlp = new RegistrationLandingPage(driver);
		rlp.getEmail().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("emailaddress"));
		rlp.getphoneNumber().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("phonenumber"));
		rlp.getpermitCategory().stream().filter(v->v.getText().equals(data.get("permitcategory"))).findAny().get().click();  
		WebElement dropdown = rlp.getcompany();
		Select d = new Select (dropdown);
		d.selectByVisibleText(data.get("company"));
		rlp.getcontinueButton().click();
		cf = new ConsentForm (driver);
		soft = new SoftAssert();
		soft.assertTrue(cf.getcontinueButton().isDisplayed());
		soft.assertEquals(cf.getcontinueButton().getText(), "I Understand, Continue");
		cf.getcontinueButton().click();
		pi=new PersonalInformation(driver);
		soft.assertTrue(pi.getformHeader().isDisplayed());
		soft.assertEquals(pi.getformHeader().getText(), "Agent Application Form");
		
		soft.assertAll();

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
		Map<String, String> validData = Map.of("emailaddress", "cobavah864@toudrum.com","phonenumber", "93410202617", 
				"permitcategory", "Pools Betting","company", "test bet", "firstname", "Test", "lastname", "Test", "guarantorEmail",
				"pedrasuspo@vusra.com", "guarantorPhone", "11102938479", "guarantorname", "john", "validCoupon","SEYOO-2LSXC");
		Map <String, String> dp1= new HashMap <>(validData);
		
		data1[0]= new Object[] { dp1 };
		Map<String, String> validData1 = Map.of("emailaddress", "cobavah864@toudrum.co", "phonenumber", "02923790462", "permitcategory",
				"Online Casino", "company", "test bet 2", "firstname", "Testboy", "lastname", 
				"noname", "guarantorEmail", "pedrasuspo@vusra.com", "guarantorPhone", "01928374904", "validCoupon", "SEYOO-5UQOK");
		Map <String, String> dp4= new HashMap <>(validData1);
		
		data1[1]= new Object[] { dp4 };
		return data1;
	}
	
	@DataProvider
	public Object[][] invalidGetData() {
		Object[][] data2=new Object[2][];
		Map<String, String> invalidData = Map.of("Invalidemailaddress", "email.com","Invalidphonenumber", "9593952048", "invalidCoupon", "okokooko");
		Map <String, String> dp2= new HashMap <>(invalidData);
		
		Map<String, String> invalidData1 = Map.of("Invalidemailaddress", "email@gmail.c","Invalidphonenumber", "9593952$4a4", "invalidCoupon", "okokooko1");
		Map <String, String> dp6= new HashMap <>(invalidData1);
	
		data2[0]= new Object[] { dp2 };
		data2[1]= new Object[] { dp6 };
		return data2;
	}
	
	
	
	@AfterClass
	public void terminate() {
		driver.close();
	}

	public void ValidateAcceptConsentForm1(WebDriver driver, HashMap<String, String> data) {
		// TODO Auto-generated method stub
		rlp = new RegistrationLandingPage(driver);
		rlp.getEmail().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("emailaddress"));
		rlp.getphoneNumber().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("phonenumber"));
		rlp.getpermitCategory().stream().filter(v->v.getText().equals(data.get("permitcategory"))).findAny().get().click();  
		WebElement dropdown = rlp.getcompany();
		Select d = new Select (dropdown);
		d.selectByVisibleText(data.get("company"));
		rlp.getcontinueButton().click();
		cf = new ConsentForm (driver);
		cf.getcontinueButton().click();
	}
}
