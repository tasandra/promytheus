package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import pages.LoginPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void baseBeforeClass(String browser, String url)  throws MalformedURLException {
//  run firefox
        if(browser.equalsIgnoreCase("firefox")) {

            driver = new FirefoxDriver();
            driver.get(url);

            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

// run chrome
        }else if (browser.equalsIgnoreCase("chrome")) {

            driver = new ChromeDriver();
            driver.get(url);

            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

// run appium
        }else if(browser.equalsIgnoreCase("appium")){

            // Create object of  DesiredCapabilities class and specify android platform
            DesiredCapabilities capabilities= DesiredCapabilities.android();

            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);

            capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);

            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");

            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Android Emulator");

            capabilities.setCapability(MobileCapabilityType.VERSION,"8.0.0");

            URL urlHub = new URL("http://127.0.0.1:4723/wd/hub");

            driver = new AndroidDriver(urlHub, capabilities);
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

// run saucelabs
        }else if(browser.equalsIgnoreCase("saucelabs")){
            String USERNAME = "tasandra";
            String ACCESS_KEY = "0bfb113f-bd86-46ad-b8a4-63eaee7ee76a";
            String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

            DesiredCapabilities caps = DesiredCapabilities.chrome();
            caps.setCapability("platform", "Linux");
            caps.setCapability("version", "47.0");

//            DesiredCapabilities caps = DesiredCapabilities.android();
//            caps.setCapability("platform", "Linux");
//            caps.setCapability("version", "4.4");
//            caps.setCapability("deviceName","Samsung Galaxy S3 Emulator");
//            caps.setCapability("deviceOrientation", "portrait");

//            DesiredCapabilities caps = DesiredCapabilities.iphone();
//            caps.setCapability("appiumVersion", "1.5.1");
//            caps.setCapability("deviceName","iPhone 6");
//            caps.setCapability("deviceOrientation", "portrait");
//            caps.setCapability("platformVersion","8.4");
//            caps.setCapability("platformName", "iOS");
//            caps.setCapability("browserName", "Safari");

//            DesiredCapabilities caps = DesiredCapabilities.safari();
//            caps.setCapability("platform", "OS X 10.9");
//            caps.setCapability("version", "7.0");
//
            driver = new RemoteWebDriver(new URL(URL), caps);

            driver.get(url);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

// run parallel on mac
        }else if (browser.equalsIgnoreCase("remote")){
            String remote = "http://192.168.0.4:5555/wd/hub";
            DesiredCapabilities cap = null;

            cap = new DesiredCapabilities().chrome();
            cap.setBrowserName("chrome");
            cap.setPlatform(Platform.MAC);
            driver = new RemoteWebDriver(new URL(remote), cap);
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        }
    }

    @AfterClass
    public void baseAfterClass(){
        driver.close();
        driver.quit();
    }

    @Test
    public void checkLogo(){
        LoginPage login = new LoginPage(driver);
        login.getLogo();
    }

}
