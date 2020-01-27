package login;

import org.testng.Assert;
import org.testng.annotations.Test;
import parentTest.ParentTest;

public class RegistrationTest extends ParentTest {

    @Test
    public void TestOpenRegistrationForm() {
        loginPage.openLoginPage();
        loginPage.clickOnFemalePhoto();
        loginPage.clickFemalePhotoDarkHair();
        loginPage.clickFemalePhotoDarkEyes();
        loginPage.clickFemalePhotoSlim();
        Assert.assertTrue(loginPage.isRegisterFormDisplayed(), "Registration form is not displayed");
    }
}
