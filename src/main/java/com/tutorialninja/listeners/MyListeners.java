package com.tutorialninja.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialninja.utils.ExtentReporter;
import com.tutorialninja.utils.Utilities;




public class MyListeners implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentTest;

	
	@Override
	public void onStart(ITestContext context) {
		
		extentReport=ExtentReporter.generateExtentReport(context.getName());
	}
	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO,result.getName()+" - Test Started Executing ");
		
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS,result.getName()+" - Test Successfully Executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
      
		WebDriver driver = null;
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	
		String destinationScreenshotPath = Utilities.captureScreenshot(driver,result.getName());
		
		try {
			extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,result.getName()+" - Test Got failed");
		
	}
	

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+" - Test got skipped");
	}

	
	@Override
	public void onFinish(ITestContext context) {
	extentReport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\ExtentReports\\Myreports1.html";
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
