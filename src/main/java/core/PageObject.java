package core;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;


public class PageObject {

    public WebDriver driver;

    protected DriverHelper driverHelper;

    public PageObject(WebDriver driver)
    {
        this.driver = driver;

        PageFactory.initElements(driver,this);

        driverHelper = new DriverHelper(driver);
    }


}
