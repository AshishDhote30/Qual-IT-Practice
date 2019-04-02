package Pages;


import Utils.Log;
import Utils.Utils;
import Utils.UserProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.After;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.PageFactory;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;



public class PageInitializer {

    private static WebDriver driver;
    static protected UserProfile userProfile;
    static protected HomePage homePage;
    static protected PurchasePage purchasePage;


    public static final String USERNAME = "Ashish30";
    public static final String ACCESS_KEY = "0d567847-3e55-4b4d-adba-09b7d874aba9";
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";


    public WebDriver Setup() {
        setSystemProperties();
        try {
            InitiateDriver();
        } catch (Exception e) {
        }
        InitializePages();
        return driver;
    }

    private void setSystemProperties() {
        String path = System.getProperty("user.dir") + "/drivers/";
        if (System.getProperty("os.name").contains("Windows 10")) {
            System.setProperty("webdriver.edge.driver", path + "win/MicrosoftWebDriver.exe");
        }
        if (System.getProperty("os.name").contains("Win")) {
            System.setProperty("webdriver.chrome.driver", path + "win/chromedriver.exe");
            System.setProperty("webdriver.ie.driver", path + "win/IEDriverServer.exe");
            System.setProperty("webdriver.firefox.marionette", path + "win/geckodriver.exe");
        } else if (System.getProperty("os.name").contains("Mac")) {
            System.setProperty("webdriver.chrome.driver", path + "mac/chromedriver");
            System.setProperty("webdriver.gecko.driver", path + "mac/geckodriver");
        } else {
            System.setProperty("webdriver.chrome.driver", path + "linux/chromedriver");
            System.setProperty("webdriver.gecko.driver", path + "linux/geckodriver");
        }
    }

    public static String getBrowserName() {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        return (cap.getBrowserName());

    }

    public void setUserProfile(String profile) {
        String file = System.getProperty("user.dir") + "/src/main/resources/userProfiles/" + profile.replace(" ", "") + ".json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            userProfile = mapper.readValue(new File(file), UserProfile.class);
        } catch (IOException e) {
            fail("User profile doesnt exist");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver WB) {
        this.driver = WB;
    }

    public void getPage(String _url) {
        Log.info("Getting URL: " + _url);
        driver.get(_url);
    }

    public void InitializePages() {
        homePage = PageFactory.initElements(driver, HomePage.class);
        purchasePage = PageFactory.initElements(driver, PurchasePage.class);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageURL() {
        return driver.getCurrentUrl();
    }

    public void getScreenShot() {
        try {
            Utils.fnScreenshot(getDriver());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private WebDriver InitiateDriver() throws Exception {
        String browser;
        Log.info("Initiating WebDriver");
        DesiredCapabilities cap = new DesiredCapabilities();
        String _browser = System.getProperty("browser");
        if (null != _browser && !_browser.isEmpty()) {
            browser = _browser;
        } else {
            browser = Utils.getConfigValue("default.browser");
        }
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions option = new ChromeOptions();
                option.setExperimentalOption("useAutomationExtension", false);
                option.addArguments("--disable-infobars");
                Map<String, Object> prefs = new HashMap<>();
                // Enable Flash
                /*prefs.put("profile.default_content_setting_values.plugins", 1);
                prefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
                prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
                // Hide save credentials prompt
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                option.setExperimentalOption("prefs", prefs);*/
                this.driver = new ChromeDriver(option);
                break;
            case "edge":
                if (System.getProperty("os.name").contains("Windows 10")) {
                    this.driver = new EdgeDriver();
                } else {
                    fail("Edge not supported on this OS");
                }
                break;
            case "ie":
                if (System.getProperty("os.name").contains("Win")) {
                    cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                    cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                    this.driver = new InternetExplorerDriver(cap);
                } else {
                    fail("ie is only supported on windows");
                }
                break;
            case "firefox":
                if (System.getProperty("os.name").contains("Mac")) {
                    FirefoxProfile profile = new FirefoxProfile();
                    profile.setPreference("plugin.state.flash", 1);
                    this.driver = new FirefoxDriver(profile);
                } else {
                    cap = DesiredCapabilities.firefox();
                    cap.setCapability("platform", "Windows 7");
                    this.driver = new FirefoxDriver(cap);
                }
                break;
            case "safari":
                SafariOptions options = new SafariOptions(); // commented as this was not working
                //options.setUseTechnologyPreview(true);
                //this.driver = new SafariDriver(options);
                this.driver = new SafariDriver();
                break;
            case "saucechrome":

                cap = DesiredCapabilities.chrome();
                cap.setCapability("platform", "Windows 7");
                cap.setCapability("version", "60.0");
                cap.setCapability("name", "Airport_Web_Automation_Chrome");
                this.driver = new RemoteWebDriver(new URL(URL), cap);
                break;

            case "saucesafari":

                cap = DesiredCapabilities.safari();
                cap.setCapability("platform", "macOS 10.13");
                cap.setCapability("version", "11.1");
                cap.setCapability("name", "Airport_Web_Automation_Safari");
                this.driver = new RemoteWebDriver(new URL(URL), cap);
                break;
            case "sauceedge":

                cap = DesiredCapabilities.edge();
                cap.setCapability("platform", "Windows 10");
                cap.setCapability("version", "16.16299");
                cap.setCapability("name", "Airport_Web_Automation_Edge");
                this.driver = new RemoteWebDriver(new URL(URL), cap);
                break;

            case "headless":
               ChromeOptions opt = new ChromeOptions();
                opt.addArguments("headless");
                opt.addArguments("window-size=1200x600");
                opt.setExperimentalOption("useAutomationExtension", false);
                opt.addArguments("--disable-infobars");
                prefs = new HashMap<>();
                // Enable Flash
              /*  prefs.put("profile.default_content_setting_values.plugins", 1);
                prefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
                prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);*/
                // Hide save credentials prompt
              /*  prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                opt.setExperimentalOption("prefs", prefs);*/
                this.driver = new ChromeDriver(opt);

                break;

            default:
                fail("Unknown browser");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return this.driver;
    }

    @After()
    private void teardown() {

        this.driver.close();
//        this.driver.quit();
    }
}


