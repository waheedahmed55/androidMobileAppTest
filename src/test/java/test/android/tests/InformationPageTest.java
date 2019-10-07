package test.android.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.android.BaseTest;
import test.pages.InformationPage;

public class InformationPageTest extends BaseTest {

    @Test
    public void information(){
        InformationPage informationPage = navigateToMainPage()
                .navigateToInformationPage();

        boolean isEmailButtonIsDisplayed =  informationPage
                .checkIsEmailButtonOnThePage();

        Assert.assertTrue(isEmailButtonIsDisplayed);
    }


}
