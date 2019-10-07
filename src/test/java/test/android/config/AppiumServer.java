package test.android.config;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppiumServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppiumServer.class);
    private static final String SERVER_IP = "127.0.0.1";

    private AppiumDriverLocalService appiumService;
    private int port;

    public AppiumServer build(){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,true);
        desiredCapabilities.setCapability(CapabilityType.ForSeleniumServer.PROXYING_EVERYTHING,true);

        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withIPAddress(SERVER_IP)
                .usingPort(port)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "debug")
                .withArgument(GeneralServerFlag.ASYNC_TRACE)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.RELAXED_SECURITY)
                .withCapabilities(desiredCapabilities);

        appiumService = AppiumDriverLocalService.buildService(builder);

        return this;
    }

    public AppiumServer start(){
        appiumService.start();

        return this;
    }

    public AppiumServer stop(){
        appiumService.stop();

        return this;
    }
}
