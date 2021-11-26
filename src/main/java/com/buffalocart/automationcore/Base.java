package com.buffalocart.automationcore;


import com.aventstack.extentreports.ExtentTest;
import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.EmailUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;
public class Base {
    public WebDriver driver;
    public static Properties prop;
    FileInputStream file;
    EmailUtility email;
    ExtentTest extentTest;

    public Base() {
        prop = new Properties();
        try {
            file = new FileInputStream(System.getProperty("user.dir") + Constants.CONFIG_FILE);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        try {
            prop.load(file);
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public void testInitialize(String browserName)  {

        if (browserName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        } else if (browserName.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }


    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browserName) throws Exception {
        String url = prop.getProperty("url");
        testInitialize(browserName);
        driver.get(url);
    }


    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        takeScreenShot(result);
        driver.close();

    }

    @AfterSuite
    public void sendingEmail() throws IOException, MessagingException {
        List<String> filenames = new ArrayList<String>();

        try (Stream<Path> filePathStream = Files.walk(Paths.get(System.getProperty("user.dir") + "//screenshots//"))) {
            filePathStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    filenames.add(filePath.toString());
                }
            });
        }

        String dateName = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        email = new EmailUtility(System.getProperty("user.dir") + "//TestReport//", "ExtentReport_" + dateName + ".html", prop.getProperty("to_email_id"), filenames, prop);
        email.sendEmail();


    }

    public void takeScreenShot(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {

            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            Date date = new Date();
            String dateFormatted = new SimpleDateFormat("dd-MM-yyyy").format(date);
            String FileName = result.getName() + "_" + dateFormatted;
            FileUtils.copyFile(screenshot, new File("./Screenshots/" + FileName + ".png"));
        }

    }

}