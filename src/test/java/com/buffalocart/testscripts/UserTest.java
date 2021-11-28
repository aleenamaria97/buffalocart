package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class UserTest extends Base {
    LoginPage login;
    HomePage home;
    UserManagementPage userManagement;
    UserPage user;
    SignOutPage sign;
    SoftAssert soft;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 10, enabled = true, description = "TC_010_Verify Users page title")
    public void verifyUserPageTitle() throws IOException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        soft = new SoftAssert();
        userManagement = new UserManagementPage(driver);
        login.enterUserName(login.get_UserName());
        extentTest.get().log(Status.PASS, "User name is entered");
        login.enterPassword(login.get_Password());
        extentTest.get().log(Status.PASS, "Password is entered");
        home = login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Clicked on login button");
        home.endTour();
        extentTest.get().log(Status.PASS, "Clicked on end tour");
        userManagement.clickOnUserManagementTab();
        user = userManagement.clickOnUserTabs();
        extentTest.get().log(Status.PASS, "clicked on user tab and redirected to user page");
        String actualTitle = user.getUserActualPageTitle();
        extentTest.get().log(Status.PASS, "Actual user page title generated");
        String expectedTitle = user.getUserPageExpectedTitle();
        extentTest.get().log(Status.PASS, "Expected user page title generated");
        soft.assertEquals(actualTitle, expectedTitle, "Error:invalidUserPageTitle");
        soft.assertAll();
        sign = home.clickOnUserName();
        login = sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Logout from user page and redirected to login page");
    }

    @Test(priority = 11, enabled = true, description = "TC_011_Verify user search with valid data")
    public void verifyUserSearchWithValidData() throws IOException {
        extentTest.get().assignCategory("Smoke");
        extentTest.get().assignCategory("Sanity");
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        soft = new SoftAssert();
        userManagement = new UserManagementPage(driver);
        login.enterUserName(login.get_UserName());
        extentTest.get().log(Status.PASS, "User name is entered");
        login.enterPassword(login.get_Password());
        extentTest.get().log(Status.PASS, "Password is entered");
        home = login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Clicked on login button");
        home.endTour();
        extentTest.get().log(Status.PASS, "Clicked on end tour");
        userManagement.clickOnUserManagementTab();
        user = userManagement.clickOnUserTabs();
        extentTest.get().log(Status.PASS, "clicked on user tab and redirected to user page");
        user.searchUser(user.getUserNameToSearch());
        String actualUserData = user.getActualUserIdAfterSearch();
        String expectedUserData = user.getUserNameToSearch();
        soft.assertEquals(actualUserData, expectedUserData, "Error:No user founded");
        soft.assertAll();
        sign = home.clickOnUserName();
        login = sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Logout from user page and redirected to login page");
    }

    @Test(priority = 12, enabled = true, description = "TC_012_Verify message displayed by  user search with invalid data")
    public void verifyMessageDisplayedByUserSearchWithInvalidData() throws IOException {
        extentTest.get().assignCategory("Smoke");
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        soft = new SoftAssert();
        userManagement = new UserManagementPage(driver);
        login.enterUserName(login.get_UserName());
        extentTest.get().log(Status.PASS, "User name is entered");
        login.enterPassword(login.get_Password());
        extentTest.get().log(Status.PASS, "Password is entered");
        home = login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Clicked on login button");
        home.endTour();
        extentTest.get().log(Status.PASS, "Clicked on end tour");
        userManagement.clickOnUserManagementTab();
        user = userManagement.clickOnUserTabs();
        extentTest.get().log(Status.PASS, "clicked on user tab and redirected to user page");
        user.searchUser(user.getInvalidUserName());
        extentTest.get().log(Status.PASS, "Invalid data entered");
        String actualMsg = user.getNoUserFoundActualMsg();
        String expectedMsg = user.getNoUserFoundExpectedMsg();
        soft.assertEquals(actualMsg, expectedMsg, "Error:User with matched records founded");
        soft.assertAll();
        sign = home.clickOnUserName();
        login = sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Logout from user page and redirected to login page");
    }


}
