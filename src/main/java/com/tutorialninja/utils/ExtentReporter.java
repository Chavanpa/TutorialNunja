package com.tutorialninja.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extentReport;
	public static ExtentTest extentTest; 
	public static ExtentReports generateExtentReport(String TestName) {
		
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\ExtentReports\\Myreports1.html");
		//File extentReportFile = new File(System.getProperty("user.dir")+"\\ExtentReports\\extentReport.html");
		//ExtentSparkReporter sparkReporter = new ExtentSparkReporter(htmlReporter);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setReportName("TutorialsNinja Test Automation Results Report");
		htmlReporter.config().setDocumentTitle("TN Automation Report");
		htmlReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(htmlReporter);
		
		Properties configProp = new Properties();
		File configPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\config\\config.properties");
		
		try {
			FileInputStream fisConfigProp = new FileInputStream(configPropFile);
			configProp.load(fisConfigProp);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application URL",configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser Name",configProp.getProperty("browserName"));
		extentReport.setSystemInfo("Email",configProp.getProperty("validEmail"));
		extentReport.setSystemInfo("Password",configProp.getProperty("validPassword"));
		extentReport.setSystemInfo("Operating System",System.getProperty("os.name"));
		extentReport.setSystemInfo("Username",System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version",System.getProperty("java.version"));
		extentTest = extentReport.createTest(TestName);
		return extentReport;
	}

}
