package Pages;

import core.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SearchPage extends PageObject {
    public SearchPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(how= How.ID, using ="search-input")
    public WebElement textSearchInput;

    @FindBy(how= How.ID, using ="search-button")
    public WebElement btnSearch;

    public void clickSearchBtn()
    {
        btnSearch.click();
    }

    public void enterSearchText(String strSearch)
    {
        textSearchInput.sendKeys(strSearch);
    }
}
