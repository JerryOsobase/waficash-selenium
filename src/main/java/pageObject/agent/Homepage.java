package pageObject.agent;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homepage {
public WebDriver driver;

	
	public Homepage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	private By agentlogin= By.xpath("//a[text()=' Agent Login ']");
	private By registrationButton=  By.xpath("//a[text()=' Register as an Agent ']");
	private By lslbOfficialLink= By.xpath("//a[text()=' © 2021 - Lagos State Lotteries Board. All Rights Reserved. ']");
	private By lslbFacebookLink= By.xpath("//a[@href='https://facebook.com/lslbng']");
	private By lsTwitterLink= By.xpath("//a[@href='https://twitter.com/lslbng']");
	private By lslbInstagramLink= By.xpath("//a[@href='https://www.instagram.com/lslbng/']");
	private By privacyPolicy= By.xpath("//a[text()=' Privacy Policy ']");
	private By frameChatBot= By.xpath("//iframe[@title='chat widget']");
	private By frameChatForm= By.id("tawk-body");
	private By chatBot = By.xpath("/html/body/div/div/button");
    private By chatName= By.xpath("//input[@aria-placeholder='Name']");
    private By chatEmail= By.xpath("//input[@aria-placeholder='Email']");
    private By chatMessage= By.xpath("//textarea[@role='textarea']");
    private By chatsubmit = By.xpath("//div[@class='tawk-form-footer']/button");
    private By chatErrorMessage = By.xpath("//small[@class='tawk-text-red-1 tawk-text-regular-1']");
    private By chatFieldTitle = By.xpath("//label[@class='tawk-form-label tawk-text-red-1']"); 
    private By chatSuccessMessage = By.xpath("//p[@class='tawk-text-bold-1']");
    private By homePagelink = By.xpath("//a[text()=' Go Home ']");
    
    
	
	public WebElement getAgentLogin() {
		return	driver.findElement(agentlogin);
			
		 
	}
	
	public WebElement getRegistrationButton() {
		return	driver.findElement(registrationButton);
		 
	}
	
	public WebElement getlslbOfficialLink() {
		return	driver.findElement(lslbOfficialLink);
		 
	}
	
	public WebElement getlslbFacebookLink() {
		return	driver.findElement(lslbFacebookLink);
		 
	}
	
	public WebElement gethomePagelink() {
		return	driver.findElement(homePagelink);
		 
	}
	
	public WebElement getlsTwitterLink() {
		return	driver.findElement(lsTwitterLink);
		 
	}
	
	public WebElement getlslbInstagramLink() {
		return	driver.findElement(lslbInstagramLink);
		 
	}
	
	public WebElement getprivacyPolicy() {
		return	driver.findElement(privacyPolicy);
		 
	}
	
	public WebElement getchatErrorMessage() {
		return	driver.findElement(chatErrorMessage);
		 
	}
	
	public List<WebElement> getchatFieldTitle() {
		return	driver.findElements(chatFieldTitle);
		 
	}
	
	public WebElement getframeChatForm() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(frameChatForm));
		return	driver.findElement(frameChatForm);
		 
	}
	public WebElement getframeChatBot() {
		@SuppressWarnings("deprecation")
		 WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(frameChatBot));
		return	driver.findElement(frameChatBot);
		 
	}
	
	public WebElement getchatBot() {
		return	driver.findElement(chatBot);
		 
	}
	
	public WebElement getchatSuccessMessage() {
	    @SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,15) ;
		wait.until(ExpectedConditions.elementToBeClickable(chatSuccessMessage));
		return	driver.findElement(chatSuccessMessage);
		
		 
	}
	
	public WebElement getchatName() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(chatName));
		return	driver.findElement(chatName);
		 
	}
	
	public WebElement getchatEmail() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(chatEmail));
		return	driver.findElement(chatEmail);
		 
	}
	
	public WebElement getchatMessage() {
		@SuppressWarnings("deprecation")
		WebDriverWait	wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(chatMessage));
		return	driver.findElement(chatMessage);
		 
	}
	
	public WebElement getchatsubmit() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,10) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(chatsubmit));
		return	driver.findElement(chatsubmit);
		 
	}
	
}
