package test.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MainPage extends BasePage {

    @AndroidFindBy(id = "com.ottawaeast.mka.reprotingappv10:id/nextbutton")
    private AndroidElement nextButton;

    @AndroidFindBy(id = "com.ottawaeast.mka.reprotingappv10:id/date")
    private AndroidElement datePickerButton;

    @AndroidFindBy(accessibility = "07 October 2019")
    private AndroidElement date;

    @AndroidFindBy(id = "android:id/button1")
    private AndroidElement acceptDateButton;

    @AndroidFindBy(id = "com.ottawaeast.mka.reprotingappv10:id/spinner")
    private AndroidElement spinner;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[6]")
    private AndroidElement mal;

    @AndroidFindBy(id = "android:id/text1")
    private AndroidElement spinnerField;

    public MainPage(AppiumDriver driver) {
        super(driver);
        waitUntilElementVisible(nextButton);
    }

    public MainPage openDatePicker(){
        waitUntilElementVisible(datePickerButton);
        datePickerButton.click();
        datePickerButton.click();
        return this;
    }

    public MainPage chooseDate(){
        waitUntilElementVisible(date);
        date.click();
        waitUntilElementVisible(acceptDateButton);
        acceptDateButton.click();
        return this;
    }

    public MainPage chooseOffice(){
        spinner.click();
        waitUntilElementVisible(mal);
        mal.click();
        return this;
    }

    public String getDate(){
        waitUntilElementVisible(datePickerButton);
        return datePickerButton.getText();
    }

    public InformationPage navigateToInformationPage(){
        nextButton.click();
        return new InformationPage(driver);
    }

    public String getTextFromSpinner(){
        return spinnerField.getText();
    }

    public boolean checkIsNextButtonOnThePage(){
        return nextButton.isDisplayed();
    }
}
