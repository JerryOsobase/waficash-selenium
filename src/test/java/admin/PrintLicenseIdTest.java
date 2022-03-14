package admin;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
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

import pageObject.admin.PrintLicense;
import pageObject.admin.SideMenu;
import resources.base;

public class PrintLicenseIdTest extends base{
	SideMenu sm;
	SoftAssert soft;
	AdminLoginTest alt;
	PrintLicense pl;
	
	@BeforeClass
	public void initialize() throws IOException {
		driver= InitializeBrowser();
		driver.get(prop.getProperty("AdminOpUrl"));
		driver.manage().window().maximize();
	}
	
	@Test(priority=1, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void ValidatePrintLicenseIdPage(HashMap<String, String> data) {
	//Verify user is taken to the Print license ID page
		alt = new AdminLoginTest();
		alt.ValidateSuccessfulAdminLogin1(driver, data);
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pl = new PrintLicense(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getPrintLicense());
		 soft.assertTrue(pl.getSearchField().isDisplayed());
		 soft.assertTrue(pl.getPrintLicenseHeader().getText().contains("Print License ID"));
		 soft.assertAll();
	}
	
	@Test(priority=2)
	public void ValidatePageTabs() {
		//Verify user can view the following tabs; Pending, Previously printed
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pl = new PrintLicense(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getPrintLicense());
		List<String> originalPageTabs= pl.getPageTab().stream().map(a->a.getText()).collect(Collectors.toList());
		String[] expectedPageTabs= {"Pending", "Previously Printed"};
			List<String> pageTabsArray= Arrays.asList(expectedPageTabs);
			Assert.assertEquals(originalPageTabs, pageTabsArray);
	}
	
	@Test(priority=3)
	public void ValidatePendingIdListHeader() {
		//Verify user can view list of agents who have not printed their permit id card with the following headers; Status, Email Address, Name, Permit ID, Phone Number, Created At, Modified At        
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pl = new PrintLicense(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getPrintLicense());
		 if(pl.getStatus().size()>=1) {
		 pl.getPendingTab();
		List<String> originalIdListHeader= pl.getTableHeader().stream().map(a->a.getText()).collect(Collectors.toList());
		String[] expectedIdListHeader= {"","#", "Status", "Email Address", "Name", "Permit ID", "Phone Number", "Created At", "Modified At", ""};
			List<String> expectedHeaderArray= Arrays.asList(expectedIdListHeader);
			soft.assertEquals(originalIdListHeader, expectedHeaderArray);
		 }
		 else if(pl.getStatus().size()<1) {
			 soft.assertEquals(pl.getEmptyApplicationText().getText(), "You have no agents that need a permit card to be printed currently.\n"
			 		+ "Once an agent registers and is verified their details will show up here and you can print a permit card for them.");
		 }
		 soft.assertAll();
	}
	
	@Test(priority=4)
	public void ValidatePreviouslyPrintedIdListHeader() {
		//Verify user can view list of agents who have printed their permit id card with the following headers; Status, Email Address, Name, Permit ID, Phone Number, Printed At        
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pl = new PrintLicense(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getPrintLicense());
		 pl.getPreviouslyPrintedTab();
		List<String> originalIdListHeader= pl.getTableHeader().stream().map(a->a.getText()).collect(Collectors.toList());
		String[] expectedIdListHeader= {"","#", "Status", "Email Address", "Name", "Permit ID", "Phone Number", "Printed At", ""};
			List<String> expectedHeaderArray= Arrays.asList(expectedIdListHeader);
			Assert.assertEquals(originalIdListHeader, expectedHeaderArray);
	}
	
	@Test(priority=5)
	public void ValidatePendingAgentStatus() {
		//Verify user can only view verified agents for pending id
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pl = new PrintLicense(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getPrintLicense());
		 pl.getPendingTab();
		 if(pl.getStatus().size()>=1) {
			 long totalCount = pl.getStatus().stream().count();
			 long verifiedCount = pl.getStatus().stream().filter(v->v.getText().equalsIgnoreCase("Verified")).count();
			 soft.assertEquals(totalCount, verifiedCount);
		 }
		 else if(pl.getStatus().size()<1) {
			 soft.assertEquals(pl.getEmptyApplicationText().getText(), "You have no agents that need a permit card to be printed currently.\n"
			 		+ "Once an agent registers and is verified their details will show up here and you can print a permit card for them.");
		 }
		 
		 soft.assertAll();
	}
	
	@Test(priority=6)
	public void ValidatePrintedAgentStatus() {
		//Verify user can only view verified agents for previously printed id
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pl = new PrintLicense(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getPrintLicense());
		 pl.getPreviouslyPrintedTab();
		 if(pl.getStatus().size()>=1) {
			 long totalCount = pl.getStatus().stream().count();
			 long verifiedCount = pl.getStatus().stream().filter(v->v.getText().equalsIgnoreCase("Verified")).count();
			 soft.assertEquals(totalCount, verifiedCount);
		 }
		 
		 soft.assertAll();
	}
	
	@Test(priority=7)
	public void ValidatePrintIdButtonPending() {
		//Verify user view a "Print id" button beside each license card for pending ids
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pl = new PrintLicense(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getPrintLicense());
		 pl.getPendingTab();
		if(pl.getPrintIdButton().size()>=1) {
			long totalCount = pl.getStatus().stream().count();
			long filteredCount = pl.getPrintIdButton().stream().filter(v->v.getText().equalsIgnoreCase("Print ID")).count();
			soft.assertEquals(totalCount, filteredCount);
		}
		else if(pl.getStatus().size()<1) {
			 soft.assertEquals(pl.getEmptyApplicationText().getText(), "You have no agents that need a permit card to be printed currently.\n"
			 		+ "Once an agent registers and is verified their details will show up here and you can print a permit card for them.");
		 }
		soft.assertAll();
		 
	}
	
	@Test(priority=8)
	public void ValidatePrintIdButtonPrinted() {
		//Verify user view a "Print id" button beside each license card for previously printed
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pl = new PrintLicense(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getPrintLicense());
		 pl.getPreviouslyPrintedTab();
		if(pl.getPrintIdButton().size()>=1) {
			long totalCount = pl.getStatus().stream().count();
			long filteredCount = pl.getPrintIdButton().stream().filter(v->v.getText().equalsIgnoreCase("Print ID")).count();
			soft.assertEquals(totalCount, filteredCount);
		}
		soft.assertAll();
		 
	}
	
	@Test(priority=9)
	public void ValidatePrintIdButtonResponsePending() throws AWTException {
	//Verify user is unable to view agent card if it ID has been printed on the pending page
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pl = new PrintLicense(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getPrintLicense());
		 pl.getPendingTab();
		 if(pl.getStatus().size()>=1) {
			 String permitId = pl.getPermitId().get(0).getText();
			 pl.getPrintIdButton().get(0).click();
			 Robot robot=new Robot();
				robot.keyPress(KeyEvent.VK_ESCAPE);
				robot.keyRelease(KeyEvent.VK_ESCAPE);
				robot.delay(2000);
			soft.assertFalse(pl.getPermitId().stream().filter(v->v.getText().contains(permitId)).findAny().isPresent());
		 }
		 else if(pl.getStatus().size()<1) {
			 soft.assertEquals(pl.getEmptyApplicationText().getText(), "You have no agents that need a permit card to be printed currently.\n"
			 		+ "Once an agent registers and is verified their details will show up here and you can print a permit card for them.");
		 }
		 
		 soft.assertAll();
			
			}
	
	@Test(priority=10)
	public void ValidateBulkPrintButtonPending() {
		//Verify the Bulk print button is enabled only when user selects more than one agent
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pl = new PrintLicense(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getPrintLicense());
		 pl.getPendingTab();
		 if(pl.getStatus().size()==1) {
			 soft.assertFalse(pl.getPrintBulkButton().isEnabled());
			 pl.getCheckbox().stream().forEach(v->v.click());
			 soft.assertFalse(pl.getPrintBulkButton().isEnabled());
		 }
		 else if(pl.getStatus().size()>1) {
			 soft.assertFalse(pl.getPrintBulkButton().isEnabled());
			 pl.getCheckbox().get(0).click();
			 soft.assertFalse(pl.getPrintBulkButton().isEnabled());
			 pl.getCheckbox().get(0).click();
			 for(int q=0; q<2; q++) {
				 pl.getCheckbox().get(q).click();
			 }
			 soft.assertTrue(pl.getPrintBulkButton().isEnabled());
		 }
		 else if(pl.getStatus().size()<1) {
			 soft.assertEquals(pl.getEmptyApplicationText().getText(), "You have no agents that need a permit card to be printed currently.\n"
			 		+ "Once an agent registers and is verified their details will show up here and you can print a permit card for them.");
		 }
		 soft.assertAll();
	}
	
	@Test(priority=11)
	public void ValidateBulkPrintButtonPrinted() {
		//Verify the Bulk print button is enabled only when user selects more than one agent
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pl = new PrintLicense(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getPrintLicense());
		 pl.getPreviouslyPrintedTab();
		 soft.assertFalse(pl.getPrintBulkButton().isEnabled());
		 if(pl.getStatus().size()==1) {
			 pl.getCheckbox().stream().forEach(v->v.click());
			 soft.assertFalse(pl.getPrintBulkButton().isEnabled());
		 }
		 else if(pl.getStatus().size()>1) {
			 pl.getCheckbox().get(0).click();
			 soft.assertFalse(pl.getPrintBulkButton().isEnabled());
			 pl.getCheckbox().get(0).click();
			 for(int q=0; q<2; q++) {
				 pl.getCheckbox().get(q).click();
			 }
			 soft.assertTrue(pl.getPrintBulkButton().isEnabled());
		 }
		 soft.assertAll();
	}
	
	@Test(priority=12)
	public void ValidateSelectAllCheckboxPending() {
		//Check system response when user click on the Select all checkbox
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pl = new PrintLicense(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getPrintLicense());
		 pl.getPendingTab();
		 if(pl.getStatus().size()>1) {
		  pl.getSelectAllCheckbox().click();
		 soft.assertTrue(pl.getPrintBulkButton().isEnabled());
		 long totalCount = pl.getCheckbox().stream().count();
		long selectedCount =  pl.getCheckbox().stream().filter(v->v.isSelected()).count();
		soft.assertEquals(totalCount, selectedCount);
		 }
		 
		 else if(pl.getStatus().size()==1) {
		  pl.getSelectAllCheckbox().click();
			 soft.assertFalse(pl.getPrintBulkButton().isEnabled());
			 long totalCount = pl.getCheckbox().stream().count();
			long selectedCount =  pl.getCheckbox().stream().filter(v->v.isSelected()).count();
			soft.assertEquals(totalCount, selectedCount);
		 }
		 else if(pl.getStatus().size()<1) {
			 soft.assertEquals(pl.getEmptyApplicationText().getText(), "You have no agents that need a permit card to be printed currently.\n"
			 		+ "Once an agent registers and is verified their details will show up here and you can print a permit card for them.");
		 }
		 soft.assertAll();
	}
	
	@Test(priority=13)
	public void ValidateSelectAllCheckboxPrinted() {
		//Check system response when user click on the Select all checkbox
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pl = new PrintLicense(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getPrintLicense());
		 pl.getPreviouslyPrintedTab();
		 if(pl.getStatus().size()>1) {
		  pl.getSelectAllCheckbox().click();
		 soft.assertTrue(pl.getPrintBulkButton().isEnabled());
		 long totalCount = pl.getCheckbox().stream().count();
		long selectedCount =  pl.getCheckbox().stream().filter(v->v.isSelected()).count();
		soft.assertEquals(totalCount, selectedCount);
		 }
		 
		 else if(pl.getStatus().size()==1) {
		  pl.getSelectAllCheckbox().click();
			 soft.assertFalse(pl.getPrintBulkButton().isEnabled());
			 long totalCount = pl.getCheckbox().stream().count();
			long selectedCount =  pl.getCheckbox().stream().filter(v->v.isSelected()).count();
			soft.assertEquals(totalCount, selectedCount);
		 }
		 soft.assertAll();
	}
	
	@Test(priority=14)
	public void ValidateAllPendingIdPrint() throws AWTException {
		//Verify user can print all pending ids at once
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 pl = new PrintLicense(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getPrintLicense());
		 pl.getPendingTab();
		 if(pl.getStatus().size()>1) {
			 pl.getSelectAllCheckbox().click();
			 pl.getPrintBulkButton().click();
			 Robot robot=new Robot();
				robot.keyPress(KeyEvent.VK_ESCAPE);
				robot.keyRelease(KeyEvent.VK_ESCAPE);
				robot.delay(2000);
				soft.assertEquals(pl.getEmptyApplicationText().getText(), "You have no agents that need a permit card to be printed currently.\n"
				 		+ "Once an agent registers and is verified their details will show up here and you can print a permit card for them.");
		 }
		 else if(pl.getStatus().size()==1) {
			pl.getPrintIdButton().stream().forEach(v->v.click());
			 Robot robot=new Robot();
				robot.keyPress(KeyEvent.VK_ESCAPE);
				robot.keyRelease(KeyEvent.VK_ESCAPE);
				robot.delay(2000);
				soft.assertEquals(pl.getEmptyApplicationText().getText(), "You have no agents that need a permit card to be printed currently.\n"
				 		+ "Once an agent registers and is verified their details will show up here and you can print a permit card for them.");
		 }
		 
		 else if(pl.getStatus().size()<1) {
			 soft.assertEquals(pl.getEmptyApplicationText().getText(), "You have no agents that need a permit card to be printed currently.\n"
			 		+ "Once an agent registers and is verified their details will show up here and you can print a permit card for them.");
		 }
		 soft.assertAll();
	}
	
	@AfterClass
	public void terminate() {
		driver.close();
	}

}
