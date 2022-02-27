package pageObject.operator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Coupon {

public WebDriver driver;
	
	public Coupon(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	private By agentSearchBar= By.xpath("//input[@type='search']");
	private By searchButton= By.xpath("//button[text()=' Search ']"); 
	private By fieldlabel= By.xpath("//label[@class='font-bold form-label']");
	private By categoryRequestCoupon= By.xpath("//following-sibling::select"); 
	private By couponCodeField= By.xpath("//input[@placeholder='Enter Coupon Code']");
	private By quantityField= By.xpath("//following-sibling::input"); 
	private By startDateField= By.xpath("//following-sibling::input"); 
	private By endDateField= By.xpath("//following-sibling::input"); 
	private By discountTypeField= By.id("discount_type"); 
	private By discountValueField= By.xpath("//input[@placeholder='Enter Discount Value']"); 
	private By agentNameField= By.xpath("//following-sibling::input"); 
	private By getQuotationButton= By.xpath("//button[text()=' Get Quotation ']");
	private By generatedCouponCategory= By.id("category");
	private By generatedCouponLabel= By.xpath("//label[@class='control-label']");
	private By generatedCouponStartDate= By.xpath("//following-sibling::input");
	private By generatedCouponEndDate= By.xpath("//following-sibling::input");
	private By filterButton = By.xpath("//button[text()=' Filter ']"); 
	private By exportCsvButton = By.xpath("//button[text()=' Export CSV ']");
	private By downloadCsvButton = By.xpath("//button[text()=' Download CSV ']");
	private By activePagination = By.xpath("//li[@class='page-item pagination-page-nav active']");
	private By nextPagination = By.xpath("//li[@class='page-item pagination-page-nav active'] //following-sibling::li");
	
	public WebElement getAgentSearchBar() {
		return driver.findElement(agentSearchBar);
	}
	
	public WebElement getActivePagination() {
		return driver.findElement(activePagination);
	}
	
	public WebElement getNextPagination() {
		return driver.findElement(nextPagination);
	}
	
	
	
	public WebElement getExportCsvButton() {
		return driver.findElement(exportCsvButton);
	}
	
	public WebElement getDownloadCsvButton() {
		return driver.findElement(downloadCsvButton);
	}
	
	public WebElement getGeneratedCouponCategory() {
		return driver.findElement(generatedCouponCategory);
	}
	
	public WebElement getFilterButton() {
		return driver.findElement(filterButton);
	}
	
	public WebElement getGeneratedCouponEndDate() {
		return driver.findElement(generatedCouponEndDate);
	}
	
	public WebElement getGeneratedCouponLabel() {
		return driver.findElement(generatedCouponLabel);
	}
	
	public WebElement getGeneratedCouponStartDate() {
		return driver.findElement(generatedCouponStartDate);
	}
	
	public WebElement getSearchButton() {
		return driver.findElement(searchButton);
	}
	
	public WebElement getQuotationButton() {
		return driver.findElement(getQuotationButton);
	}
	
	public WebElement getAgentNameField() {
		return driver.findElement(agentNameField);
	}
	
	public WebElement getDiscountValueField() {
		return driver.findElement(discountValueField);
	}
	
	public WebElement getDiscountTypeField() {
		return driver.findElement(discountTypeField);
	}
	
	public WebElement getFieldlabel() {
		return driver.findElement(fieldlabel);
	}
	
	public WebElement getEndDateField() {
		return driver.findElement(endDateField);
	}
	
	public WebElement getQuantityField() {
		return driver.findElement(quantityField);
	}
	
	public WebElement getStartDateField() {
		return driver.findElement(startDateField);
	}
	
	public WebElement getCategoryRequestCoupon() {
		return driver.findElement(categoryRequestCoupon);
	}
	
	public WebElement getCouponCodeField() {
		return driver.findElement(couponCodeField);
	}
	
}
