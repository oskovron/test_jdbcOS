package utils;

import com.codeborne.selenide.Selenide;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.InputEvent;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.String.format;

@UtilityClass
public class RobotUtils {

    private static Robot robot = getRobotInstance();

    public static Robot getRobotInstance() {
        if (robot == null) {
            try {
                robot = new Robot();
                robot.setAutoWaitForIdle(true);
                robot.setAutoDelay(800);
            } catch (AWTException e) {
                throw new IllegalStateException("Failed to instantiate Robot", e);
            }
        }
        return robot;
    }

    /**
     * Performs click-and-hold at the location of the source element,
     * moves to the location of the target element, then releases the mouse.
     * Params:
     * source – element to emulate mouse button down at.
     * target – element to move to and release the mouse at.
     */
    public static void dragAndDrop(WebElement source, WebElement target) {
        var calculatedSourcePoint = addBrowserWindowOffset(getWebElementCenter(source));
        var calculatedTargetPoint = addBrowserWindowOffset(getWebElementCenter(target));
        robot.mouseMove(calculatedSourcePoint.getX(), calculatedSourcePoint.getY());
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseMove(calculatedTargetPoint.getX(), calculatedTargetPoint.getY());
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Selenide.sleep(1000);
    }

    public static void hoverOnElement(WebElement target) {
        var calculatedTargetPoint = addBrowserWindowOffset(getWebElementCenter(target));
        robot.mouseMove(calculatedTargetPoint.getX(), calculatedTargetPoint.getY());
    }


    private Point getWebElementCenter(WebElement webElement) {
        var dimensions = webElement.getSize();
        var topRectValue = toInt(getBoundClientRect("top", webElement));
        var leftRectValue = toInt(getBoundClientRect("left", webElement));

        return new Point(leftRectValue + dimensions.getWidth() / 2,
                topRectValue + dimensions.getHeight() / 2);
    }

    private Point addBrowserWindowOffset(Point point) {
        var windowPoint = getWebDriver().manage().window().getPosition();
        return new Point(point.getX() + windowPoint.getX(), point.getY() + windowPoint.getY() + 125);
    }

    private static <T> T getBoundClientRect(String rectOptions, WebElement element) {
        return executeJavaScript(format("return arguments[0].getBoundingClientRect().%s", rectOptions), element);
    }

    private static int toInt(Object object) {
        return object != null ? Double.valueOf(object.toString()).intValue() : 0;
    }

}

