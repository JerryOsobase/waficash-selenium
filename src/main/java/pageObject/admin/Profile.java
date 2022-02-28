package pageObject.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	private By changePassword= By.xpath("//div[@class='form-group m-b-10'] //button[text()=' Change Password ']"); 
	private By currentPasswordField= By.id("user-password-c"); 
	private By newPasswordField= By.id("user-password-new");
	private By confirmPasswordField= By.id("user-password-confirm");
	private By popUpChangePassword= By.xpath("//div[@id='change-password-modal'] //button[text()=' Change Password ']"); 
	private By popUpCloseButton = By.cssSelector("div[id='change-password-modal'] button.close"); 
	private By fieldLabel= By.cssSelector("label[class*='font-bold form-label']");
	private By saveChanges= By.xpath("//button[text()=' Save Changes ']");
	private By image= By.xpath("//div[@class='dropify-wrapper has-preview'] //img");
	private By dropDownImage= By.xpath("//li[@class='nav-item dropdown'] //img");
	private By PromptMessage= By.cssSelector("div.iziToast-texts");
	private By passwordTips= By.xpath("//ul[@class='list-unstyled'] /li");
	
	public WebElement getDropDownImage() {
		return driver.findElement(dropDownImage);
	}
	
	public List<WebElement> getPasswordTips() {
		return driver.findElements(passwordTips);
	}
	
	public WebElement getConfirmPasswordField() {
		return driver.findElement(confirmPasswordField);
	}
	
	public WebElement getPopUpChangePassword() {
		return driver.findElement(popUpChangePassword);
	}
	
	public WebElement getProfilePicUpload() {
		return driver.findElement(profilePicUpload);
	}
	
	public List<WebElement> getImage() {
		return driver.findElements(image);
	}
	
	public WebElement getPromptMessage() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.elementToBeClickable(PromptMessage));
		return driver.findElement(PromptMessage);
	}
	
	public WebElement getSaveChanges() {
		return driver.findElement(saveChanges);
	}
	
	public List<WebElement> getFieldLabel() {
		return driver.findElements(fieldLabel);
	}
	
	public WebElement getPopUpCloseButton() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.elementToBeClickable(popUpCloseButton));
		return driver.findElement(popUpCloseButton);
	}
	
	public WebElement getNewPasswordField() {
		return driver.findElement(newPasswordField);
	}
	
	public WebElement getCurrentPasswordField() {
		return driver.findElement(currentPasswordField);
	}
	
	public WebElement getChangePassword() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.elementToBeClickable(changePassword));
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
