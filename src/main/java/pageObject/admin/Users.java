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
	private By userButton = By.cssSelector("div[class*='col-xs-12 m-b-10'] button");
	private By changeRoleConfirmationButton = By.xpath("//button[text()=' Change Role ']");
	private By activateConfirmationButton = By.xpath("//div[@class='col-md-12']/button");
	private By reinviteUserConfirmationButton = By.xpath("//div[@class='//div[@class='col-md-12']/button");
	private By activePagination = By.xpath("//li[@class='page-item pagination-page-nav active']");
	private By nextPagination = By.xpath("//li[@class='page-item pagination-page-nav active'] //following-sibling::li"); 
	private By popUpCloseButton = By.cssSelector("div[id*='add-single-user-modal'] button.close"); 
	private By emailAddressField = By.cssSelector("div[id*='add-single-user-modal'] input[placeholder='Enter Email Address']");
	private By popUpInviteUserButton = By.xpath("//button[text()=' Invite New User ']");
	private By tableHeader = By.xpath("//tr/th");
	private By roles = By.xpath("//tr/td[3]");
	private By clearFilterButton= By.xpath("//button[text()=' clear filter ']");
	private By clearSearchButton= By.xpath("//a[text()='clear search']");
	private By userDetailsHeader= By.xpath("//div[@class='col-xs-12']");
	private By userName= By.xpath("//tr/td[2]");
	private By userDetailsPopUpButton= By.cssSelector("div[id='multi-user-modal'] button[class*='btn']");
	private By PromptMessage= By.cssSelector("div.iziToast-texts");
	private By searchText= By.cssSelector("div[class*='p-t-10'] span");
	private By userDetailsPopUpRoleField= By.cssSelector("div[id*='multi-user-modal'] select");
	private By userInvitePopUpRoleField= By.cssSelector("div[id*='add-single-user-modal'] select");
	
	public WebElement getUserHeader() {
		return driver.findElement(userHeader);
	}
	
	public WebElement getSearchText() {
		return driver.findElement(searchText);
	}
	
	public WebElement getUserInvitePopUpRoleField() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.visibilityOfElementLocated(userInvitePopUpRoleField));
		return driver.findElement(userInvitePopUpRoleField);
	}
	
	public WebElement getUserDetailsPopUpRoleField() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.visibilityOfElementLocated(userDetailsPopUpRoleField));
		return driver.findElement(userDetailsPopUpRoleField);
	}
	
	public WebElement getPromptMessage() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.visibilityOfElementLocated(PromptMessage));
		return driver.findElement(PromptMessage);
	}
	
	public WebElement getUserDetailsPopUpButton() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.visibilityOfElementLocated(userDetailsPopUpButton));
		return driver.findElement(userDetailsPopUpButton);
	}
	
	public List<WebElement> getUserDetailsHeader() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.visibilityOfElementLocated(userDetailsHeader));
		return driver.findElements(userDetailsHeader);
	}
	
	public List<WebElement> getUserName() {
		return driver.findElements(userName);
	}
	
	public WebElement getClearFilterButton() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.visibilityOfElementLocated(clearFilterButton));
		return driver.findElement(clearFilterButton);
	}
	
	public WebElement getClearSearchButton() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(clearSearchButton));
		return driver.findElement(clearSearchButton);
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
	
	
	public WebElement getEmailAddressField() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailAddressField));
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
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.visibilityOfElementLocated(inviteUsersButton));
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
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.visibilityOfElementLocated(userButton));
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


