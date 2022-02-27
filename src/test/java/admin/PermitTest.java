package admin;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.admin.Permit;
import pageObject.admin.SideMenu;
import resources.base;

public class PermitTest extends base{
	Permit p;
	SideMenu sm;
	SoftAssert soft;
	AdminLoginTest alt;
	CouponTest ct;

	@BeforeClass
	public void initialize() throws IOException {
		driver= InitializeBrowser();
		driver.get(prop.getProperty("AdminOpUrl"));
		driver.manage().window().maximize();
	}
	
	@Test(priority=1, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void ValidatePermitPage(HashMap<String, String> data) {
	//Verify user is taken to the Permit page
		alt = new AdminLoginTest();
		alt.ValidateSuccessfulAdminLogin1(driver, data);
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 p = new Permit(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getPermit());
	 soft.assertTrue(p.getAddPermitButton().isDisplayed());
	 soft.assertAll();
	}
	
	@Test(priority=2)
	public void ValidatePermitListHeader() {
	//Verify user can the permit list with the following header; (Status, Name)
		sm = new SideMenu(driver);
	    p = new Permit(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getPermit());
			 List<String> originalPermitListHeader= p.getTableHeader().stream().map(a->a.getText()).collect(Collectors.toList());
			String[] expectedPermitListHeader= {"#", "Status", "Name", ""};
				List<String> expectedHeaderArray= Arrays.asList(expectedPermitListHeader);
				Assert.assertEquals(originalPermitListHeader, expectedHeaderArray);
	}
	
	@Test(priority=3)
 	public void ValidatePermitArrangement() {
		//Verify the permit list is arranged alphabetically
		sm = new SideMenu(driver);
	    p = new Permit(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", sm.getPermit());
			List<String> originalList= p.getName().stream().map(a->a.getText()).collect(Collectors.toList());
			List<String> sortedList= p.getName().stream().map(a->a.getText()).sorted(String::compareToIgnoreCase).collect(Collectors.toList());
			Assert.assertEquals(originalList, sortedList);
			 
	}
	
	@Test(priority=4)
	public void ValidateStatusDropodownList() {
		//Verify user can view the following filter option when user click on the status field; (All, Active, Disabled)
		sm = new SideMenu(driver);
	    p = new Permit(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", sm.getPermit());
			Select statusDropdown = new Select(p.getStatusField());
			List<WebElement> allOptions= statusDropdown.getOptions();
			List<String> statusOptions= allOptions.stream().map(a->a.getText()).collect(Collectors.toList());
			String[] stausTypeList= {"-- select status --", "All", "Active", "Disabled"};
			List<String> statusTypeListArray= Arrays.asList(stausTypeList);
			Assert.assertEquals(statusOptions, statusTypeListArray);
	}
	
	@Test(priority=5)
	public void ValidateAllStatusFilter() throws InterruptedException {
		//Check system response when user filter using All status
		sm = new SideMenu(driver);
	    p = new Permit(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", sm.getPermit());
			long count = p.getStatus().stream().count();
			 while(p.getActivePagination().size()==1) {
					p.getNextPagination().click();
					Thread.sleep(3000);
					count += p.getStatus().stream().count();
					
					  if(p.getNextPagination().getText().contains("Next")) {
						 break;
					 }
				 }
			 Select statusDropdown = new Select(p.getStatusField());
			 statusDropdown.selectByVisibleText("All");
			 executor.executeScript("arguments[0].click();",  p.getFilterButton());
			 Thread.sleep(3000);
			 long allCount = p.getStatus().stream().count();
			 while(p.getActivePagination().size()==1) {
					p.getNextPagination().click();
					Thread.sleep(3000);
					allCount += p.getStatus().stream().count();
					
					  if(p.getNextPagination().getText().contains("Next")) {
						 break;
					 }
				 }
			 Assert.assertEquals(count, allCount);
	}
	
	@Test(priority=6)
	public void ValidateActiveStatusFilter() throws InterruptedException {
		//Check system response when user filter using active status
		sm = new SideMenu(driver);
	    p = new Permit(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", sm.getPermit());
			soft = new SoftAssert();
			Thread.sleep(3000);
			int active = 0;
			 for(int q=0; q<p.getStatus().size(); q++) {
				 if(p.getStatus().get(q).getText().equalsIgnoreCase("Active")) {
					 active++;
				 }
				 }
			 while(p.getActivePagination().size()==1) {
					Thread.sleep(3000);
					p.getNextPagination().click();
					Thread.sleep(3000);
					 for(int r=0; r<p.getStatus().size(); r++) {
						 if(p.getStatus().get(r).getText().equalsIgnoreCase("Active")) {
							 active++;
						 }
					 }
					  if(p.getNextPagination().getText().contains("Next")) {
						 break;
					 }
	}
			 Select statusDropdown = new Select(p.getStatusField());
			 statusDropdown.selectByVisibleText("Active");
			 executor.executeScript("arguments[0].click();",  p.getFilterButton());
			 Thread.sleep(3000);
			 if(p.getStatus().size()>=1) {
			 long count =  p.getStatus().stream().count();
				while(p.getActivePagination().size()==1) {
					p.getNextPagination().click();
					Thread.sleep(3000);
					count += p.getStatus().stream().count();
					  if(p.getNextPagination().getText().contains("Next")) {
						 break;
					 }
				 }
				Assert.assertEquals(active, count);
			 }
			 else {
				 soft.assertTrue(active==0);
				 soft.assertEquals(p.getEmptyPermitText().getText(), "There are no permits to display"); 
			 }
soft.assertAll();
}
	
	@Test(priority=7)
	public void ValidateDisabledStatusFilter() throws InterruptedException {
		//Check system response when user filter using disabled status
		sm = new SideMenu(driver);
	    p = new Permit(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", sm.getPermit());
			soft = new SoftAssert();
			Thread.sleep(3000);
			int disabled = 0;
			 for(int q=0; q<p.getStatus().size(); q++) {
				 if(p.getStatus().get(q).getText().equalsIgnoreCase("Disabled")) {
					 disabled++;
				 }
				 }
			 while(p.getActivePagination().size()==1) {
					Thread.sleep(3000);
					p.getNextPagination().click();
					Thread.sleep(3000);
					 for(int r=0; r<p.getStatus().size(); r++) {
						 if(p.getStatus().get(r).getText().equalsIgnoreCase("Disabled")) {
							 disabled++;
						 }
					 }
					  if(p.getNextPagination().getText().contains("Next")) {
						 break;
					 }
	}
			 Select statusDropdown = new Select(p.getStatusField());
			 statusDropdown.selectByVisibleText("Disabled");
			 executor.executeScript("arguments[0].click();",  p.getFilterButton());
			 Thread.sleep(3000);
			 if(p.getStatus().size()>=1) {
			 long count =  p.getStatus().stream().count();
				while(p.getActivePagination().size()==1) {
					p.getNextPagination().click();
					Thread.sleep(3000);
					count += p.getStatus().stream().count();
					  if(p.getNextPagination().getText().contains("Next")) {
						 break;
					 }
				 }
				Assert.assertEquals(disabled, count);
			 }
			 else {
				 soft.assertTrue(disabled==0);
				 soft.assertEquals(p.getEmptyPermitText().getText(), "There are no permits to display"); 
			 }
soft.assertAll();
}
	
	@Test(priority=8, dataProvider="getData", dataProviderClass=AdminLoginTest.class)
	public void ValidateSearchBar(HashMap<String, String> data) throws InterruptedException {
		//Verify user can for search permit by name
		sm = new SideMenu(driver);
	    p = new Permit(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", sm.getPermit());
			soft = new SoftAssert();
			Thread.sleep(3000);
			Long count= p.getName().stream().filter(v->v.getText().toLowerCase().contains(data.get("permitName"))).count();
			 while(p.getActivePagination().size()==1) {
					Thread.sleep(3000);
					p.getNextPagination().click();
					Thread.sleep(3000);
					count += p.getName().stream().filter(f->f.getText().toLowerCase().contains(data.get("permitName"))).count();
					  if(p.getNextPagination().getText().contains("Next")) {
						 break;
					 }
	}
			 p.getSearchBar().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("permitName"));
			 Thread.sleep(5000);
			 Long searchCount= p.getName().stream().count();
			 while(p.getActivePagination().size()==1) {
					Thread.sleep(3000);
					p.getNextPagination().click();
					Thread.sleep(3000);
					searchCount += p.getName().stream().count();
					  if(p.getNextPagination().getText().contains("Next")) {
						 break;
					 }
	}
			 p.getClearButton().click();
			 Thread.sleep(5000);
			 Assert.assertEquals(count, searchCount);
	}
	
	@Test(priority=9)
	public void ValidateAddPermitButton() throws InterruptedException {
		//Verify user can add premit
		sm = new SideMenu(driver);
	    p = new Permit(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", sm.getPermit());
			soft = new SoftAssert();
			soft.assertTrue(p.getAddPermitButton().isDisplayed());
			p.getAddPermitButton().click();
			ct = new CouponTest();
			String permitName = ct.setCouponCode();
			p.getAddPermitField().sendKeys(Keys.chord(Keys.COMMAND, "a"), permitName);
			p.getAddPermitConfirmationButton().click();
			soft.assertEquals(p.getAddPermitConfirmationMessage().getText(), "Permit created sucessfully");
			p.getPopUpCancelButton().click();
			 Boolean staleElement = true;
			 while(staleElement){

				  try{
			long count = p.getName().stream().filter(q->q.getText().equalsIgnoreCase(permitName)).count();
			 while(p.getActivePagination().size()==1) {
					Thread.sleep(3000);
					p.getNextPagination().click();
					Thread.sleep(3000);
					count += p.getName().stream().filter(q->q.getText().equalsIgnoreCase(permitName)).count();
					  if(p.getNextPagination().getText().contains("Next")) {
						 break;
					 }
	}
			 soft.assertTrue(count==1);
			  staleElement = false;


				  } catch(StaleElementReferenceException e){

				    staleElement = true;

				  }

				}
			 
			
			 soft.assertAll();
			
	}
	
	@Test(priority=10)
	public void ValidateSetPermitButton() throws InterruptedException {
		//Verify user can change the permit fee
		sm = new SideMenu(driver);
	    p = new Permit(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", sm.getPermit());
			soft = new SoftAssert();
			soft.assertTrue(p.getSetPermitButton().isDisplayed());
			executor.executeScript("arguments[0].click();", p.getSetPermitButton());
			soft.assertEquals(p.getCurrentPermitPrice().getText(), "Current Price - ₦12,500");
			p.getPermitFeeField().click();
			p.getPermitFeeField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "20000");
			executor.executeScript("arguments[0].click();", p.getSetPermitConfirmationButton());
			Thread.sleep(5000);
			executor.executeScript("arguments[0].click();", p.getSetPermitButton());
			soft.assertEquals(p.getCurrentPermitPrice().getText(), "Current Price - ₦20,000");
			Thread.sleep(3000);
			p.getPermitFeeField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "12500");
			executor.executeScript("arguments[0].click();", p.getSetPermitConfirmationButton());
        	soft.assertAll();
	}
	
	@Test(priority=11)
	public void ValidateActiveStatusButton() throws InterruptedException {
		//Verify if a permit is Active, user can view a disable button beside it
		sm = new SideMenu(driver);
	    p = new Permit(driver);
	    soft = new SoftAssert();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", sm.getPermit());
			for(int q=0; q<p.getStatus().size(); q++) {
				if(p.getStatus().get(q).getText().contains("Active")) {
					soft.assertTrue(p.getStatusButton().get(q).getText().equalsIgnoreCase("Disable"));
				}
			}
			 while(p.getActivePagination().size()==1) {
					Thread.sleep(3000);
			   p.getNextPagination().click();
					Thread.sleep(3000);
					for(int r=0; r<p.getStatus().size(); r++) {
						if(p.getStatus().get(r).getText().contains("Disabled")) {
							soft.assertTrue(p.getStatusButton().get(r).getText().equalsIgnoreCase("Disable"));
						}
					}
					  if(p.getNextPagination().getText().contains("Next")) {
						 break;
					 }
			 }
			soft.assertAll();
	}
	
	@Test(priority=12)
	public void ValidateDisabledStatusButton() throws InterruptedException {
		//Verify if a permit is Disabled, user can view a Activate button beside it
		sm = new SideMenu(driver);
	    p = new Permit(driver);
	    soft = new SoftAssert();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", sm.getPermit());
			for(int q=0; q<p.getStatus().size(); q++) {
				if(p.getStatus().get(q).getText().contains("Disabled")) {
					soft.assertTrue(p.getStatusButton().get(q).getText().equalsIgnoreCase("Activate"));
				}
			}
			 while(p.getActivePagination().size()==1) {
					Thread.sleep(3000);
					p.getNextPagination().click();
					Thread.sleep(3000);
					for(int r=0; r<p.getStatus().size(); r++) {
						if(p.getStatus().get(r).getText().contains("Disabled")) {
							soft.assertTrue(p.getStatusButton().get(r).getText().equalsIgnoreCase("Activate"));
						}
					}
					  if(p.getNextPagination().getText().contains("Next")) {
						 break;
					 }
	}
			soft.assertAll();
	}
	
	@Test(priority=13)
	public void ValidateStatusButtonResponse() throws InterruptedException {
		//Verify user can disable & enable permit
		sm = new SideMenu(driver);
	    p = new Permit(driver);
	    soft = new SoftAssert();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", sm.getPermit());
			Thread.sleep(5000);
			for(int q=0; q<p.getName().size(); q++) {
				if(p.getName().get(q).getText().equalsIgnoreCase("Pools Betting")) {
					soft.assertEquals(p.getStatusButton().get(q).getText(), "Disable");
					executor.executeScript("arguments[0].click();", p.getStatusButton().get(q));
					Thread.sleep(3000);
					soft.assertEquals(p.getPopUpConfirmButton().getText(), "Yes, please disable permit.");
					p.getPopUpConfirmButton().click(); 
					break;
				}
			}
			
			Thread.sleep(5000);
			for(int r=0; r<p.getName().size(); r++) {
				if(p.getName().get(r).getText().equalsIgnoreCase("Pools Betting")) {
					soft.assertEquals(p.getStatus().get(r).getText(), "Disabled");
					soft.assertEquals(p.getStatusButton().get(r).getText(), "Activate");
					executor.executeScript("arguments[0].click();", p.getStatusButton().get(r));
					Thread.sleep(3000);
					soft.assertEquals(p.getPopUpConfirmButton().getText(), "Yes, please activate permit.");
					p.getPopUpConfirmButton().click(); 
					break;
				}
			}
			Thread.sleep(3000);
			soft.assertAll();
	}
	
	@Test(priority=14)
	public void ValidateDuplicatePermitResponse() {
		//Check system response when user tries to add a permit with an existing name
		sm = new SideMenu(driver);
	    p = new Permit(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", sm.getPermit());
			executor.executeScript("arguments[0].click();", p.getAddPermitButton());
			p.getAddPermitField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Pools Betting");
			p.getAddPermitConfirmationButton().click();
			Assert.assertEquals(p.getPromptMessage().getText(), "Error\n"
					+ "A permit type with that name already exists");
			
	}
	
	@AfterClass
	public void terminate() {
		driver.close();
	}
}
