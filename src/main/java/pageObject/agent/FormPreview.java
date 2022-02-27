package pageObject.agent;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormPreview {
public WebDriver driver;
	
	public FormPreview(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
private By formPreviewMenu= By.cssSelector("a[href*=preview]");
private By identityInformation= By.cssSelector("a[href*='identity-details']");
private By businessAddress= By.cssSelector("a[href*='agent-address']");
private By residentialAddress= By.cssSelector("a[href*='residential-address']");
private By personalInformation= By.cssSelector("a[href*='personal-information']");
private By guarantorInformation= By.cssSelector("a[href*='guarantor-information']");
private By editButton= By.xpath("//a[text()='[ Edit ]']");
private By backButton= By.xpath("//button[text()=' Back ']");
private By proceedButton= By.xpath("//button[text()=' Proceed to Payment ']");
private By confirmationProceedButton= By.xpath("//button[text()='Yes, proceed to payment!']");
private By confirmationCancelButton= By.xpath("//button[text()='No, let me review!']");
private By confirmationMessage= By.id("swal2-content");
private By confirmationHeader= By.id("swal2-title");
private By editHeader= By.xpath("//h2[@class='card-title']");


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

public WebElement getpersonalInformation() {
	return driver.findElement(personalInformation);
}

public WebElement getguarantorInformation() {
	return driver.findElement(guarantorInformation);
}

public List<WebElement> getEditButton() {
	return driver.findElements(editButton);
}


public WebElement getbackButton() {
	return driver.findElement(backButton);
}

public WebElement getproceedButton() {
	@SuppressWarnings("deprecation")
	WebDriverWait wait = new WebDriverWait(driver,10) ;
	wait.until(ExpectedConditions.elementToBeClickable(proceedButton));
	return driver.findElement(proceedButton);
}

public WebElement getconfirmationProceedButton() {
	return driver.findElement(confirmationProceedButton);
}

public WebElement getconfirmationCancelButton() {
	return driver.findElement(confirmationCancelButton);
}

public WebElement getconfirmationMessage() {
	@SuppressWarnings("deprecation")
	WebDriverWait wait = new WebDriverWait(driver,10) ;
	wait.until(ExpectedConditions.elementToBeClickable(confirmationMessage));
	return driver.findElement(confirmationMessage);
}
public WebElement getconfirmationHeader() {
	return driver.findElement(confirmationHeader);
}

public List<WebElement> getEditHeader() {
	return driver.findElements(editHeader);
}
}
