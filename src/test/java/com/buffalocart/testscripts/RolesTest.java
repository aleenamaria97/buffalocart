package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class RolesTest extends Base {
    LoginPage login;
    HomePage home;
    UserManagementPage userManagement;
    RolesPage role;
    SignOutPage sign;
    SoftAssert soft;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 21, enabled = true, description = "TC_021_Verify Roles page title")
    public void verifyRolesPageTitle() throws IOException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        soft=new SoftAssert();
        login.enterUserName(login.get_UserName());
        extentTest.get().log(Status.PASS, "User name is entered");
        login.enterPassword(login.get_Password());
        extentTest.get().log(Status.PASS, "Password is entered");
        home = login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Clicked on login button");
        home.endTour();
        extentTest.get().log(Status.PASS, "Clicked on end tour");
        userManagement.clickOnUserManagementTab();
        role = userManagement.clickOnRolesTabs();
        extentTest.get().log(Status.PASS, "clicked on user tab and redirected to user page");
        String actualTitle = role.getUserActualPageTitle();
        extentTest.get().log(Status.PASS, "Actual roles page title generated");
        String expectedTitle = role.getUserPageExpectedTitle();
        extentTest.get().log(Status.PASS, "Expected roles page title generated");
        soft.assertEquals(actualTitle, expectedTitle, "Error:invalidUserPageTitle");
        sign = home.clickOnUserName();
        login = sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Logout from user page and redirected to login page");
        soft.assertAll();
    }
}
