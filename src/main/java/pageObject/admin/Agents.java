package pageObject.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Agents {
public WebDriver driver;
	
	public Agents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	private By agentHeader= By.cssSelector("h3.card-title");
	private By permitSearchBar= By.xpath("//input[@placeholder=\"Enter Agent's LSLB Permit Number\"]");
	private By statusField= By.xpath("//*[@id='filter-report-form']/div/div[1]/div/select");
	private By startDateField= By.xpath("//input[@placeholder='Start Date']");
	private By endDateField= By.xpath("//input[@placeholder='End Date']");
	private By lgaField= By.xpath("//*[@id='filter-report-form']/div/div[4]/div/select");
	private By fieldLabel= By.xpath("//div[@class='form-group m-b-5']");
	private By operatorField= By.xpath("//*[@id='filter-report-form']/div/div[5]/div/select");
	private By permitType= By.xpath("//*[@id='filter-report-form']/div/div[6]/div/select");
	private By filterButton = By.xpath("//button[text()=' Filter ']"); 
	private By clearFilterButton = By.xpath("//button[text()=' clear filter ']"); 
	private By clearSearchButton = By.xpath("//button[text()=' clear search ']"); 
	private By exportCsvButton = By.xpath("//button[text()=' Export CSV ']");
	private By status = By.xpath("//td[@class=' has-tooltip']/a");
	private By filterSearchText = By.cssSelector("span.p-r-10");
	private By tableHeader = By.xpath("//tr/th");
	private By activePagination = By.xpath("//li[@class='page-item pagination-page-nav active']");
	private By nextPagination = By.xpath("//li[@class='page-item pagination-page-nav active'] //following-sibling::li");
	private By searchBar = By.xpath("//input[@placeholder='Search using name, email address or LSLB Permit Number...']");
	private By searchBar1 = By.xpath("//div[@class='m-t-15'] //div[6]/span");
	private By searchBar2 = By.xpath("//div[@class='m-t-15'] //div[13]/span");
	private By gurantor = By.cssSelector("a[href*='guarantor']");
	
	
	public WebElement gurantor() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.elementToBeClickable(gurantor));
		return driver.findElement(gurantor);
	}
	
	public WebElement getSearchBar() {
		return driver.findElement(searchBar);
	}
	
	public WebElement searchBar2() {
		return driver.findElement(searchBar2);
	}
	
	
	public WebElement searchBar1() {
		return driver.findElement(searchBar1);
	}
	
	
	public WebElement getClearFilterButton() {
		return driver.findElement(clearFilterButton);
	}
	
	public WebElement getClearSearchButton() {
		return driver.findElement(clearSearchButton);
	}
	
	public WebElement getFilterSearchText() {
		return driver.findElement(filterSearchText);
	}
	
	public WebElement getAgentHeader() {
		return driver.findElement(agentHeader);
	}
	
	public WebElement getPermitType() {
		return driver.findElement(permitType);
	}
	
	public WebElement getOperatorField() {
		return driver.findElement(operatorField);
	}
	
	public List<WebElement> getFieldLabel() {
		return driver.findElements(fieldLabel);
	}
	
	public WebElement getPermitSearchBar() {
		return driver.findElement(permitSearchBar);
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
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.elementToBeClickable(status));
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
