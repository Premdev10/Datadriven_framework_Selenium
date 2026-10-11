package Base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.Utility;

public class Baseclass {
	
	public WebDriver driver;
	public static ExtentReports extentreport;
	public static ExtentTest extenttest;
	Utility ut= new Utility();
	
	@BeforeTest
	public WebDriver initiatedriverinstance(ITestContext context) throws IOException {
		if(ut.readpropertyvalues("browserName").toString().equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			
		}
		else if(ut.readpropertyvalues("browserName").toString().equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		
			
		}
		
		else {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			
		}
		driver.get(ut.readpropertyvalues("applicationUrl").toString());
		driver.manage().window().maximize();
		extenttest=extentreport.createTest(context.getName());
		return driver;
		
	}
	
	@AfterTest
	public void closedriverinstance() {
		driver.quit();
		
	}
	
	@BeforeSuite
	public void extentinitialize() {
		ExtentSparkReporter spark1 = new ExtentSparkReporter("C:\\Eclipseworkspace\\Datadriven_framework\\target\\Reports\\repots.html");
		extentreport=new ExtentReports();
		extentreport.attachReporter(spark1);
		
	}
	
	@AfterSuite
	public void flush_report() {
		extentreport.flush();
	}
	
	@AfterMethod
	public void checkstatus(Method m,ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE) {
			String path=addscreenshot(result.getMethod().getMethodName());
			extenttest.addScreenCaptureFromPath(path);
			extenttest.fail(result.getThrowable());
			
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			String path=addscreenshot(result.getMethod().getMethodName());
			//extenttest.log(Status.PASS, (Markup) extenttest.addScreenCaptureFromPath(path));
			extenttest.pass(m.getName()+" "+"is passed");
			extenttest.addScreenCaptureFromPath(path);
			
			
		}
		
	}
	
	public String addscreenshot(String filename) throws IOException {
		TakesScreenshot takescreenshot1=(TakesScreenshot)driver;
		File srcfile=takescreenshot1.getScreenshotAs(OutputType.FILE);
		File destfile=new File("C:\\Eclipseworkspace\\Datadriven_framework\\test-output\\"+filename+".jpg");
		FileUtils.copyFile(srcfile, destfile);
		return destfile.getAbsolutePath();
	}
	

}
