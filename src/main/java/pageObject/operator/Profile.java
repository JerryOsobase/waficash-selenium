package pageObject.operator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Profile {

public WebDriver driver;
	
	public Profile(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	private By profilePicUpload= By.xpath("//input[@type='file']"); 
	private By removeButton= By.xpath("//button[text()='REMOVE']"); 
	private By profileHeader= By.cssSelector("h3.card-title");
	private By firstNameField= By.id("first-name");
	private By middleNameField= By.id("middle-name");
	private By lastNameField= By.id("last-name");
	private By phoneNumberField= By.id("phone-number"); 
	private By genderField= By.xpath("//select");
	private By emailAddressField= By.id("email-address"); 
	private By changePassword= By.xpath("//button[text()=' Change Password ']"); 
	private By currentPasswordField= By.id("user-password-c"); 
	private By newPasswordField= By.id("user-password-new");
	private By popUpCloseButton = By.cssSelector("button.close"); 
	
	public WebElement getProfilePicUpload() {
		return driver.findElement(profilePicUpload);
	}
	
	public WebElement getPopUpCloseButton() {
		return driver.findElement(popUpCloseButton);
	}
	
	public WebElement getNewPasswordField() {
		return driver.findElement(newPasswordField);
	}
	
	public WebElement getCurrentPasswordField() {
		return driver.findElement(currentPasswordField);
	}
	
	public WebElement getChangePassword() {
		return driver.findElement(changePassword);
	}
	
	public WebElement getEmailAddressField() {
		return driver.findElement(emailAddressField);
	}
	
	public WebElement getGenderField() {
		return driver.findElement(genderField);
	}
	
	public WebElement getPhoneNumberField() {
		return driver.findElement(phoneNumberField);
	}
	
	public WebElement getLastNameField() {
		return driver.findElement(lastNameField);
	}
	
	public WebElement getMiddleNameField() {
		return driver.findElement(middleNameField);
	}
	
	public WebElement getFirstNameField() {
		return driver.findElement(firstNameField);
	}
	
	public WebElement getProfileHeader() {
		return driver.findElement(profileHeader);
	}
	
	public WebElement getRemoveButton() {
		return driver.findElement(removeButton);
	}
	
	
}
