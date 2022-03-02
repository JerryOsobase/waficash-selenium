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
	private By transactionHeader= By.cssSelector("h3.card-title");
	private By selectUserField= By.xpath("//div[@class='row'] //input[@type='search']");
	private By statusField= By.id("status");
	private By startDateField= By.xpath("//input[@placeholder='Start Date']");
	private By endDateField= By.xpath("//input[@placeholder='End Date']");
	private By filterButton = By.xpath("//button[text()=' Filter ']"); 
	private By exportCsvButton = By.xpath("//button[text()=' Export CSV ']");
	private By tableHeader = By.xpath("//tr/th");
	private By activePagination = By.xpath("//li[@class='page-item pagination-page-nav active']");
	private By nextPagination = By.xpath("//li[@class='page-item pagination-page-nav active'] //following-sibling::li");
	private By requeryButton = By.xpath("//button[text()=' Re-query ']");
	private By status = By.xpath("//tr/td[2]/a");
	private By transactionId = By.xpath("//tr/td[3]/a");
	private By transactionAmountField= By.cssSelector("input[placeholder='Enter Transaction Amount']");
	private By popUpReconcileButton= By.cssSelector("div[id='reconcile-transaction-modal'] button[class*='btn']");
	private By agentFieldDropdown= By.xpath("//ul[@role='listbox']/li");
	private By PromptMessage= By.cssSelector("div.iziToast-texts");
	private By initiatedBy = By.xpath("//tr/td[8]");
	private By paymentType = By.xpath("//tr/td[5]");
	private By emptyTransactionText= By.xpath("//div[@class='text-center']/p");
	private By clearFilterButton = By.xpath("//button[text()=' clear filter ']");
	private By tableFooterText= By.xpath("//div[@class='p-30 test font-medium text-muted']");
	private By paymentStatus = By.xpath("//tr/td[4]");
	private By userDetailsHeader = By.xpath("//div[@class='col-md-4'] //span[@class='font-bold font-14']");
	private By transactionDetailsHeader = By.xpath("//div[@class='col-md-8'] //span[@class='font-bold font-14']");
	private By transactionDetails= By.cssSelector("div[class='col-md-8'] div[class='m-b-10']");
	
	
	public WebElement getTransactionIdField() {
		return driver.findElement(transactionIdField);
	}
	
	public List<WebElement> getTransactionDetailsHeader() {
		return driver.findElements(transactionDetailsHeader);
	}
	
	public List<WebElement> getTransactionDetails() {
		return driver.findElements(transactionDetails);
	}
	
	public List<WebElement> getUserDetailsHeader() {
		return driver.findElements(userDetailsHeader);
	}
	
	public List<WebElement> getPaymentStatus() {
		return driver.findElements(paymentStatus);
	}
	
	public List<WebElement> getPaymentType() {
		return driver.findElements(paymentType);
	}
	
	public WebElement getTableFooterText() {
		return driver.findElement(tableFooterText);
	}
	
	public WebElement getClearFilterButton() {
		return driver.findElement(clearFilterButton);
	}
	
	public WebElement getEmptyTransactionText() {
		return driver.findElement(emptyTransactionText);
	}
	
	public List<WebElement> getInitiatedBy() {
		return driver.findElements(initiatedBy);
	}
	
	public List<WebElement> getAgentFieldDropdown() {
		return driver.findElements(agentFieldDropdown);
	}
	
	public WebElement getPromptMessage() {
		return driver.findElement(PromptMessage);
	}
	
	public WebElement getPopUpReconcileButton() {
		return driver.findElement(popUpReconcileButton);
	}
	
	public WebElement getTransactionAmountField() {
		return driver.findElement(transactionAmountField);
	}
	
	public List<WebElement> getTransactionId() {
		return driver.findElements(transactionId);
	}
	
	public List<WebElement> getStatus() {
		return driver.findElements(status);
	}
	
	public List<WebElement> getTransactionHeader() {
		return driver.findElements(transactionHeader);
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

