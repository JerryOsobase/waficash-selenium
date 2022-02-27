package pageObject.agent;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationLandingPage {
public WebDriver driver;
	
	public RegistrationLandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
private By email= By.xpath("//input[@placeholder='Enter Email Address']");
private By phoneNumber= By.xpath("//input[@placeholder='Enter Phone Number']");
private By permitCategory= By.xpath("//div[@class='form-group']/ul/li/label");
private By company= By.xpath("//select[@required='required']");
private By continueButton= By.xpath("//button[text()=' Continue ']");
private By formHeader= By.xpath("//div[@class='col-xs-12 text-center']");


public WebElement getEmail() {
	return driver.findElement(email);
}

public WebElement getphoneNumber() {
	return driver.findElement(phoneNumber);
}
public List<WebElement> getpermitCategory() {
	return driver.findElements(permitCategory);
}
public WebElement getcompany() {
	return driver.findElement(company);
}

public WebElement getcontinueButton() {
	return driver.findElement(continueButton);
}

public WebElement getformHeader() {
	@SuppressWarnings("deprecation")
	WebDriverWait wait = new WebDriverWait(driver,10) ;
	wait.until(ExpectedConditions.elementToBeClickable(formHeader));
	return driver.findElement(formHeader);
}
}
