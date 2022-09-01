package admin;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObject.admin.HomePage;
import resources.base;



public class SubmitButtonTest extends base{
	HomePage h;
	
	@BeforeClass
	public void initialize() throws IOException {
		//To initialize browser to run test from the base class under resources 
		driver= InitializeBrowser();
		//Navigate to url
		driver.get("http://127.0.0.1:8000/emp");
		//Maximize web browser page
		driver.manage().window().maximize();
	}

	@Test
	public void buttonTest() {
		//inherit the pageobject class
		h = new HomePage(driver);
		//Input Employee id
		h.getEmployeeId().sendKeys("1234");
		//Input Employee name
		h.getEmployeeName().sendKeys("jerry test");
		//Input Employee email address
		h.getEmployeeEmail().sendKeys("test@test.com");
		//Input Employee contact
		h.getEmployeePhoneNumber().sendKeys("08035678909");
		//Click on the submit button
		h.getSubmitButton().click();
		//Adding testNG assertion to verify user can view Add new Record button signifying user was taken to the next page
		Assert.assertTrue(h.getAddButton().isDisplayed());
		
	}
	
	

  @AfterClass public void terminate() { 
	  driver.close(); 
  } 
  }
 



