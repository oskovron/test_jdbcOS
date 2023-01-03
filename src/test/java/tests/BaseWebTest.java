package tests;

import com.codeborne.selenide.WebDriverRunner;
import constants.Properties;
import core.PropertiesReader;
import listeners.TestNGListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import static com.codeborne.selenide.Selenide.open;

//Todo: you can specify listeners class for parent class or in tesNG.xml
@Listeners({TestNGListener.class})
//@Listeners({ ScreenShooter.class})
public class BaseWebTest {
//    protected static WebDriver driver;
//    private InitDriver initDriver;

//    @BeforeSuite
//    public static void initBeforeSuite() {
//        ScreenShooter.captureSuccessfulTests = true;
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true)
//                .savePageSource(false));
//    }

    @BeforeClass
    public void setUp() {
//in selenium
//        initDriver = new InitDriver();
//        initDriver.initializeDriver();
//        driver = initDriver.getDriver();
//        driver.get("https://www.phptravels.net/");

        //todo: find documentation on how to use specific browser version, not to update my browser all the time
//        Configuration.browserSize = "1366x768";
//        open(Properties.GOOGLE_URL);
        open(PropertiesReader.getProperty(Properties.GOOGLE_URL));
        WebDriverRunner.driver().getWebDriver().manage().window().maximize();
    }

    //in selenium
//    @AfterClass
//    public void quit() {
//        driver.quit();
//    }

    @AfterClass
    public void closeUp() {

    }
}
