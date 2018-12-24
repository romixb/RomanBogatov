import javafx.beans.property.SetProperty;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.System.setProperty;
import static org.testng.Assert.assertEquals;

public class SimpleTest {
    @Test
    public void simpleTest(){

        //if chromedriver is placed in a different from project root directory (default location to invoke implicitly) - specify it explicitly:
        setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //Explanation on explicit/implicit wait: http://qa-blog.alexei-vinogradov.de/2015/07/правда-о-явных-и-неявных-ожиданиях-в-selenium/
        // Explicitly - we define driver wait when invoking a method
        //ImplicitWait - we define a waiting amount before invoking any methods and till this var is changed. This value is used by default for all methods invoked after definition
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //Does pageLoadTimeout work implicitly?
        //driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);

        driver.navigate().to("https://epam.github.io/JDI/index.html");

        //Good practice - priorly import static method e.g. Assert to minimize code
        //Assert.assertEquals(driver.getTitle(), "Home Page");
        assertEquals(driver.getTitle(), "Home Page");


        driver.close();
        //driver.quit();

        //Desired capabilities for chrome: http://chromedriver.chromium.org/capabilities
        //ChromeOptions opt = new ChromeOptions();



    }
}


