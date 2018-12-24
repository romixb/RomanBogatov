package hw1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class HomeWorkOne {

    public WebDriver driver;

    private String getTargetURl() {
        return "https://epam.github.io/JDI/";
    }

    private String getExpectedTitle() {
        return "Home Page";
    }

    private String getUser() {
        return "epam";
    }

    private String getPassword() {
        return "1234";
    }

    private String getUserName() {
        return "PITER CHAILOVSKII";

    }



    @BeforeClass
    public void launchBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//        Why this doesn't work if invoked in next @Test??
        driver.navigate().to(getTargetURl());
   }

//   1. Open test site by URL
    @Test
    public void navigateTo(){
       driver.navigate().to(getTargetURl());
    }

//    2. Assert Browser title
    @Test
    public void assertTitle(){
       assertEquals(driver.getTitle(), getExpectedTitle());
    }

//    3.Perform login
    @Test
    public void loginActions(){
        driver.findElement(By.cssSelector(".profile-photo")).click();
        driver.findElement(By.cssSelector("#Name")).sendKeys(getUser());
        driver.findElement(By.cssSelector("#Password")).sendKeys(getPassword());
        driver.findElement(By.cssSelector(".login [type = 'submit']")).click();
    }

//    4.Assert User name in the left-top side of screen that user is logged
    @Test
    public void assertUserName(){
        String userName = driver.findElement(By.cssSelector(".profile-photo span")).getText();

        assertEquals(userName, getUserName());
    }


    @AfterClass
    public void closeBrowser(){
       driver.close();
   }
}
