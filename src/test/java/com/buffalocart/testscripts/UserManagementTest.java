package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class UserManagementTest extends Base {
    LoginPage login;
    HomePage home;
    SignOutPage sign;
    UserManagementPage userManagement;
    UserPage user;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 9, enabled = true, description = "TC_009_Verify the User management sub tabs")
    public void verifyUserManagementSubTabs() throws IOException {
        extentTest.get().assignCategory("Regression");
        login=new LoginPage(driver);
        userManagement=new UserManagementPage(driver);
        user=new UserPage(driver);
        login.enterUserName(login.get_UserName());
        extentTest.get().log(Status.PASS, "User name is entered");
        login.enterPassword(login.get_Password());
        extentTest.get().log(Status.PASS, "Password is entered");
        home=login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Clicked on login button");
        home.endTour();
        extentTest.get().log(Status.PASS, "Clicked on end tour");
        userManagement.clickOnUserManagementTab();
        List<String> expectedTabs=userManagement.getExpectedUserManagementTabs();
        extentTest.get().log(Status.PASS, "Expected tab list generated");
        List<String> actualTabs= userManagement.getActualUserManagementTabs();
        extentTest.get().log(Status.PASS, "Actual tab list generated");
        SoftAssert soft=new SoftAssert();
        soft.assertEquals(actualTabs,expectedTabs,"Error:user management tabs not founded");
        sign= home.clickOnUserName();
        login= sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Logout from user page and redirected to login page");
        soft.assertAll();
    }
}
