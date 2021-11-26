package com.buffalocart.pages;

import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePage extends TestHelperUtility {
    WebDriver driver;
    public  HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private final String _EndTour="//button[@class='btn btn-default btn-sm']";
    @FindBy(xpath = _EndTour)
    private WebElement endTour;
    private final String _UserName="//a[@class='dropdown-toggle']";
    @FindBy(xpath = _UserName)
    private WebElement userName;
    private final String _Date="//div[@class='m-8 pull-left mt-15 hidden-xs']";
    @FindBy(xpath =_Date)
    private WebElement date;
    public void endTour(){
           page.clickOnElement(endTour);
       }
    public String verifyUserName(){
        return page.getElementText(userName);
    }
    public SignOutPage clickOnUserName(){
         page.clickOnElement(userName);
         return new SignOutPage(driver);
    }

    public String getHomePageTitle(){
        return page.getPageTitle(driver);
    }
    public String getHomePageDate(){
        return page.getElementText(date);
    }
   public String setHomePageDate(){
       Date date = new Date();
       String exdate = new SimpleDateFormat("MM/dd/yyyy").format(date);
       return exdate;
   }
}
