package agent;

import java.io.IOException;
import java.util.HashMap;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.agent.Homepage;
import pageObject.agent.IdentityInformation;
import pageObject.agent.PersonalInformation;
import resources.base;

public class PersonalInformationTest extends base {
 // public WebDriver driver;
	
	Homepage hp;
	RegisterAsAnAgentTest rt;
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
	
	@Test(priority=1, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class)
	public void EmptyCameraUpload(HashMap <String, String> data) {
		//Verify the user is unable to save and continue if the user has not uploaded a photo
		 rt =new RegisterAsAnAgentTest();
	
		//rt.ValidateRegistrationForm(driver);
		rt.ValidateAcceptConsentForm1(driver, data);
		pi = new PersonalInformation(driver);
		pi.getfirsttName().sendKeys(data.get("firstname"));
		pi.getlastName().sendKeys(data.get("lastname"));
		Select genderDropdown =new Select(pi.getgender());
		genderDropdown.selectByIndex(1);
		pi.getcalendar().click();
		pi.getcalendarYear().sendKeys(Keys.chord(Keys.COMMAND, "a"), "1995");
		Select CalendarMonthDropdown = new Select(pi.getcalendarMonthHeader());
		CalendarMonthDropdown.selectByVisibleText("August");
		for(int d=0; d<pi.getcalendarDate().size(); d++) {
			if(pi.getcalendarDate().get(d).getText().equalsIgnoreCase("15")) {
				pi.getcalendarDate().get(d).click();
				break;
			}	
		}
		Assert.assertFalse(pi.getcontinueButton().isEnabled());
		
	}
	
	@Test(priority=2, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class)
	public void EmptyFirstNameField(HashMap <String, String> data) throws InterruptedException {
		//Verify the user is unable to save and continue if the first name field is empty
		Thread.sleep(10000);
		pi = new PersonalInformation(driver);
		pi.getfirsttName().sendKeys(Keys.chord(Keys.COMMAND, "a"), " ");
		pi.getlastName().sendKeys("");
		Select genderDropdown =new Select(pi.getgender());
		genderDropdown.selectByIndex(1);
		pi.getcalendar().click();
		pi.getcalendarYear().sendKeys(Keys.chord(Keys.COMMAND, "a"), "1995");
		Select CalendarMonthDropdown = new Select(pi.getcalendarMonthHeader());
		CalendarMonthDropdown.selectByVisibleText("August");
		for(int d=0; d<pi.getcalendarDate().size(); d++) {
			if(pi.getcalendarDate().get(d).getText().equalsIgnoreCase("15")) {
				pi.getcalendarDate().get(d).click();
				break;
			}	
		}
		Assert.assertFalse(pi.getcontinueButton().isEnabled());
	}
	
	@Test(priority=3, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class)
	public void EmptyLastNameField(HashMap <String, String> data) {
		//Verify the user is unable to save and continue if the last name field is empty
		pi = new PersonalInformation(driver);
		pi.getfirsttName().sendKeys(data.get("firstname"));
		pi.getlastName().sendKeys(Keys.chord(Keys.COMMAND, "a"), " ");
		Select genderDropdown =new Select(pi.getgender());
		genderDropdown.selectByIndex(2);
		pi.getcalendar().click();
		pi.getcalendarYear().sendKeys(Keys.chord(Keys.COMMAND, "a"), "1995");
		Select CalendarMonthDropdown = new Select(pi.getcalendarMonthHeader());
		CalendarMonthDropdown.selectByVisibleText("August");
		for(int d=0; d<pi.getcalendarDate().size(); d++) {
			if(pi.getcalendarDate().get(d).getText().equalsIgnoreCase("15")) {
				pi.getcalendarDate().get(d).click();
				break;
			}	
		}
		Assert.assertFalse(pi.getcontinueButton().isEnabled());
	}

	@Test(priority=4, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class)
	public void InvalidEmailField(HashMap <String, String> data) {
		//Check system response when user input an invalid email address
		pi.getfirsttName().sendKeys("");
		pi.getlastName().sendKeys(data.get("lastname"));
		Select genderDropdown =new Select(pi.getgender());
		genderDropdown.selectByIndex(1);
		pi.getemail().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("Invalidemailaddress"));
		pi.getcalendar().click();
		pi.getcalendarYear().sendKeys(Keys.chord(Keys.COMMAND, "a"), "1995");
		Select CalendarMonthDropdown = new Select(pi.getcalendarMonthHeader());
		CalendarMonthDropdown.selectByVisibleText("August");
		for(int d=0; d<pi.getcalendarDate().size(); d++) {
			if(pi.getcalendarDate().get(d).getText().equalsIgnoreCase("15")) {
				pi.getcalendarDate().get(d).click();
				break;
			}	
		}
		Assert.assertFalse(pi.getcontinueButton().isEnabled());
	}
	
	@Test(priority=5, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class)
	public void InvalidPhoneNumberField(HashMap <String, String> data) {
		//Check system response when user input an invalid email address
		pi.getfirsttName().sendKeys("");
		pi.getlastName().sendKeys(data.get("lastname"));
		Select genderDropdown =new Select(pi.getgender());
		genderDropdown.selectByIndex(1);
		pi.getphoneNumber().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("Invalidphonenumber"));
		pi.getemail().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("emailaddress"));
		pi.getcalendar().click();
		pi.getcalendarYear().sendKeys(Keys.chord(Keys.COMMAND, "a"), "1995");
		Select CalendarMonthDropdown = new Select(pi.getcalendarMonthHeader());
		CalendarMonthDropdown.selectByVisibleText("August");
		for(int d=0; d<pi.getcalendarDate().size(); d++) {
			if(pi.getcalendarDate().get(d).getText().equalsIgnoreCase("15")) {
				pi.getcalendarDate().get(d).click();
				break;
			}	
		}
		Assert.assertFalse(pi.getcontinueButton().isEnabled());
	}
	
	@Test(priority=6)
	public void ValidatePreviewButton() {
		//Verify user can view the Preview and Submit button and it is disabled if user has not filled all required forms
		pi = new PersonalInformation(driver);
		soft=new SoftAssert();
		soft.assertTrue(pi.getpreviewButton().isDisplayed());
		soft.assertFalse(pi.getpreviewButton().isEnabled());
		
		soft.assertAll();
	}
	
	@Test(priority=7, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class, dependsOnMethods="EmptyCameraUpload")
	public void ValidateSuccessfulPersonalInformationForm(HashMap <String, String> data) {
		//Check system response when user fill all required information
		pi.getfirsttName().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("firstname"));
		pi.getlastName().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("lastname"));
		Select genderDropdown =new Select(pi.getgender());
		genderDropdown.selectByIndex(1);
		pi.getphoneNumber().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("phonenumber"));
		pi.getemail().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("emailaddress"));
		pi.getcalendar().click();
		pi.getcalendarYear().sendKeys(Keys.chord(Keys.COMMAND, "a"), "1995");
		Select CalendarMonthDropdown = new Select(pi.getcalendarMonthHeader());
		CalendarMonthDropdown.selectByVisibleText("August");
		for(int d=0; d<pi.getcalendarDate().size(); d++) {
			if(pi.getcalendarDate().get(d).getText().equalsIgnoreCase("15")) {
				pi.getcalendarDate().get(d).click();
				break;
			}	
		}
		pi.getcontinueButton().click();
		IdentityInformation id =new IdentityInformation(driver);
		Assert.assertTrue(id.getIdNumberBlank().isDisplayed());
	}
	
		
		@AfterClass
		public void terminate() {
			driver.close();
		}
		
	}

