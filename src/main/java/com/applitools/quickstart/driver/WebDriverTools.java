package com.applitools.quickstart.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public final class WebDriverTools {

    public static void clickElement(By by) {
        DriverManager.getInstance().getWait().until(ExpectedConditions.elementToBeClickable(by));
        DriverManager.getInstance().getDriver().findElement(by).click();
    }

}
