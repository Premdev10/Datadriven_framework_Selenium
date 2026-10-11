package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Mytestlistner implements ITestListener{
	
	public void onTestStart(ITestContext context) {
		System.out.println("test execution started");
	}
	
	public void onTestSuccess(ITestResult result) {
		System.out.println("test passed");
	}

}
