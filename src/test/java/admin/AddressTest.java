package admin;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.admin.Addresses;
import pageObject.admin.ApplicationReport;
import pageObject.admin.SideMenu;
import resources.base;

public class AddressTest extends base{
	Addresses a;
	SideMenu sm;
	SoftAssert soft;
	ApplicationReport ar;
	AdminLoginTest alt;

	@BeforeClass
	public void initialize() throws IOException {
		driver= InitializeBrowser();
		driver.get(prop.getProperty("AdminOpUrl"));
		driver.manage().window().maximize();
	}
	
	@Test(priority=1, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void ValidateAddressPage(HashMap<String, String> data) {
	//Verify user is taken to the Address page
		alt = new AdminLoginTest();
		alt.ValidateSuccessfulAdminLogin1(driver, data);
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 a = new Addresses(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getAddresses());
	 soft.assertTrue(a.getStatusField().isDisplayed());
	 soft.assertEquals(a.getAddressHeader().getText(), "Addresses");
	 soft.assertAll();
	}
	
	@Test(priority=2)
	public void ValidateAddressListHeader() {
		//Verify user can view the list of agent applications with the following headers; 
		//("#", "Status", "Verification ID", "Name", "LGA/LCDA","Requested by", "Created At)
		sm = new SideMenu(driver);
		a = new Addresses(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getAddresses());
			 List<String> originalAddressListHeader= a.getTableHeader().stream().map(a->a.getText()).collect(Collectors.toList());
			String[] expectedAddressListHeader= {"#", "Status", "Verification ID", "Name", "LGA/LCDA",
					 "Requested by", "Created At"};
				List<String> expectedHeaderArray= Arrays.asList(expectedAddressListHeader);
				Assert.assertEquals(originalAddressListHeader, expectedHeaderArray);
	}
	
	@AfterClass
	public void terminate() {
		driver.close();
	}
}
