package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SignOutPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class LoginTest extends Base {
    LoginPage login;
    HomePage home;
    SignOutPage sign;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority=1,enabled=true,description ="TC_001_Verify Login page title")
    public void verifyLoginPageTitle() throws IOException {
        extentTest.get().assignCategory("Regression");
        login=new LoginPage(driver);
        String actualTitle= login.get_LoginPageActualTitle();
        extentTest.get().log(Status.PASS, "Actual login page title generated");
        String expectedTitle=login.get_LoginPageExpectedTitle();
        extentTest.get().log(Status.PASS, "Expected login page title generated");
        Assert.assertEquals(actualTitle,expectedTitle,"Error:Invalid Login Page Title Found");
        extentTest.get().log(Status.PASS, "verify title test case passed");

    }
    @Test(priority = 2,enabled = true,description = "TC_002_Verify user login with valid user credentials")
    public void verifyUserLoginWithValidUserCredentials() throws IOException {
        extentTest.get().assignCategory("Regression");
        extentTest.get().assignCategory("Smoke");
        login=new LoginPage(driver);
        home=new HomePage(driver);
        sign=new SignOutPage(driver);
        SoftAssert soft=new SoftAssert();
        login.enterUserName(login.get_UserName());
        extentTest.get().log(Status.PASS, "User Name is entered");
        login.enterPassword(login.get_Password());
        extentTest.get().log(Status.PASS, "Password is entered");
        login.clickOnLoginButton();
        home.endTour();
        String actualUserName= home.verifyUserName();
        extentTest.get().log(Status.PASS, "Actual user name generated");
        String expectedUserName=login.getExpectedUserName();
        extentTest.get().log(Status.PASS, "expected  user name generated");
        soft.assertEquals(actualUserName,expectedUserName,"Error:invalid user credentials");
        extentTest.get().log(Status.PASS, "Verify user login with valid user credentials test passed");
        home.clickOnUserName();
        login=sign.clickOnLogOutButton();
        soft.assertAll();
    }
    @Test(priority = 3,enabled = true,description = "TC_003_Verify the error message displayed for user login with invalid credentials")
    public void verifyTheErrorMessageDisplayedForUserLoginWithInValidUserCredentials() throws IOException {
        extentTest.get().assignCategory("Regression");
        login=new LoginPage(driver);
        login.enterUserName(login.get_UserName());
        extentTest.get().log(Status.PASS, "User Name is entered");
        login.enterPassword(login.getInvalidPWord());
        extentTest.get().log(Status.PASS, "Password is entered");
        home=login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Clicked on Login Button");
        String expectedErrorMessage= login.getInvalidCredentialsExpectedErrorMsg();
        extentTest.get().log(Status.PASS, "expected error message is generated");
        String actualErrorMessage= login.getInvalidCredentialsErrorMessage();
        extentTest.get().log(Status.PASS, "actual error message is generated");
        Assert.assertEquals(actualErrorMessage,expectedErrorMessage,"Error:Test Failed");
        extentTest.get().log(Status.PASS, "Verify user login with in valid user credentials test passed");
    }
    @Test(priority = 4,enabled = true,description = "TC_004_Verify whether the user is able to click on 'Remember me' checkbox")
    public void verifyWhetherTheUserIsAbleToClickOnRememberMeCheckbox() throws IOException {
        extentTest.get().assignCategory("Regression");
        login=new LoginPage(driver);
        boolean checkBox= login.rememberMeCheckBox();
        Assert.assertTrue(checkBox);
        extentTest.get().log(Status.PASS, "user is able to click on remember me check box");
    }

   }
