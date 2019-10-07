package test;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;

public class BaseTest {

    @AndroidBy(id = "com.ottawaeast.mka.reprotingappv10:id/nextbutton")
    private AndroidElement nextButton;


    public boolean nextButtonStatus() {
        return nextButton.isDisplayed();
    }
}
