package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class ViewUserTest extends Base {
    LoginPage login;
    HomePage home;
    UserPage user;
    AddUserPage addUser;
    UpdateUserPage updateUser;
    UserManagementPage userManagement;
    SoftAssert soft;
    SignOutPage sign;
    ViewUserPage view;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 20, enabled = true, description = "TC_020_Verify  the details displayed on view user page")
    public void verifyUserTheDetailsDisplayedOnViewUserPage() throws IOException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        soft = new SoftAssert();
        userManagement = new UserManagementPage(driver);
        view=new ViewUserPage(driver);
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
        view=user.clickOnViewButton(view.get_UserNameToView());

    }

}
