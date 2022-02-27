package pageObject.admin;

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
	private By reportText= By.xpath("//small[@class='col-12 text-muted mb-5']"); 
	private By generatedReportButton= By.xpath("//button[text()=' Generate Report ']");
	private By filterButton = By.xpath("//button[text()=' Filter ']");
	private By tableHeader = By.xpath("//thead/tr[1]/th");
	private By backButton = By.xpath("//button[text()=' Back ']");
	private By paginationNextButton = By.xpath("//*[name()='svg' and @viewBox='0 0 12 13']//*[name()='path' and @fill='#F5B600']");
	
	public WebElement getStandardReportTab() {
		return driver.findElement(standardReportTab);
	}
	
	public WebElement getPaginationNextButton() {
		return driver.findElement(paginationNextButton);
	}
	
	public WebElement getBackButton() {
		return driver.findElement(backButton);
	}
	
	public WebElement getTableHeader() {
		return driver.findElement(tableHeader);
	}
	
	public WebElement getFilterButton() {
		return driver.findElement(filterButton);
	}
	
	public WebElement getGeneratedReportButton() {
		return driver.findElement(generatedReportButton);
	}
	
	public WebElement getReportText() {
		return driver.findElement(reportText);
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
