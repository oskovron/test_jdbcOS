package tests.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.Test;
import java.io.File;

public class UploadFileTest {

    @Test
    public void testUploadFile() {
        Selenide.open("https://demoqa.com/upload-download");
        WebDriverRunner.driver().getWebDriver().manage().window().maximize();
        SelenideElement uploadFileInput = Selenide.$("#uploadFile");
        uploadFileInput.uploadFile(new File("src/test/resources/test_data/FileToUpload"));

//        Selenide.$("#downloadButton").click();
    }
}
