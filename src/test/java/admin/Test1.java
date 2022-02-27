package admin;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObject.admin.AdminLogin;
import pageObject.admin.AgentProfile;
import pageObject.admin.Agents;
import pageObject.admin.Coupon;
import pageObject.admin.Reports;
import pageObject.admin.SideMenu;
import resources.base;

public class Test1 extends base{
 AdminLogin al;
 SideMenu sm;
 Coupon cp;
 Reports r;
 AdminLoginTest alt;
 Agents a;
 AgentProfile ap;
 
 
	/*@BeforeClass
	public void initialize() throws IOException {
		driver= InitializeBrowser();
		driver.get("https://lslb.youverify.co/auth/login");
		driver.manage().window().maximize();
}
	
	
	@Test
	public void dodod() {
		al = new AdminLogin(driver);
		al.getEmailAddressField().sendKeys("base4jerry@gmail.com");
		al.getContinueButton().click();
		al.getPasswordField().sendKeys("Osobase4@");
		al.getLoginButton().click();
		 sm = new SideMenu(driver);  
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
		 List<WebElement> active= driver.findElements(By.xpath("//li[@class='page-item pagination-page-nav active']"));
		 while(active.size()==1) {
			 driver.findElement(By.xpath("//li[@class='page-item pagination-page-nav active'] //following-sibling::li")).click();
			  if( driver.findElement(By.xpath("//li[@class='page-item pagination-page-nav active'] //following-sibling::li")).getText().contains("Next")) {
				 break;
			 }
		 }
	}*/
	
	/*@Test(dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void djfkjd(HashMap<String, String> data) throws InterruptedException{
		al = new AdminLogin(driver);
		sm = new SideMenu(driver);
		alt = new AdminLoginTest();
		alt.ValidateSuccessfulAdminLogin1(driver, data);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getAgents());
		a = new  Agents(driver);
		ap = new AgentProfile(driver);
		Actions action = new Actions(driver);
		 int nin = 0;
		 int bvn = 0;
		 int driver1 = 0;
		 int interna = 0;
		 
		 Select select = new Select(a.getStatusField());
		 select.selectByVisibleText("Completed");
		 a.getFilterButton().click();
		 Thread.sleep(5000);
		 for(int q=0; q<a.getStatus().size(); q++) {
			 executor.executeScript("arguments[0].click();", a.getStatus().get(q));
			 executor.executeScript("arguments[0].click();", ap.getApplicationId());
			 Thread.sleep(3000);
			 executor.executeScript("arguments[0].click();", a.gurantor());
			 Thread.sleep(3000);
			 
			 if(a.searchBar2().getText().equalsIgnoreCase("National Identification Number")) {
				 nin++;
			 }
			 else if(a.searchBar2().getText().equalsIgnoreCase("Driver's License Number")) {
				 driver1++;
				}
			 else if(a.searchBar2().getText().equalsIgnoreCase("International Passport Number")) {
				 interna++;
				}
			 else if(a.searchBar2().getText().equalsIgnoreCase("Bank Verification Number")) {
				 bvn++;
				}
			 driver.navigate().back();
			 driver.navigate().back();
			 driver.navigate().back();
			 
	}
				while(a.getActivePagination().size()==1) {
				Thread.sleep(3000);
				
				a.getNextPagination().click();
				Thread.sleep(3000);
				 for(int r=0; r<a.getStatus().size(); r++) {
					 executor.executeScript("arguments[0].click();", a.getStatus().get(r));
					 executor.executeScript("arguments[0].click();", ap.getApplicationId());
					 Thread.sleep(3000);
					 executor.executeScript("arguments[0].click();", a.gurantor());
					 Thread.sleep(3000);
					 if(a.searchBar2().getText().equalsIgnoreCase("National Identification Number")) {
						 nin++;
					 }
					 else if(a.searchBar2().getText().equalsIgnoreCase("Driver's License Number")) {
						 driver1++;
						}
					 else if(a.searchBar2().getText().equalsIgnoreCase("International Passport Number")) {
						 interna++;
						}
					 else if(a.searchBar2().getText().equalsIgnoreCase("Bank Verification Number")) {
						 bvn++;
						}
					 driver.navigate().back();
					 driver.navigate().back();
					 driver.navigate().back();
					 
			}
				  if(a.getNextPagination().getText().contains("Next")) {
					 break;
				 }
				  
		 }
		
		System.out.println(nin + " nin");
		System.out.println(bvn + " bvn");
		System.out.println(driver1 + " driver");
		System.out.println(interna + " interna");
	}
	
	@Test(dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void djfkjd(HashMap<String, String> data) throws InterruptedException{
		al = new AdminLogin(driver);
		sm = new SideMenu(driver);
		alt = new AdminLoginTest();
		alt.ValidateSuccessfulAdminLogin1(driver, data);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getAgents());
		a = new  Agents(driver);
		ap = new AgentProfile(driver);
		Actions action = new Actions(driver);
		 int nin = 0;
		 int bvn = 0;
		 int driver1 = 0;
		 int interna = 0;
		 
		 Select select = new Select(a.getStatusField());
		 select.selectByVisibleText("Guarantor Outstanding");
		 a.getFilterButton().click();
		 Thread.sleep(5000);
		 for(int q=0; q<a.getStatus().size(); q++) {
			 executor.executeScript("arguments[0].click();", a.getStatus().get(q));
			 executor.executeScript("arguments[0].click();", ap.getApplicationId());
			 Thread.sleep(3000);
			 
			 if(a.searchBar1().getText().equalsIgnoreCase("National Identification")) {
				 nin++;
			 }
			 else if(a.searchBar1().getText().equalsIgnoreCase("Driver's License")) {
				 driver1++;
				}
			 else if(a.searchBar1().getText().equalsIgnoreCase("International Passport")) {
				 interna++;
				}
			 else if(a.searchBar1().getText().equalsIgnoreCase("Bank Verification")) {
				 bvn++;
				}
			 driver.navigate().back();
			 driver.navigate().back();
			 
	}
				while(a.getActivePagination().size()==1) {
				Thread.sleep(3000);
				
				a.getNextPagination().click();
				Thread.sleep(3000);
				 for(int r=0; r<a.getStatus().size(); r++) {
					 executor.executeScript("arguments[0].click();", a.getStatus().get(r));
					 executor.executeScript("arguments[0].click();", ap.getApplicationId());
					 Thread.sleep(3000);
					 if(a.searchBar1().getText().equalsIgnoreCase("National Identification")) {
						 nin++;
					 }
					 else if(a.searchBar1().getText().equalsIgnoreCase("Driver's License")) {
						 driver1++;
						}
					 else if(a.searchBar1().getText().equalsIgnoreCase("International Passport")) {
						 interna++;
						}
					 else if(a.searchBar1().getText().equalsIgnoreCase("Bank Verification")) {
						 bvn++;
						}
					 driver.navigate().back();
					 driver.navigate().back();
					 
			}
				  if(a.getNextPagination().getText().contains("Next")) {
					 break;
				 }
				  
		 }
		
		System.out.println(nin + " nin");
		System.out.println(bvn + " bvn");
		System.out.println(driver1 + " driver");
		System.out.println(interna + " interna");
	}*/
	
	@Test
	public void sjfie() {
		System.out.println("boy boy");
		}
	
	

	}



