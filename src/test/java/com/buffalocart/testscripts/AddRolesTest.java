package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import com.buffalocart.utilities.WaitUtility;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class AddRolesTest extends Base {
    LoginPage login;
    HomePage home;
    SignOutPage sign;
    UserManagementPage userManagement;
    SoftAssert soft;
    RolesPage role;
    AddRolesPage addRoles;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 22, enabled = true, description = "TC_022_Verify  Add Roles page title")
    public void verifyAddRolesPageTitle() throws IOException {
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
        addRoles = role.clickAddRolesTab();
        String actualTitle = addRoles.getUserActualPageTitle();
        String expectedTitle = addRoles.getUserPageExpectedTitle();
        soft.assertEquals(actualTitle, expectedTitle, "Error:Add user page title not founded");
        sign = home.clickOnUserName();
        login = sign.clickOnLogOutButton();
        soft.assertAll();

    }
    @Test(priority = 23, enabled = true, description = "TC_023_Verify  user can add roles ")
    public void verifyUserCanAddRoles() throws IOException, InterruptedException {
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
        addRoles = role.clickAddRolesTab();
        addRoles.enterRoles(addRoles.getUserRolesToEnter());
        role=addRoles.clickOnSaveButton();
        role.searchRoles(role.getRolesToSearch());
        String actualRole = role.getActualRoleAfterSearch();
        String expectedRole = role.getRolesToSearch();
        soft.assertEquals(actualRole,expectedRole, "Error:No such roles founded");
        Thread.sleep(6000);
        sign = home.clickOnUserName();
        login = sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Logout from user page and redirected to login page");
        soft.assertAll();
    }

}