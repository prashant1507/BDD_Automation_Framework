package org.automation.listeners;

import java.util.Arrays;
import java.util.Base64;
import org.automation.constants.GlobalVars;
import org.automation.elk.ELKUtils;
import org.automation.enums.ConfigMap;
import org.automation.reports.ExtentLogger;
import org.automation.reports.ExtentManager;
import org.automation.reports.ExtentReport;
import org.automation.setpath.ScreenshotPath;
import org.automation.setpath.VideoPath;
import org.automation.testrecorder.TestRecording;
import org.automation.utils.BrowserDetails;
import org.automation.utils.PropertyUtils;
import org.automation.utils.FileSystemHandler;
import org.automation.utils.UserInputCheck;
import org.testng.*;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;

/**
 * To set various methods for handling test cases
 * 
 * <br>
 * Class is final to avoid extend. <br>
 * <br>
 * Apr 8, 2021
 * 
 * @author User1
 * @version 1.0
 *
 */
public class Listener implements ITestListener, ISuiteListener {

	/**
	 * 
	 * Checks if the password for email is in Base64encoded <br>
	 * Apr 8, 2021
	 */

	public void onStart(ISuite suite) {
		XmlSuite a = suite.getXmlSuite();
		int z = a.getThreadCount();
		try {
			if (PropertyUtils.get(ConfigMap.SENDMAILAFTEREXECUTION)
					.equalsIgnoreCase(GlobalVars.getYes())) {
				Base64.getDecoder().decode(PropertyUtils.get(ConfigMap.EMAILPASSWORD));
			}
		} catch (Exception e) {
			UserInputCheck.designerOutputForPasswordError();
			System.exit(1);
		}
		FileSystemHandler.deleteOldReports();
		FileSystemHandler.deleteDir(GlobalVars.getScreenshotDir());
		ExtentReport.initReports();
	}

	public void onFinish(ISuite suite) {
		ExtentReport.flushReport();
		UserInputCheck.sendTestReportOnEmail();
	}

	public void onTestStart(ITestResult result) {
		Object[] paramNames = Reporter.getCurrentTestResult().getParameters();
		String scenarioName = paramNames[0].toString().replaceAll("\"", "");
		String featureName = paramNames[1].toString().replaceAll("Optional|\\[|\\]|\"", "");
		ExtentReport.createTests(scenarioName, featureName);
		ExtentManager.getExtentTest().assignCategory(featureName);
		ExtentManager.getExtentTest().assignCategory(BrowserDetails.browserDeatils());
		ScreenshotPath.setCurrentTestExecutionScreenshotsDir();
		FileSystemHandler.createRequiredDirs();
		VideoPath.setVideoPath();
		ExtentLogger.info(PropertyUtils.get(ConfigMap.URLFORENV));
	}

	public void onTestSuccess(ITestResult result) {
		ELKUtils.sendDetailsToELK(
				result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName(), "Pass");
	}

	public void onTestFailure(ITestResult result) {
		ExtentLogger.fail("<br/>" + result.getThrowable().toString() + "<br/><br/>"
				+ Arrays.toString(result.getThrowable().getStackTrace()));
		ExtentLogger.info("<video width='620' height='340' controls> <source src='" + TestRecording.getRecording()
				+ "' type='video/mp4'> <videos>");
		ELKUtils.sendDetailsToELK(
				result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName(), "Fail");
	}

	public void onTestSkipped(ITestResult result) {
		ExtentLogger.skip("<br/> Status: Skip");
		ExtentLogger.info("<video width='620' height='340' controls> <source src='" + TestRecording.getRecording()
				+ "' type='video/mp4'> <videos>");
		ELKUtils.sendDetailsToELK(
				result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName(), "Skip");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		/*
		 * For future implementation
		 */
	}

	public void onStart(ITestContext context) {
		/*
		 * Invoked after the test class is instantiated and before any configuration
		 * method is called. Currently, there is only one test
		 */
	}

	public void onFinish(ITestContext context) {
		/*
		 * Invoked after all the tests have run and all their Configuration methods have
		 * been called. Currently, there is only one test
		 */
	}

}