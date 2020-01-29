package login;

import net.bytebuddy.utility.RandomString;
import org.testng.Assert;
import org.testng.annotations.Test;
import parentTest.ParentTest;

public class RegistrationTest extends ParentTest {

    @Test(priority = 1)
    public void TestOpenRegistrationForm() {
        loginPage.openLoginPage();
        loginPage.clickOnFemalePhoto();
        loginPage.clickFemalePhotoLightHair();
        loginPage.clickFemalePhotoDarkEyes();
        loginPage.clickFemalePhotoCurvy();
        Assert.assertTrue(loginPage.isRegisterFormDisplayed(), "Registration form is not displayed");
    }

    @Test(testName = "Registration positive scenarios", priority = 1)
    public void TestRegistrationAndUploadPhoto() {
        loginPage.openLoginPage();
        loginPage.clickOnFemalePhoto();
        loginPage.clickFemalePhotoDarkHair();
        loginPage.clickFemalePhotoLightEyes();
        loginPage.clickFemalePhotoSlim();
        Assert.assertTrue(loginPage.isRegisterFormDisplayed(), "Registration form is not displayed");
        loginPage.enterLogin("Testuser");
        loginPage.enterEmail(RandomString.make(5) + "@testmail.com");
        loginPage.selectGender("f");
        loginPage.selectAge("25");
        loginPage.clickRegisterButton();
        homePage.checkAndClosePopUp();
        Assert.assertTrue(homePage.isLogoDisplayed(), "Main logo is not displayed");
        Assert.assertFalse(homePage.isUserHasAvatar(), "User already has avatar");
        homePage.uploadUserPhoto("data/avatar.jpg");
        Assert.assertTrue(homePage.isUserHasAvatar(), "User avatar is not displayed");
    }
}
