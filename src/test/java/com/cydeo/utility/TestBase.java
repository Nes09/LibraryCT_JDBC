package com.cydeo.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class TestBase {
    protected WebDriver driver;

    @BeforeAll
    public static void init() {
        String url = ConfigReader.read("library2.database.url");
        String username = ConfigReader.read("library2.database.username");
        ;
        String password = ConfigReader.read("library2.database.password");
        ;

        DB_Util.createConnection(url, username, password);

    }

    // setting up all driver stuff here directly in @BeforeEach method
    @BeforeEach
    public void setupWebDriver() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
        driver = Driver.getDriver();
        // This is how we can set maximum timeout for finding element
        // in this example it will wait for 10 seconds
        // if element is found in 1 second ,it will just move on without finishing 10 seconds
        // Thread.sleep(100000) will always wait for 10 seconds no matter what.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    public void closeBrowser() {
        Driver.closeBrowser();
    }


    @AfterAll
    public static void teardown() {
        DB_Util.destroy();
    }
}

