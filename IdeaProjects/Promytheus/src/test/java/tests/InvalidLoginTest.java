package tests;

import menus.HomeMenu;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;

public class InvalidLoginTest extends BaseTest {
    private LoginPage loginPage;
    private HomeMenu menu;

    @BeforeClass
    public void beforeClass(){
        loginPage = new LoginPage(driver);
        menu = new HomeMenu(driver);
    }

    @Test
    // log in with empty fields
    public void emptyEmail() {
        loginPage.submitLogin("", "");
        String error = loginPage.getError();

        assertEquals("This value is required.", error);
    }

    @Test
    // log in with empty email field
    public void emptyFields() {
        loginPage.submitLogin("", "password");
        String error = loginPage.getError();

        assertEquals("This value is required.", error);
    }

    @Test
    // log in with empty password field
    public void emptyPassword() {
        loginPage.submitLogin("kusiwa@cmail.club", "");
        String error = loginPage.getEmptyPassError();

        assertEquals("This value is required.", error);
    }

    @Test
    // log in with invalid email
    public void invalidEmail() {
        loginPage.submitLogin("kusiwa@", "password");
        String error = loginPage.getInvalidError();

        assertEquals("Invalid Email or Password.", error);
    }

    @Test
    // log in with invalid password
    public void invalidPassword() {
        loginPage.submitLogin("kusiwa@cmail.club", "pass");
        String error = loginPage.getInvalidError();

        assertEquals("Invalid Email or Password.", error);
    }

}
