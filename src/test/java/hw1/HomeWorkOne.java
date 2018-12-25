package hw1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomeWorkOne {

    public ChromeDriver driver;

    private static final String TARGET_URL = "https://epam.github.io/JDI/";


    private static final String EXPECTED_TITLE = "Home Page";

    private static final String TEST_USER = "epam";


    private static final String TEST_PASSWORD = "1234";

    private static final String EXPECTED_USER_NAME = "PITER CHAILOVSKII";

    private static final int EXPECTED_NAV_SIZE = 4;

    private static final List<String> EXPECTED_NAV_BAR_TEXT = new ArrayList<String>() {
            {
                add("HOME");
                add("CONTACT FORM");
                add("SERVICE");
                add("METALS & COLORS");
            }
        };

    private static final List<String> EXPECTED_TEXT = new ArrayList<String>(){
            {   //received values via IDEA Evaluate
                add("To include good practices\n" +
                        "and ideas from successful\n" +
                        "EPAM project");
                add("To be flexible and\n" +
                        "customizable");
                add("To be multiplatform");
                add("Already have good base\n" +
                        "(about 20 internal and\n" +
                        "some external projects),\n" +
                        "wish to get more…");
            }
        };

    private static final List<String> EXPECTED_HEADER_TEXT = new ArrayList<String>(){
            {
                add("EPAM FRAMEWORK WISHES…");
                add("LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT" +
                        " LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO " +
                        "LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN " +
                        "VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.");
            }
        };

    private static final String EXPECTED_SUB_HEADER_TEXT = "JDI GITHUB";

    private static final String EXPECTED_URL = "https://github.com/epam/JDI";


//----------------------------------------------------------------------------------------------------------------------
    @BeforeClass
    public void launchBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//        Why this doesn't work if invoked in next @Test?
        driver.navigate().to(TARGET_URL);
   }
//----------------------------------------------------------------------------------------------------------------------
//   1. Open test site by URL
    @Test
    public void navigateTo(){
       driver.navigate().to(TARGET_URL);
    }
//----------------------------------------------------------------------------------------------------------------------
//    2. Assert Browser title
    @Test
    public void checkTitle(){
       assertEquals(driver.getTitle(), EXPECTED_TITLE);
    }
//----------------------------------------------------------------------------------------------------------------------
//    3.Perform login
    @Test
    public void checkLoginActions(){
        driver.findElement(By.cssSelector(".profile-photo")).click();
        driver.findElement(By.cssSelector("#Name")).sendKeys(TEST_USER);
        driver.findElement(By.cssSelector("#Password")).sendKeys(TEST_PASSWORD);
        driver.findElement(By.cssSelector(".login [type = 'submit']")).click();
    }
//----------------------------------------------------------------------------------------------------------------------
//    4.Assert User name in the left-top side of screen that user is logged
    @Test(dependsOnMethods = {"checkLoginActions"})
    public void checkUserName() {
        WebElement userNameElement = driver.findElement(By.cssSelector(".profile-photo span"));
        assertEquals(userNameElement.getText(), EXPECTED_USER_NAME);
    }
//----------------------------------------------------------------------------------------------------------------------
//    5. Assert Browser title
    @Test
    public void checkTitle2(){
        assertEquals(driver.getTitle(), EXPECTED_TITLE);
    }
//----------------------------------------------------------------------------------------------------------------------
//    6.Assert that there are 4 items on the header section are displayed and they have proper texts
    @Test
    public void checkNavBar() {
        List<WebElement> navElems = driver.findElements(By.cssSelector(".navbar-nav.m-l8 > li"));
        assertEquals(navElems.size(), EXPECTED_NAV_SIZE);
//        Comparing Text in navigation bar li elems with List of expected Strings
//        TODO: Refactor assert logic
//        for (WebElement li : navElems
//        ) {
//            assertTrue(getExpectedNavBarText().contains(li.getText()));
//        }
        assertEquals(navElems.get(0).getText(),EXPECTED_NAV_BAR_TEXT.get(0));
        assertEquals(navElems.get(1).getText(),EXPECTED_NAV_BAR_TEXT.get(1));
        assertEquals(navElems.get(2).getText(),EXPECTED_NAV_BAR_TEXT.get(2));
        assertEquals(navElems.get(3).getText(),EXPECTED_NAV_BAR_TEXT.get(3));
    }
