package hw1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomeWorkOne {

    public WebDriver driver;

    private String getTargetURl() {
        return "https://epam.github.io/JDI/";
    }

    private String getExpectedTitle() {
        return "Home Page";
    }

    private String getTestUser() {
        return "epam";
    }

    private String getTestPassword() {
        return "1234";
    }

    private String getExpectedUserName() {
        return "PITER CHAILOVSKII";
    }

    private int getExpectedNavSize() {
        return 4;
    }

    private ArrayList<String> getExpectedNavBarText() {
        return new ArrayList<String>() {
            {
                add("HOME");
                add("CONTACT FORM");
                add("SERVICE");
                add("METALS & COLORS");
            }
        };
        }
//----------------------------------------------------------------------------------------------------------------------
    @BeforeClass
    public void launchBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//        Why this doesn't work if invoked in next @Test?
        driver.navigate().to(getTargetURl());
   }
//----------------------------------------------------------------------------------------------------------------------
//   1. Open test site by URL
    @Test
    public void navigateTo(){
       driver.navigate().to(getTargetURl());
    }
//----------------------------------------------------------------------------------------------------------------------
//    2. Assert Browser title
    @Test
    public void assertTitle(){
       assertEquals(driver.getTitle(), getExpectedTitle());
    }
//----------------------------------------------------------------------------------------------------------------------
//    3.Perform login
    @Test
    public void loginActions(){
        driver.findElement(By.cssSelector(".profile-photo")).click();
        driver.findElement(By.cssSelector("#Name")).sendKeys(getTestUser());
        driver.findElement(By.cssSelector("#Password")).sendKeys(getTestPassword());
        driver.findElement(By.cssSelector(".login [type = 'submit']")).click();
    }
//----------------------------------------------------------------------------------------------------------------------
//    4.Assert User name in the left-top side of screen that user is logged
    @Test(dependsOnMethods = {"loginActions"})
    public void assertUserName() {
        WebElement userNameElement = driver.findElement(By.cssSelector(".profile-photo span"));
        assertEquals(userNameElement.getText(), getExpectedUserName());
    }
//----------------------------------------------------------------------------------------------------------------------
//    5. Assert Browser title
    @Test
    public void assertTitle2(){
        assertEquals(driver.getTitle(), getExpectedTitle());
    }
//----------------------------------------------------------------------------------------------------------------------
//    5.Assert that there are 4 items on the header section are displayed and they have proper texts
    @Test
    public void navBarTest() {
        WebElement webElement = driver.findElement(By.cssSelector(".navbar-nav.m-l8"));
        List<WebElement> navElems = webElement.findElements(By.cssSelector(".navbar-nav.m-l8 > li"));
        assertEquals(navElems.size(), getExpectedNavSize());
//        Comparing Text in navigation bar li elems with List of expected Strings
        //TODO: Refactor assert logic
        for (WebElement li : navElems
        ) {
            assertTrue(getExpectedNavBarText().contains(li.getText()));
        }
    }
//----------------------------------------------------------------------------------------------------------------------
//    7. Assert that there are 4 images on the Index Page and they are displayed
    @Test
    
//----------------------------------------------------------------------------------------------------------------------
//    17.Close browser
    @AfterClass
    public void closeBrowser(){
       driver.close();
   }
}


