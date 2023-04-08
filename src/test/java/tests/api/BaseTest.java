package tests.api;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    //test
    SoftAssert softAssert;

    @BeforeMethod
    public void setup(){
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void tearDown(){
        softAssert.assertAll();
    }
}
