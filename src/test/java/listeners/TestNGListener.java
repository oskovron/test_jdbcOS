package listeners;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import core.TestRailHelper;
import io.qameta.allure.Attachment;
import libs.java.com.gurock.testrail.APIException;
import listeners.enums.TestRailStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;

public class TestNGListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        saveScreenshot(InitDriver.getDriver());
//
//        saveLogs(result.getMethod().getConstructorOrMethod().getName());
//        saveLogs(result.toString());
//        saveLogs(result.getHost());
        if (WebDriverRunner.hasWebDriverStarted()) {
            try {
                getFailureScreenshot();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            new TestRailHelper().addResultToTestRun("1", "1", TestRailStatus.PASSED.getId());
        } catch (APIException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onTestFailure(ITestResult result) {
//        saveScreenshot(InitDriver.getDriver());
//

        if (WebDriverRunner.hasWebDriverStarted()) {
            try {
                getFailureScreenshot();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        saveLogs(result.getMethod().getConstructorOrMethod().getName());
        saveLogs(result.toString());
        saveLogs(result.getThrowable().getMessage());
        saveLogs(result.getHost());

        try {
            new TestRailHelper().addResultToTestRun("1", "1", TestRailStatus.FAILED.getId());
        } catch (APIException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

    @Attachment(value = "Failure screenshot", type = "image/png")
    public byte[] getFailureScreenshot() throws IOException {
        //todo: Configuration.reportsFolder = "test-result/reports"; ???
        return Files.readAllBytes(Screenshots.takeScreenShotAsFile().toPath());
    }

    //selenium
//    @Attachment(value = "Screenshot", type = "image/png")
//    public byte[] saveScreenshot(WebDriver driver) {
//        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//    }

    @Attachment(value = "Stacktrace", type = "text/plain")
    public String saveLogs(String message) {
        return message;
    }
}
