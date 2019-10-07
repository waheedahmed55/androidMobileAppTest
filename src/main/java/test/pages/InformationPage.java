package test.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class InformationPage extends BasePage {

    @AndroidFindBy(id = "com.ottawaeast.mka.reprotingappv10:id/Emailbutton")
    private AndroidElement emailButton;

    public InformationPage(AppiumDriver driver) {
        super(driver);
        waitUntilElementVisible(emailButton);
    }

    public boolean checkIsEmailButtonOnThePage(){
        return emailButton.isDisplayed();
    }
}
