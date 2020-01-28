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

    @FindBy(xpath = "//input[@type='file']")
    WebElement uploadPhotoInput;

    @FindBy(xpath = "//a[contains(@href, 'page=photo')]")
    WebElement photoMenuItem;

    @FindBy(xpath = "//a[@class='prof-photo-list-upload-btn']")
    WebElement uploadPhotoButton;

    @FindBy(xpath = "//a[@class='add-btn']")
    WebElement addSelectedPhotoButton;

    public void checkAndClosePopUp() {
        if (actionsWithOurElements.isElementDisplayed(mobileAppPopUp)) {
            actionsWithOurElements.clickOnElement(mobileAppPopUpCloseButton);
        }
    }

    public boolean isLogoDisplayed() {
        return actionsWithOurElements.isElementDisplayed(mainPageLogo);
    }

    public void uploadUserPhoto(String fileName) {
        actionsWithOurElements.clickOnElement(photoMenuItem);
        actionsWithOurElements.clickOnElement(uploadPhotoButton);
        actionsWithOurElements.uploadFile(uploadPhotoInput, fileName);
        actionsWithOurElements.clickOnElement(addSelectedPhotoButton);

    }
}
