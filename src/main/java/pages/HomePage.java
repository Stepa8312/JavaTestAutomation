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

    @FindBy(xpath = "//div[@class='prof_add_avatar']")
    WebElement addUserAvatarButton;

    @FindBy(xpath = "//a[@class='add-ava-go-btn']")
    WebElement addAvatarCloseDialog;

    @FindBy(xpath = "//a[@class='add-ava-up-btn fileUpload']")
    WebElement uploadPhotoButton;

    @FindBy(xpath = "//div[@class='prof-ava']//div[contains(@class, 'avatar-placeholder')]")
    WebElement avatarPlaceholder;


    public void checkAndClosePopUp() {
        if (actionsWithOurElements.isElementDisplayed(mobileAppPopUp)) {
            actionsWithOurElements.clickOnElement(mobileAppPopUpCloseButton);
        }
    }

    public boolean isLogoDisplayed() {
        return actionsWithOurElements.isElementDisplayed(mainPageLogo);
    }

    public void uploadUserPhoto(String fileName) {
        actionsWithOurElements.clickOnElement(addUserAvatarButton);
        actionsWithOurElements.uploadFile(uploadPhotoButton, fileName);
        actionsWithOurElements.clickOnElement(addAvatarCloseDialog);
    }

    public boolean isUserHasAvatar() {
        return !avatarPlaceholder.getAttribute("class").contains("no-photo");
    }
}
