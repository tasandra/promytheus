package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;

    //private String pageUrl = "http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com";

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
        }
    }

    @AfterClass
    public void baseAfterClass(){
        driver.quit();
    }

    @Test
    public void checkLogo(){
        LoginPage login = new LoginPage(driver);
        login.getLogo();
    }

}
