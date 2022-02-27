package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReporter {
	static ExtentReports ext;
	public static ExtentReports getExtentReport() {
		String path =System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter spark=new ExtentSparkReporter(path);
		spark.config().setDocumentTitle("Document title");
		spark.config().setReportName("Report name");
		
		ext=new ExtentReports();
		ext.attachReporter(spark);
		ext.setSystemInfo("Tester", "Jerry osobase");
		return ext;
	}
}
