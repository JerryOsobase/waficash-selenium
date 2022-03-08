package admin;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.admin.Coupon;
import pageObject.admin.SideMenu;
import pageObject.admin.Transactions;
import resources.base;

public class TransactionsTest extends base{

	SideMenu sm;
	SoftAssert soft;
	AdminLoginTest alt;
	Transactions tr;
	Coupon c;
	CouponTest cot;
	
	@BeforeClass
	public void initialize() throws IOException {
		driver= InitializeBrowser();
		driver.get(prop.getProperty("AdminOpUrl"));
		driver.manage().window().maximize();
	}
	
	@Test(priority=1, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void ValidateTransactionsPage(HashMap<String, String> data) {
	//Verify user is taken to the transactions page
		alt = new AdminLoginTest();
		alt.ValidateSuccessfulAdminLogin1(driver, data);
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 tr = new Transactions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getTransactions());
		 soft.assertTrue(tr.getReconcileButton().isDisplayed());
		 List<String> originalPageHeader = tr.getTransactionHeader().stream().map(a->a.getText()).collect(Collectors.toList());
			String[] expectedPageHeader= {"Reconcile Transaction","Transaction History"};
				List<String> expectedLabelArray= Arrays.asList(expectedPageHeader);
				soft.assertEquals(originalPageHeader, expectedLabelArray);
		 soft.assertAll();
		
	}
	
