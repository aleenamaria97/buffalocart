package com.buffalocart.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WaitUtility {
    public static final long PAGE_LOAD_WAIT=20;
    public static final long EXPLICIT_WAIT=20;
    public static final long IMPLICIT_WAIT=20;

    public enum LocatorType{
        Id,Xpath,CssSelector,Name,TagName,LinkText,partialLinkText,ClassName;
    }
    public void waitForVisibilityOfElements(WebDriver driver, Enum locatorType, String target){
        WebDriverWait wait=new WebDriverWait(driver, TimeUnit.SECONDS.toSeconds(EXPLICIT_WAIT));
        if(locatorType.equals(LocatorType.Id)){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(target)));
        }
        else if(locatorType.equals(LocatorType.Name))
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(target)));
        }
        else if(locatorType.equals(LocatorType.Xpath))
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(target)));
        }
        else if(locatorType.equals(LocatorType.CssSelector))
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(target)));
        }
        else if(locatorType.equals(LocatorType.TagName))
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(target)));
        }
        else if(locatorType.equals(LocatorType.LinkText))
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(target)));
        }
        else if(locatorType.equals(LocatorType.partialLinkText))
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(target)));
        }
        else if (locatorType.equals(LocatorType.ClassName))
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(target)));
        }
    }
    public void waitForInvisibility(WebDriver driver, Enum locatorType, String target){
        WebDriverWait wait=new WebDriverWait(driver, TimeUnit.SECONDS.toSeconds(EXPLICIT_WAIT));
        if(locatorType.equals(LocatorType.Id)){
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(target)));
        }
        else if(locatorType.equals(LocatorType.Name))
        {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(target)));
        }
        else if(locatorType.equals(LocatorType.Xpath))
        {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(target)));
        }
        else if(locatorType.equals(LocatorType.CssSelector))
        {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(target)));
        }
        else if(locatorType.equals(LocatorType.TagName))
        {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName(target)));
        }
        else if(locatorType.equals(LocatorType.LinkText))
        {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(target)));
        }
        else if(locatorType.equals(LocatorType.partialLinkText))
        {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.partialLinkText(target)));
        }
        else if (locatorType.equals(LocatorType.ClassName))
        {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(target)));
        }
    }
}
