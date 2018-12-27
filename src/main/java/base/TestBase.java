package base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.setProperty;

public class TestBase {

    private long time;

    @BeforeSuite
    public void beforeSuite(){
        setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
        time = currentTimeMillis();
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("Test framework worked: " + (currentTimeMillis() - time));    // this value shows how long the entire framework worked
    }

}
