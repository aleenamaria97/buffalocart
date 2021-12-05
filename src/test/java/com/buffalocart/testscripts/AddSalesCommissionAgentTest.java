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

public class AddSalesCommissionAgentTest extends Base {
    LoginPage login;
    HomePage home;
    SignOutPage sign;
    UserManagementPage userManagement;
    SoftAssert soft;
    SalesCommissionAgentPage sales;
    AddSalesCommissionAgentPage addSales;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 29, enabled = true, description = "TC_029_Verify  user can add sales  agent ")
    public void verifyUserCanAddSalesAgent () throws IOException, InterruptedException {
        extentTest.get().assignCategory("Smoke");
        extentTest.get().assignCategory("Sanity");
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
        sales = userManagement.clickOnSalesCommissionTab();
        extentTest.get().log(Status.PASS, "clicked on user tab and redirected to Sales commission page");
        addSales=sales.clickOnAddButton();
        extentTest.get().log(Status.PASS, "clicked on add button");
        addSales.enterPrefix(addSales.get_prefix());
        extentTest.get().log(Status.PASS, "Prefix added");
        addSales.enterFirstName(addSales.get_FirstName());
        extentTest.get().log(Status.PASS, "First name added");
        addSales.enterLastName(addSales.get_LastName());
        extentTest.get().log(Status.PASS, "Last name added");
        addSales.enterEmail(addSales.get_Email());
        extentTest.get().log(Status.PASS, "Email added");
        addSales.enterConNumber(addSales.get_cNumber());
        extentTest.get().log(Status.PASS, "mobile number added");
        addSales.enterAddress(addSales.get_Address());
        extentTest.get().log(Status.PASS, "Address added");
        addSales.enterSalesPercentage(addSales.get_salesPer());
        extentTest.get().log(Status.PASS, "Sales percentage added");
        sales=addSales.clickOnSaveButton();
        extentTest.get().log(Status.PASS, "Saved the details");
        Thread.sleep(6000);
        String expectedAgentName = sales.getAddedSalesCommissionAgent();
        extentTest.get().log(Status.PASS, "Expected agent name generated");
        List<ArrayList<String>> tableData = sales.getTableData();
        boolean value = sales.getTableDataContains(tableData, expectedAgentName);
        soft.assertTrue(value);
        sign=home.clickOnUserName();
        login=sign.clickOnLogOutButton();
        soft.assertAll();
        extentTest.get().log(Status.PASS, "Logout");
    }
}