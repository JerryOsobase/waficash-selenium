package agent;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
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
import pageObject.agent.GuarantorInformation;
import pageObject.agent.Homepage;
import pageObject.agent.IdentityInformation;
import pageObject.agent.PersonalInformation;
import pageObject.agent.ResidentialAddress;
import resources.base;

public class ResidentialAddressTest extends base{

	RegisterAsAnAgentTest rt;
	PersonalInformation pi;
	Homepage hp;
	BusinessAddress ba;
	IdentityInformation id;
	ResidentialAddress ra;
	SoftAssert soft;
	GuarantorInformation gi;
	
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
	public void ValidateResidentialAddressPage(HashMap <String, String> data) {
		//Verify user is taken to the Residential Address page
		 rt =new RegisterAsAnAgentTest();
	
		rt.ValidateAcceptConsentForm1(driver, data);
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getresidentialAddress());
		ra = new ResidentialAddress(driver); 
	    Assert.assertTrue(ra.getaddress().isDisplayed());	
		
}
	
	@Test(priority=2, dataProvider="getData")
	public void EmptyLcaField(HashMap <String, String> data) {
		//Verify user is unable continue application if the LCA/LCDA field is empty
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getresidentialAddress());
		ra = new ResidentialAddress(driver); 
		ra.getaddress().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("address"));
		ra.getlandmark().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("Landmark"));
		Assert.assertFalse(ra.getcontinueButton().isEnabled());
	}
	
	@Test(priority=3, dataProvider="getData")
	public void EmptyAddressField(HashMap <String, String> data) {
		//Verify user is unable continue application if the Address field is empty
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getresidentialAddress());
		ra = new ResidentialAddress(driver); 
		ra.getlandmark().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("Landmark"));
		Select lcaDropdown= new Select(ra.getlgaLcda());
		lcaDropdown.selectByVisibleText(data.get("LCA/LCDA"));
		Assert.assertFalse(ra.getcontinueButton().isEnabled());
	}
	
	
	@Test(priority=4, dataProvider="getData")
	public void EmptyLandmarkField(HashMap <String, String> data) {
		//Verify user is unable continue application if the Landmark field is empty
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getresidentialAddress());
		ra = new ResidentialAddress(driver); 
		ra.getaddress().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("address"));
		Select lcaDropdown= new Select(ra.getlgaLcda());
		lcaDropdown.selectByVisibleText(data.get("LCA/LCDA"));
		Assert.assertFalse(ra.getcontinueButton().isEnabled());
		
	}
	
	@Test(priority=5)
	public void ValidateLcaArrangement() {
		//Verify the LCA/LCDA dropdown list is arranged alphabetically
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getresidentialAddress());
		ra = new ResidentialAddress(driver);
		Select lcaDropdown= new Select(ra.getlgaLcda());
		List<WebElement> allOptions = lcaDropdown.getOptions();
		List<String> originalList= allOptions.stream().map(a->a.getText()).collect(Collectors.toList());
		List<String> sortedList= allOptions.stream().map(a->a.getText()).sorted().collect(Collectors.toList());
		Assert.assertEquals(originalList, sortedList);
		
	}
	
	@Test(priority=6)
	public void ValidatePreviewButton() {
		//Verify user can view the Preview and Submit button and it is disabled if user has not filled all required forms
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getresidentialAddress());
		ra = new ResidentialAddress(driver);
		soft=new SoftAssert();
		soft.assertTrue(ra.getpreviewButton().isDisplayed());
		soft.assertFalse(ra.getpreviewButton().isEnabled());
		
		soft.assertAll();
	}
	
	@Test(priority=7)
	public void ValidateBackButton() {
	//Check system response when user click on the Back button
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getresidentialAddress());
		ra = new ResidentialAddress(driver);
		ra.getbackButton().click();
		ba = new BusinessAddress(driver);
		Boolean staleElement = true;
		while(staleElement){

			  try{

				  Assert.assertTrue(ba.getaddress().isDisplayed());

			     staleElement = false;


			  } catch(StaleElementReferenceException e){

			    staleElement = true;

			  }

			}
		
		
	}
	
	@Test(priority=8, dataProvider="getData")
	public void ValidateSuccessfulResidentialAddressForm(HashMap <String, String> data) {
		//Verify user can continue application
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getresidentialAddress());
		ra = new ResidentialAddress(driver); 
		ra.getaddress().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("address"));
		ra.getlandmark().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("Landmark"));
		Select lcaDropdown= new Select(ra.getlgaLcda());
		lcaDropdown.selectByVisibleText(data.get("LCA/LCDA"));
		ra.getcontinueButton().click();
		gi = new GuarantorInformation(driver);
		Assert.assertTrue(gi.getfirstName().isDisplayed());
		
	}
	
	@AfterClass
	public void terminate() {
		driver.close();
	}
	
	
	@DataProvider
	public  Object[][] getData() {
		Object[][] data1=new Object[2][];
		Map<String, String> validData = Map.of("address", "Testing the input text field just like that", "Landmark",
				"Testing","LCA/LCDA", "Apapa LGA");
		Map <String, String> dp1= new HashMap <>(validData);
		
		data1[0]= new Object[] { dp1 };
		Map<String, String> validData1 = Map.of("address", "Testing the input text field just like that","Landmark",
				"Testing 2","LCA/LCDA", "Yaba LCDA");
		Map <String, String> dp4= new HashMap <>(validData1);
		
		data1[1]= new Object[] { dp4 };
		return data1;
	}
	
}
