package agent;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import pageObject.agent.AgentLogin;
import pageObject.agent.Homepage;
import pageObject.agent.RegistrationLandingPage;
import resources.base;

public class HomePageTest extends base {
	public WebDriver driver;
	Homepage hp;
	AgentLogin ag;
	RegistrationLandingPage rl;
	SoftAssert soft;

	@BeforeClass
	public void initialize() throws IOException {
		driver= InitializeBrowser();
		driver.get(prop.getProperty("AgentUrl"));
		driver.manage().window().maximize();
	}
	
	
@Test(priority=1)
	public void ValidateAgentLoginLink() {
		//Verify the "Agent Login" Link is clickable
		hp = new Homepage(driver);
		hp.getAgentLogin().click();
		ag = new AgentLogin(driver);
		Assert.assertTrue(ag.AgentProfileHeader().isDisplayed());
		
	}
	@Test(priority=2)
	public void ValidateRegistrationButton() {
		//Verify user can view the "Register as an Agent button"
		//Verify clicking on the "Register as an Agent" button takes user to the Start application form page
		hp = new Homepage(driver);
		hp.gethomePagelink().click();
		 soft = new SoftAssert();
		hp.getRegistrationButton().click();
		 rl = new RegistrationLandingPage(driver);
		 soft.assertTrue(rl.getformHeader().isDisplayed());
		 soft.assertEquals(rl.getformHeader().getText(), "Register as an Agent"); 
		 soft.assertAll();
	}
	
	@Test(priority=3)
	public void ValidateChatBot() throws InterruptedException {
		//Verify the chatbot icon is clickable
		hp = new Homepage(driver);
		hp.gethomePagelink().click();
		driver.switchTo().frame(hp.getframeChatBot());
		hp.getchatBot().click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		soft = new SoftAssert();
		soft.assertTrue(hp.getchatName().isDisplayed());
		soft.assertTrue(hp.getchatEmail().isDisplayed());
		soft.assertTrue(hp.getchatMessage().isDisplayed());
		
		soft.assertAll();
		
	}
	
	@Test(priority=4, dataProvider="getData")
	public void InvalidChatRequestNoName(String name, String emailaddress, String message) {
		//Verify user is unable to submit request if the Name field is empty
		hp = new Homepage(driver);
		hp.getchatEmail().sendKeys(Keys.chord(Keys.COMMAND,"a"), emailaddress);
		hp.getchatMessage().sendKeys(Keys.chord(Keys.COMMAND,"a"), message);
		hp.getchatsubmit().click();
		Assert.assertEquals(hp.getchatErrorMessage().getText(), "This field is required"); 
	
			}

		
	
	@Test(priority=5, dataProvider="getData")
	public void InvalidChatRequestNoEmail(String name, String emailaddress, String message) {
		//Verify user is unable to submit request if the emailAddress field is empty
		hp = new Homepage(driver);
		hp.getchatEmail().sendKeys(Keys.chord(Keys.COMMAND,"a"), (Keys.chord(Keys.DELETE)));
		hp.getchatName().sendKeys(Keys.chord(Keys.COMMAND,"a"), name);
		hp.getchatMessage().sendKeys(Keys.chord(Keys.COMMAND,"a"), message);
		hp.getchatsubmit().click();
		Assert.assertEquals(hp.getchatErrorMessage().getText(), "This field is required"); 
		
		
	}
	
	@Test(priority=6, dataProvider="getData")
	public void InvalidChatRequestNoMessage(String name, String emailaddress, String message) {
		//Verify user is unable to submit request if the Message field is empty
		hp = new Homepage(driver);
		hp.getchatMessage().sendKeys(Keys.chord(Keys.COMMAND,"a"), (Keys.chord(Keys.DELETE)));
		hp.getchatName().sendKeys(Keys.chord(Keys.COMMAND,"a"), name);
		hp.getchatEmail().sendKeys(Keys.chord(Keys.COMMAND,"a"), emailaddress);
		hp.getchatsubmit().click();
		Assert.assertEquals(hp.getchatErrorMessage().getText(), "This field is required"); 
		
		
	}
	
	@Test(priority=7, dataProvider="invalidEmail")
	public void InvalidChatRequestInvalidEmail(String name, String emailaddress, String message) {
	//Verify user is unable to submit request if user inputs an invalid email address format
	hp.getchatName().sendKeys(Keys.chord(Keys.COMMAND,"a"), name);
	hp.getchatEmail().sendKeys(Keys.chord(Keys.COMMAND,"a"), emailaddress);
	hp.getchatMessage().sendKeys(Keys.chord(Keys.COMMAND,"a"), message);
	hp.getchatsubmit().click();
	Assert.assertEquals(hp.getchatErrorMessage().getText(), "Invalid email address."); 
	}
	
	@Test(priority=8)
	public void ValidateChatButtonResponse() {
	//Verify clicking on the cancel (X) button closes the chatbot form	
	hp = new Homepage(driver);
	soft = new SoftAssert();
	soft.assertTrue(hp.getframeChatForm().isDisplayed());
	driver.switchTo().defaultContent();
	driver.switchTo().frame(hp.getframeChatBot());
	hp.getchatBot().click();
	driver.switchTo().defaultContent();
	driver.switchTo().frame(1);
	soft.assertFalse(hp.getframeChatForm().isDisplayed());
	soft.assertAll();
	}
	
	@Test(priority=9, dataProvider="getData")
	public void ValidChatRequest(String name, String emailaddress, String message) {
	//Verify user is able to submit request from the chatbot form
		hp = new Homepage(driver);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(hp.getframeChatBot());
		hp.getchatBot().click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);	
		hp.getchatName().sendKeys(Keys.chord(Keys.COMMAND,"a"), name);
		hp.getchatEmail().sendKeys(Keys.chord(Keys.COMMAND,"a"), emailaddress);
		hp.getchatMessage().sendKeys(Keys.chord(Keys.COMMAND,"a"), message);
		hp.getchatsubmit().click();
		Assert.assertEquals(hp.getchatSuccessMessage().getText(), "Your message was sent successfully!"); 
		
	}
	
	@DataProvider
	public Object[][] getData() {
		Object[][] data=new Object[1][3];
		data[0][0]="Tester1";
		data[0][1]="Tester1@gmail.com";
		data[0][2]="How do i start agent registrattion on the platform";
		
		/*data[1][0]="Tester2";
		data[1][1]="Tester2@gmail.com";
	    data[1][2]="What are the list of required ids";*/
		return data;
	}
	
	@DataProvider
	public Object[][] invalidEmail() {
		Object[][] data=new Object[3][3];
		data[0][0]="Tester1";
		data[0][1]="Tester1gmail.com";
		data[0][2]="How do i start agent registrattion on the platform";
		
		data[1][0]="Tester2";
		data[1][1]="Tester2@gmail.c";
	    data[1][2]="What are the list of required ids";
	    
	    data[2][0]="Tester2";
		data[2][1]="Tester2@gmail.com1";
	    data[2][2]="What are the list of required ids";
	    
	    
		return data;
 
	}
	
	
	
	
	
	
	
	
	
	
	@AfterClass
	public void terminate() {
		driver.close();
	}
}
