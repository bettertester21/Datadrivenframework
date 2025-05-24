package com.bit.utilities;

import com.bit.base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.Platform;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtil extends BaseTest {

    public static String screenshotPath, screenshotName;
    public static String userDir = System.getProperty("user.dir");

    public static void captureScreenshot()
    {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        screenshotName = "Img_"+new SimpleDateFormat("yyyyMMddHHmm'.jpg'").format(new Date());
        System.out.println("screenshotName: "+screenshotName);  //For debugging
        try {
            FileUtils.copyFile(scrFile, new File( userDir + "/target/screenshots/"+screenshotName ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void captureScreenshot(String path)
    {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        screenshotName = "Img_"+new SimpleDateFormat("yyyyMMddHHmm'.jpg'").format(new Date());
        System.out.println("screenshotName: "+screenshotName);  //For debugging
        try {
            FileUtils.copyFile(scrFile, new File( path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//
//    public static Platform getPlatformDetails()
//    {
//        Platform platform = null;
//
//        if (platform == null) {
//            String operSys = System.getProperty("os.name").toLowerCase();
//            if (operSys.contains("win")) {
//                platform = Platform.WINDOWS;
//            } else if (operSys.contains("nix") || operSys.contains("nux")
//                    || operSys.contains("aix")) {
//                platform = Platform.LINUX;
//            } else if (operSys.contains("mac")) {
//                platform = Platform.MAC;
//            }
//        }
//        return platform;
//    }



}
