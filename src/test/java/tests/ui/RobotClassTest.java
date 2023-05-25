package tests.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RobotClassTest {
    @Test
    public void testRobotClassMouseMove() throws AWTException, InterruptedException {
        Selenide.open("https://demoqa.com/automation-practice-form");
        WebDriverRunner.driver().getWebDriver().manage().window().maximize();
        SelenideElement uploadFileInput = Selenide.$("#uploadPicture");
        uploadFileInput.click();
        Robot robot = new Robot();

        Dimension i = WebDriverRunner.driver().getWebDriver().manage().window().getSize();
        int x = (i.getWidth()/4)+20;
        int y = (i.getHeight()/10)+50;

        robot.mouseMove(x,y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        System.out.println("Browse button clicked");
        Thread.sleep(2000);

        //Closes the Desktop Windows popup
        robot.keyPress(KeyEvent.VK_ENTER);
        System.out.println("Closed the windows popup");
        Thread.sleep(1000);


    }
}
