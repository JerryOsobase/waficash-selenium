package pageObject.agent;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalInformation {
public WebDriver driver;
	
	public PersonalInformation(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
private By lastName= By.cssSelector("input[placeholder=\"Enter Agent's last name or Surname\"]");
private By firsttName= By.cssSelector("input[placeholder=\"Enter Agent's first name\"]");
private By middleName= By.cssSelector("input[placeholder=\"Enter Agent's middle name\"]");
private By gender= By.xpath("//div/select");
private By dateofBirth= By.xpath("//input[@placeholder=\"Enter Agent's date of birth\"]");
private By email= By.xpath("//input[@placeholder=\"Enter Agent's email address\"]");
private By phoneNumber= By.xpath("//input[@placeholder=\"Enter Agent's phone number\"]");
private By secondPhoneNumber= By.xpath("//input[@placeholder='Enter Other mobile number']");
private By continueButton= By.xpath("//button[text()=' Save & Continue ']");
private By previewButton= By.xpath("//button[text()=' Preview and Submit ']");
private By identityInformation= By.cssSelector("a[href*='identity-details']");
private By businessAddress= By.cssSelector("a[href*='agent-address']");
private By residentialAddress= By.cssSelector("a[href*='residential-address']");
private By guarantorInformation= By.cssSelector("a[href*='guarantor-information']");
private By PromptMessage= By.cssSelector("div.iziToast-texts");
private By formHeader= By.xpath("//h1[text()=' Agent Application Form ']");
private By calendar= By.xpath("//input[@placeholder=\"Enter Agent's date of birth\"]");
private By calendarMonthHeader= By.xpath("//div[@class='flatpickr-months'] //div/select");
private By calendarYear= By.xpath("//input[@type='number']");
private By calendarDate= By.xpath("//div[@class='dayContainer'] //span");
private By formPreview= By.cssSelector("a[href*='preview']");
private By payment= By.cssSelector("a[href*='verification-payment']");



public WebElement getPromptMessage() {
	@SuppressWarnings("deprecation")
	WebDriverWait wait = new WebDriverWait(driver,10) ;
	wait.until(ExpectedConditions.presenceOfElementLocated(PromptMessage));
	return driver.findElement(PromptMessage);
}

public WebElement getformHeader() {
	@SuppressWarnings("deprecation")
	WebDriverWait wait = new WebDriverWait(driver,10) ;
	wait.until(ExpectedConditions.presenceOfElementLocated(formHeader));
	return driver.findElement(formHeader);
}

public WebElement getlastName() {
	return driver.findElement(lastName);
}

public WebElement getcalendar() {
	return driver.findElement(calendar);
}

public WebElement getfirsttName() {
	return driver.findElement(firsttName);
}

public WebElement getpayment() {
	return driver.findElement(payment);
}

public WebElement getcalendarMonthHeader() {
	return driver.findElement(calendarMonthHeader);
}

public WebElement getFormPreview() {
	return driver.findElement(formPreview);
}

public WebElement getcalendarYear() {
	return driver.findElement(calendarYear);
}

public WebElement getmiddleName() {
	return driver.findElement(middleName);
}
public List<WebElement> getcalendarDate() {
	return driver.findElements(calendarDate);
}

public WebElement getgender() {
	return driver.findElement(gender);
}

public WebElement getdateofBirth() {
	return driver.findElement(dateofBirth);
}

public WebElement getemail() {
	return driver.findElement(email);
}

public WebElement getphoneNumber() {
	return driver.findElement(phoneNumber);
}

public WebElement getsecondPhoneNumber() {
	return driver.findElement(secondPhoneNumber);
}

public WebElement getcontinueButton() {
	return driver.findElement(continueButton);
}

public WebElement getpreviewButton() {
return driver.findElement(previewButton);
}

public WebElement getidentityInformation() {
	@SuppressWarnings("deprecation")
	WebDriverWait wait = new WebDriverWait(driver,10) ;
	wait.until(ExpectedConditions.presenceOfElementLocated(identityInformation));
	return driver.findElement(identityInformation);
}

public WebElement getbusinessAddress() {
	return driver.findElement(businessAddress);
}

public WebElement getresidentialAddress() {
	return driver.findElement(residentialAddress);
}

public WebElement getguarantorInformation() {
	return driver.findElement(guarantorInformation);
}



}
