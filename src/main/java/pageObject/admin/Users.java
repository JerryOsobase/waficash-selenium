package pageObject.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Users {

public WebDriver driver;
	
	public Users(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	private By userHeader= By.cssSelector("h3.card-title");
	private By roleField= By.xpath("//select[@class='form-control custom-select rounded-0']");
	private By filterButton = By.xpath("//button[text()=' Filter ']"); 
	private By manageRolesButton = By.xpath("//button[text()=' Manage Roles ']");
	private By inviteUsersButton = By.xpath("//button[text()=' Invite User ']");
	private By uploadCsvButton = By.xpath("//button[text()=' Upload CSV ']");
	private By searchBar = By.xpath("//input[@placeholder='Search using name...']");
	private By status = By.cssSelector("label[class*='font-bold mx-1']"); 
	private By userButton = By.xpath("//div[@class='col-xs-12 m-b-10'] //button");
	private By changeRoleConfirmationButton = By.xpath("//button[text()=' Change Role ']");
	private By activateConfirmationButton = By.xpath("//div[@class='col-md-12']/button");
	private By reinviteUserConfirmationButton = By.xpath("//div[@class='//div[@class='col-md-12']/button");
	private By activePagination = By.xpath("//li[@class='page-item pagination-page-nav active']");
	private By nextPagination = By.xpath("//li[@class='page-item pagination-page-nav active'] //following-sibling::li"); 
	private By popUpCloseButton = By.cssSelector("button.close"); 
	private By emailAddressField = By.xpath("//input[@placeholder='Enter Email Address']");
	private By popUpRoleField = By.xpath("//select[@class='form-control rounded-0 custom-select']");
	private By popUpInviteUserButton = By.xpath("//button[text()=' Invite New User ']");
	private By tableHeader = By.xpath("//tr/th");
	private By roles = By.xpath("//tr/td[3]");
	private By clearButton= By.xpath("//button[text()=' clear filter ']");
	private By userDetailsHeader= By.xpath("//div[@class='col-xs-12']");
	private By userName= By.xpath("//tr/td[2]");
	private By userDetailsPopUpButton= By.xpath("//tr/td[2]");
	private By PromptMessage= By.cssSelector("div.iziToast-texts");
	private By userDetailsPopUpRoleField= By.xpath("//div[@class='modal-content modal-border-radius'] //select[@class='form-control custom-select rounded-0']");
	
	
	public WebElement getUserHeader() {
		return driver.findElement(userHeader);
	}
	
	public WebElement getUserDetailsPopUpRoleField() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(userDetailsPopUpRoleField));
		return driver.findElement(userDetailsPopUpRoleField);
	}
	
	public WebElement getPromptMessage() {
		return driver.findElement(PromptMessage);
	}
	
	public WebElement getUserDetailsPopUpButton() {
		return driver.findElement(userDetailsPopUpButton);
	}
	
	public List<WebElement> getUserDetailsHeader() {
		return driver.findElements(userDetailsHeader);
	}
	
	public List<WebElement> getUserName() {
		return driver.findElements(userName);
	}
	
	public WebElement getClearButton() {
		return driver.findElement(clearButton);
	}
	
	public List<WebElement> getRoles() {
		return driver.findElements(roles);
	}
	
	public WebElement getPopUpInviteUserButton() {
		return driver.findElement(popUpInviteUserButton);
	}
	
	public List<WebElement> getTableHeader() {
		return driver.findElements(tableHeader);
	}
	
	public WebElement getPopUpRoleField() {
		return driver.findElement(popUpRoleField);
	}
	
	public WebElement getEmailAddressField() {
		return driver.findElement(emailAddressField);
	}
	
	public WebElement getPopUpCloseButton() {
		return driver.findElement(popUpCloseButton);
	}
	
	public WebElement getChangeRoleConfirmationButton() {
		return driver.findElement(changeRoleConfirmationButton);
	}
	
	public WebElement getActivateConfirmationButton() {
		return driver.findElement(activateConfirmationButton);
	}
	
	public WebElement getreinviteUserConfirmationButton() {
		return driver.findElement(reinviteUserConfirmationButton);
	}
	
	public WebElement getInviteUsersButton() {
		return driver.findElement(inviteUsersButton);
	}
	
	public WebElement getUploadCsvButton() {
		return driver.findElement(uploadCsvButton);
	}
	
	public WebElement getSearchBar() {
		return driver.findElement(searchBar);
	}
	
	public List<WebElement> getActivePagination() {
		return driver.findElements(activePagination);
	}
	
	public WebElement getNextPagination() {
		return driver.findElement(nextPagination);
	}
	
	public List<WebElement> getUserButton() {
		return driver.findElements(userButton);
	}
	
	public List<WebElement> getStatus() {
		return driver.findElements(status);
	}
	
	public WebElement getRoleField() {
		return driver.findElement(roleField);
	}
	
	public WebElement getManageRolesButton() {
		return driver.findElement(manageRolesButton);
	}
	
	
	public WebElement getFilterButton() {
		return driver.findElement(filterButton);
	}
	
	
}


