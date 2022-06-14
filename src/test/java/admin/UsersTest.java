package admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.admin.ManageRoles;
import pageObject.admin.SideMenu;
import pageObject.admin.Users;
import resources.base;

public class UsersTest extends base {
	Users u;
	SideMenu sm;
	SoftAssert soft;
	AdminLoginTest alt;
	ManageRoles mr;
	CouponTest ct;

	@BeforeClass
	public void initialize() throws IOException {
		driver= InitializeBrowser();
		driver.get(prop.getProperty("AdminOpUrl"));
		driver.manage().window().maximize();
	}
	
	@Test(priority=1, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void ValidateUsersPage(HashMap<String, String> data) {
	//Verify user is taken to the users page
		alt = new AdminLoginTest();
		alt.ValidateSuccessfulAdminLogin1(driver, data);
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 u = new Users(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
	 soft.assertTrue(u.getManageRolesButton().isDisplayed());
	 soft.assertTrue(u.getUserHeader().getText().contains("Users"));
	 soft.assertAll();
	}
	
	@Test(priority=2)
	public void ValidateUserListHeader() {
	//Verify user can view list of users with the following headers; (Name, Role)
		sm = new SideMenu(driver);
		u = new Users(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 List<String> originalUserListHeader= u.getTableHeader().stream().map(a->a.getText()).collect(Collectors.toList());
			String[] expectedUserListHeader= {"#", "Name", "Role"};
				List<String> expectedHeaderArray= Arrays.asList(expectedUserListHeader);
				Assert.assertEquals(originalUserListHeader, expectedHeaderArray);
	}
	
	@Test(priority=3)
	public void ValidateManageRoleButton() {
		//Check system response when user clicks on the "Manage roles" button
		sm = new SideMenu(driver);
		u = new Users(driver);
		mr = new ManageRoles(driver);
		soft = new  SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 u.getManageRolesButton().click();
		 soft.assertTrue(mr.getAddCustomRoleButton().isDisplayed());
		 soft.assertEquals(mr.getPageHeader().getText(), "Manage Roles");
		soft.assertAll();	
	}
	
	@Test(priority=4)
	public void ValidateRoleType() {
		//Verify user can the following as the role type; (Default roles, Custom roles)
		sm = new SideMenu(driver);
		u = new Users(driver);
		mr = new ManageRoles(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 u.getManageRolesButton().click();
		 List<String> originalRoleType= mr.getRoleHeader().stream().map(a->a.getText()).collect(Collectors.toList());
			String[] expectedRoleType= {"Default Roles", "Custom Roles"};
				List<String> expectedHeaderArray= Arrays.asList(expectedRoleType);
				Assert.assertEquals(originalRoleType, expectedHeaderArray);
	}
	
	@Test(priority=5)
	public void ValidateDefaultRoleList() {
		//Verify user can view the following as default roles; (Super admin, Enforcer)
		sm = new SideMenu(driver);
		u = new Users(driver);
		mr = new ManageRoles(driver);
		soft = new  SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 u.getManageRolesButton().click();
		 soft.assertTrue(mr.getAddCustomRoleButton().isDisplayed());
		 String[] expectedDefaultRole= {"Default Roles", "Enforcer", "Super Admin"};
			List<String> expectedHeaderArray= Arrays.asList(expectedDefaultRole);
		 for(int q=0; q<3; q++) {
			 soft.assertEquals(mr.getRoleList().get(q).getText(), expectedHeaderArray.get(q));
		 }
		
		 soft.assertAll();
	}
	
	@Test(priority=6, dataProvider="getData")
	public void EmptyRoleNameField(HashMap<String, String> data) {
		//Check system response when the role name field is empty
		sm = new SideMenu(driver);
		u = new Users(driver);
		mr = new ManageRoles(driver);
		soft = new  SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 u.getManageRolesButton().click();
		 mr.getAddCustomRoleButton().click();
		 mr.getRoleDescriptionField().sendKeys(data.get("roleDescription"));
		 for(int q=0; q<mr.getCheckbox().size(); q++) {
			 mr.getCheckbox().get(q).click();
		 }
		Assert.assertFalse(mr.getCreateBoxButton().isEnabled());
	}
	
	@Test(priority=7, dataProvider="getData")
	public void EmptyRoleDescriptionField(HashMap<String, String> data) {
		//Check system response when the role description field is empty
		sm = new SideMenu(driver);
		u = new Users(driver);
		mr = new ManageRoles(driver);
		soft = new  SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 u.getManageRolesButton().click();
		 mr.getAddCustomRoleButton().click();
		 mr.getRoleNameField().sendKeys(data.get("roleName"));
		 for(int q=0; q<mr.getCheckbox().size(); q++) {
			 mr.getCheckbox().get(q).click();
		 }
		Assert.assertFalse(mr.getCreateBoxButton().isEnabled());
	}
	
	@Test(priority=8, dataProvider="getData")
	public void EmptyPermissionCheckbox(HashMap<String, String> data) {
		//Check system response when the permission checkbox is empty
		sm = new SideMenu(driver);
		u = new Users(driver);
		mr = new ManageRoles(driver);
		soft = new  SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 u.getManageRolesButton().click();
		 mr.getAddCustomRoleButton().click();
		 mr.getRoleNameField().sendKeys(data.get("roleName"));
		 mr.getRoleDescriptionField().sendKeys(data.get("roleDescription"));
		Assert.assertFalse(mr.getCreateBoxButton().isEnabled());
	}
	
	
	@Test(priority=9, dataProvider="getData")
	public void ValidateCustomRoleButton(HashMap<String, String> data) throws InterruptedException {
		//Verify user can add a custom role
		sm = new SideMenu(driver);
		u = new Users(driver);
		mr = new ManageRoles(driver);
		soft = new  SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 u.getManageRolesButton().click();
		 mr.getAddCustomRoleButton().click();
		 soft.assertEquals(mr.getCustomRoleFormHeader().getText(), "Create Custom Role");
		 mr.getRoleNameField().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("roleName"));
		 mr.getRoleDescriptionField().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("roleDescription"));
		 for(int q=0; q<mr.getCheckbox().size(); q++) {
			 mr.getCheckbox().get(q).click();
		 }
		 mr.getCreateBoxButton().click();
		 soft.assertEquals(mr.getPromptMessage().getText(), "Success\n"
		 		+ "New Custom Role created successfully");
		 long count = mr.getRoleList().stream().filter(q->q.getText().equalsIgnoreCase(data.get("roleName"))).count();
		 soft.assertTrue(count==1);
		 soft.assertAll();
	}
	
	@Test(priority=10, dataProvider="getData")
	public void ValidateDuplicateSuperAdminResponse(HashMap<String, String> data) throws InterruptedException {
		//Verify user is unable to create role with the name "Super admin"
		sm = new SideMenu(driver);
		u = new Users(driver);
		mr = new ManageRoles(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 u.getManageRolesButton().click();
		 mr.getAddCustomRoleButton().click();
		 mr.getRoleNameField().sendKeys("super admin");
		 mr.getRoleDescriptionField().sendKeys(data.get("roleDescription"));
		 for(int q=0; q<mr.getCheckbox().size(); q++) {
			 mr.getCheckbox().get(q).click();
		 }
		 mr.getCreateBoxButton().click();
		 Assert.assertEquals(mr.getPromptMessage().getText(), "Error\n"
			 		+ "Role already exists");
	}
	
	@Test(priority=11, dataProvider="getData")
	public void ValidateDuplicateEnforcerResponse(HashMap<String, String> data) throws InterruptedException {
		//Verify user is unable to create role with the name "Enforcer"
		sm = new SideMenu(driver);
		u = new Users(driver);
		mr = new ManageRoles(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 u.getManageRolesButton().click();
		 mr.getAddCustomRoleButton().click();
		 mr.getRoleNameField().sendKeys("enforcer");
		 mr.getRoleDescriptionField().sendKeys(data.get("roleDescription"));
		 for(int q=0; q<mr.getCheckbox().size(); q++) {
			 mr.getCheckbox().get(q).click();
		 }
		 mr.getCreateBoxButton().click();
		 Assert.assertEquals(mr.getPromptMessage().getText(), "Error\n"
			 		+ "Role already exists");
	}
	
	@Test(priority=11, dataProvider="getData")
	public void ValidateDuplicateCustomRoleResponse(HashMap<String, String> data) throws InterruptedException {
		//Verify user is unable to create a role already existing 
		sm = new SideMenu(driver);
		u = new Users(driver);
		mr = new ManageRoles(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 u.getManageRolesButton().click();
		 mr.getAddCustomRoleButton().click();
		 mr.getRoleNameField().sendKeys(data.get("roleName"));
		 mr.getRoleDescriptionField().sendKeys(data.get("roleDescription"));
		 for(int q=0; q<mr.getCheckbox().size(); q++) {
			 mr.getCheckbox().get(q).click();
		 }
		 mr.getCreateBoxButton().click();
		 Assert.assertEquals(mr.getPromptMessage().getText(), "Error\n"
			 		+ "Role already exists");
	}
	
	@Test(priority=12)
	public void ValidateroleCardResponse() {
		//Check system response when user click on each role
		sm = new SideMenu(driver);
		u = new Users(driver);
		mr = new ManageRoles(driver);
		soft = new  SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 u.getManageRolesButton().click();
		 soft.assertTrue(mr.getAddCustomRoleButton().isDisplayed());
		 for(int q=0; q<mr.getRoleList().size(); q++) {
			 if(!mr.getRoleList().get(q).getText().contains("Roles")) {
				 mr.getRoleList().get(q).click();
				 soft.assertEquals(mr.getRoleAccessHeader().getText(), "What this role can access");	
			 }
			
		 }
		 soft.assertAll();
	}
	
	@Test(priority=13)
	public void ValidateDefaultRoleEditButton() {
		//Verify user is unable to edit the default roles
		sm = new SideMenu(driver);
		u = new Users(driver);
		mr = new ManageRoles(driver);
		soft = new  SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 u.getManageRolesButton().click();
		 soft.assertTrue(mr.getAddCustomRoleButton().isDisplayed());
		 for(int q=1; q<3; q++) {
			 mr.getRoleList().get(q).click();
			soft.assertTrue(mr.getEditButton().size()<1);
		 }
		 soft.assertAll();
		 
	}
	
	@Test(priority=14)
	public void ValidateDefaultRoleDeleteButton() {
		//Verify user is unable to delete the default roles
		sm = new SideMenu(driver);
		u = new Users(driver);
		mr = new ManageRoles(driver);
		soft = new  SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 u.getManageRolesButton().click();
		 soft.assertTrue(mr.getAddCustomRoleButton().isDisplayed());
		 for(int q=1; q<3; q++) {
			 mr.getRoleList().get(q).click();
			soft.assertTrue(mr.getDeleteButton().size()<1);
		 }
		 soft.assertAll();
		 
	}
	
	@Test(priority=15)
	public void ValidateCustomRoleDeletebutton() throws InterruptedException {
		//Verify user is unable to delete a custom role with users
		sm = new SideMenu(driver);
		u = new Users(driver);
		mr = new ManageRoles(driver);
		soft = new  SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 u.getManageRolesButton().click();
		 soft.assertTrue(mr.getAddCustomRoleButton().isDisplayed());
		 for(int q=4; q<mr.getRoleList().size(); q++) {
			 mr.getRoleList().get(q).click();
			 Thread.sleep(1000);
			 if(mr.getRoleMemberText().getText().contains("There are no team members in this role")) {
				 soft.assertTrue(mr.getDeleteButton().size()>=1);
			 }
			 else {
				 soft.assertTrue(mr.getDeleteButton().size()<1);
			 }
		 }
		 soft.assertAll();
	}
	
	@Test(priority=16, dataProvider="getData")
	public void ValidateDeleteButton(HashMap<String, String> data) throws InterruptedException {
		//Check system response when user click on the delete button
		sm = new SideMenu(driver);
		u = new Users(driver);
		mr = new ManageRoles(driver);
		soft = new  SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 u.getManageRolesButton().click();
		 soft.assertTrue(mr.getAddCustomRoleButton().isDisplayed());
		 for(int q=0; q<mr.getRoleList().size(); q++) {
		 if(mr.getRoleList().get(q).getText().equalsIgnoreCase(data.get("roleName"))) {
			 mr.getRoleList().get(q).click();
			 mr.getDeleteButton().get(0).click();
			 break;
		 }
		 }
		soft.assertEquals(mr.getPopUpDeleteText().getText(), "This action will delete the role "+ data.get("roleName") +" and you will no "
				+ "longer be able to assign team members to it");
		mr.getPopUpDeleteButton().click();
		soft.assertEquals(mr.getPromptMessage().getText(), "Success\n"
				+ "Custom Role deleted successfully");
		Thread.sleep(1000);
		long count = mr.getRoleList().stream().filter(q->q.getText().equalsIgnoreCase(data.get("roleName"))).count();
		 soft.assertTrue(count<1);
		 soft.assertAll();
	}
	
	@Test(priority=17)
	public void ValidateAssignedUsers() throws InterruptedException {
		//Verify user can view all users assigned to a particular role
		sm = new SideMenu(driver);
		u = new Users(driver);
		mr = new ManageRoles(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 u.getManageRolesButton().click();
		 soft.assertTrue(mr.getAddCustomRoleButton().isDisplayed());
		 for(int q=0; q<mr.getRoleList().size(); q++) {
				 if(!mr.getRoleList().get(q).getText().contains("Roles")) {
				  mr.getRoleList().get(q).click();
				  Thread.sleep(1000);
					 if(mr.getAssignedUsersLink().size()>=1) {
						 String roleName = mr.getRoleList().get(q).getText();
						 mr.getAssignedUsersLink().get(0).click();
						soft.assertTrue(mr.getAssignedMembersFormHeader().getText().contains(roleName + " Users"));
						String splitter[] = mr.getAssignedMembersFormHeader().getText().split(" ");
						String userNumber= splitter[splitter.length-1].trim();
						long count = mr.getAssignedUsersList().stream().count();
						String s=String.valueOf(count);
						soft.assertTrue(userNumber.contains(s));
						mr.getAssignedMembersFormCancelButton().click();
						Thread.sleep(3000);
					 }
				 }
			 
		 }
		 soft.assertAll();
	}
	
	@Test(priority=18)
	public void ValidateRoleDropDownField() {
		//Check system response when user click on the Role field
		sm = new SideMenu(driver);
		u = new Users(driver);
		mr = new ManageRoles(driver);
		soft = new  SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 Select roleDropdown = new Select(u.getRoleField());
			List<WebElement> allOptions= roleDropdown.getOptions();
			List<String> roleOptions= allOptions.stream().map(a->a.getText()).sorted().collect(Collectors.toList());
		 u.getManageRolesButton().click();
		 soft.assertTrue(mr.getAddCustomRoleButton().isDisplayed());
		 ArrayList<String> roleArray= new ArrayList<String>();
		 roleArray.add("-- select role --");
		 roleArray.add("All");
		 for(int q=0; q<mr.getRoleList().size(); q++) {
			 if(!mr.getRoleList().get(q).getText().contains("Roles")) {
				 mr.getRoleList().get(q).click();
				String roleName= mr.getRoleList().get(q).getText();
				roleArray.add(roleName);
			 }
		 }
		 Collections.sort(roleArray);
		 soft.assertEquals(roleOptions, roleArray);
		 soft.assertAll();	
	}
	
	@Test(priority=19, dataProvider="getData")
	public void ValidateRoleFieldFilter(HashMap<String, String> data) throws InterruptedException {
		//Verify user filter list by each role
		sm = new SideMenu(driver);
		u = new Users(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 int role = 0;
		 for(int q=0; q<u.getRoles().size(); q++) {
			 if(u.getRoles().get(q).getText().contains(data.get("roles"))) {
				 role++;
			 }
		 }
			 
			
		while(u.getActivePagination().size()==1) {
				Thread.sleep(3000);
				
				u.getNextPagination().click();
				Thread.sleep(3000);
				for(int r=0; r<u.getRoles().size(); r++) {
					 if(u.getRoles().get(r).getText().contains(data.get("roles"))) {
						 role++;
					 }
				 }
				  if(u.getNextPagination().getText().contains("Next")) {
					 break;
				 }
		 
		}
		 Select roleDropdown = new Select(u.getRoleField());
		 roleDropdown.selectByVisibleText(data.get("roles"));
		 u.getFilterButton().click();
		 @SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
			wait.until(ExpectedConditions.visibilityOf(u.getClearFilterButton()));
			long count =  u.getRoles().stream().count();
				
				while(u.getActivePagination().size()==1) {
					
					
					u.getNextPagination().click();
					Thread.sleep(3000);
					count += u.getRoles().stream().count();
					
					  if(u.getNextPagination().getText().contains("Next")) {
						 break;
					 }
					  
				 }
				executor.executeScript("arguments[0].click();", u.getClearFilterButton());
				wait.until(ExpectedConditions.invisibilityOf(u.getClearFilterButton()));
		
		
				Assert.assertEquals(role, count);
	}
	
	@Test(priority=20)
	public void ValidateInviteUserButton() throws InterruptedException {
		//Check system response when user click on the Invite user button
		sm = new SideMenu(driver);
		u = new Users(driver);
		soft = new SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 executor.executeScript("arguments[0].click();", u.getInviteUsersButton());
		soft.assertTrue(u.getEmailAddressField().isDisplayed());
		soft.assertTrue(u.getUserInvitePopUpRoleField().isDisplayed());
		soft.assertTrue(u.getPopUpInviteUserButton().isDisplayed());
		soft.assertAll();	 
		executor.executeScript("arguments[0].click();", u.getPopUpCloseButton());
	}
	
	@Test(priority=21)
	public void ValidateInvitedUserStatus() throws InterruptedException {
		//Verify user view users with "Invited" status if the user has not completed registration
		sm = new SideMenu(driver);
		u = new Users(driver);
		soft = new SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 Boolean staleElement = true;
			while(staleElement){

				  try{
		 for(int q=0; q<u.getUserName().size(); q++){
			 if(u.getUserName().get(q).getText().contains("invited")) {
				 soft.assertEquals(u.getStatus().get(q).getText(), "invited");
				 executor.executeScript("arguments[0].click();",  u.getUserName().get(q));
				 Thread.sleep(2000);
				List<String> originalButtonList=  u.getUserButton().stream().map(a->a.getText()).collect(Collectors.toList());
				 String[] expectedButtonList= {"Re-invite User", "Change User Role", "Deactivate User"};
					List<String> expectedButtonListArray= Arrays.asList(expectedButtonList);
					soft.assertEquals(originalButtonList, expectedButtonListArray);
				     staleElement = false;


							  }
		 }
		 }catch(StaleElementReferenceException e){

							    staleElement = true;

							  }

							}
			soft.assertAll();
			 }
	
	@Test(priority=22)
	public void ValidateActiveUserStatus() throws InterruptedException {
		//Verify user view users with "Active" status if the user has completed registration
		sm = new SideMenu(driver);
		u = new Users(driver);
		soft = new SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 Boolean staleElement = true;
			while(staleElement){

				  try{
		 for(int q=0; q<u.getUserName().size(); q++){
			 if(u.getUserName().get(q).getText().contains("active")) {
				 soft.assertEquals(u.getStatus().get(q).getText(), "active");
				 executor.executeScript("arguments[0].click();",  u.getUserName().get(q));
				 Thread.sleep(2000);
				List<String> originalButtonList=  u.getUserButton().stream().map(a->a.getText()).collect(Collectors.toList());
				 String[] expectedButtonList= {"Change User Role", "Deactivate User"};
					List<String> expectedButtonListArray= Arrays.asList(expectedButtonList);
					soft.assertEquals(originalButtonList, expectedButtonListArray);
							  }
		 }
		 staleElement = false;
		 }catch(StaleElementReferenceException e){

							    staleElement = true;

							  }

							}
			soft.assertAll();
			 }
	
	@Test(priority=23)
	public void ValidateUserCard() throws InterruptedException {
		//Verify user card is clickable
		sm = new SideMenu(driver);
		u = new Users(driver);
		soft = new SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
			 Boolean staleElement = true;
			 while(staleElement){

				  try{
					  for(int q=0; q<u.getUserName().size(); q++){
						  if(u.getUserName().get(q).getText().contains("invited")) {
						  executor.executeScript("arguments[0].click();",  u.getUserName().get(q));
						  Thread.sleep(2000);
					  List<String> originalcouponDetails= u.getUserDetailsHeader().stream().map(a->a.getText()).collect(Collectors.toList());
						 String[] couponDetails= {"Status", "Phone Number", "Email Address", "Role", "Date"};
							List<String> expectedcouponDetailsArray= Arrays.asList(couponDetails);
							soft.assertEquals(originalcouponDetails, expectedcouponDetailsArray);

				     staleElement = false;
					  }
						  else {
							  executor.executeScript("arguments[0].click();",  u.getUserName().get(q));
							  Thread.sleep(2000);
							  List<String> originalcouponDetails= u.getUserDetailsHeader().stream().map(a->a.getText()).collect(Collectors.toList());
								 String[] couponDetails= {"Name", "Phone Number", "Email Address", "Role", "Date"};
									List<String> expectedcouponDetailsArray= Arrays.asList(couponDetails);
									soft.assertEquals(originalcouponDetails, expectedcouponDetailsArray);
						  }
					  }
				  }catch(StaleElementReferenceException e){

				    staleElement = true;

				  }

			 }
			 
	
		 soft.assertAll();
	}
	
	@Test(priority=24, dataProvider="getData")
	public void ValidateChangeUserRole(HashMap<String, String> data) throws InterruptedException {
		//Verify user can change user role
		sm = new SideMenu(driver);
		u = new Users(driver);
		soft = new SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 Boolean staleElement = true;
		 while(staleElement){

			  try{
				  
		 Optional<WebElement> find;
		 do {
		find = u.getUserName().stream().filter(v->v.getText().contains("Ebuka Osobase")).findAny();
		if(!find.isPresent()) {
			u.getNextPagination().click();
		}
		 }while(!find.isPresent());
		 executor.executeScript("arguments[0].click();", find.get());
		 executor.executeScript("arguments[0].click();", u.getUserButton().stream().filter(v->v.getText().equalsIgnoreCase("Change User Role")).findAny().get());
		
		Select popUpRoleField = new Select(u.getUserDetailsPopUpRoleField());
		popUpRoleField.selectByVisibleText(data.get("roles"));
		soft.assertEquals(u.getUserDetailsPopUpButton().getText(), "Change Role");
		executor.executeScript("arguments[0].click();", u.getUserDetailsPopUpButton());
	    
		soft.assertEquals(u.getPromptMessage().getText(), "Success\n"
				+ "User role changed successfully");
		 staleElement = false;


			  } catch(StaleElementReferenceException e){

			    staleElement = true;

			  }

			}
		soft.assertAll();
		
	}
		 
	@Test(priority=25, dataProvider="getData")
	public void ValidateUserSearch(HashMap<String, String> data) throws InterruptedException{
		//Verify user can search for a user by name
		sm = new SideMenu(driver);
		u = new Users(driver);
		soft = new SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 u.getSearchBar().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("userSearch"));
		 @SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
			wait.until(ExpectedConditions.visibilityOf(u.getClearSearchButton()));
		 long totalSearch;
		 long expectedSearch;
		 totalSearch= u.getUserName().stream().count();
		  expectedSearch = u.getUserName().stream().filter(v->v.getText().toLowerCase().contains(data.get("userSearch"))).count();
		  if(u.getActivePagination().size()>=1) {
		 do {
				if(!u.getNextPagination().getText().contains("Next")) {
					u.getNextPagination().click();
					Thread.sleep(3000);
					totalSearch += u.getUserName().stream().count();
					  expectedSearch += u.getUserName().stream().filter(v->v.getText().toLowerCase().contains(data.get("userSearch"))).count();
				}
				 }while(!u.getNextPagination().getText().contains("Next"));
		  }
		 u.getClearSearchButton().click();
		 Assert.assertEquals(totalSearch, expectedSearch);
	}
	
	@Test(priority=26, dataProvider="getData")
	public void InviteUserEmptyEmailField(HashMap<String, String> data) throws InterruptedException {
		//Verify the Invite new user button is disabled if the Email field is empty
		sm = new SideMenu(driver);
		u = new Users(driver);
		soft = new SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getUsers());
		executor.executeScript("arguments[0].click();", u.getInviteUsersButton());
		Select roleDropdown = new Select(u.getUserInvitePopUpRoleField());
		roleDropdown.selectByVisibleText(data.get("roles"));
		Assert.assertFalse(u.getPopUpInviteUserButton().isEnabled());
		executor.executeScript("arguments[0].click();", u.getPopUpCloseButton()  );
		Thread.sleep(1000);
	}
	
	@Test(priority=27, dataProvider="invalidGetData", dataProviderClass=AdminLoginTest.class)
	public void InviteUserEmptyRoleField(HashMap<String, String> data) throws InterruptedException {
		//Verify the Invite new user button is disabled if the Email field is empty
		sm = new SideMenu(driver);
		u = new Users(driver);
		soft = new SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getUsers());
		executor.executeScript("arguments[0].click();", u.getInviteUsersButton());
		u.getEmailAddressField().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("invalidEmailAddress"));
		Assert.assertFalse(u.getPopUpInviteUserButton().isEnabled());
		executor.executeScript("arguments[0].click();", u.getPopUpCloseButton());
		Thread.sleep(1000);		       
	}
	
	@Test(priority=28, dataProvider="getData")
	public void SuccessfulUserInvite(HashMap<String, String> data) throws InterruptedException {
		//Verify user can invite user successfully 
		sm = new SideMenu(driver);
		u = new Users(driver);
		soft = new SoftAssert();
		ct = new CouponTest();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", sm.getUsers());
		executor.executeScript("arguments[0].click();", u.getInviteUsersButton());
		String email = data.get("roleName")+ct.setCouponCode()+"@gmail.com";
		u.getEmailAddressField().sendKeys(Keys.chord(Keys.COMMAND, "a"), email);
		Select roleDropdown = new Select(u.getUserInvitePopUpRoleField());
		roleDropdown.selectByVisibleText(data.get("roles"));
		u.getPopUpInviteUserButton().click();
		soft.assertEquals(u.getPromptMessage().getText(), "Success\n"
				+ "Invitation sent successfully");
		u.getPopUpCloseButton().click();
		Thread.sleep(2000);
		Optional<WebElement> count = u.getUserName().stream().filter(v->v.getText().toLowerCase()
				.contains(email.toLowerCase())).findAny();
		soft.assertTrue(count.isPresent());
		soft.assertAll();
	}
	
	@Test(priority=29)
	public void ValidateDeactivateUser() throws InterruptedException {
		//Verify user can deactivate user
		sm = new SideMenu(driver);
		u = new Users(driver);
		soft = new SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 Boolean staleElement = true;
		 while(staleElement){

			  try{
		 Optional<WebElement> find;
		 do {
		find = u.getUserName().stream().filter(v->v.getText().contains("Ebuka Osobase")).findAny();
		if(!find.isPresent()) {
			u.getNextPagination().click();
			Thread.sleep(3000);
		}
		 }while(!find.isPresent());
		 executor.executeScript("arguments[0].click();", find.get());
		u.getUserButton().stream().filter(v->v.getText().equalsIgnoreCase("Deactivate User")).findAny().get().click();
		soft.assertEquals(u.getUserDetailsPopUpButton().getText(), "Yes, Deactivate User");
		executor.executeScript("arguments[0].click();", u.getUserDetailsPopUpButton());
	     staleElement = false;


				  } catch(StaleElementReferenceException e){

				    staleElement = true;

				  }

				}
		soft.assertEquals(u.getPromptMessage().getText(), "Success\n"
				+ "User deactivated successfully");
		soft.assertAll();
		
	}
	@Test(dependsOnMethods="ValidateDeactivateUser")
	public void ValidateReactivateUser() throws InterruptedException {
		//Verify user can reactivate user
		sm = new SideMenu(driver);
		u = new Users(driver);
		soft = new SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getUsers());
		 Optional<WebElement> find;
		 Boolean staleElement = true;
		 while(staleElement){

			  try{
		
		 do {
		find = u.getUserName().stream().filter(v->v.getText().contains("Ebuka Osobase")).findAny();
		if(!find.isPresent()) {
			u.getNextPagination().click();
		}
		 }while(!find.isPresent());
		 soft.assertTrue(find.get().getText().contains("inactive"));
		 executor.executeScript("arguments[0].click();", find.get());
		 Thread.sleep(3000);
		u.getUserButton().stream().filter(v->v.getText().equalsIgnoreCase("Re-activate User")).findAny().get().click();
		soft.assertEquals(u.getUserDetailsPopUpButton().getText(), "Re-activate User");
		executor.executeScript("arguments[0].click();", u.getUserDetailsPopUpButton());
	   
		soft.assertEquals(u.getPromptMessage().getText(), "Success\n"
				+ "User activated successfully");
		soft.assertTrue(find.get().getText().contains("active"));
		soft.assertAll();
		  staleElement = false;


			  } catch(StaleElementReferenceException e){

			    staleElement = true;

			  }

			}
	}
	
	@AfterClass
	public void terminate() {
		driver.close();
	}
	
	
	
	@DataProvider
	public  Object[][] getData() {
		Object[][] data1=new Object[2][];
		Map<String, String> validData = Map.of("roleName", "Testing2", "roleDescription", "Automation test", "roles", "Super Admin", 
				"userSearch", "jerry");
		Map <String, String> dp1= new HashMap <>(validData);
		data1[0]= new Object[] { dp1 };
		Map<String, String> validData1 = Map.of("roleName", "Testing3", "roleDescription", "Automation test1", "roles", "Enforcer",
				"userSearch", "george");
		Map <String, String> dp2= new HashMap <>(validData1);
		data1[1]= new Object[] { dp2 };
		return data1;
	}
}
