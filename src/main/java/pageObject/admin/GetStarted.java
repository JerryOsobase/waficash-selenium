package pageObject.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GetStarted {

	public WebDriver driver;
	
	public GetStarted(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	private By getStartedHeader= By.cssSelector("h2.card-title");
	private By faqButton= By.cssSelector("a[href*='faq']"); 
	private By dashboardTourButton= By.xpath("//button[@class='btn btn-outline-success']");
	private By faqPageHeader= By.cssSelector("h1.mt-5"); 
	private By dashboardTourPopupHeader= By.cssSelector("h3.popover-title"); 
	private By dashboardTourPreviousButton= By.xpath("//button[text()='« Prev']");
	private By dashboardTourNextButton= By.xpath("//button[text()='Next »']");
	private By dashboardTourEndButton= By.xpath("//button[text()='End tour']");
	
	
	public WebElement getStartedHeader() {
		return driver.findElement(getStartedHeader);
	}
	
	public WebElement getDashboardTourPreviousButton() {
		return driver.findElement(dashboardTourPreviousButton);
	}
	
	public WebElement getDashboardTourNextButton() {
		return driver.findElement(dashboardTourNextButton);
	}
	
	public WebElement getDashboardTourEndButton() {
		return driver.findElement(dashboardTourEndButton);
	}
	
	public WebElement getDashboardTourButton() {
		return driver.findElement(dashboardTourButton);
	}
	
	public WebElement getDashboardTourPopupHeader() {
		return driver.findElement(dashboardTourPopupHeader);
	}
	
	public WebElement getFaqPageHeader() {
		return driver.findElement(faqPageHeader);
	}
	
	public WebElement getFaqButton() {
		return driver.findElement(faqButton);
	}
}
