package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Transaction {
	
	WebDriver driver;
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
	
	public Transaction(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//button[@ng-click=\"transactions()\"]")
	public WebElement transaction;
	
	@FindBy(xpath="//table//tbody//tr")
	public List<WebElement> table;
	
	public void validate_transaction() throws InterruptedException {
		transaction.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(table));
		int table_size=table.size();
		for(int i=1;i<=table_size;i++) {
			List<WebElement>column=driver.findElements(By.xpath("//tbody//tr["+i+"]//td"));
			for(int j=1;j<=column.size();j++) {
				String column_value=driver.findElement(By.xpath("//tbody//tr["+i+"]//td["+j+"]")).getText();
				System.out.println("row number:"+i+",column number:"+j+"value is"+column_value);
			}
			
		}
		Thread.sleep(2000);
	}

}
