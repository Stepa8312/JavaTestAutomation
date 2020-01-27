package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends ParentPage {

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

   @FindBy(xpath = "//div[@data-gender='f']")
    WebElement femalePhoto;

    @FindBy(xpath = "//div[@data-gender='m']")
    WebElement malePhoto;

    @FindBy(xpath = "//div[@style='display: block;']//img[@alt='Темные']")
    WebElement chooseDarkHair;

    @FindBy(xpath = "//div[@style='display: block;']//img[@alt='Светлые']")
    WebElement chooseLightHair;


    @FindBy(xpath = "//form[@name='reg-form']")
    WebElement registrationForm;

    public void openLoginPage() {
        try{
            webDriver.get("https://m.hitwe.com/landing/inter?p=15276");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Cannot open Login Page");
            Assert.fail("Cannot open Login Page");
        }
    }

    public void clickOnFemalePhoto() {
        logger.info("Click on Female photo");
        actionsWithOurElements.clickOnElement(femalePhoto);
    }

    public void clickOnMalePhoto() {
        logger.info("Click on Male photo");
        actionsWithOurElements.clickOnElement(malePhoto);
    }

    public boolean isRegisterFormDisplayed() {
        return actionsWithOurElements.isElementDisplayed(registrationForm);
    }
}
