package login;

import org.testng.annotations.Test;
import parentTest.ParentTest;

public class RegistrationTest extends ParentTest {

    @Test
    public void LoginTest() {
        loginPage.openLoginPage();
        loginPage.clickOnFemalePhoto();

    }
}
