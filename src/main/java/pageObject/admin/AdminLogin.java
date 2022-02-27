package pageObject.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AdminLogin {

	public WebDriver driver;
		
		public AdminLogin(WebDriver driver) {
			// TODO Auto-generated constructor stub
			this.driver=driver;
		}
		
		private By emailAddressField= By.name("email");
		private By goHomeLink= By.xpath("//a[text()=' Go Home ']");
		private By agentlogin= By.xpath("//a[text()=' Agent Login ']");
		private By rememberMeCheckbox = By.id("checkbox-signin");
		private By continueButton= By.xpath("//button[text()=' Continue ']");
		private By backLink= By.xpath("//a[@class='d-block text-info font-14 font-bold mb-3']");
		private By passwordField= By.id("login-password");
		private By eyeIcon= By.cssSelector("div.input-group-addon"); 
		private By forgotPassword= By.cssSelector("a[href*='forgot']");	
		private By loginButton= By.xpath("//button[text()=' Log In ']");
		private By PromptMessage= By.cssSelector("div.iziToast-texts");
		private By forgotPasswordHeader= By.cssSelector("h3.text-center");
		private By forgotPasswordEmailField= By.xpath("//input[@placeholder='Email Address']");
		private By resetPasswordMessage= By.xpath("//div[@class='col-xs-12 alert alert-success"
				+ "-light font-normal font-14 font-bold rounded-0 text-center']");
		private By resetButton= By.xpath("//button[text()=' Reset ']");
		private By backtoLoginLink= By.cssSelector("a[href*='login']");
		
		
		
		public WebElement getRememberMeCheckbox() {
			return driver.findElement(rememberMeCheckbox);
		}
		
		public WebElement getBacktoLoginLink() {
			return driver.findElement(backtoLoginLink);
		}
		
		public WebElement getResetButton() {
			return driver.findElement(resetButton);
		}
		
		public WebElement getForgotPasswordEmailField() {
			return driver.findElement(forgotPasswordEmailField);
		}
		
		public WebElement getResetPasswordMessage() {
			return driver.findElement(resetPasswordMessage);
		}
		
		public WebElement getForgotPasswordHeader() {
			return driver.findElement(forgotPasswordHeader);
		}
		
		public WebElement getPromptMessage() {
			@SuppressWarnings("deprecation")
			WebDriverWait wait = new WebDriverWait(driver,15) ;
			wait.until(ExpectedConditions.elementToBeClickable(PromptMessage));
			return driver.findElement(PromptMessage);
		}
		
		public WebElement getEyeIcon() {
			return driver.findElement(eyeIcon);
		}
		
		public WebElement getLoginButton() {
			return driver.findElement(loginButton);
		}
		
		public WebElement getForgotPassword() {
			return driver.findElement(forgotPassword);
		}
		
		public WebElement getPasswordField() {
			return driver.findElement(passwordField);
		}
		
		public WebElement getBackLink() {
			return driver.findElement(backLink);
		}
		
		public WebElement getContinueButton() {
			return driver.findElement(continueButton);
		}
		
		public WebElement getEmailAddressField() {
			return driver.findElement(emailAddressField);
		}
		
		public WebElement getGoHomeLink() {
			return driver.findElement(goHomeLink);
		}
		
		public WebElement getAgentlogin() {
			return driver.findElement(agentlogin);
		}
}
