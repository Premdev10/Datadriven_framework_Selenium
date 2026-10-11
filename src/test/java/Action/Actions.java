package Action;

import java.io.IOException;
import org.testng.annotations.Test;

import Base.Baseclass;
import Pages.Login;
import Pages.Transaction;
import Pages.home;
import utility.Utility;

public class Actions extends Baseclass{
	
	Login lg;
	home hm;
	Transaction tr;
	
Utility ut= new Utility();

	
	@Test
	public void loginpage() throws InterruptedException, IOException {
		lg=new Login(driver);
		lg.loginpage();
		hm=new home(driver);
		hm.deposit();
		tr= new Transaction(driver);
		tr.validate_transaction();
		
	}
	
	@Test
	public void validate_transactionpage() throws InterruptedException, IOException {
		lg=new Login(driver);
		lg.loginpage();
		tr= new Transaction(driver);
		tr.validate_transaction();
	}

}
