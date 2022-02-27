package pageObject.agent;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IdentityInformation {
public WebDriver driver;
	
	public IdentityInformation(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
private By personalInformation= By.cssSelector("a[href*='personal-information']");
private By businessAddress= By.cssSelector("a[href*='agent-address']");
private By residentialAddress= By.cssSelector("a[href*='residential-address']");
private By guarantorInformation= By.cssSelector("a[href*='guarantor-information']");
private By idType= By.id("identity_type");
private By idNumberBlank= By.xpath("//input[@placeholder='Enter Identity Number']");
private By idNumberBvn= By.xpath("//input[@placeholder='Enter Bank Verification Number']");
private By idNumberNin= By.xpath("//input[@placeholder='Enter National Identification Number']");
private By idNumberDriverLicense= By.xpath("//input[@placeholder=\"Enter Driver's License Number\"]");
private By idNumberMessaage= By.xpath("//div[@class='col-md-12']/input/following-sibling::p");
private By BvnSample= By.xpath("//div[@class='col-md-12 text-center font-14 p-2 p-md-5']");
private By NINSample= By.xpath("//div[@class='col-md-6 p-2 p-md-5 text-center']/img");
private By DriverSample= By.xpath("//div[@class='col-md-12 p-2 p-md-5 text-center']/img");
private By continueButton= By.xpath("//button[text()=' Save & Continue ']");
private By backButton= By.xpath("//button[text()=' Back ']");
private By previewButton= By.xpath("//button[text()=' Preview and Submit ']");





public WebElement getpersonalInformation() {
	return driver.findElement(personalInformation);
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

public WebElement getidType() {
	return driver.findElement(idType);
}

public WebElement getBvnSample() {
	return driver.findElement(BvnSample);
}

public List<WebElement> getNINSample() {
	return driver.findElements(NINSample);
}

public List<WebElement> getDriverSample() {
	return driver.findElements(DriverSample);
}

public WebElement getIdNumberBlank() {
	return driver.findElement(idNumberBlank);
}

public WebElement getIdNumberBvn() {
	return driver.findElement(idNumberBvn);
}

public WebElement getIdNumberNin() {
	return driver.findElement(idNumberNin);
}

public WebElement getIdNumberDriverLicense() {
	return driver.findElement(idNumberDriverLicense);
}

public WebElement getidNumberMessaage() {
	return driver.findElement(idNumberMessaage);
}
public WebElement getcontinueButton() {
	return driver.findElement(continueButton);
}

public WebElement getpreviewButton() {
return driver.findElement(previewButton);
}


public WebElement getbackButton() {
	return driver.findElement(backButton);
}
}
