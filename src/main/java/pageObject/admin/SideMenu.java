package pageObject.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SideMenu {

	public WebDriver driver;
	
	public SideMenu(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	private By getStarted= By.cssSelector("a[href*='get-started']");
	private By dashboard= By.cssSelector("a[href*='analytics']");
	private By coupon= By.cssSelector("a[href*='coupons']");
	private By identity= By.cssSelector("a[href*='verifications/reports']");
	private By addresses= By.cssSelector("a[href*='addresses']");
	private By permit= By.cssSelector("a[href*='permits']");
	private By agents= By.cssSelector("a[href*='agents']");
	private By users= By.cssSelector("a[href*='users']");
	private By companies= By.cssSelector("a[href*='companies']");
	private By profile= By.cssSelector("a[href*='account/profile']");
	private By transactions= By.cssSelector("a[href*='transactions']");
	private By auditLog= By.cssSelector("a[href*='audit-log']");
	private By printLicense= By.cssSelector("a[href*='print-license-ids']");
	private By report= By.xpath("//li[@id='tour-transaction-view'] //a[@href='/reports']");
	private By settings= By.cssSelector("a[href*='settings']");
	private By footerUserProfile= By.xpath("//a[@title='User Profile']");
	private By footerBusinessProfile= By.xpath("//a[@title='Business Profile']");
	private By logout= By.xpath("//a[@title='Logout']");

	
	
	
	public WebElement getFooterUserProfile() {
		return driver.findElement(footerUserProfile);
	}
	
	public WebElement getLogout() {
		return driver.findElement(logout);
	}
	
	public WebElement getFooterBusinessProfile() {
		return driver.findElement(footerBusinessProfile);
	}
	
	
	public WebElement getStarted() {
		return driver.findElement(getStarted);
	}
	public WebElement getDashboard() {
		return driver.findElement(dashboard);
	}
	public WebElement getCoupon() {
		return driver.findElement(coupon);
	}
	public WebElement getIdentity() {
		return driver.findElement(identity);
	}
	public WebElement getAddresses() {
		return driver.findElement(addresses);
	}
	public WebElement getPermit() {
		return driver.findElement(permit);
	}
	public WebElement getAgents() {
		return driver.findElement(agents);
	}
	
	public WebElement getUsers() {
		return driver.findElement(users);
	}
	public WebElement getCompanies() {
		return driver.findElement(companies);
	}
	public WebElement getProfile() {
		return driver.findElement(profile);
	}
	
	public WebElement getTransactions() {
		return driver.findElement(transactions);
	}
	public WebElement getAuditLog() {
		return driver.findElement(auditLog);
	}
	public WebElement getPrintLicense() {
		return driver.findElement(printLicense);
	}
	public WebElement getReport() {
		return driver.findElement(report);
	}
	public WebElement getSettings() {
		return driver.findElement(settings);
	}
}
