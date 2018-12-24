package lessons;

import javafx.beans.property.SetProperty;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.System.setProperty;
import static org.testng.Assert.assertEquals;

public class Lesson_1 {
    @Test
    public void simpleTest(){

//        if chromedriver is placed in a different from project root directory (default location to invoke implicitly) - specify it explicitly:
        setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

//        Explanation on explicit/implicit wait: http://qa-blog.alexei-vinogradov.de/2015/07/правда-о-явных-и-неявных-ожиданиях-в-selenium/
//        Explicitly - we define driver wait when invoking a method
//        ImplicitWait - we define a waiting amount before invoking any methods and till this var is changed. This value is used by default for all methods invoked after definition
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

//        Does pageLoadTimeout work implicitly?
//        driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);

        driver.navigate().to("https://epam.github.io/JDI/index.html");

//        Good practice - priorly import static method e.g. Assert to minimize code
//        Assert.assertEquals(driver.getTitle(), "Home Page");
//
//        Assert Title
        assertEquals(driver.getTitle(), "Home Page");


//        Key combination doesn't open ne tab, why?
//        driver.findElement(By.tagName("body")).sendKeys(Keys.chord(Keys.CONTROL, "t"));
//        or
//        String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,"t");
//        driver.findElement(By.tagName("body")).sendKeys(selectLinkOpeninNewTab);

//
//        WebElements
//
//        Login
//        Put a breakpoint on an invocation and press f8 to run through the actions
        driver.findElement(By.cssSelector(".profile-photo")).click();
        driver.findElement(By.cssSelector("#Name")).sendKeys("epam");
        driver.findElement(By.cssSelector("#Password")).sendKeys("1234");
//        Why not use selector by class ".fa-sign-in"?
        driver.findElement(By.cssSelector(".login [type = 'submit']")).click();

//        Assert element.text()

        WebElement title = driver.findElement(By.cssSelector(".main-title"));
        assertEquals(title.getText(), "EPAM FRAMEWORK WISHES…");




        driver.close();
//        driver.quit();

//        Desired capabilities for chrome: http://chromedriver.chromium.org/capabilities
//        ChromeOptions opt = new ChromeOptions();



    }
}


