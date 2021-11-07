package com.cydeo.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
    private static WebDriver obj;

    private Driver() {
    }

    /**
     * Return obj with only one WebDriver instance
     * @return same WebDriver if exists, nw onw if null
     */

    public static WebDriver getDriver() {

        // read the browser type you want to launch from properties file
        String browserName = ConfigReader.read("browser");

        if (obj == null) {

            //according to browser type set up driver correctly
            switch (browserName.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    obj = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    obj = new FirefoxDriver();
                    break;
                // other browsers omitted
                default:
                    obj = null;
                    System.out.println("UNKNOWN BROWSER TYPE!!! " + browserName);
            }

            //obj.manage().window().maximize();
//            System.out.println("One and only created for the first time");
            return obj;

        }else{
//            System.out.println("You have it just use existing one");
            return obj;

        }

    }

    /**
     * Quitting the browser and setting the value of
     * WebDriver instance to null because you can re-use already quited driver
     */

    public static void closeBrowser(){

        //check if we have WebDriver instance or not
        //basically checking of obj is null or not
        // if not null
        // quit the browser
        //  make it null, because once quit it can not be used

        if(obj != null){
            obj.quit();
            obj = null;// so when we ask for it again, it gives us not quited fresh driver
        }
    }

}
