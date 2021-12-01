package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SignOutPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class HomeTest extends Base {
    LoginPage login;
    HomePage home;
    SignOutPage sign;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 6, enabled = true, description = "TC_006_Verify home page title")
    public void verifyHomePageTitle() throws IOException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        login.enterUserName(login.get_UserName());
        extentTest.get().log(Status.PASS, "User name is entered");
        login.enterPassword(login.get_Password());
        extentTest.get().log(Status.PASS, "Password is entered");
        home=login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Clicked on login button");
        home.endTour();
        extentTest.get().log(Status.PASS, "Clicked on end tour");
        String actualTitle= home.getHomePageActualTitle();
        String expectedTitle= home.getHomePageExpectedTitle();
        SoftAssert soft=new SoftAssert();
        soft.assertEquals(actualTitle,expectedTitle,"Error:Title Miss match");
        sign= home.clickOnUserName();
        login=sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Verify home page title test passed");
        soft.assertAll();
    }
    @Test(priority = 7, enabled = true, description = "TC_007_Verify date displayed in home page ")
    public void verifyDateDisplayedInHomePage() throws IOException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        login.enterUserName(login.get_UserName());
        extentTest.get().log(Status.PASS, "User name is entered");
        login.enterPassword(login.get_Password());
        extentTest.get().log(Status.PASS, "Password is entered");
        home=login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Clicked on login button");
        home.endTour();
        extentTest.get().log(Status.PASS, "Clicked on end tour");
        String expectedDate=home.setHomePageDate();
        extentTest.get().log(Status.PASS, "expected date generated");
        String actualDate=home.getHomePageDate();
        extentTest.get().log(Status.PASS, "actual date generated");
        SoftAssert soft=new SoftAssert();
        soft.assertEquals(actualDate,expectedDate,"Error:Date is mismatched");
        sign= home.clickOnUserName();
        login=sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "verify Date Displayed In HomePage Test Passed");
        soft.assertAll();
    }

}