package tests;

import menus.HomeMenu;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ValidLoginTest extends BaseTest {


    @Test(groups = "p1")
    // log in with valid credentials
    public void validLogin() {

        loginPage.submitLogin("kusiwa@cmail.club", "password");

        WebElement logo = home.getHomeMenuLogo();
        assertTrue(logo.isDisplayed(), "User is not able to login - logo not displayed");
    }

}
