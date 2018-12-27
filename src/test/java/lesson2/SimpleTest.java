package lesson2;


import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.System.setProperty;
import static org.testng.Assert.assertEquals;

public class SimpleTest extends TestBase {
    private WebDriver driver;


    @BeforeMethod(alwaysRun = true)
    public void beforeTest(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
        driver.close();
    }

    @Test(groups = "Group1")
    public void simpleTest(){
        //2. Navigate
        driver.navigate().to("https://epam.github.io/JDI/index.html");
        //3. Assert Title
        assertEquals(driver.getTitle(), "Home Page");
        //4. login
        driver.findElement(By.cssSelector(".profile-photo")).click();
        driver.findElement(By.cssSelector("#Name")).sendKeys("epam");
        driver.findElement(By.cssSelector("#Password")).sendKeys("1234");
        driver.findElement(By.cssSelector(".login [type = 'submit']")).click();

        WebElement title = driver.findElement(By.cssSelector(".main-title"));
        assertEquals(title.getText(), "EPAM FRAMEWORK WISHES…");//This test fails because
    }
}