	@Test(priority=2, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void ValidateSingleCouponRequestReconcilationAdmin(HashMap<String, String> data) throws InterruptedException {
		//Verify user can reconcile single coupon transaction from Admin
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		soft = new  SoftAssert();
		 tr = new Transactions(driver);
		 cot = new CouponTest();
		 cot.SuccessfulSingleCouponRequestBank(driver);
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		String[] splitter = c.getBankTransferMessage().getText().split("ID");
		String[] splitter1 =	splitter[1].split("and");
		String[] splitter2 = splitter1[0].split("\\(");
		String[] transactionMessage = splitter2[1].split("\\)");
		String transactionId = transactionMessage[0].trim();
		executor.executeScript("arguments[0].click();", c.getPopUpCloseButton());
		 executor.executeScript("arguments[0].click();", sm.getTransactions());
		 Optional<WebElement> find;
		 do {
			 find = tr.getTransactionId().stream().filter(v->v.getText().contains(transactionId)).findAny();
		 for(int q=0; q<tr.getTransactionId().size(); q++) {
			 if(tr.getTransactionId().get(q).getText().contains(transactionId)) {
				 soft.assertEquals(tr.getStatus().get(q).getText(), "Initiated");
				 break;
			 }
		 }
		 if(!find.isPresent()) {
			 tr.getNextPagination().click();
			 Thread.sleep(3000);
		 }
	 
	 }while(!find.isPresent());
		tr.getTransactionIdField().sendKeys(transactionId);
		tr.getReconcileButton().click();
		soft.assertFalse(tr.getPopUpReconcileButton().isEnabled());
		tr.getTransactionAmountField().sendKeys(data.get("singleCoupon"));
		tr.getPopUpReconcileButton().click();
		Thread.sleep(3000);
		soft.assertEquals(tr.getPromptMessage().getText(), "Success\n"
		 		+ "Transaction reconciliation successful");
		 do {
			 find = tr.getTransactionId().stream().filter(v->v.getText().contains(transactionId)).findAny();
		 for(int q=0; q<tr.getTransactionId().size(); q++) {
			 if(tr.getTransactionId().get(q).getText().contains(transactionId)) {
				 soft.assertEquals(tr.getStatus().get(q).getText(), "Success");
				 break;
			 }
		 }
		 if(!find.isPresent()) {
			 tr.getNextPagination().click();
			 Thread.sleep(3000);
		 }
	 
	 }while(!find.isPresent());	
		 soft.assertAll();
	}
	
	@Test(priority=3, dataProvider="mergedData", dataProviderClass=AdminLoginTest.class)
	public void ValidateMultipleCouponRequestReconcilationAdmin(HashMap<String, String> data) throws InterruptedException {
		//Verify user can reconcile multiple coupon transaction from Admin
		sm = new SideMenu(driver);
		c = new Coupon(driver);
		soft = new  SoftAssert();
		 tr = new Transactions(driver);
		 cot = new CouponTest();
		 cot.SuccessfulMultipleCouponRequestBank(driver);
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		String[] splitter = c.getBankTransferMessage().getText().split("ID");
		String[] splitter1 =	splitter[1].split("and");
		String[] splitter2 = splitter1[0].split("\\(");
		String[] transactionMessage = splitter2[1].split("\\)");
		String transactionId = transactionMessage[0].trim();
		executor.executeScript("arguments[0].click();", c.getPopUpCloseButton());
		 executor.executeScript("arguments[0].click();", sm.getTransactions());
		 Optional<WebElement> find;
		 do {
			 find = tr.getTransactionId().stream().filter(v->v.getText().contains(transactionId)).findAny();
		 for(int q=0; q<tr.getTransactionId().size(); q++) {
			 if(tr.getTransactionId().get(q).getText().contains(transactionId)) {
				 soft.assertEquals(tr.getStatus().get(q).getText(), "Initiated");
				 break;
			 }
		 }
		 if(!find.isPresent()) {
			 tr.getNextPagination().click();
			 Thread.sleep(3000);
		 }
	 
	 }while(!find.isPresent());
		tr.getTransactionIdField().sendKeys(transactionId);
		tr.getReconcileButton().click();
		soft.assertFalse(tr.getPopUpReconcileButton().isEnabled());
		tr.getTransactionAmountField().sendKeys(data.get("multipleCoupon"));
		tr.getPopUpReconcileButton().click();
		Thread.sleep(3000);
		soft.assertEquals(tr.getPromptMessage().getText(), "Success\n"
		 		+ "Transaction reconciliation successful");
		 do {
			 find = tr.getTransactionId().stream().filter(v->v.getText().contains(transactionId)).findAny();
		 for(int q=0; q<tr.getTransactionId().size(); q++) {
			 if(tr.getTransactionId().get(q).getText().contains(transactionId)) {
				 soft.assertEquals(tr.getStatus().get(q).getText(), "Success");
				 break;
			 }
		 }
		 if(!find.isPresent()) {
			 tr.getNextPagination().click();
			 Thread.sleep(3000);
		 }
	 
	 }while(!find.isPresent());	
		 soft.assertAll();
	}
	
	@Test(priority=4)
	public void ValidateTransactionsListHeader() {
		//Verify user can view the list of all transactions with the following headers; Status, Transaction ID, Paid, Payment Type, Type, Reconciled By, Initiated By, Action, Date
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 tr = new Transactions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getTransactions());
		 List<String> originalTransactionListHeader= tr.getTableHeader().stream().map(a->a.getText()).collect(Collectors.toList());
			String[] expectedTransactionListHeader= {"#", "Status", "Transaction ID", "Paid", "Payment Type", "Type", "Reconciled By", "Initiated By", "Action", "Date"};
				List<String> expectedHeaderArray= Arrays.asList(expectedTransactionListHeader);
				Assert.assertEquals(originalTransactionListHeader, expectedHeaderArray);
	}
	
	@Test(priority=5, dataProvider="getData", dataProviderClass=AdminLoginTest.class)
	public void ValidateSearchBar(HashMap<String, String> data) throws InterruptedException {
		//Verify user can filter list by searching for by user
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 tr = new Transactions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getTransactions());
		 tr.getSelectUserField().sendKeys(data.get("agentsearch"));
		 Thread.sleep(3000);
			for(WebElement agentList : tr.getAgentFieldDropdown()) {
				if(agentList.getText().contains("Agent")) {
					agentList.click();
					break;
				}
			}
			Select statusDropdown = new Select(tr.getStatusField());
			statusDropdown.selectByVisibleText("All");
			tr.getFilterButton().click();
			Thread.sleep(3000);
				if(tr.getStatus().size()>=1) {
					for(int q=0; q<tr.getStatus().size(); q++) {
						soft.assertTrue(tr.getInitiatedBy().get(q).getText().contains(data.get("agentsearch")));
				}
				}
				else {
					soft.assertEquals(tr.getEmptyTransactionText().getText(), "There are no transactions to display");
				}
			
