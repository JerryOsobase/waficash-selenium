package pageObject.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Identity {
public WebDriver driver;
	
	public Identity(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	private By identityHeader= By.xpath("//h3[@class='text-themecolor m-b-0 m-t-0 app-title']");
	private By statusField= By.xpath("//*[@id='filter-report-form']/div/div[1]/div/select");
	private By startDateField= By.xpath("//input[@placeholder='Start Date']");
	private By endDateField= By.xpath("//input[@placeholder='End Date']");
	private By lgaField= By.xpath("//*[@id='filter-report-form']/div/div[4]/div/select");
	private By filterButton = By.xpath("//button[text()=' Filter ']"); 
	private By exportCsvButton = By.xpath("//button[text()=' Export CSV ']");
	private By status = By.cssSelector("td.has-tooltip a");
	private By tableHeader = By.xpath("//tr/th");
	private By activePagination = By.xpath("//li[@class='page-item pagination-page-nav active']");
	private By nextPagination = By.xpath("//li[@class='page-item pagination-page-nav active'] //following-sibling::li");
	private By lga = By.xpath("//tr/td[6]");
	private By tableFooterText= By.xpath("//div[@class='p-30 test font-medium text-muted']");
	private By emptyApplicationText= By.xpath("//div[@class='text-center']/p");
	private By clearButton= By.xpath("//button[text()=' clear filter ']"); 
	private By PromptMessage= By.cssSelector("div.iziToast-texts");
	
	
	
	public WebElement getIdentityHeader() {
		return driver.findElement(identityHeader);
	}
	
	public WebElement getPromptMessage() {
		return driver.findElement(PromptMessage);
	}
	
	public WebElement getClearButton() {
		return driver.findElement(clearButton);
	}
	
	public WebElement getEmptyApplicationText() {
		return driver.findElement(emptyApplicationText);
	}
	
	public WebElement getTableFooterText() {
		return driver.findElement(tableFooterText);
	}
	
	public List<WebElement> getLga() {
		return driver.findElements(lga);
	}
	
	public List<WebElement> getActivePagination() {
		return driver.findElements(activePagination);
	}
	
	public WebElement getNextPagination() {
		return driver.findElement(nextPagination);
	}
	
	public List<WebElement> getTableHeader() {
		return driver.findElements(tableHeader);
	}
	
	public List<WebElement> getStatus() {
		return driver.findElements(status);
	}
	
	public WebElement getStatusField() {
		return driver.findElement(statusField);
	}
	
	public WebElement getExportCsvButton() {
		return driver.findElement(exportCsvButton);
	}
	
	public WebElement getStartDateField() {
		return driver.findElement(startDateField);
	}
	
	public WebElement getFilterButton() {
		return driver.findElement(filterButton);
	}
	
	public WebElement getLgaField() {
		return driver.findElement(lgaField);
	}
	
	public WebElement getEndDateField() {
		return driver.findElement(endDateField);
	}
}
