package pageObject.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManageRoles {

public WebDriver driver;
	
	public ManageRoles(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	private By roles= By.xpath("//a[@role='tab']"); 
	private By assignedUsersLink= By.xpath("//a[@class='transition fade-link pointer has-tooltip']"); 
	private By assignedUsersList= By.xpath("//div[@class='modal-body'] //li");
	private By popUpCloseButton = By.cssSelector("button.close"); 
	private By addCustomRoleButton= By.xpath("//div[@class='p-2']/button");
	private By customRoleFormHeader= By.xpath("//h4[text()=' Create Custom Role ']"); 
	private By roleNameField= By.xpath("//input[@placeholder='Enter Role Name']"); 
	private By roleDescriptionField= By.xpath("//input[@placeholder='Enter Role Description']"); 
	private By checkbox= By.xpath("//div[@class='form-group m-b-10 checkbox checkbox-success']"); 
	private By createBoxButton= By.xpath("//button[text()=' Create Role ']");
	private By assignedRoleList= By.xpath("//li[@class='list-group-item font-14 p-l-0 text-capitalize']"); 
	private By roleHeader= By.xpath("//li/p");
	private By pageHeader= By.xpath("//h3[@class='text-themecolor m-b-0 m-t-0 app-title']");
	private By roleList= By.xpath("//ul[@role='tablist']/li");
	private By PromptMessage= By.cssSelector("div.iziToast-texts");
	private By roleAssignedMembers= By.xpath("//p[@class='font-12 text-muted']");
	private By roleAccessHeader= By.cssSelector("li[class*='list-group-item-success']");
	private By assignedMembersFormHeader= By.xpath("//*[@id=\"view-all-role-users-modal\"] //div[1]/h4");
	private By assignedMembersFormCancelButton= By.xpath("//*[@id=\"view-all-role-users-modal\"] //div[1]/button");
	private By editButton= By.xpath("//button[text()='Edit ']");
	private By deleteButton= By.xpath("//button[text()='Delete ']");
	private By roleMemberText= By.xpath("//div[@class='col-sm-12']/p[2]");
	private By popUpDeleteText= By.xpath("//div[@class='modal-body p-b-0']/div");
	private By popUpDeleteButton= By.xpath("//button[text()=' Yes, Delete Role ']");
	
	
	public WebElement getPopUpDeleteText() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(popUpDeleteButton));
		return driver.findElement(popUpDeleteText);
	}
	
	public WebElement getPopUpDeleteButton() {
		return driver.findElement(popUpDeleteButton);
	}
	
	public WebElement getRoles() {
		return driver.findElement(roles);
	}
	
	public WebElement getRoleMemberText() {
		return driver.findElement(roleMemberText);
	}
	
	public List<WebElement> getEditButton() {
		return driver.findElements(editButton);
	}
	
	public List<WebElement> getDeleteButton() {
		return driver.findElements(deleteButton);
	}
	
	public WebElement getAssignedMembersFormCancelButton() {
		return driver.findElement(assignedMembersFormCancelButton);
	}
	
	public WebElement getAssignedMembersFormHeader() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.elementToBeClickable(assignedMembersFormHeader));
		return driver.findElement(assignedMembersFormHeader);
	}
	
	public WebElement getRoleAssignedMembers() {
		return driver.findElement(roleAssignedMembers);
	}
	
	public WebElement getRoleAccessHeader() {
		return driver.findElement(roleAccessHeader);
	}
	
	public WebElement getPromptMessage() {
		return driver.findElement(PromptMessage);
	}
	
	public List<WebElement> getRoleList() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.elementToBeClickable(roleList));
		return driver.findElements(roleList);
	}
	
	public WebElement getPageHeader() {
		return driver.findElement(pageHeader);
	}
	
	public List<WebElement> getRoleHeader() {
		return driver.findElements(roleHeader);
	}
	
	public List<WebElement> getAssignedRoleList() {
		return driver.findElements(assignedRoleList);
	}
	
	public WebElement getCreateBoxButton() {
		return driver.findElement(createBoxButton);
	}
	
	public List<WebElement> getCheckbox() {
		return driver.findElements(checkbox);
	}
	
	public WebElement getRoleDescriptionField() {
		return driver.findElement(roleDescriptionField);
	}
	
	public WebElement getRoleNameField() {
		return driver.findElement(roleNameField);
	}
	
	public WebElement getCustomRoleFormHeader() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.elementToBeClickable(customRoleFormHeader));
		return driver.findElement(customRoleFormHeader);
	}
	
	public WebElement getAddCustomRoleButton() {
		return driver.findElement(addCustomRoleButton);
	}
	
	public WebElement getPopUpCloseButton() {
		return driver.findElement(popUpCloseButton);
	}
	
	public List<WebElement> getAssignedUsersLink() {
		return driver.findElements(assignedUsersLink);
	}
	
	public List<WebElement> getAssignedUsersList() {
		return driver.findElements(assignedUsersList);
	}
}
