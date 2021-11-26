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
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class LoginTest extends Base {
    LoginPage login;
    HomePage home;
    SignOutPage sign;
    ExcelUtility excel;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority=1,enabled=true,description ="TC_001_Verify Login page title")
    public void verifyLoginPageTitle() throws IOException {
        extentTest.get().assignCategory("Regression");
        login=new LoginPage(driver);
        String actualTitle= login.get_LoginPageActualTitle();
        extentTest.get().log(Status.PASS, "Actual login page title generated");
        String expectedTitle=login.get_LoginPageExpectedTitle();
        extentTest.get().log(Status.PASS, "Expected login page title generated");
        String errorMessage=login.get_ErrorMessage();
        Assert.assertEquals(actualTitle,expectedTitle,errorMessage);
        extentTest.get().log(Status.PASS, "verify title test case passed");

    }
    @Test(priority = 2,enabled = true,description = "TC_002_Verify user login with valid user credentials")
    public void verifyUserLoginWithValidUserCredentials() throws IOException {
        extentTest.get().assignCategory("Regression");
        extentTest.get().assignCategory("Smoke");
        login=new LoginPage(driver);
        home=new HomePage(driver);
        sign=new SignOutPage(driver);
        excel=new ExcelUtility();
        SoftAssert soft=new SoftAssert();
        List<String> readExcelData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_LOGIN_PAGE);
        login.enterUserName(readExcelData.get(2));
        extentTest.get().log(Status.PASS, "User Name is entered");
        login.enterPassword(readExcelData.get(3));
        extentTest.get().log(Status.PASS, "Password is entered");
        login.clickOnLoginButton();
        home.endTour();
        String actualUserName= home.verifyUserName();
        extentTest.get().log(Status.PASS, "Actual user name generated");
        String expectedUserName=readExcelData.get(5);
        extentTest.get().log(Status.PASS, "expected  user name generated");
        String errorMessage=readExcelData.get(4);
        soft.assertEquals(actualUserName,expectedUserName,errorMessage);
        extentTest.get().log(Status.PASS, "Verify user login with valid user credentials test passed");
        home.clickOnUserName();
        login=sign.logOut();
        soft.assertAll();
    }
    @Test(priority = 3,enabled = true,description = "TC_003_Verify the error message displayed for user login with invalid credentials")
    public void verifyTheErrorMessageDisplayedForUserLoginWithInValidUserCredentials() throws IOException {
        extentTest.get().assignCategory("Regression");
        login=new LoginPage(driver);
        excel=new ExcelUtility();
        List<String> readExcelData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_LOGIN_PAGE);
        login.enterUserName(readExcelData.get(2));
        extentTest.get().log(Status.PASS, "User Name is entered");
        login.enterPassword(readExcelData.get(6));
        extentTest.get().log(Status.PASS, "Password is entered");
        home=login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Clicked on Login Button");
        String expectedErrorMessage=readExcelData.get(7);
        extentTest.get().log(Status.PASS, "expected error message is generated");
        String actualErrorMessage= login.get_InvalidCredentialsErrorMessage();
        extentTest.get().log(Status.PASS, "actual error message is generated");
        String errorMessage=readExcelData.get(8);
        Assert.assertEquals(actualErrorMessage,expectedErrorMessage,errorMessage);
        extentTest.get().log(Status.PASS, "Verify user login with in valid user credentials test passed");
    }
    @Test(priority = 4,enabled = true,description = "TC_004_Verify whether the user is able to click on 'Remember me' checkbox")
    public void verifyWhetherTheUserIsAbleToClickOnRememberMeCheckbox() {
        extentTest.get().assignCategory("Regression");
        login=new LoginPage(driver);
        boolean checkBox= login.rememberMeCheckBox();
        Assert.assertTrue(checkBox);
        extentTest.get().log(Status.PASS, "user is able to click on remember me check box");
    }

   }
