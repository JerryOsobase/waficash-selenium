package pageObject.agent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AgentLogin {
public WebDriver driver;
	
	public AgentLogin(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
private By email= By.xpath("//input[@placeholder='Enter Email Address']");
private By phoneNumber= By.xpath("//input[@placeholder='Phone Number']");
private By continueButton= By.xpath("//button[text()=' Continue ']");
private By goHomeLink= By.xpath("//a[text()=' Go Home ']");
private By AgentProfileHeader= By.xpath("//div[.='Agent Profile']");

public WebElement getEmail() {
	return driver.findElement(email);
}

public WebElement getphoneNumber() {
	return driver.findElement(phoneNumber);
}
public WebElement getcontinueButton() {
	return driver.findElement(continueButton);
}
public WebElement getgoHomeLink() {
	return driver.findElement(goHomeLink);
}

public WebElement AgentProfileHeader() {
	return driver.findElement(AgentProfileHeader);
}
}
