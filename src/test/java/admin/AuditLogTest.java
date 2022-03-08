package admin;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.admin.AuditLog;
import pageObject.admin.SideMenu;
import resources.base;

public class AuditLogTest extends base {

	SideMenu sm;
	SoftAssert soft;
	AdminLoginTest alt;
	AuditLog aul;
	
	@BeforeClass
	public void initialize() throws IOException {
		driver= InitializeBrowser();
		driver.get(prop.getProperty("AdminOpUrl"));
		driver.manage().window().maximize();
	}
	
	@Test(priority=1, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void ValidateAuditLoginPage(HashMap<String, String> data) {
	//Verify user is taken to the Audit log page
		alt = new AdminLoginTest();
		alt.ValidateSuccessfulAdminLogin1(driver, data);
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 aul = new AuditLog(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getAuditLog());
		 soft.assertTrue(aul.getFilterButton().isDisplayed());
		 soft.assertTrue(aul.getAuditLogHeader().getText().contains("Audit Log"));
		 soft.assertAll();
	}
	
	@Test(priority=2)
	public void ValidateLogListHeader() {
		//Verify user can view the list of activity logs with the following header; (Activity, Time)
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 aul = new AuditLog(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getAuditLog());
		 List<String> originalAuditLogListHeader= aul.getTableHeader().stream().map(a->a.getText()).collect(Collectors.toList());
			String[] expectedAuditLogListHeader= {"#", "Activity", "Time"};
				List<String> expectedHeaderArray= Arrays.asList(expectedAuditLogListHeader);
				Assert.assertEquals(originalAuditLogListHeader, expectedHeaderArray);
	}
	
	@Test(priority=3, dataProvider="getData")
	public void ValidateUserSearchField(HashMap<String, String> data) throws InterruptedException {
		//Verify user can filter by searching by user
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 aul = new AuditLog(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getAuditLog());
		 aul.getSelectUserField().sendKeys(data.get("usersearch"));
		 Thread.sleep(3000);
			for(WebElement userList : aul.getUserFieldDropdown()) {
				if(userList.getText().contains(data.get("usersearch"))) {
					userList.click();
					break;
				}
			}
			Select eventDropDown = new Select(aul.getEventField());
			eventDropDown.selectByVisibleText("All");
			aul.getFilterButton().click();
			Thread.sleep(4000);
			if(aul.getActivity().size()>=1) {
		     long totalCount = aul.getActivity().stream().count();
		    long filterCount = aul.getActivity().stream().filter(v->v.getText().contains(data.get("usersearch"))).count();
		    soft.assertEquals(totalCount, filterCount);
			}
			else {
					 soft.assertEquals(aul.getEmptyApplicationText().getText(), "There are no entries to display");
				
			}
			soft.assertAll();
	}
	
	@Test(priority=4)
	public void ValidateEventFieldDropDownList() {
		//Verify user can view the following on the event dropdown list; All, View, Export, Search, Requery, Generate, Download, Deactivate, Activate, Change Role, Add User, Add Multiple Users, Manage, Login, Remove, Update, Verify, Reconcile, Request, Print, Reprint, Edit
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 aul = new AuditLog(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getAuditLog());
		 Select eventDropDown = new Select(aul.getEventField());
			List<WebElement> allOptions= eventDropDown.getOptions();
			List<String> categoryOptions= allOptions.stream().map(a->a.getText()).collect(Collectors.toList());
			String[] categoryTypeList= {"-- select event --", "All", "View", "Export", "Search", "Requery", "Generate", "Download", "Deactivate", "Activate",
					"Change Role", "Add User", "Add Multiple Users", "Manage", "Login", "Remove", "Update", "Verify", "Reconcile", 
					"Request", "Print", "Reprint", "Edit"};
			List<String> categoryTypeListArray= Arrays.asList(categoryTypeList);
			Assert.assertEquals(categoryOptions, categoryTypeListArray);
	}
	
	@Test(priority=5)
	public void ValidateAuditLogCardResponse() throws InterruptedException {
		//Verify each log card is clickable
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 aul = new AuditLog(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getAuditLog());
		 for(int q=0; q<aul.getActivity().size(); q++) {
		 Boolean staleElement = true;
		 while(staleElement){

			  try{

				  executor.executeScript("arguments[0].click();", aul.getActivity().get(q));
				  Thread.sleep(1000);
				  List<String> originalAuditDetails= aul.getAuditDetails().stream().map(a->a.getText()).collect(Collectors.toList());
					 String[] auditDetails= {"User", "Phone Number", "Email Address", "Event", "IP Address", "Activity", "Date"};
						List<String> expectedauditDetailsArray= Arrays.asList(auditDetails);
						soft.assertEquals(originalAuditDetails, expectedauditDetailsArray);

			     staleElement = false;


			  } catch(StaleElementReferenceException e){

			    staleElement = true;

			  }

			}
		 }
		 soft.assertAll();
	}
	
	@AfterClass
	public void terminate() {
		driver.close();
	}
	
	
	@DataProvider
	public  Object[][] getData() {
		Object[][] data1=new Object[2][];
		Map<String, String> validData = Map.of("usersearch","Osobase");
		Map <String, String> dp1= new HashMap <>(validData);
		Map<String, String> validData1 = Map.of("usersearch","Seyi");
		Map <String, String> dp3= new HashMap <>(validData1);
		data1[0]= new Object[] { dp1 };
		data1[1]= new Object[] { dp3 };
		return data1;
	}
}
