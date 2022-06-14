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

import pageObject.admin.Reports;
import pageObject.admin.SideMenu;
import resources.base;

public class ReportTest extends base{

	SideMenu sm;
	SoftAssert soft;
	AdminLoginTest alt;
	Reports r;
	
	@BeforeClass
	public void initialize() throws IOException {
		driver= InitializeBrowser();
		driver.get(prop.getProperty("AdminOpUrl"));
		driver.manage().window().maximize();
	}
	
	@Test(priority=1, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void ValidateReportPage(HashMap<String, String> data) {
	//Verify user is taken to the Report page
		alt = new AdminLoginTest();
		alt.ValidateSuccessfulAdminLogin1(driver, data);
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 r = new Reports(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getReport());
		 soft.assertTrue(r.getGeneratedReportButton().isDisplayed());
		 soft.assertTrue(r.getPageHeader().getText().contains("Reports"));
		 soft.assertAll();
	}
	
	@Test(priority=2)
	public void ValidatePageTabs() {
		//Verify user can view the following tabs; Standard report, Inspection report
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 r = new Reports(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getReport());
		List<String> originalPageTabs= r.getPageTab().stream().map(a->a.getText()).collect(Collectors.toList());
		String[] expectedPageTabs= {"Standard Report", "Inspection Report"};
			List<String> pageTabsArray= Arrays.asList(expectedPageTabs);
			Assert.assertEquals(originalPageTabs, pageTabsArray);
	}
	
	@Test(priority=3)
	public void ValidateCategoryDropdownList() {
		//Check system repsonse when user click on the Category field
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 r = new Reports(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getReport());
		 r.getStandardReportTab().click();
		 Select categoryDropDown = new Select(r.getCategoryField());
			List<WebElement> allOptions= categoryDropDown.getOptions();
			List<String> categoryOptions= allOptions.stream().map(a->a.getText()).collect(Collectors.toList());
			String[] categoryTypeList= {"-- select category --", "Agent Details", "Verification Failed", "Guarantor Outstanding",
					"Transaction Reports", "Infraction Report", "Enforcer Report", "Unregistered Agent Infraction Report", "Agent Summary Report"};
			List<String> categoryTypeListArray= Arrays.asList(categoryTypeList);
			Assert.assertEquals(categoryOptions, categoryTypeListArray);
	}
	
	@Test(priority=4)
	public void ValidateEmptyReportRequest() {
		//Check system resonse when user click on the Generate report button without selecting any filter option
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 r = new Reports(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getReport());
		 r.getStandardReportTab().click();
		 soft.assertFalse(r.getGeneratedReportButton().isEnabled());
		 Select categoryDropDown = new Select(r.getCategoryField());
		 categoryDropDown.selectByIndex(2);
		 soft.assertTrue(r.getGeneratedReportButton().isEnabled());
		 soft.assertAll();
	}
	
	@Test(priority=5)
	public void ValidateAgentDetailsReport() {
		//Verify user can view a list of agent details with the following headers; Name, Status, Operators ,Operator category ,Gender, LGA/LCDA
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 r = new Reports(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getReport());
		r.getStandardReportTab().click();
		 Select categoryDropDown = new Select(r.getCategoryField());
		 categoryDropDown.selectByVisibleText("Agent Details");
		 r.getGeneratedReportButton().click();
		 List<String> originalreportHeader= r.getTableHeader().stream().map(a->a.getText()).collect(Collectors.toList());
			String[] expectedreportHeader= {"SN", "Name", "Status", "Operators", "Operator category", "Gender", "LGA/LCDA"};
				List<String> reportHeaderArray= Arrays.asList(expectedreportHeader);
				Assert.assertEquals(originalreportHeader, reportHeaderArray);
	}
	
	@Test(dependsOnMethods="ValidateAgentDetailsReport")
	public void ValidateAgentDetailsBackButton() {
	//Check system response when user click on the back button
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 r = new Reports(driver);
		 r.getBackButton().click();
		 Assert.assertTrue(r.getGeneratedReportButton().isDisplayed());
	}
	
	@Test(priority=6)
	public void ValidateVerificationFailedReport() {
		//Verify user can view a list verification failed with the following headers; Agent name, Reason for failure, Agent Phone number , Agent Email, Date of verification
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 r = new Reports(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getReport());
		r.getStandardReportTab().click();
		 Select categoryDropDown = new Select(r.getCategoryField());
		 categoryDropDown.selectByVisibleText("Verification Failed");
		 r.getGeneratedReportButton().click();
		 List<String> originalreportHeader= r.getTableHeader().stream().map(a->a.getText()).collect(Collectors.toList());
			String[] expectedreportHeader= {"SN", "Agent name", "Reason for failure", "Agent Phone number", "Agent Email", "Date of verification"};
				List<String> reportHeaderArray= Arrays.asList(expectedreportHeader);
				Assert.assertEquals(originalreportHeader, reportHeaderArray);
	}
	
	@Test(dependsOnMethods="ValidateVerificationFailedReport")
	public void ValidateVerificationFailedBackButton() {
	//Check system response when user click on the back button
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 r = new Reports(driver);
		 r.getBackButton().click();
		 Assert.assertTrue(r.getGeneratedReportButton().isDisplayed());
	
	}
	
	@Test(priority=7)
	public void ValidateGuarantorOutstandingReport() {
		//Verify user can view a list of Guarantor outstanding with the following headers;, Date notified, Number of days passed, Guarantor name, Agent name, Agent mobile, Agent Email, Verification status
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 r = new Reports(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getReport());
		r.getStandardReportTab().click();
		 Select categoryDropDown = new Select(r.getCategoryField());
		 categoryDropDown.selectByVisibleText("Guarantor Outstanding");
		 r.getGeneratedReportButton().click();
		 List<String> originalreportHeader= r.getTableHeader().stream().map(a->a.getText()).collect(Collectors.toList());
			String[] expectedreportHeader= {"SN", "Date notified", "Number of days passed", "Guarantor name", "Agent name", "Agent mobile", "Agent Email", "Verification status"};
				List<String> reportHeaderArray= Arrays.asList(expectedreportHeader);
				Assert.assertEquals(originalreportHeader, reportHeaderArray);
	}
	
	@Test(dependsOnMethods="ValidateGuarantorOutstandingReport")
	public void ValidateGuarantorOutstandingBackButton() {
	//Check system response when user click on the back button
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 r = new Reports(driver);
		 r.getBackButton().click();
		 Assert.assertTrue(r.getGeneratedReportButton().isDisplayed());
	
	}
	
	@Test(priority=8)
	public void ValidateTransactionReport() {
		//Verify user can view list of transaction reports with the following headers; Transaction ID, Payer, Transaction Medium, Coupon, Transaction date, Transaction amount, Transaction status
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 r = new Reports(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getReport());
		r.getStandardReportTab().click();
		 Select categoryDropDown = new Select(r.getCategoryField());
		 categoryDropDown.selectByVisibleText("Transaction Reports");
		 r.getGeneratedReportButton().click();
		 List<String> originalreportHeader= r.getTableHeader().stream().map(a->a.getText()).collect(Collectors.toList());
			String[] expectedreportHeader= {"SN", "Transaction ID", "Payer", "Transaction Medium", "Coupon", "Transaction date", "Transaction amount", "Transaction status"};
				List<String> reportHeaderArray= Arrays.asList(expectedreportHeader);
				Assert.assertEquals(originalreportHeader, reportHeaderArray);
	}
	
	@Test(dependsOnMethods="ValidateTransactionReport")
	public void ValidateTransactionReportBackButton() {
	//Check system response when user click on the back button
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 r = new Reports(driver);
		 r.getBackButton().click();
		 Assert.assertTrue(r.getGeneratedReportButton().isDisplayed());
	
	}
	
	@Test(priority=9)
	public void ValidateInfractionReport() {
		//Verify user can view list of Infraction reports with the following headers; Infraction Category, Report Note, Report Photos, Date reported, Agent name, Agent operators, Agent Email, Agent mobile, Enforcer name
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 r = new Reports(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getReport());
		r.getStandardReportTab().click();
		 Select categoryDropDown = new Select(r.getCategoryField());
		 categoryDropDown.selectByVisibleText("Infraction Report");
		 r.getGeneratedReportButton().click();
		 List<String> originalreportHeader= r.getTableHeader().stream().map(a->a.getText()).collect(Collectors.toList());
			String[] expectedreportHeader= {"SN", "Infraction Category", "Report Note", "Report Photos", "Date reported", "Agent name", "Agent operators", "Agent Email", "Agent mobile", "Enforcer name"};
				List<String> reportHeaderArray= Arrays.asList(expectedreportHeader);
				Assert.assertEquals(originalreportHeader, reportHeaderArray);
	}
	
	@Test(dependsOnMethods="ValidateInfractionReport")
	public void ValidateInfractionReportBackButton() {
	//Check system response when user click on the back button
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 r = new Reports(driver);
		 r.getBackButton().click();
		 Assert.assertTrue(r.getGeneratedReportButton().isDisplayed());
	
	}
	
	@Test(priority=10)
	public void ValidateInfractionReportViewPhotos() {
		//Check system response when user clicks on the View Photos Link on the infraction report
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 r = new Reports(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getReport());
		r.getStandardReportTab().click();
		 Select categoryDropDown = new Select(r.getCategoryField());
		 categoryDropDown.selectByVisibleText("Infraction Report");
		 r.getGeneratedReportButton().click();
		 for(int q=0; q<r.getViewPhotos().size(); q++) {
			 executor.executeScript("arguments[0].click();", r.getViewPhotos().get(q));
			 soft.assertTrue(r.getPhotos().size()>=1);
			 r.getBackButton().click();
			 List<String> originalreportHeader= r.getTableHeader().stream().map(a->a.getText()).collect(Collectors.toList());
				String[] expectedreportHeader= {"SN", "Infraction Category", "Report Note", "Report Photos", "Date reported", "Agent name", "Agent operators", "Agent Email", "Agent mobile", "Enforcer name"};
					List<String> reportHeaderArray= Arrays.asList(expectedreportHeader);
					soft.assertEquals(originalreportHeader, reportHeaderArray);
		 }
		 r.getBackButton().click();
		 soft.assertAll();
	}
	
	@Test(priority=11)
	public void ValidateEnforcerReport() {
		//Verify user can view the list of enforcer report with the following headers; Name of Enforcer, Date of Visit, Agent Visited, Agent Operators
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 r = new Reports(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getReport());
		r.getStandardReportTab().click();
		 Select categoryDropDown = new Select(r.getCategoryField());
		 categoryDropDown.selectByVisibleText("Enforcer Report");
		 r.getGeneratedReportButton().click();
		 List<String> originalreportHeader= r.getTableHeader().stream().map(a->a.getText()).collect(Collectors.toList());
			String[] expectedreportHeader= {"SN", "Name of Enforcer", "Date of Visit", "Agent Visited", "Agent Operators"};
				List<String> reportHeaderArray= Arrays.asList(expectedreportHeader);
				Assert.assertEquals(originalreportHeader, reportHeaderArray);
	}
	
	@Test(dependsOnMethods="ValidateEnforcerReport")
	public void ValidateEnforcerReportBackButton() {
	//Check system response when user click on the back button
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 r = new Reports(driver);
		 r.getBackButton().click();
		 Assert.assertTrue(r.getGeneratedReportButton().isDisplayed());
	
	}
	
	@Test(priority=12)
	public void ValidateInspectionPage() {
		//Verify user is taken to the Inspection report page
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 r = new Reports(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getReport());
		 r.getInspectionReportTab().click();
		 soft.assertTrue(r.getFilterButton().isDisplayed());
		 List<String> originalreportHeader= r.getTableHeader().stream().map(a->a.getText()).collect(Collectors.toList());
			String[] expectedreportHeader= {"SN", "Agent Visited", "Category", "License No", "Date", "Time", "Enforcer", "Permanent Status"};
				List<String> reportHeaderArray= Arrays.asList(expectedreportHeader);
				soft.assertEquals(originalreportHeader, reportHeaderArray);
		soft.assertAll(); 
	}

	@Test(priority=13)
	public void ValidateInspectionReportPage() {
		//Verify user is taken to the Inspection report page
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 r = new Reports(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getReport());
		 r.getInspectionReportTab().click();
		 for(int q=0; q<r.getAgentVisted().size(); q++) {
			 executor.executeScript("arguments[0].click();", r.getAgentVisted().get(q));
			 List<String> originalDetails= r.getInspectionReportAgentDetailsHeader().stream().map(a->a.getText()).collect(Collectors.toList());
				String[] expectedDetails= {"Agent's Name:", "Permit Type:", "Permit Number:", "Permit Expiry:", "Permit Status:", "Business address:", "GPS Co-ordinates:", ""};
					List<String> detailsArray= Arrays.asList(expectedDetails);
					soft.assertEquals(originalDetails, detailsArray);
					r.getInspectionBackButton().click();
					 List<String> originalreportHeader= r.getTableHeader().stream().map(a->a.getText()).collect(Collectors.toList());
						String[] expectedreportHeader= {"SN", "Agent Visited", "Category", "License No", "Date", "Time", "Enforcer", "Permanent Status"};
							List<String> reportHeaderArray= Arrays.asList(expectedreportHeader);
							soft.assertEquals(originalreportHeader, reportHeaderArray);
		 }
		 soft.assertAll();
	}	
	
	@AfterClass
	public void terminate() {
		driver.close();
	}
}
