package com.SchoolOfDragons.LiveGemsPageChecks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import Factory.BrowserFactory;
import Pages.AfterLoggedInPage;
import Pages.CommonHeader;
import Pages.GemsPage;
import Pages.LoginPage;
import Utility.CaptureScreenshot;



public class TestCase10
{
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;		
	String Category;
	static String testCase10Result = "No result";
	
	@BeforeClass
	public void setUp() throws Throwable
	{		
		report=ExtentManager.Instance();
		driver = BrowserFactory.getBrowser("firefox");
	}
	
	@Parameters(value="Category")
	@Test(priority=1)
	public void ValidAuthorisedPlayerLogin(String catg) throws Throwable
	{
		logger = report.startTest("Test Case 10 : Live - Verify Gems page - Third Gems option ("+GemsPage.thirdGemsAmount1+") Verify Select Payment DB and MasterCard Payment form","This test case will verify Third Gems option ("+GemsPage.thirdGemsAmount1+") Select Payment DB and MasterCard Payment form");		
		logger.log(LogStatus.INFO, "Browser is up and running");
		String browserOpenedScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "browserOpenedScreenshot"));
		logger.log(LogStatus.INFO, browserOpenedScreenshot);		
		driver.get("http://www.schoolofdragons.com");
		logger.log(LogStatus.INFO, "Url is Loading");		
		Thread.sleep(5000);		
		CommonHeader header = PageFactory.initElements(driver, CommonHeader.class);		
		String homePageScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "homePageScreenshot"));
		logger.log(LogStatus.INFO, homePageScreenshot);		
		header.clickHeaderLoginLink();
		logger.log(LogStatus.INFO, "Clicked the Login Link on the Homepage header");		
		Thread.sleep(5000);		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);	
		String loginPageScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "loginPageScreenshot"));
		logger.log(LogStatus.INFO, loginPageScreenshot);		
		Thread.sleep(5000);		
		loginPage.userNameType("subbuPlayer");
		logger.log(LogStatus.INFO, "Entered username");
		loginPage.passwordType("123456");
		logger.log(LogStatus.INFO, "Entered password");
		String afterEnteringUsernameAndPassword=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "afterEnteringUsernameAndPassword"));
		logger.log(LogStatus.INFO, afterEnteringUsernameAndPassword);
		loginPage.playNowButtonClick();
		logger.log(LogStatus.INFO, "Clicked on the Play Now button after entering Username and Password");		
		Thread.sleep(5000);		
		AfterLoggedInPage afterLoggedInPage = PageFactory.initElements(driver, AfterLoggedInPage.class);    					
		afterLoggedInPage.currentlyLoggedInText("subbuPlayer").isDisplayed();
		afterLoggedInPage.afterLoggedInSuccessfully();
		logger.log(LogStatus.INFO, "After Logged in Page is verified successfully");
		String afterLoggedinPageScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "afterLoggedinPageScreenshot"));
		logger.log(LogStatus.INFO, afterLoggedinPageScreenshot);		
		Thread.sleep(5000);		
		header.clickMembershipTab();
		logger.log(LogStatus.INFO, "Clicked the Membership tab Link on the Homepage");		
		Thread.sleep(5000);					
		String membershipPageScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "membershipPageScreenshot"));
		logger.log(LogStatus.INFO, membershipPageScreenshot);		
		Thread.sleep(5000);		
		GemsPage gemsPage = PageFactory.initElements(driver, GemsPage.class);
		gemsPage.gemsTabClick();			
		gemsPage.verifyThirdGemsOptionSelectPaymentMethodDBandPaymentFormMasterCard(logger);
		BrowserFactory.closeBrowser();
		logger.log(LogStatus.INFO, "Quitting the Browser Opened");		
	}
	
	
	@AfterMethod
	public void afterTest(ITestResult result) throws Throwable
	{
		if(result.getStatus()==ITestResult.FAILURE)	
		{		
			logger.log(LogStatus.FAIL, "<pre>" + result.getThrowable().getMessage() + "</pre>");
			String failureScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver,result.getName()));			  
			logger.log(LogStatus.FAIL, failureScreenshot);	
			testCase10Result = "Fail";
		}		
		testCase10Result = "Pass";
	}
	
	@AfterClass
	public void tearDown()
	{
		report.endTest(logger);
		report.flush();	
		BrowserFactory.closeBrowser();
		report.close();				
	}
}