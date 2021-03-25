package parentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import pages.HomePage;
import pages.LoginPage;

public class ParentTest {
    WebDriver webDriver;
    protected LoginPage loginPage;
    protected HomePage homePage;

    public static final String HUB_URL = "http://localhost:4444/wd/hub";

    private static boolean remoteWebDriver = false;
    static {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(HUB_URL + "/status").openConnection();
            try {
                con.setRequestMethod("GET");
                remoteWebDriver = con.getResponseCode() == HttpURLConnection.HTTP_OK;
            } finally {
                con.disconnect();
            }
        } catch (IOException ignore) {}

        if (!remoteWebDriver) {
            WebDriverManager.chromedriver().setup();
        }
    }

    @BeforeTest
    public void setUp() {

        if (remoteWebDriver) {
            try {
                this.webDriver = new RemoteWebDriver(new URL(HUB_URL), DesiredCapabilities.chrome());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        } else {
            this.webDriver = new ChromeDriver();
        }

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
    }

    @AfterTest
    public void tearDown() {
        webDriver.quit();
    }

}
