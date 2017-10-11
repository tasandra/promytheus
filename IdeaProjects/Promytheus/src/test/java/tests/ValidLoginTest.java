package tests;

import menus.HomeMenu;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;

public class ValidLoginTest extends BaseTest {
    private LoginPage loginPage;
    private HomeMenu menu;

    @BeforeClass
    public void loginBeforeClass(){
        loginPage = new LoginPage(driver);
        menu = new HomeMenu(driver);
    }

    @Test
    // log in with valid credentials
    public void validLogin() {

        loginPage.submitLogin("kusiwa@cmail.club", "password");

        WebElement logo = menu.getHomeMenuLogo();
        assertEquals(true, logo.isDisplayed());
    }

}
