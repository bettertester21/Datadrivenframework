package com.bit.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.bit.utilities.ExcelReader;
import com.bit.utilities.ExtentTestManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.bit.utilities.TestConfiguration.getCurrentPlatform;

public class BaseTest {

    protected static WebDriver driver;
    protected Properties config = new Properties();
    Properties OR = new Properties();
    FileInputStream fis = null;

    protected Logger log;

    protected ExcelReader excel;

    //public ExtentReports rep = ExtentManager.createExtentReports();
    public ExtentReports extent = null;

    public ExtentTest test = null;

    protected String userDir = System.getProperty("user.dir");
    //String browser,url;

    @BeforeSuite
    public void setUp()
    {
        String userDir,browser,url;
        Platform platform = getCurrentPlatform();
        System.out.println("Platform is: "+platform.toString());
        //extent = ExtentManager.createExtentReports();
        userDir = System.getProperty("user.dir");
        String path = "src/test/resources/excel/Testdata.xlsx";
        log = LogManager.getLogger(BaseTest.class);
        excel = new ExcelReader(path);
//        test = ExtentTestManager.getTest();
        if(driver == null)
        {
            try {
                fis = new FileInputStream(userDir + "/src/test/resources/properties/Config.properties");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                config.load(fis);
                log.info("Config file is loaded!");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            browser = config.getProperty("browser");
            if(browser.equals("chrome"))
            {
                switch (platform) {
                    case MAC:
                        System.setProperty("webdriver.chrome.driver", userDir + "/src/test/resources/executables/chromedriver.exe");
                        break;
                    case WINDOWS:
                        System.setProperty("webdriver.chrome.driver", userDir + "/src/test/resources/executables/chromedriver.exe");
                        break;
                    case LINUX:
                        System.setProperty("webdriver.chrome.driver", userDir + "/src/test/resources/executables/chromedriver");
                        break;
                    default:
                        System.out.println("Please select valid browser!\n");
                        break;
                }
                driver = new ChromeDriver();
                log.info("Chrome browser is loaded!");
            }
            driver.manage().window().maximize();
        }
    }

    @AfterSuite
    public void tearDown()
    {
        if(driver !=null)
        {
            driver.quit();
            log.info("Browser is closed!");

        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
