package agent;

import java.io.IOException;
import java.util.Arrays;
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
import resources.base;

public class IdentityInformationTest extends base{

	Homepage hp;
	RegisterAsAnAgentTest rt;
	PersonalInformation pi;
	IdentityInformation id;
	SoftAssert soft;
	BusinessAddress ba;

	
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
	public void ValidateIdentityInformationPage(HashMap <String, String> data) {
		/*Verify user can view the following identity type;
1.BVN
2.NIN
3.Driver's License*/
		 rt =new RegisterAsAnAgentTest();
	
		rt.ValidateAcceptConsentForm1(driver, data);
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getidentityInformation());
		id = new IdentityInformation (driver);
		Select idTypeDropdown = new Select(id.getidType());
		List<WebElement> allOptions= idTypeDropdown.getOptions();
		List<String> idOptions= allOptions.stream().map(a->a.getText()).collect(Collectors.toList());
		String[] idTypeList= {"--Select Identity Type--","BVN","NIN","Driver's License"};
		List<String> idTypeListArray= Arrays.asList(idTypeList);
		Assert.assertEquals(idOptions, idTypeListArray);
		
}
	
	@Test(priority=2)
	public void ValidateBvnMessage() {
	/*Verify user can view the following mesage when the user selects 
	BVN as the identity type; "To get your BVN, dial *565*0# from your registered mobile number." and BVN sample*/
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getidentityInformation());
		id = new IdentityInformation (driver);
		Select idTypeDropdown = new Select(id.getidType());
		idTypeDropdown.selectByVisibleText("BVN");
		soft=new SoftAssert();
		soft.assertEquals(id.getidNumberMessaage().getText(), "To get your BVN, dial *565*0# from your registered mobile number.");  
		soft.assertEquals(id.getBvnSample().getText(), "e.g.: 2000456709\n"
				+ "BVN is an 11 digit number");
		
		soft.assertAll();
	}
	
	@Test(priority=3)
	public void ValidateNinSample() {
	/*Verify user can view the following mesage when the user selects NIN as the identity type; 
		"To get your NIN, dial *346# from your registered mobile number." and NIN sample*/
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getidentityInformation());
		id = new IdentityInformation (driver);
		Select idTypeDropdown = new Select(id.getidType());
		idTypeDropdown.selectByVisibleText("NIN");
		soft=new SoftAssert();
		soft.assertEquals(id.getidNumberMessaage().getText(), "To get your NIN, dial *346# from your registered mobile number.");
		soft.assertEquals(id.getNINSample().stream().count(), 2); 
		
		soft.assertAll();
	}
	
	@Test(priority=4)
	public void ValidateDriverLicenseSample() {
	/*Verify user can view the following mesage when the user selects Driver's license as the identity
	 *  type; "To get your Driver's License Number, check your Driver's License" and Driver's License sample*/
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getidentityInformation());
		id = new IdentityInformation (driver);
		Select idTypeDropdown = new Select(id.getidType());
		idTypeDropdown.selectByVisibleText("Driver's License");
		soft=new SoftAssert();
		soft.assertEquals(id.getidNumberMessaage().getText(), "To get your Driver's License Number, check your Driver's License");
		soft.assertEquals(id.getDriverSample().stream().count(), 1); 
		
		soft.assertAll();
	}
	
	@Test(priority=5)
	public void ValidateBackButton() {
		//Check system response when user click on the Back button
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getidentityInformation());
		id = new IdentityInformation (driver);
		id.getbackButton().click();
		pi = new PersonalInformation(driver);
		Assert.assertTrue(pi.getfirsttName().isDisplayed());
	}
	
	@Test(priority=6, dataProvider="invalidGetData")
	public void ValidateNinFormat(HashMap <String, String> data) {
		//Check system response when user input a wrong NIN format
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getidentityInformation());
		id = new IdentityInformation (driver);
		Select idTypeDropdown = new Select(id.getidType());
		idTypeDropdown.selectByVisibleText("NIN");
		id.getIdNumberNin().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("InvalidNIN"));
		Assert.assertFalse(id.getcontinueButton().isEnabled());
	}
	
	@Test(priority=7, dataProvider="invalidGetData")
	public void ValidateBvnFormat(HashMap <String, String> data) {
		//Check system response when user input a wrong BVN format
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getidentityInformation());
		id = new IdentityInformation (driver);
		Select idTypeDropdown = new Select(id.getidType());
		idTypeDropdown.selectByVisibleText("BVN");
		id.getIdNumberBvn().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("InvalidBVN"));
		Assert.assertFalse(id.getcontinueButton().isEnabled());
	}
	
	@Test(priority=8, dataProvider="invalidGetData")
	public void ValidateDriverLicenseFormat(HashMap <String, String> data) {
		//Check system response when user input a wrong Driver's license format
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getidentityInformation());
		id = new IdentityInformation (driver);
		Select idTypeDropdown = new Select(id.getidType());
		idTypeDropdown.selectByVisibleText("Driver's License");
		id.getIdNumberDriverLicense().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("InvalidDriverLicense"));
		Assert.assertFalse(id.getcontinueButton().isEnabled());
	}
	
	@Test(priority=9)
	public void ValidatePreviewButton() {
		//Verify user can view the Preview and Submit button and it is disabled if user has not filled all required forms
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getidentityInformation());
		id = new IdentityInformation(driver);
		soft=new SoftAssert();
		soft.assertTrue(id.getpreviewButton().isDisplayed());
		soft.assertFalse(id.getpreviewButton().isEnabled());
		
		soft.assertAll();
	}
	
	
	@Test(priority=10, dataProvider="validGetData", dependsOnMethods="ValidateIdentityInformationPage")
	public void ValidateSuccessfulIdentityInformationForm(HashMap <String, String> data) {
		//Check system response when user input a wrong NIN format
		 pi = new PersonalInformation(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", pi.getidentityInformation());
		id = new IdentityInformation (driver);
		Select idTypeDropdown = new Select(id.getidType());
		idTypeDropdown.selectByVisibleText("NIN");
		id.getIdNumberNin().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("validNIN"));
		Assert.assertTrue(id.getcontinueButton().isEnabled());
		id.getcontinueButton().click();
		ba = new BusinessAddress(driver);
		Assert.assertTrue(ba.getaddress().isDisplayed());
		
		
	}
	
	@AfterClass
	public void terminate() {
		driver.close();
	}
	
	
	@DataProvider
	public  Object[][] validGetData() {
		Object[][] data1=new Object[2][];
		Map<String, String> validData = Map.of("validNIN", "66200933010","validBVN",
				"25929833401","validDriverLicense", "Fp9433k40A13");
		Map <String, String> dp1= new HashMap <>(validData);
		
		data1[0]= new Object[] { dp1 };
		Map<String, String> validData1 = Map.of("validNIN", "6623028400","validBVN",
				"25936620332","validDriverLicense", "Fp3369k40A30");
		Map <String, String> dp4= new HashMap <>(validData1);
		
		data1[1]= new Object[] { dp4 };
		return data1;
	}
	
	@DataProvider
	public Object[][] invalidGetData() {
		Object[][] data2=new Object[2][];
		Map<String, String> invalidData = Map.of("InvalidNIN", "123456789","InvalidBVN",
				"95939520483","InvalidDriverLicense", "F9469k40AA13");
		Map <String, String> dp2= new HashMap <>(invalidData);
		
		Map<String, String> invalidData1 = Map.of("InvalidNIN", "123456789473","InvalidBVN", "2593952449", "InvalidDriverLicense",
				"Ft94k40AA13");
		Map <String, String> dp6= new HashMap <>(invalidData1);
	
		data2[0]= new Object[] { dp2 };
		data2[1]= new Object[] { dp6 };
		return data2;
	}
}
