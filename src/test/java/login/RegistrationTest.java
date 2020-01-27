package login;

import org.testng.Assert;
import org.testng.annotations.Test;
import parentTest.ParentTest;

public class RegistrationTest extends ParentTest {

    @Test
    public void TestOpenRegistrationForm() {
        loginPage.openLoginPage();
        loginPage.clickOnFemalePhoto();
        loginPage.clickFemalePhotoLightHair();
        loginPage.clickFemalePhotoDarkEyes();
        loginPage.clickFemalePhotoCurvy();
        Assert.assertTrue(loginPage.isRegisterFormDisplayed(), "Registration form is not displayed");
    }

    @Test
    public void TestLogin() {
        loginPage.openLoginPage();
        loginPage.clickOnFemalePhoto();
        loginPage.clickFemalePhotoDarkHair();
        loginPage.clickFemalePhotoLightEyes();
        loginPage.clickFemalePhotoSlim();
        loginPage.enterLogin("Testuser");
        loginPage.enterEmail("qatest@testmail.com");

    }
}
