package pageObject.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuditLog {

public WebDriver driver;
	
	public AuditLog(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	private By selectUserField= By.xpath("//input[@placeholder='Type to search for users...']");
	private By eventField= By.id("id");
	private By startDateField= By.xpath("//input[@placeholder='Start Date']");
	private By endDateField= By.xpath("//input[@placeholder='End Date']");
	private By filterButton = By.xpath("//button[text()=' Filter ']"); 
	private By exportCsvButton = By.xpath("//button[text()=' Export CSV ']");
	private By activePagination = By.xpath("//li[@class='page-item pagination-page-nav active']");
	private By nextPagination = By.xpath("//li[@class='page-item pagination-page-nav active'] //following-sibling::li");
	private By agentDetails = By.xpath("//span[@class='font-bold font-14']");
	
	public WebElement getSelectUserField() {
		return driver.findElement(selectUserField);
	}
	
	public WebElement getAgentDetails() {
		return driver.findElement(agentDetails);
	}
	
	public WebElement getActivePagination() {
		return driver.findElement(activePagination);
	}
	
	public WebElement getNextPagination() {
		return driver.findElement(nextPagination);
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
	
	public WebElement getEventField() {
		return driver.findElement(eventField);
	}
}
