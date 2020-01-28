package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//div[@class=\"main-tile-logo\"]")
    WebElement mainPageLogo;

    @FindBy(xpath = "//div[@class='interstial-app-screen']")
    WebElement mobileAppPopUp;

    @FindBy(xpath = "//a[@class='interstial-close']")
    WebElement mobileAppPopUpCloseButton;

    public void checkAndClosePopUp() {
        if (actionsWithOurElements.isElementDisplayed(mobileAppPopUp)) {
            actionsWithOurElements.clickOnElement(mobileAppPopUpCloseButton);
        }
    }

    public boolean isLogoDisplayed() {
        return actionsWithOurElements.isElementDisplayed(mainPageLogo);
    }
}
