package libs;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class ActionsWithOurElements {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    WebDriverWait waitLow;

    public ActionsWithOurElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        waitLow = new WebDriverWait(webDriver, 1);
//        waitHigh = new WebDriverWait(webDriver, 30);
    }

    public void enterTextIntoElement(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
            logger.info("Value " + text + " was entered into field");
        } catch (Exception e) {
            logger.error("Cannot work with element" + e);
            Assert.fail("Cannot work with element" + e);
        }
    }

    public void clickOnElement(WebElement element) {
        try {
            waitLow.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            logger.error("Cannot work with element" + e);
            Assert.fail("Cannot work with element" + e);
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            waitLow.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (Exception e) {
            logger.info("Element not found");
            return false;
        }
    }

    public void selectValueInDD(WebElement element, String value) {
        try {
            Select select = new Select(element);
            select.selectByValue(value);
            logger.info("Value " + value + " was selected in DropDown");
        } catch (Exception e) {
            logger.error("Cannot work with element" + e);
            Assert.fail("Cannot work with element" + e);
        }
    }

    public void uploadFileRobot(String path) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Robot robot = new Robot();
            for (char c : path.toCharArray()) {
                robot.delay(50);
                if (c == ':') {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_SEMICOLON);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else if (c == '/') {
                    robot.keyPress(KeyEvent.VK_BACK_SLASH);
                } else if (c == '_') {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_MINUS);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else {
                    robot.keyPress(KeyStroke.getKeyStroke(Character.toUpperCase(c), 0).getKeyCode());
                    robot.keyRelease(KeyStroke.getKeyStroke(Character.toUpperCase(c), 0).getKeyCode());
                }
            }
            robot.keyPress(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void uploadFile(WebElement element, String path) {
        File f = new File(path);
        final String filePath = f.getAbsolutePath();
        if (f.exists()) {
            logger.info("upload file " + filePath);
            Thread upload = new Thread() {
                @Override
                public void run() {
                    uploadFileRobot(filePath);
                }
            };
            upload.start();
            element.click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                upload.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            logger.error("file " + path + " not found");
            Assert.fail("file " + path + " not found");
        }
    }
}