			soft.assertAll();
			tr.getClearFilterButton().click();
			Thread.sleep(3000);
	}
	
	@Test(priority=6)
	public void ValidateStatusDropdownList() {
		//Verify user can view the following dropdown list when user click on the staus field; All, Initiated, Abandoned, Success, Failed, Reversed, Declined, Fraud
				sm = new SideMenu(driver);
				soft = new  SoftAssert();
				 tr = new Transactions(driver);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				 executor.executeScript("arguments[0].click();", sm.getTransactions());
				 Select statusTypeDropdown = new Select(tr.getStatusField());
					List<WebElement> allOptions= statusTypeDropdown.getOptions();
					List<String> statusOptions= allOptions.stream().map(a->a.getText()).collect(Collectors.toList());
					String[] statusTypeList= {"-- select status --", "All", "Initiated", "Abandoned", "Success", "Failed", "Reversed", "Declined", "Fraud"};
					List<String> statusTypeListArray= Arrays.asList(statusTypeList);
					Assert.assertEquals(statusOptions, statusTypeListArray);
	}
	
	@Test(priority=7)
	public void ValidateAllStatusField() throws InterruptedException {
		 //Verify user can filter by All status
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 tr = new Transactions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getTransactions());
		 Select statusDropdown = new Select(tr.getStatusField());
		 statusDropdown.selectByVisibleText("All");
		 tr.getFilterButton().click();
		 Thread.sleep(5000);
		 String splitter[]= tr.getTableFooterText().getText().split("of");
			long count =  tr.getStatus().stream().count();
			do {
			  tr.getNextPagination().click();
			  Thread.sleep(3000);
			  count +=  tr.getStatus().stream().count();
			}while(!tr.getNextPagination().getText().contains("Next"));
			Assert.assertEquals(splitter[1].trim(), count+ " entries");		 
	}
	
	@Test(priority=8)
	public void ValidateInitiatedStatusField() throws InterruptedException {
		// //Verify user can filter by Initiated status
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 tr = new Transactions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getTransactions());
		 Select statusDropdown = new Select(tr.getStatusField());
		 statusDropdown.selectByVisibleText("Initiated");
		 executor.executeScript("arguments[0].click();", tr.getFilterButton());
			Thread.sleep(3000);
			if(tr.getStatus().size()>=1) {
			String splitter[]= tr.getTableFooterText().getText().split("of");
			long count = tr.getStatus().stream().filter(v->v.getText().equalsIgnoreCase("Initiated")).count();
			do {
			  tr.getNextPagination().click();
			  Thread.sleep(3000);
			  count +=  tr.getStatus().stream().filter(v->v.getText().equalsIgnoreCase("Initiated")).count();
			}while(!tr.getNextPagination().getText().contains("Next"));
			soft.assertEquals(splitter[1].trim(), count+ " entries");	
			}
			else {
				soft.assertEquals(tr.getEmptyTransactionText(), "There are no transactions to display");
			}
	}
	
	@Test(priority=9)
	public void ValidateAbandonedStatusField() throws InterruptedException {
		// //Verify user can filter by Abandoned status
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 tr = new Transactions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getTransactions());
		 Select statusDropdown = new Select(tr.getStatusField());
		 statusDropdown.selectByVisibleText("Abandoned");
		 executor.executeScript("arguments[0].click();", tr.getFilterButton());
			Thread.sleep(3000);
			if(tr.getStatus().size()>=1) {
			String splitter[]= tr.getTableFooterText().getText().split("of");
			long count =  tr.getStatus().stream().filter(v->v.getText().equalsIgnoreCase("Abandoned")).count();
			do {
			  tr.getNextPagination().click();
			  Thread.sleep(3000);
			  count +=  tr.getStatus().stream().filter(v->v.getText().equalsIgnoreCase("Abandoned")).count();
			}while(!tr.getNextPagination().getText().contains("Next"));
			soft.assertEquals(splitter[1].trim(), count+ " entries");	
			}
			else {
				soft.assertEquals(tr.getEmptyTransactionText(), "There are no transactions to display");
			}
	}
	
	@Test(priority=10)
	public void ValidateSuccessStatusField() throws InterruptedException {
		// //Verify user can filter by Success status
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 tr = new Transactions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getTransactions());
		 Select statusDropdown = new Select(tr.getStatusField());
		 statusDropdown.selectByVisibleText("Success");
		 executor.executeScript("arguments[0].click();", tr.getFilterButton());
			Thread.sleep(3000);
			if(tr.getStatus().size()>=1) {
			String splitter[]= tr.getTableFooterText().getText().split("of");
			long count =  tr.getStatus().stream().filter(v->v.getText().equalsIgnoreCase("Success")).count();
			do {
			  tr.getNextPagination().click();
			  Thread.sleep(3000);
			  count +=  tr.getStatus().stream().filter(v->v.getText().equalsIgnoreCase("Success")).count();
			}while(!tr.getNextPagination().getText().contains("Next"));
			soft.assertEquals(splitter[1].trim(), count+ " entries");	
			}
			else {
				soft.assertEquals(tr.getEmptyTransactionText(), "There are no transactions to display");
			}
	}
	
	@Test(priority=11)
	public void ValidateFailedStatusField() throws InterruptedException {
		// //Verify user can filter by Failed status
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 tr = new Transactions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getTransactions());
		 Select statusDropdown = new Select(tr.getStatusField());
		 statusDropdown.selectByVisibleText("Failed");
		 executor.executeScript("arguments[0].click();", tr.getFilterButton());
			Thread.sleep(3000);
			if(tr.getStatus().size()>=1) {
			String splitter[]= tr.getTableFooterText().getText().split("of");
			long count =  tr.getStatus().stream().filter(v->v.getText().equalsIgnoreCase("Failed")).count();
			try {
			do {
				
				if(tr.getNextPagination().isDisplayed()) {
			  tr.getNextPagination().click();
			  Thread.sleep(3000);
			  count +=  tr.getStatus().stream().filter(v->v.getText().equalsIgnoreCase("Failed")).count();
				}
				
				}while(!tr.getNextPagination().getText().contains("Next"));	  
			} catch (Exception e) {
				  //log exception
			}

			soft.assertEquals(splitter[1].trim(), count+ " entries");	
			}
			else {
				soft.assertEquals(tr.getEmptyTransactionText(), "There are no transactions to display");
			}
	}
	
	@Test(priority=12)
	public void ValidateReversedStatusField() throws InterruptedException {
		// //Verify user can filter by Reversed status
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 tr = new Transactions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getTransactions());
		 Select statusDropdown = new Select(tr.getStatusField());
		 statusDropdown.selectByVisibleText("Reversed");
		 executor.executeScript("arguments[0].click();", tr.getFilterButton());
			Thread.sleep(3000);
			if(tr.getStatus().size()>=1) {
			String splitter[]= tr.getTableFooterText().getText().split("of");
			long count =  tr.getStatus().stream().filter(v->v.getText().equalsIgnoreCase("Reversed")).count();
			try {
			do {
				
				if(tr.getNextPagination().isDisplayed()) {
			  tr.getNextPagination().click();
			  Thread.sleep(3000);
			  count +=  tr.getStatus().stream().filter(v->v.getText().equalsIgnoreCase("Reversed")).count();
				}
				
				}while(!tr.getNextPagination().getText().contains("Next"));	  
			} catch (Exception e) {
				  //log exception
			}

			soft.assertEquals(splitter[1].trim(), count+ " entries");	
			}
			else {
				soft.assertEquals(tr.getEmptyTransactionText(), "There are no transactions to display");
			}
	}
	
	@Test(priority=13)
	public void ValidateDeclinedStatusField() throws InterruptedException {
		// //Verify user can filter by Declined status
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 tr = new Transactions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getTransactions());
		 Select statusDropdown = new Select(tr.getStatusField());
		 statusDropdown.selectByVisibleText("Declined");
		 executor.executeScript("arguments[0].click();", tr.getFilterButton());
			Thread.sleep(3000);
			if(tr.getStatus().size()>=1) {
			String splitter[]= tr.getTableFooterText().getText().split("of");
			long count =  tr.getStatus().stream().filter(v->v.getText().equalsIgnoreCase("Declined")).count();
			try {
			do {
				
				if(tr.getNextPagination().isDisplayed()) {
			  tr.getNextPagination().click();
			  Thread.sleep(3000);
			  count +=  tr.getStatus().stream().filter(v->v.getText().equalsIgnoreCase("Declined")).count();
				}
				
				}while(!tr.getNextPagination().getText().contains("Next"));	  
			} catch (Exception e) {
				  //log exception
			}

			soft.assertEquals(splitter[1].trim(), count+ " entries");	
			}
			else {
				soft.assertEquals(tr.getEmptyTransactionText(), "There are no transactions to display");
			}
	}
	
	@Test(priority=14)
	public void ValidateFraudStatusField() throws InterruptedException {
		// //Verify user can filter by Fraud status
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 tr = new Transactions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getTransactions());
		 Select statusDropdown = new Select(tr.getStatusField());
		 statusDropdown.selectByVisibleText("Fraud");
		 executor.executeScript("arguments[0].click();", tr.getFilterButton());
			Thread.sleep(3000);
			if(tr.getStatus().size()>=1) {
			String splitter[]= tr.getTableFooterText().getText().split("of");
			long count =  tr.getStatus().stream().filter(v->v.getText().equalsIgnoreCase("Fraud")).count();
			try {
			do {
				
				if(tr.getNextPagination().isDisplayed()) {
			  tr.getNextPagination().click();
			  Thread.sleep(3000);
			  count +=  tr.getStatus().stream().filter(v->v.getText().equalsIgnoreCase("Fraud")).count();
				}
				
				}while(!tr.getNextPagination().getText().contains("Next"));	  
			} catch (Exception e) {
				  //log exception
			}

			soft.assertEquals(splitter[1].trim(), count+ " entries");	
			}
			else {
				soft.assertEquals(tr.getEmptyTransactionText(), "There are no transactions to display");
			}
	}
	
	@Test(priority=15)
	public void ValidateInitiatedStatusTransactions() throws InterruptedException {
		// //Verify user can view the "Re-query" button beside each transaction card with status "Initiated" and it was an online payment
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 tr = new Transactions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getTransactions());
		 Select statusDropdown = new Select(tr.getStatusField());
		 statusDropdown.selectByVisibleText("Initiated");
		 executor.executeScript("arguments[0].click();", tr.getFilterButton());
			Thread.sleep(3000);
			for(int q=0; q<tr.getStatus().size(); q++) {
				if(tr.getStatus().get(q).getText().equalsIgnoreCase("Initiated")) {
					if(tr.getPaymentType().get(q).getText().equalsIgnoreCase("Online")) {
						tr.getPaymentType().get(q).findElement(By.xpath("following-sibling::td[4]/button")).click();
						Thread.sleep(3000);
						if(tr.getPromptMessage().getText().contains("Success")) {
						soft.assertEquals(tr.getPromptMessage().getText(), "Success\n"
		 		+ "Transaction requery successful");
						soft.assertEquals(tr.getStatus().get(q).getText(), "Abandoned");
						}
						else if (tr.getPromptMessage().getText().contains("Error")) {
							soft.assertEquals(tr.getPromptMessage().getText(), "Error\n"
							 		+ "Coupon has expired");
											soft.assertEquals(tr.getStatus().get(q).getText(), "Initiated");
											}
						}
					}	
				}
			soft.assertAll();
	}
	
	@Test(priority=16)
	public void ValidateSuccessTransactionPaymentStatus() throws InterruptedException {
		//Verify user can view transactions with "Success" status as Paid
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 tr = new Transactions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getTransactions());
		 Select statusDropdown = new Select(tr.getStatusField());
		 statusDropdown.selectByVisibleText("Success");
		 executor.executeScript("arguments[0].click();", tr.getFilterButton());
			Thread.sleep(3000);
			int paid = 0;
			long count = tr.getStatus().stream().count();
			 for(int q=0; q<tr.getStatus().size(); q++) {
				 if(tr.getPaymentStatus().get(q).getText().equalsIgnoreCase("Paid")) {
					 paid++;
				 }
			 }
			 Assert.assertEquals(count, paid);
	}
	
	@Test(priority=17)
	public void ValidateInitiatedTransactionPaymentStatus() throws InterruptedException {
		//Verify user can view transactions with "Initiated" status as Paid
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 tr = new Transactions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getTransactions());
		 Select statusDropdown = new Select(tr.getStatusField());
		 statusDropdown.selectByVisibleText("Initiated");
		 executor.executeScript("arguments[0].click();", tr.getFilterButton());
			Thread.sleep(3000);
			int unpaid = 0;
			long count = tr.getStatus().stream().count();
			 for(int q=0; q<tr.getStatus().size(); q++) {
				 if(tr.getPaymentStatus().get(q).getText().equalsIgnoreCase("Unpaid")) {
					 unpaid++;
				 }
			 }
			 Assert.assertEquals(count, unpaid);
	}
	
	@Test(priority=18)
	public void ValidateAbandonedTransactionPaymentStatus() throws InterruptedException {
		//Verify user can view transactions with "Abandoned" status as Paid
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 tr = new Transactions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getTransactions());
		 Select statusDropdown = new Select(tr.getStatusField());
		 statusDropdown.selectByVisibleText("Abandoned");
		 executor.executeScript("arguments[0].click();", tr.getFilterButton());
			Thread.sleep(3000);
			int unpaid = 0;
			long count = tr.getStatus().stream().count();
			 for(int q=0; q<tr.getStatus().size(); q++) {
				 if(tr.getPaymentStatus().get(q).getText().equalsIgnoreCase("Unpaid")) {
					 unpaid++;
				 }
			 }
			 Assert.assertEquals(count, unpaid);
	}
	
	@Test(priority=19, dataProvider="getData")
	public void ValidateCsvExport(String status) throws InterruptedException {
		//Verify user can export csv
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 tr = new Transactions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getTransactions());
		 Select statusDropdown = new Select(tr.getStatusField());
		 statusDropdown.selectByVisibleText(status);
		 executor.executeScript("arguments[0].click();", tr.getFilterButton());
			Thread.sleep(3000);
			executor.executeScript("arguments[0].click();", tr.getExportCsvButton());
			Thread.sleep(3000);
			soft.assertEquals(tr.getPromptMessage().getText(), "Success\n"
					+ "Transactions export request successfully!");
			soft.assertAll();
	}
	
	@Test(priority=20)
	public void ValidateTransactionCardResponse() {
		//Verify on the transaction full details page, user can view the User details and Transactions details
		sm = new SideMenu(driver);
		soft = new  SoftAssert();
		 tr = new Transactions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		 executor.executeScript("arguments[0].click();", sm.getTransactions());
		 for(int q=0; q<tr.getStatus().size(); q++) {
			 if(tr.getStatus().get(q).getText().equalsIgnoreCase("Success")) {
				 String transactionId= tr.getTransactionId().get(q).getText();
				 String status = tr.getStatus().get(q).getText();
				 executor.executeScript("arguments[0].click();", tr.getStatus().get(q));
				 List<String> originalTransactiondetails = tr.getTransactionDetailsHeader().stream().map(a->a.getText()).collect(Collectors.toList());
				 String[] expectedTransactiondetails= {"Status", "Amount", "Transaction ID", "Paid At", "Item Type", "Item ID",
								"Payment Mode", "Transaction Reference"};
							List<String> expectedTransactionArray= Arrays.asList(expectedTransactiondetails);
							soft.assertEquals(originalTransactiondetails, expectedTransactionArray);
							soft.assertEquals(tr.getTransactionDetails().get(0).getText(), status);
							soft.assertEquals(tr.getTransactionDetails().get(2).getText(), transactionId);
							driver.navigate().back();
			 }
			 else {
				 String transactionId= tr.getTransactionId().get(q).getText();
				 String status = tr.getStatus().get(q).getText();
				 executor.executeScript("arguments[0].click();", tr.getStatus().get(q));
						List<String> originalTransactiondetails = tr.getTransactionDetailsHeader().stream().map(a->a.getText()).collect(Collectors.toList());
						String[] expectedTransactiondetails= {"Status", "Amount", "Transaction ID", "Transaction Date", "Item Type", "Item ID",
								"Payment Mode", "Transaction Reference"};
							List<String> expectedTransactionArray= Arrays.asList(expectedTransactiondetails);
							soft.assertEquals(originalTransactiondetails, expectedTransactionArray);
							soft.assertEquals(tr.getTransactionDetails().get(0).getText(), status);
							soft.assertEquals(tr.getTransactionDetails().get(2).getText(), transactionId);
							driver.navigate().back();
			 }
		 }
		 soft.assertAll();
	}
	
	@AfterClass
	public void terminate() {
		driver.close();
	}
	
	
	@DataProvider
	public Object[][] getData() {
		Object[][] data=new Object[8][1];
		data[0][0]="Declined";
		data[1][0]="Initiated";
		data[2][0]="Success";
		data[3][0]="Abandoned";
		data[4][0]="Reversed";
		data[5][0]="Failed";
		data[6][0]="Fraud";
		data[7][0]="All";

		return data;
	}
}
