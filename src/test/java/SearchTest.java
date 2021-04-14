import Pages.SearchPage;
import core.BaseWebDriverTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTest extends BaseWebDriverTest {

    String strURL = "https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_csharp_search/862b0faa506b8487c25a3384cfde8af4/static/attachments/reference_page.html";

    @Test
    public void CheckTheCorrectMessageIfSearchBoxIsBlank() throws Exception {

            driver.get(strURL);
            SearchPage searchPage = new SearchPage(driver);
            searchPage.clickSearchBtn();

            WebElement resultEle =driver.findElement(By.id("error-empty-query"));
            Assert.assertEquals(resultEle.getText(),"Provide some query");



    }

    @Test
    public void CheckTheCorrectMessageIfSearchBoxHasValidValue() throws Exception {

            SearchPage searchPage = new SearchPage(driver);
            driver.get(strURL);
            searchPage.enterSearchText("isla");
            searchPage.clickSearchBtn();

            //Get list of elemens as result after search
            List<WebElement> resultEle =driver.findElements(By.xpath("//*[@id=\"search-results\"]/li"));

            for(int i=0;i<resultEle.size();i++)
            {
                WebElement element = resultEle.get(i);
                //Compare the result with the search criteria
                Assert.assertTrue(element.getText().toLowerCase().contains("isla"));
            }


    }
}
