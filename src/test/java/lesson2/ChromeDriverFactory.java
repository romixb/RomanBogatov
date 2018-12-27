package lesson2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverFactory {

    public WebDriver newDriver(){
        return new ChromeDriver();
    }


}
