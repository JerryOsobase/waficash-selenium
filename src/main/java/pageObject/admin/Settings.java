package pageObject.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Settings {
public WebDriver driver;
	
	public Settings(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	private By settingsHeader= By.xpath("//h3[@class='text-themecolor m-b-0 m-t-0 app-title']");
	private By businessNameField= By.id("business-name");
	private By businessAddressField= By.id("business-address");
	private By supportPhoneNumberField= By.id("support-phone");
	private By supportEmailField= By.id("support-email");
	private By primaryContactNameField= By.id("primary-contact-name");
	private By primaryContactNumberField= By.id("primary-contact-number");
	private By primaryContactEmailField= By.id("primary-contact-email");
	private By businessWebsiteField= By.id("business-website");
	private By saveChangesButton= By.xpath("//button[text()=' Save Changes ']");
	
	
	public WebElement getSaveChangesButton() {
		return driver.findElement(saveChangesButton);
	}
    
    public WebElement getSettingsHeader() {
		return driver.findElement(settingsHeader);
	}
    
    public WebElement getPrimaryContactEmailField() {
		return driver.findElement(primaryContactEmailField);
	}
    
    public WebElement getBusinessWebsiteField() {
		return driver.findElement(businessWebsiteField);
	}
    
    public WebElement getPrimaryContactNumberField() {
		return driver.findElement(primaryContactNumberField);
	}
    
    public WebElement getPrimaryContactNameField() {
		return driver.findElement(primaryContactNameField);
	}

    public WebElement getSupportEmailField() {
		return driver.findElement(supportEmailField);
	}
    
    public WebElement getSupportPhoneNumberField() {
		return driver.findElement(supportPhoneNumberField);
	}
    
    public WebElement getBusinessAddressField() {
		return driver.findElement(businessAddressField);
	}
    
    public WebElement getBusinessNameField() {
		return driver.findElement(businessNameField);
	}
}
