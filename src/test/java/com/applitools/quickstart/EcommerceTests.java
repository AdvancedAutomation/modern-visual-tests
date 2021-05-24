package com.applitools.quickstart;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.quickstart.driver.DriverManager;
import com.applitools.quickstart.driver.Environment;
import com.applitools.quickstart.driver.WebDriverTools;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EcommerceTests {

    private static final WebDriver DRIVER = DriverManager.getInstance().getDriver();

    private static EyesRunner runner;
    private static Eyes eyes;
    private static BatchInfo batch;

    @BeforeClass
    public static void setBatch() {
        //Must before All tests (at Class Level)
        batch = new BatchInfo("E-commerce Visual AI");

        // Initialize the Runner for your test.
        runner = new ClassicRunner();

        // Initialize the eyes SDK/ Applitools SDK
        eyes = new Eyes(runner);

        // You can get your api key from the Applitools dashboard
        eyes.setApiKey(Environment.getInstance().getToken());

        // set new batch (groups all your Applitools tests into a single group)
        eyes.setBatch(batch);
        //Navigation
        DRIVER.get("https://sapui5.hana.ondemand.com/test-resources/sap/m/demokit/cart/webapp/index.html#/category/AC");

    }

    @Test
    public void productListTestNEW() {

        // Start the test
        eyes.open(DRIVER, "SAP App", "Products List", new RectangleSize(1600, 800));

        // Check the product list
        eyes.check("Product List", Target.region(By.id("container-cart---category--page-cont")).fully());

        // End the test
        eyes.closeAsync();
    }

    @Test
    public void productGridTestNEW() {

        // Start the test
        eyes.open(DRIVER, "SAP App", "Products Grid", new RectangleSize(1600, 800));

        // Check the Product Grid
        eyes.check("Product Grid", Target.region(By.id("container-cart---welcomeView--page-cont")).fully());

        // End the test
        eyes.closeAsync();
    }

    @Test
    public void productDetailsTestNEW() {

        // Start the test
        eyes.open(DRIVER, "SAP App", "Products Details", new RectangleSize(1600, 800));

        // Open the 1st item (Available)
        WebDriverTools.clickElement(By.id("__item0-container-cart---category--productList-0-content"));

        // Check the product's details page
        eyes.checkWindow("Product details page");

        // End the test
        eyes.closeAsync();
    }

    @Test
    public void addToCartAvailableItemTestNEW() {

        // Start the test
        eyes.open(DRIVER, "SAP App", "Add Available Item to Cart", new RectangleSize(1600, 800));

        // Open the 1st item (Available)
        WebDriverTools.clickElement(By.id(("__item0-container-cart---category--productList-0-content")));

        // Add to cart
        WebDriverTools.clickElement(By.id(("__button9")));

        // Open cart
        WebDriverTools.clickElement(By.id(("__button8")));

        // Check the Cart page
        eyes.checkWindow("Cart page");

        // End the test
        eyes.closeAsync();
    }

    @Test
    public void addToCartOutOfStockItemTestNEW() {

        // Start the test
        eyes.open(DRIVER, "SAP App", "Add out-of-stock item to Cart", new RectangleSize(1600, 800));

        // Select the Out-of-stock item
        WebDriverTools.clickElement(By.id(("__item0-container-cart---category--productList-1")));

        // Add to cart
        WebDriverTools.clickElement(By.id(("__button9")));

        // Check the warning message
        eyes.checkWindow("Out of stock warning");

        // End the test
        eyes.closeAsync();

    }

    @AfterClass
    public static void tearDown() {
        DRIVER.quit();

        //If the test was aborted before eyes.close was called,ends the test as aborted
        eyes.abort();
        // Wait and collect all test results
        // we pass false to this method to suppress the exception that is thrown if we
        // find visual differences
        TestResultsSummary allTestResults = runner.getAllTestResults(false);

        // Print results
        System.out.println(allTestResults);
    }
}
