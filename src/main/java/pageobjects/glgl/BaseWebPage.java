package pageobjects.glgl;

import com.codeborne.selenide.Selenide;

public class BaseWebPage {

    public String getPageTitle() {
        return Selenide.$x("//title").getText();
    }
}
