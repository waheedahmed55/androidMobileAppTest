package test.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import test.pages.MainPage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;

public class BaseTest {

    AppiumDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup() throws MalformedURLException, InstantiationException {
        Properties properties = getProperties();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion",properties.getProperty("platformVersion") );
        capabilities.setCapability("udid", properties.getProperty("udid"));
        capabilities.setCapability("deviceName",properties.getProperty("deviceName"));
        capabilities.setCapability("platformName",properties.getProperty("platformName"));
        capabilities.setCapability("appPackage", properties.getProperty("appPackage"));
        capabilities.setCapability("appActivity",properties.getProperty("appActivity"));

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    protected MainPage navigateToMainPage(){
        return getMainPage(driver);
    }

    protected MainPage getMainPage(AppiumDriver driver){
        return new MainPage(driver);
    }

    private Properties getProperties() throws InstantiationException {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {

            inputStream = BaseTest.class
                    .getResourceAsStream("/config.properties");
            if (inputStream == null) {
                throw new RuntimeException("properties is null");
            }
            properties.load(inputStream);

            if (properties == null) {
                throw new InstantiationException("properties are missing");
            }

        } catch (IOException e) {

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {

                }

            }
        }
        return properties;
    }

    @Attachment(value = "{0}", type = "text/plain")
    private String attachFile(String attachName, String message) {
        return Optional.ofNullable(message).orElse(null);
    }

    @Attachment(value = "{0}", type = "image/png")
    private synchronized byte[] attachScreenshot(String screenshotName) {
        if (driver == null) {
            return null;
        }

        return driver.getScreenshotAs(OutputType.BYTES);
    }

    private void attachScreenshotAndFiles(ITestResult result) {
        try {
            attachScreenshot("screenshot_web.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (result != null && !result.isSuccess() && driver != null) {
                Optional
                        .ofNullable(result.getThrowable())
                        .ifPresent(error -> attachFile("error_web.txt", error.getMessage()));

                attachFile("page-source_web.txt", driver.getPageSource());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver(ITestResult result) {
        attachScreenshotAndFiles(result);
        if (driver != null) {
            driver.quit();
        }
    }

}
