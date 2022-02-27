package agent;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.agent.BusinessAddress;
import pageObject.agent.Homepage;
import pageObject.agent.IdentityInformation;
import pageObject.agent.PersonalInformation;
import pageObject.agent.ResidentialAddress;
import resources.base;

public class BusinessAddressTest extends base{

	RegisterAsAnAgentTest rt;
	PersonalInformation pi;
	Homepage hp;
	BusinessAddress ba;
	IdentityInformation id;
	ResidentialAddress ra;
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
	
	
	@Test(priority=1, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class)
	public void ValidateBusinessAddressPage(HashMap <String, String> data) {
		//Verify user is taken to the Business Address page
		 rt =new RegisterAsAnAgentTest();
	
		rt.ValidateAcceptConsentForm1(driver, data);
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getbusinessAddress());
		ba = new BusinessAddress(driver); 
	    Assert.assertTrue(ba.getaddress().isDisplayed());	
		
}
	
	@Test(priority=2, dataProvider="getData")
	public void EmptyLcaField(HashMap <String, String> data) {
		//Verify user is unable continue application if the LCA/LCDA field is empty
		ba = new BusinessAddress(driver); 
		ba.getaddress().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("address"));
		ba.getlandmark().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("Landmark"));
		Assert.assertFalse(ba.getcontinueButton().isEnabled());
	}
	
	@Test(priority=3, dataProvider="getData")
	public void EmptyAddressField(HashMap <String, String> data) {
		//Verify user is unable continue application if the Address field is empty
		ba = new BusinessAddress(driver); 
		ba.getlandmark().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("Landmark"));
		Select lcaDropdown= new Select(ba.getlgaLcda());
		lcaDropdown.selectByVisibleText(data.get("LCA/LCDA"));
		Assert.assertFalse(ba.getcontinueButton().isEnabled());
	}
	
	
	@Test(priority=4, dataProvider="getData")
	public void EmptyLandmarkField(HashMap <String, String> data) {
		//Verify user is unable continue application if the Landmark field is empty
		ba = new BusinessAddress(driver); 
		ba.getaddress().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("address"));
		Select lcaDropdown= new Select(ba.getlgaLcda());
		lcaDropdown.selectByVisibleText(data.get("LCA/LCDA"));
		Assert.assertFalse(ba.getcontinueButton().isEnabled());
		
	}
	
	@Test(priority=5)
	public void ValidateLcaArrangement() {
		//Verify the LCA/LCDA dropdown list is arranged alphabetically
		ba = new BusinessAddress(driver);
		Select lcaDropdown= new Select(ba.getlgaLcda());
		List<WebElement> allOptions = lcaDropdown.getOptions();
		List<String> originalList= allOptions.stream().map(a->a.getText()).collect(Collectors.toList());
		List<String> sortedList= allOptions.stream().map(a->a.getText()).sorted().collect(Collectors.toList());
		System.out.println(originalList);
		System.out.println(sortedList);
		Assert.assertEquals(originalList, sortedList);
		
	}
	
	@Test(priority=6)
	public void ValidatePreviewButton() {
		//Verify user can view the Preview and Submit button and it is disabled if user has not filled all required forms
		ba = new BusinessAddress(driver);
		soft=new SoftAssert();
		soft.assertTrue(ba.getpreviewButton().isDisplayed());
		soft.assertFalse(ba.getpreviewButton().isEnabled());
		
		soft.assertAll();
	}
	
	@Test(priority=7)
	public void ValidateBackButton() {
	//Check system response when user click on the Back button
		ba = new BusinessAddress(driver);
		ba.getbackButton().click();
		id = new IdentityInformation(driver);
		Assert.assertTrue(id.getidType().isDisplayed());
		
	}
	
	@Test(priority=8, dataProvider="getData")
	public void ValidateSuccessfulBusinessAddressForm(HashMap <String, String> data) {
		//Verify user can continue application
		pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getbusinessAddress());
		ba = new BusinessAddress(driver); 
		ba.getaddress().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("address"));
		ba.getlandmark().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("Landmark"));
		Select lcaDropdown= new Select(ba.getlgaLcda());
		lcaDropdown.selectByVisibleText(data.get("LCA/LCDA"));
		ba.getcontinueButton().click();
		ra = new ResidentialAddress(driver);
		Assert.assertTrue(ra.getlandmark().isDisplayed());
		
	}
	
	@AfterClass
	public void terminate() {
		driver.close();
	}
	
	
	@DataProvider
	public  Object[][] getData() {
		Object[][] data1=new Object[2][];
		Map<String, String> validData = Map.of("address", "Testing the input text field just like that", "Landmark",
				"Testing","LCA/LCDA", "Apapa LGA", "AutoSuggestionAddress", "");
		Map <String, String> dp1= new HashMap <>(validData);
		
		data1[0]= new Object[] { dp1 };
		Map<String, String> validData1 = Map.of("address", "Testing the input text field just like that","Landmark",
				"Testing 2","LCA/LCDA", "Yaba LCDA");
		Map <String, String> dp4= new HashMap <>(validData1);
		
		data1[1]= new Object[] { dp4 };
		return data1;
	}
	
}
