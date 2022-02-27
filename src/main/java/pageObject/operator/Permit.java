package pageObject.operator;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Permit {
	public WebDriver driver;
	
	public Permit(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	private By dropdownLabel= By.xpath("//label[@for='status']");
	private By statusField= By.xpath("//following-sibling::select");
	private By filterButton = By.xpath("//button[text()=' Filter ']");
	private By searchBar = By.xpath("//input[@placeholder='Search using name']");
	private By addPermitButton = By.xpath("//button[text()=' Add Permit ']");
	private By setPermitButton = By.xpath("//button[text()=' Set Permit Fee ']");
	private By permitFeeField = By.xpath("//input[@placeholder='Enter Permit Fee']");
	private By addPermitField = By.xpath("//input[@placeholder='Enter Permit Name']");
	private By addPermitConfirmationButton = By.xpath("//button[text()=' Add New Permit ']"); 
	private By status = By.xpath("//tr/td[2]");
	private By tableHeader = By.xpath("//tr/th"); 
	private By statusButton = By.xpath("//tr/td[4]/button");
	private By confirmationMessage= By.id("swal2-content");
	private By confirmationHeader= By.id("swal2-title"); 
	private By popUpConfirmButton = By.xpath("//button[@class='swal2-confirm btn btn-warning mr-3']");
	private By popUpCancelButton = By.xpath("//button[@class='swal2-cancel btn btn-danger']");
	
	public WebElement getStatusField() {
		return driver.findElement(statusField);
	}
	
	public WebElement getPopUpConfirmButton() {
		return driver.findElement(popUpConfirmButton);
	}
	
	public WebElement getPopUpCancelButton() {
		return driver.findElement(popUpCancelButton);
	}
	
	public WebElement getConfirmationHeader() {
		return driver.findElement(confirmationHeader);
	}
	
	public WebElement getConfirmationMessage() {
		return driver.findElement(confirmationMessage);
	}
	
	public WebElement getStatusButton() {
		return driver.findElement(statusButton);
	}
	
	public List<WebElement> getTableHeader() {
		return driver.findElements(tableHeader);
	}
	
	public List<WebElement> getStatus() {
		return driver.findElements(status);
	}
	
	public WebElement getAddPermitConfirmationButton() {
		return driver.findElement(addPermitConfirmationButton);
	}
	
	public WebElement getAddPermitField() {
		return driver.findElement(addPermitField);
	}
	
	public WebElement getPermitFeeField() {
		return driver.findElement(permitFeeField);
	}
	
	public WebElement getAddPermitButton() {
		return driver.findElement(addPermitButton);
	}
	
	public WebElement getSetPermitButton() {
		return driver.findElement(setPermitButton);
	}
	
	public WebElement getSearchBar() {
		return driver.findElement(searchBar);
	}
	
	public WebElement getFilterButton() {
		return driver.findElement(filterButton);
	}
	
	public List<WebElement> getDropdownLabel() {
		return driver.findElements(dropdownLabel);
	}
}
