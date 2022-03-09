package pageObject.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PrintLicense {

public WebDriver driver;
	
	public PrintLicense(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	
	private By printLicenseHeader= By.xpath("//h3[@class='text-themecolor m-b-0 m-t-0 app-title']"); 
	private By previouslyPrintedTab= By.cssSelector("a[href*='reprint']");
	private By pendingTab= By.cssSelector("a[href*='pending']"); 
	private By checkbox= By.xpath("//tbody //input");
	private By selectAllCheckbox= By.xpath("//input[@type='checkbox']");
	private By activePagination = By.xpath("//li[@class='page-item pagination-page-nav active']");
	private By nextPagination = By.xpath("//li[@class='page-item pagination-page-nav active'] //following-sibling::li");
	private By status = By.xpath("//tr/td[3]");
	private By permitId = By.xpath("//tr/td[6]");
	private By printIdButton = By.xpath("//button[text()=' Print ID ']");
	private By printBulkButton = By.xpath("//button[text()=' Print Bulk ']");
	private By searchField = By.xpath("//input[@placeholder='Search using name, email address or LSLB Permit Number...']");
	private By pageTab= By.cssSelector("ul[class*='customtab'] .nav-item");
	private By tableHeader = By.xpath("//tr/th");
	private By emptyApplicationText= By.xpath("//div[@class='text-center']/p");
	
	
	
	public WebElement getPrintLicenseHeader() {
		return driver.findElement(printLicenseHeader);
	}
	
	public List<WebElement> getPermitId() {
		return driver.findElements(permitId);
	}
	
	public List<WebElement> getTableHeader() {
		return driver.findElements(tableHeader);
	}
	
	public List<WebElement> getPageTab() {
		return driver.findElements(pageTab);
	}
	
	public WebElement getSearchField() {
		return driver.findElement(searchField);
	}
	
	public WebElement getPrintBulkButton() {
		return driver.findElement(printBulkButton);
	}
	
	public List<WebElement> getPrintIdButton() {
		return driver.findElements(printIdButton);
	}
	
	public List<WebElement> getStatus() {
		return driver.findElements(status);
	}
	
	public WebElement getActivePagination() {
		return driver.findElement(activePagination);
	}
	
	public WebElement getNextPagination() {
		return driver.findElement(nextPagination);
	}
	
	public WebElement getSelectAllCheckbox() {
		return driver.findElement(selectAllCheckbox);
	}
	
	public List<WebElement> getCheckbox() {
		return driver.findElements(checkbox);
	}
	
	public WebElement getEmptyApplicationText() {
		return driver.findElement(emptyApplicationText);
	}
	
	public Boolean getPendingTab() {
		 driver.findElement(pendingTab).click();
			@SuppressWarnings("deprecation")
			WebDriverWait wait = new WebDriverWait(driver,10) ;
			return	wait.until(ExpectedConditions.attributeContains(pendingTab, "class", "active"));
	}
	
	public Boolean getPreviouslyPrintedTab() {
		 driver.findElement(previouslyPrintedTab).click();
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		return	wait.until(ExpectedConditions.attributeContains(previouslyPrintedTab, "class", "active"));
	}
}
