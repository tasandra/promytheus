package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    //private String pageUrl = "http://ec2-52-53-181-39.us-west-1.compute.amazonaws.com";

    @Parameters({"browser", "url"})
    @BeforeClass
    public void baseBeforeClass(String browser, String url){
//        driver = new ChromeDriver();
//        driver.get(pageUrl);
        if(browser.equalsIgnoreCase("firefox")) {

            driver = new FirefoxDriver();
            driver.get(url);


        }else if (browser.equalsIgnoreCase("chrome")) {

            driver = new ChromeDriver();
            driver.get(url);

            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 5);
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
