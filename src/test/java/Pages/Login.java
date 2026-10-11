package Pages;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;

import Base.Baseclass;
import excelReader.Readexcel;

public class Login extends Baseclass{
	
	WebDriver driver;
	Readexcel rd= new Readexcel();
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(30));
	//Readexcel rx;
	String testcase= "TC1";
	
	public Login(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//button[text()='Customer Login']")
	public WebElement customerlogin;
	
	@FindBy(xpath="//select[@id=\"userSelect\"]")
	public WebElement selectdrop;
	
	@FindBy(xpath="//button[text()='Login']")
	public WebElement login;
	
	@FindBy(xpath="//strong[text()=' Welcome ']//span")
	public WebElement title;
	
	
	
	public void loginpage() throws InterruptedException, IOException {
		//rx= new Readexcel();
		String name= rd.getvalue(testcase);
		System.out.println(name);
		Thread.sleep(3000);
		customerlogin.click();
		Thread.sleep(1000);
		extenttest.info("clicked on customerlogin");
		Select select=new Select(selectdrop);
		select.selectByVisibleText("Harry Potter");
		Thread.sleep(1000);;
//		String path1=addscreenshot("login page");
//		//extenttest.addScreenCaptureFromPath(path1);
//		extenttest.log(Status.PASS, (Markup) extenttest.addScreenCaptureFromPath(path1));
		login.click();
		Thread.sleep(1000);
		extenttest.info("selected with customer name and clicked on login");
		
	}
	public void homepage() {
		wait.until(ExpectedConditions.visibilityOfAllElements(title));
		String title_home=title.getText();
		System.out.println(title_home);
		
		
	}

}
