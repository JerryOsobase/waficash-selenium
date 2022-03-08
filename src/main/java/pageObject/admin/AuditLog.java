package pageObject.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuditLog {

public WebDriver driver;
	
	public AuditLog(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	private By selectUserField= By.xpath("//input[@type='search']");
	private By eventField= By.id("event");
	private By startDateField= By.xpath("//input[@placeholder='Start Date']");
	private By endDateField= By.xpath("//input[@placeholder='End Date']");
	private By filterButton = By.xpath("//button[text()=' Filter ']"); 
	private By exportCsvButton = By.xpath("//button[text()=' Export CSV ']");
	private By activePagination = By.xpath("//li[@class='page-item pagination-page-nav active']");
	private By nextPagination = By.xpath("//li[@class='page-item pagination-page-nav active'] //following-sibling::li");
	private By agentDetails = By.xpath("//span[@class='font-bold font-14']");
	private By auditLogHeader= By.cssSelector("h3.text-themecolor.m-b-0.m-t-0.app-title");
	private By tableHeader = By.xpath("//tr/th");
	private By activity = By.xpath("//tr/td[2]");
	private By userFieldDropdown= By.xpath("//ul[@role='listbox']/li");
	private By emptyApplicationText= By.xpath("//div[@class='text-center']/p");
	private By auditDetails= By.xpath("//div[@class='col-xs-12']");
	
	
	public WebElement getSelectUserField() {
		return driver.findElement(selectUserField);
	}
	
	public List<WebElement> getAuditDetails() {
		return driver.findElements(auditDetails);
	}
	
	public WebElement getEmptyApplicationText() {
		return driver.findElement(emptyApplicationText);
	}
	
	public List<WebElement> getUserFieldDropdown() {
		return driver.findElements(userFieldDropdown);
	}
	
	public List<WebElement> getActivity() {
		return driver.findElements(activity);
	}
	
	public List<WebElement> getTableHeader() {
		return driver.findElements(tableHeader);
	}
	
	public WebElement getAuditLogHeader() {
		return driver.findElement(auditLogHeader);
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
