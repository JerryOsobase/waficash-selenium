package pageObject.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashBoard {
public WebDriver driver;
	
	public DashBoard(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	private By verifiedAgent= By.xpath("//div[@class='card-block text-center']");
	private By applicationMetrics= By.xpath("//div[@class='row'][2] //div[@class='b-all rounded dashboard-metric-card p-2']");
	private By agentMetrics= By.xpath("//div[@class='row'][3] //div[@class='b-all rounded dashboard-metric-card p-2']");
	private By logout= By.cssSelector("a[href*='logout']");
	private By profileDropdown= By.xpath("//li[@class='nav-item dropdown']/a");
	private By dashBoardHeader= By.xpath("//h3[@class='text-themecolor m-b-0 m-t-0 app-title']");
	
	
	public WebElement getVerifiedAgent() {
		return driver.findElement(verifiedAgent);
	}
	
	public WebElement getDashBoardHeader() {
		return driver.findElement(dashBoardHeader);
	}
	
	public WebElement getProfileDropdown() {
		return driver.findElement(profileDropdown);
	}
	
	public WebElement getLogout() {
		return driver.findElement(logout);
	}
	
	public WebElement getApplicationMetrics() {
		return driver.findElement(applicationMetrics);
	}
	
	public WebElement getAgentMetrics() {
		return driver.findElement(agentMetrics);
	}
}
