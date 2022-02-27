package pageObject.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AgentProfile {
public WebDriver driver;
	
	public AgentProfile(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	private By agentProfileHeader= By.xpath("//h3[@class='text-themecolor m-b-0 m-t-0 app-title']");
	private By resendInviteButton= By.xpath("//button[text()=' Resend Invite ']");  
	private By applicationTableHeader= By.xpath("//div[@class='col-md-8'] //th");
	private By applicationId= By.xpath("//tr[@class='link-row']/td[2]/a");
	private By applicationstatus= By.xpath("//tr[@class='link-row']/td[5]/a"); 
	private By dissociateButton= By.xpath("//button[text()=' Dissociate from agent ']");
	private By dissociateCommentField= By.xpath("//textarea");
	private By submitButton= By.xpath("//button[text()=' Submit ']");
	private By cancelButton= By.xpath("//button[text()=' Cancel ']"); 
	private By dissociationPopUpMessage= By.id("swal2-title"); 
	private By dissociatePopUpButton= By.xpath("//button[text()='Dissociate from agent']"); 
	private By popUpDissociateCancelButton= By.xpath("//button[text()='No, cancel!']");
	private By allAplicationTab= By.xpath("//a[text()=' All Applications ']");
	private By allCommentTab= By.xpath("//a[text()=' All Comments ']");
	private By commentField= By.xpath("//textarea[@placeholder='Enter Comment Here']");
	private By addCommentButton= By.xpath("//button[text()=' Add Comment ']");
	
	 
	public WebElement getAgentProfileHeader() {
		return driver.findElement(agentProfileHeader);
	}
	
	public WebElement getAddCommentButton() {
		return driver.findElement(addCommentButton);
	}
	
	public WebElement getCommentField() {
		return driver.findElement(commentField);
	}
	
	public WebElement getAllCommentTab() {
		return driver.findElement(allCommentTab);
	}
	
	public WebElement getAllAplicationTab() {
		return driver.findElement(allAplicationTab);
	}
	
	public WebElement getPopUpDissociateCancelButton() {
		return driver.findElement(popUpDissociateCancelButton);
	}
	
	
	public WebElement getDissociatePopUpButton() {
		return driver.findElement(dissociatePopUpButton);
	}
	
	public WebElement getDissociationPopUpMessage() {
		return driver.findElement(dissociationPopUpMessage);
	}
	
	public WebElement getCancelButton() {
		return driver.findElement(cancelButton);
	}
	
	public WebElement getSubmitButton() {
		return driver.findElement(submitButton);
	}
	
	public WebElement getDissociateCommentField() {
		return driver.findElement(dissociateCommentField);
	}
	
	public WebElement getDissociateButton() {
		return driver.findElement(dissociateButton);
	}
	
	public WebElement getApplicationstatus() {
		return driver.findElement(applicationstatus);
	}
	
	public WebElement getApplicationId() {
		return driver.findElement(applicationId);
	}
	
	public WebElement getApplicationTableHeader() {
		return driver.findElement(applicationTableHeader);
	}
	
	public WebElement getResendInviteButton() {
		return driver.findElement(resendInviteButton);
	}
	
}
