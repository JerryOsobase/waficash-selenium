package pageObject.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Permit {
	public WebDriver driver;
	
	public Permit(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	private By statusField= By.xpath("//*[@id='filter-report-form']/div/div[1]/div/select");
	private By filterButton = By.xpath("//button[text()=' Filter ']");
	private By searchBar = By.xpath("//input[@placeholder='Search using name']");
	private By addPermitButton = By.xpath("//button[text()=' Add Permit ']");
	private By setPermitButton = By.xpath("//div[@class='d-flex align-items-center'] //button[text()=' Set Permit Fee ']");
	private By permitFeeField = By.xpath("//input[@placeholder='Enter Permit Fee']");
	private By addPermitField = By.xpath("//input[@placeholder='Enter Permit Name']");
	private By addPermitConfirmationButton = By.xpath("//button[text()=' Add New Permit ']"); 
	private By setPermitConfirmationButton = By.xpath("//div[@class='col-sm-12'] //button[text()=' Set Permit Fee ']"); 
	private By status = By.xpath("//tr/td[2]");
	private By tableHeader = By.xpath("//tr/th"); 
	private By statusButton = By.xpath("//tr/td[4]/button");
	private By confirmationMessage= By.id("swal2-content");
	private By confirmationHeader= By.id("swal2-title"); 
	private By popUpConfirmButton = By.xpath("//button[@class='swal2-confirm btn btn-warning mr-3']");
	private By name = By.xpath("//tr/td[3]");
	private By activePagination = By.xpath("//li[@class='page-item pagination-page-nav active']");
	private By nextPagination = By.xpath("//li[@class='page-item pagination-page-nav active'] //following-sibling::li");
	private By emptyPermitText= By.xpath("//div[@class='text-center']/p");
	private By clearButton= By.xpath("//button[contains(.,'clear')]");
	private By addPermitConfirmationMessage= By.cssSelector("div[class*='col-xs-12 alert alert-success-light font-normal font-14 font-bold rounded-0 text-center']");
	private By popUpCancelButton= By.cssSelector("button[class='close']"); 
	private By currentPermitPrice= By.xpath("//form[@id='set-permit-fee-form']/div[1]");
	private By PromptMessage= By.cssSelector("div.iziToast-texts");
	
	public WebElement getStatusField() {
		return driver.findElement(statusField);
	}
	
	public WebElement getSetPermitConfirmationButton() {
		return driver.findElement(setPermitConfirmationButton);
	}
	
	public WebElement getPromptMessage() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,15) ;
		wait.until(ExpectedConditions.elementToBeClickable(PromptMessage));
		return driver.findElement(PromptMessage);
	}
	
	public WebElement getCurrentPermitPrice() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.elementToBeClickable(currentPermitPrice));
		return driver.findElement(currentPermitPrice);
	}
	
	public WebElement getPopUpCancelButton() {
		return driver.findElement(popUpCancelButton);
	}
	
	public WebElement getAddPermitConfirmationMessage() {
		return driver.findElement(addPermitConfirmationMessage);
	}
	
	public WebElement getEmptyPermitText() {
		return driver.findElement(emptyPermitText);
	}
	
	public WebElement getClearButton() {
		return driver.findElement(clearButton);
	}
	
	public List<WebElement> getActivePagination() {
		return driver.findElements(activePagination);
	}
	
	public WebElement getNextPagination() {
		return driver.findElement(nextPagination);
	}
	
	public List<WebElement> getName() {
		return driver.findElements(name);
	}
	
	public WebElement getPopUpConfirmButton() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(popUpConfirmButton));
		return driver.findElement(popUpConfirmButton);
	}
	
	public WebElement getConfirmationHeader() {
		return driver.findElement(confirmationHeader);
	}
	
	public WebElement getConfirmationMessage() {
		return driver.findElement(confirmationMessage);
	}
	
	public List<WebElement> getStatusButton() {
		return driver.findElements(statusButton);
	}
	
	public List<WebElement> getTableHeader() {
		return driver.findElements(tableHeader);
	}
	
	public List<WebElement> getStatus() {
		return driver.findElements(status);
	}
	
	public WebElement getAddPermitConfirmationButton() {
		return driver.findElement(addPermitConfirmationButton);
	}
	
	public WebElement getAddPermitField() {
		return driver.findElement(addPermitField);
	}
	
	public WebElement getPermitFeeField() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.elementToBeClickable(permitFeeField));
		return driver.findElement(permitFeeField);
	}
	
	public WebElement getAddPermitButton() {
		return driver.findElement(addPermitButton);
	}
	
	public WebElement getSetPermitButton() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.elementToBeClickable(setPermitButton));
		return driver.findElement(setPermitButton);
	}
	
	public WebElement getSearchBar() {
		return driver.findElement(searchBar);
	}
	
	public WebElement getFilterButton() {
		return driver.findElement(filterButton);
	}
	
}
