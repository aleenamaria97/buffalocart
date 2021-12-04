package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
    @Test(priority = 24, enabled = true, description = "TC_024_Verify Edit Role page title")
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
    @Test(priority = 25, enabled = true, description = "TC_025_Verify user can update  a role ")
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
        WebElement selselectAllect = driver.findElement(By.xpath("//form[@id='role_form']/div[3]/div[2]/div/label/div"));
        String actualValue = selselectAllect.getAttribute("aria-checked");
        soft.assertEquals(actualValue,"true");
        role=updateRoles.clickOnUpdateButton();
        Thread.sleep(6000);
        sign = home.clickOnUserName();
        login = sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Logout from user page and redirected to login page");
        soft.assertAll();

    }

}
