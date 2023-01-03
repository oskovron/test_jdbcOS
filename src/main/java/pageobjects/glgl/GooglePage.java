package pageobjects.glgl;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class GooglePage extends BaseWebPage{
    private SelenideElement searchField = $(Selectors.byName("q"));

    public void setSearchField(String searchText) {
        searchField.setValue(searchText);
    }


}
