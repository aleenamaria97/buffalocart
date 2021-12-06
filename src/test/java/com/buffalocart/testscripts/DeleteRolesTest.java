package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteRolesTest extends Base {
    LoginPage login;
    HomePage home;
    SignOutPage sign;
    UserManagementPage userManagement;
    SoftAssert soft;
    RolesPage role;
    DeleteRolesPage delete;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 26, enabled = true, description = "TC_026_Verify user can delete a role from the list",groups = {"Smoke"})
    public void verifyUserCanDeleteRole() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        soft = new SoftAssert();
        role=new RolesPage(driver);
        userManagement = new UserManagementPage(driver);
        delete=new DeleteRolesPage(driver);
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
        extentTest.get().log(Status.PASS, "directed to role page");
        delete=role.clickOnDeleteButton(delete.get_RolesToDelete());
        delete.clickOnDeleteOk();
        extentTest.get().log(Status.PASS, "Deleted a role");
        Thread.sleep(6000);
        List<ArrayList<String>> tableData = role.getTableData();
        boolean value =role.getTableDataContains(tableData, delete.get_RolesToDelete());
        soft.assertFalse(value,"ERROR : User Deletion Unsuccessful");
        Thread.sleep(6000);
        sign = home.clickOnUserName();
        sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        soft.assertAll();
    }
}