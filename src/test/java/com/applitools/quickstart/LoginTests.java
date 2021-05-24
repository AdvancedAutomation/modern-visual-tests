package com.applitools.quickstart;


import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.quickstart.driver.DriverManager;
import com.applitools.quickstart.driver.Environment;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class LoginTests {

    private static final WebDriver DRIVER = DriverManager.getInstance().getDriver();

    private static EyesRunner runner;
    private static Eyes eyes;
    private static BatchInfo batch;

    @BeforeClass
    public static void setBatch() {
        //Must before All tests (at Class Level)
        batch = new BatchInfo("Login Test Visual AI");

        // Initialize the Runner for your test.
        runner = new ClassicRunner();

        // Initialize the eyes SDK/ Applitools SDK
        eyes = new Eyes(runner);

        // You can get your api key from the Applitools dashboard
        eyes.setApiKey(Environment.getInstance().getToken());

        // set new batch (groups all your Applitools tests into a single group)
        eyes.setBatch(batch);

    }

    @Test
    public void loginTestPositive() {
        // Navigate the browser to the "ACME" demo app.
        DRIVER.get("https://demo.applitools.com/");

        // DRIVER.findElement(By.id("log-in")).click();

        // Set AUT's name, test name and viewport size (width X height)
        // We have set it to 800 x 600 to accommodate various screens. Feel free to
        // change it.
        eyes.open(DRIVER, "Demo App", "Smoke Test one", new RectangleSize(800, 600));

        //Take a screenshot so AI can analyze
        eyes.checkWindow("Login Window");

        // End the test.
        eyes.closeAsync();
    }

    @Test
    public void loginTestNegative() {
        // Navigate the browser to the "ACME" demo app.
        DRIVER.get("https://demo.applitools.com/index_v2.html");

        // DRIVER.findElement(By.id("log-in")).click();

        // Set AUT's name, test name and viewport size (width X height)
        // We have set it to 800 x 600 to accommodate various screens. Feel free to
        // change it.
        eyes.open(DRIVER, "Demo App", "Smoke Test one", new RectangleSize(800, 600));

        //Take a screenshot so AI can analyze
        eyes.checkWindow("Login Window");

        // End the test.
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
