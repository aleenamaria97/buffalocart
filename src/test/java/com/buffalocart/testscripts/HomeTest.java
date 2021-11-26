package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class HomeTest extends Base {
    LoginPage login;
    HomePage home;
    ExcelUtility excel;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 6, enabled = true, description = "TC_006_Verify home page title")
    public void verifyHomePageTitle() throws IOException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        home=new HomePage(driver);
        excel=new ExcelUtility();
        List<String> readExcelData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_HOME_PAGE);
        login.enterUserName(readExcelData.get(0));
        extentTest.get().log(Status.PASS, "User name is entered");
        login.enterPassword(readExcelData.get(1));
        extentTest.get().log(Status.PASS, "Password is entered");
        home=login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Clicked on login button");
        home.endTour();
        extentTest.get().log(Status.PASS, "Clicked on end tour");
        String actualTitle= home.getHomePageTitle();
        String expectedTitle=readExcelData.get(2);
        String errorMessage=readExcelData.get(3);
        Assert.assertEquals(actualTitle,expectedTitle,errorMessage);
        extentTest.get().log(Status.PASS, "Verify home page title test passed");
    }
    @Test(priority = 7, enabled = true, description = "TC_007_Verify date displayed in home page ")
    public void verifyDateDisplayedInHomePage() throws IOException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        home=new HomePage(driver);
        excel=new ExcelUtility();
        List<String> readExcelData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_HOME_PAGE);
        login.enterUserName(readExcelData.get(0));
        extentTest.get().log(Status.PASS, "User name is entered");
        login.enterPassword(readExcelData.get(1));
        extentTest.get().log(Status.PASS, "Password is entered");
        home=login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Clicked on login button");
        home.endTour();
        extentTest.get().log(Status.PASS, "Clicked on end tour");
        String expectedDate=home.setHomePageDate();
        extentTest.get().log(Status.PASS, "expected date generated");
        String actualDate=home.getHomePageDate();
        String errorMessage=readExcelData.get(4);
        extentTest.get().log(Status.PASS, "actual date generated");
        Assert.assertEquals(actualDate,expectedDate,errorMessage);
        extentTest.get().log(Status.PASS, "verify Date Displayed In HomePage Test Passed");
    }

}