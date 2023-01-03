package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class InitDriver {
    private static WebDriver driver;

    public void initializeDriver() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Oksana\\gitStudy\\test_jdbcOS\\src\\test\\resources\\win\\32\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static WebDriver getDriver() {
        return driver;
    }

}
