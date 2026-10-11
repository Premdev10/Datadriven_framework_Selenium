package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class home {
	
	WebDriver driver;
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(30));
	public home(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@ng-click=\"deposit()\"]")
	public WebElement deposit;
	@FindBy(xpath="//label[text()='Amount to be Deposited :']")
	public WebElement amount_title;
	@FindBy(xpath="//input[@placeholder=\"amount\"]")
	public WebElement amount;
	@FindBy(xpath="//button[text()='Deposit']")
	public WebElement deposit_cash;
	@FindBy(xpath="//span[text()='Deposit Successful']")
	public WebElement success_message;
	
	public void deposit() throws InterruptedException {
		deposit.click();
		wait.until(ExpectedConditions.visibilityOf(amount_title));
		String title=amount_title.getText();
		System.out.println(title);
		amount.sendKeys("100");
		deposit_cash.click();
		wait.until(ExpectedConditions.visibilityOf(success_message));
		System.out.println(success_message);
		Thread.sleep(1000);
		
		
		
	}

}
