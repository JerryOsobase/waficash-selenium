package pageObject.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ApplicationReport {
public WebDriver driver;
	
	public ApplicationReport(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	private By uploadPhotoButton= By.xpath("//button[text()=' Upload Photo ']"); 
	private By phoneNumberEditButton= By.xpath("//h6[1]/button"); 
	private By emailEditButton= By.xpath("//h6[2]/button"); 
	private By saveButton= By.xpath("//button[text()=' Save ']"); 
	private By cancelButton= By.xpath("//button[text()=' Cancel ']"); 
	private By retryAgentButton= By.xpath("//button[text()=' Retry Agent Verification ']"); 
	private By downloadReportButton= By.xpath("//button[text()=' Download Full Report ']"); 
	private By formTab= By.xpath("//div[@class='col-xs-12 col-sm-12 col-md-12 col-lg-8 col-xlg-9'] //li/a");
	private By agentGuarantorinfo= By.xpath("//div[@class='col-xs-12']");
	private By agentGuarantorEditButton= By.xpath("//div[@class='col-xs-12']/following-sibling::div/button");
	private By reportProgress= By.cssSelector("div.timeline-heading");
	private By applicationTab= By.xpath("//ul[@class='nav nav-tabs customtab']/li/a");
	private By viewAnywayButton= By.xpath("//a[text()=' View anyway ']");
	private By verificationfailedMessage= By.xpath("//div[@class='col-xs-12 m-b-10'] //p");
	private By pageHeader= By.xpath("//h3[@class='text-themecolor m-b-0 m-t-0 app-title']");
	
	
	
	
	public WebElement getUploadPhotoButton() {
		return driver.findElement(uploadPhotoButton);
	}
	
	public WebElement getPageHeader() {
		return driver.findElement(pageHeader);
	}
	
	public WebElement getVerificationfailedMessage() {
		return driver.findElement(verificationfailedMessage);
	}
	
	public WebElement getViewAnywayButton() {
		return driver.findElement(viewAnywayButton);
	}
	
	public List<WebElement> getViewAnywayButtons() {
		return driver.findElements(viewAnywayButton);
	}
	
	public WebElement getApplicationTab() {
		return driver.findElement(applicationTab);
	}
	
	public WebElement getReportProgress() {
		return driver.findElement(reportProgress);
	}
	
	public WebElement getAgentGuarantorEditButton() {
		return driver.findElement(agentGuarantorEditButton);
	}
	
	public List<WebElement> getAgentGuarantorinfo() {
		return driver.findElements(agentGuarantorinfo);
	}
	
	public WebElement getFormTab() {
		return driver.findElement(formTab);
	}
	
	public WebElement getDownloadReportButton() {
		return driver.findElement(downloadReportButton);
	}
	
	public WebElement getRetryAgentButton() {
		return driver.findElement(retryAgentButton);
	}
	
	public WebElement getCancelButton() {
		return driver.findElement(cancelButton);
	}
	
	public WebElement getSaveButton() {
		return driver.findElement(saveButton);
	}
	
	public WebElement getEmailEditButton() {
		return driver.findElement(emailEditButton);
	}
	
	public WebElement getPhoneNumberEditButton() {
		return driver.findElement(phoneNumberEditButton);
	}
}
