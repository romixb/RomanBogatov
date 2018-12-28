package hw2.ex1;

import base.TestBase;
import lesson2.ChromeDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import dataproviders.DataProviders;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class Exercise1 extends TestBase {

    private WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriverFactory().newDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass(){
        driver.close();
    }

    @Test(threadPoolSize = 3, invocationCount = 10, dataProvider = "checkIconTextDataProvider", dataProviderClass = DataProviders.class)
    public void checkTextIcon(int i, String s){
        driver.navigate().to("https://epam.github.io/JDI/index.html");
        List<WebElement> webElements = driver.findElements(By.cssSelector(".clerafix > .col-sm-3"));
        assertEquals(webElements.get(i).getText(), s);


    }
}
