package com.bit.testcases;

import com.aventstack.extentreports.Status;
import com.bit.base.BaseTest;
import com.bit.pageobject.swaglabs.LoginPage;
import com.bit.utilities.DataProviders;
import com.bit.utilities.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.bit.utilities.ExtentTestManager.startTest;

public class LoginTest extends BaseTest
{
	@Test(dataProviderClass = DataProviders.class, dataProvider = "dp")
	public void loginTest(String username,String password)
	{
		String screenshotName = "Img_"+new SimpleDateFormat("yyyyMMddHHmm'.jpg'").format(new Date());
		String url = config.getProperty("url");
		startTest("LoginTest","");
		driver.get(url);
		log.info("Test application URL is loaded!");
		log.info("Page title is: "+driver.getTitle());
		System.out.println("Username: "+username+" and password is: "+password);  //For debugging
		String expPageTitle = "Swag Labs",actPageTitle;
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username,password);
		TestUtil.captureScreenshot(userDir + "/target/"+screenshotName);
		actPageTitle = driver.getTitle();
		//System.out.println("Page title is: "+actPageTitle);    //For debugging
		Assert.assertEquals(actPageTitle,expPageTitle,"Login Test is not executed successfully!");
//		test.log(Status.PASS, "LoginTest is executed successfully!");
	}
}
