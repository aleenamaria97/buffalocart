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

public class DeleteUserTest extends Base {
    LoginPage login;
    HomePage home;
    UserPage user;
    AddUserPage addUser;
    UpdateUserPage updateUser;
    UserManagementPage userManagement;
    SoftAssert soft;
    SignOutPage sign;
    DeleteUserPage delete;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 19, enabled = true, description = "TC_019_Verify user can delete a user")
    public void verifyUserCanDeleteAUser() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        soft = new SoftAssert();
        userManagement = new UserManagementPage(driver);
        delete=new DeleteUserPage(driver);
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
        delete=user.clickOnDeleteButton(delete.get_UserNameToDelete());
        delete.clickOnDeleteOk();
        extentTest.get().log(Status.PASS, "Deleted a User");
        Thread.sleep(6000);
        List<ArrayList<String>> tableData = user.getTableData();
        boolean value =user.getTableDataContains(tableData, delete.get_UserNameToDelete());
        soft.assertFalse(value,"ERROR : User Deletion Unsuccessful");
        sign = home.clickOnUserName();
        sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        soft.assertAll();
    }
}