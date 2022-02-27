package pageObject.agent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GuarantorInformation {
public WebDriver driver;
	
	public GuarantorInformation(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
private By personalInformation= By.cssSelector("a[href*='personal-information']");
private By identityInformation= By.cssSelector("a[href*='identity-details']");
private By businessAddress= By.cssSelector("a[href*='agent-address']");
private By residentialAddress= By.cssSelector("a[href*='residential-address']");
private By lastName= By.xpath("//input[@placeholder=\"Enter Guarantor's last name or Surname\"]");
private By firstName= By.xpath("//input[@placeholder=\"Enter Guarantor's first name\"]");
private By middleName= By.xpath("//input[@placeholder=\"Enter Agent's middle name\"]");
private By email= By.xpath("//input[@placeholder=\"Enter Guarantor's email address\"]");
private By phoneNumber= By.xpath("//input[@placeholder=\"Enter Guarantor's phone number\"]");

private By backButton= By.xpath("//button[text()=' Back ']");
private By continueButton= By.xpath("//button[text()=' Save & Continue ']");
private By previewButton= By.xpath("//button[text()=' Preview and Submit ']");
private By PromptMessage= By.cssSelector("div.iziToast-texts");





public WebElement getpersonalInformation() {
	return driver.findElement(personalInformation);
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

public WebElement getlastName() {
	return driver.findElement(lastName);
}

public WebElement getfirstName() {
	return driver.findElement(firstName);
}

public WebElement getmiddleName() {
	return driver.findElement(middleName);
}

public WebElement getemail() {
	return driver.findElement(email);
}

public WebElement getphoneNumber() {
	return driver.findElement(phoneNumber);
}

public WebElement getbackButton() {
	return driver.findElement(backButton);
}

public WebElement getcontinueButton() {
	return driver.findElement(continueButton);
}

public WebElement getpreviewButton() {
return driver.findElement(previewButton);
}

public WebElement getPromptMessage() {
	@SuppressWarnings("deprecation")
	WebDriverWait wait = new WebDriverWait(driver,60) ;
	wait.until(ExpectedConditions.presenceOfElementLocated(PromptMessage));
	return driver.findElement(PromptMessage);
}
}
