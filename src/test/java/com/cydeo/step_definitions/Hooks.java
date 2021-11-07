package com.cydeo.step_definitions;


import com.cydeo.utility.ConfigReader;
import com.cydeo.utility.DB_Util;
import com.cydeo.utility.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {
    @BeforeAll
    public static void init() {
        String url = ConfigReader.read("library2.database.url");
        String username = ConfigReader.read("library2.database.username");
        ;
        String password = ConfigReader.read("library2.database.password");
        ;

        DB_Util.createConnection(url, username, password);

    }

    // We can set up a hook class that contains
    // methods that run before each scenario and after each scenario
    // or even when we learn tags
    // we can run certain code before and after each scenario that tagged with certain tag
    @Before("@ui")
    public void setupDriver(){
        System.out.println("THIS IS FROM @Before inside hooks class");
        // set up implicit wait or all the browser related set up
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        // maximize browser here if you wanted
        Driver.getDriver().manage().window().maximize();
    }

    @After("@ui")
    public void tearDown(Scenario scenario){

        //check if scenario failed or not
        if(scenario.isFailed()){
            //this is how we take screenshot in selenium
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshot, "image/png","WHAT EVER YOU WANT ");

        }


        System.out.println("THIS IS FROM @After inside hooks class");
        Driver.closeBrowser();

    }
    @AfterAll
    public static void teardown() {
        DB_Util.destroy();
    }
}
