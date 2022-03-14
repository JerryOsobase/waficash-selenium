package pageObject.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Reports {
public WebDriver driver;
	
	public Reports(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
    private By standardReportTab = By.xpath("//span[text()='Standard Report']"); 
    private By inspectionReportTab = By.xpath("//span[text()='Inspection Report']");
    private By categoryField = By.xpath("//label[@for='first-name'] //following-sibling::select");
    private By mailCheckbox = By.id("checkbox-signin");
    private By startDateField= By.xpath("//input[@placeholder='Start Date']");
	private By endDateField= By.xpath("//input[@placeholder='End Date']"); 
	private By generatedReportButton= By.xpath("//button[text()=' Generate Report ']");
	private By filterButton = By.xpath("//button[text()=' Filter ']");
	private By tableHeader = By.xpath("//thead/tr[1]/th");
	private By backButton = By.xpath("//button[text()=' Back ']");
	private By paginationNextButton = By.xpath("//*[name()='svg' and @viewBox='0 0 12 13']//*[name()='path' and @fill='#F5B600']");
	private By pageHeader= By.xpath("//h3[@class='text-themecolor m-b-0 m-t-0 app-title']"); 
	private By pageTab= By.cssSelector("ul[class*='customtab'] .nav-item");
	private By viewPhotos = By.cssSelector("span[class='view-photo']");
	private By photos = By.xpath("//div[@class='wrapper'] //img");
	private By agentVisted = By.xpath("//tbody/tr/th[2]");
	private By inspectionReportAgentDetailsHeader = By.xpath("//div/h6[1]");
	private By inspectionBackButton = By.cssSelector("div[class*='pointer-cursor']");
	
	public WebElement getStandardReportTab() {
		return driver.findElement(standardReportTab);
	}
	
	public WebElement getInspectionBackButton() {
		return driver.findElement(inspectionBackButton);
	}
	
	public List<WebElement> getInspectionReportAgentDetailsHeader() {
		return driver.findElements(inspectionReportAgentDetailsHeader);
		
	}
	
	public List<WebElement> getAgentVisted() {
		return driver.findElements(agentVisted);
	}
	
	public List<WebElement> getPhotos() {
		return driver.findElements(photos);
		
	}
	
	public List<WebElement> getViewPhotos() {
		return driver.findElements(viewPhotos);
		
	}
	
	public WebElement getPageHeader() {
		return driver.findElement(pageHeader);
	}
	
	public List<WebElement> getPageTab() {
		return driver.findElements(pageTab);
	}
	
	public WebElement getPaginationNextButton() {
		return driver.findElement(paginationNextButton);
	}
	
	public WebElement getBackButton() {
		return driver.findElement(backButton);
	}
	
	public List<WebElement> getTableHeader() {
		return driver.findElements(tableHeader);
	}
	
	public WebElement getFilterButton() {
		return driver.findElement(filterButton);
	}
	
	public WebElement getGeneratedReportButton() {
		return driver.findElement(generatedReportButton);
	}
	
	public WebElement getStartDateField() {
		return driver.findElement(startDateField);
	}
	
	public WebElement getEndDateField() {
		return driver.findElement(endDateField);
	}
	
	public WebElement getMailCheckbox() {
		return driver.findElement(mailCheckbox);
	}
	
	public WebElement getCategoryField() {
		return driver.findElement(categoryField);
	}
	
	public WebElement getInspectionReportTab() {
		return driver.findElement(inspectionReportTab);
	}
	
}
