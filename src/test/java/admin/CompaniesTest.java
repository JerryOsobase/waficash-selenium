package admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import agent.RegisterAsAnAgentTest;
import pageObject.admin.Companies;
import pageObject.admin.SideMenu;
import resources.base;

public class CompaniesTest extends base{
	SideMenu sm;
	SoftAssert soft;
	AdminLoginTest alt;
	Companies co;
	CouponTest	ct;
	CompaniesTest cot;
	
	@BeforeClass
	public void initialize() throws IOException {
		driver= InitializeBrowser();
		driver.get(prop.getProperty("AdminOpUrl"));
		driver.manage().window().maximize();
	}
	
	@Test(priority=1, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void ValidateCompaniesPage(HashMap<String, String> data) {
	//Verify user is taken to the companies page
		alt = new AdminLoginTest();
		alt.ValidateSuccessfulAdminLogin1(driver, data);
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 co = new Companies(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCompanies());
		 soft.assertTrue(co.getInviteCompanyButton().isDisplayed());
		 soft.assertTrue(co.getCompaniesHeader().getText().contains("Companies"));
		 soft.assertAll();
	}
	
	@Test(priority=2)
	public void ValidateCompaniesListHeader() {
		//Verify user can view the list of companies with the following header; (Company Name, Email, Mobile, Admin, Status, Action, Last Seen)
		 co = new Companies(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getCompanies());
			 List<String> originalCompaniesListHeader= co.getTableHeader().stream().map(a->a.getText()).collect(Collectors.toList());
				String[] expectedCompaniesListHeader= {"#", "Company Name", "Email", "Mobile", "Admin", "Status", "Action", "Last Seen"};
					List<String> expectedHeaderArray= Arrays.asList(expectedCompaniesListHeader);
					Assert.assertEquals(originalCompaniesListHeader, expectedHeaderArray);
	}
	
	@Test(priority=3)
	public void ValidateInviteCompanyButton() throws InterruptedException {
		//Check system response when user click on the Invite company button
		 co = new Companies(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getCompanies());
			 co.getInviteCompanyButton().click();
			 Thread.sleep(3000);
			 soft.assertTrue(co.getAdminFirstNameField().isDisplayed());
			 soft.assertTrue(co.getInviteNewCompanyButton().isDisplayed());
			 List<String> originalCompanyFieldLabel= co.getPopUpFieldLabel().stream().map(a->a.getText()).collect(Collectors.toList());
				String[] expectedCompanyFieldLabel= {"Email *", "Business Name *", "Trade Name *", "Business category *", "Admin's First Name *",
						"Admin's Last Name *", "Phone number *"};
					List<String> expectedHeaderArray= Arrays.asList(expectedCompanyFieldLabel);
					soft.assertEquals(originalCompanyFieldLabel, expectedHeaderArray);
					soft.assertAll();
					 co.getPopUpCloseButton().click();
	}
	
	@Test(priority=4, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void EmptyBusinessCategoryField(HashMap<String, String> data) {
		//Verify user is unable to invite company if the Business field is empty
		 co = new Companies(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getCompanies());
			 co.getInviteCompanyButton().click();
			 ct = new CouponTest();
			 co.getEmailAddressField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("companyEmail"));
			 co.getBusinessNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Business" + ct.setCouponCode());
			 co.getTradeNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Trade" + ct.setCouponCode());
			 co.getAdminFirstNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Admin");
			 co.getAdminLastNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), ct.setCouponCode());
			 co.getPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("companyPhoneNumber"));
			 Assert.assertFalse(co.getInviteNewCompanyButton().isEnabled());
			 co.getPopUpCloseButton().click();
			 
	}
	
	@Test(priority=5, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void EmptyEmailField(HashMap<String, String> data) {
		//Verify user is unable to invite company if the Email field is empty
		 co = new Companies(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getCompanies());
			 co.getInviteCompanyButton().click();
			 ct = new CouponTest();
			 co.getEmailAddressField().sendKeys(Keys.chord(Keys.COMMAND,"a"), Keys.chord(Keys.DELETE));
			 co.getBusinessNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Business" + ct.setCouponCode());
			 co.getTradeNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Trade" + ct.setCouponCode());
			 Select businessCategoryDropDown = new Select(co.getBusinessCategoryField());
			 businessCategoryDropDown.selectByVisibleText("Just a Test");
			 co.getAdminFirstNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Admin");
			 co.getAdminLastNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), ct.setCouponCode());
			 co.getPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("companyPhoneNumber"));
			 Assert.assertFalse(co.getInviteNewCompanyButton().isEnabled());
			 co.getPopUpCloseButton().click();
	}
	
	@Test(priority=6, dataProvider="invalidGetData", dataProviderClass=AdminLoginTest.class)
	public void InvalidEmailFormat(HashMap<String, String> data) {
		//Verify user is unable to invite company if user input an invalid email format
		 co = new Companies(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getCompanies());
			 co.getInviteCompanyButton().click();
			 ct = new CouponTest();
			 co.getEmailAddressField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("invalidEmailAddress"));
			 co.getBusinessNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Business" + ct.setCouponCode());
			 co.getTradeNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Trade" + ct.setCouponCode());
			 Select businessCategoryDropDown = new Select(co.getBusinessCategoryField());
			 businessCategoryDropDown.selectByVisibleText("Just a Test");
			 co.getAdminFirstNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Admin");
			 co.getAdminLastNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), ct.setCouponCode());
			 co.getPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), "11111111111");
			 Assert.assertFalse(co.getInviteNewCompanyButton().isEnabled());
			 co.getPopUpCloseButton().click();
	}
	
	@Test(priority=7, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void EmptyBusinessNameField(HashMap<String, String> data) {
		//Verify user is unable to invite company if the Business name field is empty
		 co = new Companies(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getCompanies());
			 co.getInviteCompanyButton().click();
			 ct = new CouponTest();
			 co.getEmailAddressField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("companyEmail"));
			 co.getBusinessNameField().sendKeys(Keys.chord(Keys.COMMAND,"a"), Keys.chord(Keys.DELETE));
			 co.getTradeNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Trade" + ct.setCouponCode());
			 Select businessCategoryDropDown = new Select(co.getBusinessCategoryField());
			 businessCategoryDropDown.selectByVisibleText("Just a Test");
			 co.getAdminFirstNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Admin");
			 co.getAdminLastNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), ct.setCouponCode());
			 co.getPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("companyPhoneNumber"));
			 Assert.assertFalse(co.getInviteNewCompanyButton().isEnabled());
			 co.getPopUpCloseButton().click();
	}
	
	@Test(priority=8, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void EmptyTradeNameField(HashMap<String, String> data) {
		//Verify user is unable to invite company if the Trade name field is empty
		 co = new Companies(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getCompanies());
			 co.getInviteCompanyButton().click();
			 ct = new CouponTest();
			 co.getEmailAddressField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("companyEmail"));
			 co.getBusinessNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Business" + ct.setCouponCode());
			 co.getTradeNameField().sendKeys(Keys.chord(Keys.COMMAND,"a"), Keys.chord(Keys.DELETE));
			 Select businessCategoryDropDown = new Select(co.getBusinessCategoryField());
			 businessCategoryDropDown.selectByVisibleText("Just a Test");
			 co.getAdminFirstNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Admin");
			 co.getAdminLastNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), ct.setCouponCode());
			 co.getPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("companyPhoneNumber"));
			 Assert.assertFalse(co.getInviteNewCompanyButton().isEnabled());
			 co.getPopUpCloseButton().click();
	}
	
	@Test(priority=9, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void EmptyAdminFirstNameField(HashMap<String, String> data) {
		//Verify user is unable to invite company if the Admin's first name field is empty
		 co = new Companies(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getCompanies());
			 co.getInviteCompanyButton().click();
			 ct = new CouponTest();
			 co.getEmailAddressField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("companyEmail"));
			 co.getBusinessNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Business" + ct.setCouponCode());
			 co.getTradeNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Trade" + ct.setCouponCode());
			 Select businessCategoryDropDown = new Select(co.getBusinessCategoryField());
			 businessCategoryDropDown.selectByVisibleText("Just a Test");
			 co.getAdminFirstNameField().sendKeys(Keys.chord(Keys.COMMAND,"a"), Keys.chord(Keys.DELETE));
			 co.getAdminLastNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), ct.setCouponCode());
			 co.getPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("companyPhoneNumber"));
			 Assert.assertFalse(co.getInviteNewCompanyButton().isEnabled());
			 co.getPopUpCloseButton().click();
	}
	
	@Test(priority=10, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void EmptyAdminLastNameField(HashMap<String, String> data) {
		//Verify user is unable to invite company if the Admin's last name field is empty
		 co = new Companies(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getCompanies());
			 co.getInviteCompanyButton().click();
			 ct = new CouponTest();
			 co.getEmailAddressField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("companyEmail"));
			 co.getBusinessNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Business" + ct.setCouponCode());
			 co.getTradeNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Trade" + ct.setCouponCode());
			 Select businessCategoryDropDown = new Select(co.getBusinessCategoryField());
			 businessCategoryDropDown.selectByVisibleText("Just a Test");
			 co.getAdminFirstNameField().sendKeys(Keys.chord(Keys.COMMAND,"a"), "Admin");
			 co.getAdminLastNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), Keys.chord(Keys.DELETE));
			 co.getPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("companyPhoneNumber"));
			 Assert.assertFalse(co.getInviteNewCompanyButton().isEnabled());
			 co.getPopUpCloseButton().click();
	}
	
	@Test(priority=11, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void EmptyPhoneNumberField(HashMap<String, String> data) {
		//Verify user is unable to invite company if the phonenumber field is empty
		 co = new Companies(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getCompanies());
			 co.getInviteCompanyButton().click();
			 ct = new CouponTest();
			 co.getEmailAddressField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("companyEmail"));
			 co.getBusinessNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Business" + ct.setCouponCode());
			 co.getTradeNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Trade" + ct.setCouponCode());
			 Select businessCategoryDropDown = new Select(co.getBusinessCategoryField());
			 businessCategoryDropDown.selectByVisibleText("Just a Test");
			 co.getAdminFirstNameField().sendKeys(Keys.chord(Keys.COMMAND,"a"), "Admin");
			 co.getAdminLastNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), ct.setCouponCode());
			 co.getPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), Keys.chord(Keys.DELETE));
			 Assert.assertFalse(co.getInviteNewCompanyButton().isEnabled());
			 co.getPopUpCloseButton().click();
	}
	
	@Test(priority=12, dataProvider="invalidGetData", dataProviderClass=RegisterAsAnAgentTest.class)
	public void InvalidPhoneNumberFormat(HashMap<String, String> data) {
		//Verify user is unable to invite company if user input an invalid phone number format
		 co = new Companies(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getCompanies());
			 co.getInviteCompanyButton().click();
			 ct = new CouponTest();
			 co.getEmailAddressField().sendKeys(Keys.chord(Keys.COMMAND,"a"), "test@gmail.com");
			 co.getBusinessNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Business" + ct.setCouponCode());
			 co.getTradeNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Trade" + ct.setCouponCode());
			 Select businessCategoryDropDown = new Select(co.getBusinessCategoryField());
			 businessCategoryDropDown.selectByVisibleText("Just a Test");
			 co.getAdminFirstNameField().sendKeys(Keys.chord(Keys.COMMAND,"a"), "Admin");
			 co.getAdminLastNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), ct.setCouponCode());
			 co.getPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("Invalidphonenumber"));
			 Assert.assertFalse(co.getInviteNewCompanyButton().isEnabled());
			 co.getPopUpCloseButton().click();
	}
	
	@Test(priority=13, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void SuccessfulCompanyInvite(HashMap<String, String> data) throws InterruptedException {
		//Verify user can invite company successfully
		 co = new Companies(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getCompanies());
			 co.getInviteCompanyButton().click();
			 ct = new CouponTest();
			 String Business=  "Business" + ct.setCouponCode();
			 String Trade = "Trade" + ct.setCouponCode();
			 String LastName = ct.setCouponCode();
			 co.getEmailAddressField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("companyEmail"));
			 co.getBusinessNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), Business);
			 co.getTradeNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), Trade);
			 Select businessCategoryDropDown = new Select(co.getBusinessCategoryField());
			 businessCategoryDropDown.selectByVisibleText("Just a Test");
			 co.getAdminFirstNameField().sendKeys(Keys.chord(Keys.COMMAND,"a"), "Admin");
			 co.getAdminLastNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), LastName);
			 co.getPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("companyPhoneNumber"));
			 co.getInviteNewCompanyButton().click();
			 Thread.sleep(3000);
			 soft.assertEquals(co.getPromptMessage().getText(), "Success\n"
			 		+ "Invitation sent successfully");
			 List<String> originalCompanyDetails= new ArrayList<String>();
			 Optional<WebElement> find;
			 do {
				 find = co.getCompanyName().stream().filter(v->v.getText().contains(Business)).findAny();
			 for(int q=0; q<co.getCompanyName().size(); q++) {
				 if(co.getCompanyName().get(q).getText().contains(Business)) {
					 originalCompanyDetails.add(co.getCompanyName().get(q).findElement(By.xpath("following-sibling::td[1]")).getText());
					 originalCompanyDetails.add(co.getCompanyName().get(q).findElement(By.xpath("following-sibling::td[2]")).getText());
					 originalCompanyDetails.add(co.getCompanyName().get(q).findElement(By.xpath("following-sibling::td[3]")).getText());
					 break;
				 }
			 }
				 if(!find.isPresent()) {
					 co.getNextPagination().click();
					 Thread.sleep(3000);
				 }
			 
			 }while(!find.isPresent());
			 String[] expectedCompanyDetails= {data.get("companyEmail"), data.get("companyPhoneNumber"), "Admin " + LastName};
				List<String> expectedCompanyDetailsArray= Arrays.asList(expectedCompanyDetails);
			 soft.assertEquals(originalCompanyDetails, expectedCompanyDetailsArray);
			 soft.assertAll();
	}
	
	@Test(dependsOnMethods="SuccessfulCompanyInvite", dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void ValidateExistingEmailInvite(HashMap<String, String> data) throws InterruptedException {
		//Check system response when user tries to invite a company with an existing email address
		 co = new Companies(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getCompanies());
			 executor.executeScript("arguments[0].click();", co.getInviteCompanyButton());
			 ct = new CouponTest();
			 co.getEmailAddressField().sendKeys(Keys.chord(Keys.COMMAND,"a"), data.get("companyEmail"));
			 co.getBusinessNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Business" + ct.setCouponCode());
			 co.getTradeNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "Trade" + ct.setCouponCode());
			 Select businessCategoryDropDown = new Select(co.getBusinessCategoryField());
			 businessCategoryDropDown.selectByVisibleText("Just a Test");
			 co.getAdminFirstNameField().sendKeys(Keys.chord(Keys.COMMAND,"a"), "Admin");
			 co.getAdminLastNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), ct.setCouponCode());
			 co.getPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND,"a"), "63938292983");
			 co.getInviteNewCompanyButton().click();
			 Thread.sleep(3000);
			 Assert.assertEquals(co.getPromptMessage().getText(), "Error\n"
			 		+ "An account with this email already exists");
			 co.getPopUpCloseButton().click();
	}
	
	@Test(priority=14)
	public void ValidateInvitedCompanyStatus() throws InterruptedException {
		//Verify user can view company's status as "Invited" if that operator has never logged into his account
		 co = new Companies(driver);
		 soft = new SoftAssert();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getCompanies());
			 do {
			 for(int q=0; q<co.getStatus().size(); q++) {
				 if(co.getStatus().get(q).getText().equalsIgnoreCase("invited")) {
					soft.assertEquals(co.getStatus().get(q).findElement(By.xpath("following-sibling::td[1]")).getText(), "Resend Invite"); 
				 }
			 }
			 if(!co.getNextPagination().getText().contains("Next")) {
				 co.getNextPagination().click();
				 Thread.sleep(3000);
			 }
			 
			 }while(!co.getNextPagination().getText().contains("Next"));
			 soft.assertAll();
	}
	
	@Test(priority=15)
	public void ValidateActiveCompanyStatus() throws InterruptedException {
		//Verify user can view company's status as "Active" if that operator has logged into his account
		 co = new Companies(driver);
		 soft = new SoftAssert();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getCompanies());
			 do {
			 for(int q=0; q<co.getStatus().size(); q++) {
				 if(co.getStatus().get(q).getText().equalsIgnoreCase("active")) {
					soft.assertEquals(co.getStatus().get(q).findElement(By.xpath("following-sibling::td[1]")).getText(), "De-Activate"); 
				 }
			 }
			 if(!co.getNextPagination().getText().contains("Next")) {
				 co.getNextPagination().click();
				 Thread.sleep(3000);
			 }
			 
			 }while(!co.getNextPagination().getText().contains("Next"));
			 soft.assertAll();
	}
	
	/*@Test(priority=15, dataProvider="mergedData", dataProviderClass=RegisterAsAnAgentTest.class)
	public void ValidateDeactivateButton(HashMap<String, String> data) throws InterruptedException {
		//Check system response when user click on the Deactivate button
		 co = new Companies(driver);
		 soft = new SoftAssert();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			 executor.executeScript("arguments[0].click();", sm.getCompanies());
			 Optional<WebElement> find;
			 do {
				 find = co.getCompanyName().stream().filter(v->v.getText().contains(data.get("company"))).findAny();
			 for(int q=0; q<co.getStatus().size(); q++) {
				 if(co.getStatus().get(q).getText().contains(data.get("company"))) {
					co.getActionButton().get(q).click();
				 }
			 }
			 if(!find.isPresent()) {
				 co.getNextPagination().click();
				 Thread.sleep(3000);
			 }
			 
			 }while(!find.isPresent());
			 soft.assertAll();
	}*/
	
	@AfterClass
	public void terminate() {
		driver.close();
	}

}
