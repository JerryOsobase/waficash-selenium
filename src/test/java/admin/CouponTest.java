package admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.admin.Coupon;
import pageObject.admin.SideMenu;
import resources.base;

public class CouponTest extends base {
	Coupon c;
	AdminLoginTest alt;
	SideMenu sm;
	SoftAssert soft;
	CouponTest ct;

	@BeforeClass
	public void initialize() throws IOException {
		driver= InitializeBrowser();
		driver.get(prop.getProperty("AdminOpUrl"));
		driver.manage().window().maximize();
	}
	
	@Test(priority=1, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void ValidateCouponPage(HashMap<String, String> data) {
	//Verify user is taken to the coupon page
		alt = new AdminLoginTest();
		alt.ValidateSuccessfulAdminLogin1(driver, data);
		sm = new SideMenu(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
	 c = new Coupon(driver);
	 soft = new SoftAssert();
	 soft.assertTrue(c.getAgentSearchBar().isDisplayed());
	 soft.assertEquals(c.getCouponHeader().getText(), "Coupons");
	 soft.assertAll();
	}
	
	@Test(priority=2)
	public void ValidateAutosuggestiveSearchBar() {
		//Verify the search bar is autosuggestive
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
		 c.getAgentSearchBar().click();
		 Assert.assertTrue(c.getAgentSearchBar().getAttribute("aria-expanded").equals("true"));
	}
	

	@Test(priority=3)
	public void ValidateCategoryDropdownList() {
	//Verify the Category dropdown contains the following;(Single & Multiple)
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
			Select couponDropDown = new Select(c.getCategoryRequestCoupon());
			List<WebElement> allOptions= couponDropDown.getOptions();
			List<String> categoryOptions= allOptions.stream().map(a->a.getText()).collect(Collectors.toList());
			String[] categoryTypeList= {"Single", "Multiple"};
			List<String> categoryTypeListArray= Arrays.asList(categoryTypeList);
			Assert.assertEquals(categoryOptions, categoryTypeListArray);
		 }
	
	@Test(priority=4)
	public void ValidateSingleCategoryQuantityField() {
	//Verify when user selects "Single" as the category, the quantity field shows 1 and it is not editable
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
				Select select = new Select(c.getCategoryRequestCoupon());
				select.selectByVisibleText("Single");
		Assert.assertFalse(c.getQuantityField().isEnabled());
	}
	
	@Test(priority=5)
	public void ValidateSingleAgentField() {
		//Verify user can only view the Agent name field only when user selects "Single" as the category
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
				Select select = new Select(c.getCategoryRequestCoupon());
				select.selectByVisibleText("Single");
				Assert.assertTrue(c.getAgentNameField().isDisplayed());
	}
	
	@Test(priority=6)
	public void ValidateMultipleCategoryQuanityField() {
		//Verify user can edit the quantity field only when the user selects "Multiple" as the category
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
				Select select = new Select(c.getCategoryRequestCoupon());
				select.selectByVisibleText("Multiple");
		Assert.assertTrue(c.getQuantityField().isEnabled());
	}
	
	@Test(priority=7)
	public void EmptyDateField() {
		//Verify user is unable to request for coupon if the start and end dates are not selected
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
				Select couponDropDown = new Select(c.getCategoryRequestCoupon());
				couponDropDown.selectByVisibleText("Multiple");
				ct = new CouponTest();
				c.getCouponCodeField().sendKeys(Keys.chord(Keys.COMMAND, "a"), ct.setCouponCode());
				c.getQuantityField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "5");
				Select discountDropDown = new Select(c.getDiscountTypeField());
				discountDropDown.selectByValue("percent");
				c.getDiscountValueField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "100");
				Assert.assertFalse(c.getQuotationButton().isEnabled());
	}
	
	@Test(priority=8)
	public void ValidateEndDateCheck() {
		//Verify user is unable to select an end date if no start date was selected
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
		 soft = new SoftAssert();
	    soft.assertFalse(c.getEndDateField().isEnabled());
		 ct = new CouponTest();
		 ct.setStartDate(driver);
		 soft.assertTrue(c.getEndDateField().isEnabled());
		 soft.assertAll();
	}
	
