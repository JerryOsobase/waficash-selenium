package pageObject.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PrintLicense {

public WebDriver driver;
	
	public PrintLicense(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	private By printLicenseHeader= By.xpath("//h3[@class='text-themecolor m-b-0 m-t-0 app-title']"); 
	private By reprintTab= By.cssSelector("a[href*='reprint']");
	private By pendingTab= By.cssSelector("a[href*='pending']"); 
	private By checkbox= By.cssSelector("//tbody //input");
	private By selectAllCheckbox= By.xpath("//input[@type='checkbox']");
	private By activePagination = By.xpath("//li[@class='page-item pagination-page-nav active']");
	private By nextPagination = By.xpath("//li[@class='page-item pagination-page-nav active'] //following-sibling::li");
	private By status = By.xpath("//tr/td[3]");
	private By printIdButton = By.xpath("//button[text()=' Print ID ']");
	private By printBulkButton = By.xpath("//button[text()=' Print Bulk ']");
	
	
	public WebElement getPrintLicenseHeader() {
		return driver.findElement(printLicenseHeader);
	}
	
	public WebElement getPrintBulkButton() {
		return driver.findElement(printBulkButton);
	}
	
	public WebElement getPrintIdButton() {
		return driver.findElement(printIdButton);
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
	
	public WebElement getPendingTab() {
		return driver.findElement(pendingTab);
	}
	
	public WebElement getReprintTab() {
		return driver.findElement(reprintTab);
	}
}
