package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class UpdateRolesTest extends Base {
    LoginPage login;
    HomePage home;
    UserManagementPage userManagement;
    RolesPage role;
    SignOutPage sign;
    SoftAssert soft;
    UpdateRolesPage updateRoles;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 24, enabled = true, description = "TC_024_Verify Edit Role page title",groups = {"Sanity","Smoke","Regression"})
    public void verifyEditRolesPageTitle() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Sanity");
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
        role = userManagement.clickOnRolesTabs();
        extentTest.get().log(Status.PASS, "clicked on user tab and redirected to roles page");
        updateRoles=role.clickOnEditButton(role.getRolesToEdit());
        extentTest.get().log(Status.PASS, "Clicked on edit button");
        String actualTitle= updateRoles.getUpdateRolesPageActualTitle();
        extentTest.get().log(Status.PASS, "Get actual page title");
        String expectedTitle=updateRoles.getUpdateRolesPageExpectedTitle();
        soft.assertEquals(actualTitle, expectedTitle, "Error:No user founded");
        sign = home.clickOnUserName();
        login = sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Logout from user page and redirected to login page");
        soft.assertAll();
    }
    @Test(priority = 25, enabled = true, description = "TC_025_Verify user can update  a role ",groups = {"Sanity","Smoke","Regression"})
    public void verifyUserCanUpdateRole() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Sanity");
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
        role = userManagement.clickOnRolesTabs();
        extentTest.get().log(Status.PASS, "clicked on user tab and redirected to roles page");
        updateRoles=role.clickOnEditButton(role.getRolesToEdit());
        extentTest.get().log(Status.PASS, "Clicked on edit button");
        updateRoles.selectAllRoles();
        role=updateRoles.clickOnUpdateButton();
        Thread.sleep(8000);
        sign = home.clickOnUserName();
        login = sign.clickOnLogOutButton();
        login.enterUserName(login.get_UserName());
        extentTest.get().log(Status.PASS, "User name is entered");
        login.enterPassword(login.get_Password());
        extentTest.get().log(Status.PASS, "Password is entered");
        home = login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Clicked on login button");
        userManagement.clickOnUserManagementTab();
        role = userManagement.clickOnRolesTabs();
        updateRoles=role.clickOnEditButton(role.getRolesToEdit());
        extentTest.get().log(Status.PASS, "Clicked on edit button");
        soft.assertEquals(updateRoles.getActualStatus(),updateRoles.getExpectedStatus());
        role=updateRoles.clickOnUpdateButton();
        Thread.sleep(6000);
        sign = home.clickOnUserName();
        login = sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Logout from user page and redirected to login page");
        soft.assertAll();

    }

}
