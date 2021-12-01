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

public class UpdateUserTest extends Base {
    LoginPage login;
    HomePage home;
    UserPage user;
    AddUserPage addUser;
    UpdateUserPage updateUser;
    UserManagementPage userManagement;
    SoftAssert soft;
    SignOutPage sign;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 17, enabled = true, description = "TC_017_Verify Edit User page title")
    public void verifyEditUserPageTitle() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Regression");
        extentTest.get().assignCategory("Sanity");
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
        extentTest.get().log(Status.PASS, "Clicked on users tab");
        updateUser=user.clickOnEditButton(user.getUserNameToSearch());
        extentTest.get().log(Status.PASS, "Clicked on edit button");
        String actualTitle= updateUser.getEditUserPageTitle();
        extentTest.get().log(Status.PASS, "Get actual page title");
        String expectedTitle=updateUser.getEditUserPageExpectedTitle();
        soft.assertEquals(actualTitle, expectedTitle, "Error:No user founded");
        sign = home.clickOnUserName();
        login = sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Logout from user page and redirected to login page");
        soft.assertAll();

    }
    @Test(priority = 18, enabled = true, description = "TC_018_Verify user can edit the user details")
    public void verifyUserCanEditTheUserDetails() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement=new UserManagementPage(driver);
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
        extentTest.get().log(Status.PASS, "Clicked on user management tab");
        user = userManagement.clickOnUserTabs();
        updateUser=user.clickOnEditButton(user.getUserNameToSearch());
        extentTest.get().log(Status.PASS, "Clicked on edit button");
        updateUser.clickOnEmail();
        updateUser.set_Email(updateUser.get_Email());
        extentTest.get().log(Status.PASS, "email is edited");
        user= updateUser.clickOnUpdate();
        extentTest.get().log(Status.PASS, "updated the changes");
        List<ArrayList<String>> tableData = user.getTableData();
        boolean value = user.getTableDataContains(tableData, updateUser.get_Email());
        soft.assertTrue(value);
        Thread.sleep(6000);
        sign = home.clickOnUserName();
        sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Clicked on log out");
        soft.assertAll();

}
}

