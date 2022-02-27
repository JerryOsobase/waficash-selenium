package pageObject.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Transactions {
public WebDriver driver;
	
	public Transactions(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	private By transactionIdField= By.xpath("//input[@placeholder='Enter Transaction ID']"); 
	private By reconcileButton= By.xpath("//button[text()=' Reconcile Transaction ']");
	private By selectUserField= By.xpath("//input[@placeholder='Type to search for users...']");
	private By statusField= By.id("status");
	private By startDateField= By.xpath("//input[@placeholder='Start Date']");
	private By endDateField= By.xpath("//input[@placeholder='End Date']");
	private By filterButton = By.xpath("//button[text()=' Filter ']"); 
	private By exportCsvButton = By.xpath("//button[text()=' Export CSV ']");
	private By tableHeader = By.xpath("//tr/th");
	private By activePagination = By.xpath("//li[@class='page-item pagination-page-nav active']");
	private By nextPagination = By.xpath("//li[@class='page-item pagination-page-nav active'] //following-sibling::li");
	private By requeryButton = By.xpath("//button[text()=' Re-query ']");
	
	
	public WebElement getTransactionIdField() {
		return driver.findElement(transactionIdField);
	}
	
	public WebElement getRequeryButton() {
		return driver.findElement(requeryButton);
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
	
	public WebElement getFilterButton() {
		return driver.findElement(filterButton);
	}
	
	public WebElement getExportCsvButton() {
		return driver.findElement(exportCsvButton);
	}
	
	public WebElement getStartDateField() {
		return driver.findElement(startDateField);
	}
	
	public WebElement getEndDateField() {
		return driver.findElement(endDateField);
	}
	
	public WebElement getStatusField() {
		return driver.findElement(statusField);
	}
	
	public WebElement getSelectUserField() {
		return driver.findElement(selectUserField);
	}
	
	public WebElement getReconcileButton() {
		return driver.findElement(reconcileButton);
	}
}

