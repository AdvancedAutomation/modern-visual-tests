package com.applitools.quickstart.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public final class DriverManager {

    private static DriverManager driverManager;

    private final WebDriver driver;
    private final WebDriverWait wait;

    private DriverManager() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, 30, 500);
    }

    public static DriverManager getInstance() {
        if (driverManager == null) {
            driverManager = new DriverManager();
        }
        return driverManager;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void setImplicitTime(int implicitTimeWait) {
        driver.manage().timeouts().implicitlyWait(implicitTimeWait, TimeUnit.SECONDS);
    }
}
