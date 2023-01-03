package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.glgl.BaseWebPage;
import tests.BaseWebTest;

public class TestRailTest extends BaseWebTest {

    @TmsLink("1")
    @Description("Test One")
    @Test
    public void testOne() {
        navToHomePage();
        secStep();
    }

    @Step("Navigate to home page")
    private void navToHomePage() {
        String title = new BaseWebPage().getPageTitle();
        Assert.assertEquals(title, "Some");
    }

    @Step("Second step")
    private void secStep() {
        Assert.assertTrue(true);
    }
}
