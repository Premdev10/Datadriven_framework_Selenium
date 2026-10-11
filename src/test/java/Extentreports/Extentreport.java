package Extentreports;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Extentreport {
	public WebDriver driver;
	
	@Test
	public void createreport() {
		ExtentReports extentreports= new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Eclipseworkspace\\Datadriven_framework\\target\\Reports\\repots.html");
		extentreports.attachReporter(spark);
		String jsondata="{\"menu\": {\r\n"
				+ "  \"id\": \"file\",\r\n"
				+ "  \"value\": \"File\",\r\n"
				+ "  \"popup\": {\r\n"
				+ "    \"menuitem\": [\r\n"
				+ "      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\r\n"
				+ "      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\r\n"
				+ "      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\r\n"
				+ "    ]\r\n"
				+ "  }\r\n"
				+ "}}";
		extentreports.createTest("xml based test")
		.log(Status.INFO,MarkupHelper.createCodeBlock(jsondata, CodeLanguage.JSON));
		extentreports.createTest("highlight log test")
		.info("not highlighted")
		.info(MarkupHelper.createLabel("this is highlighted", ExtentColor.CYAN));
		extentreports.flush();
	}

	@Test
	public void createreporttest() throws IOException {
		ExtentReports extentreports1= new ExtentReports();
		ExtentSparkReporter spark1 = new ExtentSparkReporter("C:\\Eclipseworkspace\\Datadriven_framework\\target\\Reports\\repots.html");
		extentreports1.attachReporter(spark1);
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://www.google.com/");
		String path=takecreenshot();
		extentreports1.createTest("add scgreenshot")
		.info("this is screenshot")
		.addScreenCaptureFromPath(path);
		extentreports1.flush();
		
	}
	
	public String takecreenshot() throws IOException {
		TakesScreenshot takescrshot= (TakesScreenshot)driver;
		File srcfile=takescrshot.getScreenshotAs(OutputType.FILE);
		File destfile=new File("C:\\Eclipseworkspace\\Datadriven_framework\\test-output\\google.jpg");
		FileUtils.copyFile(srcfile, destfile);
		return destfile.getAbsolutePath();
	}
}
