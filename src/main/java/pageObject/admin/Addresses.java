package pageObject.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Addresses {
		public WebDriver driver;
			
			public Addresses(WebDriver driver) {
				// TODO Auto-generated constructor stub
				this.driver=driver;
			}
			
			private By addressHeader= By.xpath("//h3[@class='text-themecolor m-b-0 m-t-0 app-title']");
			private By statusField= By.xpath("//*[@id='filter-report-form']/div/div[1]/div/select");
			private By startDateField= By.xpath("//input[@placeholder='Start Date']");
			private By endDateField= By.xpath("//input[@placeholder='End Date']");
			private By lgaField= By.xpath("//*[@id='filter-report-form']/div/div[4]/div/select");
			private By filterButton = By.xpath("//button[text()=' Filter ']"); 
			private By exportCsvButton = By.xpath("//button[text()=' Export CSV ']");
			private By addressCard = By.cssSelector("tr.link-row");
			private By tableHeader = By.xpath("//tr/th");
			private By activePagination = By.xpath("//li[@class='page-item pagination-page-nav active']");
			private By nextPagination = By.xpath("//li[@class='page-item pagination-page-nav active'] //following-sibling::li");
			private By fieldLabel= By.xpath("//div[@class='form-group m-b-5']");
			private By addressTypeField= By.xpath("//select");
			
			public WebElement getAddressHeader() {
				return driver.findElement(addressHeader);
			}
			
			public List<WebElement> getFieldLabel() {
				return driver.findElements(fieldLabel);
			}
			
			public WebElement getAddressTypeField() {
				return driver.findElement(addressTypeField);
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
			
			public WebElement getAddressCard() {
				return driver.findElement(addressCard);
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

