package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class AddUserTest extends Base {
    LoginPage login;
    HomePage home;
    SignOutPage sign;
    UserManagementPage userManagement;
    SoftAssert soft;
    UserPage user;
    AddUserPage addUser;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 13, enabled = true, description = "TC_013_Verify the error message displayed without filling mandatory fields in add user form")
    public void verifyTheErrorMessageDisplayedWithoutFillingMandatoryFieldsInAddUserForm() throws IOException {
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
        addUser=user.clickAddUserTab();
        extentTest.get().log(Status.PASS, "clicked on add user tab and redirected to add user page");
        addUser.clickOnSaveButton();
        String actualMsg= addUser.getActualErrorMsg();
        String expectedMsg=addUser.getExpectedErrorMsg();
        soft.assertEquals(actualMsg, expectedMsg, "Error:Error message is not displayed");
        soft.assertAll();
        sign = home.clickOnUserName();
        login = sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Logout from user page and redirected to login page");
    }
    @Test(priority = 14, enabled = true, description = "TC_014_Verify user login with newly added user")
    public void verifyUserLoginWithNewlyAddedUser() throws IOException, InterruptedException {
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
        addUser = user.clickAddUserTab();
        addUser.enterPrefix(addUser.get_prefix());
        extentTest.get().log(Status.PASS, "prefix entered");
        addUser.enterFirstName(addUser.get_FirstName());
        extentTest.get().log(Status.PASS, "first name entered");
        addUser.enterLastName(addUser.get_LastName());
        extentTest.get().log(Status.PASS, "last name entered");
        addUser.enterId(addUser.get_Id());
        extentTest.get().log(Status.PASS, "Email entered");
        addUser.selectRole();
        extentTest.get().log(Status.PASS, "Selected Role");
        addUser.enterUserName(addUser.get_UserName());
        extentTest.get().log(Status.PASS, "User Name entered");
        addUser.enterPassWord(addUser.get_PWord());
        extentTest.get().log(Status.PASS, "Password entered");
        addUser.enterConfirmPassWord(addUser.get_CPWord());
        extentTest.get().log(Status.PASS, "Confirmation password entered");
        addUser.clickOnSaveButton();
        extentTest.get().log(Status.PASS, "Clicked on saved button");
        sign=home.clickOnUserName();
        login=sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Clicked on log out");
        login.enterUserName(addUser.get_UserName());
        addUser.enterPassWord(addUser.get_UserName());
        extentTest.get().log(Status.PASS, "New user credentials entered");
        login.clickOnLoginButton();
        String actualTitle= home.getHomePageActualTitle();
        String expectedTitle= home.getHomePageExpectedTitle();
        soft.assertEquals(actualTitle,expectedTitle,"Verify user login with newly added user test failed");
        soft.assertAll();
        sign= home.clickOnUserName();
        login=sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Clicked on log out");
    }
    @Test(priority = 15, enabled = true, description = "TC_015_Verify  Add Users page title")
    public void verifyAddedUsePageTitle() throws IOException {
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
        addUser = user.clickAddUserTab();
        String actualTitle=addUser.getPageTitle();
        String expectedTitle=addUser.getPageExpectedTitle();
        soft.assertEquals(actualTitle,expectedTitle,"Error:Add user page title not founded");
        soft.assertAll();
        sign= home.clickOnUserName();
        login=sign.clickOnLogOutButton();}


}
