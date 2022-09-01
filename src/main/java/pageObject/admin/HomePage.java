package pageObject.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
		public WebDriver driver;
			
			public HomePage(WebDriver driver) {
				// TODO Auto-generated constructor stub
				this.driver=driver;
			}
			
			private By employeeId= By.name("id");
			private By employeeName= By.name("name");
			private By employeeEmail= By.name("email");
			private By employeePhoneNumber= By.name("Phone_Number");
			private By submitButton= By.xpath("//button[text()='Submit']");
			private By addButton= By.cssSelector("a[href*='emp']");
			
			
			public WebElement getEmployeeId() {
				return driver.findElement(employeeId);
			}
			public WebElement getEmployeeName() {
				return driver.findElement(employeeName);
			}
			
			public WebElement getEmployeeEmail() {
				return driver.findElement(employeeEmail);
			}
			
			public WebElement getEmployeePhoneNumber() {
				return driver.findElement(employeePhoneNumber);
			}
			
			public WebElement getSubmitButton() {
				return driver.findElement(submitButton);
			}
			
			public WebElement getAddButton() {
				return driver.findElement(addButton);
			}
}
			