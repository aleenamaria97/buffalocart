package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class SalesCommissionAgentTest extends Base {
    LoginPage login;
    HomePage home;
    SignOutPage sign;
    UserManagementPage userManagement;
    SoftAssert soft;
    SalesCommissionAgentPage sales;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 28, enabled = true, description = "TC_028_Verify  Sales Commission Agents page title",groups = "Regression")
    public void verifySalesCommissionAgentPageTitle() throws IOException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        soft = new SoftAssert();
        login.enterUserName(login.get_UserName());
        extentTest.get().log(Status.PASS, "User name is entered");
        login.enterPassword(login.get_Password());
        extentTest.get().log(Status.PASS, "Password is entered");
        home = login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Clicked on login button");
        home.endTour();
        extentTest.get().log(Status.PASS, "Clicked on end tour");
        userManagement.clickOnUserManagementTab();
        sales= userManagement.clickOnSalesCommissionTab();
        extentTest.get().log(Status.PASS, "clicked on user tab and redirected to Sales commission page");
        String actualTitle = sales.getUserActualPageTitle();
        extentTest.get().log(Status.PASS, "Actual roles page title generated");
        String expectedTitle = sales.getUserPageExpectedTitle();
        extentTest.get().log(Status.PASS, "Expected roles page title generated");
        soft.assertEquals(actualTitle, expectedTitle, "Error:invalidUserPageTitle");
        sign = home.clickOnUserName();
        login = sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Logout from user page and redirected to login page");
        soft.assertAll();

    }
}