	@Test(priority=9, dataProvider="invalidGetData", dataProviderClass=AdminLoginTest.class)
		public void ValidateMultipleCategoryQuanityFieldFormat(HashMap<String, String> data) {
		//Check system response when user input 0 or 1 as the quantity but selected Multiple as the category
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
				Select couponDropDown = new Select(c.getCategoryRequestCoupon());
				couponDropDown.selectByVisibleText("Multiple");
				ct = new CouponTest();
				c.getCouponCodeField().sendKeys(Keys.chord(Keys.COMMAND, "a"), ct.setCouponCode());
				c.getQuantityField().sendKeys(Keys.chord(Keys.COMMAND, "a"), data.get("invalidMultipleCouponQuantity"));
				ct.setStartDate(driver);
				ct.setEndDate(driver);
				Select discountDropDown = new Select(c.getDiscountTypeField());
				discountDropDown.selectByValue("percent");
				c.getDiscountValueField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "100");
				Assert.assertFalse(c.getQuotationButton().isEnabled());
	}
	
	@Test(priority=10)
	public void ValidateDiscountTypeDropdownList() {
		//Verify discount type dropdown contains the following (% discount, # discount)
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
			Select discountDropDown = new Select(c.getDiscountTypeField());
			List<WebElement> allOptions= discountDropDown.getOptions();
			List<String> discountOptions= allOptions.stream().map(a->a.getText()).collect(Collectors.toList());
			System.out.println(discountOptions);
			String[] categoryTypeList= {"% discount", "₦ discount"};
			List<String> categoryTypeListArray= Arrays.asList(categoryTypeList);
			Assert.assertEquals(discountOptions, categoryTypeListArray);
	}
	
	@Test(priority=11)
	public void EmptyCouponCodeField() {
		//Verify user is unable to request for coupon if the coupon code field is empty
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
				Select couponDropDown = new Select(c.getCategoryRequestCoupon());
				couponDropDown.selectByVisibleText("Multiple");
				ct = new CouponTest();
				c.getCouponCodeField().sendKeys(Keys.chord(Keys.chord(Keys.COMMAND, "a"), Keys.DELETE));
				c.getQuantityField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "5");
				ct.setStartDate(driver);
				ct.setEndDate(driver);
				Select discountDropDown = new Select(c.getDiscountTypeField());
				discountDropDown.selectByValue("percent");
				c.getDiscountValueField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "100");
				Assert.assertFalse(c.getQuotationButton().isEnabled());
	}
	
	@Test(priority=12)
		public void EmptyDiscountValueField() {
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
				Select couponDropDown = new Select(c.getCategoryRequestCoupon());
				couponDropDown.selectByVisibleText("Multiple");
				ct = new CouponTest();
				c.getCouponCodeField().sendKeys(Keys.chord(Keys.COMMAND, "a"), ct.setCouponCode());
				c.getQuantityField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "5");
				ct.setStartDate(driver);
				ct.setEndDate(driver);
				Select discountDropDown = new Select(c.getDiscountTypeField());
				discountDropDown.selectByValue("percent");
				c.getDiscountValueField().sendKeys(Keys.chord(Keys.COMMAND, "a"), Keys.DELETE);
				Assert.assertFalse(c.getQuotationButton().isEnabled());
		}

	@Test(priority=13)
	public void ValidateDiscountPercentageValueFormat() {
		//Verify if the Discount type selected is "% discount" then user is unable to input more than 100 as the discount value
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
				Select couponDropDown = new Select(c.getCategoryRequestCoupon());
				couponDropDown.selectByVisibleText("Multiple");
				ct = new CouponTest();
				c.getCouponCodeField().sendKeys(Keys.chord(Keys.COMMAND, "a"), ct.setCouponCode());
				c.getQuantityField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "5");
				ct.setStartDate(driver);
				ct.setEndDate(driver);
				Select discountDropDown = new Select(c.getDiscountTypeField());
				discountDropDown.selectByValue("percent");
				c.getDiscountValueField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "101");
				Assert.assertFalse(c.getQuotationButton().isEnabled());
	}

	@Test(priority=14)
	public void ValidateDiscountAmountValueFormat() {
		//Verify if the Discount type selected is "# discount" then user is unable to input more than 100 as the discount value
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
				Select couponDropDown = new Select(c.getCategoryRequestCoupon());
				couponDropDown.selectByVisibleText("Multiple");
				ct = new CouponTest();
				c.getCouponCodeField().sendKeys(Keys.chord(Keys.COMMAND, "a"), ct.setCouponCode());
				c.getQuantityField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "5");
				ct.setStartDate(driver);
				ct.setEndDate(driver);
				Select discountDropDown = new Select(c.getDiscountTypeField());
				discountDropDown.selectByValue("amount");
				c.getDiscountValueField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "12501");
				Assert.assertFalse(c.getQuotationButton().isEnabled());
	}
	
	@Test(priority=15)
	public void SuccessfulSingleCouponRequestOnline() throws InterruptedException {
		//Verify user can request a single coupon
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
				Select couponDropDown = new Select(c.getCategoryRequestCoupon());
				couponDropDown.selectByVisibleText("Single");
				ct = new CouponTest();
				c.getCouponCodeField().sendKeys(Keys.chord(Keys.COMMAND, "a"), ct.setCouponCode());
				ct.setStartDate(driver);
				ct.setEndDate(driver);
				Select discountDropDown = new Select(c.getDiscountTypeField());
				discountDropDown.selectByValue("percent");
				c.getDiscountValueField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "100");
				c.getAgentNameField().sendKeys("jerry");
				Thread.sleep(3000);
				for(WebElement agentList : c.getAgentFieldDropdown()) {
					if(agentList.getText().contains("batocox")) {
						agentList.click();
						break;
					}
				}
			c.getQuotationButton().click();
			c.getPopUpRequestButton().click();
			c.getPopUpRequestButton().click();
			driver.switchTo().frame(c.getPaystackPopUpFrame());
			executor.executeScript("arguments[0].click();", c.getSuccessTestCard());
			executor.executeScript("arguments[0].click();", c.getPaystackSubmitButton());
			driver.switchTo().defaultContent();
			Thread.sleep(3000);
			Assert.assertEquals(c.getPromptMessage().getText(), "Success\n"
					+ "Coupon payment successful");
	}
	
	@Test(priority=16)
	public void SuccessfulMultipleCouponRequestOnline() throws InterruptedException {
		//Verify user can request a multiple coupons
				sm = new SideMenu(driver);
				c = new Coupon(driver);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				 executor.executeScript("arguments[0].click();", sm.getCoupon());
						Select couponDropDown = new Select(c.getCategoryRequestCoupon());
						couponDropDown.selectByVisibleText("Multiple");
						c.getQuantityField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "5");
						ct = new CouponTest();
						c.getCouponCodeField().sendKeys(Keys.chord(Keys.COMMAND, "a"), ct.setCouponCode());
						ct.setStartDate(driver);
						ct.setEndDate(driver);
						Select discountDropDown = new Select(c.getDiscountTypeField());
						discountDropDown.selectByValue("percent");
						c.getDiscountValueField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "100");
					c.getQuotationButton().click();
					c.getPopUpRequestButton().click();
					c.getPopUpRequestButton().click();
					driver.switchTo().frame(c.getPaystackPopUpFrame());
					executor.executeScript("arguments[0].click();", c.getSuccessTestCard());
					executor.executeScript("arguments[0].click();", c.getPaystackSubmitButton());
					driver.switchTo().defaultContent();
					Thread.sleep(3000);
					Assert.assertEquals(c.getPromptMessage().getText(), "Success\n"
							+ "Coupon payment successful");
	}
	
	@Test(priority=17)
	public void ValidateGeneratedCouponList() throws InterruptedException {
		//Verify user can view list of Generated coupons
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
		 Thread.sleep(5000);
		String splitter[]= c.getTableFooterText().getText().split("of");
		 long count =  c.getCode().stream().count();
			
		while(c.getActivePagination().size()==1) {
			Thread.sleep(3000);
			
			c.getNextPagination().click();
			Thread.sleep(3000);
			 count += c.getCode().stream().count();
			
			  if(c.getNextPagination().getText().contains("Next")) {
				 break;
			 }
			  
		 }
		Assert.assertEquals(splitter[1].trim(), count+ " entries");	
	}
	
	@Test(priority=18)
	public void ValidateGenerateCouponCategoryDropdownList() {
		//Verify user can view the following when user clicks on the Category dropdown; (All Single, Multiple)
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
			Select couponDropDown = new Select(c.getGeneratedCouponCategory());
			List<WebElement> allOptions= couponDropDown.getOptions();
			List<String> categoryOptions= allOptions.stream().map(a->a.getText()).collect(Collectors.toList());
			String[] categoryTypeList= {"-- select category --", "All", "Single", "Multiple"};
			List<String> categoryTypeListArray= Arrays.asList(categoryTypeList);
			Assert.assertEquals(categoryOptions, categoryTypeListArray);
	}
	
	@Test(priority=19)
	public void ValidateSingleCouponFilter() throws InterruptedException {
		//Verify user can filter by the single category option
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
		 Thread.sleep(5000);
		 int single = 0;
		 int multiple = 0;
		 for(int q=0; q<c.getQuantity().size(); q++) {
			 String splitter[] = c.getQuantity().get(q).getText().split("/");
			 if(splitter[1].trim().equals("1")) {
				 single++;
			 }
			 else {
				 multiple++;
			 }
			 }
			
		while(c.getActivePagination().size()==1) {
				Thread.sleep(3000);
				
				c.getNextPagination().click();
				Thread.sleep(3000);
				 for(int r=0; r<c.getQuantity().size(); r++) {
					 String splitter1[] = c.getQuantity().get(r).getText().split("/");
					 if(splitter1[1].trim().equals("1")) {
						 single++;
					 }
					 else {
						 multiple++;
					 }
				 
				 }
				  if(c.getNextPagination().getText().contains("Next")) {
					 break;
				 }
		 }
		Select couponDropDown = new Select(c.getGeneratedCouponCategory());
		couponDropDown.selectByVisibleText("Single");
		 executor.executeScript("arguments[0].click();", c.getFilterButton());
		 Thread.sleep(5000);
		long count =  c.getCode().stream().count();
		
			while(c.getActivePagination().size()==1) {
				
				
				c.getNextPagination().click();
				Thread.sleep(3000);
				count += c.getCode().stream().count();
				
				
				  if(c.getNextPagination().getText().contains("Next")) {
					 break;
				 }
				  
			 }
			Assert.assertEquals(single, count);
			
		}
	
	@Test(dependsOnMethods="ValidateSingleCouponFilter")
	public void ValidateClearSingleList() throws InterruptedException{
		//Verify user can clear filter for single filtered list
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", c.getClearButton());
		 Thread.sleep(5000);
		String splitter[]= c.getTableFooterText().getText().split("of");
		 long count =  c.getCode().stream().count();
			
		while(c.getActivePagination().size()==1) {
			
			
			c.getNextPagination().click();
			Thread.sleep(3000);
			 count += c.getCode().stream().count();
			
			  if(c.getNextPagination().getText().contains("Next")) {
				 break;
			 }
			  
		 }
		Assert.assertEquals(splitter[1].trim(), count+ " entries");	
	}
	
	@Test(priority=20)
	public void ValidateMultipleCouponFilter() throws InterruptedException {
		//Verify user can filter by the multiple category option
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
		 Thread.sleep(5000);
		 int single = 0;
		 int multiple = 0;
		 for(int q=0; q<c.getQuantity().size(); q++) {
			 String splitter[] = c.getQuantity().get(q).getText().split("/");
			 if(splitter[1].trim().equals("1")) {
				 single++;
			 }
			 else {
				 multiple++;
			 }
			 }
			
		while(c.getActivePagination().size()==1) {
				Thread.sleep(3000);
				
				c.getNextPagination().click();
				Thread.sleep(3000);
				 for(int r=0; r<c.getQuantity().size(); r++) {
					 String splitter1[] = c.getQuantity().get(r).getText().split("/");
					 if(splitter1[1].trim().equals("1")) {
						 single++;
					 }
					 else {
						 multiple++;
					 }
				 
				 }
				  if(c.getNextPagination().getText().contains("Next")) {
					 break;
				 }
		 }
		
		Select couponDropDown = new Select(c.getGeneratedCouponCategory());
		couponDropDown.selectByVisibleText("Multiple");
		 executor.executeScript("arguments[0].click();", c.getFilterButton());
		 Thread.sleep(5000);
		long count =  c.getCode().stream().count();
			
			while(c.getActivePagination().size()==1) {
				
				
				c.getNextPagination().click();
				Thread.sleep(3000);
				count += c.getCode().stream().count();
				
				  if(c.getNextPagination().getText().contains("Next")) {
					 break;
				 }
				  
			 }
			Assert.assertEquals(multiple, count);
		}
		
	
	@Test(dependsOnMethods="ValidateMultipleCouponFilter")
	public void ValidateClearMultipleList() throws InterruptedException{
		//Verify user can clear filter for multiple filtered list
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", c.getClearButton());
		 Thread.sleep(5000);
		String splitter[]= c.getTableFooterText().getText().split("of");
		 long count =  c.getCode().stream().count();
			
		while(c.getActivePagination().size()==1) {
			
			
			c.getNextPagination().click();
			Thread.sleep(3000);
			 count += c.getCode().stream().count();
			
			  if(c.getNextPagination().getText().contains("Next")) {
				 break;
			 }
			  
		 }
		System.out.println(count+ " entries");
		Assert.assertEquals(splitter[1].trim(), count+ " entries");	
	}
	
	@Test(priority=21)
	public void ValidateGeneratedListHeader() {
		//Verify the generated coupon list has the following headers;(Code, Used/Quantity, Date)
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
		 List<String> originalGeneratedListHeader= c.getGeneratedListHeader().stream().map(a->a.getText()).collect(Collectors.toList());
		 String[] expectedGeneratedListHeader= {"#", "Code", "Used/Quantity", "Date"};
			List<String> expectedHeaderArray= Arrays.asList(expectedGeneratedListHeader);
			Assert.assertEquals(originalGeneratedListHeader, expectedHeaderArray); 
	}
	@Test(priority=22)
	public void ValidateGeneratedCouponCardResponse() throws InterruptedException {
		//Check system response when user clicks on a coupon code
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		soft =new SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
		 for(int q=0; q<c.getCode().size(); q++) {
			 Boolean staleElement = true;
			 while(staleElement){

				  try{

					  executor.executeScript("arguments[0].click();", c.getCode().get(q));
					  List<String> originalcouponDetails= c.getCouponDetails().stream().map(a->a.getText()).collect(Collectors.toList());
						 String[] couponDetails= {"Status", "Code", "Discount", "Used/Quantity", "Initiated By", "Date"};
							List<String> expectedcouponDetailsArray= Arrays.asList(couponDetails);
							soft.assertEquals(originalcouponDetails, expectedcouponDetailsArray);

				     staleElement = false;


				  } catch(StaleElementReferenceException e){

				    staleElement = true;

				  }

				}
		 }
			 
	
		 soft.assertAll();
	
	
	}
	
	@Test(priority=23)
	public void ValidateDownloadCSVButton() throws InterruptedException {
		//Check system response when user clicks on the "Download CSV" button
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		soft = new SoftAssert();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
		 for(int g=0; g<2; g++) {
			 Boolean staleElement = true;
			 while(staleElement){

				  try{
			 c.getCode().get(g).click();
			 c.getDownloadCsvButton().click();
			 Thread.sleep(3000);
			soft.assertEquals(c.getPromptMessage().getText(), "Success\n"
					+ "Coupon generated CSV has been sent to your mail."); 
		 
		  staleElement = false;


	  } catch(StaleElementReferenceException e){

	    staleElement = true;

	  }
	  }
	}
		 
		 
		 soft.assertAll();
		 
	
	}
	
	@AfterClass
	public void terminate() {
		driver.close();
	}
	
	

	
	
	
	
	
	public String setCouponCode() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddHHmm");  
		   LocalDateTime now = LocalDateTime.now();  
		   return "test"+dtf.format(now); 
	}
	
	
	public void setStartDate(WebDriver driver) {
		c = new Coupon(driver);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
		Date today = Calendar.getInstance().getTime();
		String date = dateFormat.format(today);
		String splitter[] = date.split("-");
		String year = splitter[0];
		String month = splitter[1];
		int number = Integer.parseInt(month);
		String newMonth = String.valueOf(number-1);
		String day = splitter[2];
		c.getStartDateField().click();			
		c.getCalendarYear().sendKeys(Keys.chord(Keys.COMMAND, "a"), year);
		Select CalendarMonthDropdown = new Select(c.getCalendarMonthHeader());
		CalendarMonthDropdown.selectByValue(newMonth);;
		for(int d=0; d<c.getCalendarDate().size(); d++) {
			if(!c.getCalendarDate().get(d).getAttribute("class").contains("disabled")) {
				c.getCalendarDate().get(d).click();
				break;
			}
		}
		
	}
	
	public void setEndDate(WebDriver driver) {
		c = new Coupon(driver);
		Date dt = new Date();  
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(dt); 
		calendar.add(Calendar.DATE, 10); 
		dt = calendar.getTime();  
		String tommorowsDate = new SimpleDateFormat("yyyy-M-d").format(dt);
		String splitter1[] = tommorowsDate.split("-");
		String year1 = splitter1[0];
		String month1 = splitter1[1];
		String day1 = splitter1[2];
		
		c.getEndDateField().click();			
		c.getCalendarYear().sendKeys(Keys.chord(Keys.COMMAND, "a"), year1);
		for(int d=0; d<c.getCalendarDate().size(); d++) {
			if(!c.getCalendarDate().get(d).getAttribute("class").contains("disabled")) {
			if(c.getCalendarDate().get(d).getText().equalsIgnoreCase(day1)) {
				c.getCalendarDate().get(d).click();
				break;
			}
		}
		}
	}
	
	public void SuccessfulSingleCouponRequestBank(WebDriver driver) throws InterruptedException {
		//Single Coupon request using bank transfer
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
				Select couponDropDown = new Select(c.getCategoryRequestCoupon());
				couponDropDown.selectByVisibleText("Single");
				ct = new CouponTest();
				c.getCouponCodeField().sendKeys(Keys.chord(Keys.COMMAND, "a"), ct.setCouponCode());
				ct.setStartDate(driver);
				ct.setEndDate(driver);
				Select discountDropDown = new Select(c.getDiscountTypeField());
				discountDropDown.selectByValue("percent");
				c.getDiscountValueField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "100");
				c.getAgentNameField().sendKeys("jerry");
				Thread.sleep(3000);
				for(WebElement agentList : c.getAgentFieldDropdown()) {
					if(agentList.getText().contains("batocox")) {
						agentList.click();
						break;
					}
				}
			c.getQuotationButton().click();
			c.getPopUpRequestButton().click();
			c.getPaymentMode().stream().filter(v->v.getText().equalsIgnoreCase("bank transfer")).findAny().get().click();
			c.getPopUpRequestButton().click();
	}
	
	public void SuccessfulMultipleCouponRequestBank(WebDriver driver) throws InterruptedException {
		//Single Coupon request using bank transfer
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getCoupon());
				Select couponDropDown = new Select(c.getCategoryRequestCoupon());
				couponDropDown.selectByVisibleText("Multiple");
				c.getQuantityField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "5");
				ct = new CouponTest();
				c.getCouponCodeField().sendKeys(Keys.chord(Keys.COMMAND, "a"), ct.setCouponCode());
				ct.setStartDate(driver);
				ct.setEndDate(driver);
				Select discountDropDown = new Select(c.getDiscountTypeField());
				discountDropDown.selectByValue("percent");
				c.getDiscountValueField().sendKeys(Keys.chord(Keys.COMMAND, "a"), "100");
			c.getQuotationButton().click();
			c.getPopUpRequestButton().click();
			c.getPaymentMode().stream().filter(v->v.getText().equalsIgnoreCase("bank transfer")).findAny().get().click();
			c.getPopUpRequestButton().click();
	}
}
