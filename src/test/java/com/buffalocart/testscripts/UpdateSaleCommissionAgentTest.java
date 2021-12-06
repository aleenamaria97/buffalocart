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

public class UpdateSaleCommissionAgentTest extends Base {
    LoginPage login;
    HomePage home;
    SignOutPage sign;
    UserManagementPage userManagement;
    SoftAssert soft;
    SalesCommissionAgentPage sales;
    UpdateSaleCommissionAgentPage updateSales;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 30, enabled = true, description = "TC_030_Verify Edit sales agent details",groups = {"Smoke","Regression"})
    public void verifyEditSalesAgentDetails () throws IOException, InterruptedException {
        extentTest.get().assignCategory("Smoke");
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
        updateSales=sales.clickOnEditButton(sales.getSalesToEdit());
        Thread.sleep(6000);
        extentTest.get().log(Status.PASS, "Clicked on edit button");
        updateSales.clickOnEmail();
        updateSales.enterEmail(updateSales.getEmailToEdit());
        extentTest.get().log(Status.PASS, "email is edited");
        sales= updateSales.clickOnUpdate();
        extentTest.get().log(Status.PASS, "updated the changes");
        List<ArrayList<String>> tableData = sales.getTableData();
        boolean value = sales.getTableDataContains(tableData,updateSales.getEmailToEdit());
        soft.assertTrue(value);
        Thread.sleep(6000);
        sign = home.clickOnUserName();
        sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Clicked on log out");
        soft.assertAll();
    }
}
