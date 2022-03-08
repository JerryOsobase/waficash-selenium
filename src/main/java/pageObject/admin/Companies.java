package pageObject.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Companies {

public WebDriver driver;
	
	public Companies(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	private By companiesHeader= By.cssSelector("h3.card-title");
	private By inviteCompanyButton = By.xpath("//button[text()=' Invite Company ']"); 
	private By tableHeader = By.xpath("//tr/th");
	private By activePagination = By.xpath("//li[@class='page-item pagination-page-nav active']");
	private By nextPagination = By.xpath("//li[@class='page-item pagination-page-nav active'] //following-sibling::li");
	private By emailAddressField = By.xpath("//input[@placeholder='Enter Email Address']");
	private By businessNameField = By.xpath("//input[@placeholder='Enter Business Name']");
	private By tradeNameField = By.xpath("//input[@placeholder='Enter Trade Name']");
	private By businessCategoryField = By.xpath("//label[@for='category_id']/following-sibling::select");
	private By adminFirstNameField = By.xpath("//input[@placeholder='Enter Admin First Name']");
	private By adminLastNameField = By.xpath("//input[@placeholder='Enter Admin Last Name']");
	private By phoneNumberField = By.xpath("//input[@placeholder='Enter Phone Number']");
	private By inviteNewCompanyButton = By.xpath("//button[text()=' Invite New Company ']");
	private By popUpCloseButton = By.cssSelector("div[id='add-company-modal'] button.close"); 
	private By status = By.xpath("//tr/td[6]"); 
	private By action = By.xpath("//tr/td[7]");
	private By popUpFieldLabel = By.cssSelector("label[class*='form-label']");
	private By PromptMessage= By.cssSelector("div.iziToast-texts");
	private By companyName = By.xpath("//tr/td[2]"); 
	
	
	public WebElement getCompaniesHeader() {
		return driver.findElement(companiesHeader);
	}
	
	public List<WebElement> getCompanyName() {
		return driver.findElements(companyName);
	}
	
	public WebElement getPromptMessage() {
		return driver.findElement(PromptMessage);
	}
	
	public List<WebElement> getPopUpFieldLabel() {
		return driver.findElements(popUpFieldLabel);
	}
	
	public List<WebElement> getActionButton() {
		return driver.findElements(action);
	}
	
	public List<WebElement> getStatus() {
		return driver.findElements(status);
	}
	
	public WebElement getPopUpCloseButton() {
		return driver.findElement(popUpCloseButton);
	}
	
	public WebElement getInviteNewCompanyButton() {
		return driver.findElement(inviteNewCompanyButton);
	}
	
	public WebElement getAdminLastNameField() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(adminLastNameField));
		return driver.findElement(adminLastNameField);
	}
	
	public WebElement getPhoneNumberField() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(phoneNumberField));
		return driver.findElement(phoneNumberField);
	}
	
	public WebElement getBusinessNameField() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(businessNameField));
		return driver.findElement(businessNameField);
	}
	
	public WebElement getAdminFirstNameField() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(adminFirstNameField));
		return driver.findElement(adminFirstNameField);
	}
	
	public WebElement getBusinessCategoryField() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(businessCategoryField));
		return driver.findElement(businessCategoryField);
	}
	
	public WebElement getTradeNameField() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(tradeNameField));
		return driver.findElement(tradeNameField);
	}
	
	public WebElement getEmailAddressField() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(emailAddressField));
		return driver.findElement(emailAddressField);
	}
	
	public WebElement getActivePagination() {
		return driver.findElement(activePagination);
	}
	
	public WebElement getNextPagination() {
		return driver.findElement(nextPagination);
	}
	
	public List<WebElement> getTableHeader() {
		return driver.findElements(tableHeader);
	}
	
	public WebElement getInviteCompanyButton() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.elementToBeClickable(inviteCompanyButton));
		return driver.findElement(inviteCompanyButton);
	}
	
	
	
	
}


