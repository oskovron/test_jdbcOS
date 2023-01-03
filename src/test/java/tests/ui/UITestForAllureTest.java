package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.glgl.BaseWebPage;
import tests.BaseWebTest;

public class UITestForAllureTest extends BaseWebTest {

    @TmsLink("")
    @Description("Some detailed test description")
    @Test
    public void test() {
        assertTitle();
    }

    @Step("first Ui test step. allure annotation")
    private void assertTitle() {
//        String title = driver.getTitle();
//        Assert.assertEquals(title, "Some title");
        String title = new BaseWebPage().getPageTitle();
//        Selenide.screenshot(RandomStringUtils.randomAlphabetic(5));
        Assert.assertEquals(title, "Some title");
    }

}
