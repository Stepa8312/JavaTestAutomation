package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends ParentPage {

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try{
            webDriver.get("https://m.hitwe.com/landing/inter?p=15276");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Cannot open Login Page");
            Assert.fail("Cannot open Login Page");
        }
    }
}
