package lesson2;


import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class SimpleTestInvocationCount extends TestBase {
    //This class is created to invoke @Test in multiple threads
    //To do so we move  WebDriver driver declaration inside the @Test method
    //private WebDriver driver;

    @Test(threadPoolSize = 5, invocationCount = 10)
    public void simpleTest(){
        //1.Open BR
        WebDriver driver = new ChromeDriverFactory().newDriver();//added an invocation of a new object

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

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
        assertEquals(title.getText(), "EPAM FRAMEWORK WISHES…");

        driver.close();
    }
}