//----------------------------------------------------------------------------------------------------------------------
//    7. Assert that there are 4 images on the Index Page and they are displayed
    @Test
    public void checkPageImages() {
        WebElement pageImg = driver.findElement(By.cssSelector(".icon-practise"));
        assertTrue(pageImg.isDisplayed());

        pageImg = driver.findElement(By.cssSelector(".icon-custom"));
        assertTrue(pageImg.isDisplayed());

        pageImg = driver.findElement(By.cssSelector(".icon-multi"));
        assertTrue(pageImg.isDisplayed());

        pageImg = driver.findElement(By.cssSelector(".icon-base"));
        assertTrue(pageImg.isDisplayed());
    }
//----------------------------------------------------------------------------------------------------------------------
//    8. Assert that there are 4 texts on the Index Page under icons and they have proper text
    @Test
    public void checkIconText(){
    List<WebElement> webElement = driver.findElements(By.cssSelector(".clerafix > .col-sm-3"));

    assertEquals(webElement.get(0).getText(), EXPECTED_TEXT.get(0));
    assertEquals(webElement.get(1).getText(), EXPECTED_TEXT.get(1));
    assertEquals(webElement.get(2).getText(), EXPECTED_TEXT.get(2));
    assertEquals(webElement.get(3).getText(), EXPECTED_TEXT.get(3));
    }
//----------------------------------------------------------------------------------------------------------------------
//    9.Assert a text of the main headers
    @Test
    public void checkHeaders(){
        WebElement webElement = driver.findElement(By.cssSelector("[name='main-title']"));
        assertEquals(webElement.getText(), EXPECTED_HEADER_TEXT.get(0));
        webElement = driver.findElement(By.cssSelector("[name='jdi-text']"));
        assertEquals(webElement.getText(), EXPECTED_HEADER_TEXT.get(1));
    }
//----------------------------------------------------------------------------------------------------------------------
//    10.Assert that there is the iframe in the center of page
    @Test
    public void checkIframe(){
        WebElement iFrame = driver.findElement(By.id("iframe"));
        assertTrue(iFrame.isDisplayed());
    }
//----------------------------------------------------------------------------------------------------------------------
//    11.Switch to the iframe and check that there is Epam logo in the left top conner of iframe
    @Test
    public void checkIframeLogo(){
        driver.switchTo().frame("iframe");
        WebElement logo = driver.findElement(By.id("epam_logo"));
        assertTrue(logo.isDisplayed());

//    12.Switch to original window back
        driver.switchTo().defaultContent();
    }
//----------------------------------------------------------------------------------------------------------------------
//    13.Assert a text of the sub header
    @Test
    public void checkSubHeaderText(){
        WebElement subHeader = driver.findElement(By.cssSelector("h3 > [target = '_blank']"));
        assertEquals(subHeader.getText(), EXPECTED_SUB_HEADER_TEXT);
    }
//----------------------------------------------------------------------------------------------------------------------
//    14.Assert that JDI GITHUB is a link and has a proper URL.Assert a text of the sub header
    @Test
    public void checkLink(){
        //TODO: refactor
        assertTrue(driver.findElement(By.linkText("JDI GITHUB")).isDisplayed());
        assertEquals(driver.findElement(By.linkText("JDI GITHUB")).getAttribute("href"), EXPECTED_URL);

    }
//----------------------------------------------------------------------------------------------------------------------
//    15.Assert that there is Left Section
    @Test
    public void checkLeftSection(){
        WebElement leftSection = driver.findElement(By.id("mCSB_1"));
        assertTrue(leftSection.isDisplayed());
    }
//----------------------------------------------------------------------------------------------------------------------
//    16.Assert that there is Footer
    @Test
    public void checkFooter(){
        WebElement footer = driver.findElement(By.cssSelector(".footer-content"));
        assertTrue(footer.isDisplayed());
    }
//----------------------------------------------------------------------------------------------------------------------
//    17.Close browser
    @AfterClass
    public void closeBrowser(){
       driver.close();
   }
}


