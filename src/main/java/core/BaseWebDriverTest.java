package core;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseWebDriverTest
{
    public static WebDriver driver;

    public FunctionalHelper functionalHelper;
    public DriverHelper driverHelper;




    private static final Logger logger = Logger.getLogger(BaseWebDriverTest.class.getName());


    @BeforeMethod(groups ={"Android","ServiceCentre"})
    public void setupAppium() throws MalformedURLException
    {
//        restHelper = new RestHelper();
        String strOS = System.getProperty("os.name");
        if(strOS.startsWith("Windows"))
        {
            setupChromeDriver();
        }


        driverHelper = new DriverHelper(driver);
        functionalHelper = new FunctionalHelper();


    }





    private void setupChromeDriver() throws MalformedURLException
    {

        DesiredCapabilities capability = DesiredCapabilities.chrome();

        capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        capability.setCapability("idleTimeOut",1000);
        capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);



        String os = System.getProperty("os.name").toLowerCase();
        if(os.indexOf("win")>=0){
            System.out.println("Using System Property: " + os);
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/test/resources/lib/chromedriver.exe");

        }else{
            if(os.indexOf("nix")>=0||os.indexOf("nux")>=0||os.indexOf("aix")>=0){
                System.out.println("Using System Property: " + os);
                System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/test/resources/lib/chromedriver");

            }else {
                if(os.indexOf("mac")>=0){
                    System.out.println("Using System Property: " + os);
                    System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/test/resources/lib/chromedriver_mac");
                }else
                    System.out.println("Your OS is not supported!!");
            }
        }
        ChromeOptions driverOptions = new ChromeOptions();
        driverOptions.merge(capability);
        driver = new ChromeDriver(driverOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);




        driver.manage().window().maximize();
    }



    @AfterSuite
    public void cleanUp()
    {

//        System.out.println("Before Condition: Inside AfterSuite");
        if(driver != null)
        {
            driver.quit();
        }
    }

   @AfterMethod
    public void closeAppWindow()
    {
        try {
            driver.quit();
        }catch(Exception e){
            driver.quit();
        }
    }




}

