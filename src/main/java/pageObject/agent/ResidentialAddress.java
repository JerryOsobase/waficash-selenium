package pageObject.agent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResidentialAddress {
public WebDriver driver;
	
	public ResidentialAddress(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
private By personalInformation= By.cssSelector("a[href*='personal-information']");
private By identityInformation= By.cssSelector("a[href*='identity-details']");
private By businessAddress= By.cssSelector("a[href*='agent-address']");
private By guarantorInformation= By.cssSelector("a[href*='guarantor-information']");
private By address= By.id("map");
private By landmark= By.xpath("//input[@placeholder='Opposite Ilupeju police station']");
private By lgaLcda= By.xpath("//select[@class='form-control rounded-0']");
private By backButton= By.xpath("//button[text()=' Back ']");
private By continueButton= By.xpath("//button[text()=' Save & Continue ']");
private By previewButton= By.xpath("//button[text()=' Preview and Submit ']");




public WebElement getpersonalInformation() {
	return driver.findElement(personalInformation);
}

public WebElement getidentityInformation() {
	return driver.findElement(identityInformation);
}

public WebElement getbusinessAddress() {
	return driver.findElement(businessAddress);
}

public WebElement getguarantorInformation() {
	return driver.findElement(guarantorInformation);
}

public WebElement getaddress() {
	return driver.findElement(address);
}

public WebElement getlandmark() {
	return driver.findElement(landmark);
}

public WebElement getlgaLcda() {
	return driver.findElement(lgaLcda);
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
}
