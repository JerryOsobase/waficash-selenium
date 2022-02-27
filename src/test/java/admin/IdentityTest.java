package admin;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.admin.ApplicationReport;
import pageObject.admin.Identity;
import pageObject.admin.SideMenu;
import resources.base;

public class IdentityTest extends base{
	Identity id;
	AdminLoginTest alt;
	SideMenu sm;
	SoftAssert soft;
	ApplicationReport ar;

	@BeforeClass
	public void initialize() throws IOException {
		driver= InitializeBrowser();
		driver.get(prop.getProperty("AdminOpUrl"));
		driver.manage().window().maximize();
	}
	
	@Test(priority=1, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void ValidateIdentityPage(HashMap<String, String> data) {
	//Verify user is taken to the identity page
		alt = new AdminLoginTest();
		alt.ValidateSuccessfulAdminLogin1(driver, data);
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 id = new Identity(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getIdentity());
	 soft.assertTrue(id.getStatusField().isDisplayed());
	 soft.assertEquals(id.getIdentityHeader().getText(), "Identity");
	 soft.assertAll();
	}
	
	@Test(priority=2)
	public void ValidateIdentityListHeader() {
		//Verify user can view the list of agent applications with the following headers; 
		//(Status, Application ID, LSLB Permit Number, Name, LGA/LCDA, Created At, Modified At)
		sm = new SideMenu(driver);
		 id = new Identity(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getIdentity());
			 List<String> originalIdentityListHeader= id.getTableHeader().stream().map(a->a.getText()).collect(Collectors.toList());
			 String[] expectedIdentityListHeader= {"#", "Status", "Application ID", "LSLB Permit Number", "Name",
					 "LGA/LCDA", "Created At", "Modified At"};
				List<String> expectedHeaderArray= Arrays.asList(expectedIdentityListHeader);
				Assert.assertEquals(originalIdentityListHeader, expectedHeaderArray);
	}
	
	@Test(priority=3)
	public void ValidateStatusDropdownList() {
		//Check system response user click on the status field
		sm = new SideMenu(driver);
		 id = new Identity(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getIdentity());
		Select statusTypeDropdown = new Select(id.getStatusField());
		List<WebElement> allOptions= statusTypeDropdown.getOptions();
		List<String> idOptions= allOptions.stream().map(a->a.getText()).collect(Collectors.toList());
		String[] idTypeList= {"-- select status --","All","Verified","Not Verified", "Pending", "Incomplete", "Service Down"};
		List<String> idTypeListArray= Arrays.asList(idTypeList);
		Assert.assertEquals(idOptions, idTypeListArray);
	}
	
	@Test(priority=4)
	public void ValidateVerifiedStatusField() throws InterruptedException {
		//Check system response when user filter by verified
		sm = new SideMenu(driver);
		 id = new Identity(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getIdentity());
		 Thread.sleep(5000);
		 int verified = 0;
		 for(int q=0; q<id.getStatus().size(); q++) {
			 if(id.getStatus().get(q).getText().equalsIgnoreCase("verified")) {
				 verified++;
			 }
			 }
		 while(id.getActivePagination().size()==1) {
				Thread.sleep(3000);
				
				id.getNextPagination().click();
				Thread.sleep(3000);
				 for(int r=0; r<id.getStatus().size(); r++) {
					 if(id.getStatus().get(r).getText().equalsIgnoreCase("verified")) {
						 verified++;
					 }
				 }
				  if(id.getNextPagination().getText().contains("Next")) {
					 break;
				 }
		 }
		 Select statusTypeDropdown = new Select(id.getStatusField());
			statusTypeDropdown.selectByVisibleText("Verified");
			executor.executeScript("arguments[0].click();", id.getFilterButton());
			 Thread.sleep(5000);
			long count =  id.getStatus().stream().count();
				
				while(id.getActivePagination().size()==1) {
					
					
					id.getNextPagination().click();
					Thread.sleep(3000);
					count += id.getStatus().stream().count();
					
					  if(id.getNextPagination().getText().contains("Next")) {
						 break;
					 }
					  
				 }
				Assert.assertEquals(verified, count);
	}
	
	@Test(dependsOnMethods="ValidateVerifiedStatusField")
	public void ValidateClearVerifiedList() throws InterruptedException{
		//Verify user can clear filter for verified filtered list
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", id.getClearButton());
		 Thread.sleep(5000);
		String splitter[]= id.getTableFooterText().getText().split("of");
		 long count =  id.getStatus().stream().count();
			
		while(id.getActivePagination().size()==1) {
			
			
			id.getNextPagination().click();
			Thread.sleep(3000);
			 count += id.getStatus().stream().count();
			
			  if(id.getNextPagination().getText().contains("Next")) {
				 break;
			 }
			  
		 }
		Assert.assertEquals(splitter[1].trim(), count+ " entries");	
	}
	
	@Test(priority=5)
	public void ValidateNotVerifiedStatusField() throws InterruptedException {
		//Check system response when user filter by Not verified
		sm = new SideMenu(driver);
		 id = new Identity(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getIdentity());
		 Thread.sleep(5000);
		 int notVerified = 0;
		 for(int q=0; q<id.getStatus().size(); q++) {
			 if(id.getStatus().get(q).getText().equalsIgnoreCase("not verified")) {
				 notVerified++;
			 }
			 }
		 while(id.getActivePagination().size()==1) {
				Thread.sleep(3000);
				id.getNextPagination().click();
				Thread.sleep(3000);
				 for(int r=0; r<id.getStatus().size(); r++) {
					 if(id.getStatus().get(r).getText().equalsIgnoreCase("not verified")) {
						 notVerified++;
					 }
				 }
				  if(id.getNextPagination().getText().contains("Next")) {
					 break;
				 }
		 }
		 Select statusTypeDropdown = new Select(id.getStatusField());
			statusTypeDropdown.selectByVisibleText("Not Verified");
			executor.executeScript("arguments[0].click();", id.getFilterButton());
			 Thread.sleep(5000);
			long count =  id.getStatus().stream().count();
				while(id.getActivePagination().size()==1) {
					id.getNextPagination().click();
					Thread.sleep(3000);
					count += id.getStatus().stream().count();
					  if(id.getNextPagination().getText().contains("Next")) {
						 break;
					 }
					  }
				Assert.assertEquals(notVerified, count);
	}
	
	@Test(dependsOnMethods="ValidateNotVerifiedStatusField")
	public void ValidateClearNotVerifiedList() throws InterruptedException{
		//Verify user can clear filter for Not Verified filtered list
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", id.getClearButton());
		 Thread.sleep(5000);
		String splitter[]= id.getTableFooterText().getText().split("of");
		 long count =  id.getStatus().stream().count();
			
		while(id.getActivePagination().size()==1) {
			
			
			id.getNextPagination().click();
			Thread.sleep(3000);
			 count += id.getStatus().stream().count();
			
			  if(id.getNextPagination().getText().contains("Next")) {
				 break;
			 }
			  
		 }
		Assert.assertEquals(splitter[1].trim(), count+ " entries");	
	}
	
	@Test(priority=6)
	public void ValidatePendingStatusField() throws InterruptedException {
		//Check system response when user filter by pending
		sm = new SideMenu(driver);
		 id = new Identity(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getIdentity());
		 Thread.sleep(5000);
		 int pending = 0;
		 for(int q=0; q<id.getStatus().size(); q++) {
			 if(id.getStatus().get(q).getText().equalsIgnoreCase("pending")) {
				 pending++;
			 }
			 }
		 while(id.getActivePagination().size()==1) {
				Thread.sleep(3000);
				id.getNextPagination().click();
				Thread.sleep(3000);
				 for(int r=0; r<id.getStatus().size(); r++) {
					 if(id.getStatus().get(r).getText().equalsIgnoreCase("pending")) {
						 pending++;
					 }
				 }
				  if(id.getNextPagination().getText().contains("Next")) {
					 break;
				 }
		 }
		 Select statusTypeDropdown = new Select(id.getStatusField());
			statusTypeDropdown.selectByVisibleText("Pending");
			executor.executeScript("arguments[0].click();", id.getFilterButton());
			 Thread.sleep(5000);
			long count =  id.getStatus().stream().count();
				while(id.getActivePagination().size()==1) {
					id.getNextPagination().click();
					Thread.sleep(3000);
					count += id.getStatus().stream().count();
					
					  if(id.getNextPagination().getText().contains("Next")) {
						 break;
					 }
				 }
				Assert.assertEquals(pending, count);
	}
	
	@Test(dependsOnMethods="ValidatePendingStatusField")
	public void ValidateClearPendingList() throws InterruptedException{
		//Verify user can clear filter for Pending filtered list
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", id.getClearButton());
		 Thread.sleep(5000);
		String splitter[]= id.getTableFooterText().getText().split("of");
		 long count =  id.getStatus().stream().count();
			
		while(id.getActivePagination().size()==1) {
			
			
			id.getNextPagination().click();
			Thread.sleep(3000);
			 count += id.getStatus().stream().count();
			
			  if(id.getNextPagination().getText().contains("Next")) {
				 break;
			 }
			  
		 }
		Assert.assertEquals(splitter[1].trim(), count+ " entries");	
	}
	
	@Test(priority=7)
	public void ValidateIncompleteStatusField() throws InterruptedException {
		//Check system response when user filter by incomplete
		sm = new SideMenu(driver);
		 id = new Identity(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getIdentity());
		 Thread.sleep(5000);
		 int incomplete = 0;
		 for(int q=0; q<id.getStatus().size(); q++) {
			 if(id.getStatus().get(q).getText().equalsIgnoreCase("incomplete")) {
				 incomplete++;
			 }
			 }
		 while(id.getActivePagination().size()==1) {
				Thread.sleep(3000);
				id.getNextPagination().click();
				Thread.sleep(3000);
				 for(int r=0; r<id.getStatus().size(); r++) {
					 if(id.getStatus().get(r).getText().equalsIgnoreCase("incomplete")) {
						 incomplete++;
					 }
				 }
				  if(id.getNextPagination().getText().contains("Next")) {
					 break;
				 }
		 }
		 Select statusTypeDropdown = new Select(id.getStatusField());
			statusTypeDropdown.selectByVisibleText("Incomplete");
			executor.executeScript("arguments[0].click();", id.getFilterButton());
			 Thread.sleep(5000);
			long count =  id.getStatus().stream().count();
				while(id.getActivePagination().size()==1) {
					id.getNextPagination().click();
					Thread.sleep(3000);
					count += id.getStatus().stream().count();
					  if(id.getNextPagination().getText().contains("Next")) {
						 break;
					 }
				 }
				Assert.assertEquals(incomplete, count);
	}
	
	@Test(dependsOnMethods="ValidateIncompleteStatusField")
	public void ValidateClearIncompleteList() throws InterruptedException{
		//Verify user can clear filter for Incomplete filtered list
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", id.getClearButton());
		 Thread.sleep(5000);
		String splitter[]= id.getTableFooterText().getText().split("of");
		 long count =  id.getStatus().stream().count();
			
		while(id.getActivePagination().size()==1) {
			
			
			id.getNextPagination().click();
			Thread.sleep(3000);
			 count += id.getStatus().stream().count();
			
			  if(id.getNextPagination().getText().contains("Next")) {
				 break;
			 }
			  
		 }
		Assert.assertEquals(splitter[1].trim(), count+ " entries");	
	}
	
	@Test(priority=8)
	public void ValidateServiceDownStatusField() throws InterruptedException {
		//Check system response when user filter by Service Down
		sm = new SideMenu(driver);
		 id = new Identity(driver);
		 ar = new ApplicationReport(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getIdentity());
		 Select statusTypeDropdown = new Select(id.getStatusField());
			statusTypeDropdown.selectByVisibleText(" Service Down ");
			executor.executeScript("arguments[0].click();", id.getFilterButton());
			 Thread.sleep(5000);
			long count =  id.getStatus().stream().count();
				while(id.getActivePagination().size()==1) {
					id.getNextPagination().click();
					Thread.sleep(3000);
					count += id.getStatus().stream().count();
					
					  if(id.getNextPagination().getText().contains("Next")) {
						 break;
					 }
				 }
				System.out.println(count);
				 int serviceDown = 0;
					for(int q=0; q<id.getStatus().size(); q++) {
						id.getStatus().get(q).click();
						ar.getViewAnywayButton().click();
						if(ar.getVerificationfailedMessage().getText().contains("ID Service down")) {
							serviceDown++;
							driver.navigate().back();
						}	
					}
					System.out.println(serviceDown);
				Assert.assertEquals(count, serviceDown);
	}
	
	@Test(dependsOnMethods="ValidateServiceDownStatusField")
	public void ValidateClearServiceDownList() throws InterruptedException{
		//Verify user can clear filter for Service Down filtered list
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", id.getClearButton());
		 Thread.sleep(5000);
		String splitter[]= id.getTableFooterText().getText().split("of");
		 long count =  id.getStatus().stream().count();
			
		while(id.getActivePagination().size()==1) {
			
			
			id.getNextPagination().click();
			Thread.sleep(3000);
			 count += id.getStatus().stream().count();
			
			  if(id.getNextPagination().getText().contains("Next")) {
				 break;
			 }
			  
		 }
		Assert.assertEquals(splitter[1].trim(), count+ " entries");	
	}
	
	@Test(priority=9, dataProvider="getData", dataProviderClass=AdminLoginTest.class)
	public void ValidateAllLGAFilter(HashMap<String, String> data) throws InterruptedException {
		//Verify user can filter by LCA/LCDA & All status
		sm = new SideMenu(driver);
		 id = new Identity(driver);
		 ar = new ApplicationReport(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getIdentity());
		 Select statusTypeDropdown = new Select(id.getStatusField());
			statusTypeDropdown.selectByVisibleText("All");
			 Select lgaTypeDropdown = new Select(id.getLgaField());
			 lgaTypeDropdown.selectByVisibleText(data.get("lga"));
			executor.executeScript("arguments[0].click();", id.getFilterButton());
			 Thread.sleep(5000);
			 if(id.getStatus().size()>=1) {
				 String splitter[]= id.getTableFooterText().getText().split("of");
				long count = id.getLga().stream().filter(v->v.getText().equalsIgnoreCase(data.get("lga"))).count();
				 while(id.getActivePagination().size()==1) {
						id.getNextPagination().click();
						Thread.sleep(3000);
						count += id.getStatus().stream().count();
						
						  if(id.getNextPagination().getText().contains("Next")) {
							 break;
						 }
					 }
				 Assert.assertEquals(splitter[1].trim(), count+ " entries");
			 }
			 else {
				 Assert.assertEquals(id.getEmptyApplicationText().getText(), "There are no applications to display");
			 } 
	}
	
	@Test(priority=10, dataProvider="getData", dataProviderClass=AdminLoginTest.class)
	public void ValidateVerifiedLGAFilter(HashMap<String, String> data) throws InterruptedException {
		//Verify user can filter by LCA/LCDA & Verified status
		sm = new SideMenu(driver);
		 id = new Identity(driver);
		 ar = new ApplicationReport(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getIdentity());
		 Select statusTypeDropdown = new Select(id.getStatusField());
			statusTypeDropdown.selectByVisibleText("Verified");
			 Select lgaTypeDropdown = new Select(id.getLgaField());
			 lgaTypeDropdown.selectByVisibleText(data.get("lga"));
			executor.executeScript("arguments[0].click();", id.getFilterButton());
			 Thread.sleep(5000);
			 if(id.getStatus().size()>=1) {
				 String splitter[]= id.getTableFooterText().getText().split("of");
				long count = id.getLga().stream().filter(v->v.getText().equalsIgnoreCase(data.get("lga"))).count();
				 while(id.getActivePagination().size()==1) {
						id.getNextPagination().click();
						Thread.sleep(3000);
						count += id.getStatus().stream().count();
						
						  if(id.getNextPagination().getText().contains("Next")) {
							 break;
						 }
					 }
				 Assert.assertEquals(splitter[1].trim(), count+ " entries");
			 }
			 else {
				 Assert.assertEquals(id.getEmptyApplicationText().getText(), "There are no applications to display");
			 } 
	}
	
	@Test(priority=11, dataProvider="getData", dataProviderClass=AdminLoginTest.class)
	public void ValidateNotVerifiedLGAFilter(HashMap<String, String> data) throws InterruptedException {
		//Verify user can filter by LCA/LCDA & Not Verified status
		sm = new SideMenu(driver);
		 id = new Identity(driver);
		 ar = new ApplicationReport(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getIdentity());
		 Select statusTypeDropdown = new Select(id.getStatusField());
			statusTypeDropdown.selectByVisibleText("Not Verified");
			 Select lgaTypeDropdown = new Select(id.getLgaField());
			 lgaTypeDropdown.selectByVisibleText(data.get("lga"));
			executor.executeScript("arguments[0].click();", id.getFilterButton());
			 Thread.sleep(5000);
			 if(id.getStatus().size()>=1) {
				 String splitter[]= id.getTableFooterText().getText().split("of");
				long count = id.getLga().stream().filter(v->v.getText().equalsIgnoreCase(data.get("lga"))).count();
				 while(id.getActivePagination().size()==1) {
						id.getNextPagination().click();
						Thread.sleep(3000);
						count += id.getStatus().stream().count();
						
						  if(id.getNextPagination().getText().contains("Next")) {
							 break;
						 }
					 }
				 Assert.assertEquals(splitter[1].trim(), count+ " entries");
			 }
			 else {
				 Assert.assertEquals(id.getEmptyApplicationText().getText(), "There are no applications to display");
			 } 
	}
	
	@Test(priority=12, dataProvider="getData", dataProviderClass=AdminLoginTest.class)
	public void ValidatePendingLGAFilter(HashMap<String, String> data) throws InterruptedException {
		//Verify user can filter by LCA/LCDA & pending status
		sm = new SideMenu(driver);
		 id = new Identity(driver);
		 ar = new ApplicationReport(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getIdentity());
		 Select statusTypeDropdown = new Select(id.getStatusField());
			statusTypeDropdown.selectByVisibleText("Pending");
			 Select lgaTypeDropdown = new Select(id.getLgaField());
			 lgaTypeDropdown.selectByVisibleText(data.get("lga"));
			executor.executeScript("arguments[0].click();", id.getFilterButton());
			 Thread.sleep(5000);
			 if(id.getStatus().size()>=1) {
				 String splitter[]= id.getTableFooterText().getText().split("of");
				long count = id.getLga().stream().filter(v->v.getText().equalsIgnoreCase(data.get("lga"))).count();
				 while(id.getActivePagination().size()==1) {
						id.getNextPagination().click();
						Thread.sleep(3000);
						count += id.getStatus().stream().count();
						
						  if(id.getNextPagination().getText().contains("Next")) {
							 break;
						 }
					 }
				 Assert.assertEquals(splitter[1].trim(), count+ " entries");
			 }
			 else {
				 Assert.assertEquals(id.getEmptyApplicationText().getText(), "There are no applications to display");
			 } 
	}
	
	@Test(priority=13, dataProvider="getData", dataProviderClass=AdminLoginTest.class)
	public void ValidateIncompleteLGAFilter(HashMap<String, String> data) throws InterruptedException {
		//Verify user can filter by LCA/LCDA & incomplete status
		sm = new SideMenu(driver);
		 id = new Identity(driver);
		 ar = new ApplicationReport(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getIdentity());
		 Select statusTypeDropdown = new Select(id.getStatusField());
			statusTypeDropdown.selectByVisibleText("Incomplete");
			 Select lgaTypeDropdown = new Select(id.getLgaField());
			 lgaTypeDropdown.selectByVisibleText(data.get("lga"));
			executor.executeScript("arguments[0].click();", id.getFilterButton());
			 Thread.sleep(5000);
			 if(id.getStatus().size()>=1) {
				 String splitter[]= id.getTableFooterText().getText().split("of");
				long count = id.getLga().stream().filter(v->v.getText().equalsIgnoreCase(data.get("lga"))).count();
				 while(id.getActivePagination().size()==1) {
						id.getNextPagination().click();
						Thread.sleep(3000);
						count += id.getStatus().stream().count();
						
						  if(id.getNextPagination().getText().contains("Next")) {
							 break;
						 }
					 }
				 Assert.assertEquals(splitter[1].trim(), count+ " entries");
			 }
			 else {
				 Assert.assertEquals(id.getEmptyApplicationText().getText(), "There are no applications to display");
			 } 
	}
	
	@Test(priority=14, dataProvider="getData", dataProviderClass=AdminLoginTest.class)
	public void ValidateServiceDownLGAFilter(HashMap<String, String> data) throws InterruptedException {
		//Verify user can filter by LCA/LCDA & Service down status
		sm = new SideMenu(driver);
		 id = new Identity(driver);
		 ar = new ApplicationReport(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getIdentity());
		 Select statusTypeDropdown = new Select(id.getStatusField());
			statusTypeDropdown.selectByVisibleText(" Service Down ");
			 Select lgaTypeDropdown = new Select(id.getLgaField());
			 lgaTypeDropdown.selectByVisibleText(data.get("lga"));
			executor.executeScript("arguments[0].click();", id.getFilterButton());
			 Thread.sleep(5000);
			 if(id.getStatus().size()>=1) {
				long count = id.getLga().stream().filter(v->v.getText().equalsIgnoreCase(data.get("lga"))).count();
				 while(id.getActivePagination().size()==1) {
						id.getNextPagination().click();
						Thread.sleep(3000);
						count += id.getStatus().stream().count();
						
						  if(id.getNextPagination().getText().contains("Next")) {
							 break;
						 }
					 }
				 int serviceDown = 0;
					for(int q=0; q<id.getStatus().size(); q++) {
						id.getStatus().get(q).click();
						ar.getViewAnywayButton().click();
						if(ar.getVerificationfailedMessage().getText().contains("ID Service down")) {
							serviceDown++;
							driver.navigate().back();
						}	
					}
				 Assert.assertEquals(serviceDown, count);
			 }
			 else {
				 Assert.assertEquals(id.getEmptyApplicationText().getText(), "There are no applications to display");
			 } 
	}
	
	@Test(priority=15)
	public void ValidateCSVButtonAllStatus() throws InterruptedException {
		//Verify user can export CSV for all identities
		sm = new SideMenu(driver);
		 id = new Identity(driver);
		 ar = new ApplicationReport(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getIdentity());
		 Select statusTypeDropdown = new Select(id.getStatusField());
			statusTypeDropdown.selectByVisibleText("All");
			id.getFilterButton().click();
			Thread.sleep(3000);
			id.getExportCsvButton().click();
			Thread.sleep(3000);
			Assert.assertEquals(id.getPromptMessage().getText(), "Success\n"
					+ "Report export request sent successfully!");
		
	}
	
	@Test(priority=16)
	public void ValidateCSVButtonVerifiedStatus() throws InterruptedException {
		//Verify user can export CSV for verified identities
		sm = new SideMenu(driver);
		 id = new Identity(driver);
		 ar = new ApplicationReport(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getIdentity());
		 Select statusTypeDropdown = new Select(id.getStatusField());
			statusTypeDropdown.selectByVisibleText("Verified");
			id.getFilterButton().click();
			Thread.sleep(3000);
			id.getExportCsvButton().click();
			Thread.sleep(3000);
			Assert.assertEquals(id.getPromptMessage().getText(), "Success\n"
					+ "Report export request sent successfully!");
		
	}
	
	@Test(priority=17)
	public void ValidateCSVButtonNotVerifiedStatus() throws InterruptedException {
		//Verify user can export CSV for Not verified identities
		sm = new SideMenu(driver);
		 id = new Identity(driver);
		 ar = new ApplicationReport(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getIdentity());
		 Select statusTypeDropdown = new Select(id.getStatusField());
			statusTypeDropdown.selectByVisibleText("Not Verified");
			id.getFilterButton().click();
			Thread.sleep(3000);
			id.getExportCsvButton().click();
			Thread.sleep(3000);
			Assert.assertEquals(id.getPromptMessage().getText(), "Success\n"
					+ "Report export request sent successfully!");
		
	}
	
	@Test(priority=18)
	public void ValidateCSVButtonPendingStatus() throws InterruptedException {
		//Verify user can export CSV for pending identities
		sm = new SideMenu(driver);
		 id = new Identity(driver);
		 ar = new ApplicationReport(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getIdentity());
		 Select statusTypeDropdown = new Select(id.getStatusField());
			statusTypeDropdown.selectByVisibleText("Pending");
			id.getFilterButton().click();
			Thread.sleep(3000);
			id.getExportCsvButton().click();
			Thread.sleep(3000);
			Assert.assertEquals(id.getPromptMessage().getText(), "Success\n"
					+ "Report export request sent successfully!");
		
	}
	
	@Test(priority=19)
	public void ValidateCSVButtonIncompleteStatus() throws InterruptedException {
		//Verify user can export CSV for Incomplete identities
		sm = new SideMenu(driver);
		 id = new Identity(driver);
		 ar = new ApplicationReport(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getIdentity());
		 Select statusTypeDropdown = new Select(id.getStatusField());
			statusTypeDropdown.selectByVisibleText("Incomplete");
			id.getFilterButton().click();
			Thread.sleep(3000);
			id.getExportCsvButton().click();
			Thread.sleep(3000);
			Assert.assertEquals(id.getPromptMessage().getText(), "Success\n"
					+ "Report export request sent successfully!");
		
	}
	
	@Test(priority=20)
	public void ValidateCSVButtonServiceDownStatus() throws InterruptedException {
		//Verify user can export CSV for Service down identities
		sm = new SideMenu(driver);
		 id = new Identity(driver);
		 ar = new ApplicationReport(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getIdentity());
		 Select statusTypeDropdown = new Select(id.getStatusField());
			statusTypeDropdown.selectByVisibleText(" Service Down");
			id.getFilterButton().click();
			Thread.sleep(3000);
			id.getExportCsvButton().click();
			Thread.sleep(3000);
			Assert.assertEquals(id.getPromptMessage().getText(), "Success\n"
					+ "Report export request sent successfully!");
		
	}
	
	@Test(priority=21)
	public void ValidateIdentityCardResponse() {
		//Check system response when user click on agent application id
		sm = new SideMenu(driver);
		 id = new Identity(driver);
		 ar = new ApplicationReport(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getIdentity());
		soft =new SoftAssert();
		for(int q=0;q<id.getStatus().size(); q++) {
			id.getStatus().get(q).click();
			if(ar.getViewAnywayButtons().size()>=1) {
				ar.getViewAnywayButton().click();
			}
			soft.assertTrue(ar.getUploadPhotoButton().isDisplayed());
			soft.assertEquals(ar.getPageHeader().getText(), "Report");
			driver.navigate().back();
		}
		soft.assertAll();
	}
	
	@AfterClass
	public void terminate() {
		driver.close();
	}
}
