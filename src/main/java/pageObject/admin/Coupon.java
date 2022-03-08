package pageObject.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Coupon {

public WebDriver driver;
	
	public Coupon(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	private By agentSearchBar= By.xpath("//input[@type='search']");
	private By searchButton= By.xpath("//button[text()=' Search ']"); 
	private By categoryRequestCoupon= By.xpath("//select[@type='text']"); 
	private By couponCodeField= By.xpath("//input[@placeholder='Enter Coupon Code']");
	private By quantityField= By.xpath("//input[@placeholder='0']"); 
	private By startDateField= By.xpath("//div[@class='form-group m-b-10']/input[@placeholder='Start Date']"); 
	private By endDateField= By.xpath("//div[@class='form-group m-b-10'] //input[@placeholder='End Date']"); 
	private By discountTypeField= By.id("discount_type"); 
	private By discountValueField= By.xpath("//input[@placeholder='Enter Discount Value']"); 
	private By agentNameField= By.xpath("//div[@class='row'] //input[@placeholder='Type to search for agents...']"); 
	private By getQuotationButton= By.xpath("//button[text()=' Get Quotation ']");
	private By generatedCouponCategory= By.id("category");
	private By generatedCouponStartDate= By.xpath("//div[@class='clearfix']/input[@placeholder='Start Date']");
	private By generatedCouponEndDate= By.xpath("//div[@class='form-group m-b-5'] //input[@placeholder='End Date']");
	private By filterButton = By.xpath("//button[text()=' Filter ']"); 
	private By exportCsvButton = By.xpath("//button[text()=' Export CSV ']");
	private By downloadCsvButton = By.xpath("//button[text()=' Download CSV ']");
	private By activePagination = By.xpath("//li[@class='page-item pagination-page-nav active']");
	private By nextPagination = By.xpath("//li[@class='page-item pagination-page-nav active'] //following-sibling::li");
	private By couponHeader= By.xpath("//h3[@class='text-themecolor m-b-0 m-t-0 app-title']");
	private By calendarYear= By.cssSelector("div[class*='open'] [type='number']");
	private By calendarMonthHeader= By.cssSelector("div[class*='open'] [aria-label='Month']");
	private By calendarDate= By.cssSelector("div[class*='open'] [class*='flatpickr-day ']");
	private By agentFieldDropdown= By.xpath("//ul[@role='listbox']/li");
	private By popUpRequestButton= By.xpath("//div[@class='modal-content modal-border-radius'] //button[@class='btn btn-warning rounded-0 waves-effect p-10 w-100 p-10']");
	private By successTestCard= By.xpath("//*[@id=\"test-cards\"]/div[1]/div[1]/div");
	private By paystackSubmitButton = By.xpath("//button[@type='submit']");
	private By PaystackPopUpFrame= By.xpath("//iframe[@src='https://checkout.paystack.com/popup']");
	private By PromptMessage= By.cssSelector("div.iziToast-texts");
	private By tableFooterText= By.xpath("//div[@class='p-30 test font-medium text-muted']");
	private By code= By.xpath("//tr/td[2]");
	private By quantity= By.xpath("//tr/td[3]");
	private By clearButton= By.xpath("//button[text()=' clear filter ']"); 
	private By generatedListHeader= By.xpath("//tr/th"); 
	private By couponDetails= By.xpath("//div[@class='col-xs-12']");
	private By paymentMode= By.cssSelector("label[class*='radio']");
	private By bankTransferMessage= By.cssSelector("div[id='get-coupon-quotation-modal'] div[class*='alert'] div");
	private By popUpCloseButton = By.cssSelector("div[id='get-coupon-quotation-modal'] button.close"); 
	
	
	public WebElement getAgentSearchBar() {
		return driver.findElement(agentSearchBar);
	}
	
	public WebElement getPopUpCloseButton() {
		return driver.findElement(popUpCloseButton);
	}
	
	public WebElement getBankTransferMessage() {
		return driver.findElement(bankTransferMessage);
	}
	
	public List<WebElement> getPaymentMode() {
		return driver.findElements(paymentMode);
	}
	
	public List<WebElement> getCouponDetails() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(couponDetails));
		return driver.findElements(couponDetails);
	}
	
	public List<WebElement> getGeneratedListHeader() {
		return driver.findElements(generatedListHeader);
	}
	
	public WebElement getClearButton() {
		return driver.findElement(clearButton);
	}
	
	public List<WebElement> getQuantity() {
		return driver.findElements(quantity);
	}
	
	public WebElement getTableFooterText() {
		return driver.findElement(tableFooterText);
	}
	
	public List<WebElement> getCode() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.elementToBeClickable(code));
		return driver.findElements(code);
	}
	
	public WebElement getPromptMessage() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,15) ;
		wait.until(ExpectedConditions.elementToBeClickable(PromptMessage));
		return driver.findElement(PromptMessage);
	}
	
	public WebElement getPaystackPopUpFrame() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,60) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(PaystackPopUpFrame));
		return driver.findElement(PaystackPopUpFrame);
	}
	
	public WebElement getSuccessTestCard() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,60) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(successTestCard));
		return driver.findElement(successTestCard);
	}
	
	public WebElement getPaystackSubmitButton() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,60) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(paystackSubmitButton));
		return driver.findElement(paystackSubmitButton);
	}
	
	public WebElement getPopUpRequestButton() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.elementToBeClickable(popUpRequestButton));
		return driver.findElement(popUpRequestButton);
	}
	
	public List<WebElement> getAgentFieldDropdown() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.elementToBeClickable(agentFieldDropdown));
		return driver.findElements(agentFieldDropdown);
	}
	
	public WebElement getCalendarYear() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.elementToBeClickable(calendarYear));
		return driver.findElement(calendarYear);
	}
	
	public WebElement getCalendarMonthHeader() {
		return driver.findElement(calendarMonthHeader);
	}
	
	public List<WebElement> getCalendarDate() {
		return driver.findElements(calendarDate);
	}
	
	public WebElement getCouponHeader() {
		return driver.findElement(couponHeader);
	}
	
	public List<WebElement> getActivePagination() {
		return driver.findElements(activePagination);
	}
	
	public WebElement getNextPagination() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.elementToBeClickable(nextPagination));
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
