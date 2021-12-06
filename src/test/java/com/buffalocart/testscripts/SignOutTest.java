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

public class SignOutTest extends Base {
    LoginPage login;
    HomePage home;
    SignOutPage sign;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 8, enabled = true, description = "TC_008_Verify whether user is navigating to login page by clicking on Sign out button",groups = {"Smoke","Regression"})
    public void verifyUserIsNavigatingToLoginPageByClickingOnSignOutButton() throws IOException {
        extentTest.get().assignCategory("Regression");
        extentTest.get().assignCategory("Smoke");
        login = new LoginPage(driver);
        login.enterUserName(login.get_UserName());
        extentTest.get().log(Status.PASS, "User name is entered");
        login.enterPassword(login.get_Password());
        extentTest.get().log(Status.PASS, "Password is entered");
        home=login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Clicked on login button");
        home.endTour();
        extentTest.get().log(Status.PASS, "Clicked on end tour");
        sign=home.clickOnUserName();
        login=sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Clicked on logout button");
        String actualTitle= login.get_LoginPageActualTitle();
        String expectedTitle=login.get_LoginPageExpectedTitle();
        SoftAssert soft=new SoftAssert();
        soft.assertEquals(actualTitle,expectedTitle,"Error:Navigation to login page failed");
        extentTest.get().log(Status.PASS, "User is successfully navigated to LoginPage");
        soft.assertAll();
    }


}
