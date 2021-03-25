package parentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
    String browser = "chrome";
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

//        if ("chrome".equals(browser) || browser == null) {
//            File file = new File("./src/drivers/chromedriver.exe");
//            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
//            webDriver = new ChromeDriver();
//        } else if ("firefox".equals(browser)) {
//            File file = new File("./src/drivers/geckodriver.exe");
//            System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
//            FirefoxOptions profile = new FirefoxOptions();
//            profile.addPreference("browser.startup.page", 0);
//            profile.addPreference("browser.startup.homepage_override.mstone", "ignore");
//            webDriver = new FirefoxDriver();
//        }

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
