package pageObject.agent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Payment {
public WebDriver driver;
	
	public Payment(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
private By formPreviewMenu= By.cssSelector("a[href*=preview]");
private By identityInformation= By.cssSelector("a[href*='identity-details']");
private By businessAddress= By.cssSelector("a[href*='agent-address']");
private By residentialAddress= By.cssSelector("a[href*='residential-address']");
private By personalInformation= By.cssSelector("a[href*='personal-information']");
private By guarantorInformation= By.cssSelector("a[href*='guarantor-information']");
private By couponCheckbox= By.xpath("//label[@for='checkbox-signup']");
private By couponField= By.xpath("//input[@placeholder='Enter Coupon Code']");
private By couponVerifyButton= By.xpath("//button[text()='VERIFY']");
private By submitButton= By.xpath("//div[@class='col-md-12 col-sm-12']/button");
private By backButton= By.xpath("//button[text()=' Back ']");
private By proceedButton= By.xpath("//button[text()=' Proceed to Payment ']");
private By confirmationProceedButton= By.xpath("//button[text()='Yes, proceed to payment!']");
private By confirmationCancelButton= By.xpath("//button[text()='No, let me review!']");
private By confirmationMessage= By.id("swal2-content");
private By confirmationHeader= By.id("swal2-title");
private By successTestCard= By.xpath("//*[@id=\"test-cards\"]/div[1]/div[1]/div");
private By paystackSubmitButton = By.xpath("//button[@type='submit']");
private By successfulSubmission = By.xpath("//h1[text()='Form Submission Successful']");
private By successfulSubmissionMessage = By.xpath("//p[@class='font-18']");
private By InvalidcouponMessage= By.xpath("//p[text()=' Invalid coupon code ']");
private By PromptMessage= By.cssSelector("div.iziToast-texts");
private By CouponRemoveButton= By.cssSelector("button.btn.btn-sm.btn-outline-danger.float-right");
private By CouponConfirmationRemoveButton= By.xpath("//button[text()=' Yes, Remove Coupon ']");
private By Price= By.cssSelector("div.float-right");
private By PaystackPopUpFrame= By.xpath("//iframe[@src='https://checkout.paystack.com/popup']");


public WebElement getformPreviewMenu() {
	return driver.findElement(formPreviewMenu);
}

public WebElement getidentityInformation() {
	return driver.findElement(identityInformation);
}

public WebElement getbusinessAddress() {
	return driver.findElement(businessAddress);
}

public WebElement getresidentialAddress() {
	return driver.findElement(residentialAddress);
}

public WebElement getInvalidcouponMessage() {
	return driver.findElement(InvalidcouponMessage);
}

public WebElement getPromptMessage() {
	return driver.findElement(PromptMessage);
}


public WebElement getpersonalInformation() {
	return driver.findElement(personalInformation);
}

public WebElement getPrice() {
	return driver.findElement(Price);
}

public WebElement getguarantorInformation() {
	return driver.findElement(guarantorInformation);
}


public WebElement getcouponCheckbox() {
	return driver.findElement(couponCheckbox);
}

public WebElement getCouponRemoveButton() {
	return driver.findElement(CouponRemoveButton);
}

public WebElement getCouponConfirmationRemoveButton() {
	@SuppressWarnings("deprecation")
	WebDriverWait wait = new WebDriverWait(driver,10) ;
	wait.until(ExpectedConditions.elementToBeClickable(CouponConfirmationRemoveButton));
	return driver.findElement(CouponConfirmationRemoveButton);
}

public WebElement getcouponField() {
	@SuppressWarnings("deprecation")
	WebDriverWait wait = new WebDriverWait(driver,10) ;
	wait.until(ExpectedConditions.presenceOfElementLocated(couponField));
	return driver.findElement(couponField);
}

public WebElement getcouponVerifyButton() {
	return driver.findElement(couponVerifyButton);
}

public WebElement getbackButton() {
	return driver.findElement(backButton);
}

public WebElement getsubmitButton() {
	@SuppressWarnings("deprecation")
	WebDriverWait wait = new WebDriverWait(driver,10) ;
	wait.until(ExpectedConditions.elementToBeClickable(submitButton));
	return driver.findElement(submitButton);
}

public WebElement getproceedButton() {
	return driver.findElement(proceedButton);
}
public WebElement getPaystackPopUpFrame() {
	@SuppressWarnings("deprecation")
	WebDriverWait wait = new WebDriverWait(driver,60) ;
	wait.until(ExpectedConditions.presenceOfElementLocated(PaystackPopUpFrame));
	return driver.findElement(PaystackPopUpFrame);
}

public WebElement getconfirmationProceedButton() {
	return driver.findElement(confirmationProceedButton);
}

public WebElement getsuccessTestCard() {
	@SuppressWarnings("deprecation")
	WebDriverWait wait = new WebDriverWait(driver,60) ;
	wait.until(ExpectedConditions.presenceOfElementLocated(successTestCard));
	return driver.findElement(successTestCard);
}

public WebElement getconfirmationCancelButton() {
	return driver.findElement(confirmationCancelButton);
}

public WebElement getconfirmationMessage() {
	return driver.findElement(confirmationMessage);
}
public WebElement getconfirmationHeader() {
	return driver.findElement(confirmationHeader);
}

public WebElement getpaystackSubmitButton() {
	return driver.findElement(paystackSubmitButton);
}

public WebElement getsuccessfulSubmission() {
	@SuppressWarnings("deprecation")
	WebDriverWait wait = new WebDriverWait(driver,15) ;
	wait.until(ExpectedConditions.presenceOfElementLocated(PaystackPopUpFrame));
	return driver.findElement(successfulSubmission);
}

public WebElement getsuccessfulSubmissionMessage() {
	@SuppressWarnings("deprecation")
	WebDriverWait wait = new WebDriverWait(driver,15) ;
	wait.until(ExpectedConditions.presenceOfElementLocated(PaystackPopUpFrame));
	return driver.findElement(successfulSubmissionMessage);
}
}